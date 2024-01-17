package io.github.microserviceproject.mscreditappraiser.application;

import feign.FeignException;
import io.github.microserviceproject.mscreditappraiser.application.exception.ClientDataNotFoundException;
import io.github.microserviceproject.mscreditappraiser.application.exception.CommunicationErrorMicroservicesException;
import io.github.microserviceproject.mscreditappraiser.application.exception.ErrorInCardRequestException;
import io.github.microserviceproject.mscreditappraiser.domain.model.ApprovedCard;
import io.github.microserviceproject.mscreditappraiser.domain.model.Card;
import io.github.microserviceproject.mscreditappraiser.domain.model.CardIssuanceRequestData;
import io.github.microserviceproject.mscreditappraiser.domain.model.CardRequestProtocol;
import io.github.microserviceproject.mscreditappraiser.domain.model.ClientCard;
import io.github.microserviceproject.mscreditappraiser.domain.model.ClientData;
import io.github.microserviceproject.mscreditappraiser.domain.model.ClientSituation;
import io.github.microserviceproject.mscreditappraiser.domain.model.ReturnOfClientEvaluation;
import io.github.microserviceproject.mscreditappraiser.infra.client.CardsResource;
import io.github.microserviceproject.mscreditappraiser.infra.client.ClientResource;
import io.github.microserviceproject.mscreditappraiser.infra.mqueue.CardIssuanceRequestPublisher;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

  private final ClientResource clientResource;
  private final CardsResource cardsResource;
  private final CardIssuanceRequestPublisher CardIssuancePublisher;

  public ClientSituation getClientSituation(String cpf)
      throws ClientDataNotFoundException, CommunicationErrorMicroservicesException {

    try {
      ResponseEntity<ClientData> clientDataResponse = clientResource.clientData(cpf);
      ResponseEntity<List<ClientCard>> clientCardResponse = cardsResource.getCardsByClient(cpf);

      return ClientSituation
          .builder()
          .client(clientDataResponse.getBody())
          .cards(clientCardResponse.getBody())
          .build();

    } catch (FeignException.FeignClientException e) {
      int status = e.status();

      if (HttpStatus.NOT_FOUND.value() == status) {
        throw new ClientDataNotFoundException();
      }
      throw new CommunicationErrorMicroservicesException(e.getMessage(), e.status());
    }
  }

  public ReturnOfClientEvaluation carryOutAssessment(String cpf, Long income)
      throws ClientDataNotFoundException, CommunicationErrorMicroservicesException {
    try {
      ResponseEntity<ClientData> clientDataResponse = clientResource.clientData(cpf);
      ResponseEntity<List<Card>> cardsResponse =
          cardsResource.getCardsIncomeLessThanOrEqualTo(income);

      List<Card> cards = cardsResponse.getBody();

      List<ApprovedCard> cardList = cards.stream().map(card -> {
        ClientData clientData = clientDataResponse.getBody();

        BigDecimal basicLimit = card.getBasicLimit();
        BigDecimal ageBD = BigDecimal.valueOf(clientData.getAge());

        BigDecimal factor = ageBD.divide(BigDecimal.valueOf(10));
        BigDecimal approvedLimit = factor.multiply(basicLimit);

        ApprovedCard approvedCard = new ApprovedCard();
        approvedCard.setCard(card.getName());
        approvedCard.setBrand(card.getBrand());
        approvedCard.setApprovedLimit(approvedLimit);

        return approvedCard;
      }).toList();

      return new ReturnOfClientEvaluation(cardList);

    } catch (FeignException.FeignClientException e) {
      int status = e.status();

      if (HttpStatus.NOT_FOUND.value() == status) {
        throw new ClientDataNotFoundException();
      }
      throw new CommunicationErrorMicroservicesException(e.getMessage(), e.status());
    }
  }

  public CardRequestProtocol requestCardIssuance(CardIssuanceRequestData data) {
    try {
      this.CardIssuancePublisher.requestCard(data);
      var protocol = UUID.randomUUID().toString();
      return new CardRequestProtocol(protocol);

    } catch (Exception e) {
      throw new ErrorInCardRequestException(e.getMessage());
    }
  }
}

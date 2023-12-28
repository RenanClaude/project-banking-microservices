package io.github.microserviceproject.mscreditappraiser.application;

import io.github.microserviceproject.mscreditappraiser.domain.model.ClientCard;
import io.github.microserviceproject.mscreditappraiser.domain.model.ClientData;
import io.github.microserviceproject.mscreditappraiser.domain.model.ClientSituation;
import io.github.microserviceproject.mscreditappraiser.infra.client.CardsResource;
import io.github.microserviceproject.mscreditappraiser.infra.client.ClientResource;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

  private final ClientResource clientResource;
  private final CardsResource cardsResource;

  public ClientSituation getClientSituation(String cpf) {
    ResponseEntity<ClientData> clientDataResponse = clientResource.clientData(cpf);
    ResponseEntity<List<ClientCard>> clientCardResponse = cardsResource.getCardsByClient(cpf);

    return ClientSituation
        .builder()
        .client(clientDataResponse.getBody())
        .cards(clientCardResponse.getBody())
        .build();
  }
}

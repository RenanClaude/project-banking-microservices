package io.github.microserviceproject.mscards.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.microserviceproject.mscards.application.ClientCard;
import io.github.microserviceproject.mscards.domain.Card;
import io.github.microserviceproject.mscards.domain.CardIssuanceRequestData;
import io.github.microserviceproject.mscards.infra.repository.CardRepository;
import io.github.microserviceproject.mscards.infra.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardIssuanceSubscriber {

  private final CardRepository cardRepository;
  private final ClientCardRepository clientCardRepository;

  @RabbitListener(queues = "${mq.queues.card-issuances}")
  public void receiveIssuanceRequest(@Payload String payload) {
    try {
      var mapper = new ObjectMapper();
      CardIssuanceRequestData data = mapper.readValue(payload, CardIssuanceRequestData.class);

      Card card = cardRepository.findById(data.getCardId()).orElseThrow();
      ClientCard clientCard = new ClientCard();
      clientCard.setCard(card);
      clientCard.setCpf(data.getCpf());
      clientCard.setLimit(data.getLimitReleased());

      clientCardRepository.save(clientCard);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}

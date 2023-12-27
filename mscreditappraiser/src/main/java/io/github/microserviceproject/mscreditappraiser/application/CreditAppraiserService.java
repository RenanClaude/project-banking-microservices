package io.github.microserviceproject.mscreditappraiser.application;

import io.github.microserviceproject.mscreditappraiser.domain.model.ClientCard;
import io.github.microserviceproject.mscreditappraiser.domain.model.ClientData;
import io.github.microserviceproject.mscreditappraiser.domain.model.ClientSituation;
import io.github.microserviceproject.mscreditappraiser.infra.client.ResourseClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

  private final ResourseClient resourseClient;

  public ClientSituation getClientSituation(String cpf) {
    ResponseEntity<ClientData> clientDataResponse = resourseClient.clientData(cpf);

    return ClientSituation
        .builder()
        .client(clientDataResponse.getBody())
        .build();
  }
}

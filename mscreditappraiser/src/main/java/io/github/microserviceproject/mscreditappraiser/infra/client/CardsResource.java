package io.github.microserviceproject.mscreditappraiser.infra.client;

import io.github.microserviceproject.mscreditappraiser.domain.model.ClientCard;
import io.github.microserviceproject.mscreditappraiser.domain.model.ClientData;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mscards", path = "/cards")
public interface CardsResource {

  @GetMapping(params = "cpf")
  ResponseEntity<List<ClientCard>> getCardsByClient(@RequestParam("cpf") String cpf);

}

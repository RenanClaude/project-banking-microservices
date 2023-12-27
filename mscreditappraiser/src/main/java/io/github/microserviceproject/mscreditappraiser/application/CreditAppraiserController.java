package io.github.microserviceproject.mscreditappraiser.application;

import io.github.microserviceproject.mscreditappraiser.domain.model.ClientSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("credit-appraiser")
@RequiredArgsConstructor
public class CreditAppraiserController {

  private final CreditAppraiserService creditAppraiserService;

  @GetMapping
  public String status() {
    return "OK";
  }

  @GetMapping(value = "client-situation", params = "cpf")
  public ResponseEntity<ClientSituation> checkClientSituation(@RequestParam("cpf") String cpf) {
    ClientSituation clientSituation = creditAppraiserService.getClientSituation(cpf);

    return ResponseEntity.status(HttpStatus.OK).body(clientSituation);
  }

}

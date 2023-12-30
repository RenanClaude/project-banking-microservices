package io.github.microserviceproject.mscreditappraiser.application;

import io.github.microserviceproject.mscreditappraiser.application.exception.ClientDataNotFoundException;
import io.github.microserviceproject.mscreditappraiser.application.exception.CommunicationErrorMicroservicesException;
import io.github.microserviceproject.mscreditappraiser.application.exception.ErrorInCardRequestException;
import io.github.microserviceproject.mscreditappraiser.domain.model.AssessmentData;
import io.github.microserviceproject.mscreditappraiser.domain.model.CardIssuanceRequestData;
import io.github.microserviceproject.mscreditappraiser.domain.model.CardRequestProtocol;
import io.github.microserviceproject.mscreditappraiser.domain.model.ClientSituation;
import io.github.microserviceproject.mscreditappraiser.domain.model.ReturnOfClientEvaluation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
  public ResponseEntity checkClientSituation(@RequestParam("cpf") String cpf) {
    try {
      ClientSituation clientSituation = creditAppraiserService.getClientSituation(cpf);
      return ResponseEntity.status(HttpStatus.OK).body(clientSituation);

    } catch (ClientDataNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    } catch (CommunicationErrorMicroservicesException e) {
      return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
    }
  }

  @PostMapping
  public ResponseEntity carryOutTheAssessment(@RequestBody AssessmentData data) {
    try {
      ReturnOfClientEvaluation returnOfClientEvaluation =
          creditAppraiserService.carryOutAssessment(data.getCpf(), data.getIncome());

      return ResponseEntity.status(HttpStatus.OK).body(returnOfClientEvaluation);

    } catch (ClientDataNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    } catch (CommunicationErrorMicroservicesException e) {
      return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
    }
  }

  @PostMapping("card-requests")
  public ResponseEntity requestCard(CardIssuanceRequestData data) {
    try {
      CardRequestProtocol cardRequestProtocol =
          this.creditAppraiserService.requestCardIssuance(data);
      return ResponseEntity.ok(cardRequestProtocol);

    } catch (ErrorInCardRequestException e) {
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }
}

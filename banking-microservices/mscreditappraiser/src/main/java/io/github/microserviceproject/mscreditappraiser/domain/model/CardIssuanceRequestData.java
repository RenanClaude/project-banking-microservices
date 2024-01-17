package io.github.microserviceproject.mscreditappraiser.domain.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CardIssuanceRequestData {

  private Long cardId;
  private String cpf;
  private String address;
  private BigDecimal limitReleased;


}

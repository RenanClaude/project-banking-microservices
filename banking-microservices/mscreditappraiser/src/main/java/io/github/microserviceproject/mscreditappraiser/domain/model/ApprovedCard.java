package io.github.microserviceproject.mscreditappraiser.domain.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ApprovedCard {

  private String card;
  private String brand;
  private BigDecimal approvedLimit;
}

package io.github.microserviceproject.mscreditappraiser.domain.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ClientCard {

  private String name;
  private String brand;
  private BigDecimal limitReleased;
}

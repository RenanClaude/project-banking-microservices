package io.github.microserviceproject.mscards.application.representation;

import io.github.microserviceproject.mscards.domain.Card;
import io.github.microserviceproject.mscards.domain.CardBrand;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CardSaveRequest {

  private String name;
  private CardBrand brand;
  private BigDecimal income;
  private BigDecimal basicLimit;

  public Card toModel() {
    return new Card(name, brand, income, basicLimit);
  }

}

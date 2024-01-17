package io.github.microserviceproject.mscards.application.representation;

import io.github.microserviceproject.mscards.application.ClientCard;
import io.github.microserviceproject.mscards.domain.CardBrand;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsByClientResponse {

  private String name;
  private String brand;
  private BigDecimal limitReleased;

  public static CardsByClientResponse fromModel(ClientCard model) {
    return new CardsByClientResponse(
        model.getCard().getName(),
        model.getCard().getBrand().toString(),
        model.getLimit()
    );
  }

}

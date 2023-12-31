package io.github.microserviceproject.mscards.domain;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Card {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @Enumerated(EnumType.STRING)
  private CardBrand brand;
  private BigDecimal income;
  private BigDecimal basicLimit;

  public Card(String name, CardBrand brand, BigDecimal income, BigDecimal basicLimit) {
    this.name = name;
    this.brand = brand;
    this.income = income;
    this.basicLimit = basicLimit;
  }
}

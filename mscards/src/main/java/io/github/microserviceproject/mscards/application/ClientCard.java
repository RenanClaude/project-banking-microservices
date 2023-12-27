package io.github.microserviceproject.mscards.application;

import io.github.microserviceproject.mscards.domain.Card;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class ClientCard {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String cpf;
  @ManyToOne
  @JoinColumn(name = "id_card")
  private Card card;
  @Column(name = "card_limit")
  private BigDecimal limit;


}

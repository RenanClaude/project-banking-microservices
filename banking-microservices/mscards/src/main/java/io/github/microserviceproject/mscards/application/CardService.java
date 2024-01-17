package io.github.microserviceproject.mscards.application;

import io.github.microserviceproject.mscards.domain.Card;
import io.github.microserviceproject.mscards.infra.repository.CardRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardService {

  private final CardRepository repository;

  @Transactional
  public Card saveCard(Card card) {
    return repository.save(card);
  }

  public List<Card> getCardsIncomeLessThanOrEqualTo(Long income) {
    BigDecimal incomeBigDecimal = BigDecimal.valueOf(income);
    return repository.findByIncomeLessThanEqual(incomeBigDecimal);
  }
}

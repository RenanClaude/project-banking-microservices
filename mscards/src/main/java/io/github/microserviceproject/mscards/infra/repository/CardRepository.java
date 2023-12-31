package io.github.microserviceproject.mscards.infra.repository;

import io.github.microserviceproject.mscards.domain.Card;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

  List<Card> findByIncomeLessThanEqual(BigDecimal income);
}

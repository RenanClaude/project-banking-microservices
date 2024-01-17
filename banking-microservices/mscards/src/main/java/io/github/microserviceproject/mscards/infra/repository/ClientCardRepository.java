package io.github.microserviceproject.mscards.infra.repository;

import io.github.microserviceproject.mscards.application.ClientCard;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientCardRepository extends JpaRepository<ClientCard, Long> {

  List<ClientCard> findByCpf(String cpf);
}

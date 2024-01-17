package io.github.microserviceproject.msclient.infra.repository;

import io.github.microserviceproject.msclient.domain.Client;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

  Optional<Client> findByCpf(String cpf);
}

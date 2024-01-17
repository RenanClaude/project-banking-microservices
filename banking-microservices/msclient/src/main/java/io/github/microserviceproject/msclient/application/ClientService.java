package io.github.microserviceproject.msclient.application;

import io.github.microserviceproject.msclient.domain.Client;
import io.github.microserviceproject.msclient.infra.repository.ClientRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientService {

  private final ClientRepository repository;

  @Transactional
  public Client save(Client client) {
    return repository.save(client);
  }

public Optional<Client> getByCpf(String cpf) {
    return repository.findByCpf(cpf);
}




}

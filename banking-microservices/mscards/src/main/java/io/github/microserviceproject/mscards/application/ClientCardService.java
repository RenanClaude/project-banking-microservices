package io.github.microserviceproject.mscards.application;

import io.github.microserviceproject.mscards.infra.repository.ClientCardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientCardService {

  private final ClientCardRepository repository;

  public List<ClientCard> listCardsByCpf(String cpf) {
    return repository.findByCpf(cpf);
  }
}

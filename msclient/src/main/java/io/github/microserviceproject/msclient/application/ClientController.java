package io.github.microserviceproject.msclient.application;

import io.github.microserviceproject.msclient.application.representation.ClientSaveRequest;
import io.github.microserviceproject.msclient.domain.Client;
import java.net.URI;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class ClientController {

  private final ClientService service;

  @GetMapping
  public String status() {

    return "OK";
  }

  @PostMapping
  public ResponseEntity saveClient(@RequestBody ClientSaveRequest request) {
    Client client = request.toModel();
    service.save(client);

    URI headerLocation = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .query("cpf={cpf}")
        .buildAndExpand(client.getCpf()).toUri();

    return ResponseEntity.created(headerLocation).build();
  }

  @GetMapping(params = "cpf")
  public ResponseEntity clientData(@RequestParam("cpf") String cpf) {
    Optional<Client> client = service.getByCpf(cpf);

    if (client.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(client.get());
  }

}

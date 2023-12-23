package io.github.microserviceproject.msclient.application.representation;

import io.github.microserviceproject.msclient.domain.Client;
import lombok.Data;

@Data
public class ClientSaveRequest {

  private String cpf;
  private String name;
  private Integer age;


  public Client toModel() {
    return new Client(cpf, name, age);
  }
}

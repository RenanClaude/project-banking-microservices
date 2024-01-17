package io.github.microserviceproject.mscreditappraiser.application.exception;

public class ClientDataNotFoundException extends Exception {

  public ClientDataNotFoundException() {
    super("Customer data not found for the CPF entered!");
  }
}

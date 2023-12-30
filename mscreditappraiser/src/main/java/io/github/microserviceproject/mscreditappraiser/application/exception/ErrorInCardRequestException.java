package io.github.microserviceproject.mscreditappraiser.application.exception;

public class ErrorInCardRequestException extends RuntimeException {

  public ErrorInCardRequestException(String message) {
    super(message);
  }
}

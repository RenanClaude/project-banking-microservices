package io.github.microserviceproject.mscreditappraiser.application.exception;

import lombok.Getter;

public class CommunicationErrorMicroservicesException extends Exception {

  @Getter
  private Integer status;

  public CommunicationErrorMicroservicesException(String msg, Integer status) {
    super(msg);
    this.status = status;
  }
}

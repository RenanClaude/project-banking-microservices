package io.github.microserviceproject.mscreditappraiser.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientSituation {

  private ClientData client;
  private List<ClientCard> cards;
}

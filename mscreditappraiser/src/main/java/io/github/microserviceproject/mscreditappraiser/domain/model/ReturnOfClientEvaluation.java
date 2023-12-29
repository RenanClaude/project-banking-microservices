package io.github.microserviceproject.mscreditappraiser.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnOfClientEvaluation {
  private List<ApprovedCard> cards;
}

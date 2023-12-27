package io.github.microserviceproject.mscards.application;

import io.github.microserviceproject.mscards.application.representation.CardSaveRequest;
import io.github.microserviceproject.mscards.domain.Card;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardController {

  private final CardService service;

  @GetMapping
  public String status() {
    return "OK";
  }

  @PostMapping
  public ResponseEntity registerCard(@RequestBody CardSaveRequest request) {
    Card card = request.toModel();
    service.saveCard(card);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping(params = "income")
  public ResponseEntity<List<Card>> getCardsIncomeLessThanOrEqualTo(
      @RequestParam("income") Long income) {
    List<Card> cardList = service.getCardsIncomeLessThanOrEqualTo(income);
    return ResponseEntity.status(HttpStatus.OK).body(cardList);
  }

}

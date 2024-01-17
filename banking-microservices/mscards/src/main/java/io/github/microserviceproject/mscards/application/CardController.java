package io.github.microserviceproject.mscards.application;

import io.github.microserviceproject.mscards.application.representation.CardSaveRequest;
import io.github.microserviceproject.mscards.application.representation.CardsByClientResponse;
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

  private final CardService cardService;
  private final ClientCardService clientCardService;

  @GetMapping
  public String status() {
    return "OK";
  }

  @PostMapping
  public ResponseEntity registerCard(@RequestBody CardSaveRequest request) {
    Card card = request.toModel();
    cardService.saveCard(card);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping(params = "income")
  public ResponseEntity<List<Card>> getCardsIncomeLessThanOrEqualTo(
      @RequestParam("income") Long income) {
    List<Card> cardList = cardService.getCardsIncomeLessThanOrEqualTo(income);
    return ResponseEntity.status(HttpStatus.OK).body(cardList);
  }

  @GetMapping(params = "cpf")
  public ResponseEntity<List<CardsByClientResponse>> getCardsByClient(
      @RequestParam("cpf") String cpf) {
    List<ClientCard> clientCardList = clientCardService.listCardsByCpf(cpf);

    List<CardsByClientResponse> listCardsByClientResponse =
        clientCardList.stream().map(CardsByClientResponse::fromModel).toList();

    return ResponseEntity.status(HttpStatus.OK).body(listCardsByClientResponse);
  }


}

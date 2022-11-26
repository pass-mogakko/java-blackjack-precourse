package view;

import domain.dto.BenefitResultDto;
import domain.dto.CardsToStringDto;
import domain.dto.PlayerBenefitResultDto;
import domain.dto.PlayerCardsToStringDto;
import java.util.List;
import java.util.stream.Collectors;
import view.constant.Constant;
import view.constant.Message;

public class OutputView {

    public void printDrawTwoCard(CardsToStringDto cardsToStringDto) {
        List<PlayerCardsToStringDto> playerCardsToStringDtos = cardsToStringDto.getPlayerCardsToStringDtos();
        List<String> dealerHasCards = cardsToStringDto.getDealerHasCards();
        String parsedPlayersName = parsePlayersName(playerCardsToStringDtos);
        String parsedDealerHasCards = parseCards(dealerHasCards);

        printDrawTwoCardEveryone(parsedPlayersName);
        printDealerHasCards(parsedDealerHasCards);
        printPlayersHasCards(playerCardsToStringDtos);
    }

    private String parsePlayersName(List<PlayerCardsToStringDto> playerCardsToStringDtos) {
        return playerCardsToStringDtos.stream()
                .map(PlayerCardsToStringDto::getName)
                .collect(Collectors.joining(Constant.PLAYERS_NAME_JOINING_DELIMITER));
    }

    private String parseCards(List<String> cards) {
        return cards.stream()
                .collect(Collectors.joining(Constant.CARDS_JOINING_DELIMITER));
    }

    private void printDrawTwoCardEveryone(String playersName) {
        System.out.println();
        System.out.printf(Message.DRAW_TWO_CARDS_EVERYONE, playersName);
    }

    private void printDealerHasCards(String dealerHasCards) {
        System.out.println();
        System.out.printf(Message.DEALER_CARDS, dealerHasCards);
    }

    private void printPlayersHasCards(List<PlayerCardsToStringDto> playerCardsToStringDtos) {
        System.out.println();
        playerCardsToStringDtos.forEach(this::printPlayerHasCards);
        System.out.println();

    }

    public void printPlayerHasCards(PlayerCardsToStringDto playerCardsToStringDto) {
        String name = playerCardsToStringDto.getName();
        List<String> playerHasCards = playerCardsToStringDto.getPlayerHasCards();
        String parsedPlayerHasCards = parseCards(playerHasCards);
        System.out.printf(Message.PLAYER_CARDS, name, parsedPlayerHasCards);
        System.out.println();
    }

    //    private void printPlayerHasCards(List<List<String>> playersHasCards, List<String> playersName, int index) {
    //        List<String> playerHasCards = playersHasCards.get(index);
    //        String parsedPlayerHasCards = parseCards(playerHasCards);
    //        System.out.printf(Message.PLAYER_CARDS, playersName.get(index), parsedPlayerHasCards);
    //        System.out.println();
    //    }

    public void printDealerDrawCard() {
        System.out.println(Message.DEALER_DRAW_CARD);
    }

    public void printDealerCardsResult(List<String> dealerHasCards, int CardsScore) {
        String parsedDealerHasCards = parseCards(dealerHasCards);
        System.out.println();
        System.out.printf(Message.DEALER_CARDS_RESULT, parsedDealerHasCards, CardsScore);
        System.out.println();
    }

    public void printPlayersCardsResult(List<String> playersName, List<List<String>> playersHasCards, List<Integer> playersScore) {
        for (int i = 0; i < playersName.size(); i++) {
            printPlayerCardsResult(playersName.get(i), playersHasCards.get(i), playersScore.get(i));
        }
    }

    private void printPlayerCardsResult(String playerName, List<String> playerHasCards, int CardsScore) {
        String parsedPlayerHasCards = parseCards(playerHasCards);
        System.out.printf(Message.PLAYER_CARDS_RESULT, playerName, parsedPlayerHasCards, CardsScore);
        System.out.println();
    }

    public void printBenefitResult(BenefitResultDto benefitResultDto) {
        int dealerBenefit = benefitResultDto.getDealerBenefit();
        List<PlayerBenefitResultDto> playerBenefitResultDtos = benefitResultDto.getPlayerBenefitResultDtos();
        System.out.println();
        System.out.println(Message.BENEFIT_RESULT);
        System.out.printf(Message.DEALER_BENEFIT, dealerBenefit);
        System.out.println();
        playerBenefitResultDtos.forEach(this::printPlayerBenefitResult);
    }

    private void printPlayerBenefitResult(PlayerBenefitResultDto playerBenefitResultDto) {
        String name = playerBenefitResultDto.getName();
        int playerBenefit = playerBenefitResultDto.getPlayerBenefit();
        System.out.printf(Message.PLAYER_BENEFIT, name, playerBenefit);
        System.out.println();
    }
}

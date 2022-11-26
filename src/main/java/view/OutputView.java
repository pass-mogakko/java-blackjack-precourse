package view;

import constant.Constant;
import constant.ErrorMessage;
import constant.Message;
import dto.CardsToStringDto;
import dto.GameResultDto;
import dto.PlayerBenefitResultDto;
import dto.PlayerCardsToStringDto;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        return String.join(Constant.CARDS_JOINING_DELIMITER, cards);
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

    public void printDealerDrawCard(int drawCount) {
        System.out.println();
        IntStream.range(0, drawCount)
                .forEach(i -> System.out.println(Message.DEALER_DRAW_CARD));
    }

    public void printGameResult(GameResultDto gameResultDto) {
        printDealerCardsResult(gameResultDto.getDealerHasCards(), gameResultDto.getDealerScore());
        printPlayersCardsResult(gameResultDto.getPlayerCardsToStringDtos());
        printBenefitResult(gameResultDto.getDealerBenefit(), gameResultDto.getPlayerBenefitResultDtos());

    }

    private void printDealerCardsResult(List<String> dealerHasCards, int CardsScore) {
        String parsedDealerHasCards = parseCards(dealerHasCards);

        System.out.println();
        System.out.printf(Message.DEALER_CARDS_RESULT, parsedDealerHasCards, CardsScore);
        System.out.println();
    }

    private void printPlayersCardsResult(List<PlayerCardsToStringDto> playerCardsToStringDtos) {
        playerCardsToStringDtos.forEach(this::printPlayerCardsResult);
    }

    private void printPlayerCardsResult(PlayerCardsToStringDto playerCardsToStringDto) {
        String name = playerCardsToStringDto.getName();
        List<String> playerHasCards = playerCardsToStringDto.getPlayerHasCards();
        int score = playerCardsToStringDto.getScore();

        String parsedPlayerHasCards = parseCards(playerHasCards);
        System.out.printf(Message.PLAYER_CARDS_RESULT, name, parsedPlayerHasCards, score);
        System.out.println();
    }

    private void printBenefitResult(int dealerBenefit, List<PlayerBenefitResultDto> playerBenefitResultDtos) {
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

    public void printErrorMessage(String message) {
        System.out.printf(ErrorMessage.ERROR_MESSAGE_FORM, message);
        System.out.println();
    }
}

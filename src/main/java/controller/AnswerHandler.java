package controller;

import static view.resource.OutputContent.FORMAT_INPUT_ERROR;

import view.OutputView;

import java.util.function.Supplier;

public class AnswerHandler {

    private final OutputView outputView;

    public AnswerHandler(OutputView outputView) {
        this.outputView = outputView;
    }

    public <T> T askUntilGetLegalAnswer(Supplier<T> readInput) {
        while (true) {
            try {
                return readInput.get();
            } catch (IllegalArgumentException exception) {
                outputView.printFormattedMessage(FORMAT_INPUT_ERROR, exception.getMessage());
            }
        }
    }
}
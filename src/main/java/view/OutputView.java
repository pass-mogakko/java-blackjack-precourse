package view;

import view.resource.OutputContent;

public class OutputView {

    public void printMessage(OutputContent outputMessage) {
        ConsolePrinter.printLine(outputMessage.getValue());
    }

    public void printFormattedMessage(OutputContent formattedMessage, String content) {
        ConsolePrinter.printFormattedLine(formattedMessage.getValue(), content);
    }

    public void printBlankLine() {
        ConsolePrinter.printLine("");
    }

    private static class ConsolePrinter {

        public static void printFormattedLine(String format, String content) {
            validateContent(content);
            validateContent(format);
            System.out.printf(format + "\n", content);
        }

        public static void printLine(String content) {
            validateContent(content);
            System.out.println(content);
        }

        private static void validateContent(String content) {
            if (content == null) {
                throw new IllegalArgumentException("출력할 정보의 값이 null입니다.");
            }
        }
    }
}

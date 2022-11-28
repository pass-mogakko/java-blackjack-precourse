package domain.user;

public enum Command {
    DRAW_CARD("y"),
    NOT_DRAW_CARD("n");

    private final String command;

    Command(String command){
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}

package service;

public class BlackjackService {

    private static final BlackjackService blackJackService = new BlackjackService();

    private BlackjackService() {
    }

    public static BlackjackService getInstance() {
        return blackJackService;
    }
}

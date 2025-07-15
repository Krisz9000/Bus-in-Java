package org.kafka.gameLogic;

public final class Texts {
    //TODO Write texts
    private static final String WELCOME = """
                ----------------Welcome to Ride The Bus!----------------
                This game is intended for at least 3-4 players.
                If you would like to read the rules, please press the button.
            
                Good luck, and have fun!
            
                Please enter the number of players and decks of cards:
            """;
    //TODO finish RULES
    private static final String RULES = """
            Stage 3:
            You will draw cards from this deck while you try to answer the same 4 questions from Stage 1.
            If you fail any question, you have to start again from
            the first question, until you get all 4 of them right in a row.
            
            If the deck runs out of cards, it gets reshuffled.
            """;
    private static final String QUESTION1 = """
            """;
    private static final String QUESTION2 = """
            """;
    private static final String QUESTION3 = """
            """;
    private static final String QUESTION4 = """
            """;
    private static final String INTRO_PYRAMID = """
            """;
    private static final String GIVE_DRINKS = """
            """;
    private static final String GET_DRINKS = """
            """;

    public static String getWELCOME() {
        return WELCOME;
    }

    public static String getQUESTION1() {
        return QUESTION1;
    }

    public static String getQUESTION2() {
        return QUESTION2;
    }

    public static String getQUESTION3() {
        return QUESTION3;
    }

    public static String getQUESTION4() {
        return QUESTION4;
    }

    public static String getINTRO_PYRAMID() {
        return INTRO_PYRAMID;
    }

    public static String getGIVE_DRINKS() {
        return GIVE_DRINKS;
    }

    public static String getGET_DRINKS() {
        return GET_DRINKS;
    }
}

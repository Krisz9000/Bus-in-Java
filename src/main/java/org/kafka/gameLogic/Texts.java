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
    private static final String RULES = """
            Stage 1:
            You will draw 4 cards while trying to predict something about the card by answering 4 questions.
            These 4 cards will become your hand regardless of how many correct answers you gave.
            You will use these in Stage 2.
           
            Stage 2:
            A pyramid of face-down cards will be laid out
            and you will try to get rid of your hand while the cards are slowly flipped over.
            Whoever has the most cards remaining at the and is considered the loser and moves on to Stage 3.
            In the event of a tie, all losers will participate in Stage 3.
            
            More on this in Pyramid Intro.
           
            Stage 3:
            If you find yourself in this stage, it means you have lost the game in Stage 2 and this is your punishment.
            
            You will draw cards from the deck while you try to answer the same 4 questions from Stage 1.
            If you fail any question, you have to start again from
            the first question, until you get all 4 of them right in a row.
            
            If the deck runs out of cards, it gets reshuffled and the drawing continues.
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

    public static String getRULES() {return RULES;}

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

package org.kafka.gameLogic;

import java.util.ArrayList;
import java.util.Arrays;

public class GameLogicRework {
    //TODO NoD prob redundant cuz Player, check later
    private static final ArrayList<Player> players = new ArrayList<>(Byte.MAX_VALUE);
    private final byte NUMBER_OF_QUESTIONS = 4;
    private Deck deck;
    private boolean answer;

    public GameLogicRework(byte numberOfPlayers, byte numberOfDecks) {
        initPlayers(numberOfPlayers);
        initDeck(numberOfDecks);
    }

    //TODO !DEBUG ONLY!
    public GameLogicRework() {
        this((byte) 1, (byte) 1);
    }

    /**
     * The main method that goes through the four questions.
     * If the player fails one of the questions, it loops back to the beginning of this one,
     * and their hand is emptied.
     *
     * @param p           The {@link Player} who is drawing the cards and guessing the questions
     * @param playingDeck The {@link Deck} of cards the player is drawing from
     * @return {@code true} when the player won the game by correctly guessing the fourth question, or
     * {@code false} if there was an unhandled edge-case
     */
    private static boolean startQuestions(Player p, Deck playingDeck) {
        p.drawCard(playingDeck);
        String firstGuess = firstGuess();
        //firstGuess() only returns acceptable answers, so if it's not r or red, than it must be b or black
        assert firstGuess != null;
        if (firstGuess.equalsIgnoreCase("r") || firstGuess.equalsIgnoreCase("red")) {
            if (p.getDrawnCards().getFirst().getSuit() == Suits.HEARTS || p.getDrawnCards().getFirst().getSuit() == Suits.DIAMONDS) {
                System.out.println("""
                        ---------------------------------------------
                        Congratulations!
                        You have guessed the first question correctly.
                        Now comes the second question""");
                return secondQuestion(p, playingDeck);
            } else {
                System.out.println("""
                        ---------------------------------------------
                        Unfortunately, you guessed wrong.
                        The drawn card is a(n)\s""" + p.getDrawnCards().getFirst().toString() +
                        "\nYou have to start again from the first question.");
                p.clearHand();
                return startQuestions(p, playingDeck);
            }
            //guess can only be b or black at this point
        } else {
            if (p.getDrawnCards().getFirst().getSuit() == Suits.CLUBS || p.getDrawnCards().getFirst().getSuit() == Suits.SPADES) {
                System.out.println("""
                        ---------------------------------------------
                        Congratulations!
                        You have guessed the first question correctly.
                        Now comes the second question""");
                return secondQuestion(p, playingDeck);
            } else {
                System.out.println("""
                        Unfortunately, you guessed wrong.
                        The drawn card is a(n)\s""" + p.getDrawnCards().getFirst().toString() +
                        "\nYou have to start again from the first question.");
                p.clearHand();
                return startQuestions(p, playingDeck);
            }
        }
    }

    /**
     * Handles the first question and makes sure only an accepted answer will be passed on
     *
     * @return {@code String}: An accepted answer the user gave to the first question
     */
    private static String firstGuess() {
        System.out.println("""
                ---------------------------------------------
                The first question:
                Try to guess, if the next card we draw for you, will be red or black.
                Acceptable answers are: r, red, b, black
                """);
        String firstGuess = readString();
        if (!firstGuess.equalsIgnoreCase("r") && !firstGuess.equalsIgnoreCase("red") && !firstGuess.equalsIgnoreCase("b") && !firstGuess.equalsIgnoreCase("black")) {
            System.out.println("Invalid input, please only enter one of the acceptable answers.\nHere is the question again:");
            firstGuess();
        } else return firstGuess;
        return null;
    }

    /**
     * Handles the second question and makes sure only an accepted answer will be passed on
     *
     * @param p The {@code Player} who is being asked.
     * @return {@code String} An accepted answer the user gave to the second question.
     */
    private static String secondGuess(Player p) {
        System.out.println("""
                ---------------------------------------------
                The second question:
                Try to guess, if the next card drawn will have a higher or lower value
                compared to your previous card.
                Same value is by default a lose.
                Values go from 2 (lowest) to ACE (highest).
                Acceptable answers are: h, higher, l, lower
                Your current card:\s""" + p.printDrawnCards());
        String secondGuess = readString();
        if (!secondGuess.equalsIgnoreCase("h") && !secondGuess.equalsIgnoreCase("higher") && !secondGuess.equalsIgnoreCase("l") && !secondGuess.equalsIgnoreCase("lower")) {
            System.out.println("Invalid input, please only enter one of the acceptable answers.\nHere is the question again:");
            secondGuess(p);
        } else return secondGuess;
        return null;
    }

    /**
     * Handles the player's input for the third question.
     * Keeps recursively looping until acceptable answer is given.
     *
     * @param p The {@code Player} who is being asked.
     * @return {@code String} of the player's input that has been accepted as a valid answer.
     */
    private static String thirdGuess(Player p) {
        System.out.println("""
                ---------------------------------------------
                The third question:
                Try to guess, if the next card's value will be
                inside or outside the range of your previous
                two cards.
                Same value is by default a lose.
                Example:
                You have a 4 and an 8, so a Jack or a 2 would be outside,
                but a 6 would be inside.
                Acceptable answers are: i, inside, o, outside
                Your current cards are:""" + "\n" + p.printDrawnCards());
        String thirdGuess = readString();
        if (!thirdGuess.equalsIgnoreCase("i") && !thirdGuess.equalsIgnoreCase("inside") && !thirdGuess.equalsIgnoreCase("o") && !thirdGuess.equalsIgnoreCase("outside")) {
            System.out.println("Invalid input, please only enter one of the acceptable answers.\nHere is the question again:");
            thirdGuess(p);
        } else return thirdGuess;
        return null;
    }

    /**
     * Handles the player's input for the fourth question.
     * Keeps recursively looping until acceptable answer is given.
     *
     * @param p The {@code Player} who is being asked.
     * @return {@code String} of the player's input that has been accepted as a valid answer.
     */
    private static String fourthGuess(Player p) {
        System.out.println("""
                ---------------------------------------------
                The fourth question:
                You are at the last question, victory is within grasp.
                All you have to guess is, if the next card's suit is
                one you already have, or not yet.
                Example:
                You have two Hearts and a Clubs and you draw a Diamonds
                which you dont have, so the answer is "no"
                So, do you have one of this suit already?
                Acceptable answers are: y, yes, n, no
                Your current cards are:""" + "\n" + p.printDrawnCards());
        String fourthGuess = readString();
        if (!fourthGuess.equalsIgnoreCase("y") && !fourthGuess.equalsIgnoreCase("yes") && !fourthGuess.equalsIgnoreCase("n") && !fourthGuess.equalsIgnoreCase("no")) {
            System.out.println("Invalid input, please only enter one of the acceptable answers.\nHere is the question again:");
            fourthGuess(p);
        } else return fourthGuess;
        return null;
    }

    /**
     * Manages the answer to the second question. If the player succeeds, it sends them to the third question.
     * If the player loses it, they start from the beginning.
     *
     * @param p           {@code Player} who is playing the game.
     * @param playingDeck The {@code Deck} the game is played with.
     * @return {@code true} if the player successfully answers all four questions.
     */
    private static boolean secondQuestion(Player p, Deck playingDeck) {
        String secondGuess = secondGuess(p);
        p.drawCard(playingDeck);
        //Take the value of cards based on their index in the enum list of Value
        int valueOfFirstCard = Arrays.stream(Value.values()).toList().indexOf(p.getDrawnCards().get(0).getValue());
        int valueOfSecondCard = Arrays.stream(Value.values()).toList().indexOf(p.getDrawnCards().get(1).getValue());
        assert secondGuess != null;
        if (secondGuess.equalsIgnoreCase("h") || secondGuess.equalsIgnoreCase("higher")) {
            if (valueOfFirstCard < valueOfSecondCard) {
                System.out.println("""
                        ---------------------------------------------
                        Nice work, you guessed correctly!
                        The drawn card was a(n)\s""" + p.getDrawnCards().get(1).toString() +
                        "\nYou can go onto the third question!");
                return thirdQuestion(p, playingDeck);
            } else {
                System.out.println("""
                        ---------------------------------------------
                        Sorry, but you lost.
                        The drawn card was a(n)\s""" + p.getDrawnCards().get(1).toString() +
                        "\nYou have to start again from the first question");
                p.clearHand();
                return startQuestions(p, playingDeck);
            }
        } else {
            //If they didn't guess higher, they guessed lower
            if (valueOfFirstCard > valueOfSecondCard) {
                System.out.println("""
                        ---------------------------------------------
                        Nice work, you guessed correctly!
                        The drawn card was a(n)\s""" + p.getDrawnCards().get(1).toString() +
                        "\nYou can go onto the third question!");
                return thirdQuestion(p, playingDeck);
            } else {
                System.out.println("""
                        ---------------------------------------------
                        Sorry, but you lost.
                        The drawn card was a(n)\s""" + p.getDrawnCards().get(1).toString() +
                        "\nYou have to start again from the first question");
                p.clearHand();
                return startQuestions(p, playingDeck);
            }
        }
    }

    /**
     * Manages the answer to the third question. If the player succeeds, it sends them to the fourth question.
     * If the player loses it, they start from the beginning.
     *
     * @param p           {@code Player} who is playing the game.
     * @param playingDeck The {@code Deck} the game is played with.
     * @return {@code true} if the player successfully answers all four questions.
     */
    private static boolean thirdQuestion(Player p, Deck playingDeck) {
        String thirdGuess = thirdGuess(p);
        p.drawCard(playingDeck);
        //Take the value of cards based on their index in the enum list of Value
        int valueOfFirstCard = Arrays.stream(Value.values()).toList().indexOf(p.getDrawnCards().get(0).getValue());
        int valueOfSecondCard = Arrays.stream(Value.values()).toList().indexOf(p.getDrawnCards().get(1).getValue());
        int valueOfThirdCard = Arrays.stream(Value.values()).toList().indexOf(p.getDrawnCards().get(2).getValue());
        assert thirdGuess != null;
        if (thirdGuess.equalsIgnoreCase("i") || thirdGuess.equalsIgnoreCase("inside")) {
            //Drawn card's value must be somewhere between the two, this checks for both "ways" the cards could be
            if ((valueOfFirstCard < valueOfThirdCard && valueOfSecondCard > valueOfThirdCard) ||
                    (valueOfFirstCard > valueOfThirdCard && valueOfSecondCard < valueOfThirdCard)) {
                System.out.println("""
                        ---------------------------------------------
                        Congratulations!
                        You guessed correctly.
                        The drawn card was a(n)\s""" + p.getDrawnCards().get(2).toString() +
                        "\nTime for your final question!");
                return fourthQuestion(p, playingDeck);
            } else {
                System.out.println("""
                        ---------------------------------------------
                        Unfortunately your guess was wrong.
                        You have to start again from the very beginning.
                        The drawn card was a(n)\s""" + p.getDrawnCards().get(2).toString());
                p.clearHand();
                return startQuestions(p, playingDeck);
            }
        } else {
            //Drawn card's value must either be lower than both other cards, or higher to be outside the range
            if ((valueOfFirstCard > valueOfThirdCard && valueOfSecondCard > valueOfThirdCard) ||
                    (valueOfFirstCard < valueOfThirdCard && valueOfSecondCard < valueOfThirdCard)) {
                System.out.println("""
                        ---------------------------------------------
                        Congratulations!
                        You guessed correctly.
                        The drawn card was a(n)\s""" + p.getDrawnCards().get(2).toString() +
                        "\nTime for your final question!");
                return fourthQuestion(p, playingDeck);
            } else {
                System.out.println("""
                        ---------------------------------------------
                        Unfortunately your guess was wrong.
                        You have to start again from the very beginning.
                        The drawn card was a(n)\s""" + p.getDrawnCards().get(2).toString());
                p.clearHand();
                return startQuestions(p, playingDeck);
            }
        }
    }

    private static boolean fourthQuestion(Player p, Deck playingDeck) {
        String fourthGuess = fourthGuess(p);
        //Array of only the suits of the player's cards
        ArrayList<Suits> suitsInHand = new ArrayList<>();
        for (Card c : p.getDrawnCards()) {
            suitsInHand.add(c.getSuit());
        }
        p.drawCard(playingDeck);
        assert fourthGuess != null;
        if (fourthGuess.equalsIgnoreCase("n") || fourthGuess.equalsIgnoreCase("no")) {
            if (suitsInHand.contains(p.getDrawnCards().get(3).getSuit())) {
                System.out.println("""
                        --------------------------------------------
                        Unfortunately you lost on the last question.
                        You have to start again from the very first question, good luck.
                        The drawn card was a(n)\s""" + p.getDrawnCards().get(3).toString());
                p.clearHand();
                return startQuestions(p, playingDeck);
            } else {
                System.out.println("The drawn card was a(n) " + p.getDrawnCards().get(3).toString());
                return true;
            }
        } else if (suitsInHand.contains(p.getDrawnCards().get(3).getSuit())) {
            System.out.println("The drawn card was a(n) " + p.getDrawnCards().get(3).toString());
            return true;
        } else {
            System.out.println("""
                    --------------------------------------------
                    Unfortunately you lost on the last question.
                    You have to start again from the very first question, good luck.
                    The drawn card was a(n)\s""" + p.getDrawnCards().get(3).toString());
            p.clearHand();
            return startQuestions(p, playingDeck);
        }
    }

    //Temp for compile time
    private static String readString() {
        return "";
    }

    /**
     * Initializes all the {@link Player} objects for the game through {@link GameLogicRework#players}
     *
     * @param numberOfPlayers Number of players playing the game.
     * @throws IllegalArgumentException if {@code numberOfPlayers < 1}
     */
    private void initPlayers(byte numberOfPlayers) throws IllegalArgumentException {
        try {
            if (numberOfPlayers < 1)
                throw new IllegalArgumentException("Error in GameLogicRework.initPlayers(): Number of players was negative or zero!");
            for (byte b = 1; b <= numberOfPlayers; b++) {
                players.add(new Player(players));
            }
        } catch (Exception e) {
            System.err.println("Error in GameLogicRework.initPlayers()\n" + e.getMessage());
        }
    }

    /**
     * Handles initializing the full {@link Deck} for the game <br>
     * by creating multiple decks and shuffling them together into one.
     *
     * @param numberOfDecks The number of decks to be shuffled together into one.
     * @throws IllegalArgumentException If {@code numberOfDecks < 1}
     */
    private void initDeck(byte numberOfDecks) throws IllegalArgumentException {
        if (numberOfDecks < 1)
            throw new IllegalArgumentException("Error in GameLogicRework.initDeck(): Number of decks was negative or zero!");
        //Set up the final playing Deck
        Deck fullDeck = new Deck();
        fullDeck.clearDeck();
        fullDeck.emptyDeck();
        try {
            //Create as many decks as asked for
            ArrayList<Deck> decksInPlay = new ArrayList<>();
            while (numberOfDecks > 0) {
                decksInPlay.add(new Deck());
                numberOfDecks--;
            }

            //Shuffle the Decks into one
            decksInPlay.forEach((d) -> {
                for (Card c : d.getCardsPartOfDeck()) {
                    fullDeck.getCardsPartOfDeck().add(c);
                }
            });
            fullDeck.reshuffle();
        } catch (Exception e) {
            System.err.println("Error in GameLogicRework.initDeck(): " + e.getMessage());
        }
        this.deck = fullDeck;
    }

    /**
     * Handles answering buttons.<br>It looks like it is for yes|no,
     * but since all questions only have two answers,
     * the actual meaning of the buttons doesn't matter, only the interpretation.
     *
     * @param b Set by the buttons, indicates which of the two buttons was last pushed.
     */
    public void answer(boolean b) {
        this.answer = b;
    }

    public Deck getDeck() {
        return this.deck;
    }

    /**
     * The main game logic. It sets up the Player, introduces the rules, and starts the questions.
     */
    public boolean startGame() {
        System.out.println("""
                ----------------------Welcome to Ride The Bus!----------------------
                The rules are as follows:
                
                You will choose how many decks of cards you will play with.
                You will draw cards from this deck while you try to answer 4 questions.
                You will need to consider your previous cards when guessing.
                If you fail any question, you have to start again from
                the first question, until you get all 4 of them right in a row.
                If the deck runs out of cards, it gets reshuffled.
                
                Good luck, and have fun!
                
                Please enter how many decks of cards you would like to play with:
                """);
        Deck playingDeck = deck;
        //startQuestion only finishes execution when the game has ended
        if (startQuestions(players.getFirst(), playingDeck)) {
            System.out.println("""
                    ---------------------------------------------
                    Congratulations!
                    You are a winner, you have won Ride The Bus!
                    I hope you had fun!
                    
                    How many cards it took you to get off the bus:\s""" + players.getFirst().getNumberOfDraws());
            return true;
        } else return false;
    }

}

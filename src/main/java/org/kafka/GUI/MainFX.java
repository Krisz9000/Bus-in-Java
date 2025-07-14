package org.kafka.GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kafka.gameLogic.Card;
import org.kafka.gameLogic.Deck;
import org.kafka.gameLogic.GameLogic;

import java.security.InvalidParameterException;
import java.util.Objects;

public class MainFX extends Application {

    private final int DEBUG_CODE = 1;
    private Deck deck;
    private int numberOfDraws = 0;
    
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Converts the name of a {@code card} into the filename-path of its image.
     *
     * @param card The {@link Card} which's name to convert.
     * @return The corresponding filename of the {@code card's} image,
     * to be used as a URL for an {@link ImageView} object.<br><br>
     * Format: <br>
     * "VALUE_OF_SUITS.png" where VALUE and SUITS are from {@link Card#getValue()} and {@link Card#getSuit()}.
     */
    private String filenameOfCard(Card card) {
        return card.toString().replace(' ', '_').replace("of", "OF").concat(".png");
    }


    /**
     * Fetches the image of a card by converting its attributes into a filename-path.
     *
     * @param card The {@link Card} of which to fetch its picture.
     * @return New {@link Image} object, that is the picture of the requested {@code card}.
     */
    private Image fetchImageOfCard(Card card) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/cards/" + filenameOfCard(card))));
    }

    /**
     * Sets up the entire menu bar for the application.
     * @param primaryStage Current {@link Stage}, used by menu options.
     * @return Prepared {@link MenuBar} to be added to scenes.
     */
    private MenuBar setupMenuBar(Stage primaryStage) {
        MenuItem exitButton = new MenuItem("Exit Game");
        exitButton.setOnAction(e -> System.exit(0));

        MenuItem startConsoleGameBtn = new MenuItem("Start Game in Console");
        startConsoleGameBtn.setOnAction(e -> {
            primaryStage.hide();
            GameLogic.startGame();
        });

        Menu menuGame = new Menu("Game", null, startConsoleGameBtn, exitButton);

        MenuBar menuBar = new MenuBar(menuGame);
        menuBar.setUseSystemMenuBar(true);

        return menuBar;
    }

    /**
     * Handles the logic of drawing from the deck.<br>
     * Drawing handled by {@link Deck#drawCard()}.<br>
     * Sets display labels according to the card and picks it's image for display.
     *
     * @param cardLabel   The {@link Label} that will say the name of the drawn card.
     * @param cardDisplay The {@link ImageView} object that will display the drawn card.
     * @return The drawn {@link Card}.
     * @throws InvalidParameterException If the game's {@link Deck} object is {@code null}.
     */
    private Card drawCard(Label cardLabel, ImageView cardDisplay) throws InvalidParameterException {
        Card card;
        if (deck == null) {
            throw new InvalidParameterException("The Deck has not been initialized yet.");
        } else {
            card = deck.drawCard();
            cardLabel.setText("The drawn card is: " + card.toString());
            cardDisplay.setImage(fetchImageOfCard(card));
            return card;
        }
    }

    /**
     * Fetches the picture of a card and returns a new imageview that displays it.
     *
     * @param card The {@link Card} for which to fetch the corresponding image.
     * @return New {@link ImageView} object, displaying the {@link Image} of {@code card}.
     */
    private ImageView createNewImageViewOfCard(Card card) {
        return createNewImageViewOfCard(card, 150);
    }

    /**
     * Fetches the picture of a card and returns a new imageview that displays it.
     *
     * @param card   The {@link Card} for which to fetch the corresponding image.
     * @param height The preferred height of the image display.
     * @return New {@link ImageView} object, displaying the {@link Image} of {@code card}.
     */
    private ImageView createNewImageViewOfCard(Card card, int height) {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.setImage(fetchImageOfCard(card));
        imageView.setDisable(false);

        return imageView;
    }

    @Override
    public void start(Stage primaryStage) {
        //Prepping VBox
        //Box at the center with buttons and drawn card image
        VBox vBoxCenter = new VBox(20);
        vBoxCenter.setAlignment(Pos.CENTER);
        //Box at the bottom for counters (No. of drawn cards, No. of cards left in deck)
        VBox vBoxBottom = new VBox(5);
        vBoxBottom.setAlignment(Pos.TOP_LEFT);

        //Prepping HBox
        //Buttons for starting game and drawing
        HBox hBoxButtons = new HBox(5);
        hBoxButtons.setAlignment(Pos.CENTER);
        //Displays for the cards in Player's hand
        HBox hBoxCardsInHand = new HBox(5);
        hBoxCardsInHand.setAlignment(Pos.CENTER);
        //TODO implement lambda Labels and fill up
        //Labels for the cards in the player's hand
        HBox hBoxCardsInHandLabels = new HBox(5);
        hBoxCardsInHandLabels.setAlignment(Pos.CENTER);

        //Welcome text
        Text title = new Text(30, 80, "Ride the Bus: Welcome!");
        title.setFont(new Font(26));

        //ImageView for card display
        //Last card drawn
        ImageView cardDrawnImageView = new ImageView();
        cardDrawnImageView.setFitHeight(150);
        cardDrawnImageView.setPreserveRatio(true);
        cardDrawnImageView.setSmooth(true);
        cardDrawnImageView.setCache(true);
        cardDrawnImageView.setImage(null);
        cardDrawnImageView.setDisable(true);

        //Labels
        Label cardLabel = new LabelBuilder("Press \"Start new Game\" to draw the first card!").build();
        Label remainingCardsLabel = new LabelBuilder("Remaining cards in the Deck: 0")
                .alignment(Pos.BASELINE_LEFT)
                .maxWidth(350)
                .build();
        Label numberOfDrawsLabel = new LabelBuilder("Number of draws: " + numberOfDraws)
                .alignment(Pos.BASELINE_LEFT)
                .build();
        //Setting up button for next card draw
        Button nextCardBtn = new Button("Next Card");
        nextCardBtn.setOnAction(e -> {
            Card drawnCard = drawCard(cardLabel, cardDrawnImageView);
            remainingCardsLabel.setText("Remaining cards in the Deck: " + deck.getCardsLeftInDeck().size());
            numberOfDrawsLabel.setText("Number of draws: " + ++numberOfDraws);
            hBoxCardsInHandLabels.getChildren().add(new LabelBuilder(drawnCard.toString())
                    .fontSize(8)
                    .build());
            hBoxCardsInHand.getChildren().add(createNewImageViewOfCard(drawnCard, 100));
        });
        nextCardBtn.setDisable(true);
        nextCardBtn.setPrefWidth(120);

        //TODO handle player's hand display when starting new game
        //Creating the start new game button
        Button startBtn = new Button("Start new Game");
        startBtn.setOnAction(e -> {
            deck = new Deck();
            deck.shuffle();
            drawCard(cardLabel, cardDrawnImageView);
            remainingCardsLabel.setText("Remaining cards in the Deck: " + deck.getCardsLeftInDeck().size());
            numberOfDrawsLabel.setText("Number of draws: " + ++numberOfDraws);
            startBtn.setDefaultButton(false);
            nextCardBtn.setDisable(false);
            cardDrawnImageView.setDisable(false);
            if (!vBoxCenter.getChildren().contains(cardDrawnImageView)) vBoxCenter.getChildren().add(2, cardDrawnImageView);
        });
        startBtn.setDefaultButton(true);
        startBtn.setPrefWidth(120);

        //Adding everything to the HBoxes
        hBoxButtons.getChildren().addAll(startBtn, nextCardBtn);

        //Adding everything to the VBoxes
        vBoxCenter.getChildren().addAll(title, hBoxButtons, cardLabel, hBoxCardsInHand);
        vBoxBottom.getChildren().addAll(numberOfDrawsLabel, remainingCardsLabel);

        // Setting up Menu Bar and root of the Scene
        BorderPane root = new BorderPane();
        root.setTop(setupMenuBar(primaryStage));
        root.setCenter(vBoxCenter);
        root.setBottom(vBoxBottom);

        //Setting up the Scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Ride the Bus");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

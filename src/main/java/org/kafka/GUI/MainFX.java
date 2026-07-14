package org.kafka.GUI;

import javafx.application.Application;
import javafx.application.Platform;
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
import org.kafka.gameLogic.*;

import java.security.InvalidParameterException;
import java.util.Objects;

public class MainFX extends Application {

    private final int DEBUG_CODE = 1;
    private final GameLogicRework gameLogic = new GameLogicRework();
    private boolean firstRun = true;
    //TODO rewrite, will use Player.numberOfDraws
    private int numberOfDraws = 0;
    private final double WINDOW_HEIGHT = 700;
    private final double WINDOW_WIDTH = 800;
    private final BorderPane POPUP_ROOT = new BorderPane();
    private final Scene POPUP_SCENE = new Scene(POPUP_ROOT, WINDOW_WIDTH - 200, WINDOW_HEIGHT - 200);
    private final Stage POPUP_STAGE = new Stage();
    private final HBox HBOX_POPUP = new HBox(15);
    private final VBox VBOX_POPUP = new VBox(20);
    private final Button CLOSE_BTN = new Button("Close");

    //TODO JavaFX Tutorial for myself:
    /*
    BorderPane can have V and H boxes for elements, those boxes can house the labels/buttons/Images whatevs.
    I need a BorderPane as the root for a Scene, the Scene can be displayed as a (primary)Stage.
     */

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
        exitButton.setOnAction(e -> Platform.exit());
        MenuItem restartButton = new MenuItem("Restart Game");
        restartButton.setOnAction(e -> {
            this.firstRun = true;
            this.numberOfDraws = 0;
            this.start(primaryStage);
        });

        MenuItem startConsoleGameBtn = new MenuItem("Start Game in Console");
        startConsoleGameBtn.setOnAction(e -> {
            primaryStage.hide();
            GameLogic.startGame();
        });

        Menu menuGame = new Menu("Game", null, restartButton, startConsoleGameBtn, exitButton);

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
        if (gameLogic.getDeck() == null) {
            throw new InvalidParameterException("The Deck has not been initialized yet.");
        } else {
            card = gameLogic.getDeck().drawCard();
            cardLabel.setText("The drawn card is: " + card.toString());
            cardDisplay.setImage(fetchImageOfCard(card));
            return card;
        }
    }

    @Override
    public void start(Stage primaryStage) {
            //Prepping VBox
        //Box at the center with buttons and drawn card image
        VBox vBoxGameCenter = new VBox(20);
        vBoxGameCenter.setAlignment(Pos.CENTER);
        //Box at the bottom for counters (No. of drawn cards, No. of cards left in deck, No. of drinks)
        VBox vBoxGameBottom = new VBox(5);
        vBoxGameBottom.setAlignment(Pos.TOP_LEFT);
            //Prepping PopUps
        VBOX_POPUP.setAlignment(Pos.CENTER);
        //TODO not part of a popUp yet
        //Box for picking players
        VBox vBoxPlayers = new VBox(20);
        vBoxPlayers.setAlignment(Pos.CENTER);

            //Prepping HBox
        //Buttons for starting game and drawing
        HBox hBoxGameButtons = new HBox(5);
        hBoxGameButtons.setAlignment(Pos.CENTER);
        //Displays for the cards in Player's hand
        HBox hBoxCardsInHand = new HBox(15);
        hBoxCardsInHand.setAlignment(Pos.CENTER);
        //Labels for the cards in the player's hand
        HBox hBoxCardsInHandLabels = new HBox(15);
        hBoxCardsInHandLabels.setAlignment(Pos.CENTER);
            //Prepping PopUps
        HBOX_POPUP.setAlignment(Pos.CENTER);

        POPUP_ROOT.setCenter(VBOX_POPUP);
        POPUP_STAGE.setScene(POPUP_SCENE);

        //Welcome text
        Text title = new Text(30, 80, "Ride the Bus: Welcome!");
        title.setFont(new Font(26));

        //ImageView for card display
        //Last card drawn
        ImageView cardDrawnImageView = new ImageViewBuilder(null).build();

        //Labels
        Label cardLabel = new LabelBuilder("Press \"Start new Game\" to draw the first card!").build();
        Label remainingCardsLabel = new LabelBuilder("Remaining cards in the Deck:")
                .alignment(Pos.BASELINE_LEFT)
                .maxWidth(350)
                .fontSize(12)
                .addExtraArgs(0)
                .updateText()
                .build();
        Label numberOfDrawsLabel = new LabelBuilder("Number of draws:")
                .alignment(Pos.BASELINE_LEFT)
                .fontSize(12)
                .addExtraArgs(numberOfDraws)
                .updateText()
                .build();
        Label labelNumberOfDrinks = new LabelBuilder("Your Number of Drinks:")
                .alignment(Pos.BASELINE_LEFT)
                .fontSize(12)
                //.addExtraArgs(player.getNumberOfDrinks())
                .addExtraArgs(0)
                .updateText()
                .build();
        Label labelQuestion = new LabelBuilder("Here is your next question: \n")
                .alignment(Pos.BASELINE_CENTER)
                .fontSize(16)
                .updateText()
                .build();

        //TODO fix not updating labels
        //Setting up button for next card draw
        Button nextCardBtn = new Button("Next Card");
        nextCardBtn.setOnAction(e -> {
            Card drawnCard = drawCard(cardLabel, cardDrawnImageView);
            //TODO need to take a look at how this should update
            LabelBuilder.updateLabel(remainingCardsLabel, gameLogic.getDeck().getCardsLeftInDeck().size());
            LabelBuilder.updateLabel(numberOfDrawsLabel, ++numberOfDraws);
            hBoxCardsInHandLabels.getChildren().add(new LabelBuilder(drawnCard.toString())
                    .fontSize(10)
                    .updateText()
                    .build());
            hBoxCardsInHand.getChildren().add(new ImageViewBuilder(fetchImageOfCard(drawnCard))
                    .fitHeight(100)
                    .disable(false)
                    .build());
        });
        nextCardBtn.setDisable(true);
        nextCardBtn.setPrefWidth(120);

        //TODO rewrite start button, needs to account for different amount of players.
        //Creating the start new game button
        Button startBtn = new Button("Start new Game");
        startBtn.setOnAction(e -> {
            //gameLogic.getDeck() = new Deck();
            gameLogic.getDeck().shuffle();
            if (firstRun) {
                this.firstRun = false;

                startBtn.setDefaultButton(false);
                nextCardBtn.setDefaultButton(true);
                cardDrawnImageView.setDisable(false);
                vBoxGameCenter.getChildren().add(2, cardDrawnImageView);
            } else {
                //Clean up player's hand because this is a restart
                hBoxCardsInHand.getChildren().clear();
                hBoxCardsInHandLabels.getChildren().clear();
            }
            //Imitates clicking nextCardBtn, used basically as a method call
            nextCardBtn.setDisable(false);
            nextCardBtn.fire();
        });
        startBtn.setDefaultButton(true);
        startBtn.setPrefWidth(120);

        //Buttons to answer the questions, NOTE: meant to be interpreted, not taken literally!
        //TODO add function call for iterating the question counter and continuing the game
        //TODO add to a display box
        //TODO Clear up PopUp window after pressing button
        Button yesBtn = new Button("1");
        yesBtn.setOnAction(e -> {
            gameLogic.answer(true);
            POPUP_STAGE.hide();
        });
        Button noBtn = new Button("0");
        noBtn.setOnAction(e -> {
            gameLogic.answer(false);
            POPUP_STAGE.hide();
        });

        //Button to close a PopUp window
        CLOSE_BTN.setOnAction(e -> {
            VBOX_POPUP.getChildren().clear();
            HBOX_POPUP.getChildren().clear();
            POPUP_STAGE.hide();
        });

        //Adding everything to the HBoxes
        hBoxGameButtons.getChildren().addAll(startBtn, nextCardBtn);

        //Adding everything to the VBoxes
        vBoxGameCenter.getChildren().addAll(title, hBoxGameButtons, cardLabel, hBoxCardsInHand, hBoxCardsInHandLabels);
        vBoxGameBottom.getChildren().addAll(numberOfDrawsLabel, remainingCardsLabel, labelNumberOfDrinks);

        // Setting up Menu Bar and root of the Scene
        BorderPane root = new BorderPane();
        root.setTop(setupMenuBar(primaryStage));
        root.setCenter(vBoxGameCenter);
        root.setBottom(vBoxGameBottom);

        //Setting up the Scene
        Scene gameScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle("Ride the Bus");
        primaryStage.setScene(createStartUpScene(primaryStage, gameScene));
        primaryStage.show();
    }

    private Scene createStartUpScene(Stage primaryStage, Scene gameScene) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        VBox vBoxCenter = new VBox(30);
        vBoxCenter.setAlignment(Pos.CENTER);
        HBox hBoxInputs = new HBox(40);
        hBoxInputs.setAlignment(Pos.CENTER);

        VBox vBoxPlayerInput = new VBox(20);
        vBoxPlayerInput.setAlignment(Pos.CENTER);
        VBox vBoxDecksInput = new VBox(20);
        vBoxDecksInput.setAlignment(Pos.CENTER);

        Label welcomeLabel = new LabelBuilder(Texts.getWELCOME())
                .updateText()
                .build();
        Label playersLabel = new LabelBuilder("Number Of Players").updateText().build();
        Label decksLabel = new LabelBuilder("Decks of Cards").updateText().build();
        Label labelDaRules = new LabelBuilder(Texts.getRULES()).updateText().build();

        TextField playersInput = new TextField("1");
        TextField decksInput = new TextField("1");

        //Button to give an overview about the rules of the game
        Button daRulesBtn = new Button("Da Rules");
        daRulesBtn.setOnAction(e -> {
            VBOX_POPUP.getChildren().addAll(labelDaRules, CLOSE_BTN);
            POPUP_STAGE.show();
        });

        //TODO implement input checking logic
        Button startGameBtn = new Button("Start the Game");
        startGameBtn.setOnAction(e -> {
            primaryStage.setScene(gameScene);
        });
        startGameBtn.setPrefWidth(150);

        vBoxPlayerInput.getChildren().addAll(playersLabel, playersInput);
        vBoxDecksInput.getChildren().addAll(decksLabel, decksInput);
        hBoxInputs.getChildren().addAll(vBoxPlayerInput, vBoxDecksInput);
        vBoxCenter.getChildren().addAll(welcomeLabel, daRulesBtn, hBoxInputs, startGameBtn);

        root.setTop(setupMenuBar(primaryStage));
        root.setCenter(vBoxCenter);
        return scene;
    }
}

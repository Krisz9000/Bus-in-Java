package org.kafka.GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kafka.gameLogic.Card;
import org.kafka.gameLogic.Deck;
import org.kafka.gameLogic.GameLogic;

public class MainFX extends Application {

    private final int DEBUG_CODE = 1;
    private Deck deck;
    private int numberOfDraws = 0;
    
    public static void main(String[] args) {
        launch(args);
    }

    //Setting up the Menu Bar on top
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

    private void drawCard(Label cardLabel) {
        if (deck == null) {
            cardLabel.setText("The Deck has not been initialized yet.");
        } else {
            Card card = deck.drawCard();
            cardLabel.setText("The drawn card is: " + card.toString());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        //Prepping VBox
        VBox vBoxCenter = new VBox(20);
        vBoxCenter.setAlignment(Pos.CENTER);

        VBox vBoxBottom = new VBox(5);
        vBoxBottom.setAlignment(Pos.TOP_LEFT);
        //Welcome text
        Text title = new Text(30, 80, "Ride the Bus: Welcome!");
        title.setFont(new Font(26));

        //Labels
        Label cardLabel = new Label("Press \"Start new Game\" to draw the first card!");
        Label remainingCardsLabel = new Label("Remaining cards in the Deck: 0");
        remainingCardsLabel.setMaxWidth(350);
        remainingCardsLabel.setAlignment(Pos.BASELINE_LEFT);
        Label numberOfDrawsLabel = new Label("Number of draws: " + numberOfDraws);
        numberOfDrawsLabel.setAlignment(Pos.BASELINE_LEFT);

        //Setting up button for next card draw
        Button nextCardBtn = new Button("Next Card");
        nextCardBtn.setOnAction(e -> {
            drawCard(cardLabel);
            remainingCardsLabel.setText("Remaining cards in the Deck: " + deck.getCardsLeftInDeck().size());
            numberOfDrawsLabel.setText("Number of draws: " + ++numberOfDraws);
        });
        nextCardBtn.setDisable(true);

        //Creating the start new game button
        Button startBtn = new Button("Start new Game");
        startBtn.setOnAction(e -> {
            deck = new Deck();
            deck.shuffle();
            drawCard(cardLabel);
            remainingCardsLabel.setText("Remaining cards in the Deck: " + deck.getCardsLeftInDeck().size());
            numberOfDrawsLabel.setText("Number of draws: " + ++numberOfDraws);
            startBtn.setDefaultButton(false);
            nextCardBtn.setDisable(false);
        });
        startBtn.setDefaultButton(true);
        startBtn.setPrefSize(150, 50);

        //Adding everything to the VBoxes
        vBoxCenter.getChildren().addAll(title, startBtn, nextCardBtn, cardLabel);
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

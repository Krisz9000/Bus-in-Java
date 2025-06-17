package org.kafka.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Ride the Bus: Welcome!");
        Scene scene = new Scene(label, 800, 600);
        primaryStage.setTitle("Ride the Bus");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}

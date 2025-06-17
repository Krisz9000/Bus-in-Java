package org.kafka.gameLogic;

public class Main {
    public static void main(String[] args) {

        if (GameLogic.startGame()) {
            System.exit(0);
        } else {
            System.exit(1);
        }
    }
}
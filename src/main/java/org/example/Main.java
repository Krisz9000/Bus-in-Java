package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Deck deck = new Deck();
        //deck.shuffle();
        //deck.printDeck();

        //Test Player
        Player p = new Player(0);
        deck.shuffle();
        for (int i = 0; i < 12; ++i) {
            p.drawCard(deck);
        }
        System.out.println(p.getDrawnCards().toString());
        deck.printDeck();
    }
}
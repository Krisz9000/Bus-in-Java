package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Deck deck = new Deck();
        //deck.printDeck();
        System.out.println(deck.drawCard().toString());
        System.out.println();
        deck.printDeck();
    }
}
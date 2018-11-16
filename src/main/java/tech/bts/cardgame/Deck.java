package tech.bts.cardgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

   private List<Card> cards;

    public  Deck() {
        this.cards = new ArrayList<Card>();
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public void generate() {
        for (int m = 1; m <= 8; m++ ) {
            // nested loop
            for (int s = 1; s<= 9-m; s++) {
                int i = 10 - (m + s);
                this.add(new Card(m,s,i));
            }
        }
    }

    public void shuffle() {

        System.out.println("Shuffling " + this.cards.size() + " cards");

        Random random = new Random();


        for (int i = 0; i < this.cards.size() - 1; i++) {

           // int b = this.cards.size() - 1;

            int randomIndex = random.nextInt (this.cards.size()); // random index after index i

            // int randomIndex = random.nextInt(this.cards.size()); // random index

            System.out.println("Swapping cards at indexes " + i + " and " + randomIndex );

            Card card1 = this.cards.get(i);
            Card card2 = this.cards.get(randomIndex);

            this.cards.set(randomIndex, card1);
            this.cards.set(i, card2);


        }
    }

    public Card pickCard(){

        return this.cards.remove(this.cards.size() - 1);
    }

    public Hand deal(int size) {

        List<Card> handCards = new ArrayList<Card>();

        for (int i = 0; i <= size; i++) {
            Card card = this.pickCard();
            handCards.add(card);
        }

        return new Hand(handCards);
    }
}

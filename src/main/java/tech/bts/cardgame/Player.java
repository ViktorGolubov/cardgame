package tech.bts.cardgame;

public class Player {

    private String name;
    private Hand hand;

    public Player(String name) {
        this.name = name;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public void discard(int numCardsToDiscard) {

    }

    @Override
    public String toString() {
        return name;
    }
}

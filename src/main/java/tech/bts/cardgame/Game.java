package tech.bts.cardgame;

public class Game {

    private final Player player1;
    private final Player player2;
    private final Deck deck;

     public final static int INITIAL_HAND_SIZE = 5;
     public final static int FINAL_HAND_SIZE = 3;
     public final static int NUM_CARDS_T0_DISCARD = INITIAL_HAND_SIZE - FINAL_HAND_SIZE;

    public Game(Player player1, Player player2, Deck deck) {

        this.player1 = player1;
        this.player2 = player2;
        this.deck = deck;
    }

    public void play() {

        // Battle

        // 1 - dealing the cards
        Hand hand1 = deck.deal(INITIAL_HAND_SIZE);
        Hand hand2 = deck.deal(INITIAL_HAND_SIZE);


        //player1.setHand(hand1);
        //player2.setHand(hand2);

        // TODO: 2 - discard the cards

        // 3 - fighting
        int result = compare(hand1, hand2);

        System.out.println("hand 1: " + hand1.calculate().toString());
        System.out.println("hand 2: " + hand2.calculate().toString());

        // TODO: print the name of the winner
    }

    private int compare(Hand hand1, Hand hand2) {

        int points1 = 0;
        int points2 = 0;


       Card total1 = hand1.calculate();
       Card total2 = hand2.calculate();

       if(total1.getMagic() > total2.getMagic()){
           points1++;
       } else if (total1.getMagic() < total2.getMagic()){
           points2++;

       } if(total1.getStrength() > total2.getStrength()){
            points1++;
       } else if (total1.getStrength() < total2.getStrength()){
            points2++;

       } if (total1.getIntelligence() > total2.getIntelligence()) {
           points1++;

       } else if (total1.getIntelligence() < total2.getIntelligence()) {
           points2++;

       }

       if (points1 - points2 > 0) {
           System.out.println("The winner is " + player1);
       } else if (points2 - points1 > 0) {
           System.out.println("The winner is " + player2);
       } else if (points1 - points2 == 0) {
           System.out.println("The winner is no one");
       }


        return points1 - points2;

    }

}

package tech.bts.cardgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {


    enum State { OPEN, PLAYING }

    private final Deck deck;
    private State state;
    private List<String> usernames;
    private Map<String, Card> pickedCardByUsername;
    private int discardCounter;

    public Game(Deck deck) {

        this.deck = deck;
        this.state = State.OPEN;
        this.usernames = new ArrayList<>();
        this.pickedCardByUsername = new HashMap<>();
        this.discardCounter = 0;

    }


    public State getState() {

        return state;
    }

    public void join(String username) {

        if (state != State.OPEN) {
            throw new JoiningNotAllowedException();
        }

        usernames.add(username);

        if (usernames.size() == 2) {
            state = State.PLAYING;
        }

    }

    public List<String> getPlayerNames() {
        return usernames;
    }

    public Card pickCard(String username) {

        if (state == State.OPEN) {
            throw new GameStateIsNotPlayingException();
        }


        if(!usernames.contains(username)) {
            throw new PlayerNotInTheGameException();

        }

        Card pickedCard = pickedCardByUsername.get(username);
        if(pickedCard != null) {
            throw new CannotPick2CardsInARowException();
        }

        Card newPickedCard = deck.pickCard();

        pickedCardByUsername.put(username, newPickedCard);

        return newPickedCard;
    }

    public void discard(String username) {

        Card pickedCard = pickedCardByUsername.get(username);
        if (pickedCard == null) {
            throw new UserDidNotPickCardException();
        }

        discardCounter++;
        if (discardCounter > 2){
            throw new UserDiscardMoreThan2CardsException();
        }


        pickedCardByUsername.remove(username);
    }

    public List<Card> keepCard(String username) {

        List<Card> keptcards = new ArrayList<>();

        Card pickedCard = pickedCardByUsername.get(username);

        keptcards.add(pickedCard);

        return keptcards;
    }



    /*
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
    */
}

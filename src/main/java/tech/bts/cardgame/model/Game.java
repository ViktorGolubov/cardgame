package tech.bts.cardgame.model;

import tech.bts.cardgame.exceptions.*;

import java.util.*;

public class Game {


    public enum  State { OPEN, PLAYING }

    private long id;
    private final Deck deck;
    private State state;
    private Map<String, Player> playersByUsername;


    public Game(Deck deck) {
        this.deck = deck;
        this.state = State.OPEN;
        this.playersByUsername = new HashMap<>();

    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


    public State getState() {

        return state;
    }

    public void join(String username) {

        if (state != State.OPEN) {
            throw new JoiningNotAllowedException();
        }

        Player player = new Player(username);

        playersByUsername.put(username, player);

        if (playersByUsername.size() == 2) {
            state = State.PLAYING;
        }

    }

    public Set<String> getPlayerNames() {
        return playersByUsername.keySet();
    }

    public Card pickCard(String username) {

        if(!playersByUsername.containsKey(username)) {
            throw new PlayerNotInTheGameException();
        }

        if (state != State.PLAYING) {
            throw new GameStateIsNotPlayingException();
        }

        Player player = playersByUsername.get(username);

        Card pickedCard = player.getPickedCard();
        if(pickedCard != null) {
            throw new CannotPick2CardsInARowException();
        }

        Card newPickedCard = deck.pickCard();

        player.setPickedCard(newPickedCard);

        return newPickedCard;
    }

    public void discard(String username) {

        Player player = playersByUsername.get(username);

        Card pickedCard = player.getPickedCard();

        if (pickedCard == null) {
            throw new UserDidNotPickCardException();
        }

        int discards = player.getDiscardCount();
        if (discards == 2){
            throw new UserDiscardTooManyCardsException();
        }

        player.setDiscardCount(discards + 1);

        player.setPickedCard(null);
    }

    public List<Card> keepCard(String username) {

        List<Card> keptcards = new ArrayList<>();

        playersByUsername.get(username);

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

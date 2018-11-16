package tech.bts.cardgame;

import java.util.ArrayList;
import java.util.List;

public class Game {

    enum State { OPEN, PLAYING }

    private final Deck deck;
    private State state;
    private List<String> usernames;

    public Game(Deck deck) {

        this.deck = deck;
        this.state = State.OPEN;
        this.usernames = new ArrayList<>();
    }


    public State getState() {

        return state;
    }

    public void join(String username) {

        if (state != State.OPEN) {
            throw new JoiningNotAllowedExeption();
        }

        usernames.add(username);

        if (usernames.size() == 2) {
            state = State.PLAYING;
        }

    }

    public List<String> getPlayerNames() {
        return usernames;
    }

}

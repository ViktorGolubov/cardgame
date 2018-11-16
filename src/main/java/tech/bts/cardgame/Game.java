package tech.bts.cardgame;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Deck deck;
    private String state;
    private List<String> usernames;

    public Game(Deck deck) {

        this.deck = deck;
        this.state = "open";
        this.usernames = new ArrayList<>();
    }


    public Object getState() {

        return state;
    }

    public void join(String username) {

        if (!state.equals("open")) {
            throw new JoiningNotAllowedExeption();
        }

        usernames.add(username);

        if (usernames.size() == 2) {
            state = "playing";
        }

    }

    public List<String> getPlayerNames() {
        return usernames;
    }

}

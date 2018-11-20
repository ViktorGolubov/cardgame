package tech.bts.cardgame;

import org.junit.Test;

import java.awt.*;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

/**
 * Creating a game:
 * - A game is created with a deck of cards (each card has 3 numbers (>=1) that added make 10).
 *   - Note: the 3 numbers represent magic, strength, intelligence
 * - When a game is created, its state is OPEN.
 *
 * Joining a game:
 * - A player can join an OPEN game (for simplicity, a player is indicated by its username).
 * - When 2 players join the game, the state of the game changes to PLAYING.
 * - A player can't join if the game state is not OPEN (throw an exception if someone tries).
 *
 * Picking cards:
 * - When the game is PLAYING, any player that joined the game can pick a card.
 * - After picking a card, a player must keep it or discard it.
 * - A player can only discard 2 cards (i.e. must pick 3 cards).
 *
 * The battle (point calculation):
 * - When the 2 players have picked 3 cards, the winner of that round is calculated:
 *   - Each player adds all magics, all strengths and all intelligences
 *   - Totals of each category is compared between players
 *   - Player who wins in 2 categories earns a point (there may be no winner)
 *
 * - After the points are calculated, a new battle starts (players pick cards again)
 * - If there are less than 10 cards in the deck, the game changes to state FINISHED
 */

public class GameShould {

    @Test
    public void be_open_when_created() {

        Game game = new Game(new Deck());

        assertThat(game.getState(), is(Game.State.OPEN));
    }

    @Test
    public void allow_joining_when_open() {

        // TODO* - A player can join an OPEN game

        Game game = new Game(new Deck());

        game.join("john");

        assertThat(game.getState(), is(Game.State.OPEN));
        assertThat(game.getPlayerNames(), is(Arrays.asList("john")));

    }

    @Test
    public void be_playing_when_2_players_join() {

        // TODO: When 2 players join the game,
        // TODO: the state of the game changes to PLAYING.

        Game game = new Game(new Deck());

        game.join("john");
        game.join("mary");

        assertThat(game.getState(), is(Game.State.PLAYING));
        assertThat(game.getPlayerNames(), is(Arrays.asList("john","mary")));

    }

    @Test(expected = JoiningNotAllowedException.class)
    public void not_allow_joining_if_not_open() {

        Game game = new Game(new Deck());

        game.join("john");
        game.join("mary");
        game.join("alex");

    }

    // TODO: When the game is PLAYING
    // any player that joined the game can pick a card.


    @Test
    public void allow_a_player_pick_a_card_when_playing() {

        Card card = new Card(3,2,5);
        Deck deck = new Deck();
        deck.add(card);
        Game game = new Game(deck);

        game.join("john");
        game.join("mary");

        Card pickCard = game.pickCard("john");

        assertThat(card, is(pickCard));
    }

    @Test( expected = PlayerNotInTheGameException.class)
    public void not_allow_picking_card_if_player_did_not_join() {

        // Creating the necessary objects
        Game game = new Game(new Deck());

        // Calling some method(s)
        game.join("john");
        game.join("mary");

        // assert/ check something you expect
        Card pickedCard = game.pickCard("alex");

    }

    // TODO: After picking a card, a player must keep it or discard it,
    // before picking another


    @Test(expected = CannotPick2CardsInARowException.class)
    public void not_allow_picking_2_cards_in_a_row() {

        Deck deck = new Deck();
        deck.add(new Card(3,2,5));
        Game game = new Game(deck);

        game.join("susan");
        game.join("peter");

        game.pickCard("susan");
        game.pickCard("susan");

    }

    // TODO: check that picking is allowed when playing

    @Test
    public void allow_picking_if_previous_card_was_discarded() {

        Deck deck = new Deck();
        Card card1 = new Card(3, 2, 5);
        Card card2 = new Card(2, 7, 1);
        deck.add(card1);
        deck.add(card2);
        Game game = new Game(deck);

        game.join("susan");
        game.join("peter");

        Card pickedСard1 = game.pickCard("susan");
        game.discard("susan");
        Card pickedСard2 = game.pickCard("susan");

        assertThat(pickedСard1, is(card2));
        assertThat(pickedСard2, is(card1));

    }

    @Test (expected = GameStateIsNotPlayingException.class)
    public void not_allow_picking_cards_when_it_is_not_playing_state() {

        Deck deck = new Deck();
        Card card1 = new Card(3, 2, 5);

        deck.add(card1);
        Game game = new Game(deck);

        game.join("susan");

        Card pickedСard1 = game.pickCard("susan");

    }

    @Test(expected = UserDidNotPickCardException.class)
    public void  not_let_users_discard_card_before_picking() {

        Deck deck = new Deck();
        Card card1 = new Card(2, 3, 5);

        deck.add(card1);
        Game game = new Game(deck);

        game.join("susan");
        game.join("peter");

        game.discard("susan");
    }

    @Test(expected = UserDiscardMoreThan2CardsException.class)
    public void let_discard_only_2_cards_per_user() {
        Deck deck = new Deck();
        Card card1 = new Card(2, 3, 5);
        Card card2 = new Card(3, 2, 5);
        Card card3 = new Card(4, 3, 3);

        deck.add(card1);
        deck.add(card2);
        deck.add(card3);

        Game game = new Game(deck);

        game.join("susan");
        game.join("peter");


        Card pickedСard1 = game.pickCard("susan");
        game.discard("susan");
        Card pickedСard2 = game.pickCard("susan");
        game.discard("susan");
        Card pickedСard3 = game.pickCard("susan");
        game.discard("susan");

    }

    @Test
    public void allow_pick_1_card_and_keep_it() {
        Deck deck = new Deck();
        Card card1 = new Card(3, 2, 5);

        deck.add(card1);
        Game game = new Game(deck);

        game.join("susan");
        game.join("peter");

        Card pickedСard1 = game.pickCard("susan");
        game.keepCard("susan");


    }
}
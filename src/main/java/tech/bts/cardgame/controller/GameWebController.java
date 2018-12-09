package tech.bts.cardgame.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.model.GameUser;
import tech.bts.cardgame.service.GameService;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "/games")
public class GameWebController {
    private GameService gameService;

    @Autowired
    public GameWebController(GameService gameService) {
        this.gameService = gameService;
    }


    @RequestMapping(method = GET)
    public String getAllGames() {

        return buildGameList();
    }


    @RequestMapping(method = GET, path = "/{gameId}")
    public String getGameById(@PathVariable long gameId){

        Game game = gameService.getGameById(gameId);

        String result = "<a href=\"/games\">Go back to the games</a>";


        result += "<h1> Game "+ game.getId()+ "</h1>";
        result += "<p> State: "+ game.getState() + "</p>";
        result += "<p> Players: "+ game.getPlayerNames() + "</p>";

        if (game.getState() == Game.State.OPEN){
            result += "<a href=\"/games/"+ game.getId() + "/join\">Join the game</a>";
        }

        return result;
    }

    @RequestMapping(method = GET, path = "/{gameId}/join")
    public void joinGame(HttpServletResponse response, @PathVariable long gameId) throws IOException {

        GameUser gameUser = new GameUser(gameId,"Viktor");
        gameService.joinGame(gameUser);
        response.sendRedirect("/games/" + gameId);

    }


    @RequestMapping(method = GET,path = "/create")
    public void createGame(HttpServletResponse response) throws IOException {

        gameService.createGame();
        response.sendRedirect("/games");

    }



    private String buildGameList() {
        String result = "<h1> List of games </h1>";

        result += "<ul><a href=\"/games/create\">Create game</a></ul>";

        for (Game game : gameService.getAllGames()) {


            result += "<li><a href=\"/games/"
                    + game.getId()+ "\" >game "
                    + game.getId() + " </a> is " + game.getState() + "</li>";


        }

        return result;
    }

}

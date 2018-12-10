package tech.bts.cardgame.controller;


import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.model.GameUser;
import tech.bts.cardgame.service.GameService;
import tech.bts.cardgame.util.HandlebarsUtil;

import javax.servlet.http.HttpServletResponse;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(path = "/games")
public class GameWebController {
    private GameService gameService;
    private Handlebars handlebars;

    @Autowired
    public GameWebController(GameService gameService) {
        this.gameService = gameService;

    }


    @RequestMapping(method = GET)
    public String getAllGames() throws IOException {

        Template template = HandlebarsUtil.compile("game-list");

        Map<String, Object> values = new HashMap<>();
        values.put("games", gameService.getAllGames());


        return template.apply(values);

        /*
        String result = "<h1> List of games </h1>";

        result += "<p><a href=\"/games/create\">Create game</a></p>\n";

        result += "<ul>\n";

        for (Game game : gameService.getAllGames()) {


            result += "<li><a href=\"/games/"
                    + game.getId()+ "\" >game "
                    + game.getId() + " </a> is " + game.getState() + "</li>";


        }
        result += "</ul>\n";

        return result;
        */
    }


    @RequestMapping(method = GET, path = "/{gameId}")
    public String getGameById(@PathVariable long gameId) throws IOException {

        Game game = gameService.getGameById(gameId);

        Template template = handlebars.compile("game-detail");

        Map<String, Object> values = new HashMap<>();
        values.put("game", game);
        values.put("gameIsOpen", game.getState() == Game.State.OPEN);

        return template.apply(values);

        /*
        String result = "<a href=\"/games\">Go back to the games</a>";

        result += "<h1> Game "+ game.getId()+ "</h1>";
        result += "<p> State: "+ game.getState() + "</p>";
        result += "<p> Players: "+ game.getPlayerNames() + "</p>";

        if (game.getState() == Game.State.OPEN){
            result += "<a href=\"/games/"+ game.getId() + "/join\">Join the game</a>";
        }

        return result;
        */
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

}

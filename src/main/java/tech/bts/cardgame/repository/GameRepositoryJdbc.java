package tech.bts.cardgame.repository;

import org.springframework.stereotype.Repository;
import tech.bts.cardgame.model.Game;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Stores games in a database
 */

@Repository
public class GameRepositoryJdbc {

    private DataSource dataSource;

    public GameRepositoryJdbc() {
        this.dataSource = DataSourceUtil.getDataSourceInPath();
    }


    public void create(Game game) {

        try {

            Connection connection = dataSource.getConnection(); // connection to the database
            Statement statement = connection.createStatement(); // sql statement that you can send to the database
            statement.executeUpdate("insert into games (state, players) " +
                    "values("+ game.getState()+" , "+ game.getPlayerNames()+ " , "+")");

            statement.close();
            connection.close();


        } catch (Exception e) {

            throw new RuntimeException("Error in creating games", e);

        }


    }

    public Game getById(long id) {

        try {

            Connection connection = dataSource.getConnection(); // connection to the database
            Statement statement = connection.createStatement(); // sql statement that you can send to the database
            ResultSet rs = statement.executeQuery("select * from games where id = " + id);

            statement.executeUpdate(""); // for create method

            Game game = null;

            if (rs.next()) {
                game = getGame(rs);

            }

            rs.close();
            statement.close();
            connection.close();

            return game;

        } catch (Exception e) {

            throw new RuntimeException("Error getting the games", e);
        }


    }

    public List<Game> getAll() {

        try {

            Connection connection = dataSource.getConnection(); // connection to the database
            Statement statement = connection.createStatement(); // sql statement that you can send to the database
            ResultSet rs = statement.executeQuery("select * from games");


            List<Game> games = new ArrayList<>();


            while (rs.next()) {


                Game game = getGame(rs);
                games.add(game);
            }

            rs.close();
            statement.close();
            connection.close();


            return games;

        } catch (Exception e) {

             throw new RuntimeException("Error getting the games", e);
        }

    }

    public Game getGame(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        //String state = rs.getString("state");
        String players = rs.getString("players");


        Game game = new Game(null);
        game.setId(id);


        if (players != null) {
            String[] names = players.split(","); // splits the string when it finds a ","
            for (String name : names) {
                game.join(name);
            }
        }
        return game;
    }
}

package tech.bts.cardgame.repository;

import com.github.jknack.handlebars.internal.lang3.ObjectUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tech.bts.cardgame.model.Game;
import javax.sql.DataSource;
import javax.validation.constraints.Null;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Stores games in a database
 */

@Repository
public class GameRepositoryJdbc {

    private JdbcTemplate jdbcTemplate;

    public GameRepositoryJdbc() {
        DataSource dataSource = DataSourceUtil.getDataSourceInPath();
        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }


    public void create(Game game) {

        String sql = "insert into games (state, players)" +
                " values ('" + game.getState() + "', NULL)";

        jdbcTemplate.update(sql);

    }


    public void update(Game game) {


        String sql = "update games set state = " + game.getState() +
                "players = " + " " + " where id = " + game.getId();

        jdbcTemplate.update(sql);

    }

    public void createOrUpdate(Game game) {

        if (game.getState() == null) {
            create(game);
        } else {
            update(game);
        }
    }

    public Game getById(long id) {

        return jdbcTemplate.queryForObject(
                "select * from games where id = " + id,
                (rs1, rowNum) -> getGame(rs1));
    }

    public List<Game> getAll() {

        return jdbcTemplate.query(
                "select * from games",
                (rs1, rowNum) -> getGame(rs1));

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

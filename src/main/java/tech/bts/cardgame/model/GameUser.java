package tech.bts.cardgame.model;

public class GameUser {

    private long gameId;
    private String username;

    public GameUser() {
        //Needed to POST in String Boot
    }

    public GameUser(long gameId, String username) {
        this.gameId = gameId;
        this.username = username;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public long getGameId() {
        return gameId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

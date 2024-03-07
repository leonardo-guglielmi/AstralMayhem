package com.mygdx.databaseConnection;

public class Result {

    private String playerName;
    private int points;
    private int time;

    public Result(String playerName, int points, int time) {
        this.playerName = playerName;
        this.points = points;
        this.time = time;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Result{" +
                "playerName='" + playerName + '\'' +
                ", points=" + points +
                ", time=" + time +
                '}';
    }
}

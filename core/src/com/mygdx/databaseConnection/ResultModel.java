package com.mygdx.databaseConnection;

public class ResultModel {

    private final String player;
    private final int points;
    private final int time;

    public ResultModel(String player, int points, int time) {
        this.player = player;
        this.points = points;
        this.time = time;
    }

    public String getPlayer() {
        return player;
    }

    public int getPoints() {
        return points;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        return  "player=" + player +
                ", points=" + points +
                ", time=" + time;
    }
}

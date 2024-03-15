package com.mygdx.databaseConnection;

public class Result {

    private String player;
    private int points;
    private int time;

    public Result(String player, int points, int time) {
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
        return "Result{" +
                "player=" + player +
                ", points=" + points +
                ", time=" + time +
                '}';
    }
}

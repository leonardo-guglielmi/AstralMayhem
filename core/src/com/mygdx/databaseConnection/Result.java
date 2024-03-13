package com.mygdx.databaseConnection;

public class Result {

    private int points;
    private int time;

    public Result(int points, int time) {
        this.points = points;
        this.time = time;
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
                "points=" + points +
                ", time=" + time +
                '}';
    }
}

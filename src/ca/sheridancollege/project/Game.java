package ca.sheridancollege.project;

import java.util.ArrayList;

public abstract class Game<T extends Player> {
    private ArrayList<T> players;

    public Game(String name) {
        players = new ArrayList<>();
    }

    public ArrayList<T> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<T> players) {
        this.players = players;
    }

    public abstract void play();
    public abstract void declareWinner();
}


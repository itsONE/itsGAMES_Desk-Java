package utfpr.itsone.data;

import utfpr.itsone.model.core.Game;

import java.util.ArrayList;
import java.util.List;

public class GameData {
    private ArrayList<Game> games= new ArrayList<>();

    public static GameData getData() {
        return Data.INSTANCE;
    }

    private static class Data {
        private static final GameData INSTANCE = new GameData();
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void addGame(Game game){
        games.add(game);
    }

    public void addGames(List<Game> game){
        games.addAll(game);
    }
}

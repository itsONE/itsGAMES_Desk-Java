package utfpr.itsone.model.interfaces;

import utfpr.itsone.model.Game;

import java.util.List;

public interface ImplementGame {

    void insert(Game game);

    void update(Game game);

    void delete(int id);

    List<Game> getGame(String name);

    List<Game> getAllGame();

}

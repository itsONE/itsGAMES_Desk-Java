package utfpr.itsone.model.interfaces;

import utfpr.itsone.model.Game;

import java.util.List;

public interface ImplementGame {

    void insert(Game game, int id);

    void update(Game game, int id, int grade);

    void delete(Game game, int id);

    int gameReview(Game game, int id);

    float allReview(Game game);

    List<Game> getGame(String name);

    boolean getGameUser(Game game, int id);

    List<Game> getAllGameSortName();

    List<Game> getAllGameSortDate();

    List<Game> getAllGameUser(int id);

    List<Game> getAllGame();

}

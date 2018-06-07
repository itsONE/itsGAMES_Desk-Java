package utfpr.itsone.controller;

import utfpr.itsone.model.Game;
import utfpr.itsone.model.dao.GameData;
import utfpr.itsone.model.interfaces.ImplementGame;
import utfpr.itsone.view.Index;

import java.awt.*;
import java.util.List;

public class GameController {
    private final Index panel;
    private final ImplementGame implementGame;
    private List<Game> list;

    public GameController(Index panel) {
        this.panel = panel;
        implementGame = new GameData();
        list = implementGame.getAllGame();
    }

    public void setList() {
        list = implementGame.getAllGame();
        panel.getContent().listAllGames(list);
    }

    public void setListSortName() {
        list = implementGame.getAllGameSortName();
        panel.getContent().listAllGames(list);
    }

    public void getData() {
        list = implementGame.getGame(panel.getTopBar().getSearchField().getText());
        panel.getContent().listAllGames(list);
    }

    public void setHeader(Game game){
        panel.getHeader().getTitle().setForeground(new Color(0xffffff));
        panel.getHeader().getTitle().setText(game.getName());
        panel.getHeader().setGame(game);
    }

    public void setStandardHeader(){
        panel.getHeader().getTitle().setForeground(new Color(0x000000));
        panel.getHeader().getTitle().setText("itsGAMES");
        panel.getHeader().setGame(null);
    }

    public void setListSortDate() {
        list = implementGame.getAllGameSortDate();
        panel.getContent().listAllGames(list);
    }

    public void setListGameUser() {
        list = implementGame.getAllGameUser(Session.getSession().getId());
        panel.getContent().listAllGames(list);
    }

    public void addGameUser(Game game, boolean exists){
        if (exists)
            implementGame.delete(game,Session.getSession().getId());
        else
            implementGame.insert(game,Session.getSession().getId());
    }

    public boolean getGameUser(Game game){
        return implementGame.getGameUser(game,Session.getSession().getId());
    }


    public void addReviewGame(Game game, int selectedIndex) {
        implementGame.update(game,Session.getSession().getId(),selectedIndex);
    }

    public int yourReview(Game game){
        return implementGame.gameReview(game, Session.getSession().getId());
    }

    public float avgReview(Game game){
        return implementGame.allReview(game);
    }
}

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

}

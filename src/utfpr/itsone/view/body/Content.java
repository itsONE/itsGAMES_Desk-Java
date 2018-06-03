package utfpr.itsone.view.body;

import utfpr.itsone.controller.GameController;
import utfpr.itsone.model.Game;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Content extends JPanel{
    private GameList gameList;

    public Content(GameController controller){
        super();
        //setLayout(new BorderLayout());
        setBackground(new Color(0x000715));
        setPreferredSize(new Dimension(0,1000));
        gameList = new GameList(controller);
        add(gameList);
    }

    public void listAllGames(List<Game> games){
        gameList.removeAll();
        for (Game game : games)
            this.gameList.createGameView(game);
    }

    public GameList getGameList() {
        return gameList;
    }
}

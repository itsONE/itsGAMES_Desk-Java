package utfpr.itsone.view.body;

import utfpr.itsone.model.Game;

import javax.swing.*;

public class GameView extends JButton {
    private Game game;

    public GameView(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}

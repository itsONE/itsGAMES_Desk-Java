package utfpr.itsone.view.body;

import utfpr.itsone.controller.GameController;
import utfpr.itsone.model.Game;
import utfpr.itsone.view.Index;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameList extends JPanel implements MouseListener {
    private GameController controller;

    public GameList(GameController controller) {
        this.controller = controller;
        setOpaque(false);
    }

    public void createGameView(Game game){
        GameView gameView = new GameView(game);
        add(gameView);
        game.setCover();
        game.setBackground();
        ImageIcon img = new ImageIcon(game.getCover());
        Image img2 = img.getImage();
        Image newimg = img2.getScaledInstance(100, 125, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        gameView.setIcon(newIcon);
        gameView.setPreferredSize(new Dimension(100, 125));
        gameView.revalidate();
        gameView.setOpaque(false);
        gameView.repaint();
        gameView.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        if (arg0.getSource() instanceof GameView) {

        }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        if (arg0.getSource() instanceof GameView) {
                Game game = ((GameView) arg0.getSource()).getGame();
                controller.setHeader(game);
        }
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        if (arg0.getSource() instanceof GameView)
            controller.setStandardHeader();
    }

    public void setDimension(Dimension dimension) {
        setPreferredSize(dimension);
    }
}

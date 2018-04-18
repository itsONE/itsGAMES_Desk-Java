package utfpr.itsone.view.body;

import utfpr.itsone.data.GameData;
import utfpr.itsone.model.core.Game;
import utfpr.itsone.view.Index;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Content extends JPanel implements MouseListener {
    private ArrayList<GameView> gameViews = new ArrayList<>();
    private Index parent;
    private boolean clicked = false;

    public Content(Index parent){
        super();
        this.parent = parent;
        setPreferredSize(new Dimension(0,700));
        setLayout(new FlowLayout());
        setBackground(new Color(0x000715));
        for (Game game : GameData.getData().getGames()){
            GameView gameView = new GameView(game);
            gameViews.add(gameView);
            add(gameView);
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

    }

    public void active(){

    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        if (arg0.getSource() instanceof GameView) {
            Game game = ((GameView) arg0.getSource()).getGame();
            clicked = true;
            parent.getHeader().getTitle().setForeground(new Color(0xffffff));
            parent.getHeader().getTitle().setText(game.getName());
            parent.getHeader().setDescription(game.getDescription());
            parent.getHeader().create();
            parent.getHeader().setGame(game);
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
            if (!clicked) {
                Game game = ((GameView) arg0.getSource()).getGame();
                parent.getHeader().getTitle().setForeground(new Color(0xffffff));
                parent.getHeader().getTitle().setText(game.getName());
                parent.getHeader().setGame(game);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        if (arg0.getSource() instanceof GameView) {
            if (!clicked) {
                parent.getHeader().getTitle().setForeground(new Color(0x000000));
                parent.getHeader().getTitle().setText("itsGAMES");
                parent.getHeader().setGame(null);
            }
        }
    }
}

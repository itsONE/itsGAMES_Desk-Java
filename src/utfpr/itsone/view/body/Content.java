package utfpr.itsone.view.body;

import utfpr.itsone.controller.GameController;
import utfpr.itsone.model.Game;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Content extends JPanel{
    private GameList gameList;
    private GameController controller;

    public Content(GameController controller){
        super();
        setBackground(new Color(0x000715));
        this.controller = controller;
        gameList = new GameList(controller);
        setLayout(new BorderLayout());
        JScrollPane sp1 = new JScrollPane(gameList,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp1.setBorder(null);
        sp1.getViewport().setOpaque(false);
        sp1.setOpaque(false);
        add(sp1,BorderLayout.CENTER);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));
        JButton enlarge = new JButton("+");
        JButton lower = new JButton("-");
        enlarge.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameList.setSizeVal(gameList.getSizeVal()+1);
                controller.setList();
            }
        });
        lower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameList.setSizeVal(gameList.getSizeVal()-1);
                controller.setList();
            }
        });
        Color colorForeground = new Color(0xF1F1F1);
        Color colorBackground = new Color(0x000821);
        enlarge.setForeground(colorForeground);
        enlarge.setBackground(colorBackground);
        lower.setForeground(colorForeground);
        lower.setBackground(colorBackground);
        Border line = new LineBorder(new Color(0x0B1124));
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        lower.setBorder(compound);
        enlarge.setBorder(compound);

        panel.add(enlarge);
        panel.add(lower);
        panel.setOpaque(false);
        add(panel,BorderLayout.NORTH);
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

package utfpr.itsone.view.login;

import utfpr.itsone.view.menu.TopBar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sign extends JFrame{
    public static final String NAME = "Sign In - itsGAMES";
    public static final Dimension DIMENSIONS = new Dimension(500, 500);
    private Point initialClick;
    private JFrame parent = this;
    private JSeparator seperatorCenter;

    public Sign() throws HeadlessException {
        super(NAME);
        setUndecorated(true);
        setPreferredSize(DIMENSIONS);
        revalidate();
        setLayout(new BorderLayout());
        init();
        pack();
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void init() {
        JPanel menu = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel exit = new JLabel();
        add(menu,BorderLayout.NORTH);
        switchExit(false,exit,"icons8-cancel-20");
        menu.setBackground(new Color(0x000715));
        menu.add(exit);
        exit.addMouseListener(new MouseAdapter() {
            String path = "icons8-cancel-20";
            public void mouseEntered(MouseEvent e) {
                switchExit(true,exit,path);
            }
            public void mouseExited(MouseEvent e) {
                switchExit(false,exit,path);
            }
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });
        menu.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });

        menu.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                // localização da janela
                int thisX = parent.getLocation().x;
                int thisY = parent.getLocation().y;

                // determinar o quanto se moveu
                int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
                int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

                // mover para posição
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                parent.setLocation(X, Y);
            }
        });
    }

    public void switchExit(boolean Value,JLabel action, String path){
        action.setIcon(new ImageIcon(setImage("utfpr/itsone/resources/icons/"+path+(Value?"-off":"")+".png")));
    }

    public BufferedImage setImage(String path){
        try {
            return ImageIO.read(getClass().getClassLoader().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void openSignIn(TopBar topBar){
        add(new SignIn(this,topBar),BorderLayout.CENTER);
    }

    public void openSignUp(TopBar topBar){
        add(new SignUp(this,topBar),BorderLayout.CENTER);
    }
}

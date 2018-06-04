package utfpr.itsone.view.page;

import utfpr.itsone.controller.GameController;
import utfpr.itsone.controller.UserController;
import utfpr.itsone.view.login.SignIn;
import utfpr.itsone.view.login.SignUp;
import utfpr.itsone.view.menu.TopBar;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImplementPage extends JFrame{
    public static final String NAME = "ImplementPage In - itsGAMES";
    public static final Dimension DIMENSIONS = new Dimension(500, 500);
    private Point initialClick;
    private final JFrame parent = this;
    private JSeparator seperatorCenter;
    private final SignUp signUp;
    private final SignIn signIn;
    private final TopBar topBar;
    private final UserController controller;


    public ImplementPage(TopBar topBar) throws HeadlessException {
        super(NAME);
        this.controller = new UserController(this);
        this.topBar = topBar;
        this.signIn = new SignIn(this, controller);
        this.signUp = new SignUp(this, controller);
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

    public ImplementPage(String name){
        super(name);
        this.controller = new UserController(this);
        this.topBar = null;
        this.signIn = null;
        this.signUp = null;
    }

    public void init() {
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

    public SignUp getSignUp() {
        return signUp;
    }

    public SignIn getSignIn() {
        return signIn;
    }

    public TopBar getTopBar() {
        return topBar;
    }

    public void openUpdate(){
        add(signUp,BorderLayout.CENTER);
        controller.updateUser();
        JLabel text = new JLabel("*Digite senha e confirmação para atualizar a mesma");
        text.setBorder(new EmptyBorder(0, 15, 15, 0));
        text.setBackground(new Color(0x000a1f));
        text.setForeground(Color.WHITE);
        text.setOpaque(true);
        add(text,BorderLayout.SOUTH);
    }

    public void openSignIn(){
        add(signIn,BorderLayout.CENTER);
    }

    public void openSignUp(){
        add(signUp,BorderLayout.CENTER);
    }
}

package utfpr.itsone.view.menu;

import utfpr.itsone.controller.Session;
import utfpr.itsone.model.dao.UserData;
import utfpr.itsone.model.User;
import utfpr.itsone.view.body.Content;
import utfpr.itsone.view.login.Sign;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TopBar extends JPanel {
    private Content content;
    JLabel search = new JLabel();
    JLabel user = new JLabel();
    JLabel exit = new JLabel();
    JLabel maximize = new JLabel();
    JTextField searchField = new JTextField();
    JButton sign = new JButton();
    JButton register = new JButton();
    private Point initialClick;
    private JFrame parent;
    private TopBar topBar = this;
    private JSeparator seperatorCenter;

    public TopBar(final JFrame parent, Content content) {
        this.parent = parent;
        this.content = content;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        config();
        initComponents();
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
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

    private void config() {
        setBackground(new Color(0xffffff));
    }

    public void initComponents() {
        setIcons();
        search.setBorder(new EmptyBorder(10, 10, 15, 10));
        seperatorCenter = new JSeparator(SwingConstants.HORIZONTAL);
        seperatorCenter.setMaximumSize(new Dimension(parent.getWidth() - 620, 0));
        seperatorCenter.setOpaque(false);
        JSeparator separatorRight = new JSeparator(SwingConstants.HORIZONTAL);
        separatorRight.setMaximumSize(new Dimension(10, 0));
        styleButtons();
        add(search);
        textField();
        add(seperatorCenter);
        add(sign);
        //add(user);
        add(separatorRight);
        add(register);
        actionMenu();
        add(maximize);
        add(exit);
    }

    public void textField() {
        searchField.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 0, new Color(0xcccccc)));
        searchField.setMaximumSize(new Dimension(300, searchField.getPreferredSize().height + 5));
        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                content.listGame(searchField.getText().toUpperCase());
            }
        });
        add(searchField);
    }

    public void addUser() {
        if (Session.getSession().getId() > -1) {
            User user = UserData.getData().search(Session.getSession().getId());
            remove(3);
            remove(4);
            seperatorCenter.setMaximumSize(new Dimension(parent.getWidth() - 500, 0));
            add(new JLabel(new ImageIcon(setImage("utfpr/itsone/resources/icons/icons8-user-24.png"))), 3);
            add(new JLabel(user.getUsername()), 4);
        }
    }

    public void styleButtons() {
        Color colorForeground = new Color(0x8D8D8D);
        Color colorBackground = new Color(0xF1F1F1);
        sign.setText("SIGN IN");
        sign.setForeground(colorForeground);
        sign.setBackground(colorBackground);
        register.setText("REGISTER");
        register.setForeground(colorForeground);
        register.setBackground(colorBackground);
        Border line = new LineBorder(colorForeground);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        sign.setBorder(compound);
        register.setBorder(compound);
        sign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Sign().openSignIn(topBar);
            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Sign().openSignUp(topBar);
            }
        });
    }

    public void actionMenu() {
        exit.addMouseListener(new MouseAdapter() {
            String path = "icons8-cancel-20";

            public void mouseEntered(MouseEvent e) {
                switchExit(true, exit, path);
            }

            public void mouseExited(MouseEvent e) {
                switchExit(false, exit, path);
            }

            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });
        exit.setBorder(new EmptyBorder(0, 1, 22, 5));
        maximize.addMouseListener(new MouseAdapter() {
            String path = "icons8-enlarge-17";

            public void mouseEntered(MouseEvent e) {
                switchExit(true, maximize, path);
            }

            public void mouseExited(MouseEvent e) {
                switchExit(false, maximize, path);
            }

            public void mousePressed(MouseEvent e) {
                parent.setExtendedState((parent.getExtendedState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH ? JFrame.NORMAL : JFrame.MAXIMIZED_BOTH);
                seperatorCenter.setMaximumSize(new Dimension(parent.getWidth() - 620, 0));
            }
        });
        maximize.setBorder(new EmptyBorder(0, 20, 22, 1));
    }

    public void setIcons() {
        search.setIcon(new ImageIcon(setImage("utfpr/itsone/resources/icons/icons8-search-30.png")));
        //plus.setIcon(new ImageIcon(setImage("utfpr/itsone/resources/icons/plus-math-30.png")));
        exit.setIcon(new ImageIcon(setImage("utfpr/itsone/resources/icons/icons8-cancel-20.png")));
        maximize.setIcon(new ImageIcon(setImage("utfpr/itsone/resources/icons/icons8-enlarge-17.png")));
    }

    public void switchExit(boolean Value, JLabel action, String path) {
        action.setIcon(new ImageIcon(setImage("utfpr/itsone/resources/icons/" + path + (Value ? "-off" : "") + ".png")));
    }

    public BufferedImage setImage(String path) {
        try {
            return ImageIO.read(getClass().getClassLoader().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSeparator getSeperatorCenter() {
        return seperatorCenter;
    }
}

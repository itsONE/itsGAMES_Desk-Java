package utfpr.itsone.view.menu;

import utfpr.itsone.controller.Session;
import utfpr.itsone.model.dao.UserData;
import utfpr.itsone.view.body.Content;
import utfpr.itsone.view.body.Header;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class Bar extends JPanel implements MouseListener {
    private final Content content;
    private JLabel menu = new JLabel();
    private JLabel plus = new JLabel();
    private JLabel favorite = new JLabel();
    private final Header header;

    public Bar(Header header, Content content) {
        this.header = header;
        this.content = content;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        config();
        initComponents();
        addAction();
    }

    private void config() {
        setBackground(new Color(0x000a1f));
    }

    public void initComponents() {
        setIcons();
        menu.setBorder(new EmptyBorder(55, 12, 0, 12));
        plus.setBorder(new EmptyBorder(50, 9, 0, 0));
        favorite.setBorder(new EmptyBorder(50, 9, 0, 0));
        add(menu);
        add(plus);
        add(favorite);
    }

    public void addAction() {
        menu.addMouseListener(this);
        plus.addMouseListener(this);
        favorite.addMouseListener(this);
        menu.setOpaque(true);
        plus.setOpaque(true);
        favorite.setOpaque(true);
    }

    public void setIcons() {
        menu.setIcon(new ImageIcon(setImage("utfpr/itsone/resources/icons/menu-25.png")));
        plus.setIcon(new ImageIcon(setImage("utfpr/itsone/resources/icons/plus-math-30.png")));
        favorite.setIcon(new ImageIcon(setImage("utfpr/itsone/resources/icons/filled-bookmark-ribbon-30.png")));
        menu.setBackground(new Color(0x000a1f));
        plus.setBackground(new Color(0x000a1f));
        favorite.setBackground(new Color(0x000a1f));
    }

    public BufferedImage setImage(String path) {
        try {
            return ImageIO.read(getClass().getClassLoader().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        if (arg0.getSource().equals(menu)) {
            content.listAllGames();
        } else if (arg0.getSource().equals(plus)) {
            if (Session.getSession().getId() < 0)
                showMessageDialog(null, "Faça o login para favoritar o jogo");
            else
                UserData.getData().search(Session.getSession().getId()).setGames(header.getGame());
        } else if (arg0.getSource().equals(favorite)) {
            if (Session.getSession().getId() < 0)
                showMessageDialog(null, "Faça o login para ver favoritos");
            else
                content.listAllGamesUser(UserData.getData().search(Session.getSession().getId()));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        if (arg0.getSource().equals(menu))
            menu.setBackground(new Color(0x000A34));
        else if (arg0.getSource().equals(plus))
            plus.setBackground(new Color(0x000A34));
        else if (arg0.getSource().equals(favorite))
            favorite.setBackground(new Color(0x000A34));
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        if (arg0.getSource().equals(menu))
            menu.setBackground(new Color(0x000a1f));
        else if (arg0.getSource().equals(plus))
            plus.setBackground(new Color(0x000a1f));
        else if (arg0.getSource().equals(favorite))
            favorite.setBackground(new Color(0x000a1f));
    }
}

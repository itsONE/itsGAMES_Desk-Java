package utfpr.itsone.view.menu;

import utfpr.itsone.controller.Session;
import utfpr.itsone.view.Index;
import utfpr.itsone.view.body.Content;
import utfpr.itsone.view.body.Header;
import utfpr.itsone.view.page.ImplementPage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static javax.swing.JOptionPane.showMessageDialog;

public class Bar extends JPanel implements MouseListener {
    private final Content content;
    private JLabel menu = new JLabel();
    private JLabel plus = new JLabel();
    private JLabel favorite = new JLabel();
    private JLabel name = new JLabel();
    private JLabel date = new JLabel();
    private JLabel account_games = new JLabel();
    private final int sizeSeparator = 3;
    private JSeparator[] separator = new JSeparator[sizeSeparator];
    private final Color superButtonHover = new Color(0x000F2D);
    private final Color superButton = new Color(0x000a1f);
    private final Color subButtonHover = new Color(0x222928);
    private final Header header;
    private boolean clickedHome;
    private Font customFont;
    private Index index;

    public Bar(Index index, Header header, Content content) {
        this.header = header;
        this.content = content;
        this.index = index;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        config();
        initComponents();
        addAction();
    }

    private void config() {
        setBackground(new Color(0x000a1f));
    }

    public void initComponents() {
        InputStream is = getClass().getResourceAsStream("/utfpr/itsone/resources/fonts/HelveticaNeueLTStd-Th.otf");
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setIcons();
        createSep(sizeSeparator);
        menu.setBorder(new EmptyBorder(55, 12, 0, 12));
        plus.setBorder(new EmptyBorder(50, 9, 0, 0));
        favorite.setBorder(new EmptyBorder(50, 9, 0, 0));


        menu.setFont(customFont.deriveFont(Font.BOLD, 20));
        menu.setForeground(new Color(0xCACACA));
        favorite.setFont(customFont.deriveFont(Font.BOLD, 20));
        favorite.setForeground(new Color(0xCACACA));
        plus.setFont(customFont.deriveFont(Font.BOLD, 20));
        plus.setForeground(new Color(0xCACACA));
        name.setFont(customFont.deriveFont(Font.BOLD, 13));
        name.setForeground(new Color(0xCACACA));
        date.setFont(customFont.deriveFont(Font.BOLD, 13));
        date.setForeground(new Color(0xCACACA));
        account_games.setFont(customFont.deriveFont(Font.BOLD, 13));
        account_games.setForeground(new Color(0xCACACA));

        add(menu);
        add(separator[0]);
        add(name);
        add(separator[1]);
        add(date);
        add(plus);
        add(favorite);
        add(separator[2]);
        add(account_games);
    }

    public void createSep(int number){
        for(int i = 0; i < number; i++){
            separator[i] = new JSeparator();
            updateSep(i, true);
        }
    }

    public void sep(boolean to){
        for(int i = 0; i < separator.length; i++)
            updateSep(i, to);
    }

    public void updateSep(int i, boolean to){
        if (to)
            separator[i].setMaximumSize(new Dimension(0,0));
        else
            separator[i].setMaximumSize(new Dimension(0,5));
    }

    public void addAction() {
        menu.addMouseListener(this);
        plus.addMouseListener(this);
        favorite.addMouseListener(this);
        name.addMouseListener(this);
        date.addMouseListener(this);
        account_games.addMouseListener(this);
        menu.setOpaque(true);
        plus.setOpaque(true);
        favorite.setOpaque(true);
        account_games.setOpaque(true);
        name.setOpaque(true);
        date.setOpaque(true);
    }

    public void setIcons() {
        menu.setIcon(new ImageIcon(setImage("utfpr/itsone/resources/icons/menu-25.png")));
        plus.setIcon(new ImageIcon(setImage("utfpr/itsone/resources/icons/plus-math-30.png")));
        favorite.setIcon(new ImageIcon(setImage("utfpr/itsone/resources/icons/filled-bookmark-ribbon-30.png")));
        menu.setBackground(superButton);
        plus.setBackground(superButton);
        favorite.setBackground(superButton);
        name.setBackground(superButtonHover);
        date.setBackground(superButtonHover);
        account_games.setBackground(superButtonHover);
    }

    public BufferedImage setImage(String path) {
        try {
            return ImageIO.read(getClass().getClassLoader().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void collapse(){
        if(!clickedHome){
            sep(false);
            menu.setText(" HOME");
            menu.setBorder(new EmptyBorder(55, 12, 0, 10));
            name.setText("NOME");
            name.setBorder(new EmptyBorder(10, 47, 0, 0));
            date.setText("DATA");
            date.setBorder(new EmptyBorder(10, 47, 0, 0));
            plus.setText(" TIPO");
            plus.setBorder(new EmptyBorder(25, 9, 0, 10));
            favorite.setText(" CONTA");
            favorite.setBorder(new EmptyBorder(25, 9, 0, 10));
            if(Session.getSession().getId()>0){
                account_games.setText("JOGOS");
                account_games.setBorder(new EmptyBorder(10, 47, 0, 0));
            }
            index.update();
        } else {
            sep(true);
            menu.setText("");
            name.setText("");
            date.setText("");
            account_games.setText("");
            favorite.setText("");
            plus.setText("");
            menu.setBorder(new EmptyBorder(55, 12, 0, 12));
            name.setBorder(new EmptyBorder(0,0,0,0));
            date.setBorder(new EmptyBorder(0,0,0,0));
            account_games.setBorder(new EmptyBorder(0,0,0,0));
            favorite.setBorder(new EmptyBorder(50, 9, 0, 0));
            plus.setBorder(new EmptyBorder(50, 9, 0, 0));
        }
        clickedHome = !clickedHome;
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        if (arg0.getSource().equals(menu)) {
            collapse();
        } else if (arg0.getSource().equals(name)){
            index.getController().setListSortName();
        } else if (arg0.getSource().equals(date)){
            index.getController().setListSortDate();
        } else if(arg0.getSource().equals(favorite)){
            if(Session.getSession().getId()<0)
                new ImplementPage(index.getTopBar()).openSignIn();
            else {
                new ImplementPage(index.getTopBar()).openUpdate();
            }
        } else if(arg0.getSource().equals(account_games)){
            index.getController().setListGameUser();
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
        setColors(arg0, superButtonHover, subButtonHover);
    }

    private void setColors(MouseEvent arg0, Color superButtonHover, Color subButtonHover) {
        if (arg0.getSource().equals(menu))
            menu.setBackground(superButtonHover);
        else if (arg0.getSource().equals(plus))
            plus.setBackground(superButtonHover);
        else if (arg0.getSource().equals(favorite))
            favorite.setBackground(superButtonHover);
        else if (arg0.getSource().equals(name))
            name.setBackground(subButtonHover);
        else if (arg0.getSource().equals(date))
            date.setBackground(subButtonHover);
        else if (arg0.getSource().equals(account_games))
            account_games.setBackground(subButtonHover);
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        setColors(arg0, superButton, superButtonHover);
    }
}

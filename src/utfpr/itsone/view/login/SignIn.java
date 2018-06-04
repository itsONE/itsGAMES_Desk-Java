package utfpr.itsone.view.login;

import utfpr.itsone.config.hash.BCrypt;
import utfpr.itsone.controller.Session;
import utfpr.itsone.controller.UserController;
import utfpr.itsone.model.dao.UserData;
import utfpr.itsone.model.User;
import utfpr.itsone.view.menu.TopBar;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static javax.swing.JOptionPane.showMessageDialog;

public class SignIn extends JPanel implements ActionListener {
    protected JFrame parent;
    protected JLabel userLabel = new JLabel("Username");
    protected JTextField userField = new JTextField();
    protected JLabel passwordLabel = new JLabel("Password");
    protected JPasswordField passwordField = new JPasswordField();
    protected final UserController controller;


    public SignIn(JFrame parent, UserController controller){
        this.parent = parent;
        this.controller = controller;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(new Color(0x000a1f));
        createFields();
        JButton submit = styleButtons();
        submit.setText("SIGN IN");
        add(Box.createRigidArea(new Dimension(0, 25)));
        add(submit);
        /*submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("login");
            }
        });*/
        submit.addActionListener(this);
    }

    private void createFields() {
        userField.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 0, new Color(0xcccccc)));
        userField.setMaximumSize(new Dimension(300,userField.getPreferredSize().height+5));
        userField.setBackground(new Color(0x000a1f));
        userField.setForeground(new Color(0xcccccc));
        passwordField.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 0, new Color(0xcccccc)));
        passwordField.setMaximumSize(new Dimension(300,userField.getPreferredSize().height+5));
        passwordField.setBackground(new Color(0x000a1f));
        passwordField.setForeground(new Color(0xcccccc));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userLabel.setForeground(new Color(0x8D8D8D));
        passwordLabel.setForeground(new Color(0x8D8D8D));
        add(Box.createRigidArea(new Dimension(0, 150)));
        add(userLabel);
        add(Box.createRigidArea(new Dimension(0, 2)));
        add(userField);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(passwordLabel);
        add(Box.createRigidArea(new Dimension(0, 2)));
        add(passwordField);
    }

    public JButton styleButtons(){
        JButton submit = new JButton();
        Color colorForeground = new Color(0x8D8D8D);
        Color colorBackground = new Color(0xF1F1F1);
        submit.setForeground(colorForeground);
        submit.setBackground(colorBackground);
        Border line = new LineBorder(colorForeground);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        submit.setBorder(compound);
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        return submit;
    }

    public JTextField getUserField() {
        return userField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setUserField(JTextField userField) {
        this.userField = userField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (!controller.userAcess())
            showMessageDialog(null, "Usuário e/ou senha inválidos");
    }
}

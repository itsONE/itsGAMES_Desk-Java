package utfpr.itsone.view.login;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SignIn extends JPanel {
    private JLabel userLabel = new JLabel("Username");
    private JTextField userField = new JTextField();
    private JLabel passwordLabel = new JLabel("Password");
    private JPasswordField passwordField = new JPasswordField();

    public SignIn(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(new Color(0x000a1f));
        createFields();
        styleButtons();
    }

    private void createFields() {
        userField.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 0, new Color(0xcccccc)));
        userField.setMaximumSize(new Dimension(300,userField.getPreferredSize().height+5));
        userField.setBackground(new Color(0x000a1f));
        passwordField.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 0, new Color(0xcccccc)));
        passwordField.setMaximumSize(new Dimension(300,userField.getPreferredSize().height+5));
        passwordField.setBackground(new Color(0x000a1f));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 150)));
        add(userLabel);
        add(Box.createRigidArea(new Dimension(0, 2)));
        add(userField);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(passwordLabel);
        add(Box.createRigidArea(new Dimension(0, 2)));
        add(passwordField);
    }

    public void styleButtons(){
        JButton submit = new JButton("SUBMIT");
        Color colorForeground = new Color(0x8D8D8D);
        Color colorBackground = new Color(0xF1F1F1);
        submit.setText("SIGN IN");
        submit.setForeground(colorForeground);
        submit.setBackground(colorBackground);
        Border line = new LineBorder(colorForeground);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        submit.setBorder(compound);
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 25)));
        add(submit);
    }
}

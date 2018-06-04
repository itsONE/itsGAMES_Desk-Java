package utfpr.itsone.view.login;

import utfpr.itsone.controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class SignUp extends SignIn{
    private JLabel emailLabel = new JLabel("Email");
    private JTextField emailField = new JTextField();
    protected JLabel passwordLabel2 = new JLabel("Confirm Password");
    protected JPasswordField passwordField2 = new JPasswordField();
    private JButton submitRegister;

    public SignUp(JFrame parent, UserController controller) {
        super(parent, controller);
        remove(9);
        remove(8);
        register();
    }

    public void register() {
        passwordField2.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 0, new Color(0xcccccc)));
        passwordField2.setMaximumSize(new Dimension(300,userField.getPreferredSize().height+5));
        passwordField2.setBackground(new Color(0x000a1f));
        passwordField2.setForeground(new Color(0xcccccc));
        passwordField2.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel2.setForeground(new Color(0x8D8D8D));


        emailField.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 0, new Color(0xcccccc)));
        emailField.setMaximumSize(new Dimension(300,emailField.getPreferredSize().height+5));
        emailField.setBackground(new Color(0x000a1f));
        emailField.setForeground(new Color(0xcccccc));
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailLabel.setForeground(new Color(0x8D8D8D));
        emailField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent arg0) {
                emailLabel.setForeground(new Color(0x285888));
            }

            public void focusLost(FocusEvent arg0) {
                emailLabel.setForeground(new Color(0x8D8D8D));
            }
        });
        userField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent arg0) {
                userLabel.setForeground(new Color(0x285888));
            }

            public void focusLost(FocusEvent arg0) {
                userLabel.setForeground(new Color(0x8D8D8D));
            }
        });
        passwordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent arg0) {
                passwordLabel.setForeground(new Color(0x285888));
            }

            public void focusLost(FocusEvent arg0) {
                passwordLabel.setForeground(new Color(0x8D8D8D));
            }
        });
        passwordField2.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent arg0) {
                passwordLabel2.setForeground(new Color(0x285888));
            }

            public void focusLost(FocusEvent arg0) {
                passwordLabel2.setForeground(new Color(0x8D8D8D));
            }
        });
        remove(0);
        add(Box.createRigidArea(new Dimension(0, 100)),0);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(passwordLabel2);
        add(Box.createRigidArea(new Dimension(0, 2)));
        add(passwordField2);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(emailLabel);
        add(Box.createRigidArea(new Dimension(0, 2)));
        add(emailField);
        submitRegister = styleButtons();
        submitRegister.addActionListener(this);
        submitRegister.setText("SIGN UP");
        add(Box.createRigidArea(new Dimension(0, 25)));
        add(submitRegister);
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField2() {
        return passwordField2;
    }

    public void setEmailField(JTextField emailField) {
        this.emailField = emailField;
    }

    public void setPasswordField2(JPasswordField passwordField2) {
        this.passwordField2 = passwordField2;
    }

    public JButton getSubmitRegister() {
        return submitRegister;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String error = controller.isValidRegister();
        if (error.isEmpty()) {
            controller.addUser();
            parent.dispose();
        }
        else
            showMessageDialog(null, error);
    }
}

package utfpr.itsone.controller;

import utfpr.itsone.config.hash.BCrypt;
import utfpr.itsone.model.User;
import utfpr.itsone.model.dao.UserData;
import utfpr.itsone.model.interfaces.ImplementUser;
import utfpr.itsone.view.page.ImplementPage;
import utfpr.itsone.view.login.SignUp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class UserController {
    private final ImplementPage panel;
    private final ImplementUser implementUser;
    private List<User> list;

    public UserController(ImplementPage panel) {
        this.panel = panel;
        implementUser = new UserData();
    }

    public boolean isValidUsername(String user) {
        return implementUser.getUserForName(user).size() > 0;
    }

    public boolean isValidEmailText(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return !m.matches();
    }

    public boolean isValidEmail(String email) {
        return implementUser.getUserForEmail(email).size() > 0 && !isValidEmailText(email);
    }

    public String isValidData(String user, String password, String password2, String email) {
        String error = "";
        if (user.trim().isEmpty())
            error += "Nome de usuário é obrigatório\n";
        if (password.trim().isEmpty())
            error += "Senha de usuário é obrigatório\n";
        if (email.trim().isEmpty())
            error += "Email de usuário é obrigatório\n";
        if (isValidUsername(user))
            error += "Nome de usuário inválido\n";
        if (isValidEmail(email))
            error += "Email de usuário inválido\n";
        if (!password.equals(password2))
            error += "Senhas não são iguais\n";
        if (password.length() < 5)
            error += "Senha deve ter 5 ou mais caracteres\n";
        return error;
    }

    public String isValidUpdate(String user, String password, String password2, String email, User to) {
        String error = "";
        if (user.trim().isEmpty())
            error += "Nome de usuário é obrigatório\n";
        if (email.trim().isEmpty())
            error += "Email de usuário é obrigatório\n";
        if (!to.getUsername().equals(user))
            if (isValidUsername(user))
                error += "Nome de usuário inválido\n";
        if (!to.getEmail().equals(email))
            if (isValidEmail(email))
                error += "Email de usuário inválido\n";
        if (!password.trim().isEmpty()) {
            if (!password.equals(password2))
                error += "Senhas não são iguais\n";
            if (password.length() < 5)
                error += "Senha deve ter 5 ou mais caracteres\n";
        }
        return error;
    }

    public void addUser() {
        User user = new User();
        user.setUsername(panel.getSignUp().getUserField().getText());
        user.setPassword(encrpPassword(String.valueOf(panel.getSignUp().getPasswordField().getPassword())));
        user.setEmail(panel.getSignUp().getEmailField().getText());
        implementUser.insert(user);
    }

    public boolean isValidAcess(String username, String password) {
        list = implementUser.getUserForName(username);
        if (!list.isEmpty()) {
            User user = list.get(0);
            return BCrypt.checkpw(password, user.getPassword());
        }
        return false;
    }

    public String encrpPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User getUserID(int id) {
        return implementUser.getUserForID(id);
    }

    public String isValidRegister() {
        return isValidData(panel.getSignUp().getUserField().getText(), String.valueOf(panel.getSignUp().getPasswordField().getPassword()), String.valueOf(panel.getSignUp().getPasswordField2().getPassword()), panel.getSignUp().getEmailField().getText());
    }

    public boolean userAcess() {
        if (isValidAcess(panel.getSignIn().getUserField().getText(), String.valueOf(panel.getSignIn().getPasswordField().getPassword()))) {
            Session.getSession().setId(list.get(0).getId());
            panel.getTopBar().addUser(getUserID(Session.getSession().getId()));
            panel.dispose();
            return true;
        }
        return false;
    }

    public void updateUser() {
        User user = implementUser.getUserForID(Session.getSession().getId());
        SignUp update = panel.getSignUp();
        update.getUserField().setText(user.getUsername());
        update.getEmailField().setText(user.getEmail());
        update.getSubmitRegister().setText("UPDATE");
        update.getSubmitRegister().removeActionListener(panel.getSignUp());
        update.getSubmitRegister().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInsertUser();
            }
        });
    }

    public void updateInsertUser() {
        SignUp update = panel.getSignUp();
        User val = implementUser.getUserForID(Session.getSession().getId());
        String error = isValidUpdate(update.getUserField().getText(), String.valueOf(update.getPasswordField().getPassword()), String.valueOf(update.getPasswordField2().getPassword()), update.getEmailField().getText(), val);
        if (error.isEmpty()) {
            User user = new User();
            user.setId(val.getId());
            user.setUsername(update.getUserField().getText());
            user.setEmail(update.getEmailField().getText());
            if(String.valueOf(update.getPasswordField().getPassword()).isEmpty())
                user.setPassword(val.getPassword());
            else
                user.setPassword(encrpPassword(String.valueOf(update.getPasswordField().getPassword())));
            implementUser.update(user);
            showMessageDialog(null, "Atualização de dados feito com sucesso");
            panel.dispose();
            return;
        }
        showMessageDialog(null, error);
    }


}

package utfpr.itsone.model.dao;

import utfpr.itsone.config.ConfigurationsSQL;
import utfpr.itsone.config.hash.BCrypt;
import utfpr.itsone.data.DataBase;
import utfpr.itsone.model.User;
import utfpr.itsone.model.interfaces.ImplementUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserData implements ImplementUser {
    private ArrayList<User> list;
    private final DataBase connection = new DataBase(new ConfigurationsSQL());

    //Tabela usuarios
    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_USERNAME = "username";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PASSWORD = "password";

    public static final String INSERT_USER = "INSERT INTO " + TABLE_USER
            + '(' + COLUMN_USER_USERNAME + "," + COLUMN_USER_EMAIL + "," + COLUMN_USER_PASSWORD
            + ") VALUES(?,?,?)";

    public static final String CONSULT_USERNAME = "SELECT * FROM "
            + TABLE_USER + " WHERE username=?";

    public static final String CONSULT_EMAIL = "SELECT * FROM "
            + TABLE_USER + " WHERE email=?";

    public static final String CONSULT_ID = "SELECT * FROM "
            + TABLE_USER + " WHERE id=?";

    @Override
    public void insert(User user) {
        this.connection.execute(INSERT_USER,
                user.getUsername(),
                user.getEmail(),
                user.getPassword());
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> getUserForName(String name) {
        list = new ArrayList<>();
        if (getUser(name, CONSULT_USERNAME)) return list;
        return null;
    }

    @Override
    public List<User> getUserForEmail(String email) {
        list = new ArrayList<>();
        if (getUser(email, CONSULT_EMAIL)) return list;
        return null;
    }

    @Override
    public User getUserForID(int id) {
        User user = new User();
        try {
            ResultSet rs = this.connection.query(CONSULT_ID, id);
            while (rs.next())
                user = addUser(rs);
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private boolean getUser(String to, String consult) {
        try {
            ResultSet rs = this.connection.query(consult, to);
            while (rs.next())
                list.add(addUser(rs));
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    public User addUser(ResultSet rs) {
        User user = new User();
        try {
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
        } catch (SQLException ex) {
            System.out.println("Erro ao ao acessar pelo nome: " + ex.getMessage());
        }
        return user;
    }
}

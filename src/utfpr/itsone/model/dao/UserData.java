package utfpr.itsone.model.dao;

import utfpr.itsone.config.ConfigurationsSQL;
import utfpr.itsone.data.DataBaseGeneric;
import utfpr.itsone.model.User;
import utfpr.itsone.model.interfaces.ImplementUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserData extends DataBaseGeneric implements ImplementUser {
    private ArrayList<User> list;

    //Tabela usuarios
    public static final String TABLE_USER = "user_system";
    public static final String COLUMN_USER_ID = "user_system_id";
    public static final String COLUMN_USER_USERNAME = "username";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PASSWORD = "password";


    public UserData() {
        super(new ConfigurationsSQL(), TABLE_USER);
    }

    @Override
    public void insert(User user) {
        Map<Object, Object> mapObj = new HashMap<>();
        mapObj.put(COLUMN_USER_USERNAME, user.getUsername());
        mapObj.put(COLUMN_USER_EMAIL, user.getEmail());
        mapObj.put(COLUMN_USER_PASSWORD, user.getPassword());
        this.genericInsert(mapObj);
    }

    @Override
    public void update(User user) {
        Map<Object, Object> mapObj = new HashMap<>();
        Map<Object, Object> mapConditions = new HashMap<>();
        mapObj.put(COLUMN_USER_USERNAME, user.getUsername());
        mapObj.put(COLUMN_USER_EMAIL, user.getEmail());
        mapObj.put(COLUMN_USER_PASSWORD, user.getPassword());
        mapConditions.put(COLUMN_USER_ID, user.getId());
        this.genericUpdate(mapObj, mapConditions);
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> getUserForName(String name) {
        list = new ArrayList<>();
        if (getUser(name, COLUMN_USER_USERNAME)) return list;
        return null;
    }

    @Override
    public List<User> getUserForEmail(String email) {
        list = new ArrayList<>();
        if (getUser(email, COLUMN_USER_EMAIL)) return list;
        return null;
    }

    @Override
    public User getUserForID(int id) {
        User user = new User();
        try {
            ResultSet rs = this.getOne(id);
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
            ResultSet rs = this.getEqual(consult, to);
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
            user.setId(rs.getInt(COLUMN_USER_ID));
            user.setUsername(rs.getString(COLUMN_USER_USERNAME));
            user.setEmail(rs.getString(COLUMN_USER_EMAIL));
            user.setPassword(rs.getString(COLUMN_USER_PASSWORD));
        } catch (SQLException ex) {
            System.out.println("Erro ao ao acessar pelo nome: " + ex.getMessage());
        }
        return user;
    }
}

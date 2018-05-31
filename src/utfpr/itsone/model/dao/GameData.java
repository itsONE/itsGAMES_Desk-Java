package utfpr.itsone.model.dao;

import utfpr.itsone.config.ConfigurationsSQL;
import utfpr.itsone.config.hash.BCrypt;
import utfpr.itsone.data.DataBase;
import utfpr.itsone.model.Game;
import utfpr.itsone.model.interfaces.ImplementGame;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameData implements ImplementGame {
    private ArrayList<Game> list;
    private final DataBase connection = new DataBase(new ConfigurationsSQL());

    public GameData() {
        String password = "admin";
        String name;
        ResultSet rs = this.connection.query("SELECT * FROM user");
        try {
            rs.next();
            name = rs.getString("email");
            System.out.println(name);
            String passUser = rs.getString("password");

            if (BCrypt.checkpw(password, passUser))
                System.out.println("It matches");
            else
                System.out.println("It does not match");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Game game) {

    }

    @Override
    public void update(Game game) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Game> getGame(String name) {
        return null;
    }

    @Override
    public List<Game> getAllGame() {
        return null;
    }
}

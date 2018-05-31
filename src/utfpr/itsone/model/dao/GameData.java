package utfpr.itsone.model.dao;

import utfpr.itsone.config.ConfigurationsSQL;
import utfpr.itsone.data.DataBase;
import utfpr.itsone.model.Game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameData {
    private ArrayList<Game> list;
    private final DataBase connection = new DataBase(new ConfigurationsSQL());

    public GameData() {
        String name;
        ResultSet rs = this.connection.query("SELECT * FROM user");
        try {
            rs.next();
            name = rs.getString("email");
            System.out.println(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

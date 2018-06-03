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
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameData implements ImplementGame {

    private ArrayList<Game> list;
    private final DataBase connection = new DataBase(new ConfigurationsSQL());

    //Tabela jogos
    public static final String TABLE_GAME = "game";
    public static final String COLUMN_GAME_ID = "id";
    public static final String COLUMN_GAME_NAME = "name";
    public static final String COLUMN_GAME_DESCRIPTION = "description";

    //Tabela jogos de usuarios
    public static final String TABLE_GAME_USER = "game_user";
    public static final String COLUMN_ID_GAME = "game_id";
    public static final String COLUMN_ID_USER = "user_id";

    //Tabela usuarios(id)
    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_ID = "id";

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    public static final String TABLE_GAME_VIEW = "game_list";

    public static final String CREATE_GAME_VIEW = "CREATE OR REPLACE VIEW "
            + TABLE_GAME_VIEW + " AS SELECT * FROM " + TABLE_GAME;

    public static final String VIEW_ALL_GAME = "SELECT * FROM "
            + TABLE_GAME_VIEW;

    public static final String TABLE_GAME_USER_VIEW = "game_user_list";

    public static final String CREATE_GAME_USER_VIEW = "CREATE OR REPLACE VIEW "
            + TABLE_GAME_USER_VIEW + " AS SELECT * FROM " + TABLE_GAME
            + " INNER JOIN " + TABLE_GAME_USER + " ON " + COLUMN_GAME_ID
            + "=" + COLUMN_ID_GAME;

    public static final String VIEW_ALL_GAME_USER = "SELECT * FROM "
            + TABLE_GAME_USER_VIEW + " WHERE " + COLUMN_USER_ID + "=?";

    public static final String CONSULT_GAME = "SELECT * FROM "
            + TABLE_GAME_VIEW + " WHERE " + COLUMN_GAME_NAME
            + " LIKE CONCAT('%', ? , '%')";

    public GameData() {
        this.connection.execute(CREATE_GAME_VIEW);
        this.connection.execute(CREATE_GAME_USER_VIEW);
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
        list = new ArrayList<>();
        try {
            ResultSet rs = this.connection.query(CONSULT_GAME, name);
            while (rs.next())
                list.add(addGame(rs));
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Game> getAllGame() {
        list = new ArrayList<>();
        ResultSet rs = this.connection.query(VIEW_ALL_GAME);
        try {
            while (rs.next())
                list.add(addGame(rs));
            return list;
        } catch (SQLException ex) {
            System.out.println("Erro ao retornar um jogo pelo nome: " + ex.getMessage());
        }
        return null;
    }

    public Game addGame(ResultSet rs) {
        Game game = new Game();
        try {
            game.setId(rs.getInt(COLUMN_GAME_ID));
            game.setName(rs.getString(COLUMN_GAME_NAME));
            game.setDescription(rs.getString(COLUMN_GAME_DESCRIPTION));
        } catch (SQLException ex) {
            System.out.println("Erro ao ao acessar pelo nome: " + ex.getMessage());
        }
        return game;
    }

    public ArrayList<Game> getList() {
        return list;
    }
}

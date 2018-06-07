package utfpr.itsone.model.dao;

import utfpr.itsone.config.ConfigurationsSQL;
import utfpr.itsone.config.hash.BCrypt;
import utfpr.itsone.data.DataBase;
import utfpr.itsone.data.DataBaseGeneric;
import utfpr.itsone.model.Game;
import utfpr.itsone.model.interfaces.ImplementGame;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameData extends DataBaseGeneric implements ImplementGame {

    private ArrayList<Game> list;
    private final DataBase connection = new DataBase(new ConfigurationsSQL());

    //Tabela jogos
    public static final String TABLE_GAME = "game";
    public static final String COLUMN_GAME_ID = "id";
    public static final String COLUMN_GAME_NAME = "name";
    public static final String COLUMN_GAME_DESCRIPTION = "description";
    public static final String COLUMN_GAME_DATE = "date";
    public static final String COLUMN_GAME_SITE = "site";
    public static final String COLUMN_GAME_RATING = "rating";
    public static final String COLUMN_GAME_DEVELOPER = "developer";
    public static final String COLUMN_GAME_COVER = "cover";
    public static final String COLUMN_GAME_BACKGROUND = "background";


    //Tabela jogos de usuarios
    public static final String TABLE_GAME_USER = "game_user";
    public static final String COLUMN_ID_GAME = "game_id";
    public static final String COLUMN_ID_USER = "user_id";
    public static final String COLUMN_GAME_USER_REVIEW = "review";


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
            + TABLE_GAME_USER_VIEW + " WHERE " + COLUMN_ID_USER + "=?";

    public static final String CONSULT_GAME = "SELECT * FROM "
            + TABLE_GAME_VIEW + " WHERE " + COLUMN_GAME_NAME
            + " LIKE CONCAT('%', ? , '%')";

    public static final String VIEW_ALL_GAME_SORT_NAME = VIEW_ALL_GAME
            + " ORDER BY " + COLUMN_GAME_NAME + " DESC ";;

    public static final String VIEW_ALL_GAME_SORT_DATE = VIEW_ALL_GAME
            + " ORDER BY " + COLUMN_GAME_DATE + " DESC ";

    public static final String CONSULT_GAMER_USER = "SELECT * FROM " + TABLE_GAME_USER
            + " WHERE " + COLUMN_ID_GAME + "=? AND " + COLUMN_ID_USER + "=?";

    public static final String INSERT_GAME_USER = "INSERT INTO " + TABLE_GAME_USER
            + "(" + COLUMN_ID_USER + "," + COLUMN_ID_GAME +") VALUES (?,?)";

    public static final String UPDATE_GAME_USER_REVIEW = "UPDATE " + TABLE_GAME_USER
            + " SET " + COLUMN_GAME_USER_REVIEW + "=?"
            + " WHERE " + COLUMN_ID_USER + "=? AND " + COLUMN_ID_GAME + "=?";

    public static final String DELETE_GAME_USER = "DELETE FROM " + TABLE_GAME_USER
            + " WHERE " + COLUMN_ID_USER + "=? AND " + COLUMN_ID_GAME + "=?";

    public static final String AVG_GAME_USER = "SELECT AVG(" + COLUMN_GAME_USER_REVIEW
            + ") AS AVG_REVIEW FROM " + TABLE_GAME_USER + " WHERE " + COLUMN_ID_GAME +"=?";

    public static final String CONSULT_GAMER_USER_REVIEW = "SELECT " + COLUMN_GAME_USER_REVIEW + " FROM "
            + TABLE_GAME_USER + " WHERE " + COLUMN_ID_GAME + "=? AND " + COLUMN_ID_USER + "=?";


    public GameData() {
        //super(new ConfigurationsSQL(), "java_course");
        this.connection.execute(CREATE_GAME_VIEW);
        this.connection.execute(CREATE_GAME_USER_VIEW);
    }

    @Override
    public void insert(Game game, int id) {
        this.connection.execute(INSERT_GAME_USER,
                id,
                game.getId());
    }

    @Override
    public void update(Game game, int id, int grade) {
        this.connection.execute(UPDATE_GAME_USER_REVIEW,
                grade,
                id,
                game.getId());
    }

    @Override
    public void delete(Game game, int id) {
        this.connection.execute(DELETE_GAME_USER,
                id,
                game.getId());
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
    public List<Game> getAllGameSortName() {
        list = new ArrayList<>();
        ResultSet rs = this.connection.query(VIEW_ALL_GAME_SORT_NAME);
        if (getGames(rs)) return list;
        return null;
    }

    public List<Game> getAllGameSortDate(){
        list = new ArrayList<>();
        ResultSet rs = this.connection.query(VIEW_ALL_GAME_SORT_DATE);
        if (getGames(rs)) return list;
        return null;
    }

    private boolean getGames(ResultSet rs) {
        try {
            while (rs.next())
                list.add(addGame(rs));
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao retornar um jogo pelo nome: " + ex.getMessage());
        }
        return false;
    }

    @Override
    public List<Game> getAllGame() {
        list = new ArrayList<>();
        ResultSet rs = this.connection.query(VIEW_ALL_GAME);
        if (getGames(rs)) return list;
        return null;
    }

    @Override
    public List<Game> getAllGameUser(int id) {
        list = new ArrayList<>();
        ResultSet rs = this.connection.query(VIEW_ALL_GAME_USER,id);
        if (getGames(rs)) return list;
        return null;
    }

    public Game addGame(ResultSet rs) {
        Game game = new Game();
        try {
            game.setId(rs.getInt(COLUMN_GAME_ID));
            game.setName(rs.getString(COLUMN_GAME_NAME));
            game.setDescription(rs.getString(COLUMN_GAME_DESCRIPTION));
            game.setDate(rs.getDate(COLUMN_GAME_DATE));
            game.setSite(rs.getString(COLUMN_GAME_SITE));
            game.setRating(rs.getString(COLUMN_GAME_RATING).charAt(0));
            game.setDeveloper(rs.getString(COLUMN_GAME_DEVELOPER));
            game.setCover(rs.getString(COLUMN_GAME_COVER));
            game.setBackground(rs.getString(COLUMN_GAME_BACKGROUND));
        } catch (SQLException ex) {
            System.out.println("Erro ao ao acessar pelo nome: " + ex.getMessage());
        }
        return game;
    }

    public boolean getGameUser(Game game, int id){
        try {
            ResultSet rs = this.connection.query(CONSULT_GAMER_USER,game.getId(),id);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Game> getList() {
        return list;
    }

    public int gameReview(Game game, int id){
        try {
            ResultSet rs = this.connection.query(CONSULT_GAMER_USER_REVIEW,game.getId(),id);
            return rs.next()?rs.getInt(COLUMN_GAME_USER_REVIEW):-1;
        } catch (SQLException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public float allReview(Game game){
        try {
            ResultSet rs = this.connection.query(AVG_GAME_USER,game.getId());
            return rs.next()?rs.getFloat("AVG_REVIEW"): -1;
        } catch (SQLException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}

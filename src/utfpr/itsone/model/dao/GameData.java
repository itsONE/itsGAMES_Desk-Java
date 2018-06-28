package utfpr.itsone.model.dao;

import net.sf.jasperreports.engine.JRException;
import utfpr.itsone.app.JasperReportExample;
import utfpr.itsone.config.ConfigurationsSQL;
import utfpr.itsone.data.DataBaseGeneric;
import utfpr.itsone.model.Game;
import utfpr.itsone.model.interfaces.ImplementGame;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameData extends DataBaseGeneric implements ImplementGame {

    private ArrayList<Game> list;

    //Tabela jogos
    public static final String TABLE_GAME = "game";
    public static final String COLUMN_GAME_ID = "game_id";
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
    public static final String COLUMN_ID_USER = "user_system_id";
    public static final String COLUMN_GAME_USER_ACTIVE = "active";
    public static final String COLUMN_GAME_USER_REVIEW = "review";

    public GameData() {
        super(new ConfigurationsSQL());
    }

    @Override
    public void insert(Game game, int id) {
        this.setTable(TABLE_GAME_USER);
        Map<Object, Object> mapConditions = new HashMap<>();
        mapConditions.put(COLUMN_ID_GAME, game.getId());
        mapConditions.put(COLUMN_ID_USER, id);
        ResultSet rs = this.getAllCondition(mapConditions);
        try {
            if(rs.next()){
                Map<Object, Object> mapObj = new HashMap<>();
                mapConditions = new HashMap<>();
                mapObj.put(COLUMN_GAME_USER_ACTIVE, true);
                mapConditions.put(COLUMN_ID_USER, id);
                mapConditions.put(COLUMN_ID_GAME, game.getId());
                this.genericUpdate(mapObj, mapConditions);
            } else {
                Map<Object, Object> mapObj = new HashMap<>();
                mapObj.put(COLUMN_ID_GAME, game.getId());
                mapObj.put(COLUMN_ID_USER, id);
                mapObj.put(COLUMN_GAME_USER_ACTIVE, true);
                this.genericInsert(mapObj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Game game, int id, int grade) {
        this.setTable(TABLE_GAME_USER);
        Map<Object, Object> mapObj = new HashMap<>();
        Map<Object, Object> mapConditions = new HashMap<>();
        mapObj.put(COLUMN_GAME_USER_REVIEW, grade);
        mapConditions.put(COLUMN_ID_GAME, game.getId());
        mapConditions.put(COLUMN_ID_USER, id);
        this.genericUpdate(mapObj, mapConditions);
        try {
            JasperReportExample.generate("Notas de Jogos de Usuário", this.query("SELECT * FROM "  + TABLE_GAME + " JOIN "
                    + TABLE_GAME_USER + " USING (game_id) JOIN user_system USING(user_system_id)" +
                    " WHERE game_id=" + game.getId() + " AND user_system_id=" + id),"reportB.jrxml");
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Game game, int id) {
        this.setTable(TABLE_GAME_USER);
        Map<Object, Object> mapObj = new HashMap<>();
        Map<Object, Object> mapConditions = new HashMap<>();
        mapObj.put(COLUMN_GAME_USER_ACTIVE, false);
        mapConditions.put(COLUMN_ID_USER, id);
        mapConditions.put(COLUMN_ID_GAME, game.getId());
        this.genericUpdate(mapObj, mapConditions);
    }

    @Override
    public int gameReview(Game game, int id) {
        try {
            ResultSet rs = this.query("SELECT " + COLUMN_GAME_USER_REVIEW + " FROM "
                    + TABLE_GAME_USER + " WHERE " + COLUMN_ID_GAME + "=? AND " + COLUMN_ID_USER + "=?",game.getId(),id);
            return rs.next()?rs.getInt(COLUMN_GAME_USER_REVIEW):-1;
        } catch (SQLException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public float allReview(Game game) {
        try {
            ResultSet rs = this.query("SELECT AVG(" + COLUMN_GAME_USER_REVIEW
                    + ") AS AVG_REVIEW FROM " + TABLE_GAME_USER + " WHERE " + COLUMN_ID_GAME +"=?",game.getId());
            return rs.next()?rs.getFloat("AVG_REVIEW"): -1;
        } catch (SQLException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public List<Game> getGame(String name) {
        this.setTable(TABLE_GAME);
        try {
            JasperReportExample.generate("Pesquisa de '" + name + "' em Jogos", this.getLike(COLUMN_GAME_NAME, name),"report.jrxml");
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ResultSet rs = this.getLike(COLUMN_GAME_NAME, name);
        if (getGames(rs)) return list;
        return null;
    }

    public Game getGame(int id) {
        setTable(TABLE_GAME);
        ResultSet rs = this.getOne(id);
        if (getGames(rs)) return list.get(0);
        return null;
    }

    public Game addGame(ResultSet rs) {
        Game game = new Game();
        try {
            game.setId(rs.getShort(COLUMN_GAME_ID));
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

    @Override
    public boolean getGameUser(Game game, int id) {
        try {
            setTable(TABLE_GAME_USER);
            Map<Object, Object> mapConditions = new HashMap<>();
            mapConditions.put(COLUMN_ID_GAME, game.getId());
            mapConditions.put(COLUMN_ID_USER, id);
            mapConditions.put(COLUMN_GAME_USER_ACTIVE, true);
            return this.getAllCondition(mapConditions).next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Game> getAllGameSortName() {
        setTable(TABLE_GAME);
        ResultSet rs = this.getAllSort(COLUMN_GAME_NAME);
        if (getGames(rs)) return list;
        return null;
    }

    private boolean getGames(ResultSet rs) {
        list = new ArrayList<>();
        try {
            while (rs.next())
                list.add(addGame(rs));
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao retornar um curso pelo id: " + ex.getMessage());
        }
        return false;
    }

    @Override
    public List<Game> getAllGameSortDate() {
        setTable(TABLE_GAME);
        ResultSet rs = this.getAllSort(COLUMN_GAME_DATE);
        if (getGames(rs)) return list;
        return null;
    }

    @Override
    public List<Game> getAllGameUser(int id) {
        list = new ArrayList<>();
        this.setTable(TABLE_GAME_USER);
        Map<Object, Object> mapConditions = new HashMap<>();
        mapConditions.put(COLUMN_ID_USER, id);
        mapConditions.put(COLUMN_GAME_USER_ACTIVE, true);
        ResultSet rs = this.getAllCondition(mapConditions);
        this.setTable(TABLE_GAME);
        try {
            ResultSet r;
            while(rs.next()) {
                r = this.getOne(rs.getShort("game_id"), "game_id");
                r.next();
                list.add(addGame(r));
            }
             return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Game> getAllGame() {
        setTable(TABLE_GAME);
        ResultSet rs = this.getAll();
        if (getGames(rs)) return list;
        return null;
    }
}

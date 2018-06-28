package utfpr.itsone.data;

import utfpr.itsone.config.Configurations;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    private String dbType;
    private String dbHost;
    private String dbUser;
    private String dbPass;
    private String dbPort;
    private String dbBase;
    private String dbDriv;
    private String dbSSl;
    private String dbTimeZone;

    private String connString;

    private Connection dbConnection;
    private Statement dbStatement;
    private PreparedStatement dbPreparedStm;

    public Boolean connected;
    public Configurations config;

    public DataBase(){}

    public DataBase(Configurations config){
        this.config = config;
        this.connect();
}

    private String generateConnectionString(){
        return "jdbc:" + this.dbType + "://" + this.dbHost + ":" + this.dbPort + "/" + this.dbBase + "?useUnicode=true&characterEncoding=utf-8" + this.dbSSl + this.dbTimeZone;
    }

    public void connect(){
        this.dbType = this.config.TYPE;
        this.dbHost = this.config.HOST;
        this.dbUser = this.config.USER;
        this.dbPass = this.config.PASS;
        this.dbPort = this.config.PORT;
        this.dbBase = this.config.BASE;
        this.dbDriv = this.config.DRIV;
        this.dbSSl = this.config.SSL;
        this.dbTimeZone = this.config.TIMEZONE;

        this.connString = this.generateConnectionString();

        try{
            Class.forName(this.dbDriv);
            this.dbConnection = DriverManager.getConnection(this.connString, this.dbUser, this.dbPass);
            this.connected = true;
        }
        catch(ClassNotFoundException ex){
            System.out.println("Houve um erro ao carregar o driver: " + ex.getMessage());
        }
        catch(SQLException ex){
            System.out.println("Houve um erro ao tentar relizar uma conexão: " + ex.getMessage());
        }
    }

    public ResultSet query(String sql){
        try {
            this.dbStatement = dbConnection.createStatement();
            return this.dbStatement.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("Houve um erro ao tentar executar um sql (consulta) : " + ex.getMessage());
            System.out.println("[SQL] " + sql);
        }
        return null;
    }

    public ResultSet query(String sql,  ArrayList<Object> params){
        try {
            this.dbPreparedStm = this.dbConnection.prepareStatement(sql);
            int i = 1;
            for (Object o:params)
                this.dbPreparedStm.setObject(i++, o);
            return this.dbPreparedStm.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Houve um erro ao tentar executar um sql (consulta) : " + ex.getMessage());
            System.out.println("[SQL] " + sql);
        }
        return null;
    }

    public ResultSet query(String sql, Object... params){
        try {
            this.dbPreparedStm = this.dbConnection.prepareStatement(sql);
            int i = 1;
            for (Object o:params)
                this.dbPreparedStm.setObject(i++, o);
            return this.dbPreparedStm.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Houve um erro ao tentar executar um sql (consulta) : " + ex.getMessage());
            System.out.println("[SQL] " + sql);
        }
        return null;
    }

    public void execute(String sql, Object... params){
        try {
            this.dbPreparedStm = this.dbConnection.prepareStatement(sql);
            int i = 1;
            for (Object o:params)
                this.dbPreparedStm.setObject(i++, o);
            this.dbPreparedStm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Houve um erro ao tentar executar um sql (execução) : " + ex.getMessage());
            System.out.println("[SQL] " + sql);
        }
    }

    public void execute(String sql, ArrayList<Object> params){
        try {
            this.dbPreparedStm = this.dbConnection.prepareStatement(sql);
            int i = 1;
            for (Object o:params)
                this.dbPreparedStm.setObject(i++, o);
            this.dbPreparedStm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Houve um erro ao tentar executar um sql (execução) : " + ex.getMessage());
            System.out.println("[SQL] " + sql);
        }
    }

    public void disconnect(){
        try {
            this.dbConnection.close();
            this.connected = false;
        } catch (SQLException ex) {
            System.out.println("Houve um erro ao tentar fechar uma conexão: " + ex.getMessage());
        }
    }
}

package utfpr.itsone.controller;

import java.util.Date;

public class Session {
    private int id = -1;
    private Date dateCreated;

    public static Session getSession(){
        return Data.INSTANCE;
    }

    private static class Data {
        private static final Session INSTANCE = new Session();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        dateCreated = new Date();
    }
}

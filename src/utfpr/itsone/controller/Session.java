package utfpr.itsone.controller;

import java.util.Date;

public class Session {
    private long id = -1;
    private Date dateCreated;

    public static Session getSession(){
        return Data.INSTANCE;
    }

    private static class Data {
        private static final Session INSTANCE = new Session();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        dateCreated = new Date();
    }
}

package utfpr.itsone.config;

public class ConfigurationsSQL extends Configurations {
    public ConfigurationsSQL(){
        super.TYPE = "postgresql";
        super.HOST = "localhost";
        super.USER = "postgres";
        super.PASS = "106868";
        super.PORT = "5432";
        super.BASE = "one";
        super.DRIV = "org.postgresql.Driver";
        super.SSL = "&useSSL=false";
        super.TIMEZONE = "&useTimezone=true&serverTimezone=UTC";
    }
}

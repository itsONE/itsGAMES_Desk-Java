package utfpr.itsone.config;

public class ConfigurationsSQL extends Configurations {
    public ConfigurationsSQL(){
        super.TYPE = "mysql";
        super.HOST = "mysql.stackcp.com";
        super.USER = "user-onedb";
        super.PASS = "a^%M3d(v&wjTvEC6";
        super.PORT = "51325";
        super.BASE = "itsonedb-3337937e";
        super.DRIV = "com.mysql.cj.jdbc.Driver";
        super.SSL = "&useSSL=true";
    }
}

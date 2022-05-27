package org.hinoob.coreplugin.database.type;

import org.hinoob.coreplugin.CorePlugin;
import org.hinoob.coreplugin.database.IDatabase;
import org.hinoob.coreplugin.database.SQL;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQL extends SQL implements IDatabase {

    private String hostname, username, password, database;
    private int port;
    private boolean ssl;

    public MySQL(String hostname, int port, String username, String password, String database, boolean ssl) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
        this.database = database;
        this.ssl = ssl;
    }

    @Override
    public void init(CorePlugin corePlugin) {
        try {
            Properties props = new Properties();
            props.setProperty("user", username);
            props.setProperty("password", password);
            props.setProperty("useSSL", ssl ? "true" : "false");
            props.setProperty("autoReconnect", "true");
            this.connection = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + database, props);
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}

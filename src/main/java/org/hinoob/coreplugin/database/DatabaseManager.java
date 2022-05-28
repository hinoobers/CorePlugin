package org.hinoob.coreplugin.database;

import lombok.Getter;
import org.hinoob.coreplugin.CorePlugin;
import org.hinoob.coreplugin.database.type.MySQL;
import org.hinoob.coreplugin.database.type.SQLite;
import org.hinoob.coreplugin.util.ConfigManager;

import java.io.File;

public class DatabaseManager {

    @Getter private IDatabase currentDatabase;

    public void init(CorePlugin corePlugin) {
        String type = ConfigManager.get("database.type");
        if (type.equalsIgnoreCase("mysql")) {
            String host = ConfigManager.get("database.mysql.host");
            int port = ConfigManager.get("database.mysql.port");
            String database = ConfigManager.get("database.mysql.database");
            String username = ConfigManager.get("database.mysql.username");
            String password = ConfigManager.get("database.mysql.password");
            boolean ssl = ConfigManager.get("database.mysql.ssl");

            this.currentDatabase = new MySQL(host, port, username, password, database, ssl);
        }else if(type.equals("sqlite")){
            String path = ConfigManager.get("database.sqlite.path");
            path = path.replaceAll("%plugindatafolder%", corePlugin.getDataFolder().getAbsolutePath());
            this.currentDatabase = new SQLite(new File(path));
        }
        if(currentDatabase == null) return;
        currentDatabase.init(corePlugin);

        if(currentDatabase instanceof SQL){
            SQL sql = (SQL) currentDatabase;
            sql.executeUpdate("CREATE TABLE IF NOT EXISTS `users` (`uuid` VARCHAR(255));");
        }
    }

    public boolean hasAvailableDatabase() {
        return currentDatabase != null;
    }
}

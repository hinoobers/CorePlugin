package org.hinoob.coreplugin.database.type;

import lombok.SneakyThrows;
import org.hinoob.coreplugin.CorePlugin;
import org.hinoob.coreplugin.database.IDatabase;
import org.hinoob.coreplugin.database.SQL;

import java.io.File;
import java.sql.SQLException;

public class SQLite extends SQL implements IDatabase {

    private File file;

    @SneakyThrows
    public SQLite(File file){
        this.file = file;
        if(!file.exists()){
            file.createNewFile();
        }
    }

    @Override
    public void init(CorePlugin corePlugin) {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = java.sql.DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}

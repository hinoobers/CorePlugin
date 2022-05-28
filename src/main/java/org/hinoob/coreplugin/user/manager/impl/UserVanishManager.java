package org.hinoob.coreplugin.user.manager.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.hinoob.coreplugin.database.IDatabase;
import org.hinoob.coreplugin.database.SQL;
import org.hinoob.coreplugin.user.User;
import org.hinoob.coreplugin.user.manager.IUserManager;

import java.sql.ResultSet;

public class UserVanishManager implements IUserManager {

    private final User user;

    @Getter @Setter private boolean vanished;

    public UserVanishManager(User user) {
        this.user = user;
    }

    @Override
    @SneakyThrows
    public void save(IDatabase database) {
        if(database instanceof SQL){
            SQL sql = (SQL) database;
            sql.executeUpdate("UPDATE users SET vanished = '" + (this.vanished ? 1 : 0) + "' WHERE uuid = '" + user.getUuid().toString() + "';");
        }else{
            // TODO: Support other databases
        }
    }

    @Override
    @SneakyThrows
    public void load(IDatabase database) {
        if(database instanceof SQL){
            SQL sql = (SQL) database;
            ResultSet resultSet = sql.executeQuery("SELECT * FROM users WHERE uuid = '" + user.getUuid().toString() + "';");
            this.vanished = resultSet.getBoolean("vanished");
        }else{
            // TODO: Support other databases
        }
    }
}

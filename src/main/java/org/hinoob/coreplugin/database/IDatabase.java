package org.hinoob.coreplugin.database;

import lombok.SneakyThrows;
import org.hinoob.coreplugin.CorePlugin;

import java.sql.ResultSet;
import java.util.UUID;

public interface IDatabase {

    void init(CorePlugin corePlugin);

    @SneakyThrows
    default boolean userExists(UUID uuid){
        if(this instanceof SQL){
            SQL sql = (SQL) this;

            ResultSet result = sql.executeQuery("SELECT * FROM `users` WHERE `uuid` = '" + uuid.toString() + "';");
            if(!result.next()) return false;
            return true;
        }else{
            throw new Exception("Not implemented");
        }
    }

    @SneakyThrows
    default void createUser(UUID uuid){
        if(this instanceof SQL){
            SQL sql = (SQL) this;

            sql.executeUpdate("INSERT INTO `users` (`uuid`) VALUES ('" + uuid.toString() + "');");
        }else{
            throw new Exception("Not implemented");
        }
    }
}

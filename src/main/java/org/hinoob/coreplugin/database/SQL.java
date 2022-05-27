package org.hinoob.coreplugin.database;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQL {

    protected Connection connection;

    @SneakyThrows
    public void executeUpdate(String query){
        if(connection == null) return;

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
    }

    @SneakyThrows
    public ResultSet executeQuery(String query){
        if(connection == null) return null;

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }
}

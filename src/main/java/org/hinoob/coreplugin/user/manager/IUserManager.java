package org.hinoob.coreplugin.user.manager;

import org.hinoob.coreplugin.database.IDatabase;

public interface IUserManager {


    void save(IDatabase database);
    void load(IDatabase database);
}

package org.hinoob.coreplugin.module;

import org.hinoob.coreplugin.CorePlugin;

public interface IModule {

    void init(CorePlugin plugin);
    void tick(CorePlugin plugin);
}

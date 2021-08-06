package com.github.franckyi.gameadapter.api.common;

import java.util.Map;
import java.util.Set;

public interface IRegistry<T extends IRegistryValue> {
    Set<Map.Entry<IRegistryKey, T>> getEntries();

    int getIdFromKey(IIdentifier key);

    IIdentifier getKeyFromId(int id);
}

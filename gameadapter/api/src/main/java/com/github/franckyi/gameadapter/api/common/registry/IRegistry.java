package com.github.franckyi.gameadapter.api.common.registry;

import com.github.franckyi.gameadapter.api.common.IIdentifier;

import java.util.Map;
import java.util.Set;

public interface IRegistry<T extends IRegistryValue> {
    Set<Map.Entry<IRegistryKey, T>> getEntries();

    int getIdFromKey(IIdentifier key);

    IIdentifier getKeyFromId(int id);
}

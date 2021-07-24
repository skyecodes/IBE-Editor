package com.github.franckyi.gameadapter.api.common.registry;

public interface RegistryEntry {
    String getId();

    String getName();

    static RegistryEntry of(String id, String name) {
        return new RegistryEntry() {
            @Override
            public String getId() {
                return id;
            }

            @Override
            public String getName() {
                return name;
            }
        };
    }
}

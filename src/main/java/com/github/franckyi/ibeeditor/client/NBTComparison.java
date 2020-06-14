package com.github.franckyi.ibeeditor.client;

import net.minecraft.nbt.INBT;

import java.util.HashMap;
import java.util.Map;

public final class NBTComparison {
    private final Map<String, INBT> addedTags;
    private final Map<String, INBT> removedTags;

    private NBTComparison() {
        addedTags = new HashMap<>();
        removedTags = new HashMap<>();
    }

    public Map<String, INBT> getAddedTags() {
        return addedTags;
    }

    public Map<String, INBT> getRemovedTags() {
        return removedTags;
    }

    public static NBTComparison compare(INBT oldTag, INBT newTag) {
        return compare("", oldTag, newTag);
    }

    private static NBTComparison compare(String initialPath, INBT oldTag, INBT newTag) {
        NBTComparison comparison = new NBTComparison();
        if (!initialPath.isEmpty()) initialPath += ".";
        // TODO
        return comparison;
    }
}

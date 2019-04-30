package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public final class EntityIcons {

    private static final Map<EntityType<?>, ResourceLocation> map = new HashMap<>();

    private static void register(ResourceLocation resourceLocation, EntityType<?> entityType) {
        map.put(entityType, getHeadFromEntityId(resourceLocation));
    }

    public static ResourceLocation getHeadFromEntityType(EntityType<?> entityType) {
        return map.getOrDefault(entityType, TextureManager.RESOURCE_LOCATION_EMPTY);
    }

    public static ResourceLocation getHeadFromEntityId(ResourceLocation resourceLocation) {
        return new ResourceLocation(IBEEditorMod.MODID, "textures/gui/entity/" + resourceLocation.getPath() + ".png");
    }

    static void setup() {
        IBEEditorMod.LOGGER.info("Registering custom entity icons...");
        ForgeRegistries.ENTITIES.getEntries()
                .stream()
                .filter(entry -> entry.getKey().getNamespace().equals("minecraft"))
                .forEach(entry -> EntityIcons.register(entry.getKey(), entry.getValue()));
        IBEEditorMod.LOGGER.info("Custom entity icons registering complete.");
    }
}

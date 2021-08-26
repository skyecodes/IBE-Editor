package com.github.franckyi.ibeeditor;

import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.ibeeditor.base.client.ModScreenHandler;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.terraformersmc.modmenu.gui.ModsScreen;
import net.minecraft.client.gui.screen.Screen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

public class FabricIBEEditorModMenuApiImpl implements ModMenuApi {
    private final Logger logger = LogManager.getLogger();
    private Field descriptionListWidgetField;

    public FabricIBEEditorModMenuApiImpl() {
        try {
            descriptionListWidgetField = ModsScreen.class.getDeclaredField("descriptionListWidget");
            descriptionListWidgetField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            logger.error("Unable to setup the config screen factory for IBE Editor", e);
        }
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (descriptionListWidgetField == null) {
            return screen -> null;
        }
        return screen -> {
            try {
                // workaround so it doesn't open the settings screen instantly after opening the mod menu
                if (descriptionListWidgetField.get(screen) != null) {
                    ModScreenHandler.openSettingsScreen();
                }
            } catch (IllegalAccessException e) {
                logger.error("Error while opening IBE Editor settings screen from Mod Menu", e);
            }
            return (Screen) Guapi.getScreenHandler().getScreen();
        };
    }
}

package com.github.franckyi.ibeeditor;

import com.github.franckyi.ibeeditor.IBEEditor;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class IBEKeyBindings {

    public static KeyBinding openGui = new KeyBinding("Open GUI", KeyConflictContext.UNIVERSAL, Keyboard.KEY_I, IBEEditor.NAME);

    public static void register() {
        ClientRegistry.registerKeyBinding(openGui);
    }

}

package com.github.franckyi.ibeeditor.proxy;

import com.github.franckyi.ibeeditor.IBEEditor;
import com.github.franckyi.ibeeditor.util.EnchantmentsUtil;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.input.Keyboard;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ClientProxy implements IProxy {

    public static KeyBinding openGui, guiListUp, guiListDown, guiListRemove, guiListAddBefore, guiListAddAfter;
    public static List<KeyBinding> guiKeyBindings;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        openGui = new KeyBinding("Open Editor GUI", KeyConflictContext.UNIVERSAL, Keyboard.KEY_I, IBEEditor.NAME);
        guiListUp = new KeyBinding("(GUI Menu) Up", KeyConflictContext.GUI, Keyboard.KEY_NONE, IBEEditor.NAME);
        guiListDown = new KeyBinding("(GUI Menu) Down", KeyConflictContext.GUI, Keyboard.KEY_NONE, IBEEditor.NAME);
        guiListRemove = new KeyBinding("(GUI Menu) Remove", KeyConflictContext.GUI, Keyboard.KEY_NONE, IBEEditor.NAME);
        guiListAddBefore = new KeyBinding("(GUI Menu) Add before", KeyConflictContext.GUI, Keyboard.KEY_NONE, IBEEditor.NAME);
        guiListAddAfter = new KeyBinding("(GUI Menu) Add after", KeyConflictContext.GUI, Keyboard.KEY_NONE, IBEEditor.NAME);
        Stream.of(openGui, guiListUp, guiListDown, guiListRemove, guiListAddBefore, guiListAddAfter).forEach(ClientRegistry::registerKeyBinding);
        guiKeyBindings = Arrays.asList(openGui, guiListUp, guiListDown, guiListRemove, guiListAddBefore, guiListAddAfter);
    }

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        Enchantment.REGISTRY.forEach(EnchantmentsUtil.getEnchantments()::add);
    }
}

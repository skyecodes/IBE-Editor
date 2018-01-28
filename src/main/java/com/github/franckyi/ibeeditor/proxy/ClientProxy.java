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

    public static KeyBinding openGui = new KeyBinding("Open GUI", KeyConflictContext.UNIVERSAL, Keyboard.KEY_I, IBEEditor.NAME);
    public static KeyBinding guiListUp = new KeyBinding("(GUI Menu) Up", KeyConflictContext.GUI, Keyboard.KEY_NONE, IBEEditor.NAME);
    public static KeyBinding guiListDown = new KeyBinding("(GUI Menu) Down", KeyConflictContext.GUI, Keyboard.KEY_NONE, IBEEditor.NAME);
    public static KeyBinding guiListRemove = new KeyBinding("(GUI Menu) Remove", KeyConflictContext.GUI, Keyboard.KEY_NONE, IBEEditor.NAME);
    public static KeyBinding guiListAddBefore = new KeyBinding("(GUI Menu) Add before", KeyConflictContext.GUI, Keyboard.KEY_NONE, IBEEditor.NAME);
    public static KeyBinding guiListAddAfter = new KeyBinding("(GUI Menu) Add after", KeyConflictContext.GUI, Keyboard.KEY_NONE, IBEEditor.NAME);

    public static List<KeyBinding> guiKeyBindings = Arrays.asList(openGui, guiListUp, guiListDown, guiListRemove, guiListAddBefore, guiListAddAfter);

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        Stream.of(openGui, guiListUp, guiListDown, guiListRemove, guiListAddBefore, guiListAddAfter).forEach(ClientRegistry::registerKeyBinding);
    }

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        Enchantment.REGISTRY.forEach(EnchantmentsUtil.getEnchantments()::add);
    }
}

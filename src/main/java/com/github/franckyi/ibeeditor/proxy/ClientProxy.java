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

import java.util.stream.Stream;

public class ClientProxy implements IProxy {

    public static KeyBinding openGui = new KeyBinding("Open GUI", KeyConflictContext.UNIVERSAL, Keyboard.KEY_I, IBEEditor.NAME);
    public static KeyBinding guiAddProperty = new KeyBinding("Add property", KeyConflictContext.GUI, Keyboard.KEY_ADD, IBEEditor.NAME);
    public static KeyBinding guiRemoveProperty = new KeyBinding("Remove property", KeyConflictContext.GUI, Keyboard.KEY_SUBTRACT, IBEEditor.NAME);
    public static KeyBinding[] keyBindings = {openGui, guiAddProperty, guiRemoveProperty};

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        Stream.of(keyBindings).forEach(ClientRegistry::registerKeyBinding);
    }

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        Enchantment.REGISTRY.forEach(EnchantmentsUtil.getEnchantments()::add);
    }
}

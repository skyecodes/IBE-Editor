package com.github.franckyi.ibeeditor;

import com.github.franckyi.ibeeditor.editor.item.ItemEditor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

@Mod("ibeeditor")
@Mod.EventBusSubscriber
public class IBEEditorMod {

    public static final Logger LOGGER = LogManager.getLogger();
    private static final String KEYBINDING_CATEGORY = "IBE Editor";
    public static final KeyBinding KEY_OPEN_GUI = new KeyBinding("Open GUI", KeyConflictContext.UNIVERSAL, KeyModifier.NONE, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_I, KEYBINDING_CATEGORY);

    public IBEEditorMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (KEY_OPEN_GUI.isPressed()) {
                ItemStack heldItem = Minecraft.getInstance().player.getHeldItemMainhand();
                if (!heldItem.isEmpty()) {
                    openItemEditor(heldItem);
                }
            }
        }
    }

    private static void openItemEditor(ItemStack itemStack) {
        ItemEditor editor = new ItemEditor(itemStack);
        editor.open();
    }

    private void setup(FMLClientSetupEvent event) {
        ClientRegistry.registerKeyBinding(KEY_OPEN_GUI);
    }

}

package com.github.franckyi.ibeeditor;

import com.github.franckyi.ibeeditor.gui.GuiBlockEditor;
import com.github.franckyi.ibeeditor.gui.GuiItemEditor;
import com.github.franckyi.ibeeditor.proxy.ClientProxy;
import com.github.franckyi.ibeeditor.util.ChatUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

@Mod.EventBusSubscriber(modid = IBEEditor.MODID)
public class IBEEventHandler {

    /**
     * Update config
     *
     * @param event event
     */
    @SubscribeEvent
    public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(IBEEditor.MODID)) {
            ConfigManager.sync(IBEEditor.MODID, Config.Type.INSTANCE);
        }
    }

    /**
     * In game
     *
     * @param event event
     */
    @SubscribeEvent
    public static void onClientTick(final TickEvent.ClientTickEvent event) {
        if (event.phase.equals(TickEvent.Phase.START) && ClientProxy.openGui.isKeyDown() && Minecraft.getMinecraft().currentScreen == null) {
            EntityPlayerSP player = Minecraft.getMinecraft().player;
            PlayerControllerMP controller = Minecraft.getMinecraft().playerController;
            WorldClient world = Minecraft.getMinecraft().world;
            if (player != null && controller != null && world != null) {
                if (controller.isInCreativeMode()) {
                    /*if (Minecraft.getMinecraft().pointedEntity != null) {

                    } else */
                    if (Minecraft.getMinecraft().objectMouseOver != null && Minecraft.getMinecraft().objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK) {
                        Minecraft.getMinecraft().displayGuiScreen(new GuiBlockEditor(Minecraft.getMinecraft().objectMouseOver.getBlockPos()));
                    } else
                    if (!player.getHeldItemMainhand().isEmpty()) {
                        Minecraft.getMinecraft().displayGuiScreen(new GuiItemEditor(player.getHeldItemMainhand()));
                    }
                } else {
                    ChatUtil.sendError(player, "You must be in Creative mode.");
                }
            }
        }
    }

    /**
     * In GUI
     *
     * @param event event
     */
    @SubscribeEvent
    public static void onKeyPressed(GuiScreenEvent.KeyboardInputEvent.Post event) {
        if (event.getGui() instanceof GuiContainer && Keyboard.isKeyDown(ClientProxy.openGui.getKeyCode())) {
            GuiContainer gui = ((GuiContainer) event.getGui());
            if (gui.getSlotUnderMouse() != null && gui.getSlotUnderMouse().getHasStack()) {
                if (gui instanceof GuiInventory || gui instanceof GuiContainerCreative) {
                    Minecraft.getMinecraft().displayGuiScreen(new GuiItemEditor(gui.getSlotUnderMouse().getStack()));
                } else {
                    Minecraft.getMinecraft().displayGuiScreen(new GuiItemEditor(gui.getSlotUnderMouse().getStack(),
                            gui.getSlotUnderMouse().getSlotIndex(), Minecraft.getMinecraft().objectMouseOver.getBlockPos()));
                }
            }
        }
    }
}

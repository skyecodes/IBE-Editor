package com.github.franckyi.ibeeditor;

import com.github.franckyi.ibeeditor.gui.IBEGuiHandler;
import com.github.franckyi.ibeeditor.util.ChatUtil;
import com.github.franckyi.ibeeditor.util.EnumEditorType;
import com.github.franckyi.ibeeditor.util.IBEUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import static com.github.franckyi.ibeeditor.IBEEditor.LOGGER;

@Mod.EventBusSubscriber(modid = IBEEditor.MODID)
public class IBEEventHandler {

    /**
     * Update config
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
     * @param event event
     */
    @SubscribeEvent
    public static void onClientTick(final TickEvent.ClientTickEvent event) {
        if(event.phase.equals(TickEvent.Phase.START) && Keyboard.isKeyDown(IBEKeyBindings.openGui.getKeyCode()) && Minecraft.getMinecraft().currentScreen == null) {
            EntityPlayerSP player = Minecraft.getMinecraft().player;
            PlayerControllerMP controller = Minecraft.getMinecraft().playerController;
            WorldClient world = Minecraft.getMinecraft().world;
            if(player != null && controller != null && world != null) {
                if (controller.isInCreativeMode()) {
                    if (Minecraft.getMinecraft().pointedEntity != null) {
                        IBEUtil.setEntitySupplier(() -> Minecraft.getMinecraft().pointedEntity);
                        ChatUtil.sendInfo(player, "Entity editor coming soon !");
                        //player.openGui(IBEEditor.INSTANCE, IBEGuiHandler.getEditor(EnumEditorType.ENTITY), world, 0, 0, 0);
                    } else if (Minecraft.getMinecraft().objectMouseOver != null && Minecraft.getMinecraft().objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK) {
                        IBEUtil.setBlockPosSupplier(Minecraft.getMinecraft().objectMouseOver::getBlockPos);
                        ChatUtil.sendInfo(player, "Block editor coming soon !");
                        //player.openGui(IBEEditor.INSTANCE, IBEGuiHandler.getEditor(EnumEditorType.BLOCK), world, 0, 0, 0);
                    } else if (!player.getHeldItemMainhand().isEmpty()) {
                        IBEUtil.setItemSupplier(player::getHeldItemMainhand);
                        player.openGui(IBEEditor.INSTANCE, IBEGuiHandler.getEditor(EnumEditorType.ITEM), world, 0, 0, 0);
                    }
                } else {
                    ChatUtil.sendError(player, "You must be in Creative mode.");
                }
            }
        }
    }

    /**
     * In GUI
      * @param event event
     */
    @SubscribeEvent
    public static void onKeyPressed(GuiScreenEvent.KeyboardInputEvent.Post event) {
        if(event.getGui() instanceof GuiContainer && Keyboard.isKeyDown(IBEKeyBindings.openGui.getKeyCode())) {
            GuiContainer gui = ((GuiContainer) event.getGui());
            if((gui instanceof GuiInventory || gui instanceof GuiContainerCreative && ((GuiContainerCreative) gui).getSelectedTabIndex() == CreativeTabs.INVENTORY.getTabIndex())
                    && gui.getSlotUnderMouse() != null && gui.getSlotUnderMouse().getHasStack()) {
                IBEUtil.setItemSupplier(gui.getSlotUnderMouse()::getStack);
                LOGGER.info(IBEUtil.getItem().getDisplayName());
                Minecraft.getMinecraft().player.openGui(IBEEditor.INSTANCE, IBEGuiHandler.getEditor(EnumEditorType.ITEM), Minecraft.getMinecraft().world, 0, 0, 0);
            }
        }
    }
}

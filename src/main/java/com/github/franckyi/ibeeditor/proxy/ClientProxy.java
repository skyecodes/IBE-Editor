package com.github.franckyi.ibeeditor.proxy;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.Scene;
import com.github.franckyi.ibeeditor.editor.item.ItemEditor;
import com.github.franckyi.ibeeditor.network.item.BlockInventoryItemEditorMessage;
import com.github.franckyi.ibeeditor.network.item.MainHandItemEditorMessage;
import com.github.franckyi.ibeeditor.network.item.PlayerInventoryItemEditorMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.glfw.GLFW;

public class ClientProxy implements IProxy {

    private static final String KEYBINDING_CATEGORY = "IBE Editor";
    public static final KeyBinding KEY_OPEN_GUI = new KeyBinding("Open GUI", KeyConflictContext.UNIVERSAL, KeyModifier.NONE, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_I, KEYBINDING_CATEGORY);

    private static void openItemEditor(ItemStack itemStack) {
        new ItemEditor(itemStack, MainHandItemEditorMessage::new);
    }

    private void openItemEditor(Slot slot) {
        new ItemEditor(slot.getStack(), itemStack -> new PlayerInventoryItemEditorMessage(itemStack, slot.getSlotIndex()));
    }

    private void openItemEditor(Slot slot, BlockPos blockPos) {
        new ItemEditor(slot.getStack(), itemStack -> new BlockInventoryItemEditorMessage(itemStack, blockPos, slot.getSlotIndex()));
    }

    @Override
    public void setup() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(Node.NodeEventHandler.class);
        MinecraftForge.EVENT_BUS.register(Scene.ScreenEventHandler.class);
        ClientRegistry.registerKeyBinding(KEY_OPEN_GUI);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent e) {
        if (e.phase == TickEvent.Phase.END) {
            if (KEY_OPEN_GUI.isPressed()) {
                ItemStack heldItem = Minecraft.getInstance().player.getHeldItemMainhand();
                if (!heldItem.isEmpty()) {
                    openItemEditor(heldItem);
                }
            }
        }
    }

    @SubscribeEvent
    public void onKeyPressed(GuiScreenEvent.KeyboardKeyPressedEvent.Pre e) {
        if (e.getGui() instanceof GuiContainer && e.getKeyCode() == KEY_OPEN_GUI.getKey().getKeyCode()) {
            GuiContainer gui = (GuiContainer) e.getGui();
            if (gui.getSlotUnderMouse() != null && gui.getSlotUnderMouse().getHasStack()) {
                if (gui instanceof GuiInventory || gui instanceof GuiContainerCreative) {
                    openItemEditor(gui.getSlotUnderMouse());
                } else {
                    openItemEditor(gui.getSlotUnderMouse(), Minecraft.getInstance().objectMouseOver.getBlockPos());
                }
            }
        }
    }
}

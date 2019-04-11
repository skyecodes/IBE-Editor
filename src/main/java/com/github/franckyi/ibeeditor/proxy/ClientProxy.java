package com.github.franckyi.ibeeditor.proxy;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.Scene;
import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.editor.item.ItemEditor;
import com.github.franckyi.ibeeditor.network.block.InitBlockEditorRequest;
import com.github.franckyi.ibeeditor.network.item.BlockInventoryItemEditorMessage;
import com.github.franckyi.ibeeditor.network.item.MainHandItemEditorMessage;
import com.github.franckyi.ibeeditor.network.item.PlayerInventoryItemEditorMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.glfw.GLFW;

public class ClientProxy implements IProxy {

    private static final Minecraft mc = Minecraft.getInstance();
    private static final String KEYBINDING_CATEGORY = "IBE Editor";
    public static final KeyBinding KEY_OPEN_GUI = new KeyBinding("Open GUI", KeyConflictContext.UNIVERSAL, KeyModifier.NONE, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_I, KEYBINDING_CATEGORY);

    public static boolean openEditor() {
        return openWorldEditor() || openItemEditor() || openSelfEditor();
    }

    public static boolean openWorldEditor() {
        RayTraceResult res = mc.objectMouseOver;
        switch (res.type) {
            case MISS:
                return false;
            case BLOCK:
                openBlockEditor(res.getBlockPos());
            case ENTITY:
                openEntityEditor(res.entity);
        }
        return true;
    }

    public static boolean openItemEditor() {
        ItemStack heldItem = mc.player.getHeldItemMainhand();
        if (!heldItem.isEmpty()) {
            openItemEditor(heldItem);
            return true;
        }
        return false;
    }

    public static boolean openEntityEditor() {
        RayTraceResult res = mc.objectMouseOver;
        if (res.type == RayTraceResult.Type.ENTITY) {
            openEntityEditor(res.entity);
            return true;
        }
        return false;
    }

    public static boolean openBlockEditor() {
        RayTraceResult res = mc.objectMouseOver;
        if (res.type == RayTraceResult.Type.BLOCK) {
            openBlockEditor(res.getBlockPos());
            return true;
        }
        return false;
    }

    public static boolean openSelfEditor() {
        openEntityEditor(mc.player);
        return true;
    }

    private static void openEntityEditor(Entity entity) {

    }

    private static void openBlockEditor(BlockPos blockPos) {
        IBEEditorMod.CHANNEL.sendToServer(new InitBlockEditorRequest(blockPos));
    }

    private static void openItemEditor(ItemStack itemStack) {
        new ItemEditor(itemStack, MainHandItemEditorMessage::new);
    }

    private static void openItemEditorFromGui(Slot slot) {
        new ItemEditor(slot.getStack(), itemStack -> new PlayerInventoryItemEditorMessage(itemStack, slot.getSlotIndex()));
    }

    private static void openItemEditorFromGui(Slot slot, BlockPos blockPos) {
        new ItemEditor(slot.getStack(), itemStack -> new BlockInventoryItemEditorMessage(itemStack, blockPos, slot.getSlotIndex()));
    }

    @Override
    public void onSetup() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(Node.NodeEventHandler.class);
        MinecraftForge.EVENT_BUS.register(Scene.ScreenEventHandler.class);
        ClientRegistry.registerKeyBinding(KEY_OPEN_GUI);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent e) {
        if (e.phase == TickEvent.Phase.END) {
            if (KEY_OPEN_GUI.isPressed()) {
                openEditor();
            }
        }
    }

    @SubscribeEvent
    public void onKeyPressed(GuiScreenEvent.KeyboardKeyPressedEvent.Pre e) {
        if (e.getGui() instanceof GuiContainer && e.getKeyCode() == KEY_OPEN_GUI.getKey().getKeyCode()) {
            GuiContainer gui = (GuiContainer) e.getGui();
            if (gui.getSlotUnderMouse() != null && gui.getSlotUnderMouse().getHasStack()) {
                if (gui instanceof GuiInventory || gui instanceof GuiContainerCreative) {
                    openItemEditorFromGui(gui.getSlotUnderMouse());
                } else {
                    openItemEditorFromGui(gui.getSlotUnderMouse(), Minecraft.getInstance().objectMouseOver.getBlockPos());
                }
            }
        }
    }
}

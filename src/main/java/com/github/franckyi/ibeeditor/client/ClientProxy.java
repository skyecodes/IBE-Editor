package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.Scene;
import com.github.franckyi.guapi.util.Notification;
import com.github.franckyi.ibeeditor.client.clipboard.IBEClipboard;
import com.github.franckyi.ibeeditor.client.gui.clipboard.ViewClipboard;
import com.github.franckyi.ibeeditor.common.IProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class ClientProxy implements IProxy {

    private static final Minecraft mc = Minecraft.getInstance();
    private static final String KEYBINDING_CATEGORY = "IBE Editor";
    private static final KeyBinding KEY_OPEN_EDITOR = new KeyBinding("Open GUI", KeyConflictContext.UNIVERSAL,
            KeyModifier.NONE, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_I, KEYBINDING_CATEGORY);
    private static final KeyBinding KEY_OPEN_CLIPBOARD = new KeyBinding("Open IBE clipboard", KeyConflictContext.UNIVERSAL,
            KeyModifier.NONE, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_J, KEYBINDING_CATEGORY);

    @Override
    public void onSetup() {
        MinecraftForge.EVENT_BUS.addListener(this::onClientTick);
        MinecraftForge.EVENT_BUS.addListener(this::onKeyPressed);
        MinecraftForge.EVENT_BUS.addListener(this::onWorldUnload);
        MinecraftForge.EVENT_BUS.register(Node.NodeEventHandler.class);
        MinecraftForge.EVENT_BUS.register(Scene.ScreenEventHandler.class);
        MinecraftForge.EVENT_BUS.register(Notification.class);
        ClientRegistry.registerKeyBinding(KEY_OPEN_EDITOR);
        ClientRegistry.registerKeyBinding(KEY_OPEN_CLIPBOARD);
        IBEClipboard.getInstance().load();
        EntityIcons.setup();
    }

    private void onClientTick(TickEvent.ClientTickEvent e) {
        if (e.phase == TickEvent.Phase.END) {
            if (KEY_OPEN_EDITOR.isPressed()) {
                EditorHelper.openEditor();
            } else if (KEY_OPEN_CLIPBOARD.isPressed()) {
                new ViewClipboard();
            }
        }
    }

    private void onKeyPressed(GuiScreenEvent.KeyboardKeyPressedEvent.Pre e) {
        Minecraft.getInstance().mouseHelper.ungrabMouse();
        if (e.getGui() instanceof ContainerScreen && e.getKeyCode() == KEY_OPEN_EDITOR.getKey().getKeyCode()) {
            ContainerScreen<?> gui = (ContainerScreen<?>) e.getGui();
            if (gui.getSlotUnderMouse() != null && gui.getSlotUnderMouse().getHasStack()) {
                if (gui instanceof InventoryScreen || gui instanceof CreativeScreen) {
                    EditorHelper.openItemEditorFromPlayerInventory(gui.getSlotUnderMouse());
                } else {
                    if (mc.objectMouseOver instanceof BlockRayTraceResult) {
                        EditorHelper.openItemEditorFromBlockInventory(gui.getSlotUnderMouse(), ((BlockRayTraceResult) mc.objectMouseOver).getPos());
                    } else if (mc.objectMouseOver instanceof EntityRayTraceResult) {
                        EditorHelper.openItemEditorFromEntityInventory(gui.getSlotUnderMouse(), ((EntityRayTraceResult) mc.objectMouseOver).getEntity());
                    }
                }
            }
        }
    }

    private void onWorldUnload(WorldEvent.Unload event) {
        EditorHelper.setServerEnabled(false);
    }

}

package com.github.franckyi.ibeeditor.proxy;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.Scene;
import com.github.franckyi.guapi.util.Notification;
import com.github.franckyi.ibeeditor.client.clipboard.ViewClipboard;
import com.github.franckyi.ibeeditor.client.clipboard.logic.IBEClipboard;
import com.github.franckyi.ibeeditor.client.util.EditorHelper;
import com.github.franckyi.ibeeditor.client.util.EntityIcons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
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
    public static final KeyBinding KEY_OPEN_EDITOR = new KeyBinding("Open GUI", KeyConflictContext.UNIVERSAL,
            KeyModifier.NONE, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_I, KEYBINDING_CATEGORY);
    public static final KeyBinding KEY_OPEN_CLIPBOARD = new KeyBinding("Open IBE clipboard", KeyConflictContext.UNIVERSAL,
            KeyModifier.NONE, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_J, KEYBINDING_CATEGORY);

    @Override
    public void onSetup() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(Node.NodeEventHandler.class);
        MinecraftForge.EVENT_BUS.register(Scene.ScreenEventHandler.class);
        MinecraftForge.EVENT_BUS.register(Notification.class);
        ClientRegistry.registerKeyBinding(KEY_OPEN_EDITOR);
        ClientRegistry.registerKeyBinding(KEY_OPEN_CLIPBOARD);
        IBEClipboard.getInstance().load();
        EntityIcons.setup();
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent e) {
        if (e.phase == TickEvent.Phase.END) {
            if (KEY_OPEN_EDITOR.isPressed()) {
                EditorHelper.openEditor();
            } else if (KEY_OPEN_CLIPBOARD.isPressed()) {
                new ViewClipboard();
            }
        }
    }

    @SubscribeEvent
    public void onKeyPressed(GuiScreenEvent.KeyboardKeyPressedEvent.Pre e) {
        if (e.getGui() instanceof GuiContainer && e.getKeyCode() == KEY_OPEN_EDITOR.getKey().getKeyCode()) {
            GuiContainer gui = (GuiContainer) e.getGui();
            if (gui.getSlotUnderMouse() != null && gui.getSlotUnderMouse().getHasStack()) {
                if (gui instanceof GuiInventory || gui instanceof GuiContainerCreative) {
                    EditorHelper.openItemEditorFromGui(gui.getSlotUnderMouse());
                } else {
                    switch (mc.objectMouseOver.type) {
                        case BLOCK:
                            EditorHelper.openItemEditorFromGui(gui.getSlotUnderMouse(), mc.objectMouseOver.getBlockPos());
                            break;
                        case ENTITY:
                            EditorHelper.openItemEditorFromGui(gui.getSlotUnderMouse(), mc.objectMouseOver.entity);
                            break;
                        case MISS:
                        default:
                            break;
                    }
                }
            }
        }
    }

}

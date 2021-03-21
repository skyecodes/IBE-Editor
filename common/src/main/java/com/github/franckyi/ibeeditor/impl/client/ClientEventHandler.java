package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.client.screen.Screen;
import com.github.franckyi.minecraft.api.common.Slot;
import com.github.franckyi.minecraft.api.common.world.WorldBlock;

public final class ClientEventHandler {
    public static void onKeyInput() {
        if (KeyBindings.editorKey.isPressed()) {
            ClientEditorLogic.openWorldEditor(false);
        } else if (KeyBindings.nbtEditorKey.isPressed()) {
            ClientEditorLogic.openWorldEditor(true);
        } else if (KeyBindings.clipboardKey.isPressed()) {
            ClientEditorLogic.openClipboard();
        }
    }

    public static void onScreenEvent(Screen screen, int keyCode) {
        if (keyCode == KeyBindings.editorKey.getKeyCode() || keyCode == KeyBindings.nbtEditorKey.getKeyCode()) {
            Slot slot = screen.getInventoryFocusedSlot();
            if (slot.hasStack()) {
                if (slot.isInPlayerInventory()) {
                    ClientEditorLogic.openPlayerInventoryItemEditor(slot.getStack(), keyCode == KeyBindings.nbtEditorKey.getKeyCode(), slot.getIndex(), screen.isCreativeInventoryScreen());
                } else {
                    WorldBlock block = Minecraft.getClient().getBlockMouseOver();
                    if (block != null) {
                        ClientEditorLogic.openBlockInventoryItemEditor(slot.getStack(), keyCode == KeyBindings.nbtEditorKey.getKeyCode(), slot.getIndex(), block.getBlockPos());
                    }
                }
            }
        }
    }
}

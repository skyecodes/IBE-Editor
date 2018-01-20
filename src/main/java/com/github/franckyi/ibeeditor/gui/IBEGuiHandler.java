package com.github.franckyi.ibeeditor.gui;

import com.github.franckyi.ibeeditor.IBEEditor;
import com.github.franckyi.ibeeditor.util.EnumEditorType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;

public class IBEGuiHandler implements IGuiHandler {

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 1:
                return new GuiItemEditor();
            case 2:
                return null;
            case 3:
                return null;
            default:
                return null;
        }
    }

    public static void register() {
        NetworkRegistry.INSTANCE.registerGuiHandler(IBEEditor.INSTANCE, new IBEGuiHandler());
    }

    public static int getEditor(EnumEditorType type) {
        switch (type) {
            case ITEM:
                return 1;
            case BLOCK:
                return 2;
            case ENTITY:
                return 3;
            default:
                return 0;
        }
    }
}

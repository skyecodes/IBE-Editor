package com.github.franckyi.ibeeditor.client.editor.block;

import com.github.franckyi.ibeeditor.client.editor.Category;
import com.github.franckyi.ibeeditor.client.editor.property.ButtonProperty;
import com.github.franckyi.ibeeditor.client.util.ClientUtils;
import com.github.franckyi.ibeeditor.client.util.IBENotification;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

public class ToolsBlockCategory extends Category {
    private final BlockEditor editor;

    public ToolsBlockCategory(BlockEditor editor) {
        this.editor = editor;
        this.addAll(
                new ButtonProperty("Copy /setblock command", this::copySetblockCommand,
                        TextFormatting.RED + "Be careful !", "The text formatting won't be copied",
                        "because it doesn't work with commands."),
                new ButtonProperty("Copy /give command", this::copyGiveCommand,
                        TextFormatting.RED + "Be careful !", "The text formatting won't be copied",
                        "because it doesn't work with commands.")
        );
    }

    private void copySetblockCommand() {
        mc.keyboardListener.setClipboardString(ClientUtils.unformat("/setblock ~ ~ ~ "
                + editor.getBlockState().getBlock().getRegistryName()
                + parseBlockState(editor.getBlockState())
                + (editor.getTileEntity() != null ? editor.getTileEntity().write(new NBTTagCompound()).toString() : "")
        ));
        IBENotification.show(IBENotification.Type.EDITOR, 3, TextFormatting.GREEN + "Command copied to clipboard.");
    }

    private void copyGiveCommand() {
        mc.keyboardListener.setClipboardString(ClientUtils.unformat("/give @p "
                + editor.getBlockState().getBlock().getRegistryName()
                + (editor.getTileEntity() != null ? editor.getTileEntity().write(new NBTTagCompound()).toString() : "")
                + " 1"));
        IBENotification.show(IBENotification.Type.EDITOR, 3, TextFormatting.GREEN + "Command copied to clipboard.");
    }

    private String parseBlockState(IBlockState blockState) {
        StringBuilder builder = new StringBuilder("[");
        blockState.getProperties().forEach(property -> builder
                .append(property.getName()).append("=")
                .append(blockState.get(property))
                .append(","));
        return builder
                .deleteCharAt(builder.length() - 1)
                .append("]")
                .toString();
    }
}

package com.github.franckyi.ibeeditor.common.network.editor;

import com.github.franckyi.ibeeditor.IBECommand;
import com.github.franckyi.ibeeditor.client.EditorHelper;
import com.github.franckyi.ibeeditor.common.network.IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.network.NetworkEvent;

public class OpenEditorMessage implements IMessage {

    private IBECommand.EditorArgument argument;

    public OpenEditorMessage(PacketBuffer buffer) {
        argument = IBECommand.EditorArgument.valueOf(buffer.readString(6));
    }

    public OpenEditorMessage(IBECommand.EditorArgument argument) {
        this.argument = argument;
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeString(argument.name(), 6);
    }

    @Override
    public void handle(NetworkEvent.Context ctx) {
        boolean res = false;
        switch (argument) {
            case ANY:
                res = EditorHelper.openEditor();
                break;
            case ITEM:
                res = EditorHelper.openItemEditor();
                break;
            case BLOCK:
                res = EditorHelper.openBlockEditor();
                break;
            case ENTITY:
                res = EditorHelper.openEntityEditor();
                break;
            case SELF:
                res = EditorHelper.openSelfEditor();
                break;
        }
        if (!res) {
            Minecraft.getInstance().player.sendMessage(new StringTextComponent(TextFormatting.YELLOW +
                    "Can't open editor : no " + argument + " found."));
        }
    }
}

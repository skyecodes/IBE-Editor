package com.github.franckyi.ibeeditor.network;

import com.github.franckyi.ibeeditor.IBECommand;
import com.github.franckyi.ibeeditor.client.util.EditorHelper;
import net.minecraft.network.PacketBuffer;
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
    public void handle(NetworkEvent.Context context) {
        switch (argument) {
            case ANY:
                EditorHelper.openEditor();
                break;
            case ITEM:
                EditorHelper.openItemEditor();
                break;
            case BLOCK:
                EditorHelper.openBlockEditor();
                break;
            case ENTITY:
                EditorHelper.openEntityEditor();
                break;
            case SELF:
                EditorHelper.openSelfEditor();
                break;
        }
    }
}

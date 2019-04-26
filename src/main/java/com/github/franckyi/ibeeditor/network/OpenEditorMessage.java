package com.github.franckyi.ibeeditor.network;

import com.github.franckyi.ibeeditor.IBEEditorCommand;
import com.github.franckyi.ibeeditor.client.util.EditorHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class OpenEditorMessage implements IMessage {

    private IBEEditorCommand.EditorArgument argument;

    public OpenEditorMessage(PacketBuffer buffer) {
        argument = IBEEditorCommand.EditorArgument.valueOf(buffer.readString(6));
    }

    public OpenEditorMessage(IBEEditorCommand.EditorArgument argument) {
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
                EditorHandler.openEditor();
                break;
            case ITEM:
                EditorHandler.openItemEditor();
                break;
            case BLOCK:
                EditorHandler.openBlockEditor();
                break;
            case ENTITY:
                EditorHandler.openEntityEditor();
                break;
            case SELF:
                EditorHandler.openSelfEditor();
                break;
        }
    }
}

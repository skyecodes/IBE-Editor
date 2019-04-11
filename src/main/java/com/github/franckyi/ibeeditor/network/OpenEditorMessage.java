package com.github.franckyi.ibeeditor.network;

import com.github.franckyi.ibeeditor.command.IBEEditorCommand;
import com.github.franckyi.ibeeditor.proxy.ClientProxy;
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
                ClientProxy.openEditor();
            case ITEM:
                ClientProxy.openItemEditor();
                break;
            case BLOCK:
                ClientProxy.openBlockEditor();
                break;
            case ENTITY:
                ClientProxy.openEntityEditor();
                break;
            case SELF:
                ClientProxy.openSelfEditor();
                break;
        }
    }
}

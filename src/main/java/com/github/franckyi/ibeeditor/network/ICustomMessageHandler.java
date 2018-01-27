package com.github.franckyi.ibeeditor.network;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface ICustomMessageHandler<REQ extends IMessage> extends IMessageHandler<REQ, IMessage>, BiConsumer<REQ, MessageContext> {

    @Override
    default IMessage onMessage(REQ message, MessageContext ctx) {
        FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> accept(message, ctx));
        return null;
    }

}

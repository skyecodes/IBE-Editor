package com.github.franckyi.ibeeditor;

import com.github.franckyi.ibeeditor.network.IMessage;
import com.github.franckyi.ibeeditor.network.OpenEditorMessage;
import com.github.franckyi.ibeeditor.network.block.BlockEditorMessage;
import com.github.franckyi.ibeeditor.network.block.InitBlockEditorRequest;
import com.github.franckyi.ibeeditor.network.block.InitBlockEditorResponse;
import com.github.franckyi.ibeeditor.network.entity.EntityEditorMessage;
import com.github.franckyi.ibeeditor.network.item.BlockInventoryItemEditorMessage;
import com.github.franckyi.ibeeditor.network.item.EntityInventoryItemEditorMessage;
import com.github.franckyi.ibeeditor.network.item.MainHandItemEditorMessage;
import com.github.franckyi.ibeeditor.network.item.PlayerInventoryItemEditorMessage;
import com.github.franckyi.ibeeditor.proxy.ClientProxy;
import com.github.franckyi.ibeeditor.proxy.IProxy;
import com.github.franckyi.ibeeditor.proxy.ServerProxy;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;

@Mod("ibeeditor")
public class IBEEditorMod {

    public static final String MODID = "ibeeditor";

    public static final Logger LOGGER = LogManager.getLogger();
    public static final IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);
    private static final String PROTOCOL_VERSION = "1.0.0";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MODID, "network"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals
    );
    private static int i = 0;

    public IBEEditorMod() {
        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, IBEEditorConfig.clientSpec);
        FMLJavaModLoadingContext.get().getModEventBus().register(IBEEditorConfig.class);
    }

    @SubscribeEvent
    public void onSetup(FMLCommonSetupEvent event) {
        proxy.onSetup();
        // Command
        registerMessage(OpenEditorMessage.class, OpenEditorMessage::new);
        // Item Editor
        registerMessage(MainHandItemEditorMessage.class, MainHandItemEditorMessage::new);
        registerMessage(PlayerInventoryItemEditorMessage.class, PlayerInventoryItemEditorMessage::new);
        registerMessage(BlockInventoryItemEditorMessage.class, BlockInventoryItemEditorMessage::new);
        registerMessage(EntityInventoryItemEditorMessage.class, EntityInventoryItemEditorMessage::new);
        // Block Editor
        registerMessage(InitBlockEditorRequest.class, InitBlockEditorRequest::new);
        registerMessage(InitBlockEditorResponse.class, InitBlockEditorResponse::new);
        registerMessage(BlockEditorMessage.class, BlockEditorMessage::new);
        // Entity editor
        registerMessage(EntityEditorMessage.class, EntityEditorMessage::new);
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        IBEEditorCommand.register(event.getCommandDispatcher());
    }

    private <MSG extends IMessage> void registerMessage(Class<MSG> c, Function<PacketBuffer, MSG> read) {
        CHANNEL.registerMessage(i++, c, IMessage::write, read, IMessage::handle);
    }

}

package com.github.franckyi.ibeeditor;

import com.github.franckyi.ibeeditor.network.IMessage;
import com.github.franckyi.ibeeditor.network.item.BlockInventoryItemEditorMessage;
import com.github.franckyi.ibeeditor.network.item.MainHandItemEditorMessage;
import com.github.franckyi.ibeeditor.network.item.PlayerInventoryItemEditorMessage;
import com.github.franckyi.ibeeditor.proxy.ClientProxy;
import com.github.franckyi.ibeeditor.proxy.IProxy;
import com.github.franckyi.ibeeditor.proxy.ServerProxy;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;

@Mod("ibeeditor")
public class IBEEditorMod {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("ibeeditor:network"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals
    );
    private static int i = 0;

    public IBEEditorMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(FMLCommonSetupEvent event) {
        proxy.setup();
        registerMessage(MainHandItemEditorMessage.class, MainHandItemEditorMessage::new);
        registerMessage(PlayerInventoryItemEditorMessage.class, PlayerInventoryItemEditorMessage::new);
        registerMessage(BlockInventoryItemEditorMessage.class, BlockInventoryItemEditorMessage::new);
    }

    private <MSG extends IMessage> void registerMessage(Class<MSG> c, Function<PacketBuffer, MSG> read) {
        CHANNEL.registerMessage(i++, c, IMessage::write, read, IMessage::handle);
    }

}

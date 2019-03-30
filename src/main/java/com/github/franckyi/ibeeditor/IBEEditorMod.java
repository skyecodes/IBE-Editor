package com.github.franckyi.ibeeditor;

import com.github.franckyi.ibeeditor.network.ItemEditorMessage;
import com.github.franckyi.ibeeditor.proxy.ClientProxy;
import com.github.franckyi.ibeeditor.proxy.IProxy;
import com.github.franckyi.ibeeditor.proxy.ServerProxy;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("ibeeditor")
public class IBEEditorMod {

    public static boolean testing = false;

    public static final Logger LOGGER = LogManager.getLogger();

    public static final IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("ibeeditor:network"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals
    );

    public IBEEditorMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(FMLCommonSetupEvent event) {
        int i = 0;
        proxy.setup();
        CHANNEL.registerMessage(i++, ItemEditorMessage.class, ItemEditorMessage::encode,
                ItemEditorMessage::decode, ItemEditorMessage.Handler::handle);
    }

}

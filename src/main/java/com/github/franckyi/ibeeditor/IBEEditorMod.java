package com.github.franckyi.ibeeditor;

import com.github.franckyi.ibeeditor.network.IBENetworkHandler;
import com.github.franckyi.ibeeditor.network.IMessage;
import com.github.franckyi.ibeeditor.proxy.ClientProxy;
import com.github.franckyi.ibeeditor.proxy.IProxy;
import com.github.franckyi.ibeeditor.proxy.ServerProxy;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("ibeeditor")
public class IBEEditorMod {

    public static final String MODID = "ibeeditor";

    public static final Logger LOGGER = LogManager.getLogger();
    public static final IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public IBEEditorMod() {
        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, IBEConfiguration.clientSpec);
        FMLJavaModLoadingContext.get().getModEventBus().register(IBEConfiguration.class);
    }

    @SubscribeEvent
    public void onSetup(FMLCommonSetupEvent event) {
        proxy.onSetup();
        IBENetworkHandler.init();
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        IBECommand.register(event.getCommandDispatcher());
    }

    private class IBEHandshake implements IMessage {

        private boolean payload;

        private IBEHandshake() {
            payload = true;
        }

        private IBEHandshake(PacketBuffer buffer) {
            payload = buffer.readBoolean();
        }

        @Override
        public void write(PacketBuffer buffer) {
            buffer.writeBoolean(payload);
        }

        @Override
        public void handle(NetworkEvent.Context context) {
            if (payload) {
                System.out.println("test!");
            }
        }
    }
}

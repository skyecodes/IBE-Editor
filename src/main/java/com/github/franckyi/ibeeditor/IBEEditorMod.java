package com.github.franckyi.ibeeditor;

import com.github.franckyi.ibeeditor.client.ClientProxy;
import com.github.franckyi.ibeeditor.common.IBEConfiguration;
import com.github.franckyi.ibeeditor.common.IProxy;
import com.github.franckyi.ibeeditor.common.network.IBENetworkHandler;
import com.github.franckyi.ibeeditor.common.network.handshake.S2CHandshake;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkDirection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("ibeeditor")
public class IBEEditorMod {

    public static final String MODID = "ibeeditor";

    public static final Logger LOGGER = LogManager.getLogger();
    public static final IProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> () -> new IProxy() {
    });

    public IBEEditorMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, IBEConfiguration.clientSpec);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onSetup);
        modEventBus.register(IBEConfiguration.class);
        MinecraftForge.EVENT_BUS.addListener(this::onServerStarting);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerLoggedIn);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerLoggedOut);
    }

    private void onSetup(FMLCommonSetupEvent event) {
        PROXY.onSetup();
        IBENetworkHandler.init();
    }

    private void onServerStarting(FMLServerStartingEvent event) {
        IBECommand.register(event.getCommandDispatcher());
    }

    private void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        IBENetworkHandler.getModChannel().sendTo(new S2CHandshake(),
                ((EntityPlayerMP) event.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
    }

    private void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        IBECommand.removeAllowedPlayer(event.getPlayer());
    }

}

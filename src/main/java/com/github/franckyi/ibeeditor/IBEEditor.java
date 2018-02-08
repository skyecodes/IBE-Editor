package com.github.franckyi.ibeeditor;

import com.github.franckyi.ibeeditor.network.UpdateBlockMessage;
import com.github.franckyi.ibeeditor.network.UpdateItemMessage;
import com.github.franckyi.ibeeditor.proxy.IProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = IBEEditor.MODID,
        name = IBEEditor.NAME,
        version = IBEEditor.VERSION
)
public class IBEEditor {

    public static final String MODID = "ibeeditor";
    public static final String NAME = "IBE Editor";
    public static final String VERSION = "1.0.0-alpha5";

    public static final String CLIENTPROXY = "com.github.franckyi.ibeeditor.proxy.ClientProxy";
    public static final String SERVERPROXY = "com.github.franckyi.ibeeditor.proxy.ServerProxy";

    @Mod.Instance(MODID)
    public static IBEEditor instance;

    @SidedProxy(clientSide = CLIENTPROXY, serverSide = SERVERPROXY)
    public static IProxy proxy;

    public static Logger logger;
    public static SimpleNetworkWrapper netwrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
        netwrapper.registerMessage(UpdateItemMessage.UpdateItemMessageHandler.class, UpdateItemMessage.class, 0, Side.SERVER);
        netwrapper.registerMessage(UpdateBlockMessage.UpdateBlockMessageHandler.class, UpdateBlockMessage.class, 1, Side.SERVER);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}

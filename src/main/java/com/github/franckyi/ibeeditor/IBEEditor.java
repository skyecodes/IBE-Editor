package com.github.franckyi.ibeeditor;

import com.github.franckyi.ibeeditor.gui.IBEGuiHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = IBEEditor.MODID,
        name = IBEEditor.NAME,
        version = IBEEditor.VERSION,
        clientSideOnly = true
)
public class IBEEditor {

    public static final String MODID = "ibeeditor";
    public static final String NAME = "IBE Editor";
    public static final String VERSION = "0.0.1";

    public static Logger LOGGER;

    @Mod.Instance(MODID)
    public static IBEEditor INSTANCE;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
        IBEKeyBindings.register();
        IBEGuiHandler.register();
    }

}

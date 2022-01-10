package com.github.franckyi.ibeeditor.fabric;

import com.github.franckyi.ibeeditor.common.CommonInit;
import com.github.franckyi.ibeeditor.common.ServerCommandHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

public class FabricIBEEditorModCommon implements ModInitializer {
    public FabricIBEEditorModCommon() {
        CommonInit.init();
    }

    @Override
    public void onInitialize() {
        CommonInit.setup();
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> ServerCommandHandler.registerCommand(dispatcher));
    }
}

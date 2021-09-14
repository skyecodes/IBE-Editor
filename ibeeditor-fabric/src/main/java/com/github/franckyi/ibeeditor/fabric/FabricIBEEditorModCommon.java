package com.github.franckyi.ibeeditor.fabric;

import com.github.franckyi.ibeeditor.base.common.CommonInit;
import com.github.franckyi.ibeeditor.base.common.NetworkManager;
import com.github.franckyi.ibeeditor.base.server.ServerCommandHandler;
import com.github.franckyi.ibeeditor.fabric.common.FabricNetworkManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

public class FabricIBEEditorModCommon implements ModInitializer {
    public FabricIBEEditorModCommon() {
        CommonInit.init();
    }

    @Override
    public void onInitialize() {
        NetworkManager.setup(FabricNetworkManager.INSTANCE);
        CommonInit.setup();
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> ServerCommandHandler.registerCommand(dispatcher));
    }
}

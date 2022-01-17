package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.ibeeditor.common.EditorContext;
import com.github.franckyi.ibeeditor.common.ModTexts;
import com.github.franckyi.ibeeditor.common.NetworkManager;
import com.github.franckyi.ibeeditor.common.network.NetworkHandler;
import com.github.franckyi.ibeeditor.common.network.NotificationPacket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientNetworkReceiver {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void onServerNotification(NotificationPacket packet) {
        log(NetworkManager.SERVER_NOTIFICATION);
        ClientContext.setModInstalledOnServer(true);
        ClientNetworkEmitter.sendClientNotification();
    }

    public static void onEditorCommand(EditorContext context) {
        log(NetworkManager.EDITOR_COMMAND);
        switch (context.getCommandTarget()) {
            case WORLD -> ClientEditorLogic.requestWorldEditor(context);
            case ITEM -> {
                if (!ClientEditorLogic.requestMainHandItemEditor(context)) {
                    ClientUtil.showMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.ITEM));
                }
            }
            case BLOCK -> {
                if (!ClientEditorLogic.requestBlockEditor(context)) {
                    ClientUtil.showMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.BLOCK));
                }
            }
            case ENTITY -> {
                if (!ClientEditorLogic.requestEntityEditor(context)) {
                    ClientUtil.showMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.ENTITY));
                }
            }
            case SELF -> ClientEditorLogic.requestSelfEditor(context);
        }
    }

    public static void onEditorResponse(EditorContext context) {
        log(NetworkManager.EDITOR_RESPONSE);
        ModScreenHandler.openEditor(context);
    }

    private static void log(NetworkHandler<?> handler) {
        LOGGER.debug("Receiving packet {}", handler.getLocation());
    }
}

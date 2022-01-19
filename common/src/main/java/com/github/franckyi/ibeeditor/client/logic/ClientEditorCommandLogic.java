package com.github.franckyi.ibeeditor.client.logic;

import com.github.franckyi.ibeeditor.client.ClientUtil;
import com.github.franckyi.ibeeditor.common.ModTexts;
import com.github.franckyi.ibeeditor.common.network.EditorCommandPacket;

public final class ClientEditorCommandLogic {
    public static void onEditorCommand(EditorCommandPacket command) {
        switch (command.target()) {
            case WORLD -> ClientEditorRequestLogic.requestWorldEditor(command.editorType());
            case ITEM -> {
                if (!ClientEditorRequestLogic.requestMainHandItemEditor(command.editorType())) {
                    ClientUtil.showMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.ITEM));
                }
            }
            case BLOCK -> {
                if (!ClientEditorRequestLogic.requestBlockEditor(command.editorType())) {
                    ClientUtil.showMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.BLOCK));
                }
            }
            case ENTITY -> {
                if (!ClientEditorRequestLogic.requestEntityEditor(command.editorType())) {
                    ClientUtil.showMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.ENTITY));
                }
            }
            case SELF -> ClientEditorRequestLogic.requestSelfEditor(command.editorType());
        }
    }
}

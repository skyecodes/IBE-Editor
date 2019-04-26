package com.github.franckyi.ibeeditor.client.util;

import com.github.franckyi.guapi.util.Notification;
import com.github.franckyi.ibeeditor.IBEEditorConfig;

public final class IBENotification {

    public static void show(Type type, int seconds, String... textLines) {
        if (type == Type.CLIPBOARD && IBEEditorConfig.CLIENT.showClipboardNotifications.get() ||
                type == Type.EDITOR && IBEEditorConfig.CLIENT.showEditorNotifications.get()) {
            Notification.show(seconds, textLines);
        }
    }

    public enum Type {
        CLIPBOARD, EDITOR
    }

}

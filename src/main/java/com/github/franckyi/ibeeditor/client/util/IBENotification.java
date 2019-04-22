package com.github.franckyi.ibeeditor.client.util;

import com.github.franckyi.guapi.util.Notification;
import com.github.franckyi.ibeeditor.config.IBEEditorConfig;

public class IBENotification {

    public static void show(Type type, int seconds, String... textLines) {
        if (type == Type.CLIPBOARD && IBEEditorConfig.showClipboardNotifications ||
                type == Type.EDITOR && IBEEditorConfig.showEditorNotifications) {
            Notification.show(seconds, textLines);
        }
    }

    public enum Type {
        CLIPBOARD, EDITOR
    }

}

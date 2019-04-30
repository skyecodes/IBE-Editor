package com.github.franckyi.ibeeditor.client.util;

import com.github.franckyi.guapi.util.Notification;
import com.github.franckyi.ibeeditor.IBEConfiguration;

public final class IBENotification {

    public static void show(Type type, int seconds, String... textLines) {
        if (type == Type.CLIPBOARD && IBEConfiguration.CLIENT.showClipboardNotifications.get() ||
                type == Type.EDITOR && IBEConfiguration.CLIENT.showEditorNotifications.get()) {
            Notification.show(seconds, textLines);
        }
    }

    public enum Type {
        CLIPBOARD, EDITOR
    }

}

package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.ibeeditor.common.CommonUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public final class ClientUtil {
    public static void showMessage(Component component) {
        CommonUtil.showMessage(Minecraft.getInstance().player, component);
    }
}

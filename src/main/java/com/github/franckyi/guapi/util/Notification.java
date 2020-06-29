package com.github.franckyi.guapi.util;

import net.minecraft.client.Minecraft;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Notification {

    private static final Minecraft mc = Minecraft.getInstance();
    private static final List<Notification> notifications = new ArrayList<>();
    private final List<String> textLines;
    private int counter;

    public Notification(List<String> textLines, int counter) {
        this.textLines = textLines;
        this.counter = counter;
    }

    public static void show(int seconds, String... textLines) {
        notifications.add(new Notification(Arrays.asList(textLines), seconds * 20));
    }

    @SubscribeEvent
    public static void onRenderTick(TickEvent.RenderTickEvent e) {
        if (e.phase == TickEvent.Phase.END) {
            int j = 20;
            for (Notification notification : notifications) {
                GuiUtils.drawHoveringText(notification.textLines, 0, j, mc.getMainWindow().getWidth(),
                        mc.getMainWindow().getHeight(), mc.getMainWindow().getWidth(), mc.fontRenderer);
                j += 5 + 12 * notification.textLines.size();
            }
            notifications.removeIf(notification -> notification.counter < 0);
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent e) {
        if (e.phase == TickEvent.Phase.END) {
            notifications.forEach(Notification::tick);
        }
    }

    public void tick() {
        counter--;
    }
}

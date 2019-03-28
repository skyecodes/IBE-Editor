package com.github.franckyi.ibeeditor;

import com.github.franckyi.guapi.Scene;
import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.group.VBox;
import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.*;
import com.github.franckyi.guapi.scene.Background;
import com.github.franckyi.ibeeditor.editor.item.ItemEditor;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import java.awt.Color;

@Mod("ibeeditor")
@Mod.EventBusSubscriber
public class IBEEditorMod {

    public static boolean testing = false;
    public static final Logger LOGGER = LogManager.getLogger();
    private static final String KEYBINDING_CATEGORY = "IBE Editor";
    public static final KeyBinding KEY_OPEN_GUI = new KeyBinding("Open GUI", KeyConflictContext.UNIVERSAL, KeyModifier.NONE, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_I, KEYBINDING_CATEGORY);

    public IBEEditorMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent e) {
        testing = false;
        if (e.phase == TickEvent.Phase.END) {
            if (KEY_OPEN_GUI.isPressed()) {
                if (testing) {
                    Scene scene = new Scene();
                    HBox vBox = new HBox();
                    VBox hBox = new VBox();
                    VBox hBox2 = new VBox();
                    Button button = new Button("Cycle align");
                    button.getOnMouseClickedListeners().add(event -> vBox.setAlignment(cycleAlign(vBox.getAlignment())));
                    hBox.getChildren().add(button);
                    hBox.setPrefSize(100, 100);
                    hBox2.setPrefSize(50, 50);
                    vBox.getChildren().add(hBox2);
                    vBox.getChildren().add(hBox);
                    vBox.setPrefSize(200, 200);
                    scene.setContent(vBox);
                    scene.setBackground(Background.DEFAULT);
                    scene.getOnInitGuiListeners().add(event -> LOGGER.info("Hello world!"));
                    scene.show();
                } else {
                    ItemStack heldItem = Minecraft.getInstance().player.getHeldItemMainhand();
                    if (!heldItem.isEmpty()) {
                        openItemEditor(heldItem);
                    }
                }
            }
        }
    }

    private static Pos cycleAlign(Pos alignment) {
        switch (alignment) {
            case TOP_LEFT:
                return Pos.TOP;
            case TOP:
                return Pos.TOP_RIGHT;
            case TOP_RIGHT:
                return Pos.LEFT;
            case LEFT:
                return Pos.CENTER;
            case CENTER:
                return Pos.RIGHT;
            case RIGHT:
                return Pos.BOTTOM_LEFT;
            case BOTTOM_LEFT:
                return Pos.BOTTOM;
            case BOTTOM:
                return Pos.BOTTOM_RIGHT;
            case BOTTOM_RIGHT:
            default:
                return Pos.TOP_LEFT;
        }
    }

    private static void openItemEditor(ItemStack itemStack) {
        ItemEditor editor = new ItemEditor(itemStack);
        editor.open();
    }

    private void setup(FMLClientSetupEvent event) {
        ClientRegistry.registerKeyBinding(KEY_OPEN_GUI);
    }

}

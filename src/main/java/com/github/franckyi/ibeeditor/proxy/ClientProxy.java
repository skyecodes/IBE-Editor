package com.github.franckyi.ibeeditor.proxy;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.Scene;
import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.group.VBox;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.editor.item.ItemEditor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.glfw.GLFW;

public class ClientProxy implements IProxy {

    private static final String KEYBINDING_CATEGORY = "IBE Editor";
    public static final KeyBinding KEY_OPEN_GUI = new KeyBinding("Open GUI", KeyConflictContext.UNIVERSAL, KeyModifier.NONE, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_I, KEYBINDING_CATEGORY);

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

    @Override
    public void setup() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(Node.NodeEventHandler.class);
        MinecraftForge.EVENT_BUS.register(Scene.ScreenEventHandler.class);
        ClientRegistry.registerKeyBinding(KEY_OPEN_GUI);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent e) {
        IBEEditorMod.testing = false;
        if (e.phase == TickEvent.Phase.END) {
            if (KEY_OPEN_GUI.isPressed()) {
                if (IBEEditorMod.testing) {
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
}

package com.github.franckyi.ibeeditor.gui.property;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraftforge.fml.client.config.GuiUtils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class BaseProperty<V> implements GuiListExtended.IGuiListEntry {

    protected static final Minecraft mc = Minecraft.getMinecraft();

    private String name;
    private V value;
    private final Supplier<V> defaultValue;
    private final Consumer<V> apply;

    private final GuiButton undo = new GuiButton(0, 0, 0, 20, 20, GuiUtils.UNDO_CHAR);

    protected int slotTop, slotBottom, slotLeft, slotRight;

    public BaseProperty(String name, Supplier<V> value, Consumer<V> apply) {
        setName(name);
        this.defaultValue = value;
        this.apply = apply;
        reset();
    }

    public BaseProperty(String name, Supplier<V> value) {
        this(name, value, (v) -> {});
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void place0(int slotTop, int slotBottom, int slotLeft, int slotRight) {
        this.slotTop = slotTop + 5;
        this.slotBottom = slotBottom;
        this.slotLeft = slotLeft;
        this.slotRight = slotRight;
        place();
    }

    public void apply() {
        apply.accept(value);
    }

    protected void init() {
    }

    public void reset() {
        setValue(defaultValue.get());
    }

    protected void place() {
        undo.x = slotRight - 30;
        undo.y = slotTop;
    }

    public void keyTyped(char typedChar, int keyCode) {
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if(undo.mousePressed(mc, mouseX, mouseY)) {
            reset();
            init();
        }
    }

    public void updateScreen() {
    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks) {
        undo.drawButton(mc, mouseX, mouseY, partialTicks);
    }

    @Override
    public void updatePosition(int slotIndex, int x, int y, float partialTicks) {
    }

    @Override
    public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
        return true;
    }

    @Override
    public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
    }
}

package com.github.franckyi.guapi.event;

import com.github.franckyi.guapi.EventTarget;
import com.github.franckyi.guapi.node.Node;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

public class ScreenEventType<E extends ScreenEvent> {
    public static final ScreenEventType<MouseButtonEvent> MOUSE_CLICKED = new ScreenEventType<>("MOUSE_CLICKED", true, EventTarget::mouseClicked);
    public static final ScreenEventType<MouseButtonEvent> MOUSE_RELEASED = new ScreenEventType<>("MOUSE_RELEASED", true, EventTarget::mouseReleased);
    public static final ScreenEventType<MouseDragEvent> MOUSE_DRAGGED = new ScreenEventType<>("MOUSE_DRAGGED", true, EventTarget::mouseDragged);
    public static final ScreenEventType<MouseScrollEvent> MOUSE_SCOLLED = new ScreenEventType<>("MOUSE_SCOLLED", true, EventTarget::mouseScrolled);
    public static final ScreenEventType<KeyEvent> KEY_PRESSED = new ScreenEventType<>("KEY_PRESSED", false, EventTarget::keyPressed);
    public static final ScreenEventType<KeyEvent> KEY_RELEASED = new ScreenEventType<>("KEY_RELEASED", false, EventTarget::keyReleased);
    public static final ScreenEventType<TypeEvent> CHAR_TYPED = new ScreenEventType<>("CHAR_TYPED", false, EventTarget::charTyped);
    public static final ScreenEventType<MouseEvent> MOUSE_MOVED = new ScreenEventType<>("MOUSE_MOVED", true, EventTarget::mouseMoved);
    public static final List<ScreenEventType<?>> VALUES = Arrays.asList(MOUSE_CLICKED, MOUSE_RELEASED,
            MOUSE_DRAGGED, MOUSE_SCOLLED, KEY_PRESSED, KEY_RELEASED, CHAR_TYPED, MOUSE_MOVED);
    private final String name;
    private final boolean mouseEvent;
    private final BiConsumer<EventTarget, E> onEvent;

    private ScreenEventType(String name, boolean mouseEvent, BiConsumer<EventTarget, E> onEvent) {
        this.name = name;
        this.mouseEvent = mouseEvent;
        this.onEvent = onEvent;
    }

    public String getName() {
        return name;
    }

    public boolean isMouseEvent() {
        return mouseEvent;
    }

    @SuppressWarnings("unchecked")
    public <EE extends MouseEvent> void ifMouseEvent(E event, BiConsumer<ScreenEventType<EE>, EE> thenDo, Runnable elseDo) {
        if (mouseEvent) {
            thenDo.accept((ScreenEventType<EE>) this, (EE) event);
        } else {
            elseDo.run();
        }
    }

    public void onEvent(EventTarget node, E event) {
        onEvent.accept(node, event);
    }

    @Override
    public String toString() {
        return "ScreenEventType{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScreenEventType<?> that = (ScreenEventType<?>) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

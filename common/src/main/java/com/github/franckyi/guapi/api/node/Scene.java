package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableObjectValue;
import com.github.franckyi.guapi.api.Renderable;
import com.github.franckyi.guapi.util.Insets;

import java.util.function.Consumer;

public interface Scene extends ScreenEventHandler, Parent, Renderable {
    default Node getRoot() {
        return rootProperty().getValue();
    }

    ObjectProperty<Node> rootProperty();

    default void setRoot(Node value) {
        rootProperty().setValue(value);
    }

    default boolean isFullScreen() {
        return fullScreenProperty().getValue();
    }

    BooleanProperty fullScreenProperty();

    default void setFullScreen(boolean value) {
        fullScreenProperty().setValue(value);
    }

    default int getWidth() {
        return widthProperty().getValue();
    }

    IntegerProperty widthProperty();

    default int getHeight() {
        return heightProperty().getValue();
    }

    IntegerProperty heightProperty();

    default Insets getPadding() {
        return paddingProperty().getValue();
    }

    ObjectProperty<Insets> paddingProperty();

    default void setPadding(Insets value) {
        paddingProperty().setValue(value);
    }

    default boolean isTexturedBackground() {
        return texturedBackgroundProperty().getValue();
    }

    BooleanProperty texturedBackgroundProperty();

    default void setTexturedBackground(boolean value) {
        texturedBackgroundProperty().setValue(value);
    }

    default boolean isCloseOnEsc() {
        return closeOnEscProperty().getValue();
    }

    BooleanProperty closeOnEscProperty();

    default void setCloseOnEsc(boolean value) {
        closeOnEscProperty().setValue(value);
    }

    default Node getFocused() {
        return focusedProperty().getValue();
    }

    ObservableObjectValue<Node> focusedProperty();

    default Node getHovered() {
        return hoveredProperty().getValue();
    }

    ObservableObjectValue<Node> hoveredProperty();

    void tick();

    void show();

    void onShow(Consumer<Scene> listener);

    void hide();

    void onHide(Consumer<Scene> listener);
}

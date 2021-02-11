package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableObjectValue;
import com.github.franckyi.guapi.api.Renderable;
import com.github.franckyi.guapi.api.event.ScreenEventHandler;
import com.github.franckyi.guapi.util.Insets;

public interface Scene extends ScreenEventHandler, Parent, Renderable {
    Node getRoot();

    ObjectProperty<Node> rootProperty();

    void setRoot(Node value);

    boolean isFullScreen();

    BooleanProperty fullScreenProperty();

    void setFullScreen(boolean value);

    IntegerProperty widthProperty();

    IntegerProperty heightProperty();

    ObjectProperty<Insets> paddingProperty();

    void setPadding(Insets value);

    boolean isTexturedBackground();

    BooleanProperty texturedBackgroundProperty();

    void setTexturedBackground(boolean value);

    boolean isCloseOnEsc();

    BooleanProperty closeOnEscProperty();

    void setCloseOnEsc(boolean value);

    Node getFocused();

    ObservableObjectValue<Node> focusedProperty();

    Node getHovered();

    ObservableObjectValue<Node> hoveredProperty();

    void tick();

    void show();

    void hide();
}

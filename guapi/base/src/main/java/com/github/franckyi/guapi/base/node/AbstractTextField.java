package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.*;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.node.TextField;

import java.util.function.Predicate;

import static com.github.franckyi.guapi.GuapiHelper.*;

public abstract class AbstractTextField extends AbstractLabeled implements TextField {
    private final StringProperty textProperty = DataBindings.getPropertyFactory().createStringProperty("");
    private final IntegerProperty maxLengthProperty = DataBindings.getPropertyFactory().createIntegerProperty(Integer.MAX_VALUE);
    private final ObjectProperty<Predicate<String>> validatorProperty = DataBindings.getPropertyFactory().createObjectProperty(s -> true);
    private final BooleanProperty validationForcedProperty = DataBindings.getPropertyFactory().createBooleanProperty();
    private final BooleanProperty validProperty = DataBindings.getPropertyFactory().createBooleanProperty();
    private final ObservableBooleanValue validPropertyReadOnly = DataBindings.getPropertyFactory().createReadOnlyProperty(validProperty);
    private final ObjectProperty<TextRenderer> textRendererProperty = DataBindings.getPropertyFactory().createObjectProperty();
    private final IntegerProperty cursorPositionProperty = DataBindings.getPropertyFactory().createIntegerProperty();
    private final IntegerProperty highlightPositionProperty = DataBindings.getPropertyFactory().createIntegerProperty();
    private TextFieldEventListener onTextUpdate;
    private final ObservableList<String> suggestions = DataBindings.getObservableListFactory().createObservableArrayList();
    private final BooleanProperty suggestedProperty = DataBindings.getPropertyFactory().createBooleanProperty();
    private final ObservableBooleanValue suggestedPropertyReadOnly = DataBindings.getPropertyFactory().createReadOnlyProperty(suggestedProperty);

    protected AbstractTextField() {
        this("");
    }

    protected AbstractTextField(String value) {
        this(emptyText(), value);
    }

    protected AbstractTextField(String label, String value) {
        this(text(label), value);
    }

    protected AbstractTextField(Text label, String value) {
        super(label);
        setText(value);
        textProperty().addListener(this::updateValid);
        validatorProperty().addListener(this::updateValid);
        textProperty().addListener(this::updateSuggested);
        getSuggestions().addListener(this::updateSuggested);
    }

    @Override
    public StringProperty textProperty() {
        return textProperty;
    }

    @Override
    public IntegerProperty maxLengthProperty() {
        return maxLengthProperty;
    }

    @Override
    public ObjectProperty<Predicate<String>> validatorProperty() {
        return validatorProperty;
    }

    @Override
    public BooleanProperty validationForcedProperty() {
        return validationForcedProperty;
    }

    @Override
    public ObservableBooleanValue validProperty() {
        return validPropertyReadOnly;
    }

    @Override
    public ObjectProperty<TextRenderer> textRendererProperty() {
        return textRendererProperty;
    }

    @Override
    public IntegerProperty cursorPositionProperty() {
        return cursorPositionProperty;
    }

    @Override
    public IntegerProperty highlightPositionProperty() {
        return highlightPositionProperty;
    }

    @Override
    public void onTextUpdate(int oldCursorPos, int oldHighlightPos, String oldText, int newCursorPos, String newText) {
        if (onTextUpdate != null) {
            onTextUpdate.handle(oldCursorPos, oldHighlightPos, oldText, newCursorPos, newText);
        }
    }

    @Override
    public void setOnTextUpdate(TextFieldEventListener listener) {
        onTextUpdate = listener;
    }

    @Override
    public ObservableList<String> getSuggestions() {
        return suggestions;
    }

    @Override
    public ObservableBooleanValue suggestedProperty() {
        return suggestedPropertyReadOnly;
    }

    private void updateValid() {
        validProperty.setValue(getValidator().test(getText()));
    }

    private void updateSuggested() {
        suggestedProperty.setValue(getSuggestions().contains(getText()));
    }
}

package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObservableBooleanValue;
import com.github.franckyi.databindings.api.ObservableIntegerValue;
import com.github.franckyi.gamehooks.api.client.ScreenScaling;

public abstract class AbstractScreenScaling implements ScreenScaling {
    protected final IntegerProperty scaleProperty = PropertyFactory.ofInteger();
    private final ObservableIntegerValue scalePropertyReadOnly = PropertyFactory.readOnly(scaleProperty);
    protected final BooleanProperty canScaleBeResetProperty = PropertyFactory.ofBoolean();
    private final ObservableBooleanValue canScaleBeResetPropertyReadOnly = PropertyFactory.readOnly(canScaleBeResetProperty);

    protected AbstractScreenScaling() {
        scaleProperty().addListener(this::setScreenScale);
    }

    @Override
    public void setBaseScale(int value) {
        if (value != -1) {
            setScale(value);
        } else {
            restoreScale();
        }
    }

    @Override
    public int getScale() {
        return scaleProperty().getValue();
    }

    @Override
    public ObservableIntegerValue scaleProperty() {
        return scalePropertyReadOnly;
    }

    protected void setScale(int scale) {
        scaleProperty.setValue(scale);
        canScaleBeResetProperty.setValue(true);
    }

    @Override
    public ObservableBooleanValue canScaleBeResetProperty() {
        return canScaleBeResetPropertyReadOnly;
    }

    @Override
    public int getScaleAndReset() {
        int res = canScaleBeResetProperty.getValue() ? getScale() : -1;
        setScale(-1);
        resetScale();
        return res;
    }

    @Override
    public void restoreScale() {
        setScale(getDefaultScale());
        canScaleBeResetProperty.setValue(false);
    }

    @Override
    public void scaleUp() {
        if (getScale() >= getMaxScale()) {
            setScale(0);
        } else {
            setScale(getScale() + 1);
        }
    }

    @Override
    public void scaleDown() {
        if (getScale() == 0) {
            setScale(getMaxScale());
        } else {
            setScale(getScale() - 1);
        }
    }

    @Override
    public boolean canScaleUp() {
        return getScale() != 0;
    }

    @Override
    public boolean canScaleDown() {
        return getScale() != 1;
    }

    @Override
    public void refresh() {
        setScreenScale(getScale());
    }

    protected abstract void resetScale();

    protected abstract void setScreenScale(int value);

    protected abstract int getDefaultScale();

    protected abstract int getMaxScale();
}

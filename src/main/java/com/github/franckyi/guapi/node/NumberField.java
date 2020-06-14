package com.github.franckyi.guapi.node;

public abstract class NumberField<T extends Number & Comparable<T>> extends TextFieldBase<T> {

    private T min, max;

    protected NumberField(T value, T min, T max) {
        this.min = min;
        this.max = max;
        this.getView().setValidator(s -> {
            if (s.isEmpty() || s.equals("-")) return true;
            try {
                T t = this.fromString(s);
                return t.compareTo(this.min) >= 0 && t.compareTo(this.max) <= 0;
            } catch (NumberFormatException e) {
                return false;
            }
        });
        this.setValue(value);
    }

    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        this.min = min;
        if (this.getValue().compareTo(min) < 0) {
            this.setValue(min);
        }
    }

    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        this.max = max;
        if (this.getValue().compareTo(max) > 0) {
            this.setValue(max);
        }
    }

    @Override
    public T getValue() {
        String s = this.getText();
        if (s.isEmpty() || s.equals("-")) {
            return this.getMin();
        }
        try {
            return this.fromString(s);
        } catch (NumberFormatException e) {
            return this.getMin();
        }
    }

    @Override
    public void setValue(T value) {
        this.setText(this.toString(value));
    }

    protected abstract T fromString(String s) throws NumberFormatException;

    protected abstract String toString(T value);
}

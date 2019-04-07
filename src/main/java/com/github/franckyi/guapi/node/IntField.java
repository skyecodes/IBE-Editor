package com.github.franckyi.guapi.node;

public class IntField extends TextField {

    private int min;
    private int max;

    public IntField() {
        this(0);
    }

    public IntField(int initialValue) {
        super(new GuiTextFieldView(), Integer.toString(initialValue));
        this.min = Integer.MIN_VALUE;
        this.max = Integer.MAX_VALUE;
        this.getView().setValidator(s -> {
            if (s.isEmpty()) return true;
            try {
                int i = Integer.parseInt(s);
                return i >= min && i < max;
            } catch (NumberFormatException e) {
                return false;
            }
        });
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getValue() {
        return this.getText().isEmpty() ? 0 : Integer.parseInt(this.getText());
    }

    public void setValue(int value) {
        this.setText(Integer.toString(value));
    }
}

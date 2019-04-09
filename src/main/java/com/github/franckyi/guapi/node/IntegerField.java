package com.github.franckyi.guapi.node;

public class IntegerField extends TextField {

    private int min;
    private int max;

    public IntegerField() {
        this(0);
    }

    public IntegerField(int value) {
        this(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public IntegerField(int value, int min, int max) {
        super(new GuiTextFieldView(), Integer.toString(value));
        this.min = min;
        this.max = max;
        this.getView().setValidator(s -> {
            if (s.isEmpty() || s.equals("-")) return true;
            try {
                int i = Integer.parseInt(s);
                return i >= this.min && i < this.max;
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
        if (this.getText().isEmpty() || this.getText().equals("-")) return min;
        try {
            return Integer.parseInt(this.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void setValue(int value) {
        this.setText(Integer.toString(value));
    }
}

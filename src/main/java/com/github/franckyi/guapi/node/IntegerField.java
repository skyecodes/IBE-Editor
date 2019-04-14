package com.github.franckyi.guapi.node;

public class IntegerField extends TextFieldBase<Integer> {

    private int min;
    private int max;

    public IntegerField() {
        this(0);
    }

    public IntegerField(int value) {
        this(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public IntegerField(int value, int min, int max) {
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
        this.setText(Integer.toString(value));
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

    @Override
    public Integer getValue() {
        String s = this.getText();
        if (s.isEmpty() || s.equals("-")) return min;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return min;
        }
    }

    @Override
    public void setValue(Integer value) {
        this.setText(Integer.toString(value));
    }

}

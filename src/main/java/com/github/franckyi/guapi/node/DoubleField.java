package com.github.franckyi.guapi.node;

public class DoubleField extends TextField {

    private double min;
    private double max;

    public DoubleField() {
        this(0);
    }

    public DoubleField(double value) {
        this(value, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public DoubleField(double value, double min, double max) {
        super(new GuiTextFieldView(), Double.toString(value));
        this.min = min;
        this.max = max;
        this.getView().setValidator(s -> {
            if (s.isEmpty() || s.equals("-") || s.endsWith(".")) return true;
            try {
                double d = Double.parseDouble(s);
                return d >= min && d < max;
            } catch (NumberFormatException e) {
                return false;
            }
        });
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getValue() {
        if (this.getText().isEmpty() || this.getText().equals("-") || this.getText().endsWith(".")) return 0;
        try {
            return Double.parseDouble(this.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void setValue(double value) {
        this.setText(Double.toString(value));
    }
}

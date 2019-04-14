package com.github.franckyi.guapi.node;

public class DoubleField extends TextFieldBase<Double> {

    private double min;
    private double max;

    public DoubleField() {
        this(0);
    }

    public DoubleField(double value) {
        this(value, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public DoubleField(double value, double min, double max) {
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
        this.setText(Double.toString(value));
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

    @Override
    public Double getValue() {
        String s = this.getText();
        if (s.isEmpty() || s.equals("-") || s.endsWith(".")) return min;
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return min;
        }
    }

    @Override
    public void setValue(Double value) {
        this.setText(Double.toString(value));
    }

}

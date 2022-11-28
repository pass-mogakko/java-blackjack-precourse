package model.dto;

public class Earning {

    private final String name;
    private double value;

    public Earning(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public void updateEarning(double amount) {
        this.value += amount;
    }
}

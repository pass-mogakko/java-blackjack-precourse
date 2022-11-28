package model;

public class Earning {

    private final String name;
    private double earning;

    public Earning(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getEarning() {
        return earning;
    }

    public void updateEarning(double amount) {
        this.earning += amount;
    }

    @Override
    public String toString() {
        return name + "= " + earning;
    }
}

package com.mycompany.cadastrodecarros;

public class Caminhão extends Veículo {

    private double maximumLoad;

    public Caminhão(int maximumSpeed, double price, double maximumLoad) {
        super(maximumSpeed, price, 1.5);
        this.maximumLoad = maximumLoad;
    }

    public double calculateIpva() {
        return this.getIpvaTax() * this.getPrice();
    }

    public double getMaximumLoad() {
        return this.maximumLoad;
    }
}

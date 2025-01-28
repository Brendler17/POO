package com.mycompany.cadastrodecarros;

public abstract class Veículo {

    private float maximumSpeed;
    private double price;
    private double ipvaTax;

    public Veículo(float maximumSpeed, double price, double ipvaTax) {
        this.maximumSpeed = maximumSpeed;
        this.price = price;
        this.ipvaTax = ipvaTax;
    }

    abstract double calculateIpva();

    public float getMaximumSpeed() {
        return this.maximumSpeed;
    }

    public double getPrice() {
        return this.price;
    }

    public double getIpvaTax() {
        return this.ipvaTax;
    }

}

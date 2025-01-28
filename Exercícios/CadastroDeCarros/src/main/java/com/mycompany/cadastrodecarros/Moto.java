package com.mycompany.cadastrodecarros;

public class Moto extends Veículo {

    private int cylinderCapacity;

    public Moto(int maximumSpeed, double price, int cylinderCapacity) {
        super(maximumSpeed, price, 2.0);
        this.cylinderCapacity = cylinderCapacity;
    }

    public double calculateIpva() {
        return this.getIpvaTax() * this.getPrice();
    }

    public int getCylinderCapacity() {
        return this.cylinderCapacity;
    }
}

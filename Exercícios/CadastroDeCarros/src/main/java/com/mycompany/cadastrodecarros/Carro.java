package com.mycompany.cadastrodecarros;

public class Carro extends Veículo {

    private String color;

    public Carro(int maximumSpeed, double price, String color) {
        super(maximumSpeed, price, 2.5);
        this.color = color;
    }

    public double calculateIpva() {
        return this.getIpvaTax() * this.getPrice();
    }

    public String getColor() {
        return this.color;
    }

}

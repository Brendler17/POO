package com.mycompany.mypets;

public class Cat extends Animal {

    public Cat(String name, String breed) {
        super(name, breed);
    }

    public String act() {
        return "Miau-Miau";
    }
}

package com.mycompany.mypets;

public class Dog extends Animal {

    public Dog(String name, String breed) {
        super(name, breed);
    }

    public String act() {
        return "Au-Au";
    }
}

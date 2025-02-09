package com.mycompany.mypets;

public class MyPets {

    private Animal[] animals;

    public MyPets(int size) {
        animals = new Animal[size];
    }

    public static void main(String[] args) {
        MyPets register = new MyPets(4);

        register.animals[0] = new Dog("Bob", "Labrador");
        register.animals[1] = new Cat("Nico", "Persa");
        register.animals[2] = new Dog("Caramelo", "Vira-Lata");
        register.animals[3] = new Cat("Pereba", "Siamês");

        for (int counter = 0; counter < register.animals.length; counter++) {
            Animal animal = register.animals[counter];

            System.out.printf("Name: %s - %s\n", animal.getName(), animal.act());
        }
    }
}

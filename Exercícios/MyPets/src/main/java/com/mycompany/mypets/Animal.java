package com.mycompany.mypets;

public abstract class Animal {

    private String name;
    private String breed;

    public Animal(String name, String breed) {
        if (name.isEmpty() || breed.isEmpty()) {
            throw new IllegalArgumentException("\nError! Empty parameter!\n");
        }

        this.name = name;
        this.breed = breed;
    }

    abstract String act();

    public void setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("\nError! Empty parameter!\n");
        }

        this.name = name;
    }

    public void setBreed(String breed) {
        if (breed.isEmpty()) {
            throw new IllegalArgumentException("\nError! Empty parameter!\n");
        }

        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

}

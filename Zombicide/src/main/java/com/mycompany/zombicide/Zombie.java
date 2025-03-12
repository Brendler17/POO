package com.mycompany.zombicide;

public abstract class Zombie {

    private int health;
    private int[] position;

    public Zombie(int health, int positionX, int positionY) {
        this.health = health;
        position = new int[2];
        position[0] = positionX;
        position[1] = positionY;
    }

    public abstract void Move(int[] position);
}

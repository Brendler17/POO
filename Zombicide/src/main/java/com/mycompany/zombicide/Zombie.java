package com.mycompany.zombicide;

public abstract class Zombie {

    private int health;
    private int[] position;

    public Zombie(int health, int positionX, int positionY) {
        this.health = health;
        this.position = new int[]{positionX, positionY};
    }

    public int[] getPosition() {
        return position;
    }

    public abstract void Move(int newX, int newY);
}

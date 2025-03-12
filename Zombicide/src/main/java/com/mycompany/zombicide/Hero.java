package com.mycompany.zombicide;

public class Hero {

    private int health;
    private int perception;
    private int[] position;

    public Hero(int positionX, int positionY, int playerPerception) {
        this.health = 5;
        this.perception = playerPerception;
        this.position = new int[]{positionX, positionY};
    }

    public int[] getPosition() {
        return position;
    }

    public void Move(int newX, int newY) {
    }

    public void Combat(Zombie zombie) {
    }
}

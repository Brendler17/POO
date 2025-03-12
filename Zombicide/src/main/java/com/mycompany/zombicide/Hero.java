package com.mycompany.zombicide;

public class Hero {

    private int health;
    private int perception;
    private int[] position;

    public Hero(int positionX, int positionY, int playerPerception) {
        health = 5;
        perception = playerPerception;
    }

    public void Move(int newX, int newY) {
    }

    public void Combat(Zombie zombie) {
    }
}

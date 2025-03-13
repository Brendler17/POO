package com.mycompany.zombicide;

public class Hero {

    private int health;
    private int perception;
    private int[] position;
    private String currentWeapon;

    public Hero(int positionX, int positionY, int playerPerception) {
        this.health = 5;
        this.perception = playerPerception;
        this.position = new int[]{positionX, positionY};
        this.currentWeapon = "hands";
    }

    public void Move(int newX, int newY) {
    }

    public void Combat(Zombie zombie) {
    }

    public int getHealth() {
        return health;
    }

    public int getPerception() {
        return perception;
    }

    public int[] getPosition() {
        return position;
    }

    public String getCurrentWeapon() {
        return currentWeapon;
    }
}

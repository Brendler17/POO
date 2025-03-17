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

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int newX, int newY) {
        position = new int[]{newX, newY};
    }

    public String getCurrentWeapon() {
        return currentWeapon;
    }
}

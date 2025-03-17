package com.mycompany.zombicide;

public class Chest {

    private int[] position;

    public Chest(int positionX, int positionY) {
        this.position = new int[]{positionX, positionY};
    }

    public int[] getPosition() {
        return position;
    }
}

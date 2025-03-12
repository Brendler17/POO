package com.mycompany.zombicide;

public class Chest {

    int[] position;

    public Chest(int positionX, int positionY) {
        position = new int[2];
        position[0] = positionX;
        position[1] = positionY;
    }
}

package com.mycompany.zombicide;

public class Chest {

    private int[] position;
    private String content;

    public Chest(int positionX, int positionY) {
        this.position = new int[]{positionX, positionY};
        this.content = "";
    }

    public int[] getPosition() {
        return position;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

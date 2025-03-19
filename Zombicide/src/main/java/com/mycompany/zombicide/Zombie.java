package com.mycompany.zombicide;

public abstract class Zombie {

    private int health;
    private int[] position;

    public Zombie(int health, int positionX, int positionY) {
        this.health = health;
        this.position = new int[]{positionX, positionY};
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public int[] getPosition() {
        return position;
    }

    public void setPostion(int newX, int newY) {
        this.position = new int[]{newX, newY};
    }

    public void moveZombie(int[] playerPosition, char[][] mapData) {
        int[] currentPosition = getPosition();
        int x = currentPosition[0];
        int y = currentPosition[1];

        int moveX = Integer.compare(playerPosition[0], x);
        int moveY = Integer.compare(playerPosition[1], y);

        if (moveX != 0 && isValidMove(x + moveX, y, mapData)) {
            setPostion(x + moveX, y);
            return;
        }

        if (moveY != 0 && isValidMove(x, y + moveY, mapData)) {
            setPostion(x, y + moveY);
        }
    }

    public boolean isValidMove(int newX, int newY, char[][] mapData) {
        if (newX < 0 || newX >= mapData.length || newY < 0 || newY >= mapData[0].length) {
            return false;
        }
        return mapData[newX][newY] != '#' && mapData[newX][newY] != 'Z'
                && mapData[newX][newY] != 'R' && mapData[newX][newY] != 'C'
                && mapData[newX][newY] != 'G' && mapData[newX][newY] != 'B';
    }

    public String getZombieType(Zombie zombie) {
        if (zombie instanceof ZombieCommon) {
            return "Zumbi Comum";
        }
        if (zombie instanceof ZombieCreeping) {
            return "Zumbi Rastejante";
        }
        if (zombie instanceof ZombieRunner) {
            return "Zumbi Corredor";
        }
        if (zombie instanceof ZombieGiant) {
            return "Zumbi Gigante";
        }
        return "Desconhecido";
    }
}

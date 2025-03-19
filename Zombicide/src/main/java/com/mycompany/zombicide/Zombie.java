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

    public abstract void moveZombie(int newX, int newY);

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

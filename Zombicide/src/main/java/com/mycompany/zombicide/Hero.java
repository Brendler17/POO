package com.mycompany.zombicide;

public class Hero {

    private int health;
    private int perception;
    private int[] position;
    private String[] currentWeapon;
    private int ammo;

    public Hero(int positionX, int positionY, int playerPerception) {
        this.health = 5;
        this.perception = playerPerception;
        this.position = new int[]{positionX, positionY};
        this.currentWeapon = new String[]{"hands"};
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

    public int getPerception() {
        return perception;
    }

    public void setPosition(int newX, int newY) {
        position = new int[]{newX, newY};
    }

    public boolean hasBaseballBat() {
        return currentWeapon[0].equals("baseball_bat");
    }

    public boolean hasGun() {
        return currentWeapon[1].equals("gun");
    }

    public void setCurrentWeapon(String weapon) {
        if (weapon.equals("baseball_bat")) {
            currentWeapon[0] = weapon;
        } else {
            currentWeapon[1] = weapon;
        }
    }

    public boolean hasAmmo() {
        return ammo > 0;
    }

    public void addAmmo() {
        ammo++;
    }

    public void useAmmo() {
        if (ammo > 0) {
            ammo--;
        }
    }

    public int attack() {
        if ("gun".equals(currentWeapon[1]) && ammo > 0) {
            ammo--;
            return 2;
        } else if ("baseball_bat".equals(currentWeapon[0])) {
            int diceRoll = (int) (Math.random() * 6) + 1;
            return diceRoll == 6 ? 2 : 1;
        } else {
            int diceRoll = (int) (Math.random() * 6) + 1;
            return diceRoll == 6 ? 2 : 1;
        }
    }
}

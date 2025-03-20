package com.mycompany.zombicide;

public class Hero {

    private int health;
    private int perception;
    private int[] position;
    private String[] currentWeapon;
    private int ammo;
    private int bandage;

    public Hero(int positionX, int positionY, int playerPerception) {
        this.health = 5;
        this.perception = playerPerception;
        this.position = new int[]{positionX, positionY};
        this.currentWeapon = new String[]{"hands", "none"};
        this.ammo = 0;
        this.bandage = 0;
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
        return "baseball_bat".equals(currentWeapon[0]);
    }

    public boolean hasGun() {
        return currentWeapon.length > 1 && "gun".equals(currentWeapon[1]);
    }

    public void setCurrentWeapon(String weapon) {
        if (weapon.equals("baseball_bat")) {
            currentWeapon[0] = weapon;
        } else if (weapon.equals("gun")) {
            if (currentWeapon.length < 2) {
                String[] newWeapons = new String[2];
                newWeapons[0] = currentWeapon[0];
                newWeapons[1] = weapon;
                currentWeapon = newWeapons;
            } else {
                currentWeapon[1] = weapon;
            }
        }
    }

    public String[] getCurrentWeapon() {
        return currentWeapon;
    }

    public String[] getWeaponName() {
        String[] weaponsName = new String[2];

        switch (currentWeapon[0]) {
            case "hands":
                weaponsName[0] = "Mãos";
                break;
            case "baseball_bat":
                weaponsName[0] = "Taco";
                break;
            default:
                weaponsName[0] = "";
                break;
        }

        if (currentWeapon.length > 1) {
            switch (currentWeapon[1]) {
                case "gun":
                    weaponsName[1] = "Arma";
                    break;
                case "none":
                    weaponsName[1] = "";
                    break;
                default:
                    weaponsName[1] = "";
                    break;
            }
        } else {
            weaponsName[1] = "";
        }

        return weaponsName;

    }

    public boolean hasAmmo() {
        return ammo > 0;
    }

    public int getAmmo() {
        return ammo;
    }

    public void addAmmo() {
        ammo++;
    }

    public void useAmmo() {
        if (ammo > 0) {
            ammo--;
        }
    }

    public boolean hasBandage() {
        return bandage > 0;
    }

    public int getBandage() {
        return bandage;
    }

    public void addBandage() {
        bandage++;
    }

    public void useBandage() {
        if (bandage > 0) {
            bandage--;
            health = Math.min(health + 1, 5);
        }
    }

    public int gunAttack() {
        useAmmo();
        return 2;
    }

    public int attack() {
        int diceRoll = (int) (Math.random() * 6) + 1;
        return diceRoll == 6 ? 2 : 1;
    }
}

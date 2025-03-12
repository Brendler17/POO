package com.mycompany.zombicide;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private char[][] mapData;
    private Hero player;
    private List<Zombie> zombies;
    private List<Chest> chests;

    public GameManager(int playerPerception) {
        this.mapData = MapManager.loadRandomMap();
        this.zombies = new ArrayList<>();
        this.chests = new ArrayList<>();
        iniatilizeEntities(playerPerception);
    }

    private void iniatilizeEntities(int playerPerception) {
        for (int i = 0; i < mapData.length; i++) {
            for (int j = 0; j < mapData[i].length; j++) {
                switch (mapData[i][j]) {
                    case 'P':
                        player = new Hero(i, j, playerPerception);
                        break;
                    case 'R':
                        zombies.add(new ZombieCreeping(i, j));
                        break;
                    case 'Z':
                        zombies.add(new ZombieCommon(i, j));
                        break;
                    case 'C':
                        zombies.add(new ZombieRunner(i, j));
                        break;
                    case 'G':
                        zombies.add(new ZombieGiant(i, j));
                        break;
                    case 'B':
                        chests.add(new Chest(i, j));
                        break;
                }
            }
        }
    }

    public Hero getPlayer() {
        return player;
    }

    public List<Zombie> getZombies() {
        return zombies;
    }

    public List<Chest> getChests() {
        return chests;
    }

    public char[][] getMapData() {
        return mapData;
    }
}

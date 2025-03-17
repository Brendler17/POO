package com.mycompany.zombicide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {

    private char[][] mapData;
    private Hero player;
    private List<Zombie> zombies;
    private Map<int[], Zombie> zombieMap;
    private List<Chest> chests;
    private GameUI gameUI;

    public GameManager(int playerPerception, GameUI gameUI) {
        this.mapData = MapManager.loadRandomMap();
        this.zombies = new ArrayList<>();
        this.zombieMap = new HashMap<>();
        this.chests = new ArrayList<>();
        this.gameUI = gameUI;
        iniatilizeEntities(playerPerception);
    }

    private void iniatilizeEntities(int playerPerception) {
        Zombie newZombie;
        for (int i = 0; i < mapData.length; i++) {
            for (int j = 0; j < mapData[i].length; j++) {
                switch (mapData[i][j]) {
                    case 'P':
                        player = new Hero(i, j, playerPerception);
                        break;
                    case 'R':
                        newZombie = new ZombieCreeping(i, j);
                        zombies.add(newZombie);
                        zombieMap.put(new int[]{i, j}, newZombie);
                        break;
                    case 'Z':
                        newZombie = new ZombieCommon(i, j);
                        zombies.add(newZombie);
                        zombieMap.put(new int[]{i, j}, newZombie);
                        break;
                    case 'C':
                        newZombie = new ZombieRunner(i, j);
                        zombies.add(newZombie);
                        zombieMap.put(new int[]{i, j}, newZombie);
                        break;
                    case 'G':
                        newZombie = new ZombieGiant(i, j);
                        zombies.add(newZombie);
                        zombieMap.put(new int[]{i, j}, newZombie);
                        break;
                    case 'B':
                        chests.add(new Chest(i, j));
                        break;
                }
            }
        }
    }

    public boolean isValidMove(int newX, int newY) {
        if (newX < 0 || newX >= mapData.length || newY < 0 || newY >= mapData[0].length) {
            return false;
        }
        if (mapData[newX][newY] == '#') {
            return false;
        }
        return true;
    }

    public void movePlayer(int newX, int newY) {
        if (isValidMove(newX, newY)) {
            int[] oldPosition = player.getPosition();
            mapData[oldPosition[0]][oldPosition[1]] = '.';
            player.setPosition(newX, newY);
            mapData[newX][newY] = 'P';
            gameUI.updateUI();
            gameUI.createMovementButtons();
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

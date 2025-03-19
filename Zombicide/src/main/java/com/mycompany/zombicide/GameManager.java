package com.mycompany.zombicide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        distributeChestContents();
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

    private void distributeChestContents() {
        if (chests.size() != 4) {
            System.err.println("Erro: O mapa deve conter exatamente 4 baús!");
            return;
        }

        String[] contents = {"bandage", "baseball_bat", "revolver_zombie", "revolver_zombie"};

        List<String> contentList = Arrays.asList(contents);
        Collections.shuffle(contentList);

        for (int i = 0; i < chests.size(); i++) {
            chests.get(i).setContent(contentList.get(i));
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

            for (Zombie zombie : zombies) {
                if (Arrays.equals(zombie.getPosition(), new int[]{newX, newY})) {
                    gameUI.initiateCombat(zombie);
                    return;
                }
            }

            for (Chest chest : chests) {
                if (Arrays.equals(chest.getPosition(), new int[]{newX, newY})) {
                    gameUI.openChest(new int[]{newX, newY});
                    return;
                }
            }

            player.setPosition(newX, newY);
            mapData[newX][newY] = 'P';

            moveAllZombies();
            gameUI.updateUI();
            gameUI.createMovementButtons();

            checkGameOver();
        }
    }

    public boolean tryToScape() {
        int[] playerPosition = player.getPosition();
        int x = playerPosition[0];
        int y = playerPosition[1];

        int[][] directions = {{x - 1, y}, {x + 1, y}, {x, y - 1}, {x, y + 1}};

        for (int[] direction : directions) {
            int newX = direction[0];
            int newY = direction[1];

            if (isValidMove(newX, newY) && !hasZombie(newX, newY)) {
                player.setPosition(newX, newY);
                mapData[x][y] = '.';
                mapData[newX][newY] = 'P';
                gameUI.updateUI();
                gameUI.createMovementButtons();
                return true;
            }
        }

        return false;
    }

    public void moveAllZombies() {
        int[] playerPosition = player.getPosition();
        List<Zombie> zombiesToRemove = new ArrayList<>();
        List<int[]> newPositions = new ArrayList<>();

        for (Zombie zombie : zombies) {
            if (zombie instanceof ZombieGiant) {
                continue;   // Gigante não se move
            }

            int[] oldPosition = zombie.getPosition();
            mapData[oldPosition[0]][oldPosition[1]] = '.';

            if (zombie instanceof ZombieRunner) {
                // Corredor se move 2x
                ((ZombieRunner) zombie).moveZombie(playerPosition, mapData);
                ((ZombieRunner) zombie).moveZombie(playerPosition, mapData);
            } else {
                zombie.moveZombie(playerPosition, mapData);
            }

            int[] newPosition = zombie.getPosition();

            // Zumbi encontrar jogador inicia combate
            if (Arrays.equals(newPosition, playerPosition)) {
                gameUI.initiateCombat(zombie);
                return;
            }

            // Colisão com outros zumbis
            for (int position[] : newPositions) {
                if (Arrays.equals(newPosition, position)) {
                    zombie.setPostion(oldPosition[0], oldPosition[1]);
                    newPosition = oldPosition;
                    break;
                }
            }

            newPositions.add(newPosition);

            // Atualiza posição no mapa
            if (zombie instanceof ZombieCommon) {
                mapData[newPosition[0]][newPosition[1]] = 'Z';
            } else if (zombie instanceof ZombieCreeping) {
                mapData[newPosition[0]][newPosition[1]] = 'R';
            } else if (zombie instanceof ZombieRunner) {
                mapData[newPosition[0]][newPosition[1]] = 'C';
            }
        }

        for (Zombie zombie : zombiesToRemove) {
            zombies.remove(zombie);
        }

        gameUI.updateUI();
        checkGameOver();
    }

    private boolean hasZombie(int x, int y) {
        char cell = mapData[x][y];
        return cell == 'Z' || cell == 'R' || cell == 'C' || cell == 'G';
    }

    public void removeZombie(Zombie zombie) {
        int[] position = zombie.getPosition();

        mapData[position[0]][position[1]] = '.';
        zombies.remove(zombie);

        checkGameOver();
    }

    public void checkGameOver() {
        if (player.getHealth() <= 0) {
            gameUI.gameOver(false);
            return;
        }

        if (zombies.isEmpty()) {
            gameUI.gameOver(true);
        }
    }

    public void restartGame() {
        this.mapData = MapManager.loadMap(MapManager.getCurrentMapName());
        this.zombies = new ArrayList<>();
        this.zombieMap = new HashMap<>();
        this.chests = new ArrayList<>();
        iniatilizeEntities(player.getPerception());
        gameUI.updateUI();
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

package com.mycompany.zombicide;

import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public class GameUI {

    private static final int SIZE = 10;
    private GameManager gameManager;
    private JFrame frame;
    private JButton[][] buttons;
    private char[][] mapData;
    private boolean debugMode;
    private JLabel healthLabel;
    private JLabel perceptionLabel;
    private JLabel weaponLabel;
    private JLabel ammoLabel;
    private JLabel bandageLabel;
    private JButton healButton;
    private JPanel boardPanel;
    private Hero player;

    public GameUI(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
        this.mapData = gameManager.getMapData();
        this.player = gameManager.getPlayer();
    }

    public void initializeUI() {
        frame = new JFrame("Zumbicídio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 800);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Painel para o tabuleiro
        boardPanel = new JPanel(new GridLayout(SIZE, SIZE));
        buttons = new JButton[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 14));
                addIcon(i, j);
                buttons[i][j].setDisabledIcon(buttons[i][j].getIcon());
                buttons[i][j].setEnabled(false);
                boardPanel.add(buttons[i][j]);
            }
        }

        frame.add(boardPanel, BorderLayout.CENTER);

        // Painel de status
        JPanel statusPanel = new JPanel(new GridLayout(1, 5));
        healthLabel = new JLabel("Saúde: " + player.getHealth(), SwingConstants.CENTER);
        perceptionLabel = new JLabel("Percepção: " + player.getPerception(), SwingConstants.CENTER);
        ammoLabel = new JLabel("Munição: " + player.getAmmo(), SwingConstants.CENTER);
        bandageLabel = new JLabel("Ataduras: " + player.getBandage(), SwingConstants.CENTER);

        String[] weaponsName = player.getWeaponName();
        String weaponMessage = "Equipado: ";

        if ("Mãos".equals(weaponsName[0]) && weaponsName[1].isEmpty()) {
            weaponMessage += "Mãos";
        } else if ("Taco".equals(weaponsName[0]) && weaponsName[1].isEmpty()) {
            weaponMessage += "Taco";
        } else if ("Mãos".equals(weaponsName[0]) && "Arma".equals(weaponsName[1])) {
            weaponMessage += "Arma";
        } else if ("Taco".equals(weaponsName[0]) && "Arma".equals(weaponsName[1])) {
            weaponMessage += "Taco e Arma";
        }

        weaponLabel = new JLabel(weaponMessage, SwingConstants.CENTER);

        statusPanel.add(healthLabel);
        statusPanel.add(perceptionLabel);
        statusPanel.add(weaponLabel);
        statusPanel.add(ammoLabel);
        statusPanel.add(bandageLabel);

        frame.add(statusPanel, BorderLayout.NORTH);

        // Painel de controle
        JPanel controlPanel = new JPanel();
        healButton = new JButton("Curar");
        healButton.setEnabled(player.hasBandage());
        healButton.addActionListener(e -> {
            player.useBandage();
            JOptionPane.showMessageDialog(null, "Você usou uma atadura e recuperou 1 ponto de vida!");
            gameManager.moveAllZombies();
            updateUI();
            updateStatusPanel();
            createMovementButtons();
        });

        JButton exitButton = new JButton("Sair");
        exitButton.addActionListener(e -> gameOver(false));

        controlPanel.add(healButton);
        controlPanel.add(exitButton);

        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
        createMovementButtons();
    }

    public void updateUI() {
        mapData = gameManager.getMapData();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j].setIcon(null);
                buttons[i][j].setBackground(Color.WHITE);
                addIcon(i, j);
                buttons[i][j].setDisabledIcon(buttons[i][j].getIcon());
            }
        }

        updateStatusPanel();
        frame.revalidate();
        frame.repaint();
    }

    public void updateStatusPanel() {
        healthLabel.setText("Saúde: " + player.getHealth());

        String[] weaponsName = player.getWeaponName();
        String weaponMessage = "Equipado: ";

        if ("Mãos".equals(weaponsName[0]) && (weaponsName[1] == null || weaponsName[1].isEmpty())) {
            weaponMessage += "Mãos";
        } else if ("Taco".equals(weaponsName[0]) && (weaponsName[1] == null || weaponsName[1].isEmpty())) {
            weaponMessage += "Taco";
        } else if ("Mãos".equals(weaponsName[0]) && "Arma".equals(weaponsName[1])) {
            weaponMessage += "Arma";
        } else if ("Taco".equals(weaponsName[0]) && "Arma".equals(weaponsName[1])) {
            weaponMessage += "Taco e Arma";
        }

        weaponLabel.setText(weaponMessage);
        ammoLabel.setText("Munição: " + player.getAmmo());
        healButton.setEnabled(player.hasBandage() && player.getHealth() < 5);
        bandageLabel.setText("Ataduras: " + player.getBandage());
    }

    public void createMovementButtons() {
        // Reseta os botões
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j].setEnabled(false);
                for (ActionListener action : buttons[i][j].getActionListeners()) {
                    buttons[i][j].removeActionListener(action);
                }
            }
        }

        int[] playerPosition = player.getPosition();
        int x = playerPosition[0];
        int y = playerPosition[1];

        int[][] validMovements = {
            {x - 1, y},
            {x + 1, y},
            {x, y - 1},
            {x, y + 1}
        };

        for (int[] move : validMovements) {
            int newX = move[0];
            int newY = move[1];

            if (gameManager.isValidMove(newX, newY)) {
                buttons[newX][newY].setEnabled(true);
                buttons[newX][newY].setDisabledIcon(buttons[newX][newY].getIcon());
                buttons[newX][newY].addActionListener(e -> gameManager.movePlayer(newX, newY));
                buttons[newX][newY].setBackground(Color.LIGHT_GRAY);
            }
        }
    }

    public void initiateCombat(Zombie zombie) {
        int[] playerInitialPosition = player.getPosition().clone();
        int[] zombieInitialPosition = zombie.getPosition().clone();

        JDialog combatFrame = new JDialog(frame, "Combate", true);
        combatFrame.setSize(600, 200);
        combatFrame.setLayout(new BorderLayout());
        combatFrame.setLocationRelativeTo(null);
        combatFrame.setResizable(false);

        JPanel statusPanel = new JPanel(new GridLayout(3, 1));
        JLabel zombieTypeLabel = new JLabel("Tipo de Zumbi: " + zombie.getZombieType(zombie), SwingConstants.CENTER);
        JLabel zombieHealthLabel = new JLabel("Vida do Zumbi: " + zombie.getHealth(), SwingConstants.CENTER);
        JLabel playerHealthLabel = new JLabel("Sua vida: " + player.getHealth(), SwingConstants.CENTER);

        statusPanel.add(zombieTypeLabel);
        statusPanel.add(zombieHealthLabel);
        statusPanel.add(playerHealthLabel);

        JPanel actionPanel = new JPanel(new GridLayout(1, 3));
        JButton firstAttackButton = new JButton(player.hasBaseballBat() ? "Atacar com Taco" : "Atacar com Mãos");
        JButton secondAttackButton = new JButton("Atacar com Arma");
        JButton fleeButton = new JButton("Fugir");

        secondAttackButton.setEnabled(player.hasGun() && player.hasAmmo() && !(zombie instanceof ZombieRunner));
        if (!(player.hasBaseballBat()) && !(player.hasGun()) && zombie instanceof ZombieGiant) {
            firstAttackButton.setEnabled(false);
        }

        firstAttackButton.addActionListener(e -> {
            int damage = player.attack();
            zombie.takeDamage(damage);
            zombieHealthLabel.setText("Vida do Zumbi: " + zombie.getHealth());

            if (zombie.getHealth() <= 0) {
                JOptionPane.showMessageDialog(combatFrame, "Você derrotou o zumbi!");
                gameManager.removeZombie(zombie);

                // Verifica se o jogador atacou o zumbi
                if (Arrays.equals(zombieInitialPosition, player.getPosition())) {
                    // Jogador se move para a posição do zumbi
                    player.setPosition(zombieInitialPosition[0], zombieInitialPosition[1]);
                    mapData[zombieInitialPosition[0]][zombieInitialPosition[1]] = 'P';
                } else {
                    // Jogador permanece na posição que já estava
                    mapData[playerInitialPosition[0]][playerInitialPosition[1]] = 'P';
                }

                combatFrame.dispose();
                updateUI();
                createMovementButtons();
            } else {
                int perceptionRoll = (int) (Math.random() * 3) + 1;
                if (perceptionRoll <= player.getPerception()) {
                    JOptionPane.showMessageDialog(combatFrame, "Você conseguiu desviar do ataque!");
                } else {
                    player.takeDamage(zombie instanceof ZombieGiant ? 2 : 1);
                    playerHealthLabel.setText("Sua vida: " + player.getHealth());
                    JOptionPane.showMessageDialog(combatFrame, "O zumbi te atacou!");

                    if (player.getHealth() <= 0) {
                        combatFrame.dispose();
                        gameOver(false);
                    }
                }
            }
        });

        secondAttackButton.addActionListener(e -> {
            int damage = player.gunAttack();
            zombie.takeDamage(damage);
            zombieHealthLabel.setText("Vida do Zumbi: " + zombie.getHealth());

            if (zombie.getHealth() <= 0) {
                JOptionPane.showMessageDialog(combatFrame, "Você derrotou o zumbi!");
                gameManager.removeZombie(zombie);

                // Verifica se o jogador atacou o zumbi
                if (Arrays.equals(zombieInitialPosition, player.getPosition())) {
                    // Jogador se move para a posição do zumbi
                    player.setPosition(zombieInitialPosition[0], zombieInitialPosition[1]);
                    mapData[zombieInitialPosition[0]][zombieInitialPosition[1]] = 'P';
                } else {
                    // Jogador permanece na posição que já estava
                    mapData[playerInitialPosition[0]][playerInitialPosition[1]] = 'P';
                }

                combatFrame.dispose();
                updateUI();
                createMovementButtons();
            } else {
                int perceptionRoll = (int) (Math.random() * 3) + 1;
                if (perceptionRoll <= player.getPerception()) {
                    JOptionPane.showMessageDialog(combatFrame, "Você conseguiu desviar do ataque!");
                } else {
                    player.takeDamage(zombie instanceof ZombieGiant ? 2 : 1);
                    playerHealthLabel.setText("Sua vida: " + player.getHealth());
                    JOptionPane.showMessageDialog(combatFrame, "O zumbi te atacou!");

                    if (player.getHealth() <= 0) {
                        combatFrame.dispose();
                        gameOver(false);
                    }
                }
            }

        });

        fleeButton.addActionListener(e -> {
            boolean scaped = gameManager.tryToScape();
            if (scaped) {
                JOptionPane.showMessageDialog(combatFrame, "Você conseguiu fugir!");
                combatFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(combatFrame, "Não foi possível fugir!");

                int perceptionRoll = (int) (Math.random() * 3) + 1;
                if (perceptionRoll <= player.getPerception()) {
                    JOptionPane.showMessageDialog(combatFrame, "Você conseguiu desviar do ataque!");
                } else {
                    player.takeDamage(zombie instanceof ZombieGiant ? 2 : 1);
                    playerHealthLabel.setText("Sua Vida: " + player.getHealth());
                    JOptionPane.showMessageDialog(combatFrame, "O zumbi te atacou!");

                    if (player.getHealth() <= 0) {
                        combatFrame.dispose();
                        gameOver(false);
                    }
                }
            }
        });

        actionPanel.add(firstAttackButton);
        actionPanel.add(secondAttackButton);
        actionPanel.add(fleeButton);

        JPanel messagePanel = new JPanel();
        JLabel messageLabel = new JLabel("Escolha sua ação!", SwingConstants.CENTER);
        messagePanel.add(messageLabel);

        combatFrame.add(statusPanel, BorderLayout.NORTH);
        combatFrame.add(actionPanel, BorderLayout.CENTER);
        combatFrame.add(messagePanel, BorderLayout.SOUTH);

        combatFrame.setVisible(true);
    }

    public void openChest(int[] position) {
        List<Chest> chests = gameManager.getChests();
        for (Chest chest : chests) {
            if (Arrays.equals(chest.getPosition(), position)) {
                String content = chest.getContent();
                switch (content) {
                    case "bandage":
                        JOptionPane.showMessageDialog(null, "Você encontrou uma atadura!");
                        // icon
                        player.addBandage();
                        break;
                    case "baseball_bat":
                        JOptionPane.showMessageDialog(null, "Você encontrou um taco de beisebol!");
                        // icon
                        player.setCurrentWeapon("baseball_bat");
                        break;
                    case "revolver_zombie":
                        // icon
                        if (player.hasGun()) {
                            JOptionPane.showMessageDialog(null, "Você encontrou mais munição, mas há um zumbi rastejante!");
                            player.addAmmo();
                        } else {
                            JOptionPane.showMessageDialog(null, "Você encontrou uma arma, mas há um zumbi rastejante!");
                            player.setCurrentWeapon("gun");
                            player.addAmmo();
                        }

                        int perceptionRoll = (int) (Math.random() * 3) + 1;
                        if (perceptionRoll <= player.getPerception()) {
                            JOptionPane.showMessageDialog(null, "Você conseguiu evitar o ataque do zumbi rastejante!");
                        } else {
                            player.takeDamage(1);
                            JOptionPane.showMessageDialog(null, "O zumbi rastejante te atacou! Vida restante: " + player.getHealth());
                            if (player.getHealth() <= 0) {
                                gameOver(false);
                            }
                        }
                        break;
                }

                mapData[position[0]][position[1]] = 'P';
                chests.remove(chest);
                break;
            }
        }

        updateUI();
        createMovementButtons();
    }

    public void gameOver(boolean victory) {
        String message = victory ? "Você venceu! Todos os zumbis foram derrotados!" : "Você perdeu! Sua vida chegou a zero.";

        JFrame endFrame = new JFrame("Fim de Jogo");
        endFrame.setSize(500, 200);
        endFrame.setLayout(new GridLayout(4, 1));
        endFrame.setLocationRelativeTo(null);

        JLabel resultLabel = new JLabel(message, SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton restartButton = new JButton("Reiniciar Jogo");
        JButton newGameButton = new JButton("Novo Jogo");
        JButton exitGameButton = new JButton("Sair do Jogo");

        restartButton.addActionListener(e -> {
            restartGame();
            endFrame.dispose();
        });

        newGameButton.addActionListener(e -> {
            new WelcomeScreen();
            endFrame.dispose();
            frame.dispose();
        });

        exitGameButton.addActionListener(e -> {
            System.exit(0);
        });

        endFrame.add(resultLabel);
        endFrame.add(restartButton);
        endFrame.add(newGameButton);
        endFrame.add(exitGameButton);
        endFrame.setVisible(true);
    }

    public void restartGame() {
        frame.dispose();

        String mapName = MapManager.getCurrentMapName();
        GameUI newGame = new GameUI(this.debugMode);
        GameManager newGameManager = new GameManager(player.getHealth(), newGame, mapName);
        newGame.setGameManager(newGameManager);
        newGame.initializeUI();
    }

    private void addIcon(int i, int j) {
        char symbol = mapData[i][j];
        boolean isVisible = debugMode || gameManager.isVisible(i, j);

        // Rastejante nunca é visível
        if (symbol == 'R' && !debugMode) {
            isVisible = false;
        }

        if (isVisible || symbol == 'P') {
            switch (symbol) {
                case 'P': // Jogador
                    String[] weaponsName = player.getWeaponName();
                    if ("Mãos".equals(weaponsName[0]) && weaponsName[1].isEmpty()) {
                        buttons[i][j].setIcon(createIcon("heroWithNoGuns.png"));
                    } else if ("Taco".equals(weaponsName[0]) && weaponsName[1].isEmpty()) {
                        buttons[i][j].setIcon(createIcon("heroWithBat.png"));
                    } else if ("Mãos".equals(weaponsName[0]) && "Arma".equals(weaponsName[1])) {
                        buttons[i][j].setIcon(createIcon("heroWithGun.png"));
                    } else if ("Taco".equals(weaponsName[0]) && "Arma".equals(weaponsName[1])) {
                        buttons[i][j].setIcon(createIcon("heroWithGun.png"));
                    }
                    break;
                case 'R': // Zumbi Rastejante
                    buttons[i][j].setIcon(createIcon("creeping.png"));
                    break;
                case 'Z': // Zumbi Comum
                    buttons[i][j].setIcon(createIcon("zombie.png"));
                    break;
                case 'C': // Zumbi Corredor
                    buttons[i][j].setIcon(createIcon("runner.png"));
                    break;
                case 'G': // Zumbi Gigante
                    buttons[i][j].setIcon(createIcon("giant.png"));
                    break;
                case 'B': // Baú
                    buttons[i][j].setIcon(createIcon("trunk.png"));
                    break;
                case '#': // Parede
                    buttons[i][j].setIcon(createIcon("wall.png"));
                    break;
                default: // Espaço vazio
                    buttons[i][j].setIcon(null);
                    buttons[i][j].setBackground(Color.WHITE);
                    break;
            }
        } else {
            buttons[i][j].setIcon(null);
            buttons[i][j].setBackground(Color.DARK_GRAY);
        }
    }

    private ImageIcon createIcon(String filename) {
        int width = 50, height = 50;

        if (filename.equals("wall.png")) {
            width = 80;
            height = 80;
        }

        URL iconUrl = getClass().getClassLoader().getResource("icons/" + filename);

        if (iconUrl == null) {
            System.err.println("Erro: Ícone não encontrado - " + filename);
        }

        ImageIcon icon = new ImageIcon(iconUrl);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }
}

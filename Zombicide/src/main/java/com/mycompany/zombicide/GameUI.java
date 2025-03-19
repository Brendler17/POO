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
        frame.setSize(800, 700);
        frame.setLayout(new GridLayout(SIZE, SIZE));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        buttons = new JButton[SIZE][SIZE];
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 14));
                addIcon(i, j);
                buttons[i][j].setDisabledIcon(buttons[i][j].getIcon());
                buttons[i][j].setEnabled(false);
                frame.add(buttons[i][j]);
            }
        }
        
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
        
        frame.revalidate();
        frame.repaint();
    }
    
    public void createMovementButtons() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j].setEnabled(false);
                for (ActionListener action : buttons[i][j].getActionListeners()) {
                    buttons[i][j].removeActionListener(action);
                }
            }
        }
        
        int[] playerPosition = gameManager.getPlayer().getPosition();
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
        JFrame combatFrame = new JFrame("Combate");
        combatFrame.setSize(600, 200);
        combatFrame.setLayout(new BorderLayout());
        combatFrame.setLocationRelativeTo(null);
        combatFrame.setResizable(false);
        
        JPanel statusPanel = new JPanel(new GridLayout(3, 1));
        JLabel zombieTypeLabel = new JLabel("Tipo de Zumbi: " + zombie.getZombieType(zombie), SwingConstants.CENTER);
        JLabel zombieHealthLabel = new JLabel("Vida do Zumbi: " + zombie.getHealth(), SwingConstants.CENTER);
        JLabel playerHealthLabel = new JLabel("Sua vida: " + player.getHealth() + SwingConstants.CENTER);
        
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
            player.useAmmo();
            zombie.takeDamage(2);
            zombieHealthLabel.setText("Vida do Zumbi: " + zombie.getHealth());
            
            if (zombie.getHealth() <= 0) {
                JOptionPane.showMessageDialog(combatFrame, "Você derrotou o zumbi!");
                // remover zumbi
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
                        JOptionPane.showMessageDialog(null, "Você encontrou uma arma, mas há um zumbi rastejante!");
                        // icon
                        if (player.hasGun()) {
                            player.addAmmo();
                        } else {
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
                
                mapData[position[0]][position[1]] = '.';
                chests.remove(chest);
                break;
            }
        }
        
        updateUI();
    }
    
    public void gameOver(boolean victory) {
        String message = victory ? "Você venceu! Todos os zumbis foram derrotados!" : "Você perdeu! Sua vida chegou a zero.";
        
        JFrame endFrame = new JFrame("Fim de Jogo");
        endFrame.setSize(500, 200);
        endFrame.setLayout(new GridLayout(3, 1));
        endFrame.setLocationRelativeTo(null);
        
        JLabel resultLabel = new JLabel(message, SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        JButton restartButton = new JButton("Reiniciar Jogo");
        JButton newGameButton = new JButton("Novo Jogo");
        
        restartButton.addActionListener(e -> {
            restartGame();
            endFrame.dispose();
        });
        
        newGameButton.addActionListener(e -> {
            new WelcomeScreen();
            endFrame.dispose();
        });
        
        endFrame.add(resultLabel);
        endFrame.add(restartButton);
        endFrame.add(newGameButton);
        endFrame.setVisible(true);
    }
    
    public void restartGame() {
        gameManager.restartGame();
        frame.dispose();
    }
    
    private void addIcon(int i, int j) {
        char symbol = mapData[i][j];
        
        switch (symbol) {
            case 'P': // Jogador
                buttons[i][j].setIcon(createIcon("heroWithNoGuns.png"));
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
                buttons[i][j].setBackground(Color.WHITE);
                break;
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

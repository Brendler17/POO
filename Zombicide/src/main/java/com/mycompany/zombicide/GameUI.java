package com.mycompany.zombicide;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class GameUI {

    private static final int SIZE = 10;
    private GameManager gameManager;
    private JFrame frame;
    private JButton[][] buttons;
    private char[][] mapData;

    public GameUI(int playerPerception, boolean debugMode) {
        gameManager = new GameManager(playerPerception);
        this.mapData = gameManager.getMapData();

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
                AddIcon(i, j);
                buttons[i][j].setDisabledIcon(buttons[i][j].getIcon());
                buttons[i][j].setEnabled(false);
                frame.add(buttons[i][j]);
            }
        }

        frame.setVisible(true);
    }

    private void AddIcon(int i, int j) {
        char symbol = mapData[i][j];

        switch (symbol) {
            case 'P': // Jogador
                buttons[i][j].setIcon(CreateIcon("heroWithNoGuns.png"));
                break;
            case 'R': // Zumbi Rastejante
                buttons[i][j].setIcon(CreateIcon("creeping.png"));
                break;
            case 'Z': // Zumbi Comum
                buttons[i][j].setIcon(CreateIcon("zombie.png"));
                break;
            case 'C': // Zumbi Corredor
                buttons[i][j].setIcon(CreateIcon("runner.png"));
                break;
            case 'G': // Zumbi Gigante
                buttons[i][j].setIcon(CreateIcon("giant.png"));
                break;
            case 'B': // Baú
                buttons[i][j].setIcon(CreateIcon("trunk.png"));
                break;
            case '#': // Parede
                buttons[i][j].setIcon(CreateIcon("wall.png"));
                break;
            default: // Espaço vazio
                buttons[i][j].setBackground(Color.WHITE);
                break;
        }
    }

    private ImageIcon CreateIcon(String filename) {
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

package com.mycompany.zombicide;

import java.awt.*;
import javax.swing.*;

public class GameUI {

    private static final int SIZE = 10;
    private JFrame frame;
    private JButton[][] buttons;
    private char[][] mapData;

    public GameUI() {
        this.mapData = MapManager.loadRandomMap();

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
                frame.add(buttons[i][j]);
            }
        }

        frame.setVisible(true);
    }

    private void addIcon(int i, int j) {
        char symbol = mapData[i][j];

        switch (symbol) {
            case 'P': // Jogador
                buttons[i][j].setBackground(Color.GREEN);
                break;
            case 'R': // Zumbi Rastejante
                buttons[i][j].setBackground(Color.GRAY);
                break;
            case 'C': // Zumbi Comum
                buttons[i][j].setBackground(Color.DARK_GRAY);
                break;
            case 'X': // Zumbi Corredor
                buttons[i][j].setBackground(Color.ORANGE);
                break;
            case 'G': // Zumbi Gigante
                buttons[i][j].setBackground(Color.RED);
                break;
            case 'B': // Baú
                buttons[i][j].setBackground(Color.YELLOW);
                break;
            case '#': // Parede
                buttons[i][j].setBackground(Color.BLACK);
                break;
            default: // Espaço vazio
                buttons[i][j].setBackground(Color.WHITE);
                break;
        }
    }
}

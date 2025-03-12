package com.mycompany.zombicide;

import java.awt.*;
import javax.swing.*;

public class WelcomeScreen {

    private JFrame frame;
    private int playerPerception;
    private boolean debugMode = false;

    public WelcomeScreen() {
        frame = new JFrame("Zumbicídio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JLabel title = new JLabel("Bem-Vindo ao Zumbicídio!", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(title);

        String[] difficulties = {"Fácil (Percepção: 3)", "Médio (Percepção: 2)", "Difícil (Percepção: 1)"};
        JComboBox<String> difficultyBox = new JComboBox<>(difficulties);
        frame.add(difficultyBox);

        JButton playButton = new JButton("Jogar");
        JButton debugButton = new JButton("DEBUG");
        JButton exitButton = new JButton("Sair");

        playButton.addActionListener(e -> {
            playerPerception = 3 - difficultyBox.getSelectedIndex();
            startGame(false);
        });

        debugButton.addActionListener(e -> {
            playerPerception = 3 - difficultyBox.getSelectedIndex();
            startGame(true);
        });

        exitButton.addActionListener(e -> System.exit(0));

        frame.add(playButton);
        frame.add(debugButton);
        frame.add(exitButton);

        frame.setVisible(true);
    }

    private void startGame(boolean debug) {
        debugMode = debug;
        frame.dispose();
        new GameUI(playerPerception, debugMode);
    }
}

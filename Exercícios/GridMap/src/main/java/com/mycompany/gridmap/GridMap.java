package com.mycompany.gridmap;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class GridMap {

    private static JFrame CreateFrame() {
        JFrame frame = new JFrame("Mapa Grid");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        return frame;
    }

    private static ImageIcon CreateIcon(String filename) {
        int width = 70, height = 70;

        if (filename.equals("wall.png")) {
            width = 130;
            height = 130;
        }

        ImageIcon icon = new ImageIcon(filename);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }

    private static JPanel CreateGridPanel(JButton[][] buttonsMatrix, int[] heroPosition, int rows, int columns) {
        JPanel gridPanel = new JPanel(new GridLayout(rows, columns));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                buttonsMatrix[i][j] = new JButton();
                buttonsMatrix[i][j].setEnabled(false);

                if (i == heroPosition[0] && j == heroPosition[1]) {
                    buttonsMatrix[i][j].setIcon(CreateIcon("hero.png"));
                    buttonsMatrix[i][j].setEnabled(true);
                    buttonsMatrix[i][j].putClientProperty("type", "hero");
                }

                gridPanel.add(buttonsMatrix[i][j]);
            }
        }

        return gridPanel;
    }

    private static void AddWall(JButton[][] buttonsMatrix) {
        Random random = new Random();
        int counter = 0;

        while (counter < 5) {
            int deltaX = random.nextInt(5);
            int deltaY = random.nextInt(5);

            if (!buttonsMatrix[deltaX][deltaY].isEnabled()) {
                buttonsMatrix[deltaX][deltaY].setIcon(CreateIcon("wall.png"));
                buttonsMatrix[deltaX][deltaY].putClientProperty("type", "wall");
                buttonsMatrix[deltaX][deltaY].setEnabled(true);
                counter++;
            }
        }
    }

    private static void AddEnemys(JButton[][] buttonsMatrix) {
        Random rand = new Random();
        int counter = 0;

        while (counter < 3) {
            int deltaX = rand.nextInt(5);
            int deltaY = rand.nextInt(5);

            if (!buttonsMatrix[deltaX][deltaY].isEnabled()) {
                buttonsMatrix[deltaX][deltaY].setIcon(CreateIcon("enemy" + (counter + 1) + ".png"));
                buttonsMatrix[deltaX][deltaY].putClientProperty("type", "enemy");
                buttonsMatrix[deltaX][deltaY].setEnabled(true);
                counter++;
            }

        }
    }

    private static JPanel CreateMovementPanel(JButton[][] buttonsMatrix, int[] heroPosition) {
        JPanel panel = new JPanel(new GridLayout(2, 3));

        JButton buttonLeft = new JButton("←");
        JButton buttonRight = new JButton("→");
        JButton buttonUp = new JButton("↑");
        JButton buttonDown = new JButton("↓");

        buttonLeft.addActionListener(e -> MoveHero(buttonsMatrix, heroPosition, 0, -1));
        buttonRight.addActionListener(e -> MoveHero(buttonsMatrix, heroPosition, 0, 1));
        buttonDown.addActionListener(e -> MoveHero(buttonsMatrix, heroPosition, 1, 0));
        buttonUp.addActionListener(e -> MoveHero(buttonsMatrix, heroPosition, -1, 0));

        panel.add(new JLabel());
        panel.add(buttonUp);
        panel.add(new JLabel());

        panel.add(buttonLeft);
        panel.add(buttonDown);
        panel.add(buttonRight);

        return panel;
    }

    private static void MoveHero(JButton[][] buttonsMatrix, int[] heroPosition, int deltaX, int deltaY) {
        int heroX = heroPosition[0];
        int heroY = heroPosition[1];
        int newX = heroX + deltaX;
        int newY = heroY + deltaY;

        if (newX >= 0 && newX < 5 && newY >= 0 && newY < 5) {
            String type = (String) buttonsMatrix[newX][newY].getClientProperty("type");

            if ("wall".equals(type)) {
                return;
            } else if ("enemy".equals(type)) {
                JOptionPane.showMessageDialog(null, "Combat!", "Alerta", JOptionPane.WARNING_MESSAGE);
                return;
            }

            buttonsMatrix[heroX][heroY].setIcon(null);
            buttonsMatrix[heroX][heroY].setEnabled(false);
            buttonsMatrix[heroX][heroY].putClientProperty("type", null);

            heroPosition[0] = newX;
            heroPosition[1] = newY;

            buttonsMatrix[newX][newY].setIcon(CreateIcon("hero.png"));
            buttonsMatrix[newX][newY].setEnabled(true);
            buttonsMatrix[newX][newY].putClientProperty("type", "hero");
        }
    }

    private static void AddKeyMovement(JFrame frame, JButton[][] buttonsMatrix, int[] heroPosition) {
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                switch (keyCode) {
                    case KeyEvent.VK_W:
                        MoveHero(buttonsMatrix, heroPosition, -1, 0);
                        break;
                    case KeyEvent.VK_A:
                        MoveHero(buttonsMatrix, heroPosition, 0, -1);
                        break;
                    case KeyEvent.VK_S:
                        MoveHero(buttonsMatrix, heroPosition, 1, 0);
                        break;
                    case KeyEvent.VK_D:
                        MoveHero(buttonsMatrix, heroPosition, 0, 1);
                        break;
                    default:
                        break;
                }
            }
        });

        frame.setFocusable(true);
    }

    public static void main(String[] args) {
        int rows = 5, columns = 5;
        JFrame frame = CreateFrame();
        JButton[][] buttonsMatrix = new JButton[rows][columns];
        int[] heroPosition = {0, 0};

        JPanel gridPanel = CreateGridPanel(buttonsMatrix, heroPosition, rows, columns);
        JPanel movementPanel = CreateMovementPanel(buttonsMatrix, heroPosition);

        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(movementPanel, BorderLayout.SOUTH);

        AddEnemys(buttonsMatrix);
        AddWall(buttonsMatrix);
        AddKeyMovement(frame, buttonsMatrix, heroPosition);

        frame.setVisible(true);
    }
}

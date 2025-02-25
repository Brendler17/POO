package com.mycompany.gridmap;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;

public class GridMap {

    private static JFrame CreateFrame() {
        JFrame frame = new JFrame("Mapa Grid");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        return frame;
    }

    private static ImageIcon CreateHeroIcon() {
        ImageIcon icon = new ImageIcon("hero.png");
        Image scaledImage = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }

    private static JPanel CreateMovementButton(JButton[][] buttonsMatrix, int[] heroPosition) {
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
            buttonsMatrix[heroX][heroY].setIcon(null);
            buttonsMatrix[heroX][heroY].setEnabled(false);

            heroPosition[0] = newX;
            heroPosition[1] = newY;

            buttonsMatrix[newX][newY].setIcon(CreateHeroIcon());
            buttonsMatrix[newX][newY].setEnabled(true);
        }
    }

    public static void main(String[] args) {
        int rows = 5, columns = 5;
        JFrame frame = CreateFrame();

        JPanel gridPanel = new JPanel(new GridLayout(rows, columns));
        JButton[][] buttonsMatrix = new JButton[rows][columns];
        int[] heroPosition = {0, 0};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                buttonsMatrix[i][j] = new JButton();
                buttonsMatrix[i][j].setEnabled(false);

                if (i == heroPosition[0] && j == heroPosition[1]) {
                    buttonsMatrix[i][j].setIcon(CreateHeroIcon());
                    buttonsMatrix[i][j].setEnabled(true);
                }

                gridPanel.add(buttonsMatrix[i][j]);
            }
        }

        JPanel movementPanel = CreateMovementButton(buttonsMatrix, heroPosition);

        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(movementPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}

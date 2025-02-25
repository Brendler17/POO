package com.mycompany.gridmap;

import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;

public class Gridmap {

    private static JButton createHeroIcon() {
        ImageIcon icon = new ImageIcon("hero.png");
        Image scaledImage = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        return new JButton(resizedIcon);
    }

    private static void createMovementButton(JFrame frame) {
        JButton buttonLeft = new JButton("←");
        JButton buttonRight = new JButton("→");
        JButton buttonTop = new JButton("↑");
        JButton buttonDown = new JButton("↓");

        buttonLeft.addActionListener();

        frame.add(buttonLeft);
        frame.add(buttonTop);
        frame.add(buttonDown);
        frame.add(buttonRight);
    }

    private static void moveHero(int deltaX, int deltaY) {
        int newX = heroX + deltaX;
        int newY = heroY + deltaY;

        if (newX >= 0 && newX < 5 && newY >= 0 && newY < 5) {
            for (int rows = 0; rows < 5; rows++) {
                for (int columns = 0; columns < 5; columns++) {

                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mapa Grid");
        int rows = 5, columns = 5;
        GridLayout grid = new GridLayout(rows, columns);
        JButton[][] matrix = new JButton[rows][columns];

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(grid);

        JButton heroButton = createHeroIcon();

        for (int counter = 1; counter < 25; counter++) {
            JButton button = new JButton();
            button.setEnabled(false);
            frame.add(button);
        }

        createMovementButton(frame);

        frame.setVisible(true);
    }
}

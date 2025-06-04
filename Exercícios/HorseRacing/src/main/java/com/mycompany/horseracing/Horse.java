package com.mycompany.horseracing;

import java.awt.Color;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

class Horse implements Runnable {

    private static final int TOTAL_DISTANCE = 1000;
    private static boolean winnerDeclared = false;
    private String name;
    private int position;
    private Color color;
    private Random random;
    private int updateInterval;

    public Horse(String name, Color color, int updateInterval) {
        this.name = name;
        this.position = 0;
        this.color = color;
        this.random = new Random();
        this.updateInterval = updateInterval;
    }

    @Override
    public void run() {
        while (position < TOTAL_DISTANCE) {

            // Atualiza a velocidade aleatoriamente (entre 2 e 10 m/s)
            int speed = random.nextInt(16) + 5;

            position += speed;

            // Aguarda UPDATE_INTERVAL segundos antes da próxima atualização
            try {
                Thread.sleep(updateInterval);
            } catch (InterruptedException e) {
                System.out.println(name + " was interrupted!");
            }

            // Exibir o vencedor apenas uma vez
            if (!winnerDeclared && position >= TOTAL_DISTANCE) {
                winnerDeclared = true;
                System.out.println(name + " win the race!");

                SwingUtilities.invokeLater(()
                        -> JOptionPane.showMessageDialog(null, "O vencedor é: " + name + "!", "Corrida Finalizada", JOptionPane.INFORMATION_MESSAGE)
                );
            }
        }

        System.out.println(name + " finish the race!");
    }

    public int getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}

package com.mycompany.horseracing;

import java.awt.*;
import javax.swing.*;

public class RacingPanel extends JPanel {

    private Horse[] horses;
    private long startTime;

    public RacingPanel(Horse[] horses) {
        this.horses = horses;
        this.startTime = System.currentTimeMillis(); // Marca o inicio da corrida
    }

    // Atualiza o painel com o novo tempo
    public void updateTimer() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenha a pista
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Calcula o tempo decorrido
        long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;

        // Desenha o tempo
        g.setColor(Color.BLACK);
        g.drawString("Timer: " + elapsedTime + "s", 20, 30);

        // Desenha os cavalos
        for (int i = 0; i < horses.length; i++) {
            Horse horse = horses[i];
            g.setColor(horse.getColor());
            int y = 50 + i * 50; // Posiciona cada cavalo em uma linha diferente
            int x = horse.getPosition();
            g.fillOval(x, y, 30, 30); // Desenha o cavalo como um círculo
            g.drawString(horse.getName(), x + 35, y + 20); // Exibe o nome do cavalo
        }
    }
}

package com.mycompany.horseracing;

import java.awt.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class HorseRacing {

    private static int updateInterval;

    public static void main(String[] args) {
        // Pergunta ao usuário a velocidade da corrida
        setRaceSpeed();

        // Cria a janela
        JFrame frame = createFrame();

        // Cria os cavalos
        Horse[] horses = createHorses();

        // Cria o painel da corrida e adiciona à janela
        RacingPanel panel = new RacingPanel(horses);
        frame.add(panel);

        // Inicia a corrida
        for (Horse horse : horses) {
            new Thread(horse).start();
        }

        // Adiciona temporizador e Atualiza a tela
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> panel.updateTimer());
            }

        }, 0, 100);

        // Exibe a janela
        frame.setVisible(true);
    }

    public static void setRaceSpeed() {
        String[] options = {"Lenta", "Normal", "Rápida"};
        int choice = JOptionPane.showOptionDialog(null, "Escolha a velocidade da corrida:", "Configuração", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

        switch (choice) {
            case 0 ->
                updateInterval = 700;
            case 2 ->
                updateInterval = 200;
            default ->
                updateInterval = 400;
        }
    }

    public static JFrame createFrame() {
        JFrame frame = new JFrame("Corrida de Cavalos");
        frame.setSize(1200, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        return frame;
    }

    public static Horse[] createHorses() {
        Horse[] horses = new Horse[4];
        horses[0] = new Horse("Cavalo 1", Color.RED, updateInterval);
        horses[1] = new Horse("Cavalo 2", Color.BLUE, updateInterval);
        horses[2] = new Horse("Cavalo 3", Color.GREEN, updateInterval);
        horses[3] = new Horse("Cavalo 4", Color.ORANGE, updateInterval);

        return horses;
    }
}

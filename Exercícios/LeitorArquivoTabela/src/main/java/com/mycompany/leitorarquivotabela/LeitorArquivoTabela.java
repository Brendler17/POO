package com.mycompany.leitorarquivotabela;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorArquivoTabela extends JFrame {

    public LeitorArquivoTabela(String caminhoArquivo) {
        setTitle("Tabela de Dados");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        preencherTabela(caminhoArquivo, model);
    }

    private void preencherTabela(String caminhoArquivo, DefaultTableModel model) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                String data[] = line.split(",");

                if (firstLine) {
                    for (String column : data) {
                        model.addColumn(column.trim());
                    }

                    firstLine = false;
                } else {
                    model.addRow(data);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error to read file!\n", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String caminhoArquivo = "data.txt";
            new LeitorArquivoTabela(caminhoArquivo).setVisible(true);
        });
    }
}

package com.mycompany.prova;

public class Vetor {

    private String[] vetor;
    private int tamanho;

    public Vetor(int tamanhoMaximo) {
        vetor = new String[tamanhoMaximo];
        tamanho = 0;
    }

    public void insert(String novoElemento) {
        if (tamanho >= vetor.length) {
            String[] novoVetor = new String[vetor.length * 2];

            System.arraycopy(vetor, 0, novoVetor, 0, vetor.length);

            vetor = novoVetor;
        }

        vetor[tamanho] = novoElemento;
        tamanho++;
    }

    public String get(int posicao) {
        if (posicao < 0 || posicao >= tamanho) {
            return null;
        }

        return vetor[posicao];
    }

    public int size() {
        return tamanho;
    }
}

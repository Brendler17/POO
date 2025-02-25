package com.mycompany.exerciciosprova;

public class ExerciciosProva {

    private static int[] array;

    public ExerciciosProva() {
        array = new int[10];
        fillArray(array);
    }

    public static void main(String[] args) {
        ExerciciosProva exemplo = new ExerciciosProva();

        for (int counter = 0; counter < array.length; counter++) {
            System.out.print(array[counter] + "\t");
        }

        inverte(exemplo.array);

        System.out.println("\n");
        for (int counter = 0; counter < array.length; counter++) {
            System.out.print(array[counter] + "\t");
        }
    }

    public void fillArray(int[] array) {
        array[0] = 1;
        array[1] = 2;
        array[2] = 3;
        array[3] = 4;
        array[4] = 5;
        array[5] = 6;
        array[6] = 7;
        array[7] = 8;
        array[8] = 9;
        array[9] = 10;
    }

    public static void inverte(int[] v) {
        int n = v.length;
        for (int i = 0; i < n / 2; i++) {
            int temp = v[i];
            v[i] = v[n - i - 1];
            v[n - i - 1] = temp;
        }
    }
}

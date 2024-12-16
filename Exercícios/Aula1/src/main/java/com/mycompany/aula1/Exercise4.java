package com.mycompany.aula1;

public class Exercise4 {

    public static void executar() {
        double firstNote = 4.6, secondNote = 8.1;
        double arithmeticAverage = (firstNote + secondNote) / 2;
        double weightedAverage = ((firstNote * 2) + (secondNote * 3)) / (3 + 2);

        System.out.println("A média aritimética para as notas " + firstNote + " e " + secondNote + ", é: " + arithmeticAverage);
        System.out.println("A média ponderada para as notas " + firstNote + " e " + secondNote + ", é: " + weightedAverage);
    }
}

package com.mycompany.aula1;

public class Exercise3 {

    public static void executar() {
        int coefficientA = 2, coefficientB = 5, coefficientC = 4;
        int discriminant = (int) Math.pow(coefficientB, 2) - 4 * coefficientA * coefficientC;
        System.out.println("O valor da discriminante para os coeficientes " + coefficientA + " " + coefficientB + " " + coefficientC + " é: " + discriminant);
    }
}

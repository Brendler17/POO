package com.mycompany.aula1;

import java.util.Scanner;

public class Exercise2 {

    public static void executar() {
        System.out.println("Executando o exercício 2: \n");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o IMC: ");
        int IMC = scanner.nextInt();

        if (IMC < 20) {
            System.out.println("Condição: Magro");
        } else if (IMC >= 20 && IMC < 25) {
            System.out.println("Condição: Normal");
        } else if (IMC >= 25 && IMC < 29) {
            System.out.println("Condição: Acima do Peso");
        } else if (IMC >= 29 && IMC < 30) {
            System.out.println("Condição: Obeso");
        } else if (IMC >= 30) {
            System.out.println("Condição: Muito Obeso");
        }
        
        scanner.close();
    }
}

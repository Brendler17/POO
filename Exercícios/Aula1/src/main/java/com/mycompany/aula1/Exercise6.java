package com.mycompany.aula1;

import java.util.Scanner;

public class Exercise6 {

    public static void executar() {
        int number, numberMultiples = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite um número: ");
        number = scanner.nextInt();

        for (int counter = number; counter > 0; counter--) {
            if (number % counter == 0) {
                numberMultiples++;
            }
        }

        if (numberMultiples == 2) {
            System.out.println("O número " + number + " é primo.");
        } else {
            System.out.println("O número " + number + " não é primo.");
        }

        scanner.close();
    }
}

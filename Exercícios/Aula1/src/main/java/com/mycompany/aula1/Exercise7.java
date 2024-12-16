package com.mycompany.aula1;

import java.util.Scanner;

public class Exercise7 {

    public static void executar() {
        int number, factorial = 1;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite um número: ");
        number = scanner.nextInt();

        for (int counter = number; counter > 0; counter--) {
            factorial *= counter;
        }

        System.out.println("O fatorial de " + number + " é: " + factorial);
    }
}

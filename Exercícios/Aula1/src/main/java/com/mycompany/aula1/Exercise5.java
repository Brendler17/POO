package com.mycompany.aula1;

import java.util.Scanner;

public class Exercise5 {

    public static void executar() {
        int intOne, intTwo, intThree;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o primeiro número: ");
        intOne = scanner.nextInt();
        System.out.println("Digite o segundo número: ");
        intTwo = scanner.nextInt();
        System.out.println("Digite o terceiro número: ");
        intThree = scanner.nextInt();

        if (intOne < intTwo && intOne < intThree) {
            System.out.println("O número " + intOne + " é o menor deles.\n");
        } else if (intTwo < intOne && intTwo < intThree) {
            System.out.println("O número " + intTwo + " é o menor deles.\n");
        } else {
            System.out.println("O número " + intThree + " é o menor deles.\n");
        }

        if (intOne > 0) {
            System.out.println("O número " + intOne + " é positivo.\n");
        }
        if (intTwo > 0) {
            System.out.println("O número " + intTwo + " é positivo.\n");
        }
        if (intThree > 0) {
            System.out.println("O número " + intThree + " é positivo.\n");
        }
    }
}

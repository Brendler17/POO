package com.mycompany.sistemabancario;

import java.util.Scanner;
import java.util.ArrayList;

public class SistemaBancario {

    private static final ArrayList<ContaBanco> accounts = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option;

        do {
            showMenu();
            option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    addNewAccount();
                    break;
                }
                case 2 -> {
                    removeAccount();
                    break;
                }
                case 3 -> {
                    listAccounts();
                    break;
                }
                case 4 -> {
                    searchAccount();
                    break;
                }
                case 0 -> {
                    System.out.println("Saindo...\n");
                    break;
                }
                default -> {
                    System.out.println("\nDigite uma opção válida.\n");
                    break;
                }
            }
        } while (option != 0);

    }

    public static void showMenu() {
        System.out.println("--------- MENU ---------\n"
                + "1 - Incluir Conta\n"
                + "2 - Excluir Conta\n"
                + "3 - Exibir Contas\n"
                + "4 - Buscar Conta\n"
                + "0 - Sair\n\n"
                + "Digite uma opção: ");
    }

    public static void addNewAccount() {
        System.out.println("\nSelecione o tipo de conta:\n1 - Pessoa Física\n2 - Pessoa Jurídica\n");
        int accountType = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nome Titular:");
        String name = scanner.nextLine();

        System.out.println("Saldo inicial:");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Oferecer Limite:\n1 - Sim\n2 - Não");
        int option = scanner.nextInt();
        scanner.nextLine();
        boolean hasLimit = (option == 1);

        double limit = 0;
        if (hasLimit) {
            System.out.println("Limite:");
            limit = scanner.nextDouble();
            scanner.nextLine();
        }

        ContaBanco newAccount;

        if (accountType == 1) {
            System.out.println("CPF: ");
            String cpf = scanner.nextLine();
            System.out.println("Data de Nascimento (dd mm aaaa): ");
            int day = scanner.nextInt();
            int month = scanner.nextInt();
            int year = scanner.nextInt();
            scanner.nextLine();
            Data birth = new Data(day, month, year);

            if (hasLimit) {
                newAccount = new ContaPessoaFisica(name, balance, limit, cpf, birth);
            } else {
                newAccount = new ContaPessoaFisica(name, balance, cpf, birth);
            }
        } else {
            System.out.println("CNPJ: ");
            String cnpj = scanner.nextLine();
            System.out.println("Inscrição Estadual: ");
            String stateRegistration = scanner.nextLine();

            if (hasLimit) {
                newAccount = new ContaPessoaJuridica(name, balance, limit, cnpj, stateRegistration);
            } else {
                newAccount = new ContaPessoaJuridica(name, balance, cnpj, stateRegistration);
            }
        }

        accounts.add(newAccount);
        System.out.println("Conta Adicionada com Sucesso!\n");
    }

    public static void removeAccount() {
        System.out.println("Digite o número da conta a ser removida: ");
        int removedAccountNumber = scanner.nextInt();
        scanner.nextLine();

        ContaBanco removedAccount = accounts.stream().
                filter(account -> account.getNumberAccount() == removedAccountNumber).
                findFirst().
                orElse(null);

        if (removedAccount != null) {
            accounts.remove(removedAccount);
            System.out.println("\nConta removida com sucesso!\n");
        } else {
            System.out.println("\nConta não encontrada!\n");
        }
    }
    
    public static void listAccounts(){
        if(accounts.isEmpty()){
            System.out.println("\nNenhuma conta cadastrada!\n");
        } else{
            System.out.println("\nContas Cadastradas\n");
            for(ContaBanco account : accounts){
                account.showAccount();
            }
        }
        System.out.println("\n");
    }
    
    public static void searchAccount(){
        System.out.println("Digite o número de conta a ser buscada:");
        int searchedAccountNumber = scanner.nextInt();
        scanner.nextLine();
        
        ContaBanco searchedAccount = accounts.stream().
                filter(account -> account.getNumberAccount() == searchedAccountNumber).
                findFirst().
                orElse(null);
        
        if(searchedAccount != null){
            searchedAccount.showAccount();
        } else{
            System.out.println("\nConta não encontrada\n");
        }
    }
}

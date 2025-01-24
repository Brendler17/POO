package com.mycompany.sistemabancario;

public class ContaBanco {

    private final int number;
    private String name;
    private double balance;
    private double limit;
    private static int numberAccounts;

    public ContaBanco(String name, double balance) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("\nO nome não pode ser em branco!\n");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("\nO saldo não pode ser negativo!\n");
        }

        this.name = name;
        this.balance = balance;
        numberAccounts++;
        this.number = numberAccounts;
    }

    public ContaBanco(String name, double balance, double limit) {
        this(name, balance);
        if (limit < 0) {
            throw new IllegalArgumentException("\nO limite não pode ser negativo!\n");
        }
        this.limit = limit;
    }

    public void deposit(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("\nO valor do depósito deve ser positivo!\n");
        }

        this.balance = this.balance + value;
    }

    public boolean withdraw(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("\nO valor do saque deve ser positivo!\n");
        }
        if (balance + limit >= value) {
            this.balance = this.balance - value;
            return true;
        }

        return false;
    }

    public boolean transfer(ContaBanco account, double value) {
        if (this.withdraw(value)) {
            account.deposit(value);
            return true;
        }
        return false;
    }

    public void showAccount() {
        System.out.println(this.toString());
    }

    public int getNumberAccount() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("\nO nome não pode ser em branco!\n");
        }

        this.name = name;
    }

    public double getBalance() {
        return this.balance;
    }

    public double getLimit() {
        return this.limit;
    }

    public void setLimit(double limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("\nO limite não pode ser negativo!\n");
        }
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "\n--------Conta--------"
                + "\nDono: " + this.name
                + "\nNúmero: " + this.number
                + "\nSaldo: " + this.balance
                + "\nLimite: " + this.limit
                + "\n";
    }
}

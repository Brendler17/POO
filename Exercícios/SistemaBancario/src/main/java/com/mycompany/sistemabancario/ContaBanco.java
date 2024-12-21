package com.mycompany.sistemabancario;

public class ContaBanco {

    private final int number;
    private String name;
    private double balance;
    private double limit;
    private static int numberAccounts;

    public ContaBanco(String name, double balance) {
        this.name = name;
        this.balance = balance;
        numberAccounts++;
        this.number = numberAccounts;
    }

    public ContaBanco(String name, double balance, double limit) {
        this(name, balance);
        this.limit = limit;
    }

    public void deposit(double value) {
        this.balance = this.balance + value;
    }

    public boolean withdraw(double valor) {
        if (balance + limit > valor) {
            this.balance = this.balance - valor;
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

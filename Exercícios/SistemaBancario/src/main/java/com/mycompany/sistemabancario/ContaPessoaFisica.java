package com.mycompany.sistemabancario;

public class ContaPessoaFisica extends ContaBanco {

    private String CPF;
    private Data birth;

    public ContaPessoaFisica(String name, double balance, double limit, String CPF, Data date) {
        super(name, balance, limit);

        if (CPF == null || CPF.isEmpty()) {
            throw new IllegalArgumentException("\nO CPF não pode ser nulo!\n");
        }

        this.CPF = CPF;
        this.birth = date;
    }

    public ContaPessoaFisica(String name, double balance, String CPF, Data date) {
        this(name, balance, 0, CPF, date);
    }

    public String getCPF() {
        return this.CPF;
    }

    public void setCPF(String CPF) {
        if (CPF == null || CPF.isEmpty()) {
            throw new IllegalArgumentException("\nO CPF não pode ser nulo!\n");
        }

        this.CPF = CPF;
    }

    public String toString() {
        return super.toString()
                + "CPF: " + this.CPF
                + birth.toString();
    }
}

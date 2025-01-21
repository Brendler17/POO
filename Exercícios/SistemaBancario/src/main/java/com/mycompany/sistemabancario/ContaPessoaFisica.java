package com.mycompany.sistemabancario;

public class ContaPessoaFisica extends ContaBanco {

    private final String CPF;
    private final Data birth;

    public ContaPessoaFisica(String name, double balance, double limit, String CPF, Data date) {
        super(name, balance, limit);
        this.CPF = CPF;
        this.birth = date;
    }

    public ContaPessoaFisica(String name, double balance, String CPF, Data date) {
        this(name, balance, 0, CPF, date);
    }

    public String toString() {
        return super.toString()
                + "CPF: " + this.CPF
                + birth.toString();
    }
}

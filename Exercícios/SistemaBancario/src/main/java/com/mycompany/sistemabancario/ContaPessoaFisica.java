package com.mycompany.sistemabancario;

public class ContaPessoaFisica extends ContaBanco {

    private final String CPF;
    private final Data birth;

    public ContaPessoaFisica(String name, double balance, String CPF, Data date) {
        super(name, balance);
        this.CPF = CPF;
        this.birth = date;
    }

    public String toString() {
        return super.toString()
                + "CPF: " + this.CPF
                + birth.toString();
    }
}

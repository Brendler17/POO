package com.mycompany.sistemabancario;

public class ContaPessoaJuridica extends ContaBanco {

    private String CNPJ;
    private String stateRegistration;

    public ContaPessoaJuridica(String name, double balance, double limit, String CNPJ, String stateRegistration) {
        super(name, balance, limit);
        this.CNPJ = CNPJ;
        this.stateRegistration = stateRegistration;
    }

    public ContaPessoaJuridica(String name, double balance, String CNPJ, String stateRegistration) {
        this(name, balance, 0, CNPJ, stateRegistration);
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nCNPJ: " + this.CNPJ
                + "\nRegistro de Estado: " + this.stateRegistration
                + "\n";
    }
}

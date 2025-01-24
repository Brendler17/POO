package com.mycompany.sistemabancario;

public class ContaPessoaJuridica extends ContaBanco {

    private String CNPJ;
    private String stateRegistration;

    public ContaPessoaJuridica(String name, double balance, double limit, String CNPJ, String stateRegistration) {
        super(name, balance, limit);

        if (CNPJ == null || CNPJ.isEmpty()) {
            throw new IllegalArgumentException("\nO CNPJ não pode ser nulo!\n");
        }

        if (stateRegistration == null || stateRegistration.isEmpty()) {
            throw new IllegalArgumentException("\nO registro não pode ser nulo!\n");
        }

        this.CNPJ = CNPJ;
        this.stateRegistration = stateRegistration;
    }

    public ContaPessoaJuridica(String name, double balance, String CNPJ, String stateRegistration) {
        this(name, balance, 0, CNPJ, stateRegistration);
    }

    public String getCNPJ() {
        return this.CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        if (CNPJ == null || CNPJ.isEmpty()) {
            throw new IllegalArgumentException("\nO CNPJ não pode ser nulo!\n");
        }

        this.CNPJ = CNPJ;
    }

    public String getStateRegistration() {
        return this.stateRegistration;
    }

    public void setStateRegistration(String stateRegistration) {
        if (stateRegistration == null || stateRegistration.isEmpty()) {
            throw new IllegalArgumentException("\nO registro não pode ser nulo!\n");
        }

        this.stateRegistration = stateRegistration;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nCNPJ: " + this.CNPJ
                + "\nRegistro de Estado: " + this.stateRegistration
                + "\n";
    }
}

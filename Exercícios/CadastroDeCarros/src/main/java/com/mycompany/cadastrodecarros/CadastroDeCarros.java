package com.mycompany.cadastrodecarros;

public class CadastroDeCarros {

    private Veículo[] vehicles;

    public CadastroDeCarros() {
        vehicles = new Veículo[12];
        initializeVehicles();
    }

    public void initializeVehicles() {
        vehicles[0] = new Carro(220, 110000, "Vermelho");
        vehicles[1] = new Carro(180, 87500, "Branco");
        vehicles[2] = new Carro(320, 452000, "Preto");
        vehicles[3] = new Carro(200, 97200, "Prata");
        vehicles[4] = new Caminhão(120, 345000, 3);
        vehicles[5] = new Caminhão(80, 278000, 1.5);
        vehicles[6] = new Caminhão(100, 298000, 2);
        vehicles[7] = new Caminhão(110, 310000, 2.5);
        vehicles[8] = new Moto(180, 27000, 500);
        vehicles[9] = new Moto(240, 97000, 1500);
        vehicles[10] = new Moto(150, 13500, 150);
        vehicles[11] = new Moto(180, 17800, 300);
    }

    public void displayVehicleInformation() {
        for (int counter = 0; counter < vehicles.length; counter++) {
            Veículo vehicle = vehicles[counter];

            if (vehicle instanceof Carro car) {
                System.out.printf("Veículo[%d] : Cor: %s – Velocidade Máxima: %.1f km/h – Preço: R$ %.2f – Taxa Base para o IPVA: %.1f – Valor do Imposto: R$ %.2f.%n",
                        counter, car.getColor(), car.getMaximumSpeed(), car.getPrice(), car.getIpvaTax(), car.calculateIpva());
            } else if (vehicle instanceof Caminhão truck) {
                System.out.printf("Veículo[%d] : Peso: %.1fT – Velocidade Máxima: %.1f km/h – Preço: R$ %.2f – Taxa Base para o IPVA: %.1f – Valor do Imposto: R$ %.2f.%n",
                        counter, truck.getMaximumLoad(), truck.getMaximumSpeed(), truck.getPrice(), truck.getIpvaTax(), truck.calculateIpva());
            } else if (vehicle instanceof Moto motorcycle) {
                System.out.printf("Veículo[%d] : Cilindradas: %d cc – Velocidade Máxima: %.1f km/h – Preço: R$ %.2f – Taxa Base para o IPVA: %.1f – Valor do Imposto: R$ %.2f.%n",
                        counter, motorcycle.getCylinderCapacity(), motorcycle.getMaximumSpeed(), motorcycle.getPrice(), motorcycle.getIpvaTax(), motorcycle.calculateIpva());
            }
        }
    }

    public static void main(String[] args) {
        CadastroDeCarros register = new CadastroDeCarros();
        register.displayVehicleInformation();
    }
}

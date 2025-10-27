package org.granja.creacionales.factorymethod;

/**
 * Fábrica concreta que crea objetos tipo Vaca.
 */
public class VacaFactory extends AnimalFactory {

    @Override
    public Animal crearAnimal() {
        return new Vaca();
    }
}

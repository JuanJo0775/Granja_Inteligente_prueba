package org.granja.creacionales.factorymethod;

/**
 * Fábrica concreta que crea objetos tipo Cerdo.
 */
public class CerdoFactory extends AnimalFactory {

    @Override
    public Animal crearAnimal() {
        return new Cerdo();
    }
}

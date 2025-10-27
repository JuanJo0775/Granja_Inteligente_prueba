package org.granja.creacionales.factorymethod;

/**
 * Fábrica concreta que crea objetos tipo Gallina.
 */
public class GallinaFactory extends AnimalFactory {

    @Override
    public Animal crearAnimal() {
        return new Gallina();
    }
}

package org.granja.creacionales.factorymethod;

/**
 * Clase concreta que representa una Gallina.
 */
public class Gallina implements Animal {

    @Override
    public void comer() {
        System.out.println("🐔 La gallina está picoteando su alimento.");
    }
}

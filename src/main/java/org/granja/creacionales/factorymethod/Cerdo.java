package org.granja.creacionales.factorymethod;

/**
 * Clase concreta que representa un Cerdo.
 */
public class Cerdo implements Animal {

    @Override
    public void comer() {
        System.out.println("🐖 El cerdo está comiendo maíz.");
    }
}

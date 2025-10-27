package org.granja.creacionales.factorymethod;

/**
 * Clase concreta que representa una Vaca.
 */
public class Vaca implements Animal {

    @Override
    public void comer() {
        System.out.println("🐄 La vaca está comiendo pasto.");
    }
}

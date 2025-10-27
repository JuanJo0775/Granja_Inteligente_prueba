package org.granja.estructurales.decorator;

import org.granja.creacionales.factorymethod.Animal;

/**
 * Decorador concreto que agrega un sistema de GPS
 * al comportamiento del animal.
 */
public class AnimalConGPS extends AnimalDecorator {

    public AnimalConGPS(Animal animal) {
        super(animal);
    }

    @Override
    public void comer() {
        super.comer();
        agregarGPS();
    }

    private void agregarGPS() {
        System.out.println("📍 GPS activado: posición del animal registrada.");
    }
}

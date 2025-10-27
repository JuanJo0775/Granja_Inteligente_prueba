package org.granja.estructurales.decorator;

import org.granja.creacionales.factorymethod.Animal;

/**
 * Clase abstracta base del patrón Decorator.
 * Permite añadir comportamientos adicionales a los animales
 * sin modificar sus clases originales.
 */
public abstract class AnimalDecorator implements Animal {

    protected Animal animalDecorado;

    public AnimalDecorator(Animal animal) {
        this.animalDecorado = animal;
    }

    @Override
    public void comer() {
        animalDecorado.comer();
    }
}

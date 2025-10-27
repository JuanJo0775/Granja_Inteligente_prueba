package org.granja.estructurales.facade;

import org.granja.creacionales.factorymethod.Animal;
import org.granja.estructurales.decorator.AnimalConGPS;

/**
 * Fachada adicional opcional que unifica sensor y decorador GPS.
 */
public class SistemaGPSFacade {

    private final SistemaSensoresFacade sensoresFacade;

    public SistemaGPSFacade(SistemaSensoresFacade sensoresFacade) {
        this.sensoresFacade = sensoresFacade;
    }

    public void monitorearAnimal(Animal animal) {
        AnimalConGPS animalGps = new AnimalConGPS(animal);
        sensoresFacade.verificarYAlimentar(animalGps);
    }
}

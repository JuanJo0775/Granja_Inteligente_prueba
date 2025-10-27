package org.granja.estructurales.facade;

import org.granja.creacionales.factorymethod.Animal;
import org.granja.estructurales.adapter.Sensor;

/**
 * Patrón Facade:
 * Proporciona una interfaz simplificada para el sistema de sensores,
 * ocultando la complejidad interna de los adaptadores y sensores.
 */
public class SistemaSensoresFacade {

    private final Sensor sensorAdaptado;

    public SistemaSensoresFacade(Sensor sensor) {
        this.sensorAdaptado = sensor;
    }

    /**
     * Obtiene el nivel de alimento desde el sensor.
     */
    public double obtenerNivelAlimento() {
        double nivel = sensorAdaptado.getLectura();
        System.out.println("📡 Sensor reporta nivel de alimento: " + nivel);
        return nivel;
    }

    /**
     * Verifica el nivel de alimento y decide si alimentar o no al animal.
     */
    public void verificarYAlimentar(Animal animal) {
        double nivel = obtenerNivelAlimento();

        if (nivel < 20) {
            System.out.println("⚙️ Nivel bajo detectado, activando alimentador...");
            animal.comer();
        } else {
            System.out.println("✅ Nivel suficiente, no es necesario alimentar.");
        }
    }
}

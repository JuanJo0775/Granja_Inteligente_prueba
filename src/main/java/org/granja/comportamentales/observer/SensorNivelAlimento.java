package org.granja.comportamentales.observer;

/**
 * Subject concreto: Sensor de Nivel de Alimento
 * Notifica cuando el nivel de alimento cambia y está por debajo del mínimo
 */
public class SensorNivelAlimento extends SubjectBase {
    private String ubicacion;
    private int nivelActual; // Porcentaje 0-100
    private int nivelMinimo; // Nivel que dispara la alerta

    public SensorNivelAlimento(String ubicacion, int nivelMinimo) {
        this.ubicacion = ubicacion;
        this.nivelMinimo = nivelMinimo;
        this.nivelActual = 100; // Inicia lleno
    }

    /**
     * Actualiza el nivel de alimento y notifica si es necesario
     */
    public void actualizarNivel(int nuevoNivel) {
        System.out.println("\n📡 [Sensor: " + ubicacion + "] Actualizando nivel...");
        System.out.println("   Nivel anterior: " + nivelActual + "%");
        System.out.println("   Nivel nuevo: " + nuevoNivel + "%");

        this.nivelActual = nuevoNivel;

        // Notificar a todos los observadores si el nivel es bajo
        if (nivelActual < nivelMinimo) {
            System.out.println("   ⚠️  Nivel por debajo del mínimo (" + nivelMinimo + "%)");
            notificarObservadores();
        } else {
            System.out.println("   ✓ Nivel normal");
        }
    }

    /**
     * Simula el consumo gradual de alimento
     */
    public void consumirAlimento(int cantidad) {
        int nuevoNivel = Math.max(0, nivelActual - cantidad);
        actualizarNivel(nuevoNivel);
    }

    /**
     * Simula la recarga de alimento
     */
    public void recargarAlimento() {
        System.out.println("\n🔄 [Sensor: " + ubicacion + "] Recargando alimento...");
        actualizarNivel(100);
    }

    // Getters
    public String getUbicacion() {
        return ubicacion;
    }

    public int getNivel() {
        return nivelActual;
    }

    public int getNivelMinimo() {
        return nivelMinimo;
    }
}
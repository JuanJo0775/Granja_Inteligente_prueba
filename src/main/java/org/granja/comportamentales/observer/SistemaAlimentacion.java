package org.granja.comportamentales.observer;


/**
 * Observador concreto: Sistema de Alimentación
 * Reacciona cuando el nivel de alimento es bajo activando dispensadores
 */
public class SistemaAlimentacion implements Observer {
    private String nombre;

    public SistemaAlimentacion(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(Subject subject) {
        if (subject instanceof SensorNivelAlimento) {
            SensorNivelAlimento sensor = (SensorNivelAlimento) subject;

            if (sensor.getNivel() < sensor.getNivelMinimo()) {
                System.out.println("\n🔔 [" + nombre + "] ALERTA: Nivel bajo de alimento!");
                System.out.println("   📊 Nivel actual: " + sensor.getNivel() + "%");
                System.out.println("   📍 Ubicación: " + sensor.getUbicacion());
                System.out.println("   🎯 Acción: Activando dispensador automático...");
            }
        }
    }
}
package org.granja.comportamentales.observer;

/**
 * Observador concreto: Sistema de Alertas
 * Envía notificaciones al personal responsable
 */
public class SistemaAlertas implements Observer {
    private String responsable;

    public SistemaAlertas(String responsable) {
        this.responsable = responsable;
    }

    @Override
    public void actualizar(Subject subject) {
        if (subject instanceof SensorNivelAlimento) {
            SensorNivelAlimento sensor = (SensorNivelAlimento) subject;

            if (sensor.getNivel() < sensor.getNivelMinimo()) {
                System.out.println("\n📧 [Sistema de Alertas] Notificación enviada");
                System.out.println("   👤 Destinatario: " + responsable);
                System.out.println("   📱 Mensaje: Nivel crítico en " + sensor.getUbicacion());
            }
        }
    }
}
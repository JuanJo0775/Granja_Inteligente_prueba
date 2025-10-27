package org.granja.estructurales.adapter;

/**
 * Patrón Adapter:
 * Adapta la interfaz del sensor antiguo (LegacySensor)
 * a la nueva interfaz Sensor que usa datos numéricos.
 */
public class SensorAdapter implements Sensor {

    private final LegacySensor legacySensor;

    public SensorAdapter() {
        this.legacySensor = new LegacySensor();
    }

    @Override
    public double getLectura() {
        String dato = legacySensor.leerDato();   // "Nivel:30"
        dato = dato.replace("Nivel:", "");       // "30"
        try {
            return Double.parseDouble(dato.trim());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Error al interpretar lectura del sensor: " + dato);
            return 0.0;
        }
    }
}

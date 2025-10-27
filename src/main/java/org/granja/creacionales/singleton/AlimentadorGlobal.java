package org.granja.creacionales.singleton;

/**
 * Clase Singleton que representa el alimentador global de la granja.
 */
public class AlimentadorGlobal {

    private static AlimentadorGlobal instancia;
    private double cantidadAlimento;

    private AlimentadorGlobal() {
        this.cantidadAlimento = 1000; // valor inicial en kg
    }

    public static synchronized AlimentadorGlobal getInstancia() {
        if (instancia == null) {
            instancia = new AlimentadorGlobal();
        }
        return instancia;
    }

    public void dispensarAlimento(double cantidad) {
        if (cantidadAlimento >= cantidad) {
            cantidadAlimento -= cantidad;
            System.out.println("🪣 Se dispensaron " + cantidad + " kg de alimento. Restan " + cantidadAlimento + " kg.");
        } else {
            System.out.println("⚠️ No hay suficiente alimento en el dispensador.");
        }
    }

    public void mostrarEstado() {
        System.out.println("📊 Estado actual del alimentador global: " + cantidadAlimento + " kg disponibles.");
    }

    public void rellenar(double cantidad) {
        cantidadAlimento += cantidad;
        System.out.println("✅ Se añadieron " + cantidad + " kg al alimentador. Total: " + cantidadAlimento + " kg.");
    }
}

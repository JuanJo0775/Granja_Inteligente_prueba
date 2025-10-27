package org.granja.comportamentales.strategy;

/**
 * Estrategia de ahorro
 * Optimiza recursos manteniendo nutrición adecuada
 */
public class EstrategiaAhorro implements EstrategiaAlimentacion {

    @Override
    public double calcularCantidad(double cantidadBase) {
        // Reduce 20% optimizando desperdicio
        return cantidadBase * 0.8;
    }

    @Override
    public int calcularFrecuencia(int frecuenciaBase) {
        // Aumenta el tiempo entre comidas
        return frecuenciaBase + 1;
    }

    @Override
    public String getDescripcion() {
        return "💰 AHORRO: Cantidad optimizada y menor frecuencia (reducción de desperdicio)";
    }
}
package org.granja.comportamentales.strategy;

/**
 * Estrategia para época de invierno
 * Aumenta la cantidad de alimento debido al mayor gasto energético por el frío
 */
public class EstrategiaInvierno implements EstrategiaAlimentacion {

    @Override
    public double calcularCantidad(double cantidadBase) {
        // Aumenta 30% la cantidad por el frío
        return cantidadBase * 1.3;
    }

    @Override
    public int calcularFrecuencia(int frecuenciaBase) {
        // Reduce el tiempo entre comidas (más frecuente)
        return Math.max(2, frecuenciaBase - 1);
    }

    @Override
    public String getDescripcion() {
        return "❄️  INVIERNO: Más alimento y mayor frecuencia (animales necesitan más energía)";
    }
}
package org.granja.comportamentales.strategy;

/**
 * Estrategia para época de verano
 * Reduce ligeramente la cantidad por menor gasto energético
 */
public class EstrategiaVerano implements EstrategiaAlimentacion {

    @Override
    public double calcularCantidad(double cantidadBase) {
        // Reduce 10% la cantidad por menor necesidad energética
        return cantidadBase * 0.9;
    }

    @Override
    public int calcularFrecuencia(int frecuenciaBase) {
        // Mantiene frecuencia base
        return frecuenciaBase;
    }

    @Override
    public String getDescripcion() {
        return "☀️  VERANO: Cantidad moderada y frecuencia normal";
    }
}
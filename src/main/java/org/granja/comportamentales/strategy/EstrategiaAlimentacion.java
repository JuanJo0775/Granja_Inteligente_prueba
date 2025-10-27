package org.granja.comportamentales.strategy;

/**
 * Interfaz Strategy para diferentes estrategias de alimentación
 */
public interface EstrategiaAlimentacion {
    /**
     * Calcula la cantidad de alimento a dispensar
     * @param cantidadBase cantidad base de alimento en kg
     * @return cantidad ajustada según la estrategia
     */
    double calcularCantidad(double cantidadBase);

    /**
     * Calcula la frecuencia de alimentación
     * @param frecuenciaBase frecuencia base en horas
     * @return frecuencia ajustada según la estrategia
     */
    int calcularFrecuencia(int frecuenciaBase);

    /**
     * Obtiene descripción de la estrategia
     */
    String getDescripcion();
}
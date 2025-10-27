package org.granja.comportamentales.state;

/**
 * Interfaz State del patrón State
 * Define el comportamiento de cada estado de salud del animal
 */
public interface EstadoSalud {
    /**
     * Comportamiento al alimentar al animal
     */
    void alimentar(Animal animal);

    /**
     * Comportamiento al realizar actividad física
     */
    void realizarActividad(Animal animal);

    /**
     * Comportamiento al aplicar tratamiento
     */
    void aplicarTratamiento(Animal animal);

    /**
     * Obtiene el nombre del estado
     */
    String getNombreEstado();

    /**
     * Obtiene descripción del estado
     */
    String getDescripcion();
}

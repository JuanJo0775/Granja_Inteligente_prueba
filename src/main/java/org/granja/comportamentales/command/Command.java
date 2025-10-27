package org.granja.comportamentales.command;

/**
 * Interfaz Command del patrón Command
 * Define la estructura de todos los comandos
 */
public interface Command {
    /**
     * Ejecuta el comando
     */
    void ejecutar();

    /**
     * Deshace el comando (opcional, no todos los comandos lo implementan)
     */
    void deshacer();

    /**
     * Obtiene descripción del comando
     */
    String getDescripcion();
}

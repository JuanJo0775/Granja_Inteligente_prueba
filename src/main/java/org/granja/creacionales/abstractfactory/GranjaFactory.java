package org.granja.creacionales.abstractfactory;

/**
 * Interfaz Abstract Factory para crear familias de objetos relacionados.
 */
public interface GranjaFactory {
    Raza crearRaza();
    Alimento crearAlimento();
    Entorno crearEntorno();
}

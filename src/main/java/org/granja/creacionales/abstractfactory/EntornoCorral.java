package org.granja.creacionales.abstractfactory;

/**
 * Entorno de corral cerrado para cerdos.
 */
public class EntornoCorral implements Entorno {
    @Override
    public void mostrarEntorno() {
        System.out.println("🏚 Entorno: corral cerrado.");
    }
}

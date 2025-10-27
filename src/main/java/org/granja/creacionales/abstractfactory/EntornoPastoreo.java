package org.granja.creacionales.abstractfactory;

/**
 * Entorno para vacas en campo abierto.
 */
public class EntornoPastoreo implements Entorno {
    @Override
    public void mostrarEntorno() {
        System.out.println("🌾 Entorno: campo abierto para pastoreo.");
    }
}

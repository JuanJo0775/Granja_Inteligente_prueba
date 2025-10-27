package org.granja.creacionales.abstractfactory;

/**
 * Entorno para gallinas ponedoras.
 */
public class EntornoGallinero implements Entorno {
    @Override
    public void mostrarEntorno() {
        System.out.println("🏠 Entorno: gallinero cerrado.");
    }
}

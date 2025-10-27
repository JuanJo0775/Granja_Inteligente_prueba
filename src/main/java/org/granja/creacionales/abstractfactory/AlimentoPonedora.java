package org.granja.creacionales.abstractfactory;

/**
 * Alimento especializado para gallinas ponedoras.
 */
public class AlimentoPonedora implements Alimento {
    @Override
    public void mostrarAlimento() {
        System.out.println("🥚 Alimento balanceado para producción de huevos.");
    }
}

package org.granja.creacionales.abstractfactory;

/**
 * Alimento específico para vacas lecheras.
 */
public class AlimentoLechero implements Alimento {
    @Override
    public void mostrarAlimento() {
        System.out.println("🥛 Alimento balanceado para producción de leche.");
    }
}

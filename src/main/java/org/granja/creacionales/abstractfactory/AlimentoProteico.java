package org.granja.creacionales.abstractfactory;

/**
 * Alimento para cerdos de engorde.
 */
public class AlimentoProteico implements Alimento {
    @Override
    public void mostrarAlimento() {
        System.out.println("🍽️ Alimento rico en proteínas para engorde.");
    }
}

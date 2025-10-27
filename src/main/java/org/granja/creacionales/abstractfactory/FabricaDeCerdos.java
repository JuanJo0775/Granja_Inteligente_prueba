package org.granja.creacionales.abstractfactory;

/**
 * Fábrica concreta que crea familias de objetos para cerdos.
 */
public class FabricaDeCerdos implements GranjaFactory {
    @Override
    public Raza crearRaza() {
        return new RazaDuroc();
    }

    @Override
    public Alimento crearAlimento() {
        return new AlimentoProteico();
    }

    @Override
    public Entorno crearEntorno() {
        return new EntornoCorral();
    }
}

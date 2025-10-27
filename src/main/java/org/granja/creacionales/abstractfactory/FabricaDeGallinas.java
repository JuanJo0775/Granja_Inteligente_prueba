package org.granja.creacionales.abstractfactory;

/**
 * Fábrica concreta para crear gallinas con su entorno, raza y alimento.
 */
public class FabricaDeGallinas implements GranjaFactory {
    @Override
    public Raza crearRaza() {
        return new RazaIsaBrown();
    }

    @Override
    public Alimento crearAlimento() {
        return new AlimentoPonedora();
    }

    @Override
    public Entorno crearEntorno() {
        return new EntornoGallinero();
    }
}

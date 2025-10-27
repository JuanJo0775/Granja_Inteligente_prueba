package org.granja.creacionales.abstractfactory;

/**
 * Fábrica concreta que crea una familia de objetos para vacas.
 */
public class FabricaDeVacas implements GranjaFactory {

    @Override
    public Raza crearRaza() {
        return new RazaHolstein();
    }

    @Override
    public Alimento crearAlimento() {
        return new AlimentoLechero();
    }

    @Override
    public Entorno crearEntorno() {
        return new EntornoPastoreo();
    }
}

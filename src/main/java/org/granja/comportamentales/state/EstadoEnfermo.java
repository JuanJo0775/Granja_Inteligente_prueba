package org.granja.comportamentales.state;

/**
 * Estado concreto: Animal Enfermo
 * Define el comportamiento cuando el animal tiene síntomas de enfermedad
 */
public class EstadoEnfermo implements EstadoSalud {

    @Override
    public void alimentar(Animal animal) {
        System.out.println("   🍽️  Alimentando animal enfermo");
        System.out.println("   ⚠️  Consumo reducido de alimento (50%)");
        System.out.println("   😔 El animal tiene poco apetito");
    }

    @Override
    public void realizarActividad(Animal animal) {
        System.out.println("   ⛔ El animal no debe realizar actividades");
        System.out.println("   😴 Reposo necesario para recuperación");
        System.out.println("   ⚠️  El estado del animal empeoró ligeramente");
    }

    @Override
    public void aplicarTratamiento(Animal animal) {
        System.out.println("   💉 Aplicando tratamiento médico");
        System.out.println("   🏥 Medicamento suministrado");
        System.out.println("   🔄 Cambiando a estado EN TRATAMIENTO");
        animal.setEstado(new EstadoEnTratamiento());
    }

    @Override
    public String getNombreEstado() {
        return "ENFERMO";
    }

    @Override
    public String getDescripcion() {
        return "⚠️  Animal con síntomas de enfermedad";
    }
}

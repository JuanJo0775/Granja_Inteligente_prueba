package org.granja.comportamentales.state;

/**
 * Estado concreto: Animal Sano
 * Define el comportamiento cuando el animal está en perfecto estado de salud
 */
public class EstadoSano implements EstadoSalud {

    @Override
    public void alimentar(Animal animal) {
        System.out.println("   🍽️  Alimentando animal sano");
        System.out.println("   ✅ Consumo normal de alimento (100%)");
        System.out.println("   💪 El animal está activo y saludable");
    }

    @Override
    public void realizarActividad(Animal animal) {
        System.out.println("   🏃 Animal realizando actividad normal");
        System.out.println("   ✅ Nivel de energía óptimo");

        // Pequeña posibilidad de enfermar por actividad intensa
        if (Math.random() < 0.1) { // 10% de probabilidad
            System.out.println("   ⚠️  El animal mostró signos de fatiga");
            System.out.println("   🔄 Cambiando a estado ENFERMO");
            animal.setEstado(new EstadoEnfermo());
        }
    }

    @Override
    public void aplicarTratamiento(Animal animal) {
        System.out.println("   ℹ️  El animal está sano, no necesita tratamiento");
        System.out.println("   ✅ Aplicando vitaminas preventivas");
    }

    @Override
    public String getNombreEstado() {
        return "SANO";
    }

    @Override
    public String getDescripcion() {
        return "✅ Animal en perfecto estado de salud";
    }
}
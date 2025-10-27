package org.granja.comportamentales.state;

/**
 * Estado concreto: Animal en Tratamiento
 * Define el comportamiento durante el proceso de recuperación
 */
public class EstadoEnTratamiento implements EstadoSalud {
    private int diasTratamiento;

    public EstadoEnTratamiento() {
        this.diasTratamiento = 0;
    }

    @Override
    public void alimentar(Animal animal) {
        System.out.println("   🍽️  Alimentando animal en tratamiento");
        System.out.println("   📊 Consumo moderado de alimento (75%)");
        System.out.println("   💊 Alimento especial con medicación");
        diasTratamiento++;
    }

    @Override
    public void realizarActividad(Animal animal) {
        System.out.println("   🚶 Actividad ligera permitida");
        System.out.println("   ⚕️  Ejercicio terapéutico bajo supervisión");
    }

    @Override
    public void aplicarTratamiento(Animal animal) {
        System.out.println("   💉 Continuando tratamiento médico");
        diasTratamiento++;

        // Después de 3 días de tratamiento, el animal se recupera
        if (diasTratamiento >= 3) {
            System.out.println("   ✅ Tratamiento completado exitosamente");
            System.out.println("   🎉 El animal se ha recuperado completamente");
            System.out.println("   🔄 Cambiando a estado SANO");
            animal.setEstado(new EstadoSano());
        } else {
            System.out.println("   ⏳ Progreso del tratamiento: día " + diasTratamiento + "/3");
        }
    }

    @Override
    public String getNombreEstado() {
        return "EN TRATAMIENTO";
    }

    @Override
    public String getDescripcion() {
        return "🏥 Animal bajo tratamiento médico (día " + diasTratamiento + ")";
    }
}

package org.granja.comportamentales.strategy;

/**
 * Contexto del patrón Strategy.
 * Gestiona la alimentación aplicando diferentes estrategias en tiempo de ejecución.
 */
public class GestorAlimentacion {

    private EstrategiaAlimentacion estrategia;
    private final String nombreCorral;

    public GestorAlimentacion(String nombreCorral) {
        this.nombreCorral = nombreCorral;
        // Estrategia por defecto
        this.estrategia = new EstrategiaVerano();
    }

    /**
     * Cambia la estrategia de alimentación en tiempo de ejecución.
     */
    public void setEstrategia(EstrategiaAlimentacion estrategia) {
        this.estrategia = estrategia;
        System.out.println("\n🔄 [" + nombreCorral + "] Estrategia cambiada:");
        System.out.println("   " + estrategia.getDescripcion());
    }

    /**
     * Ejecuta la alimentación con la estrategia actual.
     */
    public void alimentar(double cantidadBase, int frecuenciaBase) {
        System.out.println("\n🍽️  [" + nombreCorral + "] Ejecutando alimentación...");
        System.out.println("   📋 Estrategia: " + estrategia.getDescripcion());
        System.out.println("   📊 Parámetros base: " + cantidadBase + "kg cada " + frecuenciaBase + "h");

        double cantidadFinal = estrategia.calcularCantidad(cantidadBase);
        int frecuenciaFinal = estrategia.calcularFrecuencia(frecuenciaBase);

        System.out.println("   ✅ Cantidad ajustada: " + String.format("%.2f", cantidadFinal) + "kg");
        System.out.println("   ✅ Frecuencia ajustada: cada " + frecuenciaFinal + "h");
        System.out.println("   🎯 Dispensando alimento...");
    }

    /**
     * Método auxiliar para cambiar estrategia según estación (simplifica el uso).
     */
    public void cambiarEstacion(String estacion) {
        if (estacion == null) {
            System.out.println("⚠️  Estación no válida (nula).");
            return;
        }

        switch (estacion.toLowerCase()) {
            case "invierno" -> setEstrategia(new EstrategiaInvierno());
            case "verano" -> setEstrategia(new EstrategiaVerano());
            case "ahorro" -> setEstrategia(new EstrategiaAhorro());
            default -> System.out.println("⚠️  Estación no reconocida. Manteniendo estrategia actual.");
        }
    }

    public EstrategiaAlimentacion getEstrategia() {
        return estrategia;
    }
}

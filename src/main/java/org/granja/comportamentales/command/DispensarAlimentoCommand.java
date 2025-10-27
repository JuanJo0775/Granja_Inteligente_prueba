package org.granja.comportamentales.command;

/**
 * Comando concreto: Dispensar Alimento
 * Encapsula la acción de dispensar alimento en una ubicación específica
 */
public class DispensarAlimentoCommand implements Command {
    private String ubicacion;
    private double cantidad;
    private boolean ejecutado;

    public DispensarAlimentoCommand(String ubicacion, double cantidad) {
        this.ubicacion = ubicacion;
        this.cantidad = cantidad;
        this.ejecutado = false;
    }

    @Override
    public void ejecutar() {
        System.out.println("\n🔵 EJECUTANDO: " + getDescripcion());
        System.out.println("   📍 Ubicación: " + ubicacion);
        System.out.println("   📦 Cantidad: " + cantidad + "kg");
        System.out.println("   ✅ Alimento dispensado correctamente");
        ejecutado = true;
    }

    @Override
    public void deshacer() {
        if (ejecutado) {
            System.out.println("\n🔴 DESHACIENDO: " + getDescripcion());
            System.out.println("   ⚠️  Retirando alimento de " + ubicacion);
            System.out.println("   ✅ Operación revertida");
            ejecutado = false;
        } else {
            System.out.println("   ⚠️  No se puede deshacer: comando no ejecutado");
        }
    }

    @Override
    public String getDescripcion() {
        return "Dispensar " + cantidad + "kg de alimento en " + ubicacion;
    }
}

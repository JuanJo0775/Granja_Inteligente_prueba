package org.granja.comportamentales.command;

/**
 * Comando concreto: Encender Riego
 * Encapsula la acción de activar el sistema de riego
 */
public class EncenderRiegoCommand implements Command {
    private String zona;
    private int duracionMinutos;
    private boolean activo;

    public EncenderRiegoCommand(String zona, int duracionMinutos) {
        this.zona = zona;
        this.duracionMinutos = duracionMinutos;
        this.activo = false;
    }

    @Override
    public void ejecutar() {
        System.out.println("\n🔵 EJECUTANDO: " + getDescripcion());
        System.out.println("   🌱 Zona: " + zona);
        System.out.println("   ⏱️  Duración: " + duracionMinutos + " minutos");
        System.out.println("   💧 Sistema de riego activado");
        activo = true;
    }

    @Override
    public void deshacer() {
        if (activo) {
            System.out.println("\n🔴 DESHACIENDO: " + getDescripcion());
            System.out.println("   ⚠️  Apagando riego en " + zona);
            System.out.println("   ✅ Sistema de riego desactivado");
            activo = false;
        } else {
            System.out.println("   ⚠️  No se puede deshacer: comando no ejecutado");
        }
    }

    @Override
    public String getDescripcion() {
        return "Encender riego en " + zona + " por " + duracionMinutos + " minutos";
    }
}
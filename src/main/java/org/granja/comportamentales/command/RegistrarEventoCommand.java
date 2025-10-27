package org.granja.comportamentales.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Comando concreto: Registrar Evento
 * Encapsula la acción de registrar eventos en el sistema
 */
public class RegistrarEventoCommand implements Command {
    private String evento;
    private String timestamp;

    public RegistrarEventoCommand(String evento) {
        this.evento = evento;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timestamp = LocalDateTime.now().format(formatter);
    }

    @Override
    public void ejecutar() {
        System.out.println("\n🔵 EJECUTANDO: " + getDescripcion());
        System.out.println("   📅 Timestamp: " + timestamp);
        System.out.println("   📝 Evento: " + evento);
        System.out.println("   ✅ Evento registrado en el sistema");
    }

    @Override
    public void deshacer() {
        System.out.println("\n⚠️  Los eventos registrados no se pueden deshacer");
        System.out.println("   (Se mantiene el historial por auditoría)");
    }

    @Override
    public String getDescripcion() {
        return "Registrar evento: " + evento;
    }
}

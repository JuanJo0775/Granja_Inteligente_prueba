package org.granja.comportamentales.command;

import java.util.*;

/**
 * Invoker del patrón Command
 * Gestiona la ejecución de comandos, cola y historial
 */
public class GestorComandos {
    private Queue<Command> colaComandos;
    private Stack<Command> historial;
    private List<Command> comandosEjecutados;

    public GestorComandos() {
        this.colaComandos = new LinkedList<>();
        this.historial = new Stack<>();
        this.comandosEjecutados = new ArrayList<>();
    }

    /**
     * Agrega un comando a la cola
     */
    public void agregarComando(Command comando) {
        colaComandos.offer(comando);
        System.out.println("➕ Comando agregado a la cola: " + comando.getDescripcion());
    }

    /**
     * Ejecuta todos los comandos en la cola
     */
    public void ejecutarCola() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🚀 EJECUTANDO COLA DE COMANDOS");
        System.out.println("=".repeat(60));

        if (colaComandos.isEmpty()) {
            System.out.println("   ℹ️  La cola está vacía");
            return;
        }

        int contador = 1;
        while (!colaComandos.isEmpty()) {
            Command comando = colaComandos.poll();
            System.out.println("\n--- Comando #" + contador + " ---");
            comando.ejecutar();
            historial.push(comando);
            comandosEjecutados.add(comando);
            contador++;
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("✅ Cola de comandos ejecutada completamente");
        System.out.println("=".repeat(60));
    }

    /**
     * Ejecuta un comando inmediatamente (sin pasar por la cola)
     */
    public void ejecutarInmediato(Command comando) {
        System.out.println("\n⚡ EJECUCIÓN INMEDIATA");
        comando.ejecutar();
        historial.push(comando);
        comandosEjecutados.add(comando);
    }

    /**
     * Deshace el último comando ejecutado
     */
    public void deshacerUltimo() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("↩️  DESHACER ÚLTIMO COMANDO");
        System.out.println("=".repeat(60));

        if (historial.isEmpty()) {
            System.out.println("   ℹ️  No hay comandos para deshacer");
            return;
        }

        Command ultimoComando = historial.pop();
        ultimoComando.deshacer();

        System.out.println("=".repeat(60));
    }

    /**
     * Muestra el historial de comandos ejecutados
     */
    public void mostrarHistorial() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📋 HISTORIAL DE COMANDOS");
        System.out.println("=".repeat(60));

        if (comandosEjecutados.isEmpty()) {
            System.out.println("   ℹ️  No hay comandos en el historial");
            return;
        }

        for (int i = 0; i < comandosEjecutados.size(); i++) {
            System.out.println((i + 1) + ". " + comandosEjecutados.get(i).getDescripcion());
        }

        System.out.println("=".repeat(60));
    }

    /**
     * Muestra los comandos pendientes en la cola
     */
    public void mostrarCola() {
        System.out.println("\n📝 Comandos en cola: " + colaComandos.size());
        int i = 1;
        for (Command cmd : colaComandos) {
            System.out.println("   " + i + ". " + cmd.getDescripcion());
            i++;
        }
    }

    /**
     * Limpia la cola de comandos
     */
    public void limpiarCola() {
        colaComandos.clear();
        System.out.println("🗑️  Cola de comandos limpiada");
    }
}
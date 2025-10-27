package org.granja.comportamentales;

import org.granja.comportamentales.command.*;
import org.granja.comportamentales.observer.Observer;
import org.granja.comportamentales.observer.SensorNivelAlimento;
import org.granja.comportamentales.observer.SistemaAlertas;
import org.granja.comportamentales.observer.SistemaAlimentacion;
import org.granja.comportamentales.state.Animal;
import org.granja.comportamentales.state.EstadoEnfermo;
import org.granja.comportamentales.strategy.GestorAlimentacion;

/**
 * Clase de demostración que integra todos los patrones comportamentales
 * Ejecuta pruebas individuales y una demo de integración completa
 */
public class DemoPatronesComportamentales {

    public static void main(String[] args) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("🌾 GRANJA INTELIGENTE - DEMO DE PATRONES COMPORTAMENTALES 🌾");
        System.out.println("=".repeat(70));

        // ===== 1. PATRÓN OBSERVER =====
        demoObserver();

        esperarTecla();

        // ===== 2. PATRÓN STRATEGY =====
        demoStrategy();

        esperarTecla();

        // ===== 3. PATRÓN COMMAND =====
        demoCommand();

        esperarTecla();

        // ===== 4. PATRÓN STATE =====
        demoState();

        esperarTecla();

        // ===== 5. INTEGRACIÓN DE TODOS LOS PATRONES =====
        demoIntegracion();

        System.out.println("\n" + "=".repeat(70));
        System.out.println("✅ DEMOSTRACIÓN COMPLETADA");
        System.out.println("=".repeat(70));
    }

    /**
     * Demostración del patrón Observer
     */
    private static void demoObserver() {
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("1️⃣  PATRÓN OBSERVER - Sistema de Sensores y Notificaciones");
        System.out.println("=".repeat(70));

        // Crear sensor
        SensorNivelAlimento sensor = new SensorNivelAlimento("Corral de Cerdos", 30);

        // Crear observadores
        Observer sistemaAlimentacion = new SistemaAlimentacion("Sistema Central");
        Observer sistemaAlertas = new SistemaAlertas("Juan Pérez - Encargado");

        // Registrar observadores
        sensor.agregarObservador(sistemaAlimentacion);
        sensor.agregarObservador(sistemaAlertas);

        // Simular consumo de alimento
        System.out.println("\n--- Simulación de consumo gradual ---");
        sensor.consumirAlimento(20); // 80%
        sensor.consumirAlimento(30); // 50%
        sensor.consumirAlimento(25); // 25% - ¡Alerta!

        // Recargar alimento
        sensor.recargarAlimento();
    }

    /**
     * Demostración del patrón Strategy
     */
    private static void demoStrategy() {
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("2️⃣  PATRÓN STRATEGY - Estrategias de Alimentación");
        System.out.println("=".repeat(70));

        GestorAlimentacion gestor = new GestorAlimentacion("Corral de Vacas");

        // Parámetros base
        double cantidadBase = 50.0; // kg
        int frecuenciaBase = 8; // horas

        System.out.println("\n--- Probando diferentes estrategias ---");

        // Estrategia de Invierno
        gestor.cambiarEstacion("invierno");
        gestor.alimentar(cantidadBase, frecuenciaBase);

        // Estrategia de Verano
        gestor.cambiarEstacion("verano");
        gestor.alimentar(cantidadBase, frecuenciaBase);

        // Estrategia de Ahorro
        gestor.cambiarEstacion("ahorro");
        gestor.alimentar(cantidadBase, frecuenciaBase);
    }

    /**
     * Demostración del patrón Command
     */
    private static void demoCommand() {
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("3️⃣  PATRÓN COMMAND - Cola de Comandos y Deshacer");
        System.out.println("=".repeat(70));

        GestorComandos gestor = new GestorComandos();

        System.out.println("\n--- Agregando comandos a la cola ---");

        // Crear comandos
        Command cmd1 = new DispensarAlimentoCommand("Corral A", 30.5);
        Command cmd2 = new EncenderRiegoCommand("Zona Norte", 15);
        Command cmd3 = new RegistrarEventoCommand("Inicio de jornada matutina");
        Command cmd4 = new DispensarAlimentoCommand("Corral B", 25.0);

        // Agregar comandos a la cola
        gestor.agregarComando(cmd1);
        gestor.agregarComando(cmd2);
        gestor.agregarComando(cmd3);
        gestor.agregarComando(cmd4);

        gestor.mostrarCola();

        // Ejecutar todos los comandos
        gestor.ejecutarCola();

        // Mostrar historial
        gestor.mostrarHistorial();

        // Deshacer último comando
        gestor.deshacerUltimo();

        // Ejecutar comando inmediato
        Command cmdInmediato = new EncenderRiegoCommand("Zona Sur", 10);
        gestor.ejecutarInmediato(cmdInmediato);
    }

    /**
     * Demostración del patrón State
     */
    private static void demoState() {
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("4️⃣  PATRÓN STATE - Estados de Salud del Animal");
        System.out.println("=".repeat(70));

        Animal vaca = new Animal("Lola", "Vaca", 5, 450.0);

        vaca.mostrarInfo();

        System.out.println("\n--- Día 1: Animal sano ---");
        vaca.alimentar();
        vaca.realizarActividad();

        System.out.println("\n--- El animal se enferma ---");
        vaca.setEstado(new EstadoEnfermo());
        vaca.alimentar();
        vaca.realizarActividad();

        System.out.println("\n--- Aplicando tratamiento ---");
        vaca.aplicarTratamiento(); // Pasa a EN TRATAMIENTO

        System.out.println("\n--- Día 2: En tratamiento ---");
        vaca.alimentar();
        vaca.aplicarTratamiento();

        System.out.println("\n--- Día 3: Completando tratamiento ---");
        vaca.aplicarTratamiento(); // Se recupera, pasa a SANO

        vaca.mostrarInfo();
    }

    /**
     * Demostración de integración de todos los patrones
     */
    private static void demoIntegracion() {
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("5️⃣  INTEGRACIÓN - Todos los Patrones Trabajando Juntos");
        System.out.println("=".repeat(70));

        System.out.println("\n🌅 ESCENARIO: Rutina matutina automatizada en la granja");

        // 1. OBSERVER: Sensor detecta nivel bajo
        System.out.println("\n--- 1. Sistema de Monitoreo (Observer) ---");
        SensorNivelAlimento sensor = new SensorNivelAlimento("Corral Principal", 35);
        Observer alerta = new SistemaAlertas("Sistema Central");
        sensor.agregarObservador(alerta);
        sensor.actualizarNivel(25); // Dispara alerta

        // 2. STRATEGY: Seleccionar estrategia según temporada
        System.out.println("\n--- 2. Selección de Estrategia (Strategy) ---");
        GestorAlimentacion gestor = new GestorAlimentacion("Corral Principal");
        gestor.cambiarEstacion("invierno");

        // 3. COMMAND: Crear y ejecutar comandos
        System.out.println("\n--- 3. Cola de Acciones (Command) ---");
        GestorComandos gestorCmd = new GestorComandos();
        gestorCmd.agregarComando(new RegistrarEventoCommand("Alerta de nivel bajo detectada"));
        gestorCmd.agregarComando(new DispensarAlimentoCommand("Corral Principal", 45.0));
        gestorCmd.agregarComando(new EncenderRiegoCommand("Zona de Pastoreo", 20));
        gestorCmd.ejecutarCola();

        // 4. STATE: Verificar estado de salud de animales
        System.out.println("\n--- 4. Chequeo de Salud Animal (State) ---");
        Animal animal1 = new Animal("Margarita", "Vaca", 4, 480.0);
        Animal animal2 = new Animal("Pepito", "Cerdo", 2, 180.0);

        animal1.alimentar();
        animal2.setEstado(new EstadoEnfermo());
        animal2.aplicarTratamiento();

        System.out.println("\n✅ Rutina matutina completada exitosamente");
    }

    /**
     * Método auxiliar para pausar entre demostraciones
     */
    private static void esperarTecla() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("Presiona ENTER para continuar...");
        System.out.println("-".repeat(70));
        try {
            System.in.read();
            // Limpiar buffer
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (Exception e) {
            // Ignorar excepciones
        }
    }
}

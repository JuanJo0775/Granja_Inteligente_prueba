package org.granja.integracion;

import org.granja.comportamentales.command.*;
import org.granja.comportamentales.observer.SensorNivelAlimento;
import org.granja.comportamentales.observer.SistemaAlertas;
import org.granja.comportamentales.observer.SistemaAlimentacion;
import org.granja.comportamentales.strategy.GestorAlimentacion;
import org.granja.comportamentales.state.Animal;
import org.granja.creacionales.factorymethod.*;
import org.granja.creacionales.singleton.AlimentadorGlobal;
import org.granja.estructurales.adapter.SensorAdapter;
import org.granja.estructurales.facade.SistemaGPSFacade;
import org.granja.estructurales.facade.SistemaSensoresFacade;

import java.util.*;

/**
 * Fachada que unifica subsistemas de la granja y provee operaciones simples.
 */
public class GranjaFacade {

    private final SistemaSensoresFacade sensoresFacade;
    private final SistemaGPSFacade gpsFacade;
    private final GestorAlimentacion gestorAlimentacion;
    private final GestorComandos gestorComandos;
    private final AlimentadorGlobal alimentadorGlobal;

    // Registro simple de animales (usamos la clase state.Animal para simulación de estados)
    private final List<Animal> animales = new ArrayList<>();
    // Historial de eventos (adicional al historial de comandos)
    private final List<String> historialEventos = new ArrayList<>();

    // Sensores / Observers de ejemplo
    private final SensorNivelAlimento sensorNivel;

    public GranjaFacade() {
        // Construimos adaptador de sensor legacy -> sensor moderno y la fachada de sensores
        SensorAdapter adapter = new SensorAdapter();
        this.sensoresFacade = new SistemaSensoresFacade(adapter);
        this.gpsFacade = new SistemaGPSFacade(sensoresFacade);
        this.gestorAlimentacion = new GestorAlimentacion("Corral Principal");
        this.gestorComandos = new GestorComandos();
        this.alimentadorGlobal = AlimentadorGlobal.getInstancia();

        // Sensor de ejemplo y observadores
        this.sensorNivel = new SensorNivelAlimento("Corral Principal", 30);
        this.sensorNivel.agregarObservador(new SistemaAlimentacion("Sistema Central"));
        this.sensorNivel.agregarObservador(new SistemaAlertas("Encargado"));
    }

    // -------------------------
    // Operaciones expuestas
    // -------------------------

    public void crearAnimal(String nombre, String tipo, int edad, double peso) {
        Animal nuevo = new Animal(nombre, tipo, edad, peso);
        animales.add(nuevo);
        String msg = "🐾 Animal creado: " + nombre + " (" + tipo + ")";
        historialEventos.add(msg);
        System.out.println(msg);
    }

    public void monitorearAnimal(String identificador) {
        Animal a = buscarAnimalPorNombreOIndice(identificador);
        if (a == null) {
            System.out.println("⚠️ Animal no encontrado: " + identificador);
            return;
        }

        // Creamos un animal "factorymethod" para usar el subsistema GPS/decorador que espera ese tipo
        org.granja.creacionales.factorymethod.Animal animalFactory = crearFactoryAnimalSegunTipo(a.getTipo());
        if (animalFactory == null) {
            System.out.println("⚠️ No hay fábrica para el tipo: " + a.getTipo());
            return;
        }

        // Usar fachada GPS para monitorear (internamente usa Sensor + Decorator)
        gpsFacade.monitorearAnimal(animalFactory);
        historialEventos.add("🔎 Monitorizado animal: " + a.getNombre());
    }

    public void aplicarEstrategia(String estacion) {
        gestorAlimentacion.cambiarEstacion(estacion);
        historialEventos.add("🧭 Estrategia cambiada a: " + estacion);
    }

    /**
     * Ejecuta un comando identificando el tipo (dispensar/riego/evento).
     * Si requiere más datos, los solicita por consola (simplificación pragmática).
     */
    public void ejecutarComando(String comandoTipo) {
        if (comandoTipo == null) {
            System.out.println("⚠️ Comando inválido (nulo).");
            return;
        }

        Scanner sc = new Scanner(System.in);
        switch (comandoTipo.toLowerCase()) {
            case "dispensar" -> {
                System.out.print("Ingrese ubicación (ej: Corral A): ");
                String ubic = sc.nextLine().trim();
                System.out.print("Ingrese cantidad (kg): ");
                double cantidad = leerDouble(sc);
                Command cmd = new DispensarAlimentoCommand(ubic, cantidad);
                gestorComandos.agregarComando(cmd);
                gestorComandos.ejecutarCola();
                // También actualizamos alimentador global
                alimentadorGlobal.dispensarAlimento(cantidad);
                historialEventos.add("🛠 Dispensado " + cantidad + "kg en " + ubic);
            }
            case "riego", "regadera", "regar" -> {
                System.out.print("Ingrese zona (ej: Zona Norte): ");
                String zona = sc.nextLine().trim();
                System.out.print("Ingrese duración (minutos): ");
                int dur = leerInt(sc);
                Command cmdR = new EncenderRiegoCommand(zona, dur);
                gestorComandos.ejecutarInmediato(cmdR);
                historialEventos.add("💧 Riego activado en " + zona + " por " + dur + " min");
            }
            case "evento" -> {
                System.out.print("Ingrese descripción del evento: ");
                String evt = sc.nextLine().trim();
                Command cmdE = new RegistrarEventoCommand(evt);
                gestorComandos.agregarComando(cmdE);
                gestorComandos.ejecutarCola();
                historialEventos.add("📝 Evento registrado: " + evt);
            }
            default -> System.out.println("⚠️ Tipo de comando no reconocido: " + comandoTipo);
        }
    }

    public void simularEstadoAnimal(String identificador) {
        Animal a = buscarAnimalPorNombreOIndice(identificador);
        if (a == null) {
            System.out.println("⚠️ Animal no encontrado: " + identificador);
            return;
        }
        System.out.println("\n📆 Simulando día para: " + a.getNombre());
        a.simularDia();
        historialEventos.add("⏳ Simulación día: " + a.getNombre());
    }

    public void mostrarEstadoSistema() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("📊 ESTADO DEL SISTEMA");
        System.out.println("-".repeat(50));
        alimentadorGlobal.mostrarEstado();
        System.out.println("Sensor de nivel (actual):");
        sensoresFacade.obtenerNivelAlimento();
        System.out.println("Estrategia actual: " + gestorAlimentacion.getEstrategia().getDescripcion());
        System.out.println("Animales registrados: " + animales.size());
        System.out.println("-".repeat(50));
    }

    public void mostrarAnimalesRegistrados() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🧾 ANIMALES REGISTRADOS");
        System.out.println("=" .repeat(50));
        if (animales.isEmpty()) {
            System.out.println("   ℹ️  No hay animales registrados.");
            return;
        }
        for (int i = 0; i < animales.size(); i++) {
            Animal a = animales.get(i);
            System.out.printf("   %d) %s - %s (Edad: %d, Peso: %.1fkg) Estado: %s%n",
                    i, a.getNombre(), a.getTipo(), a.getEdad(), a.getPeso(), a.getEstado().getNombreEstado());
        }
    }

    public void mostrarHistorialAcciones() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📜 HISTORIAL DE EVENTOS");
        System.out.println("=".repeat(50));
        if (historialEventos.isEmpty()) {
            System.out.println("   ℹ️  Sin eventos registrados.");
        } else {
            for (int i = 0; i < historialEventos.size(); i++) {
                System.out.println("   " + (i + 1) + ". " + historialEventos.get(i));
            }
        }
        System.out.println("\n📋 Historial de comandos:");
        gestorComandos.mostrarHistorial();
    }

    public void resumenGeneral() {
        System.out.println("\n" + "#".repeat(50));
        System.out.println("🔎 RESUMEN GENERAL");
        System.out.println("#".repeat(50));
        System.out.println("Total animales: " + animales.size());
        long enfermos = animales.stream().filter(a -> a.getEstado().getNombreEstado().equalsIgnoreCase("ENFERMO")).count();
        System.out.println("Animales enfermos: " + enfermos);
        alimentadorGlobal.mostrarEstado();
        System.out.println("Estrategia actual: " + gestorAlimentacion.getEstrategia().getDescripcion());
        System.out.println("Comandos en cola (aprox): (se muestra arriba en historial si aplica)");
    }

    public void limpiarSistema() {
        animales.clear();
        historialEventos.clear();
        gestorComandos.limpiarCola();
        // rellenamos alimentador a un valor base
        alimentadorGlobal.rellenar(1000.0);
        System.out.println("🧹 Sistema limpiado: animales, historial y cola reiniciados. Alimentador rellenado.");
    }

    // -------------
    // Helpers
    // -------------

    private Animal buscarAnimalPorNombreOIndice(String identificador) {
        if (identificador == null) return null;
        // intentar número
        try {
            int idx = Integer.parseInt(identificador);
            if (idx >= 0 && idx < animales.size()) return animales.get(idx);
        } catch (NumberFormatException ignored) {
        }
        // buscar por nombre (case-insensitive)
        for (Animal a : animales) {
            if (a.getNombre().equalsIgnoreCase(identificador)) return a;
        }
        return null;
    }

    private org.granja.creacionales.factorymethod.Animal crearFactoryAnimalSegunTipo(String tipo) {
        if (tipo == null) return null;
        switch (tipo.toLowerCase()) {
            case "vaca" -> {
                VacaFactory vf = new VacaFactory();
                return vf.crearAnimal();
            }
            case "cerdo" -> {
                CerdoFactory cf = new CerdoFactory();
                return cf.crearAnimal();
            }
            case "gallina", "gallinas" -> {
                GallinaFactory gf = new GallinaFactory();
                return gf.crearAnimal();
            }
            default -> {
                return null;
            }
        }
    }

    private static double leerDouble(Scanner sc) {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Intente nuevamente: ");
            }
        }
    }

    private static int leerInt(Scanner sc) {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Intente nuevamente: ");
            }
        }
    }
}

package org.granja.integracion;

import org.granja.creacionales.factorymethod.*;
import org.granja.creacionales.singleton.AlimentadorGlobal;
import org.granja.estructurales.adapter.SensorAdapter;
import org.granja.estructurales.facade.SistemaSensoresFacade;
import org.granja.estructurales.decorator.AnimalConGPS;
import org.granja.comportamentales.strategy.GestorAlimentacion;
import org.granja.comportamentales.command.*;
import org.granja.comportamentales.state.EstadoEnfermo;
import org.granja.comportamentales.state.EstadoEnTratamiento;

import java.util.ArrayList;
import java.util.List;

/**
 * Fachada general que unifica todos los patrones del sistema.
 * Integra Factory, Singleton, Adapter, Facade, Strategy, Command y State.
 */
public class GranjaFacade {

    private final AlimentadorGlobal alimentadorGlobal;
    private final SistemaSensoresFacade sensoresFacade;
    private final GestorAlimentacion gestorAlimentacion;
    private final GestorComandos gestorComandos;

    private final List<Animal> animalesCreados;
    private final List<String> historialAcciones;

    public GranjaFacade() {
        this.alimentadorGlobal = AlimentadorGlobal.getInstancia();
        this.sensoresFacade = new SistemaSensoresFacade(new SensorAdapter());
        this.gestorAlimentacion = new GestorAlimentacion("Corral Principal");
        this.gestorComandos = new GestorComandos();
        this.animalesCreados = new ArrayList<>();
        this.historialAcciones = new ArrayList<>();
    }

    // ======== CREACIONALES ========
    public Animal crearAnimal(String tipo) {
        AnimalFactory factory;

        switch (tipo.toLowerCase()) {
            case "vaca" -> factory = new VacaFactory();
            case "cerdo" -> factory = new CerdoFactory();
            case "gallina" -> factory = new GallinaFactory();
            default -> {
                System.out.println("❌ Tipo de animal no reconocido.");
                return null;
            }
        }

        Animal animal = factory.crearAnimal();
        animalesCreados.add(animal);
        historialAcciones.add("Creado: " + tipo);
        System.out.println("✅ Animal creado exitosamente: " + tipo);
        return animal;
    }

    public List<Animal> getAnimalesCreados() {
        return animalesCreados;
    }

    // ======== ESTRUCTURALES ========
    public void monitorearAnimal(Animal animal, boolean usarGPS) {
        if (animal == null) {
            System.out.println("⚠️ No hay animal válido para monitorear.");
            return;
        }

        String nombreAnimal = animal.getClass().getSimpleName();

        if (usarGPS) {
            AnimalConGPS animalGPS = new AnimalConGPS(animal);
            sensoresFacade.verificarYAlimentar(animalGPS);
            historialAcciones.add("Monitoreado (GPS): " + nombreAnimal);
        } else {
            sensoresFacade.verificarYAlimentar(animal);
            historialAcciones.add("Monitoreado: " + nombreAnimal);
        }
    }

    // ======== COMPORTAMENTALES ========
    public void aplicarEstrategia(String estacion) {
        gestorAlimentacion.cambiarEstacion(estacion);
        gestorAlimentacion.alimentar(50, 8);
        historialAcciones.add("Estrategia aplicada: " + estacion);
    }

    public void ejecutarComando(String tipo) {
        Command comando = switch (tipo.toLowerCase()) {
            case "dispensar" -> new DispensarAlimentoCommand("Corral Principal", 40.0);
            case "riego" -> new EncenderRiegoCommand("Zona Pasto", 10);
            case "evento" -> new RegistrarEventoCommand("Evento registrado desde consola");
            default -> null;
        };

        if (comando != null) {
            gestorComandos.ejecutarInmediato(comando);
            historialAcciones.add("Comando ejecutado: " + tipo);
        } else {
            System.out.println("⚠️ Comando no reconocido.");
        }
    }

    public void simularEstadoAnimal() {
        // Usamos la clase Animal del paquete state (context del patrón State)
        org.granja.comportamentales.state.Animal animalState =
                new org.granja.comportamentales.state.Animal("Bobby", "Vaca", 4, 420.0);

        System.out.println("\n🐾 Simulando estados del animal (State):");

        try {
            // Mostrar información inicial (nombre, tipo, estado actual)
            animalState.mostrarInfo();                            // método real en state.Animal. :contentReference[oaicite:3]{index=3}

            // Cambiamos a ENFERMO (EstadoEnfermo) usando el método existente setEstado(...)
            animalState.setEstado(new EstadoEnfermo());           // método real: setEstado(...).
            // Simulamos acciones del nuevo estado
            animalState.mostrarInfo();
            animalState.alimentar();
            animalState.aplicarTratamiento();

            // Cambiamos a EN TRATAMIENTO y avanzamos algunos pasos simulando días/tratamiento
            animalState.setEstado(new EstadoEnTratamiento());
            animalState.mostrarInfo();
            animalState.alimentar();
            animalState.aplicarTratamiento();

            // Finalmente mostramos info (podría volver a SANO internamente si tratamiento lo logra)
            animalState.mostrarInfo();

            historialAcciones.add("Simulación de estado completada");
        } catch (Exception e) {
            System.out.println("⚠️ Error al simular estado del animal: " + e.getMessage());
        }
    }


    // ======== SISTEMA Y UTILIDADES ========
    public void mostrarEstadoSistema() {
        alimentadorGlobal.mostrarEstado();
    }

    public void mostrarAnimalesRegistrados() {
        System.out.println("\n🐄🐖🐔 Animales registrados en la granja:");
        if (animalesCreados.isEmpty()) {
            System.out.println("   (Ningún animal registrado aún)");
            return;
        }
        for (Animal a : animalesCreados) {
            String nombre = a.getClass().getSimpleName();
            System.out.println(" - " + nombre);
        }
    }

    public void mostrarHistorialAcciones() {
        System.out.println("\n📜 Historial de acciones del sistema:");
        if (historialAcciones.isEmpty()) {
            System.out.println("   (Sin acciones registradas)");
            return;
        }
        for (String accion : historialAcciones) {
            System.out.println(" - " + accion);
        }
    }

    public void limpiarSistema() {
        animalesCreados.clear();
        historialAcciones.clear();
        System.out.println("\n♻️  Sistema reiniciado: animales y registros limpiados.");
    }

    public void resumenGeneral() {
        System.out.println("\n📊 RESUMEN GENERAL DE LA GRANJA:");
        System.out.println(" - Animales registrados: " + animalesCreados.size());
        System.out.println(" - Acciones registradas: " + historialAcciones.size());
        alimentadorGlobal.mostrarEstado();
    }
}

package org.granja.integracion;

import org.granja.creacionales.factorymethod.Animal;
import org.granja.creacionales.factorymethod.AnimalFactory;
import org.granja.creacionales.factorymethod.CerdoFactory;
import org.granja.creacionales.factorymethod.GallinaFactory;
import org.granja.creacionales.factorymethod.VacaFactory;
import org.granja.creacionales.singleton.AlimentadorGlobal;
import org.granja.estructurales.adapter.SensorAdapter;
import org.granja.estructurales.facade.SistemaSensoresFacade;
import org.granja.estructurales.decorator.AnimalConGPS;
import org.granja.comportamentales.strategy.GestorAlimentacion;
import org.granja.comportamentales.command.GestorComandos;
import org.granja.comportamentales.command.Command;
import org.granja.comportamentales.command.DispensarAlimentoCommand;
import org.granja.comportamentales.command.EncenderRiegoCommand;
import org.granja.comportamentales.command.RegistrarEventoCommand;
import org.granja.comportamentales.state.EstadoEnfermo;
import org.granja.comportamentales.state.EstadoEnTratamiento;
import org.granja.comportamentales.state.EstadoSano;

/**
 * Fachada general que unifica los subsistemas del proyecto y usa los paquetes
 * disponibles en el proyecto (factorymethod, singleton, adapter, facade, decorator, strategy, command, state).
 */
public class GranjaFacade {

    private final AlimentadorGlobal alimentadorGlobal;
    private final SistemaSensoresFacade sensoresFacade;
    private final GestorAlimentacion gestorAlimentacion;
    private final GestorComandos gestorComandos;

    public GranjaFacade() {
        this.alimentadorGlobal = AlimentadorGlobal.getInstancia();
        this.sensoresFacade = new SistemaSensoresFacade(new SensorAdapter());
        this.gestorAlimentacion = new GestorAlimentacion("Corral Principal");
        this.gestorComandos = new GestorComandos();
    }

    // ===== CREACIONALES (usando factorymethod package) =====
    public Animal crearAnimal(String tipo) {
        AnimalFactory factory;
        switch (tipo == null ? "" : tipo.toLowerCase()) {
            case "vaca" -> factory = new VacaFactory();
            case "cerdo" -> factory = new CerdoFactory();
            case "gallina" -> factory = new GallinaFactory();
            default -> {
                System.out.println("❌ Tipo de animal no reconocido (vaca/cerdo/gallina).");
                return null;
            }
        }
        Animal animal = factory.crearAnimal();
        System.out.println("✅ Animal creado: " + tipo);
        return animal;
    }

    // ===== ESTRUCTURALES =====
    /**
     * Si usarGPS == true, envolvemos con AnimalConGPS (decorator) antes de pasar a la fachada de sensores.
     */
    public void monitorearAnimal(org.granja.creacionales.factorymethod.Animal animal, boolean usarGPS) {
        if (animal == null) {
            System.out.println("⚠️ Animal nulo, crea primero un animal.");
            return;
        }
        if (usarGPS) {
            AnimalConGPS decorado = new AnimalConGPS(animal);
            sensoresFacade.verificarYAlimentar(decorado);
        } else {
            sensoresFacade.verificarYAlimentar(animal);
        }
    }

    // ===== COMPORTAMENTALES (strategy + command) =====
    public void aplicarEstrategia(String estacion) {
        gestorAlimentacion.cambiarEstacion(estacion);
        gestorAlimentacion.alimentar(50.0, 8);
    }

    public void ejecutarComando(String tipo) {
        Command cmd = switch (tipo == null ? "" : tipo.toLowerCase()) {
            case "dispensar" -> new DispensarAlimentoCommand("Corral Principal", 40.0);
            case "riego" -> new EncenderRiegoCommand("Zona Pasto", 10);
            case "evento" -> new RegistrarEventoCommand("Evento desde consola");
            default -> null;
        };
        if (cmd == null) {
            System.out.println("⚠️ Comando no reconocido.");
            return;
        }
        gestorComandos.ejecutarInmediato(cmd);
    }

    // ===== ESTADOS (state) - NOTA: usamos la jerarquía de state para simulaciones independientes =====
    public void simularCambioEstado() {
        // No confundir con Animal de factorymethod; aquí uso clases del paquete state si quieres
        org.granja.comportamentales.state.Animal a = new org.granja.comportamentales.state.Animal("Demo", "Vaca", 3, 350.0);
        a.mostrarInfo();
        a.setEstado(new EstadoEnfermo());
        a.alimentar();
        a.aplicarTratamiento();
    }

    // ===== SISTEMA =====
    public void mostrarEstadoSistema() {
        alimentadorGlobal.mostrarEstado();
    }

    public AlimentadorGlobal getAlimentadorGlobal() {
        return alimentadorGlobal;
    }
}

package org.granja.integracion;

import org.granja.creacionales.factorymethod.Animal;

import java.util.Scanner;

/**
 * Menú consola que usa GranjaFacade. Importa explicitamente la interfaz Animal del package factorymethod.
 */
public class MenuConsola {

    private final GranjaFacade fachada;
    private final Scanner scanner;

    public MenuConsola() {
        this.fachada = new GranjaFacade();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> crearAnimal();
                case 2 -> monitorearAnimal();
                case 3 -> aplicarEstrategia();
                case 4 -> ejecutarComando();
                case 5 -> fachada.simularCambioEstado();
                case 6 -> fachada.mostrarEstadoSistema();
                case 0 -> System.out.println("👋 Saliendo...");
                default -> System.out.println("⚠️ Opción inválida.");
            }

            if (opcion != 0) {
                System.out.println("\nPresiona ENTER para continuar...");
                scanner.nextLine();
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n===== MENÚ - GRANJA INTELIGENTE =====");
        System.out.println("1. Crear animal (vaca/cerdo/gallina)");
        System.out.println("2. Monitorear animal (usar sensores)");
        System.out.println("3. Aplicar estrategia de alimentación (verano/invierno/ahorro)");
        System.out.println("4. Ejecutar comando (dispensar/riego/evento)");
        System.out.println("5. Simular cambio de estado (state)");
        System.out.println("6. Mostrar estado del alimentador (singleton)");
        System.out.println("0. Salir");
        System.out.println("=====================================");
    }

    private void crearAnimal() {
        System.out.print("Tipo de animal: ");
        String tipo = scanner.nextLine().trim();
        Animal animal = fachada.crearAnimal(tipo);
        if (animal != null) {
            System.out.println("→ Animal creado correctamente.");
        }
    }

    private void monitorearAnimal() {
        System.out.print("Tipo de animal a crear y monitorear (vaca/cerdo/gallina): ");
        String tipo = scanner.nextLine().trim();
        Animal animal = fachada.crearAnimal(tipo);
        if (animal == null) return;
        System.out.print("Usar GPS? (s/n): ");
        String r = scanner.nextLine().trim();
        boolean usarGPS = r.equalsIgnoreCase("s");
        fachada.monitorearAnimal(animal, usarGPS);
    }

    private void aplicarEstrategia() {
        System.out.print("Estación (invierno/verano/ahorro): ");
        String estacion = scanner.nextLine().trim();
        fachada.aplicarEstrategia(estacion);
    }

    private void ejecutarComando() {
        System.out.print("Comando (dispensar/riego/evento): ");
        String cmd = scanner.nextLine().trim();
        fachada.ejecutarComando(cmd);
    }

    private int leerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }
}

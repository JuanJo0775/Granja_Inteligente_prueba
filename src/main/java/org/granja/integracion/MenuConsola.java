package org.granja.integracion;

import org.granja.creacionales.factorymethod.Animal;

import java.util.Scanner;

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
            opcion = obtenerEntero();

            switch (opcion) {
                case 1 -> crearAnimal();
                case 2 -> monitorearAnimal();
                case 3 -> fachada.aplicarEstrategia(pedirTexto("Ingrese estación (verano/invierno/ahorro): "));
                case 4 -> fachada.ejecutarComando(pedirTexto("Ingrese comando (dispensar/riego/evento): "));
                case 5 -> fachada.simularEstadoAnimal();
                case 6 -> fachada.mostrarEstadoSistema();
                case 7 -> fachada.mostrarAnimalesRegistrados();
                case 8 -> fachada.mostrarHistorialAcciones();
                case 9 -> fachada.resumenGeneral();
                case 10 -> fachada.limpiarSistema();
                case 0 -> System.out.println("👋 Saliendo del sistema...");
                default -> System.out.println("⚠️ Opción no válida.");
            }

            if (opcion != 0) {
                System.out.println("\nPresione ENTER para continuar...");
                scanner.nextLine();
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n===== 🌾 MENÚ DE LA GRANJA INTELIGENTE =====");
        System.out.println("1. Crear animal (Factory Method)");
        System.out.println("2. Monitorear animal (Adapter + Facade + Decorator)");
        System.out.println("3. Aplicar estrategia de alimentación (Strategy)");
        System.out.println("4. Ejecutar comando manual (Command)");
        System.out.println("5. Simular estados de salud (State)");
        System.out.println("6. Mostrar estado del sistema (Singleton)");
        System.out.println("7. Ver animales registrados");
        System.out.println("8. Ver historial de acciones");
        System.out.println("9. Ver resumen general del sistema");
        System.out.println("10. Reiniciar sistema (limpiar animales y registros)");
        System.out.println("0. Salir");
        System.out.println("============================================");
    }

    private void crearAnimal() {
        String tipo = pedirTexto("Ingrese tipo de animal (vaca/cerdo/gallina): ");
        fachada.crearAnimal(tipo);
    }

    private void monitorearAnimal() {
        String tipo = pedirTexto("Ingrese tipo de animal (vaca/cerdo/gallina): ");
        Animal animal = fachada.crearAnimal(tipo);

        if (animal != null) {
            System.out.print("¿Usar GPS? (s/n): ");
            boolean usarGPS = scanner.nextLine().equalsIgnoreCase("s");
            fachada.monitorearAnimal(animal, usarGPS);
        }
    }

    private int obtenerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String pedirTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }
}

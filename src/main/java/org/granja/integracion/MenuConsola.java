package org.granja.integracion;

import java.util.Scanner;

/**
 * Menú de consola que interactúa con GranjaFacade.
 */
public class MenuConsola {

    private final GranjaFacade fachada;
    private final Scanner sc;

    public MenuConsola() {
        this.fachada = new GranjaFacade();
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion = -1;
        while (opcion != 0) {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> crearAnimal();
                case 2 -> monitorearAnimal();
                case 3 -> fachada.aplicarEstrategia(pedirTexto("Ingrese estación (verano/invierno/ahorro): "));
                case 4 -> fachada.ejecutarComando(pedirTexto("Ingrese comando (dispensar/riego/evento): "));
                case 5 -> simularEstadoAnimal();
                case 6 -> fachada.mostrarEstadoSistema();
                case 7 -> fachada.mostrarAnimalesRegistrados();
                case 8 -> fachada.mostrarHistorialAcciones();
                case 9 -> fachada.resumenGeneral();
                case 10 -> fachada.limpiarSistema();
                case 0 -> System.out.println("👋 Saliendo del sistema...");
                default -> System.out.println("⚠️ Opción no válida.");
            }
            System.out.println();
        }
    }

    private void mostrarMenu() {
        System.out.println("===========================================");
        System.out.println("     🌾 GRANGA INTELIGENTE - MENÚ INTEGRACIÓN");
        System.out.println("===========================================");
        System.out.println("1. Crear animal");
        System.out.println("2. Monitorear animal (GPS + sensores)");
        System.out.println("3. Aplicar estrategia (verano/invierno/ahorro)");
        System.out.println("4. Ejecutar comando (dispensar/riego/evento)");
        System.out.println("5. Simular estado/día de un animal");
        System.out.println("6. Mostrar estado del sistema");
        System.out.println("7. Mostrar animales registrados");
        System.out.println("8. Mostrar historial de acciones");
        System.out.println("9. Resumen general");
        System.out.println("10. Limpiar sistema / reiniciar (no destruye singleton)");
        System.out.println("0. Salir");
        System.out.println("===========================================");
    }

    private void crearAnimal() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Tipo (Vaca/Cerdo/Gallina): ");
        String tipo = sc.nextLine().trim();
        int edad = leerEntero("Edad (años): ");
        double peso = leerDouble("Peso (kg): ");
        fachada.crearAnimal(nombre, tipo, edad, peso);
    }

    private void monitorearAnimal() {
        String id = pedirTexto("Ingrese nombre o índice del animal a monitorear: ");
        fachada.monitorearAnimal(id);
    }

    private void simularEstadoAnimal() {
        String id = pedirTexto("Ingrese nombre o índice del animal a simular: ");
        fachada.simularEstadoAnimal(id);
    }

    // -------------------------
    // Utilidades de consola
    // -------------------------
    private String pedirTexto(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private int leerEntero(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Ingrese un número entero.");
            }
        }
    }

    private double leerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Ingrese un número decimal.");
            }
        }
    }
}

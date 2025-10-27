package org.granja.comportamentales.state;

/**
 * Context del patrón State
 * Representa un animal que puede cambiar de estado de salud
 */
public class Animal {
    private String nombre;
    private String tipo; // "Vaca", "Cerdo", "Gallina"
    private EstadoSalud estado;
    private int edad;
    private double peso;

    public Animal(String nombre, String tipo, int edad, double peso) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.edad = edad;
        this.peso = peso;
        // Estado inicial: Sano
        this.estado = new EstadoSano();
    }

    /**
     * Cambia el estado del animal
     */
    public void setEstado(EstadoSalud nuevoEstado) {
        System.out.println("\n🔄 [" + nombre + "] Transición de estado:");
        System.out.println("   Anterior: " + estado.getNombreEstado());
        System.out.println("   Nuevo: " + nuevoEstado.getNombreEstado());
        this.estado = nuevoEstado;
    }

    /**
     * Delega la alimentación al estado actual
     */
    public void alimentar() {
        System.out.println("\n🐄 [" + nombre + " - " + tipo + "]");
        System.out.println("   Estado actual: " + estado.getNombreEstado());
        estado.alimentar(this);
    }

    /**
     * Delega la actividad al estado actual
     */
    public void realizarActividad() {
        System.out.println("\n🐄 [" + nombre + " - " + tipo + "]");
        System.out.println("   Estado actual: " + estado.getNombreEstado());
        estado.realizarActividad(this);
    }

    /**
     * Delega el tratamiento al estado actual
     */
    public void aplicarTratamiento() {
        System.out.println("\n🐄 [" + nombre + " - " + tipo + "]");
        System.out.println("   Estado actual: " + estado.getNombreEstado());
        estado.aplicarTratamiento(this);
    }

    /**
     * Muestra información completa del animal
     */
    public void mostrarInfo() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📋 INFORMACIÓN DEL ANIMAL");
        System.out.println("=".repeat(50));
        System.out.println("🏷️  Nombre: " + nombre);
        System.out.println("🦁 Tipo: " + tipo);
        System.out.println("🎂 Edad: " + edad + " años");
        System.out.println("⚖️  Peso: " + peso + " kg");
        System.out.println("💚 Estado de salud: " + estado.getNombreEstado());
        System.out.println("   " + estado.getDescripcion());
        System.out.println("=".repeat(50));
    }

    /**
     * Simula el paso del tiempo y acciones diarias
     */
    public void simularDia() {
        System.out.println("\n☀️  ===== NUEVO DÍA =====");
        alimentar();
        realizarActividad();
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public EstadoSalud getEstado() {
        return estado;
    }

    public int getEdad() {
        return edad;
    }

    public double getPeso() {
        return peso;
    }
}

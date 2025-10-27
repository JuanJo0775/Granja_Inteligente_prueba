package org.granja.comportamentales.observer;

/**
 * Interfaz Subject del patrón Observer
 * Define los métodos para gestionar observadores
 */
public interface Subject {
    void agregarObservador(Observer observer);
    void removerObservador(Observer observer);
    void notificarObservadores();
}
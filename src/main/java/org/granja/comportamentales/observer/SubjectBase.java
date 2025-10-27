package org.granja.comportamentales.observer;


import java.util.ArrayList;
import java.util.List;

/**
 * Clase base abstracta para facilitar la implementación de Subject
 * Maneja la lista de observadores y las notificaciones
 */
public abstract class SubjectBase implements Subject {
    protected List<Observer> observadores = new ArrayList<>();

    @Override
    public void agregarObservador(Observer observer) {
        observadores.add(observer);
        System.out.println("✓ Observador agregado: " + observer.getClass().getSimpleName());
    }

    @Override
    public void removerObservador(Observer observer) {
        observadores.remove(observer);
        System.out.println("✓ Observador removido: " + observer.getClass().getSimpleName());
    }

    @Override
    public void notificarObservadores() {
        for (Observer observer : observadores) {
            observer.actualizar(this);
        }
    }
}
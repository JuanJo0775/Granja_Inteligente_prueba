package org.granja;

import org.granja.integracion.MenuConsola;

/**
 * Punto de entrada para la integración de la granja.
 */
public class MainIntegrado {
    public static void main(String[] args) {
        System.out.println("🌾 Iniciando Granja Inteligente (Integración) 🌾");
        MenuConsola menu = new MenuConsola();
        menu.iniciar();
    }
}
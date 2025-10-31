package org.granja.creacionales.buld;

public class AnimalBuild {
    private String tipo;
    private int edad;
    private double peso;
    private String historialMedico;
    private String nivelActividad;

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setHistorialMedico(String historialMedico) {
        this.historialMedico = historialMedico;
    }

    public void setNivelActividad(String nivelActividad) {
        this.nivelActividad = nivelActividad;
    }

    @Override
    public String toString() {
        return "🐾 Animal{" +
                "tipo='" + tipo + '\'' +
                ", edad=" + edad +
                ", peso=" + peso +
                ", historialMedico='" + historialMedico + '\'' +
                ", nivelActividad='" + nivelActividad + '\'' +
                '}';
    }

    interface Builder {
        void reset();
        void setTipo(String tipo);
        void setEdad(int edad);
        void setPeso(double peso);
        void setHistorialMedico(String historial);
        void setNivelActividad(String nivel);
    }


    static class AnimalBuilder implements Builder {
        private AnimalBuild animal;

        public AnimalBuilder() {
            this.reset();
        }

        @Override
        public void reset() {
            this.animal = new AnimalBuild();
        }

        @Override
        public void setTipo(String tipo) {
            this.animal.setTipo(tipo);
        }

        @Override
        public void setEdad(int edad) {
            this.animal.setEdad(edad);
        }

        @Override
        public void setPeso(double peso) {
            this.animal.setPeso(peso);
        }

        @Override
        public void setHistorialMedico(String historial) {
            this.animal.setHistorialMedico(historial);
        }

        @Override
        public void setNivelActividad(String nivel) {
            this.animal.setNivelActividad(nivel);
        }

        public AnimalBuild getProducto() {
            AnimalBuild productoFinal = this.animal;
            this.reset(); // limpia para poder construir otro si se desea
            return productoFinal;
        }
    }

}

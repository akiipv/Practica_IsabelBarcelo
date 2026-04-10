package Gear;

import java.util.HashMap;

public class Arma extends Equipamiento{
    private boolean empeñadura;
    private String tipo;

    public Arma() {
        empeñadura = false;
        tipo = "";
    }

    public Arma(String nombre, String rareza, int valor, HashMap<String, Integer> stats, boolean empeñadura, String tipo) {
        super(nombre, rareza, valor, stats);
        setEmpeñadura(empeñadura);
        setTipo(tipo);
    }

    public Arma(String nombre, String rareza, String tipo, int valor) {
        super(nombre, rareza, valor);
        setTipo(tipo);
    }

    public boolean isEmpeñadura() {
        return empeñadura;
    }

    public void setEmpeñadura(boolean empeñadura) {
        this.empeñadura = empeñadura;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (empeñadura){
            switch (tipo){
                case "espadón", "martillo", "arco", "bastón" -> this.tipo = tipo;
                default -> {this.tipo = "";}
            }
        } else {
            switch (tipo){
                case "espada", "maza", "hacha", "cetro", "daga" -> this.tipo = tipo;
                default -> {this.tipo = "";}
            }
        }
    }

    @Override
    public int recuperaEstadistica(String stat) {
        switch (stat.toLowerCase()){
            case "fuerza", "magia", "fe", "velocidad" -> {
                return getStats().getOrDefault(stat, 0);
            }
            default -> {return 0;}
        }
    }

    public String tradusirEmpeñadura(boolean emp){
        return emp ? "Dos manos" : "Una mano";
    }

    @Override
    public String toString() {
        return "Cargando datos del armadura.. ૮ ․ ․ ྀིა " +
                super.toString() +
                "\n\t· Tipo:" + getTipo() +
                "\n\t· Empeñadura:" + tradusirEmpeñadura(isEmpeñadura());
    }

    public boolean equals(Arma otro) { return(super.equals(otro) &&
            this.getTipo().equals(otro.getTipo()) &&
            this.isEmpeñadura() == otro.isEmpeñadura());
    }
}

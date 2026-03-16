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
    public void recuperaEstadistica(String stat) {
        switch (stat.toLowerCase()){
            case "fuerza", "magia", "fe", "velocidad" -> getStats().get(stat);
            default -> {}
        }
    }
}

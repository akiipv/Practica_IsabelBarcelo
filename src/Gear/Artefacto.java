package Gear;

import java.util.HashMap;

public class Artefacto extends Equipamiento{
    private String tipo;

    public Artefacto() {
        tipo = "";
    }

    public Artefacto(String nombre, String rareza, int valor, HashMap<String, Integer> stats, String tipo) {
        super(nombre, rareza, valor, stats);
        setTipo(tipo);
    }

    public void setTipo(String tipo) {
        switch (tipo){
            case "anillos", "amuletos" -> this.tipo = tipo;
            default -> {this.tipo = "";}
        }
    }

    public String getTipo() {
        return tipo;
    }
}

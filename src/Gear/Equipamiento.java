package Gear;

import Characters.Personaje;

import java.util.HashMap;

public abstract class Equipamiento {
    private String nombre, rareza;
    private int valor;
    private final HashMap<String, Integer> stats  = new HashMap<>();

    public Equipamiento() {
        rareza = nombre = "";
        valor = 1;
    }

    public Equipamiento(String nombre, String rareza, int valor) {
        setNombre(nombre);
        setRareza(rareza);
        setValor(valor);
    }

    public void setNombre(String nombre) {
        if (nombre.length() > 20 || (nombre.isEmpty()))
            this.nombre = "";
        else this.nombre = nombre;
    }

    public void setRareza(String rareza) {
        if (rareza.equalsIgnoreCase("común") ||
                rareza.equalsIgnoreCase("raro") ||
                rareza.equalsIgnoreCase("épico") ||
                rareza.equalsIgnoreCase("legendario"))
            this.rareza = rareza;
        else this.rareza = "";
    }
    public void setValor(int valor) {
        this.valor = Math.max(valor, 1);
    }

    public String getNombre() { return nombre; }
    public String getRareza() { return  rareza; }
    public int getValor() { return valor; }

    //todo aser esto

    public void recuperaEstadistica(String stat) {}

    @Override
    public String toString() { return ""; }

   // @Override
    public boolean equals(Equipamiento equipamiento) { return true; }
}



package Gear;

import Characters.Personaje;

import java.util.HashMap;

public abstract class Equipamiento {
    private String nombre, rareza;
    private int valor;
    private HashMap<String, Integer> stats;

    public Equipamiento() {
        rareza = nombre = "";
        valor = 1;
        stats = new HashMap<>();
    }

    public Equipamiento(String nombre, String rareza, int valor, HashMap<String, Integer> stats) {
        setNombre(nombre);
        setRareza(rareza);
        setValor(valor);
        setStats(stats);
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

    public void setStats(HashMap<String, Integer> stats) {
        if (stats.isEmpty())
            this.stats = null;
        else this.stats = new HashMap<>(stats);
    }

    public String getNombre() { return nombre; }
    public String getRareza() { return  rareza; }
    public int getValor() { return valor; }

    public HashMap<String, Integer> getStats() {
        return stats;
    }

    public void recuperaEstadistica(String stat) {
        stats.getOrDefault(stat, -1);
    }

    @Override
    public String toString() {
        return "\n\t· Rareza:" + getRareza() +
            "\n\t· Nombre:" + getNombre() +
            "\n\t· Valor:" + getValor() +
            "\n\t· Estadísticas asociadas:" + getStats();
    }

   // @Override
    public boolean equals(Equipamiento otro) { return(this.getNombre().equals(otro.getNombre()) &&
            this.getRareza().equals(otro.getRareza()) &&
            this.getValor() == otro.getValor() &&
            this.stats.equals(otro.stats));
    }
}

// ivan: no doy risa



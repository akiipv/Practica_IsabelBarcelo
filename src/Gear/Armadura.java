package Gear;

import java.util.HashMap;

public class Armadura extends Equipamiento{
    private String tipo, material;

    public Armadura() {
        tipo = material = "";
    }

    public Armadura(String nombre, String rareza, int valor, HashMap<String, Integer> stats, String tipo) {
        super(nombre, rareza, valor, stats);
        setTipo(tipo);
        setMaterial(material);
    }

    public void setTipo(String tipo) {
        switch (tipo.toLowerCase()){
            case "yelmos", "pecheras", "hombreras", "guanteletes", "grebas", "botas" -> this.tipo = tipo;
            default -> {this.tipo = "";}
        }
    }

    public void setMaterial(String material) {
        switch (material.toLowerCase()){
            case "tela", "cuero", "metal" -> this.material = material;
            default -> {this.material = "";}
        }
    }

    public String getMaterial() {
        return material;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Cargando datos de la armadura.. ૮ ․ ․ ྀིა " +
                super.toString() +
                "\n\t· Tipo:" + getTipo() +
                "\n\t· Material:" + getMaterial();
    }

    public boolean equals(Armadura otro) { return(super.equals(otro) &&
            this.getTipo().equals(otro.getTipo()) &&
            this.getMaterial().equals(otro.getMaterial()));
    }
}

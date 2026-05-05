package Gear;

import java.util.HashMap;

/**
 * Clase que representa una armadura dentro del sistema de equipamiento.
 * Proporciona defensa y protección a los personajes según su tipo y material.
 * Extiende de {@link Equipamiento}.
 */

public class Armadura extends Equipamiento{
    private String tipo, material; /** Tipo y material de armadura **/

    /**
     * Constructor por defecto.
     * Inicializa la armadura sin tipo ni material.
     */

    public Armadura() {
        tipo = material = "";
    }

    /**
     * Constructor completo con estadísticas.
     *
     * @param nombre nombre de la armadura
     * @param rareza rareza de la armadura
     * @param valor valor de la armadura
     * @param stats estadísticas asociadas
     * @param tipo tipo de pieza de armadura
     * @param material material del que está hecha
     */

    public Armadura(String nombre, String rareza, int valor, HashMap<String, Integer> stats, String tipo, String material) {
        super(nombre, rareza, valor, stats);
        setTipo(tipo);
        setMaterial(material);
    }

    /**
     * Constructor simplificado de armadura sin estadísticas completas.
     *
     * @param nombre nombre de la armadura
     * @param rareza rareza de la armadura
     * @param tipo tipo de pieza de armadura
     * @param material material de la armadura
     * @param valor valor de la armadura
     */

    public Armadura(String nombre, String rareza, String tipo, String material, int valor) {
        super(nombre, rareza, valor);
        setTipo(tipo);
        setMaterial(material);
    }

    /**
     * Establece el tipo de armadura validando su valor.
     *
     * @param tipo tipo de armadura a asignar
     */

    public void setTipo(String tipo) {
        switch (tipo.toLowerCase()){
            case "yelmo", "pechera", "hombreras", "guanteletes", "grebas", "botas" -> this.tipo = tipo;
            default -> {this.tipo = "";}
        }
    }

    /**
     * Establece el material de la armadura validando su valor.
     *
     * @param material material de la armadura
     */

    public void setMaterial(String material) {
        switch (material.toLowerCase()){
            case "tela", "cuero", "metal" -> this.material = material;
            default -> {this.material = "";}
        }
    }

    /**
     * Devuelve el material de la armadura.
     *
     * @return material de la armadura
     */

    public String getMaterial() {
        return material;
    }

    /**
     * Devuelve el tipo de la armadura.
     *
     * @return tipo de armadura
     */

    public String getTipo() {
        return tipo;
    }

    /**
     * Recupera una estadística concreta de la armadura.
     * Solo aplica a atributos defensivos.
     *
     * @param stat nombre de la estadística
     * @return valor de la estadística o 0 si no aplica
     */

    @Override
    public int recuperaEstadistica(String stat) {
        switch (stat.toLowerCase()){
            case "defensa", "resistencia mágica", "vida" -> {
                return getStats().getOrDefault(stat, 0);
            }
            default -> {return 0;}
        }
    }

    /**
     * Representación en texto de la armadura.
     *
     * @return cadena con la información de la armadura
     */

    @Override
    public String toString() {
        return "Cargando datos de la armadura.. ૮ ․ ․ ྀིა " +
                super.toString() +
                "\n\t· Tipo:" + getTipo() +
                "\n\t· Material:" + getMaterial();
    }

    /**
     * Compara dos armaduras para determinar si son iguales.
     *
     * @param otro otra armadura a comparar
     * @return true si son iguales, false en caso contrario
     */

    public boolean equals(Armadura otro) { return(super.equals(otro) &&
            this.getTipo().equals(otro.getTipo()) &&
            this.getMaterial().equals(otro.getMaterial()));
    }
}

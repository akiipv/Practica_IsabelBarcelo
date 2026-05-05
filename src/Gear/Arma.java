package Gear;

import java.util.HashMap;

/**
 * Clase que representa un arma dentro del sistema de equipamiento.
 * Puede tener diferentes tipos y configuraciones de empuñadura.
 * Extiende de {@link Equipamiento}.
 */

public class Arma extends Equipamiento{
    private boolean empeñadura; /** Indica si el arma requiere dos manos (true) o una mano (false) **/
    private String tipo; /** Tipo de arma (espada, arco, bastón, etc.) **/

    /**
     * Constructor por defecto.
     * Inicializa el arma sin tipo ni configuración de empuñadura.
     */

    public Arma() {
        empeñadura = false;
        tipo = "";
    }

    /**
     * Constructor completo de arma con estadísticas y configuración.
     *
     * @param nombre nombre del arma
     * @param rareza rareza del arma
     * @param valor valor del arma
     * @param stats estadísticas del arma
     * @param empeñadura indica si es a dos manos o una mano
     * @param tipo tipo de arma
     */

    public Arma(String nombre, String rareza, int valor, HashMap<String, Integer> stats, boolean empeñadura, String tipo) {
        super(nombre, rareza, valor, stats);
        setEmpeñadura(empeñadura);
        setTipo(tipo);
    }

    /**
     * Constructor simplificado de arma sin estadísticas completas.
     *
     * @param nombre nombre del arma
     * @param rareza rareza del arma
     * @param tipo tipo de arma
     * @param valor valor del arma
     */

    public Arma(String nombre, String rareza, String tipo, int valor) {
        super(nombre, rareza, valor);
        setTipo(tipo);
    }

    /**
     * Indica si el arma es de dos manos.
     *
     * @return true si es de dos manos, false si es de una mano
     */

    public boolean isEmpeñadura() {
        return empeñadura;
    }

    /**
     * Establece si el arma es de una o dos manos.
     *
     * @param empeñadura configuración de empuñadura
     */

    public void setEmpeñadura(boolean empeñadura) {
        this.empeñadura = empeñadura;
    }

    /**
     * Devuelve el tipo de arma.
     *
     * @return tipo del arma
     */

    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de arma validando su compatibilidad con la empuñadura.
     *
     * @param tipo tipo de arma a asignar
     */

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

    /**
     * Recupera una estadística concreta del arma.
     * Solo aplica a atributos ofensivos como fuerza o magia.
     *
     * @param stat nombre de la estadística
     * @return valor de la estadística o 0 si no aplica
     */

    @Override
    public int recuperaEstadistica(String stat) {
        switch (stat.toLowerCase()){
            case "fuerza", "magia", "fe", "velocidad" -> {
                return getStats().getOrDefault(stat, 0);
            }
            default -> {return 0;}
        }
    }

    /**
     * Traduce la empuñadura a un formato legible.
     *
     * @param emp true si es a dos manos, false si es a una
     * @return texto descriptivo de la empuñadura
     */

    public String tradusirEmpeñadura(boolean emp){
        return emp ? "Dos manos" : "Una mano";
    }

    /**
     * Representación en texto del arma.
     *
     * @return cadena con los datos del arma
     */

    @Override
    public String toString() {
        return "Cargando datos del armadura.. ૮ ․ ․ ྀིა " +
                super.toString() +
                "\n\t· Tipo:" + getTipo() +
                "\n\t· Empeñadura:" + tradusirEmpeñadura(isEmpeñadura());
    }

    /**
     * Compara dos armas para determinar si son iguales.
     *
     * @param otro otra arma a comparar
     * @return true si son iguales, false en caso contrario
     */

    public boolean equals(Arma otro) { return(super.equals(otro) &&
            this.getTipo().equals(otro.getTipo()) &&
            this.isEmpeñadura() == otro.isEmpeñadura());
    }
}

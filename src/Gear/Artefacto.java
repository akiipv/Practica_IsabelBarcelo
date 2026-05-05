package Gear;

import java.util.HashMap;

/**
 * Clase que representa un artefacto dentro del sistema de equipamiento.
 * Son objetos especiales como anillos o amuletos que otorgan mejoras globales.
 * Extiende de {@link Equipamiento}.
 */

public class Artefacto extends Equipamiento{
    private String tipo; /** Tipo de artefacto **/

    /**
     * Constructor por defecto.
     * Inicializa el artefacto sin tipo.
     */

    public Artefacto() {
        tipo = "";
    }

    /**
     * Constructor completo con estadísticas.
     *
     * @param nombre nombre del artefacto
     * @param rareza rareza del artefacto
     * @param valor valor del artefacto
     * @param stats estadísticas asociadas
     * @param tipo tipo de artefacto
     */

    public Artefacto(String nombre, String rareza, int valor, HashMap<String, Integer> stats, String tipo) {
        super(nombre, rareza, valor, stats);
        setTipo(tipo);
    }

    /**
     * Constructor simplificado sin estadísticas completas.
     *
     * @param nombre nombre del artefacto
     * @param rareza rareza del artefacto
     * @param valor valor del artefacto
     * @param tipo tipo de artefacto
     */

    public Artefacto(String nombre, String rareza, int valor, String tipo) {
        super(nombre, rareza, valor);
        setTipo(tipo);
    }

    /**
     * Establece el tipo de artefacto validando su valor.
     *
     * @param tipo tipo de artefacto a asignar
     */

    public void setTipo(String tipo) {
        switch (tipo){
            case "anillo", "amuleto" -> this.tipo = tipo;
            default -> {this.tipo = "";}
        }
    }

    /**
     * Devuelve el tipo de artefacto.
     *
     * @return tipo del artefacto
     */

    public String getTipo() {
        return tipo;
    }

    /**
     * Recupera una estadística del artefacto.
     * Puede afectar a todas las estadísticas del personaje.
     *
     * @param stat nombre de la estadística
     * @return valor de la estadística o 0 si no existe
     */

    @Override
    public int recuperaEstadistica(String stat) {
        switch (stat.toLowerCase()){
            case "fuerza", "magia", "fe", "velocidad", "defensa", "resistencia mágica", "vida" -> {
                return getStats().getOrDefault(stat, 0);
            }
            default -> {return 0;}
        }
    }

    /**
     * Representación en texto del artefacto.
     *
     * @return cadena con la información del artefacto
     */

    @Override
    public String toString() {
        return "Cargando datos del artefacto.. ૮ ․ ․ ྀིა " +
                super.toString() +
                "\n\t· Tipo:" + getTipo();
    }

    /**
     * Compara dos artefactos para determinar si son iguales.
     *
     * @param otro otro artefacto a comparar
     * @return true si son iguales, false en caso contrario
     */

    public boolean equals(Artefacto otro) { return(super.equals(otro) &&
            this.getTipo().equals(otro.getTipo()));
    }
}

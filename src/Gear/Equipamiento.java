package Gear;

import Characters.Personaje;

import java.util.HashMap;

/**
 * Clase abstracta que representa un equipamiento del juego.
 * Puede ser un arma, armadura o artefacto, y contiene estadísticas
 * que afectan a los personajes que lo usan.
 */

public abstract class Equipamiento {
    private String nombre, rareza; /** Nombre y rareza del equipamiento **/
    private int valor; /** valor del equipamiento **/
    private HashMap<String, Integer> stats; /** stats del equipamiento **/

    /**
     * Constructor por defecto.
     * Inicializa el equipamiento con valores vacíos y básicos.
     */

    public Equipamiento() {
        rareza = nombre = "";
        valor = 1;
        stats = new HashMap<>();
    }

    /**
     * Constructor completo con estadísticas incluidas.
     *
     * @param nombre nombre del equipamiento
     * @param rareza rareza del equipamiento
     * @param valor valor del equipamiento
     * @param stats estadísticas asociadas
     */

    public Equipamiento(String nombre, String rareza, int valor, HashMap<String, Integer> stats) {
        setNombre(nombre);
        setRareza(rareza);
        setValor(valor);
        setStats(stats);
    }

    /**
     * Constructor sin estadísticas.
     *
     * @param nombre nombre del equipamiento
     * @param rareza rareza del equipamiento
     * @param valor valor del equipamiento
     */

    public Equipamiento(String nombre, String rareza, int valor) {
        setNombre(nombre);
        setRareza(rareza);
        setValor(valor);
    }

    /**
     * Establece el nombre del equipamiento.
     * Si no cumple los requisitos, se asigna vacío.
     *
     * @param nombre nombre a asignar
     */

    public void setNombre(String nombre) {
        if (nombre.length() > 20 || (nombre.isEmpty()))
            this.nombre = "";
        else this.nombre = nombre;
    }

    /**
     * Establece la rareza del equipamiento.
     * Solo se aceptan valores válidos predefinidos.
     *
     * @param rareza rareza a asignar
     */

    public void setRareza(String rareza) {
        if (rareza.equalsIgnoreCase("común") ||
                rareza.equalsIgnoreCase("raro") ||
                rareza.equalsIgnoreCase("épico") ||
                rareza.equalsIgnoreCase("legendario"))
            this.rareza = rareza;
        else this.rareza = "";
    }

    /**
     * Establece el valor del equipamiento.
     * Siempre será como mínimo 1.
     *
     * @param valor valor a asignar
     */

    public void setValor(int valor) {
        this.valor = Math.max(valor, 1);
    }

    /**
     * Establece las estadísticas del equipamiento.
     * Si están vacías, se asigna null.
     *
     * @param stats mapa de estadísticas
     */

    public void setStats(HashMap<String, Integer> stats) {
        if (stats.isEmpty())
            this.stats = null;
        else this.stats = new HashMap<>(stats);
    }

    /**
     * Devuelve el nombre del equipamiento.
     *
     * @return nombre del equipamiento
     */

    public String getNombre() { return nombre; }

    /**
     * Devuelve la rareza del equipamiento.
     *
     * @return rareza del equipamiento
     */

    public String getRareza() { return  rareza; }

    /**
     * Devuelve el valor del equipamiento.
     *
     * @return valor del equipamiento
     */

    public int getValor() { return valor; }

    /**
     * Devuelve las estadísticas del equipamiento.
     *
     * @return mapa de estadísticas
     */

    public HashMap<String, Integer> getStats() {
        return stats;
    }

    /**
     * Recupera el valor de una estadística concreta.
     *
     * @param stat nombre de la estadística
     * @return valor de la estadística solicitada
     */

    public abstract int recuperaEstadistica(String stat);

    /**
     * Representación en texto del equipamiento.
     *
     * @return cadena con la información del equipamiento
     */

    @Override
    public String toString() {
        return "\n\t· Rareza:" + getRareza() +
            "\n\t· Nombre:" + getNombre() +
            "\n\t· Valor:" + getValor() +
            "\n\t· Estadísticas asociadas:" + getStats();
    }

    /**
     * Compara dos equipamientos para ver si son iguales.
     *
     * @param otro otro equipamiento a comparar
     * @return true si son iguales, false en caso contrario
     */

   // @Override
    public boolean equals(Equipamiento otro) { return(this.getNombre().equals(otro.getNombre()) &&
            this.getRareza().equals(otro.getRareza()) &&
            this.getValor() == otro.getValor() &&
            this.stats.equals(otro.stats));
    }
}

// ivan: no doy risa
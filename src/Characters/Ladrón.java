package Characters;

/**
 * Subclase Ladrón.
 * Representa un tipo de Personaje especializado en velocidad y ataques
 * estratégicos, con la habilidad especial de robar.
 * Al usar la acción especial, golpea con su velocidad en lugar de ataque.
 */

public class Ladrón extends Personaje {

    /**
     * Contador de la cantidad de veces que el Ladrón ha robado.
     */

    private int robo;

    /**
     * Constructor por defecto del Ladrón.
     * Inicializa el personaje con valores por defecto y contador de robos en 0.
     */

    public Ladrón() {
        super();
        robo = 0;
    }

    /**
     * Constructor por parámetros del Ladrón.
     *
     * @param nombre nombre del Ladrón
     * @param pv vida
     * @param atq ataque
     * @param arm armadura
     * @param nivel nivel
     * @param vel velocidad
     * @param res resistencia mágica
     */

    public Ladrón(String nombre, int pv, int atq, int arm, int nivel, int vel, int res) {
        super(nombre, pv, atq, arm, nivel, vel, res);
        robo = 0;
    }

    /**
     * Devuelve la cantidad de robos realizados por el Ladrón.
     *
     * @return número de robos
     */

    public int getRobo() {
        return robo;
    }

    /**
     * Incrementa el nivel del Ladrón y mejora sus estadísticas
     * según sus ventajas y penalizaciones propias.
     */

    @Override
    public void subirNivel() {
        if (prob(40))
            setPv(getPv() + 1);

        if (prob(60))
            setAtq(getAtq() + 2);

        if (prob(40))
            setArm(getArm() + 1);

        if (prob(40))
            setRes(getRes() + 1);

        if (prob(85))
            setVel(getVel() + 2);


        setNivel(getNivel() + 1);
        System.out.println(getNombre() + ", ¡ha subido de nivel!\n\t" + toString());
    }

    /**
     * Acción especial del Ladrón: Robar.
     * Por ahora, solo devuelve la velocidad del Ladrón y aumenta
     * el contador de robos.
     *
     * @param enemigo personaje objetivo
     * @return valor de la velocidad usada como ataque
     */

    public int robar(Personaje enemigo) {
        System.out.println(coquetoL());
        System.out.println(getNombre() + " le ha robado a " + enemigo.getNombre() + "..");
        robo++;
        return this.getVel();
    }

    /**
     * Ejecuta la acción especial del Ladrón.
     *
     * @param enemigo personaje objetivo
     */

    @Override
    public void accEspesial(Personaje enemigo) {
        printPerezita("\uD835\uDC79\uD835\uDC90\uD835\uDC83\uD835\uDC82\uD835\uDC93..");
        enemigo.defensa(this.robar(enemigo), this.getTipoAtaque());
    }

    /**
     * Devuelve una representación textual del Ladrón,
     * mostrando sus estadísticas actuales y número de robos realizados.
     *
     * @return descripción del Ladrón
     */

    @Override
    public String toString() {
        String resultado = "Cargando datos del ladrón.. ૮ ․ ․ ྀིა " +
                "\n\t· Nombre: " + getNombre() +
                "\n\t· Vida: " + getPv() +
                "\n\t· Ataque: " + getAtq() +
                "\n\t· Armadura: " + getArm() +
                "\n\t· Velocidad: " + getVel() +
                "\n\t· Resistencia mágica: " + getRes() +
                "\n\t· Cantidad de robos realizados: " + getRobo() +
                "\n\t· Nivel: " + getNivel();
        return coquetudo() + "\n\n" + resultado;
    }

    /**
     * Devuelve una representación ASCII decorativa del personaje.
     *
     * @return cadena decorativa
     */

    public String coquetoL() {
        return "⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⢀⣾⣶⣤⡀⠀⡀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⢐⠁⠁⠀⠄⠀⠀⠀⠀⠀⠸⣿⣿⣿⡇⠀⢁⣑⠀⠀⠀⠀⡀⠤⣄⠄⠀⠆⠀\n" +
                "⠀⠀⠀⢀⠀⠀⠠⣠⠀⠀⠀⣹⢿⡿⢿⣾⣿⡿⠀⠀⠀⠸⠀⠀⣨⠀⠀⠀⠀\n" +
                "⠰⡈⠀⠈⠆⠠⠭⢀⣀⣠⣾⣿⣭⣿⣾⣟⠋⠀⠠⣀⣀⣀⡠⠊⠁⠀⠀⠀⠀\n" +
                "⢀⠈⠢⢆⡀⠀⢰⣿⣟⣻⣿⠿⠿⣿⣿⡿⣷⡄⠀⠀⠀⠀⠀⠀⠀⠂⠀⠀⠀\n" +
                "⠀⠀⠄⠀⠀⠀⢿⣿⣿⣿⣿⣉⢅⣿⣿⣶⣿⣿⣾⣿⡿⠁⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⢀⣴⣶⣶⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⠛⣾⣿⣥⡀⠀⠀⠐⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠈⠻⣿⣿⣿⡿⣃⣈⠻⠿⠟⠛⠛⠓⢄⠈⠻⠿⠿⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠜⠉⠉⢁⣾⣿⣿⣶⣿⣶⡀⠀⠀⠀⡷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠌⠂⠈⠀⠀⠸⠿⠛⠉⢻⣿⣿⡇⠀⠀⠐⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠘⡀⠀⠀⠉⢆⠀⠀⠀⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠑⢦⣄⡤⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n";
    }
}

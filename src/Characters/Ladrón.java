package Characters;

import java.io.File;
import java.io.IOException;

/**
 * Subclase Ladrón.
 * Representa un tipo de Personaje especializado en velocidad y ataques
 * estratégicos, con la habilidad especial de robar.
 * Al usar la acción especial, golpea con su velocidad en lugar de ataque.
 * @author Isa Barceló
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

    public Ladrón(File file) throws IOException {
        super(file);
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

    @Override
    public void setOtro(int robo) {
        this.robo = robo;
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
     * Por ahora, solo ataca con la velocidad del ladrón y aumenta
     * el contador de robos.
     *
     * @param enemigo personaje objetivo
     */

    public void robar(Personaje enemigo) {
        int rob = enemigo.defender(this.getVel(), this.getTipoAtaque());
        enemigo.defensa(this.getVel(), this.getTipoAtaque());
        System.out.println(coquetoL());
        if (rob > 0)
            System.out.println(this.getNombre() + " le ha robado a " + enemigo.getNombre() + ".. quitándole " + rob + " de vida con su velocidad..");
        else System.out.println("\n" + this.getNombre() + " le intenta robar a " + enemigo.getNombre() + " pero no le hace ni cosquillas.." + details(4));
        printPv(enemigo);
        robo++;
    }

    /**
     * Ejecuta la acción especial del Ladrón.
     *
     * @param enemigo personaje objetivo
     */

    @Override
    public void accEspesial(Personaje enemigo) {
        printPerezita("\uD835\uDC79\uD835\uDC90\uD835\uDC83\uD835\uDC82\uD835\uDC93..");
        this.robar(enemigo);
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

    public String cartita() {
        return "₊˚ ‿︵‿︵‿︵୨୧ · · ♡ · · ୨୧‿︵‿︵‿︵ ˚₊\n" +
                "\n· Nombre: "  + getNombre() +
                "\n   · Vida: " + getPv() +
                "\n   · Ataque: " + getAtq() +
                "\n   · Armadura: " + getArm() +
                "\n   · Velocidad: " + getVel() +
                "\n   · Resistencia mágica: " + getRes() +
                "\n   · Cantidad de robos realizados: " + getRobo() +
                "\n   · Nivel: " + getNivel();
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

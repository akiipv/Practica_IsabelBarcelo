package Characters;

import Gear.Arma;
import Gear.Armadura;
import Manolo.DWritersito;

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

    /**
     * Constructor que crea un Ladrón a partir de un archivo.
     * Inicializa todas las estadísticas del personaje leyendo el fichero
     * proporcionado, incluyendo los atributos heredados y el contador de robos.
     *
     * @param file archivo que contiene los datos del Ladrón
     * @throws IOException si ocurre un error al leer el fichero
     */

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

    @Override
    public int getOtro() {
        return robo;
    }

    /**
     * Incrementa el nivel del Ladrón y mejora sus estadísticas
     * según sus ventajas y penalizaciones propias.
     */

    @Override
    public void subirNivel() {
        if (prob(40)) setPv(getPv() + 1);
        if (prob(60)) setAtq(getAtq() + 2);
        if (prob(40)) setArm(getArm() + 1);
        if (prob(40)) setRes(getRes() + 1);
        if (prob(85)) setVel(getVel() + 2);

        setNivel(getNivel() + 1);
        System.out.println(getNombre() + ", ¡ha subido de nivel!\n\t" + toString());
    }

    /**
     * Acción especial del Ladrón: Robar.
     * Ataca con la velocidad del Ladrón en lugar del ataque normal y
     * aumenta el contador de robos realizados.
     * Los resultados se muestran mediante DWritersito.
     *
     * @param enemigo personaje objetivo del robo
     * @param pw instancia de DWritersito para salida en pantalla y fichero
     */

    public void robar(Personaje enemigo, DWritersito pw) {
        int rob = enemigo.defender(this.getVel(), this.getTipoAtaque());
        enemigo.defensa(this.getVel(), this.getTipoAtaque());
        pw.println(coquetoL());
        if (rob > 0)
            pw.println(this.getNombre() + " le ha robado a " + enemigo.getNombre() + ".. quitándole " + rob + " de vida con su velocidad..");
        else pw.println("\n" + this.getNombre() + " le intenta robar a " + enemigo.getNombre() + " pero no le hace ni cosquillas.." + details(4));
        printPv(enemigo, pw);
        robo++;
    }

    /**
     * Ejecuta la acción especial del Ladrón.
     *
     * @param enemigo personaje objetivo
     * @param dw instancia de DWritersito para salida en pantalla y fichero
     */

    @Override
    public void accEspesial(Personaje enemigo, DWritersito dw) {
        printPerezita("\uD835\uDC79\uD835\uDC90\uD835\uDC83\uD835\uDC82\uD835\uDC93..", dw);
        this.robar(enemigo, dw);
    }

    @Override
    public void equipArma(Arma arma){
        switch (arma.getTipo()) {
            case "espada", "daga" -> super.equipArma(arma);
            default -> System.out.println("Este tipo de arma no se puede equipar.");
        }
    }

    @Override
    public void equipArmadura(Armadura armadura){
        switch (armadura.getMaterial()) {
            case "tela", "cuero" -> super.equipArmadura(armadura);
            default -> System.out.println("No puedes equipar armadura de este material.");
        }
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
                super.toString() +
                "\n\t· Cantidad de robos realizados: " + getOtro();
        return coquetudo() + "\n\n" + resultado;
    }

    /**
     * Devuelve una “tarjeta” con la información principal del personaje.
     *
     * @return cadena con los atributos y estadísticas del personaje
     */

    public String cartita() {
        return "₊˚ ‿︵‿︵‿︵୨୧ · · ♡ · · ୨୧‿︵‿︵‿︵ ˚₊\n" +
                "\n· Nombre: "  + getNombre() +
                "\n· Clase: " + getClass().getSimpleName() +
                "\n   · Raza: " + getRaza() +
                "\n   · Vida: " + getPv() +
                "\n   · Ataque: " + getAtq() +
                "\n   · Armadura: " + getArm() +
                "\n   · Velocidad: " + getVel() +
                "\n   · Resistencia mágica: " + getRes() +
                "\n   · Cantidad de robos realizados: " + getOtro() +
                "\n   · Nivel: " + getNivel();
    }

    /**
     * Devuelve una representación ASCII decorativa del personaje.
     *
     * @return cadena decorativa
     */

    private String coquetoL() {
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

package Characters;

import Manolo.DWritersito;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Subclase Guerrero.
 * Representa un tipo de Personaje especializado en combate físico,
 * con grandes probabilidades de mejorar ataque, vida y armadura,
 * pero con una baja afinidad por la resistencia mágica.
 *
 * El Guerrero dispone del estado especial "furia", que incrementa
 * temporalmente su ataque a costa de recibir más daño al defenderse.
 * @author Isa Barceló
 */

public class Guerrero extends Personaje {
    private boolean furia;

    /**
     * Constructor por defecto del Guerrero.
     * Inicializa el personaje usando los valores por defecto
     * de la superclase y desactiva la furia.
     */

    public Guerrero() {
        super();
        furia = false;
    }

    /**
     * Constructor por parámetros del Guerrero.
     * Inicializa el personaje con los valores indicados y
     * establece el estado inicial de la furia.
     *
     * @param nombre nombre del guerrero
     * @param pv puntos de vida
     * @param atq ataque
     * @param arm armadura
     * @param nivel nivel del personaje
     * @param vel velocidad
     * @param res resistencia mágica
     * @param furia estado inicial de la furia
     */

    public Guerrero(String nombre, int pv, int atq, int arm, int nivel, int vel, int res, boolean furia) {
        super(nombre, pv, atq, arm, nivel, vel, res);
        setFuria(furia);
    }

    public Guerrero(File file) throws IOException {
        super(file);
    }

    /**
     * Devuelve el estado actual de la furia.
     *
     * @return true si la furia está activa, false en caso contrario
     */

    public boolean isFuria() {
        return furia;
    }

    /**
     * Establece el estado de la furia.
     *
     * @param furia nuevo estado de la furia
     */

    public void setFuria(boolean furia) {
        this.furia = furia;
    }

    /**
     * Activa o desactiva la furia según su estado actual.
     * Al activarse o desactivarse, se muestra un mensaje descriptivo.
     */

    public void modificarFuria(DWritersito dw) {

        if (isFuria()) {
            this.furia = false;
        } else this.furia = true;

        if (isFuria())
            printPerezita("\uD835\uDC6D\uD835\uDC96\uD835\uDC93\uD835\uDC8A\uD835\uDC82 \uD835\uDC82\uD835\uDC84\uD835\uDC95\uD835\uDC8A\uD835\uDC97\uD835\uDC82\uD835\uDC85\uD835\uDC82..", dw);
        else
            printPerezita("\uD835\uDC6D\uD835\uDC96\uD835\uDC93\uD835\uDC8A\uD835\uDC82 \uD835\uDC85\uD835\uDC86\uD835\uDC94\uD835\uDC82\uD835\uDC84\uD835\uDC95\uD835\uDC8A\uD835\uDC97\uD835\uDC82\uD835\uDC85\uD835\uDC82..", dw);
        System.out.println(coquetoG());
    }

    /**
     * Acción especial del Guerrero.
     * Permite alternar el estado de la furia durante el combate.
     *
     * @param enemigo personaje objetivo (no se utiliza directamente)
     */

    @Override
    public void accEspesial(Personaje enemigo, DWritersito dw) {
        modificarFuria(dw);
    }

    /**
     * Incrementa el nivel del Guerrero y mejora sus estadísticas
     * según las probabilidades y bonificaciones propias de su clase.
     */

    @Override
    public void subirNivel() {
        if (prob(75))
            setPv(getPv() + 1);

        if (prob(80))
            setAtq(getAtq() + 2);

        if (prob(75))
            setArm(getArm() + 1);

        if (prob(20))
            setRes(getRes() + 1);

        if (prob(50))
            setVel(getVel() + 1);

        setNivel(getNivel() + 1);
        System.out.println(getNombre() + ", ¡ha subido de nivel!\n\t" + toString());
    }

    /**
     * Calcula el daño de ataque del Guerrero.
     * Si la furia está activa, el ataque se duplica.
     *
     * @return valor del daño de ataque
     */

    @Override
    public int atacar() {
        if (isFuria()) {
            return getAtq() * 2;
        } else return getAtq();
    }

    /**
     * Calcula el daño recibido por el Guerrero al defenderse.
     * Si la furia está activa, el daño final recibido se duplica.
     *
     * @param dañoHecho daño inicial recibido
     * @param tipoDaño tipo de daño recibido (físico o mágico)
     * @return daño final recibido tras aplicar defensas y modificadores
     */

    @Override
    public int defender(int dañoHecho, String tipoDaño) {

        int dañoRecibido = 0;

        switch (tipoDaño) {
            case "fisico":
                dañoRecibido = dañoHecho - getArm();
                if (dañoRecibido < 0)
                    dañoRecibido = 0;

                if (isFuria())
                    dañoRecibido *= 2;
                break;
            case "magico":
                dañoRecibido = dañoHecho - getRes();
                if (dañoRecibido < 0)
                    dañoRecibido = 0;

                if (isFuria())
                    dañoRecibido *= 2;
                break;
        }

        return dañoRecibido;
    }

    /**
     * Devuelve una representación textual del Guerrero,
     * mostrando sus estadísticas actuales y el estado de la furia.
     *
     * @return descripción del Guerrero
     */

    @Override
    public String toString() {
        String resultado = "Cargando datos del guerrero.. ૮ ․ ․ ྀིა " +
                "\n\t· Nombre: " + getNombre() +
                "\n\t· Vida: " + getPv() +
                "\n\t· Ataque: " + getAtq() +
                "\n\t· Armadura: " + getArm() +
                "\n\t· Velocidad: " + getVel() +
                "\n\t· Resistencia mágica: " + getRes() +
                "\n\t· Furia: " + isFuria() +
                "\n\t· Nivel: " + getNivel();
        return coquetudo() + "\n\n" + resultado;
    }

    @Override
    public String cartita() {
        return "₊˚ ‿︵‿︵‿︵୨୧ · · ♡ · · ୨୧‿︵‿︵‿︵ ˚₊\n" +
                "\n· Nombre: "  + getNombre() +
                "\n   · Vida: " + getPv() +
                "\n   · Ataque: " + getAtq() +
                "\n   · Armadura: " + getArm() +
                "\n   · Velocidad: " + getVel() +
                "\n   · Resistencia mágica: " + getRes() +
                "\n   · Furia: " + isFuria() +
                "\n   · Nivel: " + getNivel();
    }

    /**
     * Devuelve una representación ASCII decorativa del personaje.
     *
     * @return cadena decorativa
     */

    public String coquetoG() {
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡧⠀⠀⠠⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢀⠀⠀⠀⢠⠀⡀⣷⠀⠀⡌⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠁⠠⣀⢼⣄⡂⣿⢸⢰⠄⡠⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠠⠀⢀⣺⢋⣠⣌⢋⣭⡍⢻⡂⠠⠄⠀⠀⠀⠀⠀\n" +
                "⠰⣶⠷⠶⠶⠶⠆⣰⣾⠸⣿⣷⣿⢿⡿⢸⣿⠿⠷⠶⠶⠶⠶⠆\n" +
                "⠀⠀⠀⠀⠀⠀⢀⡠⢞⢢⡑⢑⣝⡟⢁⡾⡫⢌⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠠⠀⠈⠀⠀⡀⠊⣱⢌⠋⢴⡍⠽⠢⠀⠈⠂⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠄⠀⠀⡐⠁⠼⣿⠃⠐⠄⠀⠈⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠁⠌⠀⠀⠀⣿⠀⠀⠐⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⢻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";
    }

}

package Characters;

import java.io.File;
import java.io.IOException;

/**
 * Clase abstracta Creyente.
 * Representa un personaje que puede realizar milagros mediante plegarias.
 * Los tipos de milagros dependen de la subclase específica.
 * @author Isa Barceló
 */

public abstract class Creyente extends Personaje {

    /**
     * Puntos de fe del Creyente, determinan su destreza al realizar milagros.
     */

    private int fe;

    /**
     * Constructor por defecto del Creyente.
     * Inicializa la fe a 0.
     */

    public Creyente() {
        super();
        fe = 0;
    }

    /**
     * Constructor por parámetros del Creyente.
     *
     * @param nombre nombre del personaje
     * @param pv vida
     * @param atq ataque
     * @param arm armadura
     * @param nivel nivel
     * @param vel velocidad
     * @param res resistencia mágica
     * @param fe puntos de fe
     */

    public Creyente(String nombre, int pv, int atq, int arm, int nivel, int vel, int res, int fe) {
        super(nombre, pv, atq, arm, nivel, vel, res);
        setOtro(fe);
    }

    public Creyente(File file) throws IOException {
        super(file);
    }

    /**
     * Asigna la cantidad de puntos de fe al Creyente.
     *
     * @param fe puntos de fe
     */

    @Override
    public void setOtro(int fe) {
        this.fe = fe;
    }

    /**
     * Devuelve la cantidad de puntos de fe del Creyente.
     *
     * @return puntos de fe
     */

    public int getFe() {
        return fe;
    }

    /**
     * Método abstracto plegaria.
     * Permite al Creyente efectuar milagros en su turno.
     *
     * @param enemigo personaje objetivo del milagro
     */

    public abstract void plegaria(Personaje enemigo);

    /**
     * Devuelve una representación textual del Creyente,
     * incluyendo sus estadísticas y puntos de fe.
     *
     * @return descripción del Creyente
     */

    @Override
    public String toString() {
        String resultado = "Cargando datos del creyente.. ૮ ․ ․ ྀིა " +
                "\n\t· Nombre: " + getNombre() +
                "\n\t· Vida: " + getPv() +
                "\n\t· Ataque: " + getAtq() +
                "\n\t· Armardura: " + getArm() +
                "\n\t· Nivel: " + getNivel() +
                "\n\t· Puntos de fe: " + getFe();
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
                "\n   · Fe: " + getFe() +
                "\n   · Nivel: " + getNivel();
    }
}

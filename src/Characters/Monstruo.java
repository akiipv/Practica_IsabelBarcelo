package Characters;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * La subclase Monstruo representa un personaje enemigo cuyo crecimiento y estadísticas
 * dependen de su raza: Bestia, No-muerto o Gigante.
 *
 * Cada raza tiene ventajas y penalizaciones específicas al subir de nivel:
 * - Bestia: 80% de probabilidad de incrementar el doble de ataque y velocidad,
 *   15% para armadura y resistencia mágica.
 * - No-muerto: 70% de probabilidad de incrementar cuatro veces la resistencia mágica,
 *   30% para vida y armadura, 5% para velocidad.
 * - Gigante: vida siempre aumenta (100%) con incremento doble o triple aleatorio,
 *   10% para velocidad y resistencia mágica, sin modificar armadura.
 *
 * Hereda de Personaje.
 *
 * @author Isa Barceló
 */

public class Monstruo extends Personaje {

    /** Raza del monstruo: "bestia", "no-muerto" o "gigante". */

    private String raza;

    /**
     * Constructor por defecto.
     * Inicializa los atributos heredados con los valores por defecto y la raza como cadena vacía.
     */

    public Monstruo() {
        super();
        setRaza("");
    }

    public Monstruo(File file) throws IOException {
        super(file);
    }

    /**
     * Constructor con parámetros.
     * Inicializa todos los atributos heredados de Personaje y asigna la raza del monstruo.
     *
     * @param nombre Nombre del monstruo
     * @param pv Puntos de vida
     * @param atq Puntos de ataque
     * @param arm Puntos de armadura
     * @param nivel Nivel del monstruo
     * @param vel Velocidad
     * @param res Resistencia mágica
     * @param tipo Raza del monstruo ("bestia", "no-muerto", "gigante")
     */

    public Monstruo(String nombre, int pv, int atq, int arm, int nivel, int vel, int res, String tipo) {
        super(nombre, pv, atq, arm, nivel, vel, res);
        setRaza(tipo);
    }

    /**
     * Devuelve la raza del monstruo.
     *
     * @return La raza como String
     */

    public String getRaza() {
        return raza;
    }

    /**
     * Asigna la raza del monstruo.
     * Solo se permite "bestia", "no-muerto" o "gigante".
     *
     * @param raza La raza a asignar
     */

    public void setRaza(String raza) {
        if (raza.equalsIgnoreCase("bestia") ||
                raza.equalsIgnoreCase("no-muerto") ||
                raza.equalsIgnoreCase("gigante"))
            this.raza = raza;
        else this.raza = "";
    }

    /**
     * Incrementa el nivel del monstruo y ajusta sus estadísticas
     * de acuerdo con su raza y probabilidades específicas.
     */

    @Override
    public void subirNivel() {

        switch (raza.toLowerCase()) {
            case "bestia":
                statsMonstruo(50, 80, 15, 15, 80);
                break;
            case "no-muerto":
                statsMonstruo(30, 50, 30, 70, 5);
                break;
            case "gigante":
                statsMonstruo(100, 50, 50, 10, 10);
                break;
            default:
                System.err.println("ok mañana");
        }

        setNivel(getNivel() + 1);
        System.out.println(getNombre() + ", ¡ha subido de nivel!\n\t" + toString());
    }

    /**
     * Ajusta las estadísticas del monstruo según los porcentajes dados.
     *
     * @param pctPV Probabilidad de aumentar la vida
     * @param pctATQ Probabilidad de aumentar el ataque
     * @param pctARM Probabilidad de aumentar la armadura
     * @param pctRES Probabilidad de aumentar la resistencia mágica
     * @param pctVEL Probabilidad de aumentar la velocidad
     */

    public void statsMonstruo(int pctPV, int pctATQ, int pctARM, int pctRES, int pctVEL) {
        if (prob(pctPV))
            setPv(getPv() + cantidad("pv"));

        if (prob(pctATQ))
            setAtq(getAtq() + cantidad("atq"));

        if (prob(pctARM))
            setArm(getArm() + cantidad("arm"));

        if (prob(pctRES))
            setRes(getRes() + cantidad("res"));

        if (prob(pctVEL))
            setVel(getVel() + cantidad("vel"));
    }

    /**
     * Calcula la cantidad a incrementar para cada estadística
     * según la raza del monstruo y el tipo de atributo.
     *
     * @param stat Nombre del atributo ("pv", "atq", "arm", "res", "vel")
     * @return Cantidad a incrementar
     */

    public int cantidad(String stat) {
        int cantidad = 1;
        Random r = new Random();

        switch (raza.toLowerCase()) {
            case "bestia":
                if (stat.equalsIgnoreCase("atq") || stat.equalsIgnoreCase("vel"))
                    cantidad = 2;
                break;
            case "no-muerto":
                if (stat.equalsIgnoreCase("res"))
                    cantidad = 4;
                break;
            case "gigante":
                if (stat.equalsIgnoreCase("pv"))
                    cantidad = r.nextInt(2, 4);
                break;
        }

        return cantidad;
    }

    /**
     * Devuelve una representación textual del estado del monstruo,
     * incluyendo nombre, estadísticas, raza y nivel.
     *
     * @return Información completa del monstruo como String
     */

    @Override
    public String toString() {
        String resultado = "Cargando datos del monstruo.. ૮ ․ ․ ྀིა " +
                "\n\t· Nombre: " + getNombre() +
                "\n\t· Raza: " + getRaza() +
                "\n\t· Vida: " + getPv() +
                "\n\t· Ataque: " + getAtq() +
                "\n\t· Armadura: " + getArm() +
                "\n\t· Nivel: " + getNivel();
        return coquetudo() + "\n\n" + resultado;
    }

    @Override
    public String cartita() {
        return "₊˚ ‿︵‿︵‿︵୨୧ · · ♡ · · ୨୧‿︵‿︵‿︵ ˚₊\n" +
                "\n· Nombre: "  + getNombre() +
                "\n   · Raza: " + getRaza() +
                "\n   · Vida: " + getPv() +
                "\n   · Ataque: " + getAtq() +
                "\n   · Armadura: " + getArm() +
                "\n   · Velocidad: " + getVel() +
                "\n   · Resistencia mágica: " + getRes() +
                "\n   · Nivel: " + getNivel();
    }
}

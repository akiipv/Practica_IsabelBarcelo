package Characters;

import Gear.Arma;
import Manolo.DWritersito;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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

    /**
     * Furia del guerrero
     */

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

    /**
     * Constructor que carga un Guerrero desde un archivo.
     *
     * @param file archivo que contiene los datos del guerrero
     * @throws IOException si ocurre un error al leer el archivo
     */

    public Guerrero(File file) throws IOException {
        super(file);

        BufferedReader br = new BufferedReader(new FileReader(file));

        for (int i = 0; i < 3; i++) {
            br.readLine();
        }

        String[] campos;
        String linea;
        while ((linea = br.readLine()) != null) {
            campos = linea.split(": ");

            if ((campos[0].replace("·", "").trim()).equals("Furia")) {
                setFuria(campos[1].equalsIgnoreCase("Activa"));
            }
        }
        br.close();
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
        this.furia = Boolean.parseBoolean(String.valueOf(furia));
    }

    /**
     * Método genérico heredado para usar con atributos adicionales.
     * Aquí representa la furia como 0 (inactiva) o 1 (activa).
     *
     * @param otro valor 0 o 1 que representa la furia
     */

    @Override
    public void setOtro(int otro) {
        this.furia = (otro == 1);
    }

    /**
     * Devuelve la representación numérica del atributo adicional.
     *
     * @return 1 si la furia está activa, 0 si está inactiva
     */

    @Override
    public int getOtro(){
        return isFuria() ? 1 : 0;
    }

    /**
     * Activa o desactiva la furia del Guerrero.
     * Muestra un mensaje descriptivo en pantalla y fichero usando DWritersito.
     *
     * @param dw instancia de DWritersito para salida en pantalla y fichero
     */

    public void modificarFuria(DWritersito dw) {

        this.furia = !isFuria();

        if (isFuria())
            printPerezita("\uD835\uDC6D\uD835\uDC96\uD835\uDC93\uD835\uDC8A\uD835\uDC82 \uD835\uDC82\uD835\uDC84\uD835\uDC95\uD835\uDC8A\uD835\uDC97\uD835\uDC82\uD835\uDC85\uD835\uDC82..", dw);
        else
            printPerezita("\uD835\uDC6D\uD835\uDC96\uD835\uDC93\uD835\uDC8A\uD835\uDC82 \uD835\uDC85\uD835\uDC86\uD835\uDC94\uD835\uDC82\uD835\uDC84\uD835\uDC95\uD835\uDC8A\uD835\uDC97\uD835\uDC82\uD835\uDC85\uD835\uDC82..", dw);
        System.out.println(coquetoG());
    }

    /**
     * Acción especial del Guerrero.
     * Alterna el estado de la furia durante el combate utilizando DWritersito.
     *
     * @param enemigo personaje objetivo (no se utiliza directamente)
     * @param dw instancia de DWritersito para salida en pantalla y fichero
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
        if (prob(75)) setPv(getPv() + 1);
        if (prob(80)) setAtq(getAtq() + 2);
        if (prob(75)) setArm(getArm() + 1);
        if (prob(20)) setRes(getRes() + 1);
        if (prob(50)) setVel(getVel() + 1);

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
            return getAtck() * 2;
        } else return getAtck();
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
                dañoRecibido = dañoHecho - getArmie();
                if (dañoRecibido < 0) dañoRecibido = 0;
                if (isFuria()) dañoRecibido *= 2;
                break;
            case "magico":
                dañoRecibido = dañoHecho - getResis();
                if (dañoRecibido < 0) dañoRecibido = 0;
                if (isFuria()) dañoRecibido *= 2;
                break;
        }

        return dañoRecibido;
    }

    @Override
    public void equipArma(Arma arma){
        switch (arma.getTipo()) {
            case "cetro", "baston", "arco" -> System.out.println("Este tipo de arma no se puede equipar");
            default -> super.equipArma(arma);
        }
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
                super.toString() +
                "\n\t· Furia: " + tradusirFuria(isFuria());
        return coquetudo() + "\n\n" + resultado;
    }

    /**
     * Devuelve una “tarjeta” con la información principal del personaje.
     *
     * @return cadena con los atributos y estadísticas del personaje
     */

    @Override
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
                "\n   · Furia: " + tradusirFuria(isFuria()) +
                "\n   · Nivel: " + getNivel();
    }

    /**
     * Traduce el estado de la furia a texto legible.
     *
     * @param furia true si está activa, false si está inactiva
     * @return "Activa" o "Inactiva"
     */

    public String tradusirFuria(boolean furia){
        return furia ? "Activa" : "Inactiva";
    }

    /**
     * Devuelve una representación ASCII decorativa del personaje.
     *
     * @return cadena decorativa
     */

    private String coquetoG() {
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

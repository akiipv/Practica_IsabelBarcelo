package Characters;

import Manolo.DWritersito;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Subclase Clérigo.
 * Representa un tipo de Creyente que combate a distancia y sana a sus aliados,
 * con habilidades especiales basadas en sus puntos de fe.
 * @author Isa Barceló
 */

public class Clérigo extends Creyente {

    /**
     * Constructor por defecto del Clérigo.
     */

    public Clérigo() {
    }

    /**
     * Constructor por parámetros del Clérigo.
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

    public Clérigo(String nombre, int pv, int atq, int arm, int nivel, int vel, int res, int fe) {
        super(nombre, pv, atq, arm, nivel, vel, res, fe);
    }

    /**
     * Constructor que inicializa un Clérigo a partir de un archivo.
     *
     * @param file archivo con la información del Clérigo
     * @throws IOException si ocurre un error al leer el archivo
     */

    public Clérigo(File file) throws IOException {
        super(file);
    }

    /**
     * Incrementa las estadísticas del Clérigo al subir de nivel
     * de acuerdo a sus ventajas y penalizaciones específicas.
     */

    @Override
    public void subirNivel() {
        if (prob(20)) setPv(getPv() + 1);
        if (prob(10)) setAtq(getAtq() + 1);
        if (prob(20)) setArm(getArm() + 1);
        if (prob(80)) setRes(getRes() + 2);
        if (prob(50)) setVel(getVel() + 1);
        if (prob(80)) setOtro(getOtro() + 2);

        setNivel(getNivel() + 1);
        System.out.println(getNombre() + ", ¡ha subido de nivel!\n\t" + toString());
    }

    /**
     * Permite al Clérigo realizar milagros durante su turno.
     * Los milagros disponibles son:
     * 1. Sanación: recupera vida de un objetivo según puntos de fe.
     * 2. Rezo sagrado: cura a todo el equipo.
     * 3. Cólera divina: inflige daño mágico a un enemigo.
     *
     * @param objetivo personaje objetivo de la plegaria
     * @param dw instancia de DWritersito para salida en pantalla y fichero
     */

    @Override
    public void plegaria(Personaje objetivo, DWritersito dw) {

        int opcion;
        int pleg;
        Scanner scan = new Scanner(System.in);

        dw.println(coquetoC());

        do {
            menusito("¿Qué tipo de conjuro quiere hacer?", new String[]{"Sanación", "Rezo sagrado", "Cólera divina", "Volver al menú principal"}, 2, dw);
            opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                    pleg = (int) (getOtro() * 0.7);
                    this.setPv(getPv() + pleg);
                    dw.println(this.getNombre() + " " + anderlain("sana") + " con su fe a " + objetivo.getNombre() + " subiéndole la vida " + pleg + " puntos..");
                    printPv(this, dw);
                    break;
                case 2:
                    pleg = (int) (getOtro() * 0.35);
                    this.setPv(getPv() + pleg);
                    dw.println(this.getNombre() + " hace un " + anderlain("rezo sagrado") + " y sana " + pleg + " puntos con su fe a todo el equipo..");
                    printPv(this, dw);
                    break;
                case 3:
                    this.setTipoAtaque("magico");
                    pleg = (int) (getOtro() * 0.55);
                    objetivo.defensa(pleg, this.getTipoAtaque());
                    dw.println(this.getNombre() + " lanza " + anderlain("cólera divina") + ".. " + objetivo.getNombre() + " recibe " + objetivo.defender(pleg, this.getTipoAtaque()) + " puntos de daño de sangrado..");
                    printPv(objetivo, dw);
                    break;
                case 4:
                    realizarTurno(objetivo, dw);
                    break;
            }
        } while (opcion > 4 || opcion < 1);
    }

    /**
     * Acción especial del Clérigo.
     * Por defecto, ejecuta la plegaria sobre un enemigo.
     *
     * @param enemigo personaje objetivo
     * @param dw instancia de DWritersito para salida en pantalla y fichero
     */

    @Override
    public void accEspesial(Personaje enemigo, DWritersito dw) {
        printPerezita("\uD835\uDC0F\uD835\uDC25\uD835\uDC1E\uD835\uDC20\uD835\uDC1A\uD835\uDC2B\uD835\uDC22\uD835\uDC1A..", dw);
        plegaria(enemigo, dw);
    }

    /**
     * Devuelve una representación textual del Clérigo,
     * incluyendo estadísticas y puntos de fe.
     *
     * @return descripción del Clérigo
     */

    @Override
    public String toString() {
        String resultado = "Cargando datos del clérigo.. ૮ ․ ․ ྀིა " +
                "\n\t· Nombre: " + getNombre() +
                "\n\t· Vida: " + getPv() +
                "\n\t· Ataque: " + getAtq() +
                "\n\t· Armadura: " + getArm() +
                "\n\t· Velocidad: " + getVel() +
                "\n\t· Resistencia mágica: " + getRes() +
                "\n\t· Puntos de fe: " + getOtro() +
                "\n\t· Nivel: " + getNivel();
        return coquetudo() + "\n\n" + resultado;
    }

    /**
     * Devuelve una representación ASCII decorativa del personaje.
     *
     * @return cadena decorativa
     */

    public String coquetoC() {
        return "⠀⠀⠀⠀⢀⡠⣾⣳⡀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⡀⠀⠚⢿⣿⣿⡿⠙⠀⠀⠀⠀\n" +
                "⠀⣘⣿⣇⡀⢘⣿⣿⠀⢀⣠⣶⡀⠀\n" +
                "⠺⣿⣷⣝⣾⣿⣿⣿⣿⣿⣹⣷⣿⠆\n" +
                "⠀⠘⠟⠁⠀⠀⣿⣟⠀⠀⠙⠿⠁⠀\n" +
                "⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢠⣿⣿⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢸⣿⡿⡄⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠠⣖⣿⣿⣻⡷⡄⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠈⢻⡟⠁⠀⠀⠀⠀⠀\n";
    }
}

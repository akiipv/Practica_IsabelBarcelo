package Characters;

import Gear.Arma;
import Gear.Armadura;
import Manolo.DWritersito;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Subclase Paladín.
 * Representa un tipo de Creyente especializado en combate cuerpo a cuerpo,
 * capaz de realizar milagros con su fe.
 * @author Isa Barceló
 */

public class Paladin extends Creyente {

    /**
     * Constructor por defecto del Paladín.
     */

    public Paladin() {
        super();
    }

    /**
     * Constructor por parámetros del Paladín.
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

    public Paladin(String nombre, int pv, int atq, int arm, int nivel, int vel, int res, int fe) {
        super(nombre, pv, atq, arm, nivel, vel, res, fe);
    }

    /**
     * Constructor que inicializa el Paladín desde un archivo.
     * Se asume que el archivo contiene los atributos en formato compatible
     * con la superclase Creyente/Personaje.
     *
     * @param file archivo con los datos del personaje
     * @throws IOException si ocurre un error leyendo el archivo
     */

    public Paladin(File file) throws IOException {
        super(file);
    }

    /**
     * Incrementa las estadísticas del Paladín al subir de nivel
     * de acuerdo a sus ventajas y penalizaciones específicas.
     */

    @Override
    public void subirNivel() {
        if (prob(50)) setPv(getPv() + (int) (getPv() * 0.05));
        if (prob(60)) setAtq(getAtq() + 1);
        if (prob(70)) setArm(getArm() + 2);
        if (prob(40)) setRes(getRes() + 1);
        if (prob(15)) setVel(getVel() + 1);
        if (prob(30)) setOtro(getOtro() + 1);

        setNivel(getNivel() + 1);
        System.out.println(getNombre() + ", ¡ha subido de nivel!\n\t" + toString());
    }

    /**
     * Permite al Paladín realizar milagros durante su turno.
     * Los milagros disponibles son: Imbuir arma, Baluarte de fe, Fogonazo sagrado.
     *
     * @param enemigo personaje objetivo de la plegaria
     * @param dw instancia de DWritersito para salida en pantalla y fichero
     */

    @Override
    public void plegaria(Personaje enemigo, DWritersito dw) {

        int opcion;
        int pleg;
        Scanner scan = new Scanner(System.in);

        dw.println(coquetoP());

        do {
            menusito("¿Qué tipo de conjuro quiere hacer?", new String[]{"Imbuir arma", "Baluarte de fe", "Fogonazo sagrado", "Volver al menú principal"}, 2, dw);
            opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                    pleg = (int) (getOtro() * 0.8);
                    this.setAtq(getAtq() + pleg);
                    dw.println(this.getNombre() + " comienza a " + anderlain("imbuir su arma") + " aumentando su ataque " + pleg + " puntos..\n\t· Ataque: " + this.getAtq());
                    break;
                case 2:
                    pleg = (int) (getOtro() * 0.3);
                    this.setArm(getArm() + pleg);
                    dw.println(this.getNombre() + " empieza a fortalecer su cuerpo con un " + anderlain("baluarte de fe") + " subiendo su armadura " + pleg + " puntos..\n\t· Armadura: " + this.getArm());
                    break;
                case 3:
                    pleg = (int) (getOtro() * 0.4);
                    enemigo.setVel(enemigo.getVel() - pleg);
                    enemigo.setRes(enemigo.getRes() - pleg);
                    dw.println(this.getNombre() + " lanza un " + anderlain("fogonazo sagrado") + " hacia " + enemigo.getNombre() + " cegándole, reduciendo así su velocidad y resistencia mágica " + pleg + " puntos..\n\t· Velocidad: " + enemigo.getVel() + "\n\t· Resistencia mágica: " + enemigo.getRes());
                    break;
                case 4:
                    realizarTurno(enemigo, dw);
                    break;
            }
        } while (opcion > 4 || opcion < 1);
    }

    /**
     * Acción especial del Paladín. Por defecto ejecuta la plegaria.
     *
     * @param enemigo personaje objetivo
     * @param dw instancia de DWritersito para salida en pantalla y fichero
     */

    @Override
    public void accEspesial(Personaje enemigo, DWritersito dw) {
        printPerezita("\uD835\uDC0F\uD835\uDC25\uD835\uDC1E\uD835\uDC20\uD835\uDC1A\uD835\uDC2B\uD835\uDC22\uD835\uDC1A..", dw);
        plegaria(enemigo, dw);
    }

    @Override
    public void equipArma(Arma arma){
        switch (arma.getTipo()) {
            case "arco", "baston" -> System.out.println("Este tipo de arma no se puede equipar.");
            default -> super.equipArma(arma);
        }
    }

    @Override
    public void equipArmadura(Armadura armadura){
        if (armadura.getMaterial().equals("metal"))
            super.equipArmadura(armadura);
        else System.out.println("No puedes equipar armadura de este material.");
    }

    /**
     * Devuelve una representación textual del Paladín,
     * incluyendo estadísticas y puntos de fe.
     *
     * @return descripción del Paladín
     */

    @Override
    public String toString() {
        String resultado = "Cargando datos del paladín.. ૮ ․ ․ ྀིა " +
                super.toString();
        return coquetudo() + "\n\n" + resultado;
    }

    /**
     * Devuelve una representación ASCII decorativa del personaje.
     *
     * @return cadena decorativa
     */

    private String coquetoP() {
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣶⡀\n" +
                "⠀⠀⢠⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣾⠏⠘⠿⣦⣤\n" +
                "⠀⠀⣾⠉⠻⢶⠶⠛⢻⡇⠀⠀⠀⠘⢻⡦⠀⠀⢰⡾⠃\n" +
                "⢀⣤⠿⠀⠀⠀⠀⢠⡟⠁⠀⠀⠀⠀⠸⠷⠿⠿⣾⣷\n" +
                "⢿⣥⣀⠀⠀⠀⠀⠀⢻⡆\n" +
                "⠀⠈⠉⣿⣀⣾⠟⠛⠋⠁    \n" +
                "⠀⠀⠀⠘⠛⠁\n";
    }

}

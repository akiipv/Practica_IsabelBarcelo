package Characters;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Subclase Mago.
 * Representa un tipo de Personaje especializado en el uso de la magia,
 * con gran afinidad por la resistencia mágica, la velocidad y el lanzamiento
 * de conjuros, pero con desventajas en vida, armadura y ataque físico.
 *
 * El Mago dispone de puntos de magia (mag) que determinan la potencia
 * de sus hechizos.
 * @author Isa Barceló
 */

public class Mago extends Personaje {

    /**
     * Magia del mago
     */

    private int mag;

    /**
     * Constructor por defecto del Mago.
     * Inicializa el personaje con los valores por defecto de la superclase
     * y establece los puntos de magia iniciales en 10.
     */

    public Mago() {
        super();
        mag = 10;
    }

    public Mago(File file) throws IOException {
        super(file);
    }

    /**
     * Constructor por parámetros del Mago.
     * Inicializa el personaje con los valores indicados y asigna
     * los puntos de magia especificados.
     *
     * @param nombre nombre del mago
     * @param pv puntos de vida
     * @param atq ataque
     * @param arm armadura
     * @param nivel nivel del personaje
     * @param vel velocidad
     * @param res resistencia mágica
     * @param mag puntos de magia
     */

    public Mago(String nombre, int pv, int atq, int arm, int nivel, int vel, int res, int mag) {
        super(nombre, pv, atq, arm, nivel, vel, res);
        setOtro(mag);
    }

    /**
     * Devuelve los puntos de magia actuales del Mago.
     *
     * @return puntos de magia
     */

    public int getMag() {
        return mag;
    }

    /**
     * Establece los puntos de magia del Mago.
     *
     * @param mag nuevo valor de puntos de magia
     */

    @Override
    public void setOtro(int mag) {
        this.mag = mag;
    }

    /**
     * Incrementa el nivel del Mago y mejora sus estadísticas
     * según las probabilidades y bonificaciones propias de su clase.
     */

    @Override
    public void subirNivel() {
        if (prob(35))
            setPv(getPv() + 1);

        if (prob(15))
            setAtq(getAtq() + 2);

        if (prob(35))
            setArm(getArm() + 1);

        if (prob(80))
            setRes(getRes() + 1);

        if (prob(65))
            setVel(getVel() + 1);

        if (prob(85))
            setOtro(getMag() + 1);


        setNivel(getNivel() + 1);
        System.out.println(getNombre() + ", ¡ha subido de nivel!\n\t" + toString());
    }

    /**
     * Permite al Mago lanzar un conjuro sobre un objetivo.
     * El tipo de conjuro se selecciona por teclado y su efecto
     * depende de los puntos de magia del Mago.
     *
     * @param enemigo personaje objetivo del conjuro
     */

    public void lanzarConjuro(Personaje enemigo) {

        this.setTipoAtaque("magico");
        int dañoConjuro = 0;
        int opcion;
        Scanner scan = new Scanner(System.in);

        System.out.println(coquetoM());

        do {
            menusito("¿Qué tipo de conjuro quiere hacer?", new String[]{"Bola de fuego", "Escudo arcano", "Céfiro", "Presteza mental", "Volver al menú principal"}, 2);
            opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                    dañoConjuro = (int) (getMag() * 0.70);
                    enemigo.defensa(dañoConjuro, this.getTipoAtaque());
                    System.out.println(this.getNombre() + " " + anderlain("bola de fuego") + " y hace " + enemigo.defender(dañoConjuro, this.getTipoAtaque()) + " de daño a " + enemigo.getNombre() + details(2));
                    printPv(enemigo);
                    break;
                case 2:
                    setArm(getArm() + (int) (getMag() * 0.5));
                    setRes(getRes() + (int) (getMag() * 0.5));
                    System.out.println("Un " + anderlain("escudo arcano") + " se manifiesta alrededor de " + this.getNombre() + ".. aumentando su armadura y resistencia mágica.." + details(5) + "\n\t· Armadura: " + this.getArm() + "\n\t· Resistencia mágica: " + this.getRes());
                    break;
                case 3:
                    dañoConjuro = (int) (getMag() * 0.30);
                    enemigo.defensa(dañoConjuro, this.getTipoAtaque());
                    System.out.println(this.getNombre() + " lanza " + anderlain("céfiro") + ", un fuerte viento se desata sobre " + enemigo.getNombre() + ".. causándole " + enemigo.defender(dañoConjuro, this.getTipoAtaque()) + " de daño mágico.." + details(4));
                    printPv(enemigo);
                    break;
                case 4:
                    setVel(super.getVel() + getMag());
                    System.out.println("La " + anderlain("presteza mental") + " de " + getNombre() + " le hace ganar " + mag + " puntos de velocidad.." + details(2) + "\n\t· Velocidad: " + this.getVel());
                    break;
                case 5:
                    realizarTurno(enemigo);
                    break;
            }
        } while (opcion > 5 || opcion < 1);
    }

    /**
     * Acción especial del Mago.
     * Permite lanzar conjuros durante el combate.
     *
     * @param enemigo personaje objetivo
     */

    @Override
    public void accEspesial(Personaje enemigo) {
        printPerezita("\uD835\uDC73\uD835\uDC82\uD835\uDC8F\uD835\uDC9B\uD835\uDC82\uD835\uDC93 \uD835\uDC84\uD835\uDC90\uD835\uDC8F\uD835\uDC8B\uD835\uDC96\uD835\uDC93\uD835\uDC90..");
        lanzarConjuro(enemigo);
    }

    /**
     * Devuelve una representación textual del Mago,
     * mostrando sus estadísticas actuales y puntos de magia.
     *
     * @return descripción del Mago
     */

    @Override
    public String toString() {
        String resultado = "Cargando datos del mago.. ૮ ․ ․ ྀིა " +
                "\n\t· Nombre: " + getNombre() +
                "\n\t· Vida: " + getPv() +
                "\n\t· Ataque: " + getAtq() +
                "\n\t· Armadura: " + getArm() +
                "\n\t· Velocidad: " + getVel() +
                "\n\t· Resistencia mágica: " + getRes() +
                "\n\t· Nivel: " + getNivel() +
                "\n\t· Puntos de magia: " + getMag();
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
                "\n   · Puntos de magia: " + getMag() +
                "\n   · Nivel: " + getNivel();
    }

    /**
     * Devuelve una representación ASCII decorativa del personaje.
     *
     * @return cadena decorativa
     */

    public String coquetoM() {
        return "⠀⠀⠀⠀⠀⠀⠀⠀⢰⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣸⠤⠖⠛⠉⠉⠉⠉⠉⠻⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⣠⠞⠋⡾⡀⠀⠀⠀⠀⠀⠀⠀⠀⢈⡧⢖⣒⣋⠙⢢⡀⠀⠀\n" +
                "⠀⠀⠀⡼⢁⣄⣠⠇⠳⣤⠤⠀⠀⠀⠀⠀⣴⠟⢹⠀⠀⠀⠈⠙⢷⡀⠀\n" +
                "⠀⠀⢸⡅⠀⠈⠳⡄⡞⠁⢀⠀⠀⠀⠀⡾⠁⠀⢸⠀⠀⠀⠀⠀⠀⣷⠀\n" +
                "⠀⠀⠈⠳⣄⣀⣀⣿⠖⠋⠁⠀⠀⠀⠀⡇⠀⣠⠏⠀⠀⠀⠀⣀⣼⡝⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⡏⠀⠀⠀⠀⠀⠀⠀⠙⠋⠁⠀⠀⣀⠤⠊⣡⡟⠁⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠤⣤⣤⣒⠮⠭⠤⠒⠋⢁⣇⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡶⠛⠉⠁⠀⠀⠀⠀⠀⠀⢀⡤⣀⡞⠘⡄⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⣴⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣇⢠⠞⠙\n" +
                "⠀⠀⠀⠀⠀⠀⠀⢠⠻⡀⠀⠀⠒⠒⠲⣆⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⣇⠙⠦⣀⣀⡠⠜⡹⠀⠀⠀⠀⠀⠀⠀⠸⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⠢⠤⠠⠆⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n";
    }
}

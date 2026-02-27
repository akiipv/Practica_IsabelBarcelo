package Characters;

import Manolo.DWritersito;

import java.io.PrintWriter;
import java.util.Scanner;

public class Coquette extends Personaje {
    private int coqueteria;

    /**todo 47 cm ataque -arm
     * todo añadir atributo género*/

    public Coquette() {
        super();
        coqueteria = 0;
    }

    public Coquette(String nombre, int pv, int atq, int arm, int nivel, int vel, int res, int coqueteria) {
        super(nombre, pv, atq, arm, nivel, vel, res);
        setCoqueteria(coqueteria);

    }

    public int getCoqueteria() {
        return coqueteria;
    }

    public void setCoqueteria(int coqueteria) {
        this.coqueteria = coqueteria;
    }

    @Override
    public void subirNivel() {
        if (prob(50))
            setPv(getPv() + 1);

        if (prob(85))
            setAtq(getAtq() + 2);

        if (prob(50))
            setArm(getArm() + 1);

        if (prob(85))
            setRes(getRes() + 2);

        if (prob(50))
            setVel(getVel() + 2);


        setNivel(getNivel() + 1);
        System.out.println(getNombre() + ", ¡ha subido de nivel!\n\t" + toString());
    }

    public void Corazon(Personaje enemigo, DWritersito dw) {
        this.setTipoAtaque("magico");
        int dañoCoquette = 0;
        int opcion;
        Scanner scan = new Scanner(System.in);

        dw.println(coquetoCQ());

        do {
            menusito("¿Qué tipo de conjuro quiere hacer?", new String[]{"Robar corazón", "Mandar besitos", "Admirarse en un espejo", "No, yo más", "Volver al menú principal"}, 2, dw);
            opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                    dañoCoquette = Integer.MAX_VALUE;
                    this.setTipoAtaque("fisico");
                    enemigo.defensa(dañoCoquette, this.getTipoAtaque());
                    dw.println(this.getNombre() + " le ha " + anderlain("robado el corazón") + " a " + enemigo.getNombre() + " matándolo directamente con su coquetería.." + details(2));
                    printPv(enemigo, dw);
                    break;
                case 2:
                    dañoCoquette = (int) (coqueteria * 0.85);
                    enemigo.setRes(getRes() - dañoCoquette);
                    dw.println(this.getNombre() + " le " + anderlain("manda besitos") + " a " + enemigo.getNombre() + ".. bajándole la resistencia mágica con su coquetería.." + details(5) + "\n\t· Resistencia mágica: " + enemigo.getRes());
                    break;
                case 3:
                    dañoCoquette = (int) (coqueteria * 0.90);
                    this.setAtq(getAtq() + dañoCoquette);
                    dw.println(this.getNombre() + " se " + anderlain("admira en el espejo") + ", reforzando su confianza y aumentando su ataque.. \n\t· Ataque: " + this.getAtq() + details(5));
                    break;
                case 4:
                    dañoCoquette = enemigo.getAtq() * 2;
                    enemigo.defensa(dañoCoquette, this.getTipoAtaque());
                    dw.println(this.getNombre() + " ama más a " + enemigo.getNombre() + " por lo que hace el doble de daño que él.." + details(2));
                    printPv(enemigo, dw);
                    break;
                case 5:
                    realizarTurno(enemigo, dw);
                    break;
            }
        } while (opcion > 5);
    }

    @Override
    public void accEspesial(Personaje enemigo, DWritersito dw) {
        printPerezita("\uD835\uDC6A\uD835\uDC90\uD835\uDC93\uD835\uDC82\uD835\uDC9B\uD835\uDC90\uD835\uDC8F \uD835\uDC84\uD835\uDC90\uD835\uDC92\uD835\uDC96\uD835\uDC86\uD835\uDC95\uD835\uDC90..", dw);
        Corazon(enemigo, dw);
    }

    @Override
    public String toString() {
        String resultado = "Cargando datos del coquette.. ૮ ․ ․ ྀིა " +
                "\n\t· Nombre: " + getNombre() +
                "\n\t· Vida: " + getPv() +
                "\n\t· Ataque: " + getAtq() +
                "\n\t· Armadura: " + getArm() +
                "\n\t· Velocidad: " + getVel() +
                "\n\t· Resistencia mágica: " + getRes() +
                "\n\t· Coquetería: " + getCoqueteria() +
                "\n\t· Nivel: " + getNivel();
        return coquetudo() + "\n\n" + resultado;
    }

    /**
     * Devuelve una representación ASCII decorativa del personaje.
     *
     * @return cadena decorativa
     */

    public String coquetoCQ() {
        return "⠀⠀⠀⠀⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⢤⣺⡟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠈⡅⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣶⣄⡀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣀⣀⠀⠀⢀⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡿⠃⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⣼⠋⠻⢿⣤⢿⠏⠀⠉⣟⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⣿⡄⠀⠀⠁⠀⠀⠀⢀⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠸⣧⠀⠀⠀⠀⠀⣄⡾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⡂⠀⠀⠀⠀⠀⠐⣷⢀⣀⣴⡿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⡤⡄⠀⠀⠀⠀\n" +
                "⣤⣧⡤⠀⠀⠀⠀⠀⠀⠘⠘⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢼⣿⠋⠉⡇⣇⣤⣤⣤⡀\n" +
                "⠀⢲⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢻⡇⠀⠀⣷⡟⠁⠀⢸⡇\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠐⠃⠀⠀⢀⣼⠇\n" +
                "⠀⠀⠀⠀⠀⠀⢠⡶⢢⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⢸⡇⠀⣀⣤⣤⣶⠧⠃⠀\n" +
                "⠀⠀⠀⢠⡖⠲⣿⠁⣸⠇⠀⠀⠀⠀⠀⡠⣧⠆⠀⠀⠀⠀⠈⠛⠳⠕⠛⠁⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠐⠿⡤⢤⣠⡿⠀⠀⠀⠀⠀⠀⠀⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n";
    }
}

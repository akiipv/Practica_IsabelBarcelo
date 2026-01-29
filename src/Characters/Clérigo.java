package Characters;

import java.util.Scanner;

public class Clérigo extends Creyente {

    public Clérigo() {
    }

    public Clérigo(String nombre, int pv, int atq, int arm, int nivel, int vel, int res, int fe) {
        super(nombre, pv, atq, arm, nivel, vel, res, fe);
    }

    @Override
    public void subirNivel() {
        if (prob(20))
            setPv(getPv() + 1);

        if (prob(10))
            setAtq(getAtq() + 1);

        if (prob(20))
            setArm(getArm() + 1);

        if (prob(80))
            setRes(getRes() + 2);

        if (prob(50))
            setVel(getVel() + 1);

        if (prob(80))
            setFe(getFe() + 2);


        setNivel(getNivel() + 1);
        System.out.println(getNombre() + ", ¡ha subido de nivel!\n\t" + toString());
    }

    @Override
    public void plegaria(Personaje objetivo) {

        int opcion;
        int pleg;
        Scanner scan = new Scanner(System.in);

        System.out.println(coquetoC());

        menusito("¿Qué tipo de conjuro quiere hacer?", new String[]{"Sanación", "Rezo sagrado", "Cólera divina"}, 2);
        opcion = scan.nextInt();

        switch (opcion) {
            case 1:
                pleg = (int) (getFe() * 0.7);
                objetivo.setPv(getPv() + pleg);
                System.out.println(this.getNombre() + anderlain("sana") + " con su fe a " + objetivo.getNombre() + " subiéndole la vida " + pleg + " puntos..");
                printPv(objetivo);
                break;
            case 2:
                pleg = (int) (getFe() * 0.35);
                objetivo.setPv(getPv() + pleg);
                System.out.println(this.getNombre() + " hace un " + anderlain("rezo sagrado") + " y sana " + pleg + " puntos con su fe a todo el equipo..");
                printPv(objetivo);
                break;
            case 3:
                this.setTipoAtaque("magico");
                pleg = (int) (getFe() * 0.55);
                objetivo.defensa(pleg, this.getTipoAtaque());
                System.out.println(this.getNombre() + " lanza " + anderlain("cólera divina") + ".. " + objetivo.getNombre() + " recibe " + objetivo.defender(pleg, this.getTipoAtaque()) + " puntos de daño de sangrado..");
                printPv(objetivo);
                break;
        }
    }

    @Override
    public void accEspesial(Personaje enemigo) {
        printPerezita("\uD835\uDC00\uD835\uDC1C\uD835\uDC1C\uD835\uDC22ó\uD835\uDC27 \uD835\uDC1E\uD835\uDC2C\uD835\uDC29\uD835\uDC1E\uD835\uDC1C\uD835\uDC22\uD835\uDC1A\uD835\uDC25: \uD835\uDC0F\uD835\uDC25\uD835\uDC1E\uD835\uDC20\uD835\uDC1A\uD835\uDC2B\uD835\uDC22\uD835\uDC1A..");
        plegaria(enemigo);
    }

    @Override
    public String toString() {
        String resultado = "Cargando datos del clérigo.. ૮ ․ ․ ྀིა " +
                "\n\t· Nombre: " + getNombre() +
                "\n\t· Vida: " + getPv() +
                "\n\t· Ataque: " + getAtq() +
                "\n\t· Armadura: " + getArm() +
                "\n\t· Velocidad: " + getVel() +
                "\n\t· Resistencia mágica: " + getRes() +
                "\n\t· Puntos de fe " + getFe() +
                "\n\t· Nivel: " + getNivel();
        return coquetudo() + "\n\n" + resultado;
    }

    public String coquetoC() {
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢾⣿⣿⡷⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣝⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⢿⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣾⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⣠⣾⣦⢤⣤⡠⣤⣠⣴⣿⡿⣿⣻⣧⣤⣠⡠⣤⣄⣴⣷⣄\n" +
                "⠙⢿⠟⠙⠙⠛⠛⠙⠛⣿⣷⣿⣿⡟⠛⠙⠛⠛⠋⠻⡿⠋\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣾⣿⡃⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⣳⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢱⣷⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣿⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣟⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢾⡷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢾⣿⣿⡷⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n";
    }
}

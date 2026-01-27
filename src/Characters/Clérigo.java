package Characters;

import java.util.Scanner;

public class Clérigo extends Creyente{

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
        Scanner scan = new Scanner(System.in);

        System.out.println(coquetoC());

        menusito("¿Qué tipo de conjuro quiere hacer?", new String[]{"Sanación", "Rezo sagrado", "Cólera divina"}, 2);
        opcion = scan.nextInt();

        switch (opcion){
            case 1:
                objetivo.setPv(getPv() + (int)(getFe() * 0.7));
                System.out.println(getNombre() + anderlain("sana") + " con su fe a " +  objetivo.getNombre() + " dejándolo a " + objetivo.getPv() + " pts de vida.");
                break;
            case 2:
                objetivo.setPv(getPv() + (int)(getFe() * 0.35));
                System.out.println(getNombre() + " hace un " + anderlain("rezo sagrado") + " y sana con su fe a todo el equipo..");
                break;
            case 3:
                this.setTipoAtaque("magico");
                objetivo.setPv(objetivo.getPv() - (int)(getFe() * 0.55));
                System.out.println(getNombre() + " lanza " + anderlain("cólera divina") + ".. " + objetivo.getNombre() + " recibe daño de sangrado..");
                break;
        }
    }

    @Override
    public void accEspesial(Personaje enemigo){
        printPerezita("\uD835\uDC00\uD835\uDC1C\uD835\uDC1C\uD835\uDC22ó\uD835\uDC27 \uD835\uDC1E\uD835\uDC2C\uD835\uDC29\uD835\uDC1E\uD835\uDC1C\uD835\uDC22\uD835\uDC1A\uD835\uDC25: \uD835\uDC0F\uD835\uDC25\uD835\uDC1E\uD835\uDC20\uD835\uDC1A\uD835\uDC2B\uD835\uDC22\uD835\uDC1A..");
        plegaria(enemigo);
    }

    @Override
    public String toString() {
        String resultado = "Cargando datos del clérigo.. ૮ ․ ․ ྀིა " +
                "\n\t· Nombre: " + super.getNombre() + "" +
                "\n\t· Vida: " + super.getPv() +
                "\n\t· Ataque: " + super.getAtq() +
                "\n\t· Armardura: " + super.getArm() +
                "\n\t· Nivel: " + super.getNivel() +
                "\n\t· Puntos de fe " + super.getFe();
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

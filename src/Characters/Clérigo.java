package Characters;

import java.util.Random;
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

        System.out.println("¿Qué tipo de conjuro quiere hacer? ⋆˙⟡ — " +
                "\n\t1. Sanación" +
                "\n\t2. Rezo sagrado" +
                "\n\t3. Cólera divina");
        opcion = scan.nextInt();

        switch (opcion){
            case 1:
                objetivo.setPv(getPv() + (int)(getFe() * 0.7));
                System.out.println(getNombre() + " sana con su fe a " +  objetivo.getNombre() + " dejándolo a " + objetivo.getPv() + " pts de vida.");
                break;
            case 2:
                objetivo.setPv(getPv() + (int)(getFe() * 0.35));
                System.out.println(getNombre() + " sana con su fe a todo el equipo..");
                break;
            case 3:
                setTipoAtaque("magico");
                objetivo.setPv(objetivo.getPv() - (int)(getFe() * 0.55));
                System.out.println(getNombre() + " lanza Cólera divina.. " + objetivo.getNombre() + " recibe daño de sangrado..");
                break;
        }
    }

    @Override
    public void accEspesial(){
        System.out.println("Acción especial: Plegaria..");
        // plegaria();
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

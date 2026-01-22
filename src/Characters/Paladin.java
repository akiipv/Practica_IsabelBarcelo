package Characters;

import java.util.Random;
import java.util.Scanner;

public class Paladin extends Creyente{

    public Paladin() {
        super();
    }

    public Paladin(String nombre, int pv, int atq, int arm, int nivel, int vel, int res, int fe) {
        super(nombre, pv, atq, arm, nivel, vel, res, fe);
    }

    @Override
    public void subirNivel() {
        if (prob(50))
            setPv(getPv() + (int)(getPv() * 0.05));

        if (prob(60))
            setAtq(getAtq() + 1);

        if (prob(70))
            setArm(getArm() + 2);

        if (prob(40))
            setRes(getRes() + 1);

        if (prob(15))
            setVel(getVel() + 1);

        if (prob(30))
            setFe(getFe() + 1);


        setNivel(getNivel() + 1);
        System.out.println(getNombre() + ", ¡ha subido de nivel!\n\t" + toString());
    }

    @Override
    public void plegaria(Personaje enemigo) {

        int opcion;
        Scanner scan = new Scanner(System.in);

        System.out.println(coquetoP());

        System.out.println("¿Qué tipo de conjuro quiere hacer? ⋆˙⟡ — " +
                "\n\t1. Imbuir arma" +
                "\n\t2. Baluarte de fe" +
                "\n\t3. Fogonazo sagrado");
        opcion = scan.nextInt();

        switch (opcion){
            case 1:
                setAtq(getAtq() + (int)(getFe() * 0.8));
                System.out.println(getNombre() + " aumenta su ataque con Imbuir arma");
                break;
            case 2:
                setArm(getArm() + (int)(getFe() * 0.3));
                System.out.println(getNombre() + " aumenta su armadura con Baluarte de fe");
                break;
            case 3:
                enemigo.setVel((int)(enemigo.getVel() - getFe() * 0.4));
                enemigo.setRes((int)(enemigo.getRes() - getFe() * 0.4));
                System.out.println(getNombre() + " lanza Fogonazo sagrado! " + enemigo.getNombre() + " ve reducida su velocidad y resistencia mágica.");
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
        String resultado = "Cargando datos del paladín.. ૮ ․ ․ ྀིა " +
                "\n\t· Nombre: " + super.getNombre() + "" +
                "\n\t· Vida: " + super.getPv() +
                "\n\t· Ataque: " + super.getAtq() +
                "\n\t· Armardura: " + super.getArm() +
                "\n\t· Nivel: " + super.getNivel() +
                "\n\t· Puntos de fe " + super.getFe();
        return coquetudo() + "\n\n" + resultado;
    }

    public String coquetoP(){
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
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";
    }

}

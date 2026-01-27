package Characters;

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

        menusito("¿Qué tipo de conjuro quiere hacer?", new String[]{"Imbuir arma", "Baluarte de fe", "Fogonazo sagrado"}, 2);
        opcion = scan.nextInt();

        switch (opcion){
            case 1:
                this.setAtq(getAtq() + (int)(getFe() * 0.8));
                System.out.println(getNombre() + " comienza a " + anderlain("imbuir su arma" + " aumentando su ataque a " + this.getAtq()));
                break;
            case 2:
                /**todo terminar este y el case 3*/
                this.setArm(getArm() + (int)(getFe() * 0.3));
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
    public void accEspesial(Personaje enemigo){
        printPerezita("\uD835\uDC00\uD835\uDC1C\uD835\uDC1C\uD835\uDC22ó\uD835\uDC27 \uD835\uDC1E\uD835\uDC2C\uD835\uDC29\uD835\uDC1E\uD835\uDC1C\uD835\uDC22\uD835\uDC1A\uD835\uDC25: \uD835\uDC0F\uD835\uDC25\uD835\uDC1E\uD835\uDC20\uD835\uDC1A\uD835\uDC2B\uD835\uDC22\uD835\uDC1A..");
        plegaria(enemigo);
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
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣶⡀\n" +
                "⠀⠀⢠⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣾⠏⠘⠿⣦⣤\n" +
                "⠀⠀⣾⠉⠻⢶⠶⠛⢻⡇⠀⠀⠀⠘⢻⡦⠀⠀⢰⡾⠃\n" +
                "⢀⣤⠿⠀⠀⠀⠀⢠⡟⠁⠀⠀⠀⠀⠸⠷⠿⠿⣾⣷\n" +
                "⢿⣥⣀⠀⠀⠀⠀⠀⢻⡆\n" +
                "⠀⠈⠉⣿⣀⣾⠟⠛⠋⠁    \n" +
                "⠀⠀⠀⠘⠛⠁\n";
    }

}

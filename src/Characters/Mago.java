package Characters;

import java.util.Random;
import java.util.Scanner;

public class Mago extends Personaje {

    private int mag;

    public Mago() {
        super();
        mag = 10;
    }

    public Mago(String nombre, int pv, int atq, int arm, int nivel, int vel, int res, int mag) {
        super(nombre, pv, atq, arm, nivel, vel, res);
        setMag(mag);
    }

    public int getMag() {
        return mag;
    }

    public void setMag(int mag) {
        this.mag = mag;
    }

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
            setMag(getMag() + 1);


        setNivel(getNivel() + 1);
        System.out.println(getNombre() + ", ¡ha subido de nivel!\n\t" + toString());
    }

    public void lanzarConjuro(Personaje enemigo) {

        setTipoAtaque("magico");
        double dañoConjuro = 0;
        int opcion;
        Scanner scan = new Scanner(System.in);

        System.out.println(coquetoM());

        System.out.println("¿Qué tipo de conjuro quiere hacer? ⋆˙⟡ — " +
                "\n\t1. Bola de fuego" +
                "\n\t2. Escudo arcano" +
                "\n\t3. Céfiro" +
                "\n\t4. Presteza mental");
        opcion = scan.nextInt();

        switch (opcion) {
            case 1:
                dañoConjuro = mag * 0.70;
                enemigo.setPv(enemigo.getPv() - (int)dañoConjuro);
                System.out.println(getNombre() + " lanza Bola de fuego y hace " + dañoConjuro + " de daño a " + enemigo.getNombre() + " ⋆˙⟡ —");
                break;
            case 2:
                setArm(getArm() + (int)(mag * 0.5));
                setRes(getRes() + (int)(mag * 0.5));
                System.out.println(getNombre() + " activa Escudo Arcano: " + toString() + " ⋆˙⟡ —");
                break;
            case 3:
                dañoConjuro = mag * 0.30;
                enemigo.setPv(enemigo.getPv() - (int)dañoConjuro);
                System.out.println("Un viento causa un 30% de sus puntos de magia como daño a todos los enemigos presentes.. ⋆˙⟡ —");
                break;
            case 4:
                 setVel(super.getVel() + mag);
                System.out.println("La presteza mental de " + getNombre() + " le hace ganar velocidad.. ⋆˙⟡ —");
                break;
        }
    }

    @Override
    public void accEspesial(){
        System.out.println("Acción especial: Lanzar conjuro..");
        // lanzarConjuro();
    }

    @Override
    public String toString() {
        String resultado = "Cargando datos del mago.. ૮ ․ ․ ྀིა " +
                "\n\t· Nombre: " + super.getNombre() + "" +
                "\n\t· Vida: " + super.getPv() +
                "\n\t· Ataque: " + super.getAtq() +
                "\n\t· Armardura: " + super.getArm() +
                "\n\t· Nivel: " + super.getNivel() +
                "\n\t· Puntos de magia: " + mag;
        return coquetudo() + "\n\n" + resultado;
    }

    public String coquetoM(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⡀⠀⡖⢉⢲⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣏⠰⠎⢀⣣⣈⣁⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠂⠍⠟⠉⣩⠖⠉⠁⠀⠈⠙⠦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠌⣼⠃⠀⠀⠀⠀⠀⠀⠀⠘⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢨⢸⣏⠀⠀⠀⢠⣤⣤⠀⠀⠀⢹⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⡄⠀⠀⠘⠂⣸⠇⠀⢀⡿⣀⠤⢀⠖⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠻⣦⣤⣤⡶⠋⠀⣠⣾⣿⡵⠦⢡⡬⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⣀⣤⣶⡲⡲⡤⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⢀⣴⣿⠋⡟⢧⠈⢂⡐⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⣠⠖⠉⠀⠀⠀⠈⠉⠪⢦⡙⢷⡄⠀⠀⢠⠜⠃⡠⠀⠀⣠⣾⠟⠁⠀⠈⠂⡆⠂⣀⣀⠀⠀⠀⠀⠀⠀\n" +
                "⢀⡜⠁⠀⠀⠀⠀⠀⠀⠀⠀⠈⠺⡐⢍⢆⠀⠀⠇⠢⠆⢀⣴⡿⠋⡠⠔⠒⠦⡄⠀⠀⠜⠻⠁⠀⠀⠀⠀⠀\n" +
                "⡜⠀⠀⠀⠀⠀⢀⣀⡀⠀⠀⠀⠀⠘⢮⢑⢆⠀⠀⠀⣴⡿⠋⠀⢰⠀⠠⢄⢀⠹⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⡇⠀⠀⠀⢀⣾⡿⠛⠛⠷⣄⠀⠀⠀⠈⢮⡘⡄⠀⣼⢿⡇⠅⠀⠈⠳⠦⠊⡀⢰⠤⠶⢶⣦⡄⠀⠀⠀⠀⠀\n" +
                "⢧⠀⠀⠀⠸⣿⠁⢀⠀⠀⠘⡆⠀⠀⢀⠈⢎⢇⣸⠃⠂⠹⣄⡁⠂⠠⠀⢂⠔⠁⢀⠤⠀⣿⣿⠀⠀⠀⠀⠀\n" +
                "⠘⣆⠀⠀⠀⠈⠒⠚⠀⠀⠀⡇⠀⠘⠻⠀⠘⣿⡏⠈⣠⠤⢬⣝⠛⠛⠉⢁⡀⠀⠘⢶⣶⡿⠃⠀⠀⠀⠀⠀\n" +
                "⠀⠘⣆⠀⠀⠀⠀⠀⠀⢀⡴⠁⢀⠀⠀⢀⠀⣿⣡⠎⢀⠔⣤⠈⡇⠀⢄⠈⠑⢐⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠈⠷⣄⣀⢀⣀⣠⠏⠀⠀⣄⠀⢌⠸⠀⣿⣿⠀⠈⠄⠡⠌⠀⠀⡈⣀⡀⡁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠉⠉⠉⠀⠀⠀⠄⡃⠩⡆⡁⠠⣿⣏⠀⠀⣠⠶⠶⡄⠀⠁⢀⣈⣃⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢗⠉⠉⠄⢸⡿⢻⡀⠀⢿⣄⠇⡹⢠⣶⡿⠛⠛⠻⢿⣦⡄⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠆⡈⣄⢴⠂⠃⠀⠀⠀⠠⣿⠰⠙⣦⠤⠤⠊⢰⣿⠃⠀⠀⣀⠀⠀⠘⢿⡄⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⢙⣻⡾⡛⠩⠉⠍⠙⡳⢦⡀⢻⡨⠁⠈⠳⡀⠀⢸⣿⡀⠀⢸⣋⠻⡄⠀⠘⡇⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⡴⢁⠁⠀⠀⠀⠀⠀⠀⠂⠙⢾⣇⠂⡎⣙⡟⡄⠀⢻⣧⠀⠀⠀⡰⠁⠀⢠⠇⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⡸⠡⠀⠀⠀⠀⢀⣀⣀⠀⠀⠀⠀⠹⣦⡱⢬⡤⠇⠀⠀⠉⠛⠒⠉⠀⠀⣠⠎⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⣏⡇⠀⠀⢀⡜⠉⠀⠀⠙⢆⠀⠀⠀⠹⣟⠢⣀⡀⠀⠀⠀⠀⢀⣀⡤⠞⠁⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠣⡹⡄⠀⠘⣄⠀⠈⡁⠀⢸⡆⠀⠀⠀⠙⣎⢃⠊⠋⢍⣸⣉⡉⠁⢤⣴⣀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠘⡂⢝⢦⣀⠈⠛⠈⠀⠀⡜⠀⣘⠂⢚⠀⠈⢧⡡⠠⠏⠱⣲⠙⡄⠉⠛⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠑⡡⡪⡫⡓⡦⠤⡄⠊⠀⠈⠁⢂⠊⠁⠀⠀⠹⣄⢇⡘⢶⣞⣁⣀⣤⣀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠑⠚⠒⠊⠁⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠀⠈⠳⣌⠉⠁⢠⢒⣦⠀⢡⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⠏⣤⣸⠇⠀⠀⠈⠣⡀⢹⣌⣁⣠⠎⡴⠚⠉⠑⢦⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣄⣀⣀⣀⣠⡴⢶⡶⠾⣦⡈⠉⠀⠸⣅⢠⡄⠀⢈⡆\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠉⠀⢀⠊⣀⡂⡀⠈⠢⢄⡀⠈⠉⠀⣀⣾⠃\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢆⣃⡼⠀⠀⠈⠀⠉⠛⠲⠟⠋⠁⠀\n";
    }
}

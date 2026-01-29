package Characters;

import java.util.Random;

public class Ladrón extends Personaje {

    private int robo;

    public Ladrón() {
        super();
        robo = 0;
    }

    public Ladrón(String nombre, int pv, int atq, int arm, int nivel, int vel, int res) {
        super(nombre, pv, atq, arm, nivel, vel, res);
        robo = 0;
    }

    public int getRobo() {
        return robo;
    }

    @Override
    public void subirNivel() {
        if (prob(40))
            setPv(getPv() + 1);

        if (prob(60))
            setAtq(getAtq() + 2);

        if (prob(40))
            setArm(getArm() + 1);

        if (prob(40))
            setRes(getRes() + 1);

        if (prob(85))
            setVel(getVel() + 2);


        setNivel(getNivel() + 1);
        System.out.println(getNombre() + ", ¡ha subido de nivel!\n\t" + toString());
    }

    public int Robar(Personaje enemigo) {
        System.out.println(coquetoL());
        System.out.println(getNombre() + " le ha robado a " + enemigo.getNombre() + "..");
        robo++;
        return getVel();
    }

    @Override
    public void accEspesial(Personaje enemigo) {
        printPerezita("\uD835\uDC79\uD835\uDC90\uD835\uDC83\uD835\uDC82\uD835\uDC93..");
        Robar(enemigo);
    }

    @Override
    public String toString() {
        String resultado = "Cargando datos del ladrón.. ૮ ․ ․ ྀིა " +
                "\n\t· Nombre: " + getNombre() +
                "\n\t· Vida: " + getPv() +
                "\n\t· Ataque: " + getAtq() +
                "\n\t· Armadura: " + getArm() +
                "\n\t· Velocidad: " + getVel() +
                "\n\t· Resistencia mágica: " + getRes() +
                "\n\t· Cantidad de robos realizados: " + getRobo() +
                "\n\t· Nivel: " + getNivel();
        return coquetudo() + "\n\n" + resultado;
    }

    public String coquetoL() {
        return "⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⢀⣾⣶⣤⡀⠀⡀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⢐⠁⠁⠀⠄⠀⠀⠀⠀⠀⠸⣿⣿⣿⡇⠀⢁⣑⠀⠀⠀⠀⡀⠤⣄⠄⠀⠆⠀\n" +
                "⠀⠀⠀⢀⠀⠀⠠⣠⠀⠀⠀⣹⢿⡿⢿⣾⣿⡿⠀⠀⠀⠸⠀⠀⣨⠀⠀⠀⠀\n" +
                "⠰⡈⠀⠈⠆⠠⠭⢀⣀⣠⣾⣿⣭⣿⣾⣟⠋⠀⠠⣀⣀⣀⡠⠊⠁⠀⠀⠀⠀\n" +
                "⢀⠈⠢⢆⡀⠀⢰⣿⣟⣻⣿⠿⠿⣿⣿⡿⣷⡄⠀⠀⠀⠀⠀⠀⠀⠂⠀⠀⠀\n" +
                "⠀⠀⠄⠀⠀⠀⢿⣿⣿⣿⣿⣉⢅⣿⣿⣶⣿⣿⣾⣿⡿⠁⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⢀⣴⣶⣶⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⠛⣾⣿⣥⡀⠀⠀⠐⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠈⠻⣿⣿⣿⡿⣃⣈⠻⠿⠟⠛⠛⠓⢄⠈⠻⠿⠿⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠜⠉⠉⢁⣾⣿⣿⣶⣿⣶⡀⠀⠀⠀⡷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠌⠂⠈⠀⠀⠸⠿⠛⠉⢻⣿⣿⡇⠀⠀⠐⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠘⡀⠀⠀⠉⢆⠀⠀⠀⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠑⢦⣄⡤⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n";
    }
}

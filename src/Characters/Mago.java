package Characters;

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

    /**
     * todo cambiar pq no queda aesthetic
     */

    public void lanzarConjuro(Personaje enemigo) {

        setTipoAtaque("magico");
        int dañoConjuro = 0;
        int opcion;
        Scanner scan = new Scanner(System.in);

        System.out.println(coquetoM());

        do {
            menusito("¿Qué tipo de conjuro quiere hacer?", new String[]{"Bola de fuego", "Escudo arcano", "Céfiro", "Presteza mental"}, 2);
            opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                    dañoConjuro = (int) (mag * 0.70);
                    enemigo.defensa(dañoConjuro, this.getTipoAtaque());
                    System.out.println(this.getNombre() + " lanza \033[0;4mbola de fuego\033[0;0m y hace " + enemigo.defender(dañoConjuro, this.getTipoAtaque()) + " de daño a " + enemigo.getNombre() + details(2));
                    printPv(enemigo);
                    break;
                case 2:
                    setArm(getArm() + (int) (mag * 0.5));
                    setRes(getRes() + (int) (mag * 0.5));
                    System.out.println("Un " + anderlain("escudo arcano") + " se manifiesta alrededor de " + this.getNombre() + ".. aumentando su armadura y resistencia mágica.." + details(5) + "\n\t· Armadura: " + this.getArm() + "\n\t· Resistencia mágica: " + this.getRes() + "\n");
                    break;
                case 3:
                    dañoConjuro = (int) (mag * 0.30);
                    enemigo.defensa(dañoConjuro, this.getTipoAtaque());
                    System.out.println(this.getNombre() + " lanza " + anderlain("céfiro") + ", que se desata sobre " + enemigo.getNombre() + ".. causándole " + enemigo.defender(dañoConjuro, this.getTipoAtaque()) + " de daño mágico.." + details(4));
                    printPv(enemigo);
                    break;
                case 4:
                    setVel(super.getVel() + mag);
                    System.out.println("La " + anderlain("presteza mental") + " de " + getNombre() + " le hace ganar " + mag + " puntos de velocidad.." + details(2) + "\n\t· Velocidad: " + this.getVel());
                    break;
            }
        } while (opcion > 4);
    }


    @Override
    public void accEspesial(Personaje enemigo) {
        printPerezita("\uD835\uDC73\uD835\uDC82\uD835\uDC8F\uD835\uDC9B\uD835\uDC82\uD835\uDC93 \uD835\uDC84\uD835\uDC90\uD835\uDC8F\uD835\uDC8B\uD835\uDC96\uD835\uDC93\uD835\uDC90..");
        lanzarConjuro(enemigo);
    }

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
                "\n\t· Puntos de magia: " + mag;
        return coquetudo() + "\n\n" + resultado;
    }

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

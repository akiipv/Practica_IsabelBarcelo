package Characters;

import java.util.Random;

public class Guerrero extends Personaje {
    private boolean furia;

    public Guerrero() {
        super();
        furia = false;
    }

    public Guerrero(String nombre, int pv, int atq, int arm, int nivel, int vel, int res, boolean furia) {
        super(nombre, pv, atq, arm, nivel, vel, res);
        setFuria(furia);
    }

    public boolean isFuria() {
        return furia;
    }

    public void setFuria(boolean furia) {
        this.furia = furia;
    }

    public void modificarFuria(){
        System.out.println(coquetoG());
        System.out.println("\nLa furia ha sido modificada a: " + isFuria());

        if (isFuria()){
            this.furia = false;
        } else this.furia = true;
    }

    @Override
    public void accEspesial(){
        System.out.println("Acción especial: Furia");
        modificarFuria();
    }

    @Override
    public void subirNivel() {
        if (prob(75))
            setPv(getPv() + 1);

        if (prob(80))
            setAtq(getAtq() + 2);

        if (prob(75))
            setArm(getArm() + 1);

        if (prob(20))
            setRes(getRes() + 1);

        if (prob(50))
            setVel(getVel() + 1);

        setNivel(getNivel() + 1);
        System.out.println(getNombre() + ", ¡ha subido de nivel!\n\t" + toString());
    }

    @Override
    public int atacar() {
        if(isFuria()){
            return getAtq() * 2;
        } else return getAtq();
    }

    @Override
    public int defender(int dañoHecho, String tipoDaño) {

        int dañoRecibido = 0;

        switch (tipoDaño) {
            case "fisico":
                dañoRecibido = dañoHecho - super.getArm();
                if (dañoRecibido < 0)
                    dañoRecibido = 0;

                if (isFuria())
                    dañoRecibido *= 2;
                break;
            case "magico":
                dañoRecibido = dañoHecho - super.getRes();
                if (dañoRecibido < 0)
                    dañoRecibido = 0;

                if (isFuria())
                    dañoRecibido *= 2;
        }

        return dañoRecibido;
    }

    @Override
    public String toString() {
        String resultado = "Cargando datos del guerrero.. ૮ ․ ․ ྀིა " +
                "\n\t· Nombre: " + super.getNombre() + "" +
                "\n\t· Vida: " + super.getPv() +
                "\n\t· Ataque: " + super.getAtq() +
                "\n\t· Armardura: " + super.getArm() +
                "\n\t· Nivel: " + super.getNivel() +
                "\n\t· Furia: " + isFuria();
        return coquetudo() + "\n\n" + resultado;
    }

    public String coquetoG(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡔⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠇⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠐⡀⠀⠀⠀⠀⠀⢺⠀⡄⠀⠀⠀⠀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡀⠀⠀⠀⠈⡀⣴⠀⠀⠀⠀⠀⠀⡔\n" +
                "⠀⠀⠀⠈⢦⣀⠀⠀⠀⠘⣷⡇⠀⠀⠀⠀⢦⡈⠑⠢⣄⠀⠀⠀⠀⠀⠀⠀⢀⡤⠚⠉⡠⠂⠀⠀⠀⠀⣷⡏⠀⠀⠀⢀⡤⠊⠀\n" +
                "⠀⠀⠀⠀⠀⠈⡟⠒⠢⠀⢳⠱⡀⠀⠀⠀⠀⠙⣦⣄⣈⡷⢄⠀⠀⠀⣠⡶⣏⣀⣴⡟⡀⠀⠀⠀⠀⡜⡹⠁⡠⠒⢻⡋⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠁⠙⠻⢤⡀⠁⠀⣳⡈⢆⠀⠀⠀⠈⢻⡟⠁⠀⠀⠑⠀⠐⠁⠀⠀⠹⣿⠋⠀⠀⠀⠠⠊⣴⠁⠀⠁⣠⠾⠛⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠻⢿⣄⠀⠀⠀⠑⣾⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⠖⠁⠀⠀⢀⣾⠟⠋⠉⠁⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣦⣀⠀⣦⡈⢻⡙⢄⠀⠀⠀⠀⠀⣠⠞⡸⠋⣠⡆⢀⣴⣟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⠠⠤⠐⢛⣋⣉⣳⠎⢽⠗⠣⣌⢷⢆⠀⣠⢾⢃⡤⠓⣿⠇⢾⣋⣉⣛⠓⠠⠤⠐⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠈⠦⣀⠀⠑⢆⠙⢁⠞⠁⢀⡴⠃⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠂⠀⠳⠁⠀⠂⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀             ";
    }

}

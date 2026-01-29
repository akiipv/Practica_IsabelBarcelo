package Characters;

public abstract class Creyente extends Personaje {
    private int fe;

    public Creyente() {
        super();
        fe = 0;
    }

    public Creyente(String nombre, int pv, int atq, int arm, int nivel, int vel, int res, int fe) {
        super(nombre, pv, atq, arm, nivel, vel, res);
        setFe(fe);
    }

    public void setFe(int fe) {
        this.fe = fe;
    }

    public int getFe() {
        return fe;
    }

    public abstract void plegaria(Personaje enemigo);

    @Override
    public String toString() {
        String resultado = "Cargando datos del creyente.. ૮ ․ ․ ྀིა " +
                "\n\t· Nombre: " + getNombre() +
                "\n\t· Vida: " + getPv() +
                "\n\t· Ataque: " + getAtq() +
                "\n\t· Armardura: " + getArm() +
                "\n\t· Nivel: " + getNivel() +
                "\n\t· Puntos de fe " + getFe();
        return coquetudo() + "\n\n" + resultado;
    }
}

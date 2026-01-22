package Characters;

import java.util.Random;

public class Cazador extends Personaje {

    private Mascota mascota;

    public Cazador() {
        super();
        mascota = this.new Mascota("", getNivel(), "");
    }

    public Cazador(String nombre, int pv, int atq, int arm, int nivel, int vel, int res, String raza, String nombreMascota) {
        super(nombre, pv, atq, arm, nivel, vel, res);
        mascota = this.new Mascota(raza, getNivel(), nombreMascota);
    }

    public Mascota getMascota() {
        return mascota;
    }

    @Override
    public void subirNivel() {
        if (prob(70)) {
            setVel(getVel() + 2);
        }

        setNivel(getNivel() + 1);
        System.out.println(getNombre() + ", ¡ha subido de nivel!\n\t" + toString());
    }

    @Override
    public String toString()
    {
        String resultado = "Cargando datos del cazador.. ૮ ․ ․ ྀིა " +
                "\n\t· Nombre: " + getNombre() + "" +
                "\n\t· Vida: " + getPv() +
                "\n\t· Ataque: " + getAtq() +
                "\n\t· Armardura: " + getArm() +
                "\n\t· Nivel: " + getNivel() +
                "\n\t\t" + mascota.toString();
        return coquetudo() + "\n\n" + resultado;
    }

    @Override
    public int atacar() {
        coquetoCM();
        return getAtq() + mascota.atacar();
    }

    class Mascota extends Personaje{

        private String raza;

        public Mascota(){
            super();
            raza = "";
        }

        public Mascota(String raza, int nivel, String nombre) {
            switch (raza) {
                case "canido":
                    statsMascotita(0.20, 0.20, 0.20, 0.20, 0.20, nivel, raza);
                    break;
                case "felino":
                    statsMascotita(0.15, 0.30, 0.15, 0.30, 0.15, nivel, raza);
                    break;
                case "rapaz":
                    statsMascotita(0.05, 0.15, 0.05, 0.35, 0.25, nivel, raza);
                    break;
                    default:
                        System.err.println("ok mañana");
            }

            setNombre(nombre);
        }

        public void statsMascotita(double pctPV, double pctATQ, double pctARM, double pctVEL, double pctRES, int nivel, String raza){
            setPv((int) (Cazador.this.getPv() * pctPV));
            setAtq((int) (Cazador.this.getAtq() * pctATQ));
            setArm((int) (Cazador.this.getArm() * pctARM));
            setVel((int) (Cazador.this.getVel() * pctVEL));
            setRes((int) (Cazador.this.getRes() * pctRES));
            setNivel(nivel);
            setRaza(raza);
        }

        public String getRaza() {
            return raza;
        }

        public void setRaza(String raza) {
            if (raza.equalsIgnoreCase("canido") ||
                    raza.equalsIgnoreCase("felino") ||
                    raza.equalsIgnoreCase("rapaz"))
                this.raza = raza;
            else this.raza = "";
        }


        @Override
        public String toString()
        {
            String resultado = "Cargando datos de la mascotita.. ૮ ․ ․ ྀིა " +
                    "\n\t· Nombre: " + getNombre() + "" +
                    "\n\t· Raza: " + getRaza() +
                    "\n\t· Vida: " + getPv() +
                    "\n\t· Ataque: " + getAtq() +
                    "\n\t· Armardura: " + getArm() +
                    "\n\t· Nivel: " + getNivel();
            return coquetudo() + "\n\n" + resultado;
        }

        @Override
        public void subirNivel()
        {
            setNivel(getNivel() + 1);
        }
    }

    public String coquetoCM() {
        return "⢠⣤⡀⣾⣿⣿⠀⣤⣤⡄\n" +
                "⢿⣿⡇⠘⠛⠁⢸⣿⣿⠃\n" +
                "⠈⣉⣤⣾⣿⣿⡆⠉⣴⣶⣶\n" +
                "⣾⣿⣿⣿⣿⣿⣿⡀⠻⠟⠃⠀\n" +
                "⠙⠛⠻⢿⣿⣿⣿⡇";
    }
}

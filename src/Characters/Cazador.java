package Characters;

import java.util.Random;

/**
 * Subclase Cazador.
 * Representa un tipo de Personaje especializado en velocidad y en el uso
 * de un Compañero Animal que comparte parcialmente sus estadísticas
 * y participa en los ataques.
 * @author Isa Barceló
 */

public class Cazador extends Personaje {

    /**
     * Compañero animal del Cazador.
     */

    private Mascota mascota;

    /**
     * Constructor por defecto del Cazador.
     * Inicializa el Cazador con valores por defecto y crea una mascota vacía.
     */

    public Cazador() {
        super();
        mascota = this.new Mascota("", getNivel(), "");
    }

    /**
     * Constructor por parámetros del Cazador.
     * Inicializa el Cazador con los valores indicados y crea una mascota
     * de la raza y nombre indicados.
     *
     * @param nombre nombre del Cazador
     * @param pv vida
     * @param atq ataque
     * @param arm armadura
     * @param nivel nivel
     * @param vel velocidad
     * @param res resistencia mágica
     * @param raza raza de la mascota
     * @param nombreMascota nombre de la mascota
     */

    public Cazador(String nombre, int pv, int atq, int arm, int nivel, int vel, int res, String raza, String nombreMascota) {
        super(nombre, pv, atq, arm, nivel, vel, res);
        mascota = this.new Mascota(raza, getNivel(), nombreMascota);
    }

    /**
     * Devuelve la mascota del Cazador.
     *
     * @return objeto Mascota
     */

    public Mascota getMascota() {
        return mascota;
    }

    /**
     * Incrementa el nivel del Cazador y mejora sus estadísticas
     * según sus ventajas y penalizaciones. La mascota también sube
     * de nivel (sin modificar estadísticas).
     */

    @Override
    public void subirNivel() {

        if (prob(50))
            setPv(getPv() + 1);

        if (prob(50))
            setAtq(getAtq() + 1);

        if (prob(50))
            setArm(getArm() + 1);

        if (prob(50))
            setRes(getRes() + 1);

        if (prob(70)) {
            setVel(getVel() + 2);
        }

        setNivel(getNivel() + 1);
        mascota.subirNivel();
        System.out.println(getNombre() + ", ¡ha subido de nivel!\n\t" + toString());
    }

    /**
     * Representación textual del Cazador, incluyendo la información
     * de la mascota.
     *
     * @return descripción del Cazador
     */

    @Override
    public String toString() {
        String resultado = "Cargando datos del cazador.. ૮ ․ ․ ྀིა " +
                "\n\t· Nombre: " + getNombre() +
                "\n\t· Vida: " + getPv() +
                "\n\t· Ataque: " + getAtq() +
                "\n\t· Armardura: " + getArm() +
                "\n\t· Velocidad: " + getVel() +
                "\n\t· Resistencia mágica: " + getRes() +
                "\n\t· Nivel: " + getNivel() +
                "\n\t\t" + mascota.toString();
        return coquetudo() + "\n\n" + resultado;
    }

    /**
     * Devuelve el ataque total del Cazador sumando el de la mascota.
     *
     * @return valor de ataque combinado
     */

    @Override
    public int atacar() {
        return (getAtq() + mascota.atacar());
    }

    /**
     * Ataque estilizado que muestra información de la mascota
     * durante la acción.
     *
     * @param enemigo personaje objetivo
     */

    @Override
    public void ataqueCoquetudo(Personaje enemigo) {
        int dañito = enemigo.defender(this.atacar(), this.getTipoAtaque());
        if (dañito <= 0)
            System.out.println("\n" + this.getNombre() + " decide atacar a " + enemigo.getNombre() + ", y " + mascota.getNombre() + " se suma al ataque, pero no le hacen ni cosquillas.." + details(4));
        else {
            System.out.println("\n" + this.getNombre() + " decide atacar a " + enemigo.getNombre() + ", y " + mascota.getNombre() + " se suma al ataque" + details(3) + "\n");
            printPerezita("\uD835\uDC74\uD835\uDC82\uD835\uDC94\uD835\uDC84\uD835\uDC90\uD835\uDC95\uD835\uDC8A\uD835\uDC95\uD835\uDC82 \uD835\uDC82\uD835\uDC8D \uD835\uDC82\uD835\uDC95\uD835\uDC82\uD835\uDC92\uD835\uDC96\uD835\uDC86..");
            System.out.println(coquetoCM() + "\n" + this.getNombre() + " y " + mascota.getNombre() + " han realizado " + enemigo.defender(this.atacar(), this.getTipoAtaque()) + " de daño a " + enemigo.getNombre() + "..");
        }
        enemigo.defensa(this.atacar(), this.getTipoAtaque());
        printPv(enemigo);
    }

    /**
     * Clase anidada Mascota.
     * Representa al Compañero Animal del Cazador.
     * Su subida de nivel solo aumenta el nivel, las estadísticas
     * dependen del Cazador y de la raza.
     */

    private class Mascota extends Personaje {

        private String raza;

        /**
         * Constructor por defecto de la Mascota.
         */

        public Mascota() {
            super();
            raza = "";
        }

        /**
         * Constructor por parámetros de la Mascota.
         *
         * @param raza especie de la mascota
         * @param nivel nivel inicial
         * @param nombre nombre de la mascota
         */

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

        /**
         * Ajusta las estadísticas de la mascota según el porcentaje
         * correspondiente a la raza y al Cazador.
         *
         * @param pctPV porcentaje de vida
         * @param pctATQ porcentaje de ataque
         * @param pctARM porcentaje de armadura
         * @param pctVEL porcentaje de velocidad
         * @param pctRES porcentaje de resistencia mágica
         * @param nivel nivel inicial
         * @param raza raza de la mascota
         */

        public void statsMascotita(double pctPV, double pctATQ, double pctARM, double pctVEL, double pctRES, int nivel, String raza) {
            setPv((int) (Cazador.this.getPv() * pctPV));
            setAtq((int) (Cazador.this.getAtq() * pctATQ));
            setArm((int) (Cazador.this.getArm() * pctARM));
            setVel((int) (Cazador.this.getVel() * pctVEL));
            setRes((int) (Cazador.this.getRes() * pctRES));
            setNivel(nivel);
            setRaza(raza);
        }

        /**
         * Modifica el tipo de raza de la mascota.
         *
         * @param raza tipo de raza
         */

        public void setRaza(String raza) {
            if (raza.equalsIgnoreCase("canido") ||
                    raza.equalsIgnoreCase("felino") ||
                    raza.equalsIgnoreCase("rapaz"))
                this.raza = raza;
            else this.raza = "";
        }

        /**
         * Devuelve el tipo de raza de la mascota.
         *
         * @return tipo de raza
         */

        public String getRaza() {
            return raza;
        }

        /**
         * Representación textual de la mascota.
         *
         * @return descripción de la mascota
         */

        @Override
        public String toString() {
            String resultado = "Cargando datos de la mascotita.." + details(3) +
                    "\n\t· Nombre: " + getNombre() +
                    "\n\t· Raza: " + getRaza() +
                    "\n\t· Vida: " + getPv() +
                    "\n\t· Ataque: " + getAtq() +
                    "\n\t· Armardura: " + getArm() +
                    "\n\t· Velocidad: " + getVel() +
                    "\n\t· Resistencia mágica: " + getRes() +
                    "\n\t· Nivel: " + getNivel();
            return coquetudo() + "\n\n" + resultado;
        }

        /**
         * Incrementa únicamente el nivel de la mascota.
         */

        @Override
        public void subirNivel() {
            setNivel(getNivel() + 1);
        }
    }

    /**
     * Devuelve una representación ASCII decorativa de la mascota
     * según su raza.
     *
     * @return cadena decorativa
     */

    public String coquetoCM() {

        switch (mascota.getRaza()) {

            case "canido":
                return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡀⣀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⢀⣤⣄⣀⠀⡀⢀⣴⣾⠿⠿⠛⠛⠛⠛⠳⢶⣄⡀⠀⠀⠀⢀⠀⠀⠀\n" +
                        "⢀⣾⠛⠙⠻⠶⠷⠞⠛⠉⠀⠀⠀⠀⠀⠀⠀⠈⠙⠳⣤⠴⠶⠺⣦⠀⠀\n" +
                        "⢸⡏⠀⢀⡀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡀⣤⡀⠈⠃⠀\n" +
                        "⠻⠀⣠⡿⣧⡞⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡾⢷⣦⡀⡗\n" +
                        "⢟⣴⠋⢠⣾⠁⠀⠀⠀⣀⣀⡀⠀⠀⠀⠀⠀⣠⣀⡀⠀⢸⠇⠀⠈⠙⠛\n" +
                        "⠈⠉⠀⠈⡿⠀⠀⠀⣴⠿⠛⠁⠀⢀⣀⣀⠀⠉⠻⣷⡄⣸⠀⠀⠀⠀⠀\n" +
                        "⠀⣠⡴⠾⣿⡀⢠⡄⠀⠀⠀⠀⢸⣿⣿⣿⡿⠀⠀⠀⢾⡿⠶⠶⢤⡄⠀\n" +
                        "⠼⠻⡄⣦⡈⠳⣾⡅⠀⠀⠀⠀⠀⠈⣹⡏⠀⠀⠀⠀⢨⡇⠀⣰⡄⠹⠀\n" +
                        "⠀⠀⠻⠿⠟⠛⠋⠻⣦⣄⣀⣠⣠⣴⠟⢷⣤⣀⣠⡤⠿⠵⠶⠟⠷⠞⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠛⠋⠛⠿⠒⠚⠛⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀\n";
            case "felino":
                return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⡯⢙⢍⣛⣶⣤⠴⠶⠦⢤⣤⣀⡀⠀⠀⢀⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⣨⡾⠋⠁⠀⠀⠀⠀⠀⠀⠉⠙⠷⠛⣫⠍⣻⢍⠹⡆⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠢⡄⠀⢸⠀⣷⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢢⣼⠀⣿⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⠁⠀⠀⣴⣶⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠀⡏⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⢀⣴⣶⢿⡷⣆⠀⠀⠿⠿⠟⢀⣀⠀⠀⠀⢰⣿⣿⣦⠀⠀⠀⠀⠀⠀⠀⣿⡀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⣾⣥⠛⡘⢳⡾⡇⠀⠀⠀⡀⠸⣿⠟⠀⠀⠘⠻⠟⠃⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⢻⡈⠿⠿⠿⠀⣧⠀⠀⠀⠛⠛⠻⠦⠤⠴⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⡇⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⢸⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡀⠀⠀⠀⣠⡟⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠸⣇⠀⠀⠀⠀⠀⠻⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠛⠉⠉⠙⢷⣾⠋⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⣀⣀⣀⣀⣠⣿⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣸⡇⣶⠀⣆⢀⣶⣿⣀⣀⣀⣀⣀⣀⡀⠀\n" +
                        "⠈⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠙⠛⠛⠻⠛⠋⠉⠉⠉⠉⠉⠉⠉⠉⠁\n";
            case "rapaz":
                return "⠀⠀⠀⠀⠀⠀⢀⣠⠴⠒⠒⠒⠒⠒⠶⠦⠤⠴⠒⠚⠉⣰⠟⠁⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⢀⡞⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠒⡿⠃⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⢠⡿⣤⣄⠀⠀⠀⠀⠀⢀⣤⣄⠀⠀⠀⣰⠞⠁⠀⠀⠀⢠⣤⠀⠀⠀⠀\n" +
                        "⠀⠀⢠⡟⠸⣿⡿⢀⠤⢄⠀⠐⣷⣿⣿⡷⠀⠀⢻⠀⠀⠀⢀⡴⠋⠘⡇⠀⠀⠀\n" +
                        "⠀⠀⢸⠈⠲⡬⢠⡏⠀⢀⡷⠀⠨⣭⠭⠖⣇⠀⣾⠀⠀⡴⠋⠀⠀⠀⣧⠀⠀⠀\n" +
                        "⠀⠀⢸⣷⠞⠁⠀⠳⠖⠋⠀⠀⠀⠙⠶⠶⠃⡼⢿⣠⠎⠀⠀⠀⠀⠀⣿⠀⠀⠀\n" +
                        "⠀⠀⢸⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡾⠃⠀⠀⠀⠀⠀⠀⡟⠀⠀⠀\n" +
                        "⠀⠀⡼⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⡄⠀⠀⠀⠀⠀⢠⠃⠀⠀⠄\n" +
                        "⢀⣰⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠉⠉⠉⠉⠲⢄⡀⢀⣠⡷⠀⠀⢠\n" +
                        "⢸⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠓⠧⠤⢾⢀⠏\n" +
                        "⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢨⠏⠀\n" +
                        "⢸⠀⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡏⠀⠀\n" +
                        "⠈⢷⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠢⡀⠀⠀⠀⠀⠀⠀⢀⣤⡏⠀⠀⠀\n" +
                        "⠀⠈⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠑⠲⠶⠶⠖⠊⠁⣴⠇⠀⠀⠀\n" +
                        "⠀⠀⠘⢧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⠁⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠈⠻⢦⣀⠀⠀⠀⠀⠀⠀⣠⣎⡀⡀⠀⠀⣀⣰⢶⠶⠚⠁⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⣰⠛⠉⡙⠛⢛⣷⠖⠒⢖⣾⠟⢛⠛⠺⣿⣏⠁⠀⠀⠀⠀⠀⠀⠀⠀\n";
            default:
                return "";
        }
    }
}

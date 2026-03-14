package Characters;

import Manolo.DWritersito;

import java.io.*;

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
     * Constructor que inicializa un Cazador a partir de un archivo.
     *
     * @param file archivo con la información del Cazador
     * @throws IOException si ocurre un error al leer el archivo
     */

    public Cazador(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        for (int i = 0; i < 3; i++) {
            br.readLine();
        }

        String[] campos;
        String linea;
        while (!(linea = br.readLine()).equals("── ⟢ ・⸝⸝  \uD835\uDC74\uD835\uDC82\uD835\uDC94\uD835\uDC84\uD835\uDC90\uD835\uDC95\uD835\uDC8A\uD835\uDC95\uD835\uDC82 .ᐟ.ᐟ")) {
            campos = linea.split(": ");

            switch (campos[0].replace("·", "").trim()) {
                case "Nombre" -> setNombre(campos[1]);
                case "Vida" -> setPv(Integer.parseInt(campos[1]));
                case "Ataque" -> setAtq(Integer.parseInt(campos[1]));
                case "Armadura" -> setArm(Integer.parseInt(campos[1]));
                case "Velocidad" -> setVel(Integer.parseInt(campos[1]));
                case "Resistencia mágica" -> setRes(Integer.parseInt(campos[1]));
                case "Nivel" -> setNivel(Integer.parseInt(campos[1]));
                case "Raza" -> setRaza(campos[1]);
            }
        }

        mascota = new Mascota(file);
        br.close();
    }

    /**
     * Actualiza los atributos del Cazador a partir de un archivo.
     * Si los atributos cambian, también se actualiza la mascota.
     *
     * @param file archivo con la información actualizada del Cazador
     * @throws IOException si ocurre un error al leer el archivo
     */

    @Override
    public void updtPJ(File file) throws IOException {
        Personaje playerFicheado = Factory.crear(this.getClass().getSimpleName(), file);

        if (!this.getNombre().equals(playerFicheado.getNombre())) return;
        if (!this.equals(playerFicheado)){
            this.copyCat(playerFicheado);
            mascota = new Mascota(file);
        }
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
     * Incrementa el nivel del Cazador y ajusta sus estadísticas
     * según ventajas y penalizaciones de su clase.
     * La mascota también sube de nivel, pero sus estadísticas permanecen dependientes del Cazador.
     */

    @Override
    public void subirNivel() {

        if (prob(50)) setPv(getPv() + 1);
        if (prob(50)) setAtq(getAtq() + 1);
        if (prob(50)) setArm(getArm() + 1);
        if (prob(50)) setRes(getRes() + 1);
        if (prob(70)) setVel(getVel() + 2);

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
     * Devuelve una “tarjeta” con la información principal del personaje.
     *
     * @return cadena con los atributos y estadísticas del personaje
     */

    @Override
    public String cartita() {
        return "₊˚ ‿︵‿︵‿︵୨୧ · · ♡ · · ୨୧‿︵‿︵‿︵ ˚₊\n" +
                "\n· Nombre: "  + getNombre() +
                "\n· Clase: " + getClass().getSimpleName() +
                "\n   · Raza: " + getRaza() +
                "\n   · Vida: " + getPv() +
                "\n   · Ataque: " + getAtq() +
                "\n   · Armadura: " + getArm() +
                "\n   · Velocidad: " + getVel() +
                "\n   · Resistencia mágica: " + getRes() +
                "\n   · Nivel: " + getNivel() +
                "\n\t" + mascota.cartita();
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
     * Representación estilizada del ataque del Cazador.
     * Incluye la contribución de la mascota al ataque y mensajes descriptivos.
     *
     * @param enemigo personaje objetivo
     * @param dw instancia de DWritersito para salida en pantalla y fichero
     */

    @Override
    public void ataqueCoquetudo(Personaje enemigo, DWritersito dw) {
        int dañito = enemigo.defender(this.atacar(), this.getTipoAtaque());

        if (dañito <= 0) dw.println("\n" + this.getNombre() + " decide atacar a " + enemigo.getNombre() + ", y " + mascota.getNombre() + " se suma al ataque, pero no le hacen ni cosquillas.." + details(4));
        else {
            dw.println("\n" + this.getNombre() + " decide atacar a " + enemigo.getNombre() + ", y " + mascota.getNombre() + " se suma al ataque" + details(3) + "\n");
            printPerezita("\uD835\uDC74\uD835\uDC82\uD835\uDC94\uD835\uDC84\uD835\uDC90\uD835\uDC95\uD835\uDC8A\uD835\uDC95\uD835\uDC82 \uD835\uDC82\uD835\uDC8D \uD835\uDC82\uD835\uDC95\uD835\uDC82\uD835\uDC92\uD835\uDC96\uD835\uDC86..", dw);
            dw.println(coquetoCM() + "\n" + this.getNombre() + " y " + mascota.getNombre() + " han realizado " + enemigo.defender(this.atacar(), this.getTipoAtaque()) + " de daño a " + enemigo.getNombre() + "..");
        }
        enemigo.defensa(this.atacar(), this.getTipoAtaque());
        printPv(enemigo, dw);
    }

    /**
     * Clase anidada Mascota.
     * Representa al Compañero Animal del Cazador.
     * Su subida de nivel solo aumenta el nivel, las estadísticas
     * dependen del Cazador y de la raza.
     */

    private class Mascota extends Personaje {

        /**
         * Constructor por defecto de la Mascota.
         */

        public Mascota() {
            super();
        }

        /**
         * Constructor de la Mascota a partir de un archivo.
         *
         * @param file archivo con la información de la mascota
         * @throws IOException si ocurre un error al leer el archivo
         */

        public Mascota(File file) throws IOException {
            BufferedReader br = new BufferedReader(new FileReader(file));

            for (int i = 0; i < 15; i++) {
                br.readLine();
            }

            String[] campos;
            String linea;
            while ((linea = br.readLine()) != null) {
                campos = linea.split(": ");

                switch (campos[0].replace("·", "").trim()) {
                    case "Nombre" -> setNombre(campos[1]);
                    case "Vida" -> setPv(Integer.parseInt(campos[1]));
                    case "Ataque" -> setAtq(Integer.parseInt(campos[1]));
                    case "Armadura" -> setArm(Integer.parseInt(campos[1]));
                    case "Velocidad" -> setVel(Integer.parseInt(campos[1]));
                    case "Resistencia mágica" -> setRes(Integer.parseInt(campos[1]));
                    case "Nivel" -> setNivel(Integer.parseInt(campos[1]));
                    case "Raza" -> setRaza(campos[1]);
                }
            }
            br.close();
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
                case "canido" -> statsMascotita(0.20, 0.20, 0.20, 0.20, 0.20, nivel, raza);
                case "felino" -> statsMascotita(0.15, 0.30, 0.15, 0.30, 0.15, nivel, raza);
                case "rapaz" -> statsMascotita(0.05, 0.15, 0.05, 0.35, 0.25, nivel, raza);
                default -> System.err.println("Raza no disponible");
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

        @Override
        public void setRaza(String raza) {
            if (raza.equalsIgnoreCase("canido") ||
                    raza.equalsIgnoreCase("felino") ||
                    raza.equalsIgnoreCase("rapaz"))
                super.settearRaza(raza);
            else super.settearRaza("");
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
         * Devuelve una “tarjeta” con la información principal de la mascota.
         *
         * @return cadena con los atributos y estadísticas de la mascota.
         */


        @Override
        public String cartita() {
            return "\n── ⟢ ・⸝⸝  \uD835\uDC74\uD835\uDC82\uD835\uDC94\uD835\uDC84\uD835\uDC90\uD835\uDC95\uD835\uDC8A\uD835\uDC95\uD835\uDC82 .ᐟ.ᐟ\n" +
                    "\n· Nombre: "  + getNombre() +
                    "\n· Raza: "  + getRaza() +
                    "\n   · Vida: " + getPv() +
                    "\n   · Ataque: " + getAtq() +
                    "\n   · Armadura: " + getArm() +
                    "\n   · Velocidad: " + getVel() +
                    "\n   · Resistencia mágica: " + getRes() +
                    "\n   · Nivel: " + getNivel();
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

        return switch (mascota.getRaza()) {

            case "canido" ->  "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡀⣀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
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
            case "felino" ->  "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
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
            case "rapaz" ->  "⠀⠀⠀⠀⠀⠀⢀⣠⠴⠒⠒⠒⠒⠒⠶⠦⠤⠴⠒⠚⠉⣰⠟⠁⠀⠀⠀⠀⠀⠀\n" +
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
            default -> "";
        };
    }
}

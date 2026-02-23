package Characters;

import java.io.*;
import java.util.*;

import GameMap.Trampa;
import Characters.Factory;


/**
 * Clase abstracta que representa un personaje del juego.
 * Contiene las estadísticas básicas, lógica de combate y control de turnos.
 *
 * @author Isa Barceló
 * @version 200.0
 */

public abstract class Personaje implements Comparable<Personaje> {

    private String nombre, tipoAtaque, raza;
    private int pv, atq, arm, nivel, res, vel, otro;
    private boolean def;

    /**
     * Constructor por defecto.
     * Inicializa los atributos con valores base.
     */

    public Personaje() {
        atq = arm = nivel = vel = res = 10;
        pv = 100;
        nombre = "";
        tipoAtaque = "fisico";
        def = false;
        raza = "humano";
    }

    /**
     * Constructor por parámetros.
     *
     * @param nombre Nombre del personaje
     * @param pv     Puntos de vida
     * @param atq    Ataque
     * @param arm    Armadura (defensa física)
     * @param nivel  Nivel del personaje
     * @param vel    Velocidad del personaje
     * @param res    Resistencia mágica
     */

    public Personaje(String nombre, int pv, int atq, int arm, int nivel, int vel, int res) {
        setNombre(nombre);
        setPv(pv);
        setAtq(atq);
        setArm(arm);
        setNivel(nivel);
        setVel(vel);
        setRes(res);
        setTipoAtaque("fisico");
        setDef(false);
        setRaza("humano");
    }

    public Personaje(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        for (int i = 0; i < 3; i++) {
            br.readLine();
        }

        String[] campos = new String[2];
        String linea;
        while ((linea = br.readLine()) != null) {
            campos = linea.split(": ");

            switch (campos[0]) {
                case "· Nombre" -> setNombre(campos[1]);
                case "   · Vida" -> setPv(Integer.parseInt(campos[1]));
                case "   · Ataque" -> setAtq(Integer.parseInt(campos[1]));
                case "   · Armadura" -> setArm(Integer.parseInt(campos[1]));
                case "   · Velocidad" -> setVel(Integer.parseInt(campos[1]));
                case "   · Resistencia mágica" -> setRes(Integer.parseInt(campos[1]));
                case "   · Nivel" -> setNivel(Integer.parseInt(campos[1]));
                case "   · Raza" -> setRaza(campos[1]);
                default -> setOtro(Integer.parseInt(campos[1]));
            }
        }
        br.close();
    }

    // Estoy cansada, jefe

    public void updtPJ(File file) throws IOException {
        Personaje playerFicheado = Factory.crear(String.valueOf(this.getClass()), file);

        if (!this.getNombre().equals(playerFicheado.getNombre())) {
            return;
        }

        if (!this.equals(playerFicheado))
            this.copyCat(playerFicheado);
    }

    protected void copyCat(Personaje player) {
        this.setPv(player.getPv());
        this.setAtq(player.getAtq());
        this.setArm(player.getArm());
        this.setVel(player.getVel());
        this.setRes(player.getRes());
        this.setNivel(player.getNivel());
        this.setRaza(player.getRaza());
        this.setOtro(player.getOtro());
    }

   /* public Personaje(String nombre) {
        Personaje player = GameTest.randomizaPersonaje(nombre);

        setNombre(nombre);
        setNivel(player.getNivel());
        setPv(player.getPv());
        setAtq(player.getAtq());
        setArm(player.getArm());
        setVel(player.getVel());
        setRes(player.getRes());

    } */

    /* public Personaje(String nombre, int nivelFinal) {
        Personaje player = GameTest.randomizaPersonaje(nombre);
        int nivel = player.getNivel();

        setNombre(nombre);
        setNivel(nivel);
        setPv(player.getPv());
        setAtq(player.getAtq());
        setArm(player.getArm());

        while (getNivel() < nivelFinal) {
            setPv(getPv() + 1);
            setAtq(getAtq() + 1);
            setArm(getArm() + 1);
            setNivel(getNivel() + 1);
        }
    } */

    /**
     * Constructor copia.
     *
     * @param copia Personaje a copiar
     */

    public Personaje(Personaje copia) {
        setNombre(copia.getNombre());
        setPv(copia.getPv());
        setAtq(copia.getAtq());
        setArm(copia.getArm());
        setNivel(copia.getNivel());
        setVel(copia.getVel());
        setRes(copia.getRes());
        setTipoAtaque(copia.getTipoAtaque());
        setRaza(copia.getRaza());
    }

    /**
     * Devuelve la resistencia mágica.
     *
     * @return resistencia mágica
     */

    public int getRes() {
        return res;
    }

    /**
     * Modifica la resistencia mágica.
     *
     * @param res nueva resistencia mágica
     */

    public void setRes(int res) {
        if (res <= 0)
            this.res = 0;
        else this.res = res;
    }

    public void setOtro(int otro) {
        this.otro = otro;
    }

    public int getOtro() {
        return otro;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getRaza() {
        return raza;
    }

    /**
     * Devuelve la velocidad del personaje.
     *
     * @return velocidad
     */

    public int getVel() {
        return vel;
    }

    /**
     * Modifica la velocidad del personaje.
     *
     * @param vel nueva velocidad
     */

    public void setVel(int vel) {
        if (vel <= 0)
            this.vel = 0;
        else this.vel = vel;
    }

    /**
     * Modifica el nombre del personaje.
     *
     * @param nombre nuevo nombre
     */

    public void setNombre(String nombre) {

        if (nombre.isEmpty() || nombre.contains("GM") || nombre.length() < 2) {
            System.err.println("Error. El nombre debe tener caracteres válidos.");
        } else this.nombre = nombre;
    }

    /**
     * Modifica los puntos de vida.
     *
     * @param pv nuevos puntos de vida
     */

    public void setPv(int pv) {
        if (pv < 0) {
            this.pv = 0;
        } else {
            this.pv = pv;
        }
    }

    /**
     * Modifica el ataque.
     *
     * @param atq nuevo ataque
     */

    public void setAtq(int atq) {
        if (atq < 0) {
            System.err.println("Error. El ataque debe ser mayor o igual a 0.");
            this.atq = 0;
        } else {
            this.atq = atq;
        }
    }

    /**
     * Modifica la armadura (defensa física).
     *
     * @param arm nueva armadura
     */

    public void setArm(int arm) {
        if (arm < 0) {
            System.err.println("Error. La defensa debe ser mayor o igual a 0.");
            this.arm = 0;
        } else {
            this.arm = arm;
        }
    }

    /**
     * Modifica el nivel del personaje.
     *
     * @param nivel nuevo nivel
     */

    public void setNivel(int nivel) {
        if (nivel < 1 || nivel > 100) {
            System.err.println("Error. El nivel debe estar entre 1 y 100.");
        } else this.nivel = nivel;
    }

    /**
     * Activa o desactiva el estado defensivo.
     *
     * @param def estado defensivo
     */

    public void setDef(boolean def) {
        this.def = def;
    }

    /**
     * Devuelve el nombre del personaje.
     *
     * @return nombre
     */

    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve los puntos de vida.
     *
     * @return puntos de vida
     */

    public int getPv() {
        return pv;
    }

    /**
     * Devuelve el ataque.
     *
     * @return ataque
     */

    public int getAtq() {
        return atq;
    }

    /**
     * Devuelve la armadura.
     *
     * @return armadura
     */

    public int getArm() {
        return arm;
    }

    /**
     * Devuelve el nivel del personaje.
     *
     * @return nivel
     */

    public int getNivel() {
        return nivel;
    }

    /**
     * Permite al personaje beber una poción.
     *
     * @param pocion cantidad de vida recuperada
     */

    public void beberPocion(int pocion) {
        if (getPv() <= 30) {
            setPv(getPv() + pocion);
            System.out.println("\n" + getNombre() + ", se ha bebido una poción.. su vida sube ahora es " + getPv());
        }
    }

    /**
     * Incrementa temporalmente una estadística.
     *
     * @param cantidad cantidad a aumentar
     * @param tipo     tipo de estadística
     */

    public void inspirar(int cantidad, String tipo) {
        switch (tipo) {
            case "ataque":
                setAtq(getAtq() + cantidad);
                System.out.println("\n" + getNombre() + ", se ha inspirado.. su ataque sube ahora es " + getAtq() + details(2));
                break;
            case "defensa":
                setArm(getArm() + cantidad);
                System.out.println("\n" + getNombre() + ", se ha inspirado.. su armadura sube ahora es " + getArm() + details(2));
                break;
            default:
                System.err.println("Error. Introduzca ataque o defensa.");
        }
    }

    /**
     * Determina si ocurre un evento según una probabilidad.
     *
     * @param pct porcentaje de probabilidad
     * @return true si ocurre el evento
     */

    protected boolean prob(int pct) {
        Random r = new Random();
        return r.nextInt(100) < pct;
    }

    /**
     * Aumenta el nivel del personaje con probabilidad independiente por estadística.
     */

    public void subirNivel() {
        if (prob(50))
            setPv(getPv() + 1);

        if (prob(50))
            setAtq(getAtq() + 1);

        if (prob(50))
            setArm(getArm() + 1);

        if (prob(50))
            setRes(getRes() + 1);

        if (prob(50))
            setVel(getVel() + 1);

        setNivel(getNivel() + 1);
        System.out.println(getNombre() + ", ¡ha subido de nivel!\n\n" + toString());
    }

    /**
     * Comprueba si el personaje está muerto.
     *
     * @return true si los puntos de vida son 0 o menos
     */

    public boolean estaMuerto() {
        if (pv <= 0) {
            return true;
        } else return false;
    }

    /* public Personaje clone() {
        Personaje clon = new Personaje(this.nombre, this.pv, this.atq, this.def, this.nivel);
        return clon;
    }
    */

    /**
     * Compara dos personajes.
     *
     * @param otro personaje a comparar
     * @return true si son iguales
     */

    public boolean equals(Personaje otro) {
        boolean comparacion = true;
        if (!this.nombre.equals(otro.nombre) ||
                this.pv != otro.pv ||
                this.atq != otro.atq ||
                this.arm != otro.arm ||
                this.nivel != otro.nivel ||
                this.vel != otro.vel ||
                this.res != otro.res)
            comparacion = false;
        return comparacion;
    }

    /**
     * Devuelve una representación en texto del personaje.
     *
     * @return información del personaje
     */

    public String toString() {
        String resultado = "Cargando datos del personaje.. ૮ ․ ․ ྀིა " +
                "\n\t· Nombre: " + getNombre() +
                "\n\t· Vida: " + getPv() +
                "\n\t· Ataque: " + getAtq() +
                "\n\t· Armadura: " + getArm() +
                "\n\t· Velocidad: " + getVel() +
                "\n\t· Resistencia mágica: " + getRes() +
                "\n\t· Nivel: " + getNivel();
        return coquetudo() + "\n\n" + resultado;
    }

    public String cartita() {
        return "₊˚ ‿︵‿︵‿︵୨୧ · · ♡ · · ୨୧‿︵‿︵‿︵ ˚₊\n" +
                "\n· Nombre: " + getNombre() +
                "\n   · Vida: " + getPv() +
                "\n   · Ataque: " + getAtq() +
                "\n   · Armadura: " + getArm() +
                "\n   · Velocidad: " + getVel() +
                "\n   · Resistencia mágica: " + getRes() +
                "\n   · Nivel: " + getNivel();
    }

    /**
     * Aplica los efectos de una trampa.
     *
     * @param t trampa activada
     */

    public void caerTrampa(Trampa t) {

        int perjuicioT = t.activaTrampa();

        if (perjuicioT > 0) {

            switch (t.getTipo()) {

                case "Pinchos":
                    setPv(getPv() - perjuicioT);
                    System.out.println("\n\t" + anderlain("Estacas afiladas") + " salen de las superficies cercanas y atraviesan a " + getNombre() + " por " + perjuicioT + " puntos de daño..\n");
                    printPv(this);
                    break;
                case "Brea":
                    setArm(getArm() - perjuicioT);
                    System.out.println("\n\t" + anderlain("Aceite viscoso") + " cae sobre " + getNombre() + ", arruinando su armadura..\n\t· Armadura: " + getArm() + "\n");
                    break;
                case "Serpientes":
                    setAtq(getAtq() - perjuicioT);
                    System.out.println("\n\tUn " + anderlain("nido de víboras") + " aparece frente a " + getNombre() + ". La visión es tan terrorífica que pierde las fuerza para atacar..\n\t· Ataque" + getAtq() + "\n");
                    break;
            }
        } else {
            System.out.println("\n\tAfortunadamente, ¡" + getNombre() + " escapó de la trampa indemne!");
        }
    }

    /**
     * Devuelve el daño de ataque del personaje.
     *
     * @return daño de ataque
     */

    public int atacar() {
        return getAtq();
    }

    /**
     * Calcula el daño recibido según el tipo de daño.
     *
     * @param dañoHecho daño infligido
     * @param tipoDaño  tipo de daño (físico o mágico)
     * @return daño recibido
     */

    public int defender(int dañoHecho, String tipoDaño) {

        int dañoRecibido = 0;

        switch (tipoDaño) {
            case "fisico":
                dañoRecibido = dañoHecho - getArm();
                if (dañoRecibido < 0)
                    dañoRecibido = 0;
                break;
            case "magico":
                dañoRecibido = dañoHecho - getRes();
                if (dañoRecibido < 0)
                    dañoRecibido = 0;
        }

        return dañoRecibido;
    }

    /**
     * Aplica el daño recibido al personaje.
     *
     * @param dañoHecho daño infligido
     * @param tipoDaño  tipo de daño
     */

    public void defensa(int dañoHecho, String tipoDaño) {
        this.setPv(this.getPv() - this.defender(dañoHecho, tipoDaño));
    }

    /**
     * Muestra los puntos de vida de un personaje.
     *
     * @param player personaje a mostrar
     */

    public void printPv(Personaje player) {
        System.out.println("\t· Su vida actual es de: " + player.getPv());
    }

    /**
     * Activa la postura defensiva del personaje.
     */

    public void defensaUppie() {
        setDef(true);

        setArm(getArm() + ((int) (getArm() * 0.2)));
        setRes(getRes() + ((int) (getRes() * 0.2)));
        System.out.println(getNombre() + " adopta una postura defensiva.. \nSus stats mejoran" + details(5) + "\n\t· Armadura: " + getArm() + "\n\t· Resistencia: " + getRes());
    }

    /**
     * Desactiva la postura defensiva del personaje.
     */

    public void defensaDown() {
        setArm((int) (getArm() * 0.8));
        setRes((int) (getRes() * 0.8));
        System.out.println("\n" + getNombre() + " se relaja.. \nSus stats vuelven a la normalidad, pero aumenta su cansancio" + details(4) + "\n\t· Armadura: " + getArm() + "\n\t· Resistencia: " + getRes());
    }

    /**
     * Acción especial del personaje.
     *
     * @param enemigo personaje enemigo
     */

    public void accEspesial(Personaje enemigo) {
        System.out.println("Este personaje no tiene acción especial.." + details(4));
    }

    /**
     * Gestiona el turno del personaje.
     *
     * @param enemigo personaje enemigo
     */

    public void realizarTurno(Personaje enemigo) {
        int opcion;

        if (def) {
            this.setDef(false);
            this.defensaDown();
        }

        System.out.println("\nIniciando turno .ᐟ.ᐟ \n\t⤷ Turno de: " + this.getNombre() + details(5) + "\n");

        Scanner scan = new Scanner(System.in);

        do {
            menusito("¿Qué acción quiere realizar?", new String[]{"Atacar", "Acción Especial", "Defender", "Pasar turno"}, 0);
            opcion = scan.nextInt();

            switch (opcion) {
                case 1 -> this.ataqueCoquetudo(enemigo);
                case 2 -> this.accEspesial(enemigo);
                case 3 -> this.defensaUppie();
                case 4 -> System.out.println("\n" + getNombre() + " decide pasar el turno.." + details(4));
            }
        } while (opcion > 4 || opcion < 1);
    }

    /**
     * Realiza un ataque con mensajes personalizados.
     *
     * @param enemigo personaje enemigo
     */

    public void ataqueCoquetudo(Personaje enemigo) {
        int dañito = enemigo.defender(this.atacar(), this.getTipoAtaque());
        if (dañito <= 0)
            System.out.println("\n" + this.getNombre() + " decide atacar a " + enemigo.getNombre() + " pero no le hace ni cosquillas.." + details(4));
        else
            System.out.println("\n" + this.getNombre() + " decide atacar a " + enemigo.getNombre() + " haciéndole " + dañito + " de daño.." + details(5));
        enemigo.defensa(this.atacar(), this.getTipoAtaque());
        printPv(enemigo);
    }

    /**
     * Modifica el tipo de ataque del personaje.
     *
     * @param tipoAtaque tipo de ataque
     */

    public void setTipoAtaque(String tipoAtaque) {
        this.tipoAtaque = tipoAtaque;
    }

    /**
     * Devuelve el tipo de ataque del personaje.
     *
     * @return tipo de ataque
     */

    public String getTipoAtaque() {
        return tipoAtaque;
    }

    /**
     * Muestra un menú de opciones.
     *
     * @param mensaje  mensaje principal
     * @param opciones opciones disponibles
     * @param detail   nivel de detalle visual
     */

    public void menusito(String mensaje, String[] opciones, int detail) {
        System.out.println(details(1) + "\n" + mensaje + details(detail));

        for (int i = 0; i < opciones.length; i++) {
            int index = i + 1;
            System.out.println("\t" + index + ". " + opciones[i]);
        }

        printDetallito();
    }

    // A partir de aquí, son solo métodos de decoración, am sori

    /**
     * Devuelve una representación ASCII decorativa del personaje.
     *
     * @return cadena decorativa
     */

    public String coquetudo() {
        return "⠀⠀⠀⠀⠀⠀⠀⠀⡤⠤⠤⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⣀⣠⡤⠤⢤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⢠⡤⣤⣤⡀⠀⠀⠀⠀⠀⢘⢇⠀⠀⠀⠘⠙⠢⣀⠀⠀⠀⠀⠀⢀⡖⠋⠁⠀⠀⠀⡼⠇⠀⠀⠀⠀⠀⢀⣤⣤⡦⡄⠀⠀⠀⠀\n" +
                "⣴⢖⣄⠀⠸⣅⢀⣠⠇⠀⣴⢖⣄⠀⠾⠋⠁⠀⠀⠀⠀⠀⠹⣾⠏⠻⠋⢂⠟⠀⠀⠀⠀⠒⠀⠉⡗⠀⢰⣶⣦⠀⠘⢄⡀⣱⠟⠀⢠⣶⣦\n" +
                "⠈⠛⠀⠀⠀⠈⠛⠁⠀⠀⠈⠛⠁⠀⠉⢲⠀⠀⠀⠀⠀⠀⣠⠟⢮⣄⡴⠛⣤⡀⠀⠀⠀⠀⠀⡞⠃⠀⠀⠛⠁⠀⠀⠀⠹⠋⠀⠀⠀⠙⠁\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠙⠤⡶⠋⠙⠁⠀⠀⠙⠁⠀⠈⠉⠙⢲⢴⠟⠙⠁⠀⠀\n";
    }

    /**
     * Devuelve un detalle visual según el tipo indicado.
     *
     * @param opcion identificador del detalle
     * @return cadena con el detalle visual
     */

    public String details(int opcion) {

        return switch (opcion) {
            case 1 -> " ₊˚ ‿︵‿୨୧ · · ♡ · · ୨୧‿︵‿ ˚₊";
            case 2 -> " ۶ৎ";
            case 3 -> " ฅᨐฅ";
            case 4 -> " ૮ ྀིᴗ͈ . ᴗ͈ ྀིა.ᐟ";
            case 5 -> " ദ്ദി◝ ⩊ ◜.ᐟ";
            case 6 -> " ٩(•̤̀ᵕ•̤́๑)ˡᵉᵗ'ˢ ᵍᵒᵎᵎᵎᵎ";
            default -> "";
        };
    }

    /**
     * Muestra un símbolo decorativo de entrada por consola.
     */

    public void printDetallito() {
        System.out.printf("› ");
    }

    /**
     * Muestra un mensaje decorativo por consola.
     *
     * @param acc mensaje a mostrar
     */

    public void printPerezita(String acc) {
        System.out.println("\t\t" + acc + "\n");
    }

    /**
     * Devuelve un texto subrayado para mostrar por consola.
     *
     * @param opcion texto a subrayar
     * @return texto formateado
     */

    public String anderlain(String opcion) {
        return "\033[0;4m" + opcion + "\033[0;0m";
    }

    //

    @Override
    public int compareTo(Personaje player) {
        return Integer.compare(this.getVel(), player.getVel());
    }
}



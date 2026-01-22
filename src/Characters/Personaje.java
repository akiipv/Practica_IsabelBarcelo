package Characters;

import java.util.Random;
import java.util.Scanner;

import GameMap.Trampa;
import Misc.GameTest;

public abstract class Personaje {

    private String nombre, tipoAtaque;
    private int pv, atq, arm, nivel, res, vel;

    public Personaje() {
        atq = arm = nivel = vel = res = 10;
        pv = 100;
        nombre = "";
        tipoAtaque = "fisico";
    }

    public Personaje(String nombre, int pv, int atq, int arm, int nivel, int vel, int res) {
        setNombre(nombre);
        setPv(pv);
        setAtq(atq);
        setArm(arm);
        setNivel(nivel);
        setVel(vel);
        setRes(res);
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

    public Personaje(Personaje copia) {
        this.nombre = copia.nombre;
        this.pv = copia.pv;
        this.atq = copia.atq;
        this.arm = copia.arm;
        this.nivel = copia.nivel;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public int getVel() {
        return vel;
    }

    public void setVel(int vel) {
        this.vel = vel;
    }

    public void setNombre(String nombre) {

        if (nombre.isEmpty() || nombre.contains("GM") || nombre.length() < 2) {
            System.err.println("Error. El nombre debe tener caracteres válidos.");
        } else this.nombre = nombre;
    }

    public void setPv(int pv) {
        if (pv < 0) {
            this.pv = 0;
        } else {
            this.pv = pv;
        }
    }

    public void setAtq(int atq) {
        if (atq < 0) {
            System.err.println("Error. El ataque debe ser mayor o igual a 0.");
            this.atq = 0;
        } else {
            this.atq = atq;
        }
    }

    public void setArm(int arm) {
        if (arm < 0) {
            System.err.println("Error. La defensa debe ser mayor o igual a 0.");
            this.arm = 0;
        } else {
            this.arm = arm;
        }
    }

    public void setNivel(int nivel) {
        if (nivel < 1 || nivel > 100) {
            System.err.println("Error. El nivel debe estar entre 1 y 100.");
        } else this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPv() {

        return pv;
    }

    public int getAtq() {

        return atq;
    }

    public int getArm() {

        return arm;
    }

    public int getNivel() {

        return nivel;
    }

    public void beberPocion(int pocion) {
        if (pv <= 30) {
            pv += pocion;
            System.out.println("El jugador: " + getNombre() + ", se ha bebido una poción.. su vida sube a: " + pv);
        }
    }

    public void inspirar(int cantidad, String tipo) {
        switch (tipo) {
            case "ataque":
                atq += cantidad;
                System.out.println("El jugador: " + getNombre() + ", se ha inspirado.. su ataque sube a: " + atq);
                break;
            case "defensa":
                arm += cantidad;
                System.out.println("El jugador: " + getNombre() + ", se ha inspirado.. su defensa sube a: " + arm);
                break;
            default:
                System.err.println("Error. Introduzca ataque o defensa.");
        }
    }

    protected boolean prob(int pct) {
        Random r = new Random();
        return r.nextInt(100) < pct;
    }

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
        System.out.println(getNombre() + ", ¡ha subido de nivel!\n\t" + toString());
    }

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

    public String toString() {
        String resultado = "Cargando datos del personaje.. ૮ ․ ․ ྀིა " +
                "\n\t· Nombre: " + nombre + "" +
                "\n\t· Vida: " + pv +
                "\n\t· Ataque: " + atq +
                "\n\t· Armadura: " + arm +
                "\n\t· Nivel: " + nivel;
        return coquetudo() + "\n\n" + resultado;
    }

    public void caerTrampa(Trampa t) {

        int perjuicioT = t.activaTrampa();

        if (perjuicioT > 0) {

            switch (t.getTipo()) {

                case "Pinchos":
                    setPv(getPv() - perjuicioT);
                    System.out.println("Estacas afiladas salen de las superficies cercanas y " + "atraviesan a " + nombre + " por " + perjuicioT + " puntos de daño.. ahora su vida es de: " + pv + ".");
                    break;
                case "Brea":
                    setArm(getArm() - perjuicioT);
                    System.out.println("Aceite viscoso cae de pronto sobre " + nombre + ", impidiéndole moverse con libertad.. ahora su defensa es de: " + arm + ".");
                    break;
                case "Serpientes":
                    setAtq(getAtq() - perjuicioT);
                    System.out.println("Un nido de víboras aparece frente a " + nombre + ". La visión es tan terrorífica que pierde las ganas de continuar.. ahora su ataque es de: " + atq + ".");
                    break;
            }

        } else {

            System.out.println("Afortunadamente, ¡" + nombre + " escapó de la trampa indemne!");
        }
    }

    public int atacar() {
        return getAtq();
    }

    /**
     * todo creo q está hacido pero miralo luego y corrígelo sorrita jejejejeje
     */

    public int defender(int dañoHecho, String tipoDaño) {

        int dañoRecibido = 0;

        switch (tipoDaño) {
            case "fisico":
                dañoRecibido = dañoHecho - arm;
                if (dañoRecibido < 0)
                    dañoRecibido = 0;
                break;
            case "magico":
                dañoRecibido = dañoHecho - res;
                if (dañoRecibido < 0)
                    dañoRecibido = 0;
        }

        return dañoRecibido;
    }

    /**
     * todo maldision
     */

    public void accEspesial(){
        System.out.println("Acción especial no implementada.");
    }

    public int realizarTurno() {
        //depue
        int opcion;
        int turno = 0;

        Scanner scan = new Scanner(System.in);

        System.out.println("¿Qué acción quiere realizar? " +
                "\n\t1. Atacar" +
                "\n\t2. Acción Especial" +
                "\n\t3. Defender" +
                "\n\t4. Pasar turno");
        opcion = scan.nextInt();

        switch (opcion) {
            case 1:
                System.out.println(nombre + " decide atacar.");
                atacar();
                break;
            case 2:
                accEspesial();
                break;
            case 3:
                System.out.println(nombre + " adopta una postura defensiva.");
                setArm((getArm() / 10) * 2);
                setRes((getRes() / 10) * 2);
                break;
            case 4:
                System.out.println(nombre + " pasa el turno.");
                break;
            default:
                System.out.println("Opción no válida.");
        }

        turno++;

        if (opcion == 3) { setArm((getArm() / 2) * 10); setRes((getRes() / 2) * 10); }

        return opcion;
    }

    public void setTipoAtaque(String tipoAtaque) {
        this.tipoAtaque = tipoAtaque;
    }

    public String getTipoAtaque() {
        return tipoAtaque;
    }

    public  String coquetudo(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡤⠤⠤⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⡤⠤⢤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⢠⡤⣤⣤⡀⠀⠀⠀⠀⠀⢘⢇⠀⠀⠀⠘⠙⠢⣀⠀⠀⠀⠀⠀⢀⡖⠋⠁⠀⠀⠀⡼⠇⠀⠀⠀⠀⠀⢀⣤⣤⡦⡄⠀⠀⠀⠀\n" +
                "⣴⢖⣄⠀⠸⣅⢀⣠⠇⠀⣴⢖⣄⠀⠾⠋⠁⠀⠀⠀⠀⠀⠹⣾⠏⠻⠋⢂⠟⠀⠀⠀⠀⠒⠀⠉⡗⠀⢰⣶⣦⠀⠘⢄⡀⣱⠟⠀⢠⣶⣦\n" +
                "⠈⠛⠀⠀⠀⠈⠛⠁⠀⠀⠈⠛⠁⠀⠉⢲⠀⠀⠀⠀⠀⠀⣠⠟⢮⣄⡴⠛⣤⡀⠀⠀⠀⠀⠀⡞⠃⠀⠀⠛⠁⠀⠀⠀⠹⠋⠀⠀⠀⠙⠁\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠙⠤⡶⠋⠙⠁⠀⠀⠙⠁⠀⠈⠉⠙⢲⢴⠟⠙⠁⠀⠀\n";
    }
}



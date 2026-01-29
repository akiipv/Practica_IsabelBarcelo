package Characters;

import java.util.Random;
import java.util.Scanner;

import GameMap.Trampa;

public abstract class Personaje {

    private String nombre, tipoAtaque;
    private int pv, atq, arm, nivel, res, vel;
    private boolean def;

    public Personaje() {
        atq = arm = nivel = vel = res = 10;
        pv = 100;
        nombre = "";
        tipoAtaque = "fisico";
        def = false;
    }

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
        this.def = copia.def;
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

    public void setDef(boolean def) {
        this.def = def;
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
        if (getPv() <= 30) {
            setPv(getPv() + pocion);
            System.out.println("\n" + getNombre() + ", se ha bebido una poción.. su vida sube ahora es " + getPv());
        }
    }

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
        System.out.println(getNombre() + ", ¡ha subido de nivel!\n\n" + toString());
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
                "\n\t· Nombre: " + getNombre() +
                "\n\t· Vida: " + getPv() +
                "\n\t· Ataque: " + getAtq() +
                "\n\t· Armadura: " + getArm() +
                "\n\t· Velocidad: " + getVel() +
                "\n\t· Resistencia mágica: " + getRes() +
                "\n\t· Nivel: " + getNivel();
        return coquetudo() + "\n\n" + resultado;
    }

    public void caerTrampa(Trampa t) {

        int perjuicioT = t.activaTrampa();

        if (perjuicioT > 0) {

            switch (t.getTipo()) {

                case "Pinchos":
                    setPv(getPv() - perjuicioT);
                    System.out.println("\n\t" + anderlain("Estacas afiladas") + " salen de las superficies cercanas y atraviesan a " + getNombre() + " por " + perjuicioT + " puntos de daño..");
                    printPv(this);
                    break;
                case "Brea":
                    setArm(getArm() - perjuicioT);
                    System.out.println("\n\t" + anderlain("Aceite viscoso") + " cae sobre " + getNombre() + ", arruinando su armadura..\n\t· Armadura: " + getArm());
                    break;
                case "Serpientes":
                    setAtq(getAtq() - perjuicioT);
                    System.out.println("\n\tUn " + anderlain("nido de víboras") + " aparece frente a " + getNombre() + ". La visión es tan terrorífica que pierde las fuerza para atacar..\n\t· Ataque" + getAtq());
                    break;
            }
        } else {
            System.out.println("\n\tAfortunadamente, ¡" + getNombre() + " escapó de la trampa indemne!");
        }
    }

    public int atacar() {
        return getAtq();
    }

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

    public void defensa(int dañoHecho, String tipoDaño) {
        this.setPv(this.getPv() - this.defender(dañoHecho, tipoDaño));
    }

    public void printPv(Personaje player) {
        System.out.println("\t· Su vida actual es de: " + player.getPv());
    }

    public void defensaUppie() {
        setDef(true);

        setArm(getArm() + ((int) (getArm() * 0.2)));
        setRes(getRes() + ((int) (getRes() * 0.2)));
        System.out.println(getNombre() + " adopta una postura defensiva.. \nSus stats mejoran" + details(5) + ":\n\t· Armadura: " + getArm() + "\n\t· Resistencia: " + getRes());
    }

    public void defensaDown() {
        setArm((int) (getArm() * 0.8));
        setRes((int) (getRes() * 0.8));
        System.out.println("\n" + getNombre() + " se relaja.. \nSus stats vuelven a la normalidad" + details(4) + ":\n\t· Armadura: " + getArm() + "\n\t· Resistencia: " + getRes());
    }

    public void accEspesial(Personaje enemigo) {
        System.out.println("Acción especial no implementada.." + details(4));
    }

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
                case 1:
                    this.ataqueCoquetudo(enemigo);
                    break;
                case 2:
                    this.accEspesial(enemigo);
                    break;
                case 3:
                    this.defensaUppie();
                    break;
                case 4:
                    System.out.println("\n" + getNombre() + " decide pasar el turno.." + details(4));
                    break;
            }
        } while (opcion > 4);
    }

    public void ataqueCoquetudo(Personaje enemigo) {
        int dañito = enemigo.defender(this.atacar(), this.getTipoAtaque());
        if (dañito <= 0)
            System.out.println("\n" + this.getNombre() + " decide atacar a " + enemigo.getNombre() + " pero no le hace ni cosquillas.." + details(4));
        else
            System.out.println("\n" + this.getNombre() + " decide atacar a " + enemigo.getNombre() + " haciéndole " + dañito + " de daño.." + details(5));
        enemigo.defensa(this.atacar(), this.getTipoAtaque());
        printPv(enemigo);
    }

    public void setTipoAtaque(String tipoAtaque) {
        this.tipoAtaque = tipoAtaque;
    }

    public String getTipoAtaque() {
        return tipoAtaque;
    }

    public void menusito(String mensaje, String[] opciones, int detail) {
        System.out.println(details(1) + "\n" + mensaje + details(detail));

        for (int i = 0; i < opciones.length; i++) {
            int index = i + 1;
            System.out.println("\t" + index + ". " + opciones[i]);
        }

        printDetallito();
    }

    // A partir de aquí, son solo métodos de decoración, am sori

    public String coquetudo() {
        return "⠀⠀⠀⠀⠀⠀⠀⠀⡤⠤⠤⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⣀⣠⡤⠤⢤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⢠⡤⣤⣤⡀⠀⠀⠀⠀⠀⢘⢇⠀⠀⠀⠘⠙⠢⣀⠀⠀⠀⠀⠀⢀⡖⠋⠁⠀⠀⠀⡼⠇⠀⠀⠀⠀⠀⢀⣤⣤⡦⡄⠀⠀⠀⠀\n" +
                "⣴⢖⣄⠀⠸⣅⢀⣠⠇⠀⣴⢖⣄⠀⠾⠋⠁⠀⠀⠀⠀⠀⠹⣾⠏⠻⠋⢂⠟⠀⠀⠀⠀⠒⠀⠉⡗⠀⢰⣶⣦⠀⠘⢄⡀⣱⠟⠀⢠⣶⣦\n" +
                "⠈⠛⠀⠀⠀⠈⠛⠁⠀⠀⠈⠛⠁⠀⠉⢲⠀⠀⠀⠀⠀⠀⣠⠟⢮⣄⡴⠛⣤⡀⠀⠀⠀⠀⠀⡞⠃⠀⠀⠛⠁⠀⠀⠀⠹⠋⠀⠀⠀⠙⠁\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠙⠤⡶⠋⠙⠁⠀⠀⠙⠁⠀⠈⠉⠙⢲⢴⠟⠙⠁⠀⠀\n";
    }

    public String details(int opcion) {

        switch (opcion) {
            case 1:
                return " ₊˚ ‿︵‿୨୧ · · ♡ · · ୨୧‿︵‿ ˚₊";
            case 2:
                return " ۶ৎ";
            case 3:
                return " ฅᨐฅ";
            case 4:
                return " ૮ ྀིᴗ͈ . ᴗ͈ ྀིა.ᐟ";
            case 5:
                return " ദ്ദി◝ ⩊ ◜.ᐟ";
            case 6:
                return " ٩(•̤̀ᵕ•̤́๑)ˡᵉᵗ'ˢ ᵍᵒᵎᵎᵎᵎ";
            default:
                return "";
        }
    }

    public void printDetallito() {
        System.out.printf("› ");
    }

    public void printPerezita(String acc) {
        System.out.println("\t\t" + acc + "\n");
    }

    public String anderlain(String opcion) {
        return "\033[0;4m" + opcion + "\033[0;0m";
    }
}



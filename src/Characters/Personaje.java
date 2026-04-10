package Characters;

import java.io.*;
import java.util.*;

import GameMap.Trampa;
import Gear.Arma;
import Gear.Armadura;
import Gear.Artefacto;
import Manolo.DWritersito;


/**
 * Clase abstracta que representa un personaje del juego.
 * Contiene las estadísticas básicas, lógica de combate y control de turnos.
 *
 * @author Isa Barceló
 * @version 200.0
 */

public abstract class Personaje implements Comparable<Personaje> {

    /** Nombre, tipo de ataque y raza del personaje. */
    private String nombre, tipoAtaque, raza;
    /** Vida, ataque, armadura, nivel, resistencia mágica y velocidad del personaje. */
    private int pv, atq, arm, nivel, res, vel;
    /** ¿Está defendiendo? */
    private boolean def;
    private HashMap<String, Armadura> armaduras;
    private ArrayList<Artefacto> artefactos;
    private Arma arma;


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
        raza = "Humano";
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
        setRaza("Humano");
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
     * @param raza   Raza
     */

    public Personaje(String nombre, int pv, int atq, int arm, int nivel, int vel, int res, String raza) {
        setNombre(nombre);
        setPv(pv);
        setAtq(atq);
        setArm(arm);
        setNivel(nivel);
        setVel(vel);
        setRes(res);
        setTipoAtaque("fisico");
        setDef(false);
        setRaza(raza);
    }

    /**
     * Constructor que inicializa un personaje a partir de un archivo.
     *
     * @param file archivo con la información del personaje
     * @throws IOException si ocurre un error al leer el archivo
     */

    public Personaje(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        for (int i = 0; i < 3; i++) {
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
                case "Puntos de fe", "Puntos de magia", "Cantidad de robos realizados", "Coquetería" -> setOtro(Integer.parseInt(campos[1]));
            }
        }
        br.close();
    }

    /**
     * Actualiza los atributos del personaje a partir de un archivo.
     *
     * @param file archivo con la información actualizada del personaje
     * @throws IOException si ocurre un error al leer el archivo
     */

    public void updtPJ(File file) throws IOException {
        Personaje playerFicheado = Factory.crear(this.getClass().getSimpleName(), file);

        if (!this.getNombre().equals(playerFicheado.getNombre())) return;
        if (!this.equals(playerFicheado)) this.copyCat(playerFicheado);
    }

    /**
     * Copia todos los atributos de otro personaje a este.
     *
     * @param player personaje a copiar
     */

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

    public HashMap<String, Armadura> getArmaduras() {
        return armaduras;
    }

    public void setArmaduras(HashMap<String, Armadura> armaduras) {
        if (armaduras.isEmpty())
            this.armaduras = null;
        else this.armaduras = new HashMap<>(armaduras);
    }

    public ArrayList<Artefacto> getArtefactos() {
        return artefactos;
    }

    public void setArtefactos(ArrayList<Artefacto> artefactos) {
        if (artefactos.isEmpty())
            this.artefactos = null;
        else this.artefactos = new ArrayList<>(artefactos);
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
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
        this.res = Math.max(res, 0);
    }

    /**
     * Establece un valor extra (no usado en esta clase base).
     *
     * @param otro valor a establecer
     */

    public void setOtro(int otro) {
    }

    /**
     * Devuelve el valor extra (no usado en esta clase base).
     *
     * @return valor extra
     */

    public int getOtro() {
        return 0;
    }

    /**
     * Establece la raza del personaje.
     *
     * @param raza nueva raza
     */

    /*todo en algún momento reflexionaré para que quede coqueto y tenga funcionalidad pero ahora me da pereza

    public void setRaza(String raza) {
        if (raza.equalsIgnoreCase("humano") ||
                raza.equalsIgnoreCase("elfo") ||
                raza.equalsIgnoreCase("ninfa") ||
                raza.equalsIgnoreCase("vampiro") ||
                raza.equalsIgnoreCase("hada"))
            this.raza = raza;
        else this.raza = "";
    }
    */

    /**
     * Establece la raza del personaje directamente.
     *
     * @param raza nueva raza
     */

    public void setRaza(String raza) { this.raza = raza; }

    /**
     * Devuelve la raza del personaje.
     *
     * @return raza
     */

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
        this.vel = Math.max(vel, 0);
    }

    /**
     * Modifica el nombre del personaje.
     *
     * @param nombre nuevo nombre
     */

    public void setNombre(String nombre) {
        if (nombre.contains("GM") || nombre.length() < 2) System.err.println("Error. El nombre debe tener caracteres válidos.");
        else this.nombre = nombre;
    }

    /**
     * Modifica los puntos de vida.
     *
     * @param pv nuevos puntos de vida
     */

    public void setPv(int pv) {
        this.pv = Math.max(0, pv);
    }

    /**
     * Modifica el ataque.
     *
     * @param atq nuevo ataque
     */

    public void setAtq(int atq) {
        this.atq = Math.max(0, atq);
    }

    /**
     * Modifica la armadura (defensa física).
     *
     * @param arm nueva armadura
     */

    public void setArm(int arm) {
        this.arm = Math.max(0, arm);
    }

    /**
     * Modifica el nivel del personaje.
     *
     * @param nivel nuevo nivel
     */

    public void setNivel(int nivel) {
        if (nivel < 1 || nivel > 100) this.nivel = 0;
        else this.nivel = nivel;
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
     * @param dw instancia de DWritersito para salida en pantalla y fichero
     */

    public void beberPocion(int pocion, DWritersito dw) {
        if (getPv() <= 30) {
            setPv(getPv() + pocion);
            dw.println("\n" + getNombre() + ", se ha bebido una poción.. su vida sube ahora es " + getPv());
        }
    }

    /**
     * Incrementa temporalmente una estadística.
     *
     * @param cantidad cantidad a aumentar
     * @param tipo     tipo de estadística
     * @param dw instancia de DWritersito para salida en pantalla y fichero
     */

    public void inspirar(int cantidad, String tipo, DWritersito dw) {
        switch (tipo) {
            case "ataque":
                setAtq(getAtq() + cantidad);
                dw.println("\n" + getNombre() + ", se ha inspirado.. su ataque sube ahora es " + getAtq() + details(2));
                break;
            case "defensa":
                setArm(getArm() + cantidad);
                dw.println("\n" + getNombre() + ", se ha inspirado.. su armadura sube ahora es " + getArm() + details(2));
                break;
            default:
                dw.println("Error. Introduzca ataque o defensa.");
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
        if (prob(50)) setPv(getPv() + 1);
        if (prob(50)) setAtq(getAtq() + 1);
        if (prob(50)) setArm(getArm() + 1);
        if (prob(50)) setRes(getRes() + 1);
        if (prob(50)) setVel(getVel() + 1);

        setNivel(getNivel() + 1);
        System.out.println(getNombre() + ", ¡ha subido de nivel!\n\n" + this);
    }

    /**
     * Comprueba si el personaje está muerto.
     *
     * @return true si los puntos de vida son 0 o menos
     */

    public boolean estaMuerto() {
        return pv <= 0;
    }

    /**
     * Compara dos personajes.
     *
     * @param otro personaje a comparar
     * @return true si son iguales
     */

    public boolean equals(Personaje otro) {
        return(this.nombre.equals(otro.nombre) &&
                this.pv == otro.pv &&
                this.atq == otro.atq &&
                this.arm == otro.arm &&
                this.nivel == otro.nivel &&
                this.vel == otro.vel &&
                this.res == otro.res &&
                this.getOtro() == otro.getOtro());
    }

    /**
     * Devuelve una representación en texto del personaje.
     *
     * @return información del personaje
     */

    @Override
    public String toString() {
        return "\n\t· Nombre: " + getNombre() +
                "\n\t· Raza: " + getRaza() +
                "\n\t· Vida: " + getPv() +
                "\n\t· Ataque: " + getAtq() +
                "\n\t· Armadura: " + getArm() +
                "\n\t· Velocidad: " + getVel() +
                "\n\t· Resistencia mágica: " + getRes() +
                "\n\t· Nivel: " + getNivel();
    }

    /**
     * Devuelve una “tarjeta” con la información principal del personaje.
     *
     * @return cadena con los atributos y estadísticas del personaje
     */

    public abstract String cartita();

    /**
     * Aplica los efectos de una trampa.
     *
     * @param t trampa activada
     * @param dw instancia de DWritersito para salida en pantalla y fichero
     */

    public void caerTrampa(Trampa t, DWritersito dw) {

        int perjuicioT = t.activaTrampa();

        if (perjuicioT > 0) {

            switch (t.getTipo()) {
                case "Pinchos":
                    setPv(getPv() - perjuicioT);
                    System.out.println("\n\t" + anderlain("Estacas afiladas") + " salen de las superficies cercanas y atraviesan a " + getNombre() + " por " + perjuicioT + " puntos de daño..\n");
                    printPv(this, dw);
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
        } else System.out.println("\n\tAfortunadamente, ¡" + getNombre() + " escapó de la trampa indemne!");

    }

    /**
     * Devuelve el daño de ataque del personaje.
     *
     * @return daño de ataque
     */

    public int atacar() {
        if (getArma() == null && getArmaduras().isEmpty() && getArtefactos().isEmpty()) return getAtq();
        return getAtck();
    }

    protected int getAtck(){
        int total = getArma().recuperaEstadistica("fuerza");

        for (Artefacto art : this.getArtefactos()){
            total += art.recuperaEstadistica("fuerza");
        }

        return this.getAtq() + total;
    }

    public int getVelos(){
        int total = getArma().recuperaEstadistica("velocidad");

        for (Artefacto art : this.getArtefactos()){
            total += art.recuperaEstadistica("velocidad");
        }

        return this.getVel() + total;
    }

    public int getArmie(){
        int total = 0;

        for (Artefacto art : this.getArtefactos()){
            total += art.recuperaEstadistica("defensa");
        }

        for (Armadura armadura : this.getArmaduras().values()){
            total += armadura.recuperaEstadistica("defensa");
        }

        return this.getArm() + total;
    }

    public int getResis(){
        int total = 0;

        for (Artefacto art : this.getArtefactos()){
            total += art.recuperaEstadistica("resistencia mágica");
        }

        for (Armadura armadura : this.getArmaduras().values()){
            total += armadura.recuperaEstadistica("resistencia mágica");
        }

        return this.getRes() + total;
    }

    public int getHP(){
        int total = 0;

        for (Artefacto art : this.getArtefactos()){
            total += art.recuperaEstadistica("vida");
        }

        for (Armadura armadura : this.getArmaduras().values()){
            total += armadura.recuperaEstadistica("vida");
        }

        return this.getPv() + total;
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
            case "fisico" -> {
                dañoRecibido = dañoHecho - getArmie() ;
                if (dañoRecibido < 0) dañoRecibido = 0;
            }
            case "magico" -> {
                dañoRecibido = dañoHecho - getResis();
                if (dañoRecibido < 0) dañoRecibido = 0;
            }
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
     * @param dw instancia de DWritersito para salida en pantalla y fichero
     */

    public void printPv(Personaje player, DWritersito dw) {
        dw.println("\t· Su vida actual es de: " + player.getHP());
    }

    /**
     * Activa la postura defensiva del personaje.
     *
     * @param dw instancia de DWritersito para salida en pantalla y fichero
     */

    public void defensaUppie(DWritersito dw) {
        setDef(true);

        setArm(getArm() + ((int) (getArm() * 0.2)));
        setRes(getRes() + ((int) (getRes() * 0.2)));
        dw.println(getNombre() + " adopta una postura defensiva.. \nSus stats mejoran" + details(5) + "\n\t· Armadura: " + getArm() + "\n\t· Resistencia: " + getRes());
    }

    /**
     * Desactiva la postura defensiva del personaje.
     */

    public void defensaDown(DWritersito dw) {
        setArm((int) (getArm() * 0.8));
        setRes((int) (getRes() * 0.8));
        dw.println("\n" + getNombre() + " se relaja.. \nSus stats vuelven a la normalidad, pero aumenta su cansancio" + details(4) + "\n\t· Armadura: " + getArm() + "\n\t· Resistencia: " + getRes());
    }

    /**
     * Acción especial del personaje.
     *
     * @param enemigo personaje enemigo
     */

    public void accEspesial(Personaje enemigo, DWritersito dw) {
        dw.println("Este personaje no tiene acción especial.." + details(4));
    }

    /**
     * Gestiona el turno del personaje.
     *
     * @param enemigo personaje enemigo
     * @param dw instancia de DWritersito para salida en pantalla y fichero
     */

    public void realizarTurno(Personaje enemigo, DWritersito dw) {
        int opcion;

        if (def) {
            this.setDef(false);
            this.defensaDown(dw);
        }

        dw.println("\nIniciando turno .ᐟ.ᐟ \n\t⤷ Turno de: " + this.getNombre() + details(5) + "\n");

        Scanner scan = new Scanner(System.in);

        do {
            menusito("¿Qué acción quiere realizar?", new String[]{"Atacar", "Acción Especial", "Defender", "Pasar turno"}, 0, dw);
            opcion = scan.nextInt();

            switch (opcion) {
                case 1 -> this.ataqueCoquetudo(enemigo, dw);
                case 2 -> this.accEspesial(enemigo, dw);
                case 3 -> this.defensaUppie(dw);
                case 4 -> dw.println("\n" + getNombre() + " decide pasar el turno.." + details(4));
            }
        } while (opcion > 4 || opcion < 1);
    }

    /**
     * Realiza un ataque con mensajes personalizados.
     *
     * @param enemigo personaje enemigo
     * @param dw instancia de DWritersito para salida en pantalla y fichero
     */

    public void ataqueCoquetudo(Personaje enemigo, DWritersito dw) {
        int dañito = enemigo.defender(this.atacar(), this.getTipoAtaque());

        if (dañito <= 0) dw.println("\n" + this.getNombre() + " decide atacar a " + enemigo.getNombre() + " pero no le hace ni cosquillas.." + details(4));
        else dw.println("\n" + this.getNombre() + " decide atacar a " + enemigo.getNombre() + " haciéndole " + dañito + " de daño.." + details(5));
        enemigo.defensa(this.atacar(), this.getTipoAtaque());
        printPv(enemigo, dw);
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
     * @param dw instancia de DWritersito para salida en pantalla y fichero
     */

    public void menusito(String mensaje, String[] opciones, int detail, DWritersito dw) {
        dw.println(details(1));
        dw.printlnFichita();
        System.out.println("\n" + mensaje + details(detail));

        for (int i = 0; i < opciones.length; i++) {
            int index = i + 1;
            System.out.println("\t" + index + ". " + opciones[i]);
        }

        printDetallito();
    }

    public void menusito(String mensaje, String[] opciones, int detail) {
        System.out.println(details(1) + "\n" + mensaje + details(detail));

        for (int i = 0; i < opciones.length; i++) {
            int index = i + 1;
            System.out.println("\t" + index + ". " + opciones[i]);
        }

        printDetallito();
    }

    public void equipArmadura(Armadura armadura){
        if (getArmaduras().size() >= 6)
            return;

        if (getArmaduras().containsKey(armadura.getTipo()))
            replaceArmadura(armadura);
        else getArmaduras().put(armadura.getTipo(), armadura);
    }

    public void unEquipArmadura(Armadura armadura){
        if (!getArmaduras().containsValue(armadura))
            return;
        getArmaduras().remove(armadura.getTipo(), armadura);
    }

    // todo mirar en mi casita

    public void replaceArmadura(Armadura armadura){
        Scanner scan = new Scanner(System.in);
        menusito("¿Desea reemplazar su " + armadura.getTipo() + " actual?", new String[]{"Sí", "No"}, 2);

        int opcion;
        do {
            opcion = scan.nextInt();
            switch (opcion){
                case 1 -> {
                    System.out.println("Cambio realizado. " +
                            "\n\t Ahora tienes equipado:\n" + armadura.toString());
                    getArmaduras().put(armadura.getTipo(), armadura);
                }
                case 2 -> System.out.println("Cambio no realizado, te quedas con:" + getArmaduras().get(armadura.getTipo()).toString());
                default -> {}
            }
        } while (opcion > 2 || opcion < 1);
    }

    public void equipArtefasto(Artefacto artefacto){

        int anillo = 0, amuleto = 0;
        for (Artefacto a : getArtefactos()){
            if (a.getTipo().equals("anillo"))
                anillo++;

            if (a.getTipo().equals("amuleto"))
                amuleto++;
        }

        switch (artefacto.getTipo()) {
            case "anillo" -> {
                if (anillo == 2){
                    System.out.println("Ya tienes 2 anillos.");
                    replaceArtefasto(artefacto);
                } else getArtefactos().add(artefacto);
            }
            case "amuleto" -> {
                if (amuleto == 1) {
                    System.out.println("Ya tienes 1 amuleto.");
                    replaceArtefasto(artefacto);
                } else getArtefactos().add(artefacto);
            }
            default -> {}
        }
    }

    public void replaceArtefasto(Artefacto artefacto){
        Scanner scan = new Scanner(System.in);
        if (artefacto.getTipo().equals("anillo"))
            menusito("¿Desea reemplazar alguno de sus " + artefacto.getTipo() + "s actuales?", new String[]{"Sí", "No"}, 2);

        if (artefacto.getTipo().equals("amuleto"))
            menusito("¿Desea reemplazar su " + artefacto.getTipo() + " actual?", new String[]{"Sí", "No"}, 2);

        int opcion;
        do {
            opcion = scan.nextInt();
            switch (opcion){
                case 1 -> {
                    siReplace(artefacto);
                }
                case 2 -> System.out.println("Cambio no realizado.");
                default -> {}
            }
        } while (opcion > 2 || opcion < 1);
    }

    protected void siReplace(Artefacto artefacto){
        System.out.println("¿Cuál desea reemplazar?");
        ArrayList<Integer> clave = new ArrayList<>();

        for (int i = 0; i < getArtefactos().size(); i++) {
            if (artefacto.getTipo().equals(getArtefactos().get(i).getTipo()))
                System.out.println("\n\t" + (i + 1) + ". " + getArtefactos().get(i).toString());
            Artefacto a = getArtefactos().get(i);
            if (a.getTipo().equals(artefacto.getTipo()))
                clave.add(i);
        }

        Scanner scan = new Scanner(System.in);
        int opcion;
        do {
            opcion = scan.nextInt();
            if (opcion > 0 && opcion <= clave.size()) {
                System.out.println("Cambio realizado. " +
                        "\n\t· Ahora tienes equipado:\n" + artefacto.toString());

                int changesito = clave.get(opcion - 1);
                getArtefactos().set(changesito, artefacto);
            }
        } while (!(opcion > 0 && opcion <= clave.size()));
    }

    public void unEquipArtefasto(Artefacto artefacto){
        if (!getArtefactos().contains(artefacto))
            return;
        getArtefactos().remove(artefacto);
    }

    public void equipArma(Arma arma){
        if (this.arma != null)
            replaceArma(arma);
        else setArma(arma);
    }

    public void replaceArma(Arma arma){
        menusito("¿Desea reemplazar su " + this.arma.getTipo() + " por " + arma.getTipo() + "?", new String[]{"Sí", "No"}, 2);

        Scanner scan = new Scanner(System.in);
        int opcion;
        do {
            opcion = scan.nextInt();
            switch (opcion){
                case 1 -> {
                    System.out.println("Cambio realizado. " +
                            "\n\t· Ahora tienes equipado:\n" + arma.toString());
                    setArma(arma);
                }
                case 2 -> System.out.println("Cambio no realizado.");
                default -> {}
            }
        } while (opcion > 2 || opcion < 1);
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
        System.out.print("› ");
    }

    /**
     * Muestra un mensaje decorativo por consola.
     *
     * @param acc mensaje a mostrar
     * @param dw instancia de DWritersito para salida en pantalla y fichero
     */

    public void printPerezita(String acc, DWritersito dw) {
        dw.println("\t\t" + acc + "\n");
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

    /**
     * Compara dos personajes por velocidad para ordenación.
     *
     * @param player personaje a comparar
     * @return resultado de la comparación
     */

    @Override
    public int compareTo(Personaje player) {
        return Integer.compare(player.getVel(), this.getVel());
    }
}



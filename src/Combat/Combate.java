package Combat;

import Characters.Personaje;
import GameMap.*;
import Gear.*;
import Manolo.DWritersito;

import java.io.*;
import java.util.*;

/**
 * Gestiona el sistema de combate por turnos entre dos personajes.
 * El orden de actuación se determina por la velocidad (vel) y se
 * contemplan turnos dobles si un personaje duplica la velocidad del otro.
 * @author Isa Barceló
 */

public class Combate {

    private static Collection<Equipamiento> tesoros;

    /**
     * Inicia y controla un combate completo entre dos personajes.
     * Determina el orden inicial según la velocidad y ejecuta
     * las rondas hasta que uno o ambos personajes mueren.
     *
     * @param c1 primer personaje participante en el combate
     * @param c2 segundo personaje participante en el combate
     */

    public static void combatir(Personaje c1, Personaje c2, DWritersito dw) {

        // Ella jura 💜

        int ronda = 0;
        Personaje primero;
        Personaje segundo;

        if (c1.getVelos() >= c2.getVelos()) {
            primero = c1;
            segundo = c2;
        } else {
            segundo = c1;
            primero = c2;
        }

        primero.printPerezita(primero.toString(), dw);
        segundo.printPerezita(segundo.toString(), dw);

        while (!primero.estaMuerto() && !segundo.estaMuerto()) {
            ronda++;
            dw.println(dividerC());
            dw.println("\n\t\t  Ronda " + ronda + " ⟢");

            bucleCombate(primero, segundo, dw);

            if (!segundo.estaMuerto())bucleCombate(segundo, primero, dw);
        }
        imprimirGanador(primero, segundo, dw);

        reclamarPremio(getPremiesito((ArrayList<Equipamiento>) tesoros), getGanador(primero, segundo));
    }

    /**
     * Imprime el resultado final del combate, mostrando el personaje
     * ganador o indicando un empate si ambos han muerto.
     *
     * @param c1 primer personaje
     * @param c2 segundo personaje
     */

    public static void imprimirGanador(Personaje c1, Personaje c2, DWritersito dw) {
        if (getGanador(c1, c2) == null) c1.printPerezita("\uD835\uDC6C\uD835\uDC8E\uD835\uDC91\uD835\uDC82\uD835\uDC95\uD835\uDC86..", dw);
        else dw.println("\n\t" + getGanador(c1, c2).getNombre() + " \uD835\uDC89\uD835\uDC82 \uD835\uDC88\uD835\uDC82\uD835\uDC8F\uD835\uDC82\uD835\uDC85\uD835\uDC90.." + c1.details(6));
    }

    protected static Personaje getGanador(Personaje c1, Personaje c2){
        if (c1.estaMuerto() && c2.estaMuerto()) return null;
        else if (c1.estaMuerto() && !c2.estaMuerto()) return c2;
        else return c1;
    }

    /**
     * Gestiona la activación aleatoria de una trampa sobre un personaje.
     * La trampa puede causar un perjuicio o, en caso de fallo,
     * otorgar un beneficio según su tipo.
     *
     * @param player personaje que puede activar la trampa
     */

    public static void trampita(Personaje player, DWritersito dw){
        Random r = new Random();
        Area area = new Area();

        if (r.nextInt(0, 100) <= 5) {
            String[] tipos = {"Pinchos", "Brea", "Serpientes"};
            int perjuicio = r.nextInt(5, 21);
            double fracaso = r.nextInt(0, 76);

            Trampa trampa = Area.generarTrampa(tipos[r.nextInt(tipos.length)], perjuicio, fracaso, area);

            perjuicio = trampa.activaTrampa();

            if (perjuicio > 0)
                player.caerTrampa(trampa, dw);
            else {
                switch (trampa.getTipo()) {
                    case "Brea" -> player.inspirar(trampa.getPerjuicio(), "defensa", dw);
                    case "Pinchos" -> player.beberPocion(trampa.getPerjuicio(), dw);
                    case "Serpientes" -> player.inspirar(trampa.getPerjuicio(), "ataque", dw);
                }
            }
        }
    }

    /**
     * Ejecuta el turno de ataque de un personaje sobre otro.
     * Si el atacante duplica la velocidad del objetivo,
     * podrá realizar un turno adicional.
     *
     * @param ataca personaje que realiza la acción
     * @param recibe personaje que recibe la acción
     */

    public static void bucleCombate(Personaje ataca, Personaje recibe, DWritersito pw){
        int ataques = 1;

        if (ataca.getVel() >= (recibe.getVel() * 2)){
            ataques++;
            pw.println("\n" + ataca.getNombre() + " es tan veloz que tiene doble turno.. ₍^ >ヮ<^₎ .ᐟ.ᐟ");
        }

        for (int i = 0; i < ataques; i++) {
            ataca.realizarTurno(recibe, pw);
        }

        trampita(ataca, pw);
    }

    public static Equipamiento getPremiesito(ArrayList<Equipamiento> tesoros){
        Random r = new Random();
        int limiste = r.nextInt(0, tesoros.size());

        return tesoros.get(limiste);
    }

    public static void reclamarPremio(Equipamiento eq, Personaje player){
        Scanner scan = new Scanner(System.in);

        // todo cuando tenga internet lo pongo coquette ahora mismo no puedo, m kiero suisidar
        System.out.println("felisidades, as gnado este premiesito: " +
                "\n" + eq.toString());
        player.menusito("¿Desea equiparlo", new String[]{"Sí", "No"}, 2);
        int opcion = 0;

        do {
            opcion = scan.nextInt();

            if (opcion == 1){
                switch (eq.getClass().getSimpleName()){
                    case "Arma" -> player.equipArma((Arma) eq);
                    case "Armadura" -> player.equipArmadura((Armadura) eq);
                    case "Artefacto" -> player.equipArtefasto((Artefacto) eq);
                    default -> {}
                }
            } else System.out.println("El premio ha sido desechado, lol que mal");
        } while (opcion > 2 || opcion < 1);

        tesoros.remove(eq);
    }

    public static void cargarPremiesitos() throws IOException {
        premiesitos("artefactos");
        premiesitos("armas");
        premiesitos("armadura");
    }

    public static void premiesitos(String csvName) throws IOException{
        if (tesoros == null) tesoros = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File("./csv/tesoros/" + csvName.toLowerCase() + ".csv")));

        String linea;
        String [] campos;

        br.readLine();
        while ((linea = br.readLine()) != null){
            campos = linea.split(",");
            switch (csvName.toLowerCase()){
                case "artefactos" -> {
                    Artefacto artefacto = new Artefacto(campos[0], campos[1], Integer.parseInt(campos[4]), campos[2]);
                    campos = campos[3].split("-");
                    statsPonel(campos, new String[]{"fuerza", "velocidad", "magia", "fe", "defensa", "resistencia mágica", "vida"}, artefacto);
                    tesoros.add(artefacto);
                }
                case "armas" -> {
                    Arma arma = new Arma(campos[0], campos[1], campos[2], Integer.parseInt(campos[4]));
                    campos = campos[3].split("-");
                    statsPonel(campos, new String[]{"fuerza", "velocidad", "magia", "fe"}, arma);
                    tesoros.add(arma);
                }
                case "armadura" -> {
                    Armadura armadura = new Armadura(campos[0], campos[1], campos[2], campos[3], Integer.parseInt(campos[5]));
                    campos = campos[4].split("-");
                    statsPonel(campos, new String[]{"defensa", "resistencia mágica", "vida"}, armadura);
                    tesoros.add(armadura);
                }
                default -> {}
            }
        }
        br.close();
    }

    private static HashMap<String, Integer> getStatsitas(String [] key, int [] valores){
        HashMap<String, Integer> stats = new HashMap<>();

        for (int i=0; i< key.length; i++){
            stats.put(key[i], valores[i]);
        }

        return stats;
    }

    private static void statsPonel(String [] campos, String [] key, Equipamiento eq){
        int [] valores = new int[campos.length];

        for (int i = 0; i < campos.length; i++){
            valores[i] = Integer.parseInt(campos[i]);
        }

        eq.setStats(getStatsitas(key, valores));
    }

    /**
     * Devuelve un separador visual para mostrar entre rondas
     * del combate por consola.
     *
     * @return cadena de texto decorativa usada como separador
     */

    public static String dividerC(){
        return "\n\t────•⋅⊰༻♥༺⊱⋅•────";
    }
}

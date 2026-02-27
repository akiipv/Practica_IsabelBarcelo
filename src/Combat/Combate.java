package Combat;

import Characters.Personaje;
import GameMap.*;
import Manolo.DWritersito;

import java.io.PrintWriter;
import java.util.Random;

/**
 * Gestiona el sistema de combate por turnos entre dos personajes.
 * El orden de actuación se determina por la velocidad (vel) y se
 * contemplan turnos dobles si un personaje duplica la velocidad del otro.
 * @author Isa Barceló
 */

public class Combate {

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

        if (c1.getVel() >= c2.getVel()) {
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

            if (!segundo.estaMuerto()){
                bucleCombate(segundo, primero, dw);
            }
        }
        imprimirGanador(primero, segundo, dw);
    }

    /**
     * Imprime el resultado final del combate, mostrando el personaje
     * ganador o indicando un empate si ambos han muerto.
     *
     * @param c1 primer personaje
     * @param c2 segundo personaje
     */

    public static void imprimirGanador(Personaje c1, Personaje c2, DWritersito dw) {

        if (c1.estaMuerto() && c2.estaMuerto()) {
            c1.printPerezita("\uD835\uDC6C\uD835\uDC8E\uD835\uDC91\uD835\uDC82\uD835\uDC95\uD835\uDC86..", dw);
        } else if (c1.estaMuerto() && !c2.estaMuerto()) {
            dw.println("\n\t" + c2.getNombre() + " \uD835\uDC89\uD835\uDC82 \uD835\uDC88\uD835\uDC82\uD835\uDC8F\uD835\uDC82\uD835\uDC85\uD835\uDC90.." + c2.details(6));
        } else dw.println("\n\t" + c1.getNombre() + " \uD835\uDC89\uD835\uDC82 \uD835\uDC88\uD835\uDC82\uD835\uDC8F\uD835\uDC82\uD835\uDC85\uD835\uDC90.." + c1.details(6));
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
                    case "Brea":
                        player.inspirar(trampa.getPerjuicio(), "defensa");
                        break;
                    case "Pinchos":
                        player.beberPocion(trampa.getPerjuicio());
                        break;
                    case "Serpientes":
                        player.inspirar(trampa.getPerjuicio(), "ataque");
                        break;
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

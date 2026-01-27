package Combat;

import Characters.Personaje;
import GameMap.*;

import java.util.Random;

public class Combate {

    public static void combatir(Personaje c1, Personaje c2) {

        // Ella jura 💜

        int ronda = 0;
        Personaje primero;
        Personaje segundo;

        if (c1.getVel() >= c2.getVel()) {
            primero = c1;
            segundo = c2;
        } else {
            segundo = c2;
            primero = c1;
        }

        while (!primero.estaMuerto() && !segundo.estaMuerto()) {
            ronda++;
            System.out.println(dividerC());
            System.out.println("\n\t\t  Ronda " + ronda + " ⟢");

            bucleCombate(primero, segundo);

            if (!segundo.estaMuerto()){
                bucleCombate(segundo, primero);
            }
        }
        imprimirGanador(primero, segundo);
    }

    public static void imprimirGanador(Personaje c1, Personaje c2) {

        if (c1.estaMuerto() && c2.estaMuerto()) {
            c1.printPerezita("\uD835\uDC6C\uD835\uDC8E\uD835\uDC91\uD835\uDC82\uD835\uDC95\uD835\uDC86..");
        } else if (c1.estaMuerto() && !c2.estaMuerto()) {
            System.out.println("\n\t" + c2.getNombre() + " \uD835\uDC89\uD835\uDC82 \uD835\uDC88\uD835\uDC82\uD835\uDC8F\uD835\uDC82\uD835\uDC85\uD835\uDC90.." + c2.details(6));
        } else System.out.println("\n\t" + c1.getNombre() + " \uD835\uDC89\uD835\uDC82 \uD835\uDC88\uD835\uDC82\uD835\uDC8F\uD835\uDC82\uD835\uDC85\uD835\uDC90.." + c2.details(6));
    }

    public static void trampita(Personaje player){
        Random r = new Random();
        Area area = new Area();

        if (r.nextInt(0, 100) <= 5) {
            String[] tipos = {"Pinchos", "Brea", "Serpientes"};
            int perjuicio = r.nextInt(5, 21);
            double fracaso = r.nextInt(0, 76);

            Trampa trampa = Area.generarTrampa(tipos[r.nextInt(tipos.length)], perjuicio, fracaso, area);

            perjuicio = trampa.activaTrampa();

            if (perjuicio > 0)
                player.caerTrampa(trampa);
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

    public static void bucleCombate(Personaje ataca, Personaje recibe){

        int ataques = 1;
        String tipo = ataca.getTipoAtaque();

        if (ataca.getVel() >= (recibe.getVel() * 2)){
            ataques++;
            System.out.println("\n" + ataca.getNombre() + " es tan veloz que tiene doble turno.. ₍^ >ヮ<^₎ .ᐟ.ᐟ");
        }

        for (int i = 0; i < ataques; i++) {
            ataca.realizarTurno(recibe);
        }

        trampita(ataca);

    }

    public static String dividerC(){
        return "\t────•⋅⊰༻♥༺⊱⋅•────";
    }
}

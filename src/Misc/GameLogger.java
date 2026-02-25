package Misc;

import Characters.*;

import java.io.*;
import java.util.Arrays;

public class GameLogger {

    public static void cardIB(Personaje player) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("./fichitas/personajes/" + player.getNombre() + ".txt"));

        bw.newLine();
        bw.write(player.cartita());
        bw.close();
    }

    public static void cardIB(Personaje[] player) throws IOException {
        // for (Personaje p : player)
        for (int i = 0; i < player.length; i++) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./fichitas/personajes/" + player[i].getNombre() + ".txt"));

            bw.newLine();
            bw.write(player[i].cartita());
            bw.close();
        }
    }

    public static void sortArrayito(Personaje[] player) {
        Arrays.sort(player);
    }

    /**todo no entiendo cmo hacerlo capa es pq tengo sueño y estoy medio drogada*/
    /**todo Mirar el findAny()*/
    public boolean fichitaExists(File[] fichitas, String nombre){
        boolean resultado = false;

        for (File ficha : fichitas) {
          //  if (ficha.getName())
        }

        return resultado;
    }
}

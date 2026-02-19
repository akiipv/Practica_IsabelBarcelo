package Misc;

import Characters.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class GameLogger {

    public static void cardIB(Personaje player) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("./" + player.getNombre() + ".txt"));

        bw.newLine();
        bw.write(player.toString2());
        bw.close();
    }

    public static void cardIB(Personaje[] player) throws IOException {

        // for (Personaje p : player)
        for (int i = 0; i < player.length; i++) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./" + player[i].getNombre() + ".txt"));

            bw.newLine();
            bw.write(player[i].toString2());
            bw.close();
        }
    }

    // lo di todo

    public static void sortArrayito(Personaje[] player) {
        Arrays.sort(player);
    }
}

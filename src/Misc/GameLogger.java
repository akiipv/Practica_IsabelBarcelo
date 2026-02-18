package Misc;

import Characters.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GameLogger {

    public static void cardIB(Personaje player) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("./" + player.getNombre() + ".txt"));

        bw.newLine();
        bw.write(player.toString2());
        bw.close();
    }

    public static void cardIB(Personaje[] player) throws IOException {

        for (int i = 0; i < player.length; i++) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./" + player[i].getNombre() + ".txt"));

            bw.newLine();
            bw.write(player[i].toString2());
            bw.close();
        }
    }

    public static void sortArrayito(Personaje[] player) throws IOException {

    }

}

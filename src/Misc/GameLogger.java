package Misc;

import Characters.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GameLogger {

    public static void Fichita(Personaje player) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("./" + player.getNombre() + ".txt"));

        bw.newLine();
        bw.write(player.toString2());
        bw.close();
    }

}

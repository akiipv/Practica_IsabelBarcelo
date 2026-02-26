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

    public boolean fichitaExists(File[] fichitas, String nombre){
        boolean resultado = false;

        for (File ficha : fichitas) {
          if (ficha.getName().equalsIgnoreCase(nombre))
              resultado = true;
          resultado = false;
        }

        return resultado;
    }

    /**todo mirarlo otra ve luego cuando esté ivan presente pq en mi mente tiene sentido pero cmo no lo he probao y encima lo estoy haciendo sin gafas y a destiempo pq ahora mismo tendria que estar preparandome y no haciendo programación pue jejejej*/

    public boolean claseRepetia(File[] fichitas, String nombre) throws IOException {
        boolean resultado = false;

        for (int i = 0; i < fichitas.length; i++) {
            if (getClasesita(fichitas[i]).equals(getClasesita(fichitas[i+1])))
                resultado = true;
            resultado = false;
        }

        return resultado;
    }

    public String getClasesita(File ficha) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(ficha));

        for (int i = 0; i < 3; i++) {
            br.readLine();
        }

        String[] campos = new String[2];
        String linea;
        String clase = "";
        while ((linea = br.readLine()) != null) {
            campos = linea.split(": ");

            if (campos[0].contains("Clase"))
                clase = campos[1];
        }
        br.close();

        return clase;
    }
}

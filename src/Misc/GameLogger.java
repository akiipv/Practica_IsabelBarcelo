package Misc;

import Characters.*;
import Combat.*;
import Manolo.DWritersito;

import java.io.*;
import java.time.*;
import java.util.Arrays;

public class GameLogger {

    private static final String directorio = "./fichitas/combate/";
    private static LocalTime hora = LocalTime.now();
    private static LocalDate fecha = LocalDate.now();

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

    public static boolean fichitaExists(File[] fichitas, String nombre){
        boolean resultado = false;

        for (File ficha : fichitas) {
          if (ficha.getName().equalsIgnoreCase(nombre))
              resultado = true;
          resultado = false;
        }

        return resultado;
    }

    public static boolean claseRepetia(File[] fichitas, String nombre) throws IOException {
        boolean resultado = false;

        for (int i = 0; i < fichitas.length; i++) {
            if (getClasesita(fichitas[i]).equals(getClasesita(fichitas[i+1])))
                resultado = true;
            resultado = false;
        }

        return resultado;
    }

    public static String getClasesita(File ficha) throws IOException {
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

    public static void writtieCombate(Personaje player, Personaje perchonaje, String nombreFichita) throws IOException {
        File fichita = new File(directorio + fecha + "|" + getHorita() + " — " + nombreFichita + ".txt");
        if (!fichita.exists()) fichita.createNewFile();
        PrintWriter ficherito = new PrintWriter(new FileWriter(fichita));
        PrintWriter consolita = new PrintWriter(System.out, true);
        DWritersito dw = new DWritersito(consolita, ficherito);
        Combate.combatir(player, perchonaje, dw);
        dw.flush();
        dw.close();
    }

    /**todo terminar esto jeje*/

    public static String getHorita(){
        String minuto;
        String horita;

        if (hora.getMinute() < 10)
            minuto = "0" + String.valueOf(hora.getMinute());
        else minuto = String.valueOf(hora.getMinute());

        if (hora.getHour() < 10)
            horita = "0" + String.valueOf(hora.getHour());
        else horita = String.valueOf(hora.getHour());

        return horita + ":" + minuto + " ";
    }
}

package Misc;

import Characters.*;
import Combat.*;
import Manolo.DWritersito;

import java.io.*;
import java.time.*;
import java.util.Arrays;

public class GameLogger {

    private static final String directorio = "./fichitas/combate/";
    private static final LocalTime hora = LocalTime.now();
    private static final LocalDate fecha = LocalDate.now();

    public static void cardIB(Personaje player) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("./fichitas/personajes/" + player.getNombre() + ".txt"));

        bw.newLine();
        bw.write(player.cartita());
        bw.close();
    }

    public static void cardIB(Personaje[] player) throws IOException {
        // for (Personaje p : player)
        Arrays.sort(player);
        BufferedWriter bw = new BufferedWriter(new FileWriter("./fichitas/personajes/partysita" + player[0].getNombre() + ".txt"));

        for (Personaje p : player) {
            bw.newLine();
            bw.write(p.cartita());
            bw.newLine();
        }

        bw.close();
    }

    public static boolean fichitaExists(File[] fichitas, String nombre){

        for (File ficha : fichitas) {
          if (ficha.getName().equalsIgnoreCase(nombre))
              return true;
        }

        return false;
    }

    public static boolean claseRepetia(File[] fichitas, String nombre) throws IOException {

        for (int i = 0; i < fichitas.length - 1; i++) {
            for (int j = i + 1; j < fichitas.length; j++)
                if (getClasesita(fichitas[j]).equals(getClasesita(fichitas[i])))
                    return true;
        }

        return false;
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

    public static void writtieCombate(Personaje player, Personaje perchonaje) throws IOException {
        File fichita = new File(directorio + fecha + "_" + getHorita() + " — " + player.getNombre() + "VS" + perchonaje.getNombre() + ".txt");

        PrintWriter ficherito = new PrintWriter(new FileWriter(fichita));
        PrintWriter consolita = new PrintWriter(System.out, true);
        DWritersito dw = new DWritersito(consolita, ficherito);

        Combate.combatir(player, perchonaje, dw);
        dw.flush();
        dw.close();
    }

    public static String getHorita(){
        String minuto;
        String horita;

        if (hora.getMinute() < 10)
            minuto = "0" + hora.getMinute();
        else minuto = String.valueOf(hora.getMinute());

        if (hora.getHour() < 10)
            horita = "0" + hora.getHour();
        else horita = String.valueOf(hora.getHour());

        return horita + "." + minuto + " ";
    }

    public static void lvlUpGanador(File fichaLusha, Personaje [] players) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(fichaLusha));

        String linea;
        String campos;
        while ((linea = br.readLine()) != null){
            if (linea.contains(players[0].details(6))){
                campos = linea.split(" ")[0].trim();
                for (Personaje player : players){
                    if (player.getNombre().equalsIgnoreCase(campos))
                        player.subirNivel();
                }
            }
        }
        br.close();
    }
}

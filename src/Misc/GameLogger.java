package Misc;

import Characters.*;
import Combat.*;
import Manolo.DWritersito;

import java.io.*;
import java.time.*;
import java.util.Arrays;

/**
 * Clase GameLogger.
 * Se encarga de guardar, leer y gestionar archivos relacionados con personajes
 * y combates del juego.
 *
 * Funcionalidades principales:
 * - Crear "cartitas" de personajes individuales o de grupos.
 * - Verificar existencia de fichas de personajes.
 * - Comprobar clases repetidas en un grupo.
 * - Registrar combates en archivos.
 * - Gestionar subida de nivel tras victorias.
 *
 * @author Isa Barceló
 */

public class GameLogger {

    /**Directorio*/

    private static final String directorio = "./fichitas/combate/";

    /**Hora actual*/

    private static final LocalTime hora = LocalTime.now();

    /**Fecha actual*/

    private static final LocalDate fecha = LocalDate.now();

    /**
     * Crea un archivo con la tarjeta de un personaje.
     *
     * @param player personaje del cual se generará la cartita
     * @throws IOException si ocurre un error al escribir el archivo
     */

    public static void cardIB(Personaje player) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("./fichitas/personajes/" + player.getNombre() + ".txt"));

        bw.newLine();
        bw.write(player.cartita());
        bw.close();
    }

    /**
     * Crea un archivo con las tarjetas de un grupo de personajes.
     *
     * @param player array de personajes
     * @throws IOException si ocurre un error al escribir el archivo
     */

    public static void cardIB(Personaje[] player) throws IOException {
        // for (Personaje p : player)
        Arrays.sort(player);
        BufferedWriter bw = new BufferedWriter(new FileWriter("./fichitas/personajes/party" + player[0].getNombre() + ".txt"));

        for (Personaje p : player) {
            bw.newLine();
            bw.write(p.cartita());
            bw.newLine();
        }

        bw.close();
    }

    /**
     * Comprueba si existe un archivo de personaje con un nombre específico.
     *
     * @param fichitas array de archivos
     * @param nombre nombre del personaje
     * @return true si existe, false si no
     */

    public static boolean fichitaExists(File[] fichitas, String nombre){

        for (File ficha : fichitas) {
          if (ficha.getName().equalsIgnoreCase(nombre))
              return true;
        }

        return false;
    }

    /**
     * Comprueba si hay personajes con la misma clase en un array de fichas.
     *
     * @param fichitas array de archivos de personajes
     * @param nombre nombre del personaje principal (no se usa directamente)
     * @return true si hay clases repetidas, false si no
     * @throws IOException si ocurre un error al leer los archivos
     */

    public static boolean claseRepetia(File[] fichitas, String nombre) throws IOException {

        for (int i = 0; i < fichitas.length - 1; i++) {
            for (int j = i + 1; j < fichitas.length; j++)
                if (getClasesita(fichitas[j]).equals(getClasesita(fichitas[i])))
                    return true;
        }

        return false;
    }

    /**
     * Extrae la clase de un personaje a partir de su archivo.
     *
     * @param ficha archivo del personaje
     * @return nombre de la clase como String
     * @throws IOException si ocurre un error al leer el archivo
     */

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

    /**
     * Registra un combate entre dos personajes en un archivo
     * y simultáneamente en consola.
     *
     * @param player personaje principal
     * @param perchonaje personaje enemigo
     * @throws IOException si ocurre un error al crear el archivo
     */

    public static void writtieCombate(Personaje player, Personaje perchonaje) throws IOException {
        File fichita = new File(directorio + fecha + "_" + getHorita() + " — " + player.getNombre() + "VS" + perchonaje.getNombre() + ".txt");

        PrintWriter ficherito = new PrintWriter(new FileWriter(fichita));
        PrintWriter consolita = new PrintWriter(System.out, true);
        DWritersito dw = new DWritersito(consolita, ficherito);

        Combate.combatir(player, perchonaje, dw);
        dw.flush();
        dw.close();
    }

    /**
     * Obtiene la hora actual en formato HH.mm.
     *
     * @return cadena con la hora actual
     */

    public static String getHorita(){
        String minuto;
        String horita;

        if (hora.getMinute() < 10) minuto = "0" + hora.getMinute();
        else minuto = String.valueOf(hora.getMinute());

        if (hora.getHour() < 10) horita = "0" + hora.getHour();
        else horita = String.valueOf(hora.getHour());

        return horita + "." + minuto + " ";
    }

    /**
     * Sube de nivel al ganador de un combate leyendo su ficha.
     *
     * @param fichaLusha archivo de ficha del ganador
     * @param players array de personajes del grupo
     * @throws IOException si ocurre un error al leer el archivo
     */

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

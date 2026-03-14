package Manolo;

import java.io.*;

/**
 * DWritersito.
 * Clase auxiliar que permite escribir mensajes simultáneamente
 * en la consola y en un archivo (fichita), manteniendo ambos sincronizados.
 *
 * @author Isa Barceló
 */

public class DWritersito {

    /**Printwriter que hace referencia a la consola*/

    private PrintWriter consola;

    /**Printwriter que hace referencia al fichero*/

    private PrintWriter fichita;

    /**
     * Constructor de DWritersito.
     *
     * @param consola PrintWriter para la salida de consola
     * @param fichita PrintWriter para la salida en archivo
     */

    public DWritersito(PrintWriter consola, PrintWriter fichita){
        this.consola = consola;
        this.fichita = fichita;
    }

    /**
     * Imprime una línea de texto en consola y archivo.
     *
     * @param nose texto a imprimir
     */

    public void println(String nose){
        consola.println(nose);
        fichita.println(nose);
    }

    /**
     * Imprime una línea en blanco en consola y archivo.
     */

    public void println(){
        consola.println();
        fichita.println();
    }

    /**
     * Imprime una línea en blanco solo en el archivo.
     */

    public void printlnFichita(){
        fichita.println();
    }

    /**
     * Imprime un texto sin salto de línea en consola y archivo.
     *
     * @param nose texto a imprimir
     */

    public void printf(String nose){
        consola.printf(nose);
        fichita.printf(nose);
    }

    /**
     * Vacía los buffers de consola y archivo, asegurando que todo
     * el contenido se escriba.
     */

    public void flush(){
        consola.flush();
        fichita.flush();
    }

    /**
     * Cierra el DWritersito, primero vaciando los buffers.
     */

    public void close(){
        flush();
        fichita.close();
    }
}

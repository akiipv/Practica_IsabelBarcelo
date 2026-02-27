package Manolo;

import java.io.*;

public class DWritersito {
    private PrintWriter consola;
    private PrintWriter fichita;

    public DWritersito(PrintWriter consola, PrintWriter fichita){
        this.consola = consola;
        this.fichita = fichita;
    }

    public void println(String nose){
        consola.println(nose);
        fichita.println(nose);
    }

    public void println(){
        consola.println();
        fichita.println();
    }

    public void printlnFichita(){
        fichita.println();
    }

    public void printf(String nose){
        consola.printf(nose);
        fichita.printf(nose);
    }

    public void flush(){
        consola.flush();
        fichita.flush();
    }

    public void close(){
        flush();
        fichita.close();
    }
}

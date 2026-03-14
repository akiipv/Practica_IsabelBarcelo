package Characters;

import java.io.*;

/**
 * Clase Factory para la creación de objetos Personaje a partir de un archivo.
 * Permite instanciar subclases concretas de Personaje según el tipo indicado.
 *
 * @author Isa Barceló
 */

public class Factory {

    /**
     * Crea un personaje de tipo específico leyendo sus datos desde un archivo.
     *
     * @param tipo tipo de personaje a crear (ej: "guerrero", "mago", "clérigo", etc.)
     * @param file archivo que contiene la información del personaje
     * @return instancia del Personaje correspondiente, o null si el tipo no coincide
     * @throws IOException si ocurre un error al leer el archivo
     */

    public static Personaje crear(String tipo, File file) throws IOException {
        return switch (tipo.toLowerCase()){
            case "guerrero" -> new Guerrero(file);
            case "mago" -> new Mago(file);
            case "ladrón" -> new Ladrón(file);
            case "cazador" -> new Cazador(file);
            case "paladin" -> new Paladin(file);
            case "clérigo" -> new Clérigo(file);
            case "monstruo" -> new Monstruo(file);
            default -> null;
        };
    }
}

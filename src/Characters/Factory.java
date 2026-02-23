package Characters;

import java.io.*;

public class Factory {

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

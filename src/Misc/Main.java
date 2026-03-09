package Misc;

import Characters.*;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Coquetería pura, ojalá que cuando lo pruebe no se cague encima

        File ficha = new File("fichitas/combate/2026-03-06_14.28  — IsaVSAle.txt");

        Personaje p1 = new Ladrón("Isa", 10, 10, 10, 10, 1, 10);
        Personaje p2 = new Paladin("Ale", 100, 100, 1000, 10, 10, 10, 10);
        Personaje p3 = new Cazador("Juan", 100, 100, 100, 10, 5, 10, "felino", "achu");
        Personaje [] personajes = new Personaje[]{p1, p2, p3};

        GameLogger.cardIB(personajes);
    }
}
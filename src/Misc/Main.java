package Misc;

import Characters.*;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Coquetería pura, ojalá que cuando lo pruebe no se cague encima

        File ficha = new File("fichitas/combate/2026-03-06_14.28  — IsaVSAle.txt");
        File ficha2 = new File("fichitas/personajes/Isa.txt");

        /*
            Personaje p1 = new Ladrón("Isa", 10, 10, 10, 10, 1, 10);
            Personaje p2 = new Paladin("Ale", 100, 100, 1000, 10, 10, 10, 10);
            Personaje p3 = new Guerrero("Juan", 100, 100, 100, 10, 5, 10, false);
            Personaje p4 = new Ladrón(ficha2);
         */

        Personaje p1 = new Cazador("Kim Namjoon", 23, 45, 67, 12, 23, 34, "rapaz", "Irina");
        Personaje p2 = new Clérigo("Kim Seokjin", 43, 6, 24, 87, 12, 45, 8);
        Personaje p3 = new Guerrero("Min Yoongi", 45, 27, 84, 24, 73, 24, true);
        Personaje p4 = new Ladrón("Jung Hoseok", 7, 32, 96, 74, 64, 74);
        Personaje p5 = new Mago("Park Jimin", 23, 54, 56, 12, 68, 45, 79);
        Personaje p6 = new Monstruo("Kim Taehyung", 93, 83, 56, 25, 78, 54, "bestia");
        Personaje p7 = new Paladin("Jeon Jungkook", 74, 53, 79, 23, 65 ,31 ,73);
        Personaje [] personajes = new Personaje[]{p1, p2, p3, p4, p5, p6, p7};

        GameLogger.cardIB(personajes);
    }
}
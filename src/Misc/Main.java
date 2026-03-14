package Misc;

import Characters.*;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Coquetería pura, ojalá que cuando lo pruebe no se cague encima

        File ficha1 = new File("fichitas/personajes/Kim Namjoon.txt");
        File ficha2 = new File("fichitas/personajes/Kim Seokjin.txt");
        File ficha3 = new File("fichitas/personajes/Min Yoongi.txt");
        File ficha4 = new File("fichitas/personajes/Jung Hoseok.txt");
        File ficha5 = new File("fichitas/personajes/Park Jimin.txt");
        File ficha6 = new File("fichitas/personajes/Kim Taehyung.txt");
        File ficha7 = new File("fichitas/personajes/Jeon Jungkook.txt");

        /*
            Personaje p1 = new Ladrón("Isa", 10, 10, 10, 10, 1, 10);
            Personaje p2 = new Paladin("Ale", 100, 100, 1000, 10, 10, 10, 10);
            Personaje p3 = new Guerrero("Juan", 100, 100, 100, 10, 5, 10, false);
            Personaje p4 = new Ladrón(ficha2);

        Personaje p1 = new Cazador(new File("fichitas/personajes/Kim Namjoon.txt"));
        Personaje p2 = new Clérigo(new File("fichitas/personajes/Kim Seokjin.txt"));
        Personaje p3 = new Guerrero(new File("fichitas/personajes/Min Yoongi.txt"));
        Personaje p4 = new Ladrón(new File("fichitas/personajes/Jung Hoseok.txt"));
        Personaje p5 = new Mago(new File("fichitas/personajes/Park Jimin.txt"));
        Personaje p6 = new Monstruo(new File("fichitas/personajes/Kim Taehyung.txt"));
        Personaje p7 = new Paladin(new File("fichitas/personajes/Jeon Jungkook.txt"));*/

        // funsiona Personaje p1 = new Cazador("Kim Namjoon", 23, 45, 67, 12, 23, 34, "rapaz", "Irina");
        // funsiona Personaje p2 = new Clérigo("Kim Seokjin", 43, 6, 24, 87, 12, 45, 8);
        // funsiona Personaje p3 = new Guerrero("Min Yoongi", 45, 27, 84, 24, 73, 24, true);
        // funsiona Personaje p4 = new Ladrón("Jung Hoseok", 7, 32, 96, 74, 64, 74);
        // funsiona Personaje p5 = new Mago("Park Jimin", 23, 54, 56, 12, 68, 45, 79);
        // funsiona Personaje p6 = new Monstruo("Kim Taehyung", 93, 83, 56, 25, 78, 54, "bestia");
        Personaje p7 = new Paladin("Jeon Jungkook", 74, 53, 79, 23, 65 ,31 ,73);

        p7.updtPJ(ficha7);
        System.out.println(p7.cartita());
    }
}
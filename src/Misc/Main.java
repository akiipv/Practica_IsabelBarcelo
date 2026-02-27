package Misc;

import Characters.*;
import Combat.Combate;
import GameMap.*;
import Manolo.DWritersito;
import Misc.GameTest;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Coquetería pura, ojalá que cuando lo pruebe no se cague encima

        Personaje p1 = new Ladrón("Isa", 10, 10, 10, 10, 1, 10);
        Personaje p2 = new Paladin("Ale", 100, 100, 1000, 10, 10, 10, 10);

        GameLogger.writtieCombate(p1, p2, "AleVsIsa");
    }
}
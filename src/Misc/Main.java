package Misc;

import Characters.*;
import Combat.Combate;
import GameMap.*;
import Misc.GameTest;

public class Main {
    public static void main(String[] args) {
        // Coquetería pura, ojalá que cuando lo pruebe no se cague encima

        Personaje p1 = new Ladrón("Isa", 100, 10, 10, 10, 100, 10);
        Personaje p2 = new Paladin("Ale", 100, 10, 1000, 10, 10, 10, 10);

        Combate.combatir(p1, p2);
    }
}
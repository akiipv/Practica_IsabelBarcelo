package Misc;

import Characters.*;
import Combat.Combate;
import GameMap.*;
import Misc.GameTest;

public class Main {
    public static void main(String[] args) {
        // Que luego dices que no pruebo lo que hago, hater 🥀

        Personaje Isa = new Paladin("Isa", 100, 10, 10, 10, 10, 10, 10);
        Personaje Iván = new Guerrero("Iván", 100, 10, 10, 10, 10, 10, false);

        Combate.combatir(Isa, Iván);
    }
}
package Misc;

import Characters.*;
import Combat.Combate;
import GameMap.*;
import Misc.GameTest;

public class Main {
    public static void main(String[] args) {
        // Que luego dices que no pruebo lo que hago, hater 🥀

        Personaje Isa = new Mago("Isa", 7, 10, 63, 2, 80, 21, 23);
        Personaje Alejandro = new Mago("Alejandro", 20, 23, 5, 36, 22, 5, 24);

        Combate.combatir(Isa, Alejandro);
    }
}
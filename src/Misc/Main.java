package Misc;

import Characters.*;
import Gear.Artefacto;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Personaje p = new Paladin("Iván", 99, 99, 99, 99, 99, 99, 99);
        GameLogger.cardIB(p);
    }
}
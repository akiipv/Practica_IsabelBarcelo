package Misc;

import Characters.*;
import GameMap.*;
import Gear.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        File fichero = new File("./csv/mazmorras/hogarDelHacedor.csv");
        Mazmorra mazmorra = new Mazmorra(fichero);
    }
}
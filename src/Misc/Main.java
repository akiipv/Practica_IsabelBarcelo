package Misc;

import Characters.*;
import GameMap.*;
import Gear.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Monstruo> monstruos = new ArrayList<>();
        monstruos.add(new Monstruo("pepe", 5, 7, 3, 6, 2, 4, "bestia"));
        monstruos.add(new Monstruo("juan", 3, 4, 8, 2, 5, 6, "no-muerto"));
        monstruos.add(new Monstruo("ivan", 9, 10, 7, 8, 6, 5, "gigante"));
        monstruos.add(new Monstruo("cristian", 6, 5, 4, 7, 3, 2, "bestia"));
        monstruos.add(new Monstruo("carmen", 4, 6, 9, 3, 7, 8, "no-muerto"));
        Mazmorra m = new Mazmorra("ola", 1, monstruos);

        System.out.println(m.getMonstruos().toString());
    }
}
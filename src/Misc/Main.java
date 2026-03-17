package Misc;

import Characters.*;
import Gear.Artefacto;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Artefacto ar = new Artefacto();

        System.out.println(ar.toString());
    }
}
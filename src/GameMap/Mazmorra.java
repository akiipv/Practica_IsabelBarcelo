package GameMap;

import Characters.Monstruo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Mazmorra {
    private String nombre;
    private int nivel;
    private HashSet<Monstruo> monstruos;

    public Mazmorra() {
        setNombre("");
        setNivel(0);
        monstruos = new HashSet<>();
    }

    public Mazmorra(File fichero) throws IOException {
        if (!fichero.exists() && !fichero.canRead()) return;
        BufferedReader br = new BufferedReader(new FileReader(fichero));

        String linea = br.readLine();

        String[] campos = linea.split(",");
        setNombre(campos[0].trim());
        setNivel(Integer.parseInt(campos[1].trim()));
        setMonstruos(loadMounstruo(fichero));

        br.close();
    }

    protected HashSet<Monstruo> loadMounstruo(File fichero) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fichero));

        HashSet<Monstruo> monstruos = new HashSet<>();

        String linea;
        br.readLine();
        while ((linea = br.readLine()) != null) {
            Monstruo m = new Monstruo();
            String[] campos = linea.split(",");
            if (campos.length == 2) {
                m.setRaza(campos[0].trim());
                m.setNombre(campos[1].trim());
                monstruos.add(m);
            }
        }
        br.close();

        return monstruos;
    }

    public Mazmorra(Mazmorra m) {
        setNombre(m.getNombre());
        setNivel(m.getNivel());
        setMonstruos(m.getMonstruos());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public HashSet<Monstruo> getMonstruos() {
        return monstruos;
    }

    public void setMonstruos(HashSet<Monstruo> monstruos) {
        HashSet<Monstruo> monstruosSA = new HashSet<>();
        for (Monstruo m : monstruos) {
            if (m.getNivel() <= this.getNivel() + 3 &&
                    m.getNivel() >= this.getNivel() - 3 ) monstruosSA.add(m);
        }
        this.monstruos = new HashSet<>(monstruosSA);
    }

    public Monstruo combateRandom(){
        if (getMonstruos().isEmpty()) return null;
        ArrayList<Monstruo> monstruosSA = new ArrayList<>(getMonstruos());

        Random r  = new Random();
        int i = r.nextInt(monstruosSA.size());

        return monstruosSA.get(i);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("Cargando datos de la mazmorra.." +
                "\n· Nombre: " + getNombre() +
                "\n· Nivel: " + getNivel() +
                "\n· Monstruos: ");
        for(Monstruo m : getMonstruos()) {
            res.append("\n\t").append(m.toString()).append("\n");
        }
        return res.toString();
    }
}

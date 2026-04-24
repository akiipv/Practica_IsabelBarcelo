package GameMap;

import Characters.Monstruo;
import Characters.Personaje;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Mazmorra {
    private String nombre;
    private int nivel;
    private ArrayList<Monstruo> monstruos;

    public Mazmorra() {
        setNombre("");
        setNivel(0);
        monstruos = new ArrayList<>();
    }

    public Mazmorra(File fichero) {
        /*setNombre(nombre);
        setNivel(nivel);
        setMonstruos(monstruos);
        No había leído lo del fichero, lo tengo que cambiar ;C*/
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

    public ArrayList<Monstruo> getMonstruos() {
        return monstruos;
    }

    public void setMonstruos(ArrayList<Monstruo> monstruos) {
        ArrayList<Monstruo> depresion = new ArrayList<>();
        for (Monstruo m : monstruos) {
            if (!depresion.contains(m) &&
                    m.getNivel() <= this.getNivel() + 3 &&
                    m.getNivel() >= this.getNivel() - 3 ) depresion.add(m);
        }
        this.monstruos = new ArrayList<>(depresion);
    }

    public Monstruo combateRandom(){
        if (getMonstruos().isEmpty()) return null;

        Random r  = new Random();
        int i = r.nextInt(getMonstruos().size());

        return getMonstruos().get(i);
    }

    @Override
    public String toString() {
        String res = "Cargando datos de la mazmorra.." +
                "\n· Nombre: " + getNombre() +
                "\n· Nivel: " + getNivel() +
                "\n Monstruos: ";
        for(Monstruo m : getMonstruos()) {
            res += "\n\t" + m.toString() + "\n";
        }
        return res;
    }
}

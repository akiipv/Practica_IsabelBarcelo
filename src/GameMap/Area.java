package GameMap;

import java.util.Random;

public class Area {

    private String nombre;
    private String bioma;
    private int nivel;

    public Area() {
        nombre = "????";
        bioma = "Pradera";
        nivel = 1;
    }

    public Area(String nombre, String bioma) {
        setNombre(nombre);
        setBioma(bioma);
        asignarNivel();
    }

    public Area(Area copia) {
        this.nombre = copia.nombre;
        this.bioma = copia.bioma;
        this.nivel = copia.nivel;
    }

    public void setNombre(String nombre) {
        if (nombre.isEmpty() || nombre.contains(" ")) System.err.println("Error. El nombre no puede dejarse en blanco ni contener espacios.");
        else this.nombre = nombre;
    }

    public void setBioma(String bioma) {
        bioma = bioma.toLowerCase();
        if (bioma.equals("pradera") || bioma.equals("jungla") || bioma.equals("desierto") || bioma.equals("montaña") || bioma.equals("mazmorra")) this.bioma = bioma;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public String getBioma() {
        return bioma;
    }

    public int getNivel() {
        return nivel;
    }

    public void asignarNivel() {
        Random r = new Random();

        switch (bioma) {
            case "Pradera" -> nivel = r.nextInt(1, 11);
            case "Jungla" -> nivel = r.nextInt(11, 31);
            case "Desierto" -> nivel = r.nextInt(31, 61);
            case "Montaña" -> nivel = r.nextInt(61, 91);
            case "Mazmorra" -> nivel = r.nextInt(91, 106);
        }
    }

    public int generarPocion() {
        Random r = new Random();
        int pocion = 111;

        switch (bioma) {
            case "Pradera" -> pocion = 15;
            case "Jungla"-> pocion = r.nextInt(15, 26);
            case "Desierto" -> pocion = r.nextInt(1, 31);
            case "Montaña" -> pocion = 30;
            case "Mazmorra" -> {
                if (r.nextInt(0, 101) <= 20) pocion = -10;
                else pocion = 20;
            }
        }

        return pocion;
    }


    public static Trampa generarTrampa(String t, int p, double f, Area area) {

        Trampa trampa;
        trampa = new Trampa();

        trampa.setTipo(t);
        trampa.setPerjuicio(p);
        trampa.setFracaso(f);

        area = new Area();
        String bioma = area.getBioma();

        switch (bioma) {
            case "Pradera", "Montaña" -> {}
            case "Jungla" -> {
                if (t.equals("Serpientes")) trampa.setFracaso(trampa.getFracaso() - 10);
            }
            case "Desierto" -> {
                if (t.equals("Brea")) trampa.setFracaso(trampa.getFracaso() - 15);
            }
            case "Mazmorra" -> {
                if (t.equals("Pinchos")) trampa.setPerjuicio(trampa.getPerjuicio() + 5);
            }
        }

        return trampa;
    }

    public Area clone() {
        return new Area(this.nombre, this.bioma);
    }

    public String toString() {
        return "Cargando datos del área.. " +
                "\n\t· Nombre: " + getNombre() +
                "\n\t· Bioma: " + getBioma() +
                "\n\t· Nivel: " + getNivel();
    }
}
package GameMap;

import java.util.Random;

public class Trampa {

    private String tipo;
    private int perjuicio;
    private double fracaso;

    public Trampa() {
        tipo = "";
        perjuicio = 0;
        fracaso = 0;
    }

    public Trampa(String categoria) {
        Random random = new Random();

        setTipo(categoria);
        setPerjuicio(random.nextInt(5, 21));
        setFracaso(random.nextInt(0, 76));
    }

    public Trampa(String categoria, int perjuicio) {
        Random random = new Random();

        setTipo(categoria);
        setPerjuicio(perjuicio);
        setFracaso(random.nextInt(0, 76));
    }

    public Trampa(Trampa copia) {
        this.tipo = copia.tipo;
        this.fracaso = copia.fracaso;
        this.perjuicio = copia.perjuicio;
    }

    public void setTipo(String tipo) {
        if (tipo.equals("Pinchos") || tipo.equals("Brea") || tipo.equals("Serpientes")) this.tipo = tipo;
        else System.err.println("Tipo inválido.");
    }

    public void setPerjuicio(int perjuicio) {
        if (perjuicio >= 5 && perjuicio <= 20) this.perjuicio = perjuicio;
        else System.err.println("Perjuicio fuera del rango permitido.");
    }

    public void setFracaso(double fracaso) {
        if (fracaso >= 0.0 && fracaso <= 75.0) this.fracaso = fracaso;
        else System.err.println("Probabilidad de fracaso no válida.");
    }

    public String getTipo() {
        return tipo;
    }

    public int getPerjuicio() {
        return perjuicio;
    }

    public double getFracaso() {
        return fracaso;
    }

    public int activaTrampa() {
        Random r = new Random();
        if (r.nextInt(0, 101) > fracaso) return perjuicio;
        else return 0;
    }

    public boolean equals(Trampa otro) {
        if (!this.tipo.equals(otro.tipo) ||
                this.perjuicio != otro.perjuicio ||
                this.fracaso != otro.fracaso)
            return false;
        else return true;
    }

    public String toString() {
        return "Una trampa se ha activado.. " +
                "\n\t· Tipo de trampa: " + getTipo() +
                "\n\t· Probabilidad de fracaso: " + getFracaso() + "%" +
                "\n\t· Perjuicio: " + getPerjuicio();
    }
}

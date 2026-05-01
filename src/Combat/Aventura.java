package Combat;

import Characters.Monstruo;
import Characters.Personaje;
import GameMap.Mazmorra;
import Combat.Combate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Aventura { // habria q hacer una clase que se llame romeo santos
    private Mazmorra mazmorra;
    private ArrayList<Personaje> pari;

    public ArrayList<Personaje> getPari() {
        return pari;
    }

    public void setPari(ArrayList<Personaje> pari) {
        if (pari != null)
            this.pari = new ArrayList<>(pari);
    }

    public Mazmorra getMazmorra() {
        return mazmorra;
    }

    public void setMazmorra(Mazmorra mazmorra) {
        this.mazmorra = mazmorra;
    }

    public void quisieraCargar(File fichero, ArrayList<Personaje> pari) throws IOException {
        mazmorra = new Mazmorra(fichero);
        setPari(pari);
    }

    public ArrayList<Personaje> Ozuna(){
        ArrayList<Personaje> monstruos = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < r.nextInt(1,4); i++){
            Monstruo m = mazmorra.combateRandom();
            if (m != null) monstruos.add(m);
        }
        return monstruos;
    }

    public void tenerAventura() throws IOException {
        for (int i = 0; i <= 10; i++) {
            if (pari.isEmpty()) {
                System.out.println("Ya no queda nadie en el equipo.." +
                        "\n\tLos monstruos ganan.." + Ozuna().getFirst().details(4));
                return;
            }

            System.out.println(Combate.dividerC() + "\n\t\t  Combate " + i + " ⟢");

            Combate.combateGrupo(pari, Ozuna());
            pari.removeIf(Personaje::estaMuerto); //
        }
    }
}

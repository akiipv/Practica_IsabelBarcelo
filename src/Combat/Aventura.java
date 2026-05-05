package Combat;

import Characters.Monstruo;
import Characters.Personaje;
import GameMap.Mazmorra;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que gestiona una aventura dentro de una mazmorra.
 * Controla el grupo de personajes y los combates sucesivos
 * contra monstruos generados aleatoriamente.
 *
 * @author Isa Barceló
 */

public class Aventura { // habria q hacer una clase que se llame romeo santos
    private Mazmorra mazmorra; /** Mazmorra en la que se desarrolla la aventura **/
    private ArrayList<Personaje> pari; /** Grupo de personajes que participan en la aventura **/

    /**
     * Devuelve el grupo de personajes que participan en la aventura.
     *
     * @return lista de personajes del grupo actual
     */

    public ArrayList<Personaje> getPari() {
        return pari;
    }

    /**
     * Establece el grupo de personajes que participarán en la aventura.
     * Se realiza una copia defensiva de la lista recibida.
     *
     * @param pari lista de personajes a asignar
     */

    public void setPari(ArrayList<Personaje> pari) {
        if (pari != null)
            this.pari = new ArrayList<>(pari);
    }

    /**
     * Devuelve la mazmorra asociada a la aventura.
     *
     * @return mazmorra actual de la aventura
     */

    public Mazmorra getMazmorra() {
        return mazmorra;
    }

    /**
     * Establece la mazmorra en la que se desarrollará la aventura.
     *
     * @param mazmorra mazmorra a asignar
     */

    public void setMazmorra(Mazmorra mazmorra) {
        this.mazmorra = mazmorra;
    }

    /**
     * Inicializa la aventura cargando una mazmorra desde un fichero
     * y asignando el grupo de personajes participante.
     *
     * @param fichero archivo de configuración de la mazmorra
     * @param pari grupo de personajes que participan en la aventura
     * @throws IOException si ocurre un error al leer el fichero
     */

    public void quisieraCargar(File fichero, ArrayList<Personaje> pari) throws IOException {
        mazmorra = new Mazmorra(fichero);
        setPari(pari);
    }

    /**
     * Genera una lista aleatoria de monstruos obtenidos de la mazmorra.
     * La cantidad de enemigos se decide de forma aleatoria.
     *
     * @return lista de monstruos generados para el encuentro
     */

    public ArrayList<Personaje> monstruitos(){
        ArrayList<Personaje> monstruos = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < r.nextInt(1,4); i++){
            Monstruo m = mazmorra.combateRandom();
            if (m != null) monstruos.add(m);
        }
        return monstruos;
    }

    /**
     * Ejecuta la aventura completa en una secuencia de combates.
     * Cada ronda enfrenta al grupo del jugador contra monstruos generados
     * aleatoriamente desde la mazmorra. Finaliza si el grupo se queda sin miembros.
     *
     * @throws IOException si ocurre un error durante los combates
     */

    public void tenerAventura() throws IOException {
        for (int i = 1; i <= 10; i++) {
            if (pari.isEmpty()) {
                System.out.println("Ya no queda nadie en el equipo.." +
                        "\n\tLos monstruos ganan.." + monstruitos().getFirst().details(4));
                return;
            }

            System.out.println(Combate.dividerC() + "\n\t\t  Combate " + i + " ⟢");

            Combate.combateGrupo(pari, monstruitos());
            pari.removeIf(Personaje::estaMuerto); // no sabia q existia esto pero me gusta es coquette
        }
        System.out.println("\n\t \uD835\uDC89\uD835\uDC82 \uD835\uDC88\uD835\uDC82\uD835\uDC8F\uD835\uDC82\uD835\uDC85\uD835\uDC90.." + pari.getFirst().details(6));
    }
}

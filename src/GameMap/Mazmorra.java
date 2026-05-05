package GameMap;

import Characters.Monstruo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Clase que representa una mazmorra del juego.
 * Contiene información básica como su nombre, nivel y el conjunto de monstruos disponibles.
 * También permite cargar su contenido desde un fichero y seleccionar enemigos aleatorios.
 *
 * @author Isa Barceló
 */

public class Mazmorra {

    private String nombre; /** Nombre de la mazmorra **/
    private int nivel; /** Nivel de dificultad de la mazmorra **/
    private HashSet<Monstruo> monstruos; /** Nivel de dificultad de la mazmorra.*/

    /**
     * Constructor por defecto.
     * Inicializa una mazmorra vacía sin nombre, nivel 0 y sin monstruos.
     */

    public Mazmorra() {
        setNombre("");
        setNivel(0);
        monstruos = new HashSet<>();
    }

    /**
     * Constructor que carga una mazmorra desde un fichero.
     * Lee el nombre, nivel y los monstruos definidos en el archivo.
     *
     * @param fichero archivo que contiene la información de la mazmorra
     * @throws IOException si ocurre un error al leer el fichero
     */

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

    /**
     * Carga los monstruos desde un fichero asociado a la mazmorra.
     * Filtra aquellos que no cumplen los requisitos de nivel.
     *
     * @param fichero archivo de donde se leen los monstruos
     * @return conjunto de monstruos válidos para la mazmorra
     * @throws IOException si ocurre un error al leer el fichero
     */

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

    /**
     * Constructor de copia.
     * Crea una nueva mazmorra a partir de otra existente.
     *
     * @param m mazmorra a copiar
     */

    public Mazmorra(Mazmorra m) {
        setNombre(m.getNombre());
        setNivel(m.getNivel());
        setMonstruos(m.getMonstruos());
    }

    /**
     * Devuelve el nombre de la mazmorra.
     *
     * @return nombre de la mazmorra
     */

    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la mazmorra.
     *
     * @param nombre nuevo nombre
     */

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el nivel de la mazmorra.
     *
     * @return nivel de la mazmorra
     */

    public int getNivel() {
        return nivel;
    }

    /**
     * Establece el nivel de la mazmorra.
     *
     * @param nivel nuevo nivel
     */

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     * Devuelve el conjunto de monstruos de la mazmorra.
     *
     * @return conjunto de monstruos
     */

    public HashSet<Monstruo> getMonstruos() {
        return monstruos;
    }

    /**
     * Establece los monstruos de la mazmorra filtrando por nivel.
     * Solo se aceptan aquellos dentro del rango permitido respecto al nivel de la mazmorra.
     *
     * @param monstruos conjunto de monstruos a asignar
     */

    public void setMonstruos(HashSet<Monstruo> monstruos) {
        HashSet<Monstruo> monstruosSA = new HashSet<>();
        for (Monstruo m : monstruos) {
            if (m.getNivel() <= this.getNivel() + 3 &&
                    m.getNivel() >= this.getNivel() - 3 ) monstruosSA.add(m);
        }
        this.monstruos = new HashSet<>(monstruosSA);
    }

    /**
     * Devuelve un monstruo aleatorio de la mazmorra para combate.
     *
     * @return monstruo aleatorio o null si no hay disponibles
     */

    public Monstruo combateRandom(){
        if (getMonstruos().isEmpty()) return null;
        ArrayList<Monstruo> monstruosSA = new ArrayList<>(getMonstruos());

        Random r  = new Random();
        int i = r.nextInt(monstruosSA.size());

        return monstruosSA.get(i);
    }

    /**
     * Representación en texto de la mazmorra.
     * Muestra su nombre, nivel y todos los monstruos que contiene.
     *
     * @return cadena con la información completa de la mazmorra
     */

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

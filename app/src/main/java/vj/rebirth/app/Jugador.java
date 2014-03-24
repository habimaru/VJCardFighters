package vj.rebirth.app;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Habimaru on 19/03/14.
 */
public class Jugador implements Cloneable{
    String nombre;
    int idGrafx;
    String descripcion;
    ArrayList<String> taunts = new ArrayList<String>();
    String victoria;
    String derrota;
    ArrayList<String> cartas = new ArrayList<String>();
    float HP = 100;
    ArrayList<String> manoActual = new ArrayList<String>();


    public Jugador(String nombre, int idGrafx, String descripcion, ArrayList<String> taunts, String victoria, String derrota, ArrayList<String> cartas) {
        this.nombre = nombre;
        this.idGrafx = idGrafx;
        this.descripcion = descripcion;
        this.taunts = taunts;
        this.victoria = victoria;
        this.derrota = derrota;
        this.cartas = cartas;
    }
    public Jugador(Jugador jugadorCopia){
        this.nombre = jugadorCopia.getNombre();
        this.idGrafx = jugadorCopia.getIdGrafx();
        this.descripcion = jugadorCopia.getDescripcion();
        this.taunts = jugadorCopia.getTaunts();
        this.victoria = jugadorCopia.getVictoria();
        this.derrota = jugadorCopia.getDerrota();
        this.cartas = jugadorCopia.getCartas();
    }


    public ArrayList<String> generarCartas(String tipo1, String tipo2, String noTipo1, String noTipo2){
        ArrayList<String> ret = new ArrayList<String>();
        for(int i=0;i<8;i++){
            ret.add(tipo1);
        }
        for(int i=0;i<5;i++){
            ret.add(tipo2);
        }

        for(int i=0;i<4;i++){
            ret.add("acero");
            ret.add("agua");
            ret.add("bicho");
            ret.add("dragon");
            ret.add("electrico");
            ret.add("fantasma");
            ret.add("fuego");
            ret.add("hada");
            ret.add("hielo");
            ret.add("lucha");
            ret.add("normal");
            ret.add("psiquico");
            ret.add("roca");
            ret.add("siniestro");
            ret.add("tierra");
            ret.add("veneno");
            ret.add("volador");
        }
        ret.remove(noTipo1);
        ret.remove(noTipo2);
        ret.remove(noTipo2);

        return ret;
    }



    public String getNombre() {
        return nombre;
    }

    public int getIdGrafx() {
        return idGrafx;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ArrayList<String> getTaunts() {
        return taunts;
    }

    public String getVictoria() {
        return victoria;
    }

    public String getDerrota() {
        return derrota;
    }

    public ArrayList<String> getCartas() {
        return cartas;
    }

    public void generaManoInicial(){
        Random generator = new Random(System.nanoTime());
        String c0=cartas.get(generator.nextInt(cartas.size()));
        String c1=cartas.get(generator.nextInt(cartas.size()));
        String c2=cartas.get(generator.nextInt(cartas.size()));
        manoActual.clear();
        this.manoActual.add(c0);
        this.manoActual.add(c1);
        this.manoActual.add(c2);
    }

    public void reemplazaCarta(int carta){
        this.manoActual.remove(carta);
        this.manoActual.add(cartas.get((int)(Math.random()*cartas.size())));
    }

    public Jugador getJugador(){
        return this;
    }


    @Override
    protected Jugador clone() throws CloneNotSupportedException {
        return (Jugador) super.clone();
    }
}
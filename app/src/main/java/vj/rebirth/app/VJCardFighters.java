package vj.rebirth.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class VJCardFighters extends ActionBarActivity {
    Jugador jugadorActivo;
    Jugador oponente;
    boolean ataque = true;

    /**
     * Método onCreate. Inicializa jugadorActivo a partir del seleccionado anteriormente y elige
     * un oponente al azar. Acto seguido, inicializa sus manos y las imprime por pantalla
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vjcard_fighters);
        Intent intent = getIntent();
        try {
            jugadorActivo = MainActivity.jugadores.get(MainActivity.jugadorActivo).clone();
            oponente = new Jugador(MainActivity.jugadores.get((int) (Math.random() * MainActivity.jugadores.size())));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        TextView t = (TextView) findViewById(R.id.nombreJugador);
        t.setText(jugadorActivo.getNombre());
        t = (TextView) findViewById(R.id.nombreOponente);
        t.setText(oponente.getNombre());
        ImageView i = (ImageView) findViewById(R.id.imagenJugador);
        i.setImageResource(jugadorActivo.getIdGrafx());
        i = (ImageView) findViewById(R.id.imagenOponente);
        i.setImageResource(oponente.getIdGrafx());
        jugadorActivo.generaManoInicial();
        oponente.generaManoInicial();
        refrescarCartas();
        jugadorActivo.HP=100;
        oponente.HP=100;

    }

    /**
     * Método onCreateOptionsMenu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vjcard_fighters, menu);
        return true;
    }

    /**
     * Método onOptionsItemSelected
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


    /**
     * Ataque (o, depende del turno, defensa). Toma un valor básico de 10 y le aplica un modificador
     * en función de la efectividad de tipos. Si el ataque es súper efectivo, el atacante soltará una
     * burla. Si es la defensa lo efectivo, será el defendiente quien se burle.
     *
     * Se restará al atacado el daño eficaz y, en caso de llevar el HP a cero, finalizará la partida.
     * @param jug tipo jugador
     * @param opo tipo oponente
     */
    public void atacar(String jug, String opo){
        float dano = 10;
        float mod=0;
        if(ataque){
            mod = calculaDano(jug,opo);
            TextView t = (TextView) findViewById(R.id.taunt);
            if(mod<1){
                t.setText(oponente.taunts.get((int)(Math.random()*oponente.getTaunts().size())));
                t.setTextColor(Color.BLUE);
            }
            if(mod>=2){
                t.setText(jugadorActivo.taunts.get((int)(Math.random()*jugadorActivo.getTaunts().size())));
                t.setTextColor(Color.RED);
            }
            if(mod==1){
                t.setText("");
            }
            dano = dano*mod;

            oponente.HP=oponente.HP-dano;
            ProgressBar p = (ProgressBar) findViewById(R.id.hpOpo);
            p.setProgress((int)oponente.HP);
            if(oponente.HP<=0){
                t = (TextView) findViewById(R.id.fraseJug);
                t.setText(jugadorActivo.victoria);
                t = (TextView) findViewById(R.id.fraseOpo);
                t.setText(oponente.derrota);
                t = (TextView) findViewById(R.id.taunt);
                t.setText("");
                finalizarPartida();
            }
            ataque=false;

        }else{
            mod = calculaDano(opo,jug);
            TextView t = (TextView) findViewById(R.id.taunt);
            if(mod<1){
                t.setText(jugadorActivo.taunts.get((int)(Math.random()*jugadorActivo.getTaunts().size())));
                t.setTextColor(Color.RED);
            }
            if(mod>=2){
                t.setText(oponente.taunts.get((int)(Math.random()*oponente.getTaunts().size())));
                t.setTextColor(Color.BLUE);
            }
            if(mod==1){
                t.setText("");
            }
            dano = dano*mod;
            jugadorActivo.HP=jugadorActivo.HP-dano;
            ProgressBar p = (ProgressBar) findViewById(R.id.hpJug);
            p.setProgress((int)jugadorActivo.HP);

            if(jugadorActivo.HP<=0){
                t = (TextView) findViewById(R.id.fraseJug);
                t.setText(jugadorActivo.derrota);
                t = (TextView) findViewById(R.id.fraseOpo);
                t.setText(oponente.victoria);
                t = (TextView) findViewById(R.id.taunt);
                t.setText("");
                finalizarPartida();
            }
            ataque=true;
        }
    }

    /**
     * Finaliza la partida. Para ello, bloquea los botones del usuario
     */
    public void finalizarPartida(){
        ImageView i = (ImageView) findViewById(R.id.cartaJ0);
        i.setEnabled(false);
        i = (ImageView) findViewById(R.id.cartaJ1);
        i.setEnabled(false);
        i = (ImageView) findViewById(R.id.cartaJ2);
        i.setEnabled(false);
    }

    /**
     * Refresca gráficamente las manos de jugador y oponente. También comprueba si es turno de ataque
     * o defensa y lo muestra por pantalla
     */
    public void refrescarCartas() {
        ImageView i = (ImageView) findViewById(R.id.cartaJ0);
        i.setImageResource(idCarta(jugadorActivo.manoActual.get(0)));
        i = (ImageView) findViewById(R.id.cartaJ1);
        i.setImageResource(idCarta(jugadorActivo.manoActual.get(1)));
        i = (ImageView) findViewById(R.id.cartaJ2);
        i.setImageResource(idCarta(jugadorActivo.manoActual.get(2)));
        i = (ImageView) findViewById(R.id.cartaO0);
        i.setImageResource(idCarta(oponente.manoActual.get(0)));
        i = (ImageView) findViewById(R.id.cartaO1);
        i.setImageResource(idCarta(oponente.manoActual.get(1)));
        i = (ImageView) findViewById(R.id.cartaO2);
        i.setImageResource(idCarta(oponente.manoActual.get(2)));
        TextView t = (TextView) findViewById(R.id.AD);
        if(ataque){
            t.setText("¡Ataca!");
        }else{
            t.setText("¡Defiende!");
        }
    }

    /**
     * Dado el tipo de una carta muetra el gráfico de ésta
     * @param carta tipo de la carta
     * @return id del gráfico
     */
    public int idCarta(String carta) {
        if (carta.equals("acero"))
            return R.drawable.acero;
        if (carta.equals("agua"))
            return R.drawable.agua;
        if (carta.equals("bicho"))
            return R.drawable.bicho;
        if (carta.equals("dragon"))
            return R.drawable.dragon;
        if (carta.equals("electrico"))
            return R.drawable.electrico;
        if (carta.equals("fantasma"))
            return R.drawable.fantasma;
        if (carta.equals("fuego"))
            return R.drawable.fuego;
        if (carta.equals("hada"))
            return R.drawable.hada;
        if (carta.equals("hielo"))
            return R.drawable.hielo;
        if (carta.equals("lucha"))
            return R.drawable.lucha;
        if (carta.equals("normal"))
            return R.drawable.normal;
        if (carta.equals("planta"))
            return R.drawable.planta;
        if (carta.equals("psiquico"))
            return R.drawable.psiquico;
        if (carta.equals("roca"))
            return R.drawable.roca;
        if (carta.equals("siniestro"))
            return R.drawable.siniestro;
        if (carta.equals("tierra"))
            return R.drawable.tierra;
        if (carta.equals("veneno"))
            return R.drawable.veneno;
        if (carta.equals("volador"))
            return R.drawable.volador;
        if (carta.equals("limit"))
            return R.drawable.limit;
        return R.drawable.habimaru;
    }

    /**
     * En función del tipo de ataque y defensa, calcula el modificador de daño. La tabla está "hardcoded"
     * @param ataque
     * @param defensa
     * @return
     */
    public float calculaDano(String ataque, String defensa){
        float ret=1;
        if(ataque.equals("acero")){
            if(defensa.equals("fuego")||defensa.equals("agua")||defensa.equals("electrico")||defensa.equals("acero")){
                ret=0.5f;
            }
            if(defensa.equals("hielo")||defensa.equals("hada")){
                ret=2f;
            }
        }
        if(ataque.equals("agua")){
            if(defensa.equals("agua")||defensa.equals("planta")||defensa.equals("dragon")){
                ret=0.5f;
            }
            if(defensa.equals("fuego")||defensa.equals("tierra")||defensa.equals("roca")){
                ret=2f;
            }
        }
        if(ataque.equals("bicho")){
            if(defensa.equals("fuego")||defensa.equals("lucha")||defensa.equals("veneno")
                    ||defensa.equals("volador")||defensa.equals("fantasma")||defensa.equals("acero")
                    ||defensa.equals("hada")){
                ret=0.5f;
            }
            if(defensa.equals("planta")||defensa.equals("psiquico")||defensa.equals("siniestro")){
                ret=2f;
            }
        }
        if(ataque.equals("dragon")){
            if(defensa.equals("acero")){
                ret=0.5f;
            }
            if(defensa.equals("dragon")){
                ret=2f;
            }
            if(defensa.equals("hada")){
                ret=0f;
            }
        }
        if(ataque.equals("electrico")){
            if(defensa.equals("electrico")||defensa.equals("planta")||defensa.equals("dragon")){
                ret=0.5f;
            }
            if(defensa.equals("agua")||defensa.equals("volador")){
                ret=2f;
            }
            if(defensa.equals("tierra")){
                ret=0f;
            }
        }
        if(ataque.equals("fantasma")){
            if(defensa.equals("siniestro")){
                ret=0.5f;
            }
            if(defensa.equals("fantasma")||defensa.equals("psiquico")){
                ret=2f;
            }
            if(defensa.equals("normal")){
                ret=0f;
            }
        }
        if(ataque.equals("fuego")){
            if(defensa.equals("fuego")||defensa.equals("roca")||defensa.equals("agua")
                    ||defensa.equals("dragon")){
                ret=0.5f;
            }
            if(defensa.equals("planta")||defensa.equals("hielo")||defensa.equals("bicho")
                    ||defensa.equals("acero")){
                ret=2f;
            }
        }
        if(ataque.equals("hada")){
            if(defensa.equals("fuego")||defensa.equals("lucha")||defensa.equals("acero")){
                ret=0.5f;
            }
            if(defensa.equals("lucha")||defensa.equals("dragon")||defensa.equals("siniestro")){
                ret=2f;
            }
        }
        if(ataque.equals("hielo")){
            if(defensa.equals("fuego")||defensa.equals("agua")||defensa.equals("hielo")||defensa.equals("acero")){
                ret=0.5f;
            }
            if(defensa.equals("planta")||defensa.equals("dragon")||defensa.equals("volador")
                    ||defensa.equals("tierra")){
                ret=2f;
            }
        }
        if(ataque.equals("lucha")){
            if(defensa.equals("veneno")||defensa.equals("volador")||defensa.equals("psiquico")
                    ||defensa.equals("bicho")||defensa.equals("hada")){
                ret=0.5f;
            }
            if(defensa.equals("normal")||defensa.equals("hielo")||defensa.equals("roca")
                    ||defensa.equals("siniestro")||defensa.equals("acero")){
                ret=2f;
            }
        }
        if(ataque.equals("lucha")){
            if(defensa.equals("veneno")||defensa.equals("volador")||defensa.equals("psiquico")
                    ||defensa.equals("bicho")||defensa.equals("hada")){
                ret=0.5f;
            }
            if(defensa.equals("normal")||defensa.equals("hielo")||defensa.equals("roca")
                    ||defensa.equals("siniestro")||defensa.equals("acero")){
                ret=2f;
            }
            if(defensa.equals("fantasma")){
                ret=0f;
            }
        }
        if(ataque.equals("normal")){
            if(defensa.equals("roca")||defensa.equals("acero")){
                ret=0.5f;
            }
            if(defensa.equals("fantasma")){
                ret=0f;
            }
        }
        if(ataque.equals("psiquico")){
            if(defensa.equals("psiquico")||defensa.equals("acero")){
                ret=0.5f;
            }
            if(defensa.equals("lucha")||defensa.equals("veneno")){
                ret=2f;
            }
            if(defensa.equals("siniestro")){
                ret=0f;
            }
        }
        if(ataque.equals("roca")){
            if(defensa.equals("lucha")||defensa.equals("tierra")||defensa.equals("acero")){
                ret=0.5f;
            }
            if(defensa.equals("fuego")||defensa.equals("hielo")||defensa.equals("bicho")||defensa.equals("volador")){
                ret=2f;
            }
            if(defensa.equals("siniestro")){
                ret=0f;
            }
        }
        if(ataque.equals("siniestro")){
            if(defensa.equals("lucha")||defensa.equals("siniestro")||defensa.equals("hada")){
                ret=0.5f;
            }
            if(defensa.equals("psiquico")||defensa.equals("fantasma")){
                ret=2f;
            }
        }
        if(ataque.equals("tierra")){
            if(defensa.equals("planta")||defensa.equals("bicho")){
                ret=0.5f;
            }
            if(defensa.equals("fuego")||defensa.equals("electrico")||defensa.equals("veneno")
                    ||defensa.equals("roca")||defensa.equals("acero")){
                ret=2f;
            }
        }
        if(ataque.equals("veneno")){
            if(defensa.equals("veneno")||defensa.equals("tierra")||defensa.equals("roca")
                    ||defensa.equals("fantasma")){
                ret=0.5f;
            }
            if(defensa.equals("planta")||defensa.equals("hada")){
                ret=2f;
            }
            if(defensa.equals("acero")){
                ret=0f;
            }
        }
        if(ataque.equals("volador")){
            if(defensa.equals("electrico")||defensa.equals("roca")||defensa.equals("acero")){
                ret=0.5f;
            }
            if(defensa.equals("planta")||defensa.equals("lucha")||defensa.equals("bicho")){
                ret=2f;
            }
        }
        if(defensa.equals("limit")){
            ret=0f;
        }
        if(ataque.equals("limit")){
            ret=3f;
        }
        return ret;
    }



/**
 * MÉTODOS DE ENTRADA DEL JUGADOR
 */
    /**
     * Usa la carta superior y fuerza al oponente a usar una de las suyas
     * @param v
     */
    public void carta0(View v) {
        ImageView i = (ImageView) findViewById(R.id.cartaJug);
        i.setImageResource(idCarta(jugadorActivo.manoActual.get(0)));
        int cartaOponente = (int)(Math.random()*3);
        i = (ImageView) findViewById(R.id.cartaOpo);
        i.setImageResource(idCarta(oponente.manoActual.get(cartaOponente)));
        atacar(jugadorActivo.manoActual.get(2),oponente.manoActual.get(cartaOponente));
        oponente.reemplazaCarta(cartaOponente);
        jugadorActivo.reemplazaCarta(0);
        refrescarCartas();
    }
    /**
     * Usa la carta media y fuerza al oponente a usar una de las suyas
     * @param v
     */
    public void carta1(View v) {
        ImageView i = (ImageView) findViewById(R.id.cartaJug);
        i.setImageResource(idCarta(jugadorActivo.manoActual.get(1)));

        int cartaOponente = (int)(Math.random()*3);
        i = (ImageView) findViewById(R.id.cartaOpo);
        i.setImageResource(idCarta(oponente.manoActual.get(cartaOponente)));
        atacar(jugadorActivo.manoActual.get(1),oponente.manoActual.get(cartaOponente));
        oponente.reemplazaCarta(cartaOponente);
        jugadorActivo.reemplazaCarta(1);
        refrescarCartas();
    }
    /**
     * Usa la carta inferior y fuerza al oponente a usar una de las suyas
     * @param v
     */
    public void carta2(View v) {
        ImageView i = (ImageView) findViewById(R.id.cartaJug);
        i.setImageResource(idCarta(jugadorActivo.manoActual.get(2)));
        int cartaOponente = (int)(Math.random()*3);
        i = (ImageView) findViewById(R.id.cartaOpo);
        i.setImageResource(idCarta(oponente.manoActual.get(cartaOponente)));
        atacar(jugadorActivo.manoActual.get(2),oponente.manoActual.get(cartaOponente));
        oponente.reemplazaCarta(cartaOponente);
        jugadorActivo.reemplazaCarta(2);
        refrescarCartas();

    }
}
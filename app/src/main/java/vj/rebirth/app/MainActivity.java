package vj.rebirth.app;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {
    /**
     * El programa, a nivel básico contará sólo con dos datos: la lista de personajes disponible y el
     * índice del seleccionado por el jugador
     */
    static ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    static int jugadorActivo = 0;


    /**
     * Al crearse la actividad principal, inicializa la lista de personajes y refresca los personajes
     * que aparecen en pantalla
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initJugadores();
        ImageView i=(ImageView)findViewById(R.id.jugadorIzq);
        i.setImageResource(jugadores.get(jugadores.size()-1).getIdGrafx());
        TextView t=(TextView)findViewById(R.id.nombrePersonaje);
        t.setText(jugadores.get(0).getNombre());
        TextView t2=(TextView)findViewById(R.id.descripcionPersonaje);
        t2.setText(jugadores.get(0).getDescripcion());
        i=(ImageView)findViewById(R.id.imageView);
        i.setImageResource(jugadores.get(0).getIdGrafx());
        i=(ImageView)findViewById(R.id.jugadorDer);
        i.setImageResource(jugadores.get(1).getIdGrafx());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.ayuda) {
            ayuda(this.getCurrentFocus());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Inicializa todos los jugadores y los guarda en un ArrayList
     * Está planeado, en futuras versiones, tomarlo directamente de una base de datos, permitiendo así
     * almacenar estadísticas de juego
     */
    public void initJugadores(){
        ArrayList<String> taunts = new ArrayList<String>();
        taunts.add("Dios, eres peor que Code Geass");
        taunts.add("Hail Habimaru");
        taunts.add("Eso por no leer mis libros :(");
        jugadores.add(new Jugador("Habimaru", R.drawable.habimaru, "Pikachu Supersaiyan de nivel 3",taunts, "¿Qué esperabas? Yo hice este juego", "Volveré con mas fuerza", generarCartas("electrico","lucha","planta","roca")));
        taunts = new ArrayList<String>();
        taunts.add("Ninja'd");
        taunts.add("Lo que llevo en el cuello es un pañuelo, no dientes");
        taunts.add("Tú debes ser el Vincent de este juego, ¿no?");
        jugadores.add(new Jugador("Kaos", R.drawable.kaos, "Carismático Dictador de Kaosonia",taunts, "Ea, que te la pique un pollo", "Ay, me han crujido el lomo", generarCartas("electrico","fantasma","fuego","roca")));
        taunts= new ArrayList<String>();
        taunts.add("EL PODER DE LA AMISTAD");
        taunts.add("Esto lo aprendí con un libro de Tolkien");
        taunts.add("Esto lo aprendí con Harry Potter");
        taunts.add("Tu ataque está sobrevalorado :3");
        jugadores.add(new Jugador("Malfuin", R.drawable.malfuin, "El Viajero Carente de Rumbo",taunts, "ewé", "uwu", generarCartas("electrico","fuego","agua","siniestro")));
        taunts = new ArrayList<String>();
        taunts.add("*chiste malo*");
        taunts.add("*chiste MUY malo*");
        taunts.add("*chiste MUY, MUY malo*");
        taunts.add("*chiste MUY, pero que MUY, MUY malo*");
        taunts.add("Siri, what's my name?");
        taunts.add("Jafar desnudo, jaja");
        jugadores.add(new Jugador("Flan", R.drawable.flan, "Cons. Pref. Antes del 03/11/2015",taunts, "*chiste de flan*", "*se va al rincón*", generarCartas("tierra","planta","agua","volador")));
        taunts = new ArrayList<String>();
        taunts.add("Si sigo así, me ligaré una chica");
        taunts.add("Y esto es lo que le hago a las tías que fuman");
        taunts.add("QUE HE VISTO HAJIME NO IPPO Y ESTOY QUE LO FLIPO Y TE MUELO A HOSTIAS");
        jugadores.add(new Jugador("Dark", R.drawable.dark, "Eón Dorado",taunts, "Suele pasar", "Qué guarra...", generarCartas("fuego","dragon","roca","agua")));
        taunts= new ArrayList<String>();
        taunts.add("POR EUSKALHERRIA");
        taunts.add("ESCALDAAAR");
        taunts.add("MI TRAJE ES ARMADURA DE VERDAD");
        taunts.add("DERRÍTETE COMO ME DERRITO YO POR KIRBY");
        jugadores.add(new Jugador("Lighthelco", R.drawable.light, "Guerrera aficionada al agua caliente",taunts, "SÍIIII", "Esto es peor que Glazed :(", generarCartas("fuego","psiquico","planta","veneno")));
        taunts= new ArrayList<String>();
        taunts.add("Besa mi brillante culo metálico (jeje soy original)");
        taunts.add("WARK WARK WAAARK");
        taunts.add("Esto te demostrará que no soy un buitre");
        jugadores.add(new Jugador("Crow", R.drawable.crow, "¡No soy un buitre!",taunts, "WARK WARK", "Wark...", generarCartas("siniestro","electrico","hada","bicho")));
        taunts = new ArrayList<String>();
        taunts.add("Bueno, Monokuma te haría más daño que yo");
        taunts.add("¿Qué quieres decir? ¿Que no estoy siendo suficientemente borde?");
        jugadores.add(new Jugador("Rayden", R.drawable.rayden, "El OTRO canoso",taunts, "¡Soy el canoso de verdad!", "¿Por qué no soy el verdadero canoso?", generarCartas("electrico","agua","normal","hielo")));
        taunts = new ArrayList<String>();
        taunts.add("Mochiii :3");
        taunts.add("Sopa de miso :3");
        taunts.add("Rameeen :3");
        taunts.add("Sushiiii :3");
        taunts.add("¡Japonudo!");
        jugadores.add(new Jugador("Ari", R.drawable.ari, "Desfase horario",taunts, "Kawaii desu ne!!!!", "Eso no es kawaii", generarCartas("fuego","dragon","veneno","hielo")));
        taunts = new ArrayList<String>();
        taunts.add("SI BAER T BASILA T T KAYA I LO ASIMILA");
        taunts.add("BCM EL HORTO");
        taunts.add("KBRONASO SUFRE");
        jugadores.add(new Jugador("Vaeryn", R.drawable.vaer, "T KAYA I LO ASIMILAH",taunts, "BCM L HRTO", "K KVRON", generarCartas("lucha","acero","hada","dragon")));
        taunts = new ArrayList<String>();
        taunts.add("Tomo SMT para desayunar, ¿crees que vas a poder conmigo? JAJAJA");
        taunts.add("Vigila tu ortografía");
        taunts.add("EL PODER DEL TRADUCTOR");
        jugadores.add(new Jugador("ShySpy", R.drawable.shy, "El alfil afilado que abandonó Teleco",taunts, "Sieg Heil, Grammar!", "Esto es peor que Transmisión de Ondas", generarCartas("siniestro","acero","psiquico","planta")));
        taunts = new ArrayList<String>();
        taunts.add("Sufre. Supongo.");
        taunts.add("Te dolerá. Espero.");
        taunts.add("Parece que soy mejor que tú");
        jugadores.add(new Jugador("Drail", R.drawable.drail, "Qué",taunts, "Bien.", "Meh.", generarCartas("tierra","fuego","psiquico","normal")));
        taunts= new ArrayList<String>();
        taunts.add("¡Que no soy informático!");
        taunts.add("Este juego me costó 1500TC y TE VOY A GANAR");
        taunts.add("EL PODER DEL HENTAI");
        taunts.add("Visitad mis webs hentai :3");
        jugadores.add(new Jugador("Loud", R.drawable.loud, "VEAN TODOS MI HENTAI",taunts, "ESTO ME DA 0.002€, ¿NO?", "Pues me voy al DotA", generarCartas("psiquico","agua","bicho","lucha")));
        taunts=new ArrayList<String>();
        taunts.add("FZZZZZZT");
        taunts.add("Les daré una golpisa");
        taunts.add("xoxoxoxoxo");
        jugadores.add(new Jugador("Soda", R.drawable.soda, "El refrescante",taunts, "Les di una golpisa", "Me quedé sin burbujas", generarCartas("agua","dragon","roca","normal")));
        taunts= new ArrayList<String>();
        taunts.add("KEBAB");
        taunts.add("SAKKA");
        taunts.add("LLIARZ");
        taunts.add("FURGOL");
        jugadores.add(new Jugador("Xevii", R.drawable.xevii, "FUTBOL LLIARZ KEBAB",taunts, "GOL", "Hoy me quedo sin kebabs", generarCartas("agua","fuego","fantasma","siniestro")));
        taunts= new ArrayList<String>();
        taunts.add("Eres más tonto que Square");
        taunts.add("Menos mal que este juego tiene parches pequeños");
        taunts.add("Tu elección ha sido más monguer que elegir al PP");
        taunts.add("¿En serio? ¿Un juego para Android? Haz triple A, coñe");
        jugadores.add(new Jugador("León", R.drawable.leon, "Monguerismos disponibles",taunts, "Jaja, qué monguer", "¿Este juego lo ha hecho Square o qué?", generarCartas("fuego","agua","bicho","siniestro")));
        taunts=new ArrayList<String>();
        taunts.add("*barba erizándose*");
        jugadores.add(new Jugador("TrueFaiterMan", R.drawable.trufa, "En barba y hueso",taunts, "*barba molona*", "*barba triste*", generarCartas("fuego","agua","bicho","siniestro")));

    }

    /**
     * Genera el mazo de cartas del jugador en función de sus tipos preferentes y no preferentes
     * @param tipo1 Tipo preferido 1
     * @param tipo2 Tipo preferido 2
     * @param noTipo1 Tipo no preferido 1
     * @param noTipo2 Tipo no preferido 2
     * @return ArrayList con el mazo
     */
    public ArrayList<String> generarCartas(String tipo1, String tipo2, String noTipo1, String noTipo2){
        ArrayList<String> ret = new ArrayList<String>();
        for(int i=0;i<8;i++){
            ret.add(tipo1);
        }
        for(int i=0;i<4;i++){
            ret.add(tipo2);
        }
        ret.add("limit");
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

    /**
     * Avanza la lista de jugadores a la izquierda
     * @param v
     */
    public void shiftI(View v){
        ImageView i=(ImageView)findViewById(R.id.jugadorDer);
        i.setImageResource(jugadores.get(jugadorActivo).getIdGrafx());
        if(jugadorActivo==0){
            jugadorActivo=jugadores.size()-1;
        }else{
            jugadorActivo--;
        }
        TextView t=(TextView)findViewById(R.id.nombrePersonaje);
        t.setText(jugadores.get(jugadorActivo).getNombre());
        TextView t2=(TextView)findViewById(R.id.descripcionPersonaje);
        t2.setText(jugadores.get(jugadorActivo).getDescripcion());
        i=(ImageView)findViewById(R.id.imageView);
        i.setImageResource(jugadores.get(jugadorActivo).getIdGrafx());
        i=(ImageView)findViewById(R.id.jugadorIzq);
        if(jugadorActivo==0){
            i.setImageResource(jugadores.get(jugadores.size()-1).getIdGrafx());
        }else{
            i.setImageResource(jugadores.get(jugadorActivo-1).getIdGrafx());
        }


    }

    /**
     * Avanza la lista de jugadores a la derecha
     * @param v
     */
    public void shiftD(View v){
        ImageView i=(ImageView)findViewById(R.id.jugadorIzq);
        System.out.println(jugadorActivo);
        i.setImageResource(jugadores.get(jugadorActivo).getIdGrafx());
        if(jugadorActivo==jugadores.size()-1){
            jugadorActivo=0;
        }else{
            jugadorActivo++;
        }
        TextView t=(TextView)findViewById(R.id.nombrePersonaje);
        t.setText(jugadores.get(jugadorActivo).getNombre());
        TextView t2=(TextView)findViewById(R.id.descripcionPersonaje);
        t2.setText(jugadores.get(jugadorActivo).getDescripcion());
        i=(ImageView)findViewById(R.id.imageView);
        i.setImageResource(jugadores.get(jugadorActivo).getIdGrafx());
        i=(ImageView)findViewById(R.id.jugadorDer);
        if(jugadorActivo==jugadores.size()-1){
            i.setImageResource(jugadores.get(0).getIdGrafx());
        }else{
            i.setImageResource(jugadores.get(jugadorActivo+1).getIdGrafx());
        }

    }

    /**
     * Inicia una partida con el jugador activo
     * @param v
     */
    public void newGame(View v){
        Intent intent = new Intent(this, VJCardFighters.class);
        startActivity(intent);
    }

    /**
     * Inicia la pantalla "ayuda"
     * @param v
     */
    public void ayuda(View v){
        Intent intent = new Intent(this, Ayuda.class);
        startActivity(intent);
    }

}

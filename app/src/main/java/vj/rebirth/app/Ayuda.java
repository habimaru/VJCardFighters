package vj.rebirth.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.util.ArrayList;

/**
 * La actividad ayuda muestra una pantalla con un pequeño tutorial sobre cómo jugar
 */
public class Ayuda extends ActionBarActivity {
    ArrayList<String> info = new ArrayList<String>();
    ArrayList<String> img= new ArrayList<String>();
    int posicion=0;

    /**
     * El botón "avanzar" llama a este método, que avanza la página
     * @param v
     */
    public void pagMas(View v){
        posicion++;
        refrescar();
    }

    /**
     * El botón "retroceder" llama a este método, que retrocede la página
     * @param v
     */
    public void pagMenos(View v){
        posicion--;
        refrescar();
    }

    /**
     * Inicializa los campos de texto e imágenes necesarios
     */
    public void init(){
        info.add("Bueno, esto no tiene mucha ciencia. Elige tu personaje y... ¡A luchar! ");
        info.add("Pulsa en los jugadores de los lados para moverte por la pantalla de selección");
        info.add("En el turno de ataque, tendrás que elegir una carta para atacar. ¡Intenta ser muy efectivo!");
        info.add("En el de defensa, tu carta será para defender. ¡Intenta que tu elemento sea inmune o resistente!");
        info.add("Si tu ataque es muy efectivo o tu defensa muy resistente, soltarás una burla. ¡Pruébalas todas!");
        info.add("Al agotar el HP de uno de los jugadores, acabará la partida");
        img.add("ayuda1");
        img.add("ayuda2");
        img.add("ayuda3");

    }

    /**
     * Refresca la pantalla. Normalmente, lo hace cuando se cambia de página
     */
    public void refrescar(){
        EditText t = (EditText) findViewById(R.id.editText);
        t.setText(info.get(posicion*2));
        t = (EditText) findViewById(R.id.editText2);
        t.setText(info.get((posicion*2)+1));
        ImageView i = (ImageView) findViewById(R.id.imageView);
        i.setImageResource(getId(img.get(posicion)));
        if(posicion==0){
            Button b =(Button) findViewById(R.id.button2);
            b.setEnabled(false);
            b =(Button) findViewById(R.id.button);
            b.setEnabled(true);
        }else{
            if(posicion==2){
                Button b =(Button) findViewById(R.id.button);
                b.setEnabled(false);
                b =(Button) findViewById(R.id.button2);
                b.setEnabled(true);
            }
            else{
                Button b =(Button) findViewById(R.id.button);
                b.setEnabled(true);
                b =(Button) findViewById(R.id.button2);
                b.setEnabled(true);
            }
        }
    }

    public int getId(String str){
        int out =0;
        if(str.equals("ayuda1")){
            out=R.drawable.ayuda1;
        }
        if(str.equals("ayuda2")){
            out=R.drawable.ayuda2;
        }
        if(str.equals("ayuda3")){
            out=R.drawable.ayuda3;
        }
        return out;
    }

    /**
     * Método onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
        init();
        refrescar();
    }

    /**
     * Método onCreateOptionsMenu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ayuda, menu);
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

}

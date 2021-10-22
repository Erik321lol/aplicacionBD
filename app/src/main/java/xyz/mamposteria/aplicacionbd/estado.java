package xyz.mamposteria.aplicacionbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class estado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado);
        System.out.println(seguimiento.codigo);
        estadoactual(seguimiento.codigo);
    }


    public void estadoactual (int actual){
        String estd = "";
        if (actual == 1){
            estd = "EN TIENDA";
        }else if(actual == 2){
            estd = "EN CAMINO";
        }else if(actual == 3){
            estd = "ENTREGADO";
        }
        TextView textView = (TextView)findViewById(R.id.textView5);
        textView.setText("El ESTADO ACTUAL DEL PEDIDO ES: " + estd);
    }
    //boton
    public void regresarseguimiento(View view){
        Intent sig = new Intent(this, seguimiento.class);
        startActivity(sig);
    }
}
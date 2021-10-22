package xyz.mamposteria.aplicacionbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        coneccion llamar = new coneccion();
    }

    //boton

    public void irseguimiento(View view){
        Intent sig = new Intent(this, seguimiento.class);
        startActivity(sig);
    }

    public void irlogin(View view){
        Intent sig = new Intent(this, login.class);
        startActivity(sig);
    }

    public void irproductos(View view){
        Intent sig = new Intent(this, productos.class);
        startActivity(sig);
    }

}
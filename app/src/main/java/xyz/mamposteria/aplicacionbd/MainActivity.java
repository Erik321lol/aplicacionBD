package xyz.mamposteria.aplicacionbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.mamposteria.aplicacionbd.interfaces.api;
import xyz.mamposteria.aplicacionbd.models.usuario;

public class MainActivity extends AppCompatActivity {

    EditText edtCodigo;
    TextView tvNombre;
    TextView tvPrecio;
    TextView tvDescripcion;
    Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtCodigo=findViewById(R.id.edtCodigo);
        tvNombre=findViewById(R.id.tvNombre);
        tvPrecio=findViewById(R.id.tvPrecio);
        tvDescripcion=findViewById(R.id.tvDescripcion);
        btnBuscar=findViewById(R.id.btnBuscar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("boton");
                find(edtCodigo.getText().toString());
            }
        });
    }

    private void find(String cod){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.2:8081/")
            .addConverterFactory(GsonConverterFactory.create()).build();

         api Api =retrofit.create(api.class);
         Call<usuario> call = Api.find(cod);
         call.enqueue(new Callback<usuario>() {

             @Override
             public void onResponse(Call<usuario> call, Response<usuario> response) {
                 try{
                     if(response.isSuccessful()){
                         usuario p = response.body();
                         tvNombre.setText(p.getContrasena());
                     }
                 }catch (Exception ex){
                     Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT);
                 }
             }

             @Override
             public void onFailure(Call<usuario> call, Throwable t) {
                 Toast.makeText(MainActivity.this, "Error de coneccion", Toast.LENGTH_SHORT);
             }
         });

    }

    //metodos del boton
    /*public void cobrar(View view){
        Intent sig = new Intent(this, MainActivity.class);
        startActivity(sig);
    }*/
}
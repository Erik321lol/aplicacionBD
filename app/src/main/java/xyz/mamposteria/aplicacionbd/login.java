package xyz.mamposteria.aplicacionbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.mamposteria.aplicacionbd.interfaces.api;
import xyz.mamposteria.aplicacionbd.models.usuario;

public class login extends AppCompatActivity {

    EditText edtUsuario;
    EditText edtContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    //consultar la contrasena

    private void find(String user){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.2:8081/")
            .addConverterFactory(GsonConverterFactory.create()).build();

        api Api =retrofit.create(api.class);
        Call<usuario> call = Api.find(user);
        call.enqueue(new Callback<usuario>() {
            @Override
            public void onResponse(Call<usuario> call, Response<usuario> response) {
                System.out.println("estamos aca");
                try{
                    if(response.isSuccessful()){
                        usuario p = response.body();
                        System.err.println(p.getContrasena());
                        edtUsuario=findViewById(R.id.Usuario);
                        edtContrasena=findViewById(R.id.Contrasena);
                        if(edtContrasena.getText().toString().equals(p.getContrasena())){
                            cambiar();
                        }else{
                            Toast.makeText(getApplicationContext(), "Error en credenciales", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception ex){
                    Toast.makeText(login.this, ex.getMessage(), Toast.LENGTH_SHORT);
                }

            }

            @Override
            public void onFailure(Call<usuario> call, Throwable t) {
                Toast.makeText(login.this, "Error de coneccion", Toast.LENGTH_SHORT);
            }
        });
    }

     public void login(View view){
         edtUsuario=findViewById(R.id.Usuario);
         find(edtUsuario.getText().toString());
         System.out.println();
     }

     public void cambiar(){
         Intent sig = new Intent(this, codigopedidoadmin.class);
         startActivity(sig);
     }

    //boton

    public void regresarinicio(View view){
        Intent sig = new Intent(this, inicio.class);
        startActivity(sig);
    }
}
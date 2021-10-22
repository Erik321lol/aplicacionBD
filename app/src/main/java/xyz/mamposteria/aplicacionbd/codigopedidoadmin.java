package xyz.mamposteria.aplicacionbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.mamposteria.aplicacionbd.interfaces.api;
import xyz.mamposteria.aplicacionbd.interfaces.apipedido;
import xyz.mamposteria.aplicacionbd.models.pedido;
import xyz.mamposteria.aplicacionbd.models.usuario;

public class codigopedidoadmin extends AppCompatActivity {

    EditText edtCodigo;
    public static int codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigopedidoadmin);
    }

    //consultar codigo

    private void find(int id){

        System.out.println("aca estoy");

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.2:8081/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        apipedido Api = retrofit.create(apipedido.class);
        Call<pedido> call = Api.find(id);
        call.enqueue(new Callback<pedido>() {
            @Override
            public void onResponse(Call<pedido> call, Response<pedido> response) {
                System.out.println("estamos aca");
                try{
                    if(response.isSuccessful()){
                        pedido p = response.body();
                        System.err.println(p.getEstado());
                        cambiar();
                    }else{
                        Toast.makeText(getApplicationContext(), "Este Codigo no existe", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex){
                    Toast.makeText(codigopedidoadmin.this, ex.getMessage(), Toast.LENGTH_SHORT);
                }
            }
            @Override
            public void onFailure(Call<pedido> call, Throwable t) {
                Toast.makeText(codigopedidoadmin.this, "Error de coneccion", Toast.LENGTH_SHORT);
            }
        });
    }

    //botones

    public void irinformacionpedido(View view){
        System.out.println("holamundo");
        edtCodigo = findViewById(R.id.edtcodigo1);
        System.out.println("acaafjkd");
        codigo = Integer.parseInt(edtCodigo.getText().toString());
        find(Integer.parseInt(edtCodigo.getText().toString()));
    }

    public void cambiar(){
        Intent sig = new Intent(this, informacionpedido.class);
        startActivity(sig);
    }

    public void regresarlogin(View view){
        Intent sig = new Intent(this, login.class);
        startActivity(sig);
    }

}
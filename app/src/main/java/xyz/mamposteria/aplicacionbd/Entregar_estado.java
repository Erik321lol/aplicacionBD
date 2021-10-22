package xyz.mamposteria.aplicacionbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.mamposteria.aplicacionbd.interfaces.apiproducto;
import xyz.mamposteria.aplicacionbd.interfaces.pedidos;
import xyz.mamposteria.aplicacionbd.models.pedido;
import xyz.mamposteria.aplicacionbd.models.productos1;

import static xyz.mamposteria.aplicacionbd.datospedido.acutales;

public class Entregar_estado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregar_estado);
        coneccion();
/*
        Intent intent = getIntent();
        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("PaymentAmount"));
        }catch (JSONException e){
            e.printStackTrace();
        }*/
    }

   /* private void verDetalles(JSONObject response, String monto){
        try {
            System.out.println(response.getString("id"));
            System.out.println(response.getString("state"));
            System.out.println("$" + monto);
        }catch (JSONException e){

        }
    }*/


    public void coneccion() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.2:8081/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        pedidos Api = retrofit.create(pedidos.class);
        Call<List<pedido>> call = Api.getPosts();
        call.enqueue(new Callback<List<pedido>>() {
            @Override
            public void onResponse(Call<List<pedido>> call, Response<List<pedido>> response) {
                if(!response.isSuccessful()){
                    System.out.println("Error: " + response.code());
                }else{
                    List<pedido> productosList = response.body();
                    for(pedido proc: productosList){
                        System.out.println(proc.getCod_pedido());
                        int coc = proc.getCod_pedido() + 1;
                        TextView textView = (TextView)findViewById(R.id.textView5);
                        textView.setText("El codigo de su pedido es: \n" + coc + "\n Gracias por su confianza");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<pedido>> call, Throwable t) {

            }
        });
    }

    public void regresarinicio(View view){
        Intent sig = new Intent(this, inicio.class);
        startActivity(sig);
    }
}
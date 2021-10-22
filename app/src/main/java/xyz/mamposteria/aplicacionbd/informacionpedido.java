package xyz.mamposteria.aplicacionbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PostProcessor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.internal.bind.util.ISO8601Utils;

import org.w3c.dom.ls.LSOutput;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.mamposteria.aplicacionbd.interfaces.apiagregarpedido;
import xyz.mamposteria.aplicacionbd.interfaces.apipedido;
import xyz.mamposteria.aplicacionbd.models.pedido;
import xyz.mamposteria.aplicacionbd.models.pedido2;

public class informacionpedido extends AppCompatActivity {

    TextView text1, text2, text3;
    CheckBox c1, c2, c3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacionpedido);
        c1 = findViewById(R.id.checkBox3);
        c2 = findViewById(R.id.checkBox2);
        c3 = findViewById(R.id.checkBox);
        codigopedidoadmin llamar = new codigopedidoadmin();
        findinicio(llamar.codigo);
    }

     public void actualizar(View view){
        codigopedidoadmin llamar = new codigopedidoadmin();
        find(llamar.codigo);
         Toast.makeText(informacionpedido.this, "actualizado", Toast.LENGTH_SHORT);
         Intent sig = new Intent(this, codigopedidoadmin.class);
         startActivity(sig);
    }


    public void cambiar(int id, String nombre, String direccion, String telefono, String nit, int monto, int estado, String producto, int cod_producto){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.2:8081/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        apiagregarpedido Api = retrofit.create(apiagregarpedido.class);
        pedido2 Pedido = new pedido2(id,nombre, direccion, telefono, nit, monto, estado, producto, cod_producto);
        Call<pedido2> call = Api.find(id, Pedido);
        call.enqueue(new Callback<pedido2>() {
            @Override
            public void onResponse(Call<pedido2> call, Response<pedido2> response) {
                System.out.println("pota");
            }

            @Override
            public void onFailure(Call<pedido2> call, Throwable t) {

            }
        });
    }

    private void find(int id){
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
                        estadoactual(p.getEstado(), p.getMonto());
                        System.err.println(p.getMonto());
                        codigopedidoadmin llamar = new codigopedidoadmin();
                        cambiar(llamar.codigo, p.getNombre(), p.getDireccion(), p.getTelefono(), p.getNit(), p.getMonto(), switchestado(), p.getProducto(), p.getCod_producto());
                        findinicio(llamar.codigo);
                        c1.setEnabled(true);
                        c2.setEnabled(true);
                        c3.setEnabled(true);

                    }
                }catch (Exception ex){
                    Toast.makeText(informacionpedido.this, ex.getMessage(), Toast.LENGTH_SHORT);
                }
            }
            @Override
            public void onFailure(Call<pedido> call, Throwable t) {
                Toast.makeText(informacionpedido.this, "Error de coneccion", Toast.LENGTH_SHORT);
            }
        });
    }





    public static int estadoactualizado = 1;

    public void estadoactual (int actual, int monto){
        String estd = "";
        if (actual == 1){
            estd = "EN TIENDA";
            text1 = (TextView)findViewById(R.id.textView6) ;
            text1.setText("X");
            text2 = (TextView)findViewById(R.id.textView7) ;
            text2.setText("");
            text3 = (TextView)findViewById(R.id.textView8) ;
            text3.setText("");
        }else if(actual == 2){
            estd = "EN CAMINO";
            text1 = (TextView)findViewById(R.id.textView6) ;
            text1.setText("");
            text2 = (TextView)findViewById(R.id.textView7) ;
            text2.setText("X");
            text3 = (TextView)findViewById(R.id.textView8) ;
            text3.setText("");
        }else if(actual == 3){
            estd = "ENTREGADO";
            text1 = (TextView)findViewById(R.id.textView6) ;
            text1.setText("");
            text2 = (TextView)findViewById(R.id.textView7) ;
            text2.setText("");
            text3 = (TextView)findViewById(R.id.textView8) ;
            text3.setText("X");
        }
        TextView textView = (TextView)findViewById(R.id.textView5);
        textView.setText("El ESTADO ACTUAL DEL PEDIDO ES: " + estd + "\n El MONTO ES: Q" + monto );
    }

    public void regresarcodigopedidoadmin(View view){
        Intent sig = new Intent(this, codigopedidoadmin.class);
        startActivity(sig);
    }

    public void bloquear(View view){
        if(c1.isChecked() == true){
            c2.setEnabled(false);
            c3.setEnabled(false);
        }else if(c2.isChecked() == true){
            c1.setEnabled(false);
            c3.setEnabled(false);
        }else if(c3.isChecked() == true){
            c1.setEnabled(false);
            c2.setEnabled(false);
        }else{
            c1.setEnabled(true);
            c2.setEnabled(true);
            c3.setEnabled(true);
        }
    }

    public int switchestado(){
            if(c1.isChecked() == true){
                return 1;
            }else if(c2.isChecked() == true){
                return 2;
            }else if(c3.isChecked() == true){
                return 3;
            }else{
                return 1;
            }
    }

    private void findinicio(int id){
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
                        estadoactual(p.getEstado(), p.getMonto());
                        System.err.println(p.getMonto());
                    }
                }catch (Exception ex){
                    Toast.makeText(informacionpedido.this, ex.getMessage(), Toast.LENGTH_SHORT);
                }
            }
            @Override
            public void onFailure(Call<pedido> call, Throwable t) {
                Toast.makeText(informacionpedido.this, "Error de coneccion", Toast.LENGTH_SHORT);
            }
        });
    }
}
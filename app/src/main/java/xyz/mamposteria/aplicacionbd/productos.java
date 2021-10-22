package xyz.mamposteria.aplicacionbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import xyz.mamposteria.aplicacionbd.interfaces.apipedido;
import xyz.mamposteria.aplicacionbd.interfaces.apiproducto;
import xyz.mamposteria.aplicacionbd.models.pedido;
import xyz.mamposteria.aplicacionbd.models.productos1;

public class productos extends AppCompatActivity {

    String[] categorias = {"Aceite", "Luces", "Frenos", "Ruedas"};
    Spinner j_spinner;
    ImageView imagen0;
    ImageView imagen1;
    ImageView imagen2;
    ImageView imagen3;
    ImageView imagen4;
    ImageView imagen5;
    TextView texto1;
    TextView texto2;
    TextView texto3;
    TextView texto4;
    TextView texto5;

    public static int producto = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        j_spinner = (Spinner)findViewById(R.id.spinner3);
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorias);
        j_spinner.setAdapter(adapter);
        imagen0=findViewById(R.id.imageView3);
        imagen1=findViewById(R.id.imgProducto);
        imagen2=findViewById(R.id.imgProducto2);
        imagen3=findViewById(R.id.imgProducto3);
        imagen4=findViewById(R.id.imgProducto5);
        imagen5=findViewById(R.id.imgProducto6);
        texto1=findViewById(R.id.textView);
        texto2=findViewById(R.id.textView2);
        texto3=findViewById(R.id.textView3);
        texto4=findViewById(R.id.textView5);
        texto5=findViewById(R.id.textView6);
        j_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                String seleccion = j_spinner.getSelectedItem().toString();
                if(seleccion.equals("Aceite")){
                    coneccion("Aceite");
                }else if(seleccion.equals("Luces")){
                    coneccion("Luces");
                }else if(seleccion.equals("Frenos")){
                    coneccion("Freno");
                }else if(seleccion.equals("Ruedas")){
                    coneccion("Rueda");
                }else{
                    coneccion("Rueda");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }

        });
    }

    public static int contadorproductos = 0;
    public static String micarrito[][] = new String[3][5];

    int contador = 0;
    public static String matriz[][] = new String[3][5];

    public void coneccion(String tipo){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.2:8081/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        apiproducto Api = retrofit.create(apiproducto.class);
        Call<List<productos1>> call = Api.getPosts();
        call.enqueue(new Callback<List<productos1>>() {
            @Override
            public void onResponse(Call<List<productos1>> call, Response<List<productos1>> response) {
                if(!response.isSuccessful()){
                    System.out.println("Error: " + response.code());
                }else{
                    List<productos1> productosList = response.body();
                    for(productos1 proc: productosList){
                        System.out.println(proc.getNombre());
                        if(tipo.equals(proc.getNombre())){
                            matriz[0][contador] = proc.getCod_producto() + "";
                            matriz[1][contador] = proc.getNombre();
                            matriz[2][contador] = proc.getPrecio() + "";
                            String URLimg1 = "http://192.168.1.2:8081/img/"+proc.getCod_producto()+".png";
                            if(contador == 0){
                                Glide.with(getApplication()).load(URLimg1).into(imagen1);
                                texto1.setText(proc.getNombre() + " Marca: " + proc.getMarca() + " Cantidad: " + proc.getMedida() + " Precio: " + proc.getPrecio());
                            }else  if(contador == 1){
                                Glide.with(getApplication()).load(URLimg1).into(imagen2);
                                texto2.setText(proc.getNombre() + " Marca: " + proc.getMarca() + " Cantidad: " + proc.getMedida() + " Precio: "  + proc.getPrecio());
                            }else  if(contador == 2){
                                Glide.with(getApplication()).load(URLimg1).into(imagen3);
                                texto3.setText(proc.getNombre() + " Marca: " + proc.getMarca() + " Cantidad: " + proc.getMedida() + " Precio: "  + proc.getPrecio());
                            }else  if(contador == 3){
                                Glide.with(getApplication()).load(URLimg1).into(imagen4);
                                texto4.setText(proc.getNombre() + " Marca: " + proc.getMarca() + " Cantidad: " + proc.getMedida() + " Precio: " + proc.getPrecio());
                            }else  if(contador == 4){
                                Glide.with(getApplication()).load(URLimg1).into(imagen5);
                                texto5.setText(proc.getNombre() + " Marca: " + proc.getMarca() + " Cantidad: " + proc.getMedida() + " Precio: " + proc.getPrecio());
                            }
                            contador ++;
                        }
                    }
                    contador = 0;
                }
            }

            @Override
            public void onFailure(Call<List<productos1>> call, Throwable t) {
                System.out.println("Error2: " + t.getMessage());
            }
        });
    }


    //boton

    public void irdatospedido(View view){
        Intent sig = new Intent(this, datospedido.class);
        startActivity(sig);
        contadorproductos = 0;
    }

    public void regresarinicio(View view){
        Intent sig = new Intent(this, inicio.class);
        startActivity(sig);
    }

    public void actualizarimg(){
        if(contadorproductos == 0){
            imagen0.setImageResource(R.mipmap.carrito1);
        }else if(contadorproductos == 1){
            imagen0.setImageResource(R.mipmap.carrito2);
        }else if(contadorproductos == 2){
            imagen0.setImageResource(R.mipmap.carrito3);
        }else if(contadorproductos == 3){
            imagen0.setImageResource(R.mipmap.carrito4);
        }else if(contadorproductos == 4){
            imagen0.setImageResource(R.mipmap.carrito5);
        }
        contadorproductos ++;
    }

    public static int contadorcarrito = 0;

    public void agregarproducto(View view){
        quitar1();
    }

    public void agregarproducto2(View view){
        quitar2();
    }

    public void agregarproducto3(View view){
        quitar3();
    }

    public void agregarproducto4(View view){
        quitar4();
    }

    public void agregarproducto5(View view) { quitar5(); }

    public void todo(){
        actualizarimg();
        micarrito[0][0] = "0";
        micarrito[1][0] = "0";
        micarrito[2][0] = "0";
        micarrito[0][1] = "0";
        micarrito[1][1] = "0";
        micarrito[2][1] = "0";
        micarrito[0][2] = "0";
        micarrito[1][2] = "0";
        micarrito[2][2] = "0";
        micarrito[0][3] = "0";
        micarrito[1][3] = "0";
        micarrito[2][3] = "0";
        micarrito[0][4] = "0";
        micarrito[1][4] = "0";
        micarrito[2][4] = "0";
        imprimir();
    }

    public void quitar1(){
        actualizarimg();
        micarrito[0][0] = matriz[0][0];
        micarrito[1][0] = matriz[1][0];
        micarrito[2][0] = matriz[2][0];
        micarrito[0][1] = "0";
        micarrito[1][1] = "0";
        micarrito[2][1] = "0";
        micarrito[0][2] = "0";
        micarrito[1][2] = "0";
        micarrito[2][2] = "0";
        micarrito[0][3] = "0";
        micarrito[1][3] = "0";
        micarrito[2][3] = "0";
        micarrito[0][4] = "0";
        micarrito[1][4] = "0";
        micarrito[2][4] = "0";
        imprimir();
    }

    public void quitar2(){
        actualizarimg();
        micarrito[0][1] = matriz[0][1];
        micarrito[1][1] = matriz[1][1];
        micarrito[2][1] = matriz[2][1];
        micarrito[0][2] = "0";
        micarrito[1][2] = "0";
        micarrito[2][2] = "0";
        micarrito[0][3] = "0";
        micarrito[1][3] = "0";
        micarrito[2][3] = "0";
        micarrito[0][4] = "0";
        micarrito[1][4] = "0";
        micarrito[2][4] = "0";
        imprimir();
    }

    public void quitar3(){
        actualizarimg();
        micarrito[0][2] = matriz[0][2];
        micarrito[1][2] = matriz[1][2];
        micarrito[2][2] = matriz[2][2];
        micarrito[0][3] = "0";
        micarrito[1][3] = "0";
        micarrito[2][3] = "0";
        micarrito[0][4] = "0";
        micarrito[1][4] = "0";
        micarrito[2][4] = "0";
        imprimir();
    }

    public void quitar4(){
        actualizarimg();
        micarrito[0][3] = matriz[0][3];
        micarrito[1][3] = matriz[1][3];
        micarrito[2][3] = matriz[2][3];
        micarrito[0][4] = "0";
        micarrito[1][4] = "0";
        micarrito[2][4] = "0";
        imprimir();
    }


    public void quitar5(){
        actualizarimg();
        micarrito[0][4] = matriz[0][4];
        micarrito[1][4] = matriz[1][4];
        micarrito[2][4] = matriz[2][4];
        imprimir();
    }

    public void imprimir(){
        for(int i=0; i<5; i++){
            for(int j=0; j<3; j++){
                System.out.println("esta: "+ micarrito[j][i]);
            }
        }
    }
}
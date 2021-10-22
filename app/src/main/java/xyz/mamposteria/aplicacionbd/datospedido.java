package xyz.mamposteria.aplicacionbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.mamposteria.aplicacionbd.interfaces.apiproducto;
import xyz.mamposteria.aplicacionbd.models.productos1;

public class datospedido extends AppCompatActivity {
    TextView informacion;

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
    Button boton1;
    Button boton2;
    Button boton3;
    Button boton4;
    Button boton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datospedido);
       // informacion.findViewById(R.id.infopedido);
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
        boton1=findViewById(R.id.button11);
        boton2=findViewById(R.id.button12);
        boton3=findViewById(R.id.button13);
        boton4=findViewById(R.id.button14);
        boton5=findViewById(R.id.button16);
        productos llamar = new productos();
        acutales = llamar.micarrito;
        coneccion();
    }

    public static String acutales[][];
    public static int precio = 0;
    public static int prodcto = 0;
    int contador = 0;

    public void coneccion(){
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
                        System.out.println(acutales[0][0]+acutales[0][1]+acutales[0][2]+acutales[0][3]+acutales[0][4]);
                        if(Integer.parseInt(acutales[0][1]) == proc.getCod_producto() || Integer.parseInt(acutales[0][2]) == proc.getCod_producto()
                        || Integer.parseInt(acutales[0][3]) == proc.getCod_producto() || Integer.parseInt(acutales[0][4]) == proc.getCod_producto()
                        || Integer.parseInt(acutales[0][0]) == proc.getCod_producto()){
                            String URLimg1 = "http://192.168.1.2:8081/img/"+proc.getCod_producto()+".png";
                            if(contador == 0){
                                imagen1.setVisibility(View.VISIBLE);
                                Glide.with(getApplication()).load(URLimg1).into(imagen1);
                                texto1.setText(proc.getNombre() + " Marca: " + proc.getMarca() + " Cantidad: " + proc.getMedida() + " Precio: " + proc.getPrecio());
                                boton1.setVisibility(View.VISIBLE);
                                precio += proc.getPrecio();
                                prodcto = proc.getCod_producto();
                            }else  if(contador == 1){
                                imagen2.setVisibility(View.VISIBLE);
                                Glide.with(getApplication()).load(URLimg1).into(imagen2);
                                texto2.setText(proc.getNombre() + " Marca: " + proc.getMarca() + " Cantidad: " + proc.getMedida() + " Precio: "  + proc.getPrecio());
                                boton2.setVisibility(View.VISIBLE);
                                precio += proc.getPrecio();
                            }else  if(contador == 2){
                                imagen3.setVisibility(View.VISIBLE);
                                Glide.with(getApplication()).load(URLimg1).into(imagen3);
                                texto3.setText(proc.getNombre() + " Marca: " + proc.getMarca() + " Cantidad: " + proc.getMedida() + " Precio: "  + proc.getPrecio());
                                boton3.setVisibility(View.VISIBLE);
                                precio += proc.getPrecio();
                            }else  if(contador == 3){
                                imagen4.setVisibility(View.VISIBLE);
                                Glide.with(getApplication()).load(URLimg1).into(imagen4);
                                texto4.setText(proc.getNombre() + " Marca: " + proc.getMarca() + " Cantidad: " + proc.getMedida() + " Precio: " + proc.getPrecio());
                                boton4.setVisibility(View.VISIBLE);
                                precio += proc.getPrecio();
                            }else  if(contador == 4){
                                imagen5.setVisibility(View.VISIBLE);
                                Glide.with(getApplication()).load(URLimg1).into(imagen5);
                                texto5.setText(proc.getNombre() + " Marca: " + proc.getMarca() + " Cantidad: " + proc.getMedida() + " Precio: " + proc.getPrecio());
                                boton5.setVisibility(View.VISIBLE);
                                precio += proc.getPrecio();
                            }
                            contador ++;
                        }
                        System.out.println("casH" + precio);
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
    public void regresarproductos(View view){
        Intent sig = new Intent(this, productos.class);
        startActivity(sig);
        precio = 0;
        System.out.println(precio);
    }

    public void irconfirmarpedido(View view){
        Intent sig = new Intent(this, confirmarpedido.class);
        startActivity(sig);
    }

    public void quitar1(View view){
        acutales[0][0] = "0";
        acutales[1][0] = "0";
        acutales[2][0] = "0";
        imagen1.setVisibility(View.INVISIBLE);
        texto1.setText("");
        boton1.setVisibility(View.INVISIBLE);
        coneccion();
    }

    public void quitar2(View view){
        acutales[0][1] = "0";
        acutales[1][1] = "0";
        acutales[2][1] = "0";
        imagen2.setVisibility(View.INVISIBLE);
        texto2.setText("");
        boton2.setVisibility(View.INVISIBLE);
        coneccion();
    }

    public void quitar3(View view){
        acutales[0][2] = "0";
        acutales[1][2] = "0";
        acutales[2][2] = "0";
        imagen3.setVisibility(View.INVISIBLE);
        texto3.setText("");
        boton3.setVisibility(View.INVISIBLE);
        coneccion();
    }

    public void quitar4(View view){
        acutales[0][3] = "0";
        acutales[1][3] = "0";
        acutales[2][3] = "0";
        imagen4.setVisibility(View.INVISIBLE);
        texto4.setText("");
        boton4.setVisibility(View.INVISIBLE);
        coneccion();
    }

    public void quitar5(View view){
        acutales[0][4] = "0";
        acutales[1][4] = "0";
        acutales[2][4] = "0";
        imagen5.setVisibility(View.INVISIBLE);
        texto5.setText("");
        boton5.setVisibility(View.INVISIBLE);
        coneccion();
    }
}
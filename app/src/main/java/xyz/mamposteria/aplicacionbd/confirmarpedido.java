package xyz.mamposteria.aplicacionbd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.mamposteria.aplicacionbd.interfaces.apinuevopedido;
import xyz.mamposteria.aplicacionbd.models.pedido;

public class confirmarpedido extends AppCompatActivity {

    private EditText nombre, direccion, telefono, nit;
    private Button postDataBtn;

    @Override
    protected void onDestroy(){
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmarpedido);
        // inicializamos variables
        nombre = findViewById(R.id.txtnombre);
        direccion = findViewById(R.id.txt_direccion);
        telefono = findViewById(R.id.txt_telefono);
        nit = findViewById(R.id.txt_nit);
        postDataBtn = findViewById(R.id.btn_confirmar);

        //iniciar paypal
        /*Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configt);
        startService(intent);*/

        postDataBtn.setOnClickListener(new View.OnClickListener() {
        datospedido llamar = new datospedido();
        productos instans = new productos();
            @Override
            public void onClick(View v) {
                postData(nombre.getText().toString(), direccion.getText().toString(), telefono.getText().toString(), nit.getText().toString(), llamar.precio, llamar.prodcto + "", llamar.prodcto);
                regresar();
            }
        });
    }

    private void postData(String nombre, String direccion, String telefono, String nit, int monto, String producto, int cod_producto){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.2:8081/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        apinuevopedido retrofitAPI = retrofit.create(apinuevopedido.class);
        pedido Pedido = new pedido(nombre, direccion, telefono, nit, monto, 1, producto,cod_producto);
        Call<pedido> call = retrofitAPI.find(Pedido);
        call.enqueue(new Callback<pedido>() {
            @Override
            public void onResponse(Call<pedido> call, Response<pedido> response) {
                System.out.println("todo bien");
            }
            @Override
            public void onFailure(Call<pedido> call, Throwable t) {
                System.out.println("no todo bien");
            }
        });
    }


    //PAYPAL

    private static final int PAYPAL_REQUEST_CODE = 7171;
    private static PayPalConfiguration configt = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
    .clientId(coneccion.PAYPAL_CLIENT_ID);
    String monto;

    public void paypalpagar(){
        datospedido llamar = new datospedido();

        //recibir el monto
        monto = llamar.precio + "";

        PayPalPayment payPalPayemnt = new PayPalPayment(new BigDecimal(String.valueOf(monto)), "MXN", "Pagado por Isai", PayPalPayment.PAYMENT_INTENT_SALE);

        //Enviar parametros

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configt);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayemnt);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == PAYPAL_REQUEST_CODE){
            if(requestCode == RESULT_OK){
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                if(confirmation != null){
                    try{
                        String paymentDetails = confirmation.toJSONObject().toString(4);
                        startActivity(new Intent(this, Entregar_estado.class).putExtra("PaymentDetails", paymentDetails)
                        .putExtra("PaymentAmount", monto));

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }else if(resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(this, "Cancelada", Toast.LENGTH_LONG).show();
            }
        }else if(resultCode == PaymentActivity.RESULT_EXTRAS_INVALID){
            Toast.makeText(this, "Invalida", Toast.LENGTH_LONG).show();
        }

        System.out.println("adios");
        super.onActivityResult(requestCode, resultCode, data);
    }

    //botones

    public void regresar(){
        Intent sig = new Intent(this, Entregar_estado.class);
        startActivity(sig);
    }
    public void regresardatospedido(View view){
        Intent sig = new Intent(this, datospedido.class);
        startActivity(sig);
    }
}
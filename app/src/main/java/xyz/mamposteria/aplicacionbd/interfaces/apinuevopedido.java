package xyz.mamposteria.aplicacionbd.interfaces;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import xyz.mamposteria.aplicacionbd.models.pedido;

public interface apinuevopedido {
    @POST("api/pedido/")
    Call<pedido> find(@Body pedido pedido);
}

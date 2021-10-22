package xyz.mamposteria.aplicacionbd.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import xyz.mamposteria.aplicacionbd.models.pedido;

public interface apipedido {
    @GET("api/pedido/{id}")
    public Call<pedido> find(@Path("id") int id);
}

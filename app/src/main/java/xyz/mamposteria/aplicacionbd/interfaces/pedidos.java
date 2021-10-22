package xyz.mamposteria.aplicacionbd.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import xyz.mamposteria.aplicacionbd.models.pedido;
import xyz.mamposteria.aplicacionbd.models.productos1;

public interface pedidos {
    @GET("api/pedido/")
    public Call<List<pedido>> getPosts();
}

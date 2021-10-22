package xyz.mamposteria.aplicacionbd.interfaces;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import xyz.mamposteria.aplicacionbd.models.pedido;
import xyz.mamposteria.aplicacionbd.models.pedido2;
import xyz.mamposteria.aplicacionbd.models.usuario;

public interface apiagregarpedido {
    @PUT("api/pedido/{id}")
    Call<pedido2> find(@Path("id") int id, @Body pedido2 pedido2);
}

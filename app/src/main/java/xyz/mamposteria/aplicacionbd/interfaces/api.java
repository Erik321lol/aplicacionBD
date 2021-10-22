package xyz.mamposteria.aplicacionbd.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import xyz.mamposteria.aplicacionbd.models.usuario;

public interface api {
    @GET("api/usuario/{user}")
    public Call<usuario> find(@Path("user") String user);
}

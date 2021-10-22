package xyz.mamposteria.aplicacionbd.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import xyz.mamposteria.aplicacionbd.models.productos1;
import xyz.mamposteria.aplicacionbd.models.usuario;
import xyz.mamposteria.aplicacionbd.productos;

public interface apiproducto {
    @GET("api/producto/")
    public Call<List<productos1>> getPosts();

}

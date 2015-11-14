package com.devazt.carrito;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Nekszer on 14/11/2015.
 */
public interface IProduct {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("/v1/productos")
    Call<List<Producto>> getProductos();
}

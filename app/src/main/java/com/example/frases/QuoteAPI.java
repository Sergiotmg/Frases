package com.example.frases;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
//cada vez que llamemos al
// todas las peticiones a internet en esta clase


public interface QuoteAPI {
    // un get por cada consulta que quieras llamar a la api
    @GET("posts?filter[orderby]=rand&filter[posts_per_page]=10")//entre comillas la base de la URL
    Call<List<Quote>> loadQuotes();//nos devovlera una lisa de quipotes
    //al load quotes le pasariamos un valor paradecirle cuantos cargar , seria dinamica , que
    //el nº lo elija el user
    // loadquotes para 1 solo seria loadSingleQuotes() p ej
}

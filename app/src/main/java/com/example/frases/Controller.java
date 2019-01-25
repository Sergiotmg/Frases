package com.example.frases;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<List<Quote>> { //implemnenta llamada que se hara
    //callback al encargada de espear estas respuesta ,nos fuerza poner el onResponse y el onFailure
    // en list quote
    ResponseListener listener;

    public Controller(ResponseListener listener) {
        this.listener = listener;
    }



    static final String BASE_URL = "https://quotesondesign.com/wp-json/";

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        //
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //abrimos la api
        // que la cree con a libreria retrofit
        QuoteAPI api = retrofit.create(QuoteAPI.class);

        Call<List<Quote>> call = api.loadQuotes();
        call.enqueue(this);//cuando llegue esa la mostrara, se queda esperando
    }

    // TIENEN QUE ESTAR POR NARICES SI TENEMOS UN  Callback<List<.....>>
    @Override
    //nos da una respuesta qwue puede no ser correcta

    public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
        if(response.isSuccessful()) {
            //lo convert alista de cuotas
            List<Quote> quoteList = response.body();
            if (quoteList.size()!=0){
                Log.d("Controller",quoteList.get(0).getTitle());
                listener.onResponse(quoteList);//eso en el main acitivy cuando ya tengamos el metodo on
                // response ya podremos hacer lo que queramos socn esa lista
            }

        } else {
            Log.d("Controller",response.errorBody().toString());
            //para poderlo pasarlo al mainactivity y que el user lo pueda ver
            listener.onResponseFailed(response.errorBody().toString());
        }
    }
    //nos da un error
    @Override
    public void onFailure(Call<List<Quote>> call, Throwable t) {
        Log.d("Controller",t.getMessage());
        listener.onFailure(t.getMessage());//lo pasare en un toast
    }
    //esa interface , cualquier clase que implemente onRsponse listener va a tner el metodo onresponse
    //recibira un alista de frases , cuando reciba los cambios le diremos onresponse para que se el
    // manacitivty el que reciba la clase
    //implementarmeos esa interfaz en el main acitivyt
    //no debemos pasasr un main activity entero al otro lado
    //no dejamos que desde aqui tenagamos acceso a tot el main activity
    //si solo necesitamos que pueda llamar a ese metodo entonces llamamosa ese metodo

    //lo generamos para que luego en el main activity podamos mostrar cuando ha ido bie no mal
    interface  ResponseListener{// podria estar en otro archivo
        void onResponse(List<Quote> list);
        void onResponseFailed(String response);//solo devuelve el error
        void onFailure(String message);

    }
}

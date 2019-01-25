package com.example.frases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity implements Controller.ResponseListener {
    //responselistener porque es el escuchador que espera cambios, podria llamars ede otra forma
    //TextView txtHello;//quitarlo!!!!!!
    QuoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Controller controller = new Controller(this);//necesita el response listener
        //this porque hemos hecho que toda esa clase implemente esa interfaz
        controller.start();//lanzamos  svicoi retrofit, haciendo peticion url y esperando restdo
        //to do eso en 2º plano sin tener que estar esprando

        //txtHello =findViewById(R.id.txtHello);


        // LO IMPORTANTE!!
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter= new QuoteAdapter(this, null);
        //si getitemcount es = 0 no hace nada
        recyclerView.setAdapter(adapter);
        //se tiene que poner para que se vea si no se ponen se vera en blanco !!!
        // grid o stagredLayoutManager segun haga falta
        LinearLayoutManager manager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onResponse(List<Quote> list) {
        // ahora lo mostraremos en el textview
        //cuando llamemos a ese metodo le pasaremos el adaptador del recyclerview
        //le pasaraismos toda la lista al adapter del recycler view
        //en vez de la lista de Recetas esa lista se la pasamos al adaptador y nos la
        // muestra el adapter view
        //txtHello.setText(list.get(0).getContent());
        // si tot va bien , le decimos al dapatador que cambie los datos
        adapter.setData(list);
    }

    @Override
    public void onResponseFailed(String response) {
        Toast.makeText(this,response,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}

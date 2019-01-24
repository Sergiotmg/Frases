package com.example.frases;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>  {//dentro imple,ento el QuoteAdapter.QuoteViewHolder
    //To hold your data in the adapter:

    private  List<Quote> mQuoteList;//los items que iremos mostrando
    private LayoutInflater mInflater;//para crear distintos layout
    private Context context; // nos hara context.getresources para coger los colores de fondo
    // SI QUEREMOS USAR PICASSO O GLIDE NOS PIDE EL CONTEXTO POR SI ACASO , lo pasamos por el const

        //CONSTRUCTOR
        public QuoteAdapter(Context context,List<Quote> quoteList) {//context no se deberia

            mInflater = LayoutInflater.from(context);// si hay 20 creara 20
            this.mQuoteList = quoteList;
            this.context=context;
        }


    //una clase dentro de otra clase+

    class QuoteViewHolder extends RecyclerView.ViewHolder  {
            //el enargado de generar las vistas para cad  uno de los items

        public final TextView txtTitle;
        public final TextView txtContent;
        //public final LinearLayout linearLayout; no hace falta
        //final QuoteAdapter mAdapter; NO SE USA

        // constructor para el wordViewHolder
        public QuoteViewHolder(View itemView, QuoteAdapter adapter) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtContent=itemView.findViewById(R.id.txtContent);
            //this.mAdapter=adapter;
        }
    }

    @NonNull
    @Override
    //si tenemos 20 obj tenemos 20 view holder
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //iria el layout generado para 1 item
            View mItemView=mInflater
                    .inflate(R.layout.quote_item,viewGroup,false);

        return new QuoteViewHolder(mItemView,this);
    }

    @Override
    //el que rellena los textos
    //siempre recibimos la spocision
    public void onBindViewHolder(@NonNull QuoteViewHolder quoteViewHolder, int position) {
        Quote quote=mQuoteList.get(position);//cogemos el objeto
        //les cambiamos lo que sea
        quoteViewHolder.txtTitle.setText(quote.getTitle());
        quoteViewHolder.txtContent.setText(quote.getContent());
        // QUITARLE LAS ETIQUETAS
        Spanned spanned= Html.fromHtml(quote.getContent());
        quoteViewHolder.txtContent.setText(spanned);
    }

    @Override
    public int getItemCount() {
        if(mQuoteList==null)
            return 0;

        return mQuoteList.size();
    }
    // para cuando desde el serviro noes envian una lista nueva que diga oye es la lista
    // nueva actualizamoe los datos

    public  void setData (List<Quote> newList){
            mQuoteList=newList;
            //para avisar de que hemos cmabiado nuestros datos
            notifyDataSetChanged();
    }



}

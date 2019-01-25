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

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>  {//dentro
    // implemento el QuoteAdapter.QuoteViewHolder

    private List<Quote> mQuoteList;//los items que iremos mostrando
    private LayoutInflater mInflater;//para inflar cada uno delos layout de cada item
    //private Context context; // nos hara context.getresources para coger los colores de fondo
    // SI QUEREMOS USAR PICASSO O GLIDE NOS PIDE EL CONTEXTO POR SI ACASO , lo pasamos por el const

        //CONSTRUCTOR
        public QuoteAdapter(Context context,List<Quote> quoteList) {//context no se deberia
            mInflater = LayoutInflater.from(context);// si hay 20 creara 20
            this.mQuoteList = quoteList;
            //this.context=context;// no necesaria , podemos eliminar arriva private Context context
        }


    //una clase dentro de otra clase+

    class QuoteViewHolder extends RecyclerView.ViewHolder  { // NUNCA CAMBIA ESTA FRASE
            //el enargado de generar las vistas para cad  uno de los items

        public final TextView txtTitle;
        public final TextView txtContent;
        //public final LinearLayout linearLayout; no hace falta
        //final QuoteAdapter mAdapter; NO SE USA

        // CONSTRUCTOR// no tiene el metodo findviewBy ide, no lo tiene el adaptar
        // //solo todas las vistas tiene el findByIdpara el wordViewHolder
        //en viewholer se pone siempre itemView.findViewById!!!!!!!!!!!OJO!!
        public QuoteViewHolder(View itemView) {
            //public QuoteViewHolder(View itemView, QuoteAdapter adapter) {
            //adapter solo se utilizaria para el itemNotifyChanged mas abajo pero ahora no needed
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtContent=itemView.findViewById(R.id.txtContent);
            //this.mAdapter=adapter;
        }
    }

    @NonNull
    @Override
    // LO PODEMOS IMPLEMENTAR CON cTRL +O
    //si tenemos 20 obj tenemos 20 view holder
    //el que infla la vista
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //iria el layout generado para 1 item
            View mItemView=mInflater
                    // viewGroup ¿para añadirlo al recycler?no se qwuede anclado a esa lista ( false)
                    .inflate(R.layout.quote_item,viewGroup,false);
            return new QuoteViewHolder(mItemView);// cada uno para cada item
            //return new QuoteViewHolder(mItemView,this);--> solo cuando arriba   :
            // public QuoteViewHolder(View itemView, QuoteAdapter adapter) {
    }

    @Override
    //el que rellena los textos
    //siempre recibimos la spocision
    //par aunier las listas con el codigo
    public void onBindViewHolder(@NonNull QuoteViewHolder quoteViewHolder, int position) {
        Quote current=mQuoteList.get(position);//cogemos el objeto
        //les cambiamos lo que sea
        quoteViewHolder.txtTitle.setText(current.getTitle());
        quoteViewHolder.txtContent.setText(current.getContent());
        // QUITARLE LAS ETIQUETAS <b> que salian en el texto y no se veia negrita
        Spanned spanned= Html.fromHtml(current.getContent());
        quoteViewHolder.txtContent.setText(spanned);
    }

    @Override

    public int getItemCount() {
            // PARA EVITAR EL NULL POINTER EXCEP
        if(mQuoteList==null) return 0;
        return mQuoteList.size();
    }
    // para cuando desde el serviro noes envian una lista nueva que diga oye es la lista
    // nueva actualizamoe los datos
    // le pasamos la lista nueva
    public  void setData (List<Quote> newList){
            mQuoteList=newList;//la de aqui es la que le acabamos de pasar
            //para avisar de que hemos cmabiado nuestros datos
            // notificar que nuestro set de datos ha cambiado
            notifyDataSetChanged();
    }



}

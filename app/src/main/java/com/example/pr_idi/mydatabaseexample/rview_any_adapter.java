package com.example.pr_idi.mydatabaseexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rvila21 on 29/12/2016.
 */

public class rview_any_adapter extends RecyclerView.Adapter<rview_any_adapter.rviewHolder_any> {
    private List<Film> films;
    private Context ctx;




    public rview_any_adapter(List<Film> films, Context ctx){
        this.films = films;
        this.ctx = ctx;
    }

    @Override
    public rviewHolder_any onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rview_item_layout,parent,false);
        rviewHolder_any rviewHolder_any = new rviewHolder_any(view, films,ctx);
        return rviewHolder_any;
    }

    @Override
    public void onBindViewHolder(rviewHolder_any holder, int position) {
        Film film = films.get(position);
        holder.titol.setText(film.getTitle());
        holder.director.setText(film.getDirector());
        holder.pais.setText(film.getCountry());
        holder.any.setText(Integer.toString(film.getYear()));
        holder.protagonista.setText(film.getProtagonist());
        holder.valoracio.setText(Integer.toString(film.getCritics_rate()));
    }

    @Override
    public int getItemCount() {
        if(films == null)return 0;
        return films.size();
    }

    public static class rviewHolder_any extends RecyclerView.ViewHolder implements View.OnClickListener{
        List<Film> films = new ArrayList<Film>();
        TextView titol;
        TextView director;
        TextView pais;
        TextView any;
        TextView protagonista;
        TextView valoracio;
        Context ctx;
        interface2 myinterface2;
        public interface interface2{
            public void thisitem(int Position);
        }
        public rviewHolder_any(View view, List<Film> films, Context ctx){
            super(view);
            view.setOnClickListener(this);
            this.films = films;
            this.ctx = ctx;
            myinterface2 = (interface2) ctx;
            titol = (TextView) view.findViewById(R.id.titol);
            director = (TextView) view.findViewById(R.id.director);
            pais = (TextView) view.findViewById(R.id.pais);
            any = (TextView) view.findViewById(R.id.any);
            protagonista = (TextView) view.findViewById(R.id.protagonista);
            valoracio = (TextView) view.findViewById(R.id.valoracio);
        }

        @Override
        public void onClick(View v){
            int Position = getAdapterPosition();
            myinterface2.thisitem(Position);
        }
    }
}

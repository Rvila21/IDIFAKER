package com.example.pr_idi.mydatabaseexample;

import android.support.v7.widget.RecyclerView;
import android.text.StaticLayout;
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

    public rview_any_adapter(List<Film> films){
        this.films = films;
    }

    @Override
    public rviewHolder_any onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rview_item_layout,parent,false);
        rviewHolder_any rviewHolder_any = new rviewHolder_any(view);
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
        return films.size();
    }

    public static class rviewHolder_any extends RecyclerView.ViewHolder {
        TextView titol;
        TextView director;
        TextView pais;
        TextView any;
        TextView protagonista;
        TextView valoracio;
        public rviewHolder_any(View view){
            super(view);
            titol = (TextView) view.findViewById(R.id.titol);
            director = (TextView) view.findViewById(R.id.director);
            pais = (TextView) view.findViewById(R.id.pais);
            any = (TextView) view.findViewById(R.id.any);
            protagonista = (TextView) view.findViewById(R.id.protagonista);
            valoracio = (TextView) view.findViewById(R.id.valoracio);
        }
    }
}

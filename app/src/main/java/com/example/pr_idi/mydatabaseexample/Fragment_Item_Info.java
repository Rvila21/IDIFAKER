package com.example.pr_idi.mydatabaseexample;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Item_Info.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Item_Info#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Item_Info extends Fragment {



    private View rootView;
    private Film film;
    private FilmData filmData;

    private OnFragmentInteractionListener mListener;

    public Fragment_Item_Info() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment Fragment_Item_Info.
     */
    //MÈTODE INÙTIL NOMÉS PER PROVES
    public static void newInstance() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //Titol = getArguments().getString(ARG_Titol);
            //Director = getArguments().getString(ARG_Director);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_fragment__specific_info, container, false);
        TextView t = (TextView)rootView.findViewById(R.id.infotitol);
        t.setText(film.getTitle());
        t = (TextView)rootView.findViewById(R.id.infodirector);
        t.setText(film.getDirector());
        t = (TextView)rootView.findViewById(R.id.infopais);
        t.setText(film.getCountry());
        t = (TextView)rootView.findViewById(R.id.infoany);
        t.setText(Integer.toString(film.getYear()));
        t = (TextView)rootView.findViewById(R.id.infoprotagonista);
        t.setText(film.getProtagonist());
        t = (TextView)rootView.findViewById(R.id.infovaloracio);
        t.setText(Integer.toString(film.getCritics_rate()));
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void change_rate(Integer s){
        Film aux = film;
        film.setCritics_rate(s);
        TextView t = (TextView)rootView.findViewById(R.id.infovaloracio);
        t.setText(Integer.toString(film.getCritics_rate()));
        filmData.deleteFilm(aux);
        filmData.createFilm(film.getTitle(),film.getDirector(),film.getCountry(),film.getYear(),film.getProtagonist(),film.getCritics_rate());

    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }





    public void setFilm(Film film) {
        this.film = film;
    }

    public Film getFilm(){
        return this.film;
    }

    public void setValoracio(Film film) {
        this.film = film;
        TextView t = (TextView)rootView.findViewById(R.id.infovaloracio);
        t.setText(Integer.toString(film.getCritics_rate()));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

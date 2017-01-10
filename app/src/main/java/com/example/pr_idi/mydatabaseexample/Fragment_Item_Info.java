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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_Titol = "Titol";
    private static final String ARG_Director = "Director";
    private static final String ARG_Pais = "Pais";
    private static final String ARG_Any = "Any";
    private static final String ARG_Protagonista = "Protagonista";
    private static final String ARG_Valoracio = "Valoracio";

    // TODO: Rename and change types of parameters
    private String Titol;
    private String Director;
    private String Pais;
    private int Any;
    private String Protagonista;
    private int Valoracio;
    private View rootView;
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
    public static Fragment_Item_Info newInstance(String Titol, String Director, String Pais, int Any, String Protagonista, int Valoracio) {
        Fragment_Item_Info fragment = new Fragment_Item_Info();
        Bundle args = new Bundle();
        args.putString(ARG_Titol, Titol);
        args.putString(ARG_Director, Director);
        args.putString(ARG_Pais, Pais);
        args.putInt(ARG_Any, Any);
        args.putString(ARG_Protagonista, Protagonista);
        args.putInt(ARG_Valoracio, Valoracio);
        fragment.setArguments(args);
        return fragment;
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
        t.setText(Titol);
        t = (TextView)rootView.findViewById(R.id.infodirector);
        t.setText(Director);
        t = (TextView)rootView.findViewById(R.id.infopais);
        t.setText(Pais);
        t = (TextView)rootView.findViewById(R.id.infoany);
        t.setText(Integer.toString(Any));
        t = (TextView)rootView.findViewById(R.id.infoprotagonista);
        t.setText(Protagonista);
        t = (TextView)rootView.findViewById(R.id.infovaloracio);
        t.setText(Integer.toString(Valoracio));
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void change_rate(Integer s){
        List<Film> films = filmData.getAllFilms();
        Film choosenone = null;
        Film aux;
        for(Film film : films){
            String name = film.getTitle();
            if(Titol == name){
                choosenone = film;
                break;
            }
        }
        aux = choosenone;
        choosenone.setCritics_rate(s);
        TextView t = (TextView)rootView.findViewById(R.id.infovaloracio);
        t.setText(Integer.toString(choosenone.getCritics_rate()));
        filmData.deleteFilm(aux);
        filmData.createFilm(choosenone.getTitle(),choosenone.getDirector(),choosenone.getCountry(),choosenone.getYear(),choosenone.getProtagonist(),choosenone.getCritics_rate());

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

    public String getTitol() {
        return Titol;
    }

    public void setTitol(String titol) {
        Titol = titol;

    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public int getAny() {
        return Any;
    }

    public void setAny(int any) {
        Any = any;
    }

    public String getProtagonista() {
        return Protagonista;
    }

    public void setProtagonista(String protagonista) {
        Protagonista = protagonista;
    }

    public int getValoracio() {
        return Valoracio;
    }

    public void setValoracio(int valoracio) {
        this.Valoracio = valoracio;
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

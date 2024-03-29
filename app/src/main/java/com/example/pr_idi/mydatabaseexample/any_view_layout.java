package com.example.pr_idi.mydatabaseexample;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link any_view_layout.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link any_view_layout#newInstance} factory method to
 * create an instance of this fragment.
 */
public class any_view_layout extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private FilmData filmData;
    private List<Film> values = null;
    RecyclerView rv_any;
    RecyclerView.Adapter rview_any_adapter;
    RecyclerView.LayoutManager rview_any_LayoutManager;

    private OnFragmentInteractionListener mListener;
    MyInterface myinterface;

    public void setValues(List<Film> values) {
        this.values = new ArrayList<>();
        this.values = values;
        rview_any_adapter = new rview_any_adapter(values,getActivity());
        rview_any_adapter.notifyDataSetChanged();
    }

    public interface MyInterface{
        public void rview_itemSelected(int Position);
    }

    public any_view_layout() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment any_view_layout.
     */
    // TODO: Rename and change types and number of parameters
    public static any_view_layout newInstance(String param1, String param2) {
        any_view_layout fragment = new any_view_layout();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_any_view_layout, container, false);
        //filmData.open();
        rv_any = (RecyclerView)rootView.findViewById(R.id.anyrviewid);
        if(this.values == null) values = filmData.getAllFilmsOrderedbyAny();
        rview_any_adapter = new rview_any_adapter(values,getActivity());
        rv_any.setHasFixedSize(true);
        rview_any_LayoutManager = new LinearLayoutManager(getActivity());
        rv_any.setAdapter(rview_any_adapter);
        rv_any.setLayoutManager(rview_any_LayoutManager);
        rview_any_adapter.notifyDataSetChanged();
        return rootView;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
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
    }
}

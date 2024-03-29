package com.example.pr_idi.mydatabaseexample;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DesplegableActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentCercaCamera.OnFragmentInteractionListener
        , FragmentAbout.OnFragmentInteractionListener, FragmentAfegir.OnFragmentInteractionListener,any_view_layout.OnFragmentInteractionListener,
        rview_any_adapter.rviewHolder_any.interface2, Fragment_Item_Info.OnFragmentInteractionListener,FragmentBuscar.OnFragmentInteractionListener{

    private FilmData filmData;
    private List<Film> rview_values;
    ListView lista = null;
    ListView actorList = null;
    SearchView searchView = null;
    ArrayAdapter<String> adapter2;
    ArrayAdapter<String> adapter3;
    ArrayAdapter<String> adapter4;
    Fragment fragment;
    Fragment_Item_Info fragmentinfo;
    any_view_layout fragmentrview;
    Film filminfo;
    Stack<String> fragmentos = new Stack<>();
    String fragmentoActual = "main";
    boolean recycle = false;

    //pelicules de mostra
    String[] Tx_titols = {"The Arrival", "Hacksaw Ridge", "The Amazing Spiderman", "The Amazing Spiderman 2", "The Godfather", "The Godfather II", "The Godfather III"};
    String[] Tx_paisos = {"USA", "USA", "USA", "USA", "USA", "USA", "USA"};
    String[] Tx_directors = {"Denis Villeneuve", "Mel Gibbson", "Marc Webb", "Marc Webb", "Francis Ford Coppola", "Francis Ford Coppola", "Francis Ford Coppola"};
    int[] int_anys = {2016, 2016, 2012, 2014, 1972, 1974, 1990};
    String[] Tx_protagonista = {"Amy Adams", "Andrew Garfield", "Andrew Garfield", "Andrew Garfield", "Marlon Brando", "Marlon Brando", "Al Pacino"};
    int[] int_val = {8, 7, 6, 5, 9, 8, 7};
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //List<Film> films = new List<Film>();
    public void setfilm(Film filminfo){
        this.filminfo = filminfo;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desplegable);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        lista = (ListView)findViewById(R.id.listView_Lista);
        if(R.id.anyrviewid != 0)cleanDB();
        fragmentos.add("main");
        showFilms();
        rview_values = filmData.getAllFilmsOrderedbyAny();

    }

    private void clickActor(){


    }

    private void cleanDB(){
        //Db stuff
        filmData = new FilmData(this);
        filmData.open();

        //setContentView(R.layout.fragment_any_view_layout);
        List<Film> values = filmData.getAllFilms();


        //neteja de la base de dades, descomentar per a que es s'executi en iniciar l'aplicació
        /*while(values.size()>0){
            Film del = values.get(0);
            filmData.deleteFilm(del);
            values.remove(0);
        }*/


        //Introducció de valors base amb repetició de protagnista, titols semblants i més d'una pelicula del mateix any
        if(values == null) {
            for (int i = 0; i < 7; ++i) {
                Film film = filmData.createFilm(Tx_titols[i], Tx_directors[i], Tx_paisos[i], int_anys[i], Tx_protagonista[i], int_val[i]);
                values.add(film);
            }
        }

    }

    private void showListActor(String name) {

        filmData = new FilmData(this);
        filmData.open();
        actorList = (ListView)findViewById(R.id.listViewActor);
        List<Film> values = filmData.getAllFilms();
        ArrayList<String> aux = new ArrayList<>();
        boolean vacio = true;
        for(Film film : values){
            String prota = film.getProtagonist();
            if(prota.contains(name)){
                aux.add(film.getTitle());
                vacio = false;
            }

        }
        Toast toast = new Toast(getApplicationContext());
        if(vacio)toast.makeText(getApplicationContext(),"No s'han trobat resultats",Toast.LENGTH_LONG).show();
        List<String> auxActor = aux;
        adapter4 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, auxActor);
        actorList.setAdapter(adapter4);
        actorList.setAdapter(adapter4);
        adapter4.notifyDataSetChanged();

    }

    private void showFilms() {
        //Db stuff
        filmData = new FilmData(this);
        filmData.open();

        List<Film> values = filmData.getAllFilms();
        ArrayList<String> values_titles = filmData.getAllFilmsTitles();

        adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, values_titles);
        lista.setAdapter(adapter2);


        @SuppressWarnings("unchecked")
        ArrayAdapter<String> adapter2 = (ArrayAdapter<String>) lista.getAdapter();
       /* Film film;
       String[] newFilm = new String[] { "Blade Runner", "Ridley Scott", "Rocky Horror Picture Show", "Jim Sharman", "The Godfather", "Francis Ford Coppola", "Toy Story", "John Lasseter",
                "Skt","Faker","Batman","Manolo","IronMan","Adolfo","Los chicos del coro","Mama" };
        //String [] yep = new String [] {"Pablo","Manco","Sergi","Manco","Martin","Manco","Carlos","Manco"};

        for(int i=0;i<newFilm.length;i=i+2) {
            film = filmData.createFilm(newFilm[i], newFilm[i + 1]);
            adapter2.add(newFilm[i]);
        }
        /*
        for(int i = 0;i<newFilm.length;i=i+2) {
            // save the new film to the database
            film = filmData.createFilm(newFilm[i], newFilm[i+1]);
            adapter2.add(newFilm[i]);
                if (lista.getAdapter().getCount() > 0) {
                film = (Film) lista.getAdapter().getItem(0);
                filmData.deleteFilm(film);
                adapter2.remove(newFilm[i]);
            }
        */
        Collections.sort(values_titles);
        adapter2.notifyDataSetChanged();
        //}

    }

    private void setListView(List<String> valuesFilter) {
        Collections.sort(valuesFilter);
        adapter3 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, valuesFilter);
        lista.setAdapter(adapter3);
        adapter3.notifyDataSetChanged();
    }



    @Override
    public void onBackPressed() {
        if(fragmentoActual == "main"){
            showFilms();
            recycle = false;
            lista.setVisibility(View.VISIBLE);
            searchView.setVisibility(View.VISIBLE);
            fragment = new FragmentCercaCamera();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_desplegable, fragment).commit();
            getSupportActionBar().setTitle("FilmsManager");

        }
        else if(fragmentoActual == "recycle"){
            fragmentrview = new any_view_layout();
            recycle = true;
            fragmentrview.setValues(rview_values);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_desplegable, fragmentrview).commit();
            getSupportActionBar().setTitle("Pel·lícules per any");
        }
        else if(fragmentoActual == "afegir"){
            recycle = false;
            fragment = new FragmentAfegir();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_desplegable, fragment).commit();
            getSupportActionBar().setTitle("Afegir Pel·lícula");
        }
        else if(fragmentoActual == "buscar"){
            recycle = false;
            fragment = new FragmentBuscar();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_desplegable, fragment).commit();
            getSupportActionBar().setTitle("Filtrar per protagonista");


        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!recycle) {
                    List<String> valuesfilter = filmData.getStudentListByKeyword(newText);
                    if (valuesfilter != null) setListView(valuesfilter);
                    else lista.setAdapter(null);
                    return false;
                }
                if(recycle){
                rview_values = filmData.getAllFilms();
                ArrayList<Film> aux = new ArrayList<Film>();
                for(Film film : rview_values) {
                    String name = film.getTitle();
                    if (name.contains(newText)) {

                        aux.add(film);
                    }
                }
                    if(aux != null){
                        rview_values = aux;
                        fragmentrview = new any_view_layout();
                        fragmentrview.setValues(rview_values);
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_desplegable, fragmentrview).commit();
                    }
                }
                return false;

            }
        });

        getMenuInflater().inflate(R.menu.desplegable, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        fragment = null;
        boolean FragTransaction = false;
        boolean MainView = true;

        if (id == R.id.nav_camera) {
            // Handle the camera action
            MainView = true;
            showFilms();
            lista.setVisibility(View.VISIBLE);
            searchView.setVisibility(View.VISIBLE);
            fragmentoActual = fragmentos.firstElement();
            fragmentos.add("main");
            recycle = false;
        } else if (id == R.id.nav_gallery) {
            MainView = false;
            lista.setVisibility(View.GONE);
            searchView.setVisibility(View.VISIBLE);
            fragmentrview = new any_view_layout();
            FragTransaction = true;
            recycle = true;
            fragmentoActual = fragmentos.lastElement();
            fragmentos.add("recycle");
            //showList();


        } else if (id == R.id.nav_slideshow) {
            MainView = false;
            recycle = false;
            lista.setVisibility(View.GONE);
            searchView.setVisibility(View.GONE);
            fragment = new FragmentAfegir();
            FragTransaction = true;
            fragmentoActual = fragmentos.lastElement();
            fragmentos.add("afegir");
        } else if (id == R.id.nav_manage) {
            MainView = false;
            recycle = false;
            lista.setVisibility(View.GONE);
            searchView.setVisibility(View.GONE);
            FragTransaction = true;
            fragment = new FragmentBuscar();
            fragmentoActual = fragmentos.lastElement();
            fragmentos.add("buscar");
        } else if (id == R.id.nav_share) {
            MainView = false;
            recycle = false;
            lista.setVisibility(View.GONE);
            searchView.setVisibility(View.GONE);
            fragment = new FragmentHelp();
            FragTransaction = true;
        } else if (id == R.id.nav_send) {
            MainView = false;
            recycle = false;
            lista.setVisibility(View.GONE);
            searchView.setVisibility(View.GONE);
            fragment = new FragmentAbout();
            FragTransaction = true;

        }

        if (FragTransaction) {
            if(recycle){
                rview_values = filmData.getAllFilmsOrderedbyAny();
                fragmentrview.setValues(rview_values);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_desplegable, fragmentrview).commit();
                item.setChecked(true);
                getSupportActionBar().setTitle(item.getTitle());

            }
            else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_desplegable, fragment).commit();
                item.setChecked(true);
                getSupportActionBar().setTitle(item.getTitle());
            }
        }

        if (MainView) {
            fragment = new FragmentCercaCamera();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_desplegable, fragment).commit();
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public static Boolean isValidInteger(String value) {
        try {
            Integer val = Integer.valueOf(value);
            if (val != null)
                return true;
            else
                return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public void onClick(View view){
        final Toast toast = new Toast(getApplicationContext());
        switch(view.getId()) {
            case (R.id.actButton):
                EditText editText = (EditText)findViewById(R.id.editTextActor);
                String name = editText.getText().toString();
                if(name.length()!= 0) showListActor(name);
                else editText.setError("El camp no pot ser buit.");

                break;

            case (R.id.aButton):
                EditText eted = ((EditText) findViewById(R.id.aedTitol));
                String auxTitol = eted.getText().toString();
                if(auxTitol.length()==0){
                    eted.setError("El camp Titol no pot ser buit.");
                    toast.makeText(getApplicationContext(),"ERROR AL AFEGIR",Toast.LENGTH_LONG).show();
                    break;
                }
                eted = ((EditText) findViewById(R.id.aedDirector));
                String auxDirector = eted.getText().toString();
                if(auxDirector.length()==0){
                    eted.setError("El camp Director no pot ser buit.");
                    toast.makeText(getApplicationContext(),"ERROR AL AFEGIR",Toast.LENGTH_LONG).show();
                    break;
                }
                eted = ((EditText) findViewById(R.id.aedPais));
                String auxPais = eted.getText().toString();
                if(auxPais.length()==0){
                    eted.setError("El camp Pais no pot ser buit.");
                    toast.makeText(getApplicationContext(),"ERROR AL AFEGIR",Toast.LENGTH_LONG).show();
                    break;
                }
                EditText eteda = ((EditText) findViewById(R.id.aedAny));
                String auxAny = eteda.getText().toString();
                if(auxAny.length()==0){
                    eteda.setError("El camp Any no pot ser buit.");
                    toast.makeText(getApplicationContext(),"ERROR AL AFEGIR",Toast.LENGTH_LONG).show();
                    break;
                }
                eted = ((EditText) findViewById(R.id.aedProtagonista));
                String auxProtagonista = eted.getText().toString();
                if(auxProtagonista.length()==0){
                    eted.setError("El camp Protagonista no pot ser buit.");
                    toast.makeText(getApplicationContext(),"ERROR AL AFEGIR",Toast.LENGTH_LONG).show();
                    break;
                }
                eted = ((EditText) findViewById(R.id.aedCrate));
                String auxCrate = eted.getText().toString();
                if (Integer.parseInt(auxCrate) < 0 || 9 < Integer.parseInt(auxCrate)){
                    eted.setError("El camp Valoració ha d'estar entre 0 i 9");
                    toast.makeText(getApplicationContext(),"ERROR AL AFEGIR",Toast.LENGTH_LONG).show();
                    break;
                }
                if(auxCrate.length()==0){
                    eted.setError("El camp Valoració no pot ser buit.");
                    toast.makeText(getApplicationContext(),"ERROR AL AFEGIR",Toast.LENGTH_LONG).show();
                    break;
                }
                try {
                    filmData.createFilm(auxTitol, auxDirector, auxPais, Integer.parseInt(auxAny), auxProtagonista, Integer.parseInt(auxCrate));
                    adapter2.add(auxTitol);
                    adapter2.notifyDataSetChanged();
                    toast.makeText(getApplicationContext(),"S'ha afegit l'element correctament",Toast.LENGTH_LONG).show();
                }catch (IllegalStateException e){
                    eteda.setError("Valoració o Any no son un nombre");
                    eted.setError("Valoració o Any no son un nombre");
                    toast.makeText(getApplicationContext(),"ERROR AL AFEGIR",Toast.LENGTH_LONG).show();
                }
                break;

            case (R.id.infodelbutton):
                final Film filmdel = fragmentinfo.getFilm();
                new AlertDialog.Builder(this)
                        .setTitle("ESBORRAR")
                        .setMessage("Segur que vols esborrar " +filmdel.getTitle() + " ?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                filmData.deleteFilm(filmdel);
                                rview_values = filmData.getAllFilmsOrderedbyAny();
                                fragmentrview.setValues(rview_values);
                                getSupportFragmentManager().beginTransaction().replace(R.id.content_desplegable, fragmentrview).commit();
                                toast.makeText(getApplicationContext(), "S'ha esborrat correctament " + filmdel.getTitle(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("Cancel·la", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();




                break;

            case (R.id.infovalbutton):
                EditText et = (EditText)findViewById(R.id.infovalvalue);
                String newValString = et.getText().toString();
                if(newValString.length()==0)et.setError("El camp no pot ser buit.");
                else{
                    boolean valid = isValidInteger(newValString);
                    if(!valid){
                        et.setError("Ha de ser un número.");
                        break;
                    }
                    Integer newVal = Integer.parseInt(newValString);
                    if (newVal >= 0 && 9 >= newVal) {
                        Film film = fragmentinfo.getFilm();
                        Film aux = film;
                        if (film != null) film.setCritics_rate(newVal);
                        fragmentinfo.setValoracio(film);
                        filmData.deleteFilm(aux);
                        filmData.createFilm(film.getTitle(), film.getDirector(), film.getCountry(), film.getYear(), film.getProtagonist(), film.getCritics_rate());
                        toast.makeText(getApplicationContext(), "S'ha modificat la valoració correctament", Toast.LENGTH_LONG).show();
                    } else {
                        editText = (EditText) findViewById(R.id.infovalvalue);
                        editText.setError("Número incorrecte");
                }
            }
                break;


            default:
                break;
        }
    }

    @Override
    public void thisitem(int Position) {
        Film film = filmData.getAllFilmsOrderedbyAny().get(Position);
        fragmentinfo = new Fragment_Item_Info();
        fragmentinfo.setFilm(film);
        fragmentoActual = fragmentos.lastElement();
        fragmentos.add("info");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_desplegable, fragmentinfo).commit();
        getSupportActionBar().setTitle(film.getTitle());
    }

}




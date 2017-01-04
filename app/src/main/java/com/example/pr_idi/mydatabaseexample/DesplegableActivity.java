package com.example.pr_idi.mydatabaseexample;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesplegableActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentCercaCamera.OnFragmentInteractionListener
        , FragmentAbout.OnFragmentInteractionListener, FragmentAfegir.OnFragmentInteractionListener,any_view_layout.OnFragmentInteractionListener {

    private FilmData filmData;
    ListView lista = null;
    RecyclerView rv_any;
    RecyclerView.Adapter rview_any_adapter;
    RecyclerView.LayoutManager rview_any_LayoutManager;
    SearchView searchView = null;
    ArrayAdapter<String> adapter2;
    ArrayAdapter<String> adapter3;

    //pelicules de mostra
    String[] Tx_titols = {"The Arrival", "Hacksaw Ridge", "The Amazing Spiderman", "The Amazing Spiderman 2", "The Godfather", "The Godfather II", "The Godfather III"};
    String[] Tx_paisos = {"USA", "USA", "USA", "USA", "USA", "USA", "USA"};
    String[] Tx_directors = {"Denis Villeneuve", "Mel Gibbson", "Marc Webb", "Marc Webb", "Francis Ford Coppola", "Francis Ford Coppola", "Francis Ford Coppola"};
    int[] int_anys = {2016, 2016, 2012, 2014, 1972, 1974, 1990};
    String[] Tx_protagonista = {"Amy Adams", "Andrew Garfield", "Andrew Garfield", "Andrew Garfield", "Marlon Brando", "Marlon Brando", "Al Pacino"};
    int[] int_val = {8, 7, 6, 5, 10, 8, 7};
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    //List<Film> films = new List<Film>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desplegable);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        lista = (ListView)findViewById(R.id.listView_Lista);
        //rv_any = (RecyclerView)findViewById(R.id.anyrviewid);
        cleanDB();
        //showList();
        showFilms();
        //rv_any.setVisibility(View.GONE);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void cleanDB(){
        //Db stuff
        filmData = new FilmData(this);
        filmData.open();

        List<Film> values = filmData.getAllFilms();

        while(values.size()>0){
            Film del = values.get(0);
            filmData.deleteFilm(del);
            values.remove(0);
        }
        //Neteja simple de la base de dades

        for(int i = 0; i < 7;++i){
            Film film = filmData.createFilm(Tx_titols[i],Tx_directors[i],Tx_paisos[i],int_anys[i],Tx_protagonista[i],int_val[i]);
            values.add(film);
        }

    }

    private void showList() {
        //Db stuff
        filmData = new FilmData(this);
        filmData.open();

       // setContentView(R.layout.fragment_any_view_layout);
        rv_any = (RecyclerView)findViewById(R.id.anyrviewid);
        List<Film> values = filmData.getAllFilms();

        rview_any_adapter = new rview_any_adapter(values);
        rv_any.setHasFixedSize(true);
        //rview_any_LayoutManager = new LinearLayoutManager(this);
        rv_any.setAdapter(rview_any_adapter);
        //rv_any.setLayoutManager(rview_any_LayoutManager);
        rview_any_adapter.notifyDataSetChanged();
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<String> valuesfilter = filmData.getStudentListByKeyword(newText);
                if (valuesfilter != null) setListView(valuesfilter);
                else lista.setAdapter(null);
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
        Fragment fragment = null;
        boolean FragTransaction = false;
        boolean MainView = true;

        if (id == R.id.nav_camera) {
            // Handle the camera action
            MainView = true;
            showFilms();
            lista.setVisibility(View.VISIBLE);
            searchView.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_gallery) {
            MainView = false;
            lista.setVisibility(View.GONE);
            searchView.setVisibility(View.GONE);
            //showList();
            fragment = new any_view_layout();
            FragTransaction = true;

        } else if (id == R.id.nav_slideshow) {
            MainView = false;
            lista.setVisibility(View.GONE);
            fragment = new FragmentAfegir();
            FragTransaction = true;
        } else if (id == R.id.nav_manage) {
            MainView = false;
            lista.setVisibility(View.GONE);
        } else if (id == R.id.nav_share) {
            MainView = false;
            lista.setVisibility(View.GONE);
        } else if (id == R.id.nav_send) {
            MainView = false;
            lista.setVisibility(View.GONE);
            fragment = new FragmentAbout();
            FragTransaction = true;

        }

        if (FragTransaction) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_desplegable, fragment).commit();
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());


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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Desplegable Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public void onClick(View view){
        switch(view.getId()) {
            case (R.id.aButton):
                String auxTitol = ((EditText)findViewById(R.id.aedTitol)).getText().toString();
                String auxDirector = ((EditText)findViewById(R.id.aedDirector)).getText().toString();
                String auxPais = ((EditText)findViewById(R.id.aedPais)).getText().toString();
                String auxAny = ((EditText)findViewById(R.id.aedAny)).getText().toString();
                String auxProtagonista = ((EditText)findViewById(R.id.aedProtagonista)).getText().toString();
                String auxCrate = ((EditText)findViewById(R.id.aedCrate)).getText().toString();
                filmData.createFilm(auxTitol, auxDirector, auxPais, Integer.parseInt(auxAny), auxProtagonista, Integer.parseInt(auxCrate)) ;
                adapter2.add(auxTitol);
                adapter2.notifyDataSetChanged();
                break;
            default:
                filmData.createFilm("TEST2", "PP", "SUMONERS RIFT", 2016, "Patricio", 10) ;
                break;
        }
    }
}




package com.example.pr_idi.mydatabaseexample;

/**
 * FilmData
 * Created by pr_idi on 10/11/16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FilmData {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    // Here we only select Title and Director, must select the appropriate columns
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_TITLE,
            MySQLiteHelper.COLUMN_DIRECTOR,
            MySQLiteHelper.COLUMN_COUNTRY,
            MySQLiteHelper.COLUMN_YEAR_RELEASE,
            MySQLiteHelper.COLUMN_PROTAGONIST,
            MySQLiteHelper.COLUMN_CRITICS_RATE };

    public FilmData(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Film createFilm(String title, String director, String country, int year, String protagonist, int crate) {
        ContentValues values = new ContentValues();
        Log.d("Creating", "Creating " + title + " " + director + " " + country + " " + Integer.toString(year) + " " + protagonist + " " + Integer.toString(crate) );

        // Add data: Note that this method only provides title and director
        // Must modify the method to add the full data
        values.put(MySQLiteHelper.COLUMN_TITLE, title);
        values.put(MySQLiteHelper.COLUMN_DIRECTOR, director);
        values.put(MySQLiteHelper.COLUMN_COUNTRY, country);
        values.put(MySQLiteHelper.COLUMN_YEAR_RELEASE, year);
        values.put(MySQLiteHelper.COLUMN_PROTAGONIST, protagonist);
        values.put(MySQLiteHelper.COLUMN_CRITICS_RATE, crate);

        // Actual insertion of the data using the values variable
        long insertId = database.insert(MySQLiteHelper.TABLE_FILMS, null,
                values);

        // Main activity calls this procedure to create a new film
        // and uses the result to update the listview.
        // Therefore, we need to get the data from the database
        // (you can use this as a query example)
        // to feed the view.

        Cursor cursor = database.query(MySQLiteHelper.TABLE_FILMS,allColumns,null,null,null,null,null);
        cursor.moveToFirst();
        Film newFilm = cursorToFilm(cursor);

        // Do not forget to close the cursor
        cursor.close();

        // Return the book
        return newFilm;
    }

    public void deleteFilm(Film film) {
        long id = film.getId();
        System.out.println("Film deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_FILMS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Film> getAllFilms() {
        List<Film> comments = new ArrayList<>();

       Cursor cursor = database.query(MySQLiteHelper.TABLE_FILMS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Film comment = cursorToFilm(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    public ArrayList<String>  getStudentListByKeyword(String search) {
        //Open connection to read only
        ArrayList<String> christmas = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  rowid as " +
                MySQLiteHelper.COLUMN_ID + "," +
                MySQLiteHelper.COLUMN_TITLE + "," +
                MySQLiteHelper.COLUMN_COUNTRY + "," +
                MySQLiteHelper.COLUMN_YEAR_RELEASE + "," +
                MySQLiteHelper.COLUMN_DIRECTOR + "," +
                MySQLiteHelper.COLUMN_PROTAGONIST + "," +
                MySQLiteHelper.COLUMN_CRITICS_RATE +
                " FROM " + MySQLiteHelper.TABLE_FILMS +
                " WHERE " +  MySQLiteHelper.COLUMN_DIRECTOR + "  LIKE  '%" +search + "%' "
                ;


        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Film film = cursorToFilm(cursor);
            String aux = film.getTitle();
            christmas.add(aux);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        return christmas;


    }

    public ArrayList<String> getAllFilmsTitles(){
        ArrayList<String> comments = new ArrayList<>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_FILMS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Film film = cursorToFilm(cursor);
            String aux = film.getTitle();
            comments.add(aux);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private Film cursorToFilm(Cursor cursor) {
        Film film = new Film();
        film.setId(cursor.getLong(0));
        film.setTitle(cursor.getString(1));
        film.setDirector(cursor.getString(2));
        film.setCountry(cursor.getString(3));
        film.setYear(cursor.getInt(4));
        film.setProtagonist(cursor.getString(5));
        film.setCritics_rate(cursor.getInt(6));
        return film;
    }
}
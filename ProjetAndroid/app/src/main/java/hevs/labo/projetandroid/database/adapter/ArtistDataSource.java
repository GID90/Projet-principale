package hevs.labo.projetandroid.database.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import hevs.labo.projetandroid.database.SQLiteHelper;

/**
 * Created by Anthony on 07/11/2015.
 */
public class ArtistDataSource {
    private SQLiteDatabase db;
    private Context context;

    public ArtistDataSource(Context context){
        SQLiteHelper sqliteHelper = SQLiteHelper.getInstance(context);
        db = sqliteHelper.getWritableDatabase();
        this.context = context;
    }

    /**
     * Insert new artist
     */
}
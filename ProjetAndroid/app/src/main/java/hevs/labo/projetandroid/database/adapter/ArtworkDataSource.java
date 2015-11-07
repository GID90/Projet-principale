package hevs.labo.projetandroid.database.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import hevs.labo.projetandroid.database.SQLiteHelper;

/**
 * Created by Darlène on 07.11.2015.
 */
public class ArtworkDataSource {

    private SQLiteDatabase db;
    private Context context;


    public ArtworkDataSource(Context context){
        SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(context);
        db = sqLiteHelper.getWritableDatabase();
        this.context = context;
    }

    /**
     * Insert a new Artwork
     */


}

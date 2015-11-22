package hevs.labo.projetandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import hevs.labo.projetandroid.database.adapter.ArtistDataSource;
import hevs.labo.projetandroid.database.object.Artist;

public class List_artist extends AppCompatActivity {


    ListView listView_artist;
    List<Artist> al;
    String[] tabArtistCreated;
    private Artist artistpicked;
    private ActionMode mActionMode = null;
    String expo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_artist);
        //getSupportActionBar().show();

        final ArtistDataSource ards = new ArtistDataSource(this);

        List<Artist> artistList = ards.getAllArtists();
        al= ards.getAllArtists();

        listView_artist = (ListView) findViewById(R.id.list_artist);

        listView_artist.setClickable(true);

        listView_artist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Artist a = al.get(position);


                Intent intent = new Intent(List_artist.this, Card_artist.class);
                intent.putExtra("id_artistRecup", String.valueOf(a.getId()));
                startActivity(intent);
            }
        });

        listView_artist.setChoiceMode(listView_artist.CHOICE_MODE_SINGLE);
        listView_artist.setTextFilterEnabled(true);

        if(artistList == null){
            return;
        }

        tabArtistCreated = new String[artistList.size()];

        for(int i = 0; i < artistList.size(); i++)
        {

            if(artistList.get(i).isExposed() == true)
            {
                 expo = "-------*EXPO*";

            }
            else
            {
                 expo = "---*NOEXPO*";
            }
           // tabArtistCreated[i] = artistList.get(i).toString();
            tabArtistCreated[i]= artistList.get(i).getLastname() + " " + artistList.get(i).getFirstname()+ "/" + artistList.get(i).getPseudo()+ " " +expo;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, tabArtistCreated);

        listView_artist.setAdapter(adapter);


    }


  /*  public void onListItemClick(ListView parent, View v, int position, long id){
        CheckedTextView item = (CheckedTextView) v;
        Toast.makeText(this, tabArtistCreated[position] + "checked : " + item.isChecked(), Toast.LENGTH_SHORT).show();
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_artist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {

            case R.id.accueil_menu:
                Intent intenthome = new Intent(this, MainActivity.class);
                startActivity(intenthome);
                return true;

            case R.id.list_artwork_menu:
                Intent intentartwork = new Intent(this, List_artwork.class);
                startActivity(intentartwork);
                return true;

            case R.id.exposition_menu:
                Intent intentexhibition = new Intent(this, List_exhibition.class);
                startActivity(intentexhibition);
                return true;

            case R.id.parametres_menu:
                Intent intentsettings = new Intent(this, Settings.class);
                startActivity(intentsettings);
                return true;

            case R.id.addArtist_menu:
                Intent intentaddArtistmenu = new Intent(this, Create_artist.class);
                startActivity(intentaddArtistmenu);
                return true;
        }

        return (super.onOptionsItemSelected(item));
    }
}

package hevs.labo.projetandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import hevs.labo.projetandroid.database.adapter.ArtistDataSource;
import hevs.labo.projetandroid.database.object.Artist;
import hevs.labo.projetandroid.database.object.Artwork;
import hevs.labo.projetandroid.database.SQLiteHelper;
import hevs.labo.projetandroid.database.adapter.ArtworkDataSource;

public class List_artwork extends AppCompatActivity {
    ListView listView;
    String[] tabArtworkCreated;
    List<Artwork> arl;
    String expo;
    Artist artistPickByForeign_Key;
    int idArtistForeign_Key;
    String nameArtistByFK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_artwork);

        ArtworkDataSource ads = new ArtworkDataSource(this);
        ArtistDataSource artds = new ArtistDataSource(this);

        List<Artwork> artworkList = ads.getAllArtworks();
        arl = ads.getAllArtworks();

        listView = (ListView) findViewById(R.id.list_artwork);

        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Artwork aw = arl.get(position);

                Intent intentaw = new Intent(List_artwork.this, Card_artwork.class);
                intentaw.putExtra("id_artworkRecup", String.valueOf(aw.getId()));
                startActivity(intentaw);

            }
        });

        listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);

        listView.setTextFilterEnabled(true);

        if(artworkList == null)
                return;

        tabArtworkCreated = new String[artworkList.size()];

        for(int i = 0; i < artworkList.size(); i++)
        {

            if(artworkList.get(i).getExposed() == true)
            {
                expo = "-------*EXPO*";

            }
            else
            {
                expo = "---*NOEXPO*";
            }


                  idArtistForeign_Key = artworkList.get(i).getForeign_key_Artist_id();

                    artistPickByForeign_Key = artds.getArtistById(idArtistForeign_Key);

                    nameArtistByFK = artistPickByForeign_Key.getLastname();

                    tabArtworkCreated[i]= artworkList.get(i).getName() + "----- " + artworkList.get(i).getType() + "-----"+ nameArtistByFK + "-----" + expo;

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, tabArtworkCreated);

        listView.setAdapter(adapter);


        //close db instance
        SQLiteHelper sqlHelper = SQLiteHelper.getInstance(this);
        sqlHelper.getWritableDatabase().close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_artwork, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){

            case R.id.accueil_menu:
                Intent intenthome = new Intent(this, MainActivity.class);
                startActivity(intenthome);
                return true;

            case R.id.artist_menu:
                Intent intentartist = new Intent(this, List_artist.class);
                startActivity(intentartist);
                return true;

            case R.id.exposition_menu:
                Intent intentexhibition = new Intent(this, List_exhibition.class);
                startActivity(intentexhibition);
                return true;

            case R.id.parametres_menu:
                Intent intentsettings = new Intent(this, Settings.class);
                startActivity(intentsettings);
                return true;

            case R.id.addArtwork_menu:
                Intent intentaddArtworkmenu = new Intent(this, Create_artwork.class);
                startActivity(intentaddArtworkmenu);
                return true;
        }

        return (super.onOptionsItemSelected(item));
    }
}

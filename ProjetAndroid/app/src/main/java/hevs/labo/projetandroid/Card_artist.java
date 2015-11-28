package hevs.labo.projetandroid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import hevs.labo.projetandroid.database.adapter.ArtistDataSource;
import hevs.labo.projetandroid.database.adapter.ArtworkDataSource;
import hevs.labo.projetandroid.database.object.Artist;
import hevs.labo.projetandroid.database.object.Artwork;

public class Card_artist extends AppCompatActivity {

    private TextView titre;
    private TextView annee;
    private Artist artistAafficher;
    private TextView artistMouvement;
    private ImageView photoArtist;

    //liste d artwork
    ListView listView_artworkFromTheArtist;
    String[] tabArtworkByArtist;
    String expo;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_artist);

        ArtistDataSource ards = new ArtistDataSource(this);
        ArtworkDataSource artworkDataSource = new ArtworkDataSource(this);

       // Intent intent = getIntent();

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            id =extras.getInt("id_artistRecup");
        }

        //récupérer l id puis
      /*  String id = intent.getStringExtra("id_artistRecup");
        int id_artist = Integer.parseInt(id);*/

        artistAafficher = ards.getArtistById(id);

        titre = (TextView) findViewById(R.id.tv_nom_artiste);
        titre.setText(artistAafficher.getFirstname() + " " +artistAafficher.getLastname());

        annee = (TextView) findViewById(R.id.tv_descriptionArtist_year);
        annee.setText(artistAafficher.getBirth() + " - " + artistAafficher.getDeath());

        artistMouvement = (TextView) findViewById(R.id.tv_descriptionArtist_descriptionmovement);
        artistMouvement.setText(artistAafficher.getMovement());

        photoArtist = (ImageView) findViewById(R.id.imageView_Artist);
        File imgFile = new  File(artistAafficher.getImage_path());

        if(imgFile.exists()) {
            Context context = getApplicationContext();
            CharSequence text = "Hello toast!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Uri uri = Uri.fromFile(imgFile);
            photoArtist.setImageURI(uri);
        }

        listView_artworkFromTheArtist = (ListView) findViewById(R.id.list_oeuvre);
        //pour supprimer une oeuvre il faut aller directement sur la carte
        //pas possible de supprimer depuis la liste
        listView_artworkFromTheArtist.setClickable(false);

        listView_artworkFromTheArtist.setTextFilterEnabled(true);

        List<Artwork> listArtwork = artworkDataSource.getAllArtworksByArtist(id);

        if(listArtwork == null)
        {
            return;
        }

        tabArtworkByArtist = new String[listArtwork.size()];

        for(int i = 0; i < listArtwork.size(); i++)
        {

            if(listArtwork.get(i).getExposed() == true)
            {
                expo = "-------*EXPO*";

            }
            else
            {
                expo = "---*NOEXPO*";
            }
            // tabArtistCreated[i] = artistList.get(i).toString();
            tabArtworkByArtist[i]= listArtwork.get(i).getName() + "\t" + listArtwork.get(i).getType() + "\t" + listArtwork.get(i).getCreationYear() + "\t" +expo;
        }

        ArrayAdapter<String> adapterArtworkByArtist = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, tabArtworkByArtist);

        listView_artworkFromTheArtist.setAdapter(adapterArtworkByArtist);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_card_artist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){

            case R.id.artist_menu:
                Intent intentartist = new Intent(this, List_artist.class);
                startActivity(intentartist);
                return true;

            case R.id.modifyArtist_menu:

                int id_artist_modif = artistAafficher.getId();

                Intent intentmodifyArtist = new Intent(this, Modify_artist.class);
                intentmodifyArtist.putExtra("id_artist_modif", String.valueOf(id_artist_modif));
                startActivity(intentmodifyArtist);


                return true;

            case R.id.deleteArtist_menu:

                int id_artist  = artistAafficher.getId();
                ArtistDataSource artistDataSource = new ArtistDataSource(this);
                artistDataSource.deleteArtist(id_artist);

                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(this, "Artist deleted", duration);
                toast.show();

                Intent backToList = new Intent(this, List_artist.class);
                startActivity(backToList);
                return true;
        }



        return (super.onOptionsItemSelected(item));


    }
}


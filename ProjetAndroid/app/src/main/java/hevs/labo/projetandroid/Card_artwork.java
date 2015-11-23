package hevs.labo.projetandroid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import hevs.labo.projetandroid.database.object.Artist;

import org.w3c.dom.Text;

import java.io.File;

import hevs.labo.projetandroid.database.adapter.ArtistDataSource;
import hevs.labo.projetandroid.database.adapter.ArtworkDataSource;
import hevs.labo.projetandroid.database.object.Artwork;

public class Card_artwork extends AppCompatActivity {

    private Artwork artworkAafficher;
    private TextView titreArtwork;
    private ImageView photoArtwork;
    private TextView realisationYear;
    private TextView artistCreator;
    private Artist artistCreatorArtist;
    private TextView typeartwork;
    private TextView descriptionartwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_artwork);

        ArtworkDataSource artworkdata = new ArtworkDataSource(this);
        Intent intent = getIntent();

        ArtistDataSource artistData = new ArtistDataSource(this);

        String id = intent.getStringExtra("id_artworkRecup");
        int id_artwork = Integer.parseInt(id);

        artworkAafficher = artworkdata.getArtworkById(id_artwork);

        titreArtwork= (TextView) findViewById(R.id.tv_nom_oeuvre);
        titreArtwork.setText(artworkAafficher.getName());

        photoArtwork = (ImageView) findViewById(R.id.ImageView_artworkCard);
        File imageArtworkFile = new File(artworkAafficher.getImage_path());

        if(imageArtworkFile.exists()) {
            Context context = getApplicationContext();
            CharSequence text = "Hello toast!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Uri uri = Uri.fromFile(imageArtworkFile);
            photoArtwork.setImageURI(uri);
        }

        realisationYear = (TextView) findViewById(R.id.tv_yearOfCreation);
        realisationYear.setText(String.valueOf(artworkAafficher.getCreationYear()));

        artistCreator = (TextView)findViewById(R.id.tv_artistCardArtwork);
        //recup nom
        int idFkArtistCreator = artworkAafficher.getForeign_key_Artist_id();
        artistCreatorArtist = artistData.getArtistById(idFkArtistCreator);

        artistCreator.setText(artistCreatorArtist.getLastname());

        typeartwork = (TextView) findViewById(R.id.tv_styleCardArtwork);
        typeartwork.setText(artworkAafficher.getType());


        descriptionartwork = (TextView) findViewById(R.id.tv_descriptionCardArtwork);
        descriptionartwork.setText(artworkAafficher.getDescription());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_card_artwork, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){

            case R.id.list_artwork_menu:
                Intent intentartwork = new Intent(this, List_artwork.class);
                startActivity(intentartwork);
                return true;

            case R.id.modifyArtwork_menu:
                int id_artworkToModify = artworkAafficher.getId();

                Intent intentmodifyArtwork = new Intent(this, Modify_artwork.class);
                intentmodifyArtwork.putExtra("id_artworkToModify" , String.valueOf(id_artworkToModify));
                startActivity(intentmodifyArtwork);
                return true;

            case R.id.deleteArtwork_menu:
                return true;
        }



        return (super.onOptionsItemSelected(item));

    }
}

package hevs.labo.projetandroid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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

    ArtworkArtistAdapter artworkAdapter;


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

        //here we recover the artist from the database by it's id
        artistAafficher = ards.getArtistById(id);

        //we recover the information about the artist to put in the fields which correspond to the information
        titre = (TextView) findViewById(R.id.tv_nom_artiste);
        titre.setText(artistAafficher.getFirstname() + " " +artistAafficher.getLastname());

        annee = (TextView) findViewById(R.id.tv_descriptionArtist_year);
        annee.setText(artistAafficher.getBirth() + " / " + artistAafficher.getDeath());

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

        final List<Artwork> listArtwork = artworkDataSource.getAllArtworksByArtist(id);

        View header = getLayoutInflater().inflate(R.layout.header_artwork_artist, null);

        listView_artworkFromTheArtist.addHeaderView(header);

       artworkAdapter = new ArtworkArtistAdapter(this.getApplicationContext(), listArtwork);

        listView_artworkFromTheArtist.setAdapter(artworkAdapter);


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

    public class ArtworkArtistAdapter extends BaseAdapter {

        ArtworkDataSource ads;
        ArtistDataSource ards;
        List<Artwork> listartadap;
        String[] artworks;

        public ArtworkArtistAdapter(Context context, List<Artwork> listartw){
            ads = new ArtworkDataSource(context);
            ards = new ArtistDataSource(context);
            listartadap = getDataForListView();
        }


        public List<Artwork> getDataForListView() {
            List<Artwork> listArtwork;
            listArtwork = ads.getAllArtworks();

            return listArtwork;
        }


        @Override
        public int getCount() {
            return listartadap.size();
        }

        @Override
        public Object getItem(int position) {
            return listartadap.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
            {
                LayoutInflater inflater = (LayoutInflater) Card_artist.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_list_artwork_adapter, parent, false);
            }

            TextView t1 = (TextView)convertView.findViewById(R.id.label1_NameArtwork);
            TextView t2 = (TextView) convertView.findViewById(R.id.label2_ArtistArtwork);
            ImageView i3 = (ImageView) convertView.findViewById(R.id.logo_artworkExposed);

            Artwork r = listartadap.get(position);

            t1.setText(r.getName());


            t2.setText(String.valueOf(r.getCreationYear()));

            if(r.getExposed() == true){
                i3.setImageDrawable(getResources().getDrawable(R.drawable.exposed));
            }
            else
            {
                i3.setImageDrawable(getResources().getDrawable(R.drawable.occuped));
            }

            return convertView;
        }


        public Artwork getArtwork(int position) {return listartadap.get(position);}


    }
}



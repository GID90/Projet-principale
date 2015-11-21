package hevs.labo.projetandroid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

import hevs.labo.projetandroid.database.adapter.ArtistDataSource;
import hevs.labo.projetandroid.database.object.Artist;

public class Modify_artist extends AppCompatActivity {

    private EditText lastname;
    private EditText firstname;
    private EditText pseudo;
    private EditText birthdate;
    private EditText deathdate;
    private Spinner spinner;
    private ImageButton btn_changePicture;
    private ImageView pictureArtistToModify;
    private Artist artistToModify;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_artist);


        ArtistDataSource ards = new ArtistDataSource(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id_artist_modif");
        int id_artist_modif = Integer.parseInt(id);

        artistToModify = ards.getArtistById(id_artist_modif);

        lastname = (EditText) findViewById(R.id.editText_nameArtistModiy);
        lastname.setText(artistToModify.getLastname());

        firstname = (EditText) findViewById(R.id.editText_lastnameArtistModify);
        firstname.setText(artistToModify.getFirstname());

        pseudo = (EditText) findViewById(R.id.editText_pseudoArtistModify);
        pseudo.setText(artistToModify.getPseudo());

        birthdate = (EditText) findViewById(R.id.editText_birthArtistModify);
        birthdate.setText(artistToModify.getBirth());

        deathdate = (EditText) findViewById(R.id.editText_deathArtistModify);
        deathdate.setText(artistToModify.getDeath());

        spinner = (Spinner) findViewById(R.id.spinner_mvtArtistModify);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.mvt_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        final Spinner spin = (Spinner) findViewById (R.id.spinner_mvtArtistModify);
        String[] groupe = getResources().getStringArray(R.array.mvt_array);

        ArrayAdapter<String> ad = new ArrayAdapter<String>(Modify_artist.this, android.R.layout.simple_spinner_dropdown_item,groupe);
        spin.setAdapter(ad);
        spin.setSelection(ad.getPosition(artistToModify.getMovement()));


        pictureArtistToModify = (ImageView) findViewById(R.id.imageView_photoArtistModify);
        File imgFile = new  File(artistToModify.getImage_path());

        if(imgFile.exists()) {
            Context context = getApplicationContext();
            Uri uri = Uri.fromFile(imgFile);
            pictureArtistToModify.setImageURI(uri);
        }







    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modify_artist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.artist_menu:
                Intent intentartist = new Intent(this, List_artist.class);
                startActivity(intentartist);
                return true;

            case R.id.cancelartistmodified_menu:
                //nous retournons à la page précédente : Card artist sans aucune modification
                Intent intentcancelArtist = new Intent(this, Card_artist.class);
                startActivity(intentcancelArtist);
                return true;

            case R.id.saveartistmodified_menu:




                /*
                String imagepath = saveToInternalStorage(bitmap);
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;


                Toast toastpict = Toast.makeText(context, imagepath, duration);
                toastpict.show();


                artist = new Artist();
                ArtistDataSource ads = new ArtistDataSource(this);

                EditText et = (EditText) findViewById(R.id.editText_nomArtistCreate);
                artist.setLastname(et.getText().toString());

                et = (EditText) findViewById(R.id.editText_prenomArtistCreate);
                artist.setFirstname(et.getText().toString());

                et = (EditText) findViewById(R.id.editText_pseudoArtistCreate);
                artist.setPseudo(et.getText().toString());

                et = (EditText) findViewById(R.id.editText_naissanceArtistCreate);
                artist.setBirth(et.getText().toString());

                et= (EditText) findViewById(R.id.editText_decesArtistCreate);
                artist.setDeath(et.getText().toString());

                Spinner spinner = (Spinner) findViewById(R.id.spinner_mvtArtistCreate);
                String recup = spinner.getSelectedItem().toString();
                artist.setMovement(recup);

                //path de la picture

                artist.setImage_path(imagepath);

                CheckBox bl = (CheckBox) findViewById(R.id.chbox_artistExposed);
                if(bl.isChecked()){
                    artist.setExposed(true);
                }
                else
                {
                 artist.setExposed(false);
                }

                artist.setId((int) ads.createArtist(artist));

                SQLiteHelper sqlHelper = SQLiteHelper.getInstance(this);
                sqlHelper.getWritableDatabase().close();

                startActivity(new Intent(this, List_artist.class));

                Toast toast = Toast.makeText(this, "Artist added", Toast.LENGTH_LONG);
                toast.show();

                return true;*/


                return true;

        }

        return (super.onOptionsItemSelected(item));

    }
}

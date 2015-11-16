package hevs.labo.projetandroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import hevs.labo.projetandroid.database.ArtGalleryContract;
import hevs.labo.projetandroid.database.SQLiteHelper;
import hevs.labo.projetandroid.database.adapter.ArtistDataSource;
import hevs.labo.projetandroid.database.object.Artist;

public class Create_artist extends Activity implements View.OnClickListener {
    private static final int RESULT_LOAD_ARTIST_IMAGE = 1;
    private ImageButton saveArtist;
    private Artist artist;

    ImageView imageToUpload;
    ImageButton bUploadImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_artist);

        imageToUpload = (ImageView) findViewById(R.id.imageView_photoArtistCreate);

        imageToUpload.setOnClickListener(this);
        bUploadImage.setOnClickListener(this);


    }

    public void onClick(View v)
    {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_ARTIST_IMAGE);
    }

    public void uploadArtistPicture(View view){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent,RESULT_LOAD_ARTIST_IMAGE );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_LOAD_ARTIST_IMAGE && resultCode == RESULT_OK && data!=null){
            Uri selectedImage = data.getData();
            imageToUpload.setImageURI(selectedImage);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_artist, menu);
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

            case R.id.cancelartistcreated_menu:
                return true;

            case R.id.saveartistcreated_menu:
                artist = new Artist();
                ArtistDataSource ards = new ArtistDataSource(this);

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

                artist.setId((int) ards.createArtist(artist));

                SQLiteHelper sqlHelper = SQLiteHelper.getInstance(this);
                sqlHelper.getWritableDatabase().close();

                startActivity(new Intent(this, List_artist.class));

                Toast toast = Toast.makeText(this, "Artist added", Toast.LENGTH_LONG);
                toast.show();

                return true;
        }

        return (super.onOptionsItemSelected(item));
    }
}

package hevs.labo.projetandroid;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Random;

import hevs.labo.projetandroid.database.SQLiteHelper;
import hevs.labo.projetandroid.database.adapter.ArtistDataSource;
import hevs.labo.projetandroid.database.adapter.ArtworkDataSource;
import hevs.labo.projetandroid.database.object.Artist;
import hevs.labo.projetandroid.database.object.Artwork;

public class Modify_artwork extends AppCompatActivity  {

    private int id_artworkToModify;
    private Artwork artworktoModify;
    private EditText nameArtwork;
    private Spinner nameArtistCreator;
    private EditText creationYear;
    private EditText typeArtwork;
    private EditText descriptionArtwork;
    private ImageButton btn_changePictureArtworkToModify;
    private ImageView pictureArtworkToModify;
    private Uri selectedImage;
    private Bitmap bitmap;
    private boolean isPicture;
    private CheckBox checkbexposedArtworkToModify;
    private List<Artist> listArtistCreatorToModify;
    String[] resourcesSpinnerNameArtistsToModify;
    private static final int RESULT_LOAD_ARTWORK_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_artwork);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id_artworkToModify");
        id_artworkToModify = Integer.parseInt(id);



        btn_changePictureArtworkToModify = (ImageButton) findViewById(R.id.imageButton_btnDownloadArtworkModify);
        btn_changePictureArtworkToModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_ARTWORK_IMAGE);
            }
        });


        pictureArtworkToModify = (ImageView) findViewById(R.id.imageView_photoArtworkModify);


        ArtworkDataSource artworkDataSource = new ArtworkDataSource(this);
        ArtistDataSource artistDataSource = new ArtistDataSource(this);

        artworktoModify = artworkDataSource.getArtworkById(id_artworkToModify);

        nameArtwork = (EditText) findViewById(R.id.editText_nameArtworkModif);
        nameArtwork.setText(artworktoModify.getName());

        //spinner : recuperer l artiste createur
        nameArtistCreator = (Spinner) findViewById(R.id.spinner_ArtistArtworkCreatedToModify);

        listArtistCreatorToModify = artistDataSource.getAllArtists();

        resourcesSpinnerNameArtistsToModify = new String[listArtistCreatorToModify.size()];

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapterArtist = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, resourcesSpinnerNameArtistsToModify);

// Specify the layout to use when the list of choices appears
        adapterArtist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        nameArtistCreator.setAdapter(adapterArtist);

        final Spinner spin = (Spinner) findViewById (R.id.spinner_ArtistArtworkCreatedToModify);

        ArrayAdapter<String> adapterModifyArtist = new ArrayAdapter<String>(Modify_artwork.this, android.R.layout.simple_spinner_dropdown_item,resourcesSpinnerNameArtistsToModify);
        spin.setAdapter(adapterModifyArtist);
        spin.setSelection(adapterModifyArtist.getPosition(String.valueOf(artworktoModify.getForeign_key_Artist_id())));

        creationYear = (EditText) findViewById(R.id.editText_realisationArtworkModif);
        creationYear.setText(artworktoModify.getCreationYear());

        typeArtwork = (EditText) findViewById(R.id.editText_typeArtworkModif);
        typeArtwork.setText(artworktoModify.getType());

        descriptionArtwork = (EditText) findViewById(R.id.edit_text_descriptionArtworkModify);
        descriptionArtwork.setText(artworktoModify.getDescription());




        File imgFile = new  File(artworktoModify.getImage_path());

        if(imgFile.exists()) {
            Context context = getApplicationContext();
            Uri uri = Uri.fromFile(imgFile);
            pictureArtworkToModify.setImageURI(uri);
        }

        checkbexposedArtworkToModify = (CheckBox) findViewById(R.id.chbox_artistExposedModif);
        if(artworktoModify.getExposed() == true)
        {
            checkbexposedArtworkToModify.setSelected(true);
        }
        else
        {
            checkbexposedArtworkToModify.setSelected(false);
        }





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {

            if (requestCode == RESULT_LOAD_ARTWORK_IMAGE && resultCode == RESULT_OK && null != data) {

                selectedImage = data.getData();
                pictureArtworkToModify.setImageURI(selectedImage);
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                isPicture = true;
            } else {
                Toast.makeText(this, "You haven't picket Image", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e ){
            Log.e("error", e.toString());
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }

    private String saveToInternalStorage(Bitmap bitmapImage) {



        Random rd = new Random();
        int randomnum = 1+ (int)(Math.random()*4000);

        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, randomnum+".jpg");

        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(mypath);

            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mypath.getPath();
    }

    private void onLoad() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT_LOAD_ARTWORK_IMAGE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modify_artwork, menu);
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

            case R.id.cancelartworkmodified_menu:
                Intent intentcancelArtWork = new Intent(this, Card_artwork.class);
                startActivity(intentcancelArtWork);
                return true;

            case R.id.saveartworkmodified_menu:

                //voir si on doit faire new artworktoModify

                String imagepathArtworkModify = saveToInternalStorage(bitmap);

                ArtworkDataSource artworkDataSource = new ArtworkDataSource(this);
                ArtistDataSource artistDataSource = new ArtistDataSource(this);

                EditText et = (EditText) findViewById(R.id.editText_nameArtworkModif);
                artworktoModify.setName(et.getText().toString());

                Spinner spinnerArtistFk = (Spinner) findViewById(R.id.spinner_ArtistArtworkCreatedToModify);
                String recupArtistFK = spinnerArtistFk.getSelectedItem().toString();
                String parts[] = recupArtistFK.split(" ");
                String idRecupArtist = parts[0];
                int fkArtist = Integer.parseInt(idRecupArtist);

                artworktoModify.setForeign_key_Artist_id(fkArtist);

                et = (EditText) findViewById(R.id.editText_realisationArtworkModif);
                artworktoModify.setCreationYear(Integer.parseInt(et.getText().toString()));

                et = (EditText) findViewById(R.id.editText_typeArtworkModif);
                artworktoModify.setType(et.getText().toString());

                et = (EditText) findViewById(R.id.edit_text_descriptionArtworkModify);
                artworktoModify.setDescription(et.getText().toString());

                if(imagepathArtworkModify !=  null){
                    artworktoModify.setImage_path(imagepathArtworkModify);
                }
                else
                {
                    artworktoModify.setImage_path(artworktoModify.getImage_path());
                }

                CheckBox checkboxArtworkExposed = (CheckBox) findViewById(R.id.chbox_artworkExposedToModify);
                if(checkboxArtworkExposed.isChecked()){
                    artworktoModify.setExposed(true);
                }
                else
                {
                    artworktoModify.setExposed(false);
                }

                artworkDataSource.updateArtwork(artworktoModify);


                SQLiteHelper sqlHelper = SQLiteHelper.getInstance(this);
                sqlHelper.getWritableDatabase().close();

                startActivity(new Intent(this, List_artwork.class));

                Toast toast = Toast.makeText(this, "Artwork modified", Toast.LENGTH_LONG);
                toast.show();

                return true;
        }



        return (super.onOptionsItemSelected(item));

    }
}

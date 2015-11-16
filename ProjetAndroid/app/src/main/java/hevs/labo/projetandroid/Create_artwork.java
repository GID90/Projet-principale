package hevs.labo.projetandroid;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import hevs.labo.projetandroid.database.object.Artwork;
import hevs.labo.projetandroid.database.SQLiteHelper;
import hevs.labo.projetandroid.database.adapter.ArtworkDataSource;

public class Create_artwork extends Activity {

    private Artwork artwork;
    private static int RESULT_LOAD_IMAGE = 1;
    String imgDecodableString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_artwork);

    }

   public void pickPicture(View view){
        Intent intentpickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intentpickImage, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
                    && data != null) {
                // Get the Image from data

                Uri selectedImage = data.getData();

                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imageView_photoArtworkCreate);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_artwork, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){

            case R.id.list_artwork_menu:
                startActivity(new Intent(this, List_artwork.class));
                return true;

            case R.id.cancelartworkcreated_menu:
                startActivity(new Intent(this, List_artwork.class));
                return true;

            case R.id.saveartworkcreated_menu:
                artwork = new Artwork();
                ArtworkDataSource ads = new ArtworkDataSource(this);

                EditText et = (EditText) findViewById(R.id.editText_nameArtworkCreate);
                artwork.setName(et.getText().toString());
                et = (EditText) findViewById(R.id.editText_realisationArtworkCreate);
                artwork.setCreationYear(Integer.parseInt(et.getText().toString()));
                et = (EditText) findViewById(R.id.editText_typeArtworkCreate);
                artwork.setType(et.getText().toString());
                et = (EditText) findViewById(R.id.editText_movementArtworkCreate);
                artwork.setMovement(et.getText().toString());
                et = (EditText) findViewById(R.id.edit_text_descriptionArtworkCreate);
                artwork.setDescription(et.getText().toString());

                artwork.setId((int) ads.createArtwork(artwork));

                //close db instance
                SQLiteHelper sqlHelper = SQLiteHelper.getInstance(this);
                sqlHelper.getWritableDatabase().close();

                startActivity(new Intent(this, List_artwork.class));

                Toast toast = Toast.makeText(this, "Artwork added", Toast.LENGTH_LONG);
                toast.show();

                return true;
        }

        return (super.onOptionsItemSelected(item));
    }
}

package hevs.labo.projetandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import hevs.labo.projetandroid.database.object.Artwork;
import hevs.labo.projetandroid.database.SQLiteHelper;
import hevs.labo.projetandroid.database.adapter.ArtworkDataSource;

public class Create_artwork extends AppCompatActivity {

    private Artwork artwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_artwork);
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
                artwork.setName(et.toString());
                //et = (EditText) findViewById(R.id.editText_realisationArtworkCreate);
                //artwork.setCreationYear(Integer.parseInt(et.toString()));
                et = (EditText) findViewById(R.id.editText_typeArtworkCreate);
                artwork.setType(et.toString());
                et = (EditText) findViewById(R.id.editText_movementArtworkCreate);
                artwork.setMovement(et.toString());
                et = (EditText) findViewById(R.id.edit_text_descriptionArtworkCreate);
                artwork.setDescription(et.toString());

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

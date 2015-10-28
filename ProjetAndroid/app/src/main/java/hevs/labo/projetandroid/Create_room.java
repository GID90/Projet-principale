package hevs.labo.projetandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Create_room extends AppCompatActivity {

    private ImageButton saveRoom;
    private EditText name;
    private EditText size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        saveRoom = (ImageButton) findViewById(R.id.button_saveCreate);
        name = (EditText) findViewById(R.id.et_create_room_name);
        size = (EditText) findViewById(R.id.et_create_room_size);
    }

    public void saveCreateRoom(View view){
        Intent intent = new Intent(Create_room.this, list_room.class);
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("size", size.getText().toString());

        Create_room.this.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_room, menu);
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
                Intent intentartist = new Intent(this, list_artist.class);
                startActivity(intentartist);
                return true;

            case R.id.list_artwork_menu:
                Intent intentartwork = new Intent(this, list_artwork.class);
                startActivity(intentartwork);
                return true;

            case R.id.exposition_menu:
                return true;

            case R.id.parametres_menu:
                return true;
        }

        return (super.onOptionsItemSelected(item));
    }
}

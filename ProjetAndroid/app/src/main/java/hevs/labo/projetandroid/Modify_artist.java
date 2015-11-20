package hevs.labo.projetandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class Modify_artist extends AppCompatActivity {

    private EditText lastname;
    private EditText firstname;
    private EditText pseudo;
    private EditText birthdate;
    private EditText deathdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_artist);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id_artist_modif");
        int id_artist_modif = Integer.parseInt(id);



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
                return true;

            case R.id.saveartistmodified_menu:
                return true;

        }

        return (super.onOptionsItemSelected(item));

    }
}

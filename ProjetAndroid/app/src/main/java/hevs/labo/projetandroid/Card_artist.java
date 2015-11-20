package hevs.labo.projetandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Card_artist extends AppCompatActivity {

    private TextView firstname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_artist);

        Intent intent = getIntent();

        firstname = (TextView) findViewById(R.id.tv_nom_artiste);
        firstname.setText(intent.getStringExtra("firstname"));

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
                Intent intentmodifyArtist = new Intent(this, Modify_artist.class);
                startActivity(intentmodifyArtist);
                return true;

            case R.id.deleteArtist_menu:
                return true;
        }



        return (super.onOptionsItemSelected(item));


    }
}


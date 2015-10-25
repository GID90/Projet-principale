package hevs.labo.projetandroid;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class create_artist extends Activity {

    private ImageButton saveArtist;
    private EditText firstname;
    private EditText lastname;
    private EditText pseudo;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_artist);

        saveArtist = (ImageButton)findViewById(R.id.button_saveCreate);
        firstname = (EditText)findViewById(R.id.editText_nomArtistCreate);
        lastname = (EditText) findViewById(R.id.editText_prenomArtistCreate);
        pseudo = (EditText) findViewById(R.id.editText_pseudoArtistCreate);



    }


    public void saveCreateArtist(View view){
        Intent intent = new Intent(create_artist.this, list_artist.class);
        intent.putExtra("firstname", firstname.getText().toString());
        intent.putExtra("lastname", lastname.getText().toString());
        intent.putExtra("pseudo", pseudo.getText().toString());

        create_artist.this.startActivity(intent);





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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

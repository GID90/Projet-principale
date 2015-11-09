package hevs.labo.projetandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import java.util.ArrayList;

import hevs.labo.projetandroid.database.Object.Artist;

public class List_artist extends AppCompatActivity {

    String firstname;
    String lastname;
    String pseudo;

    CheckedTextView ctv_artitst;
    ListView listView;

    Artist artist;

    ArrayList<Artist> listArtistCreated = new ArrayList<>();
    String[] tabArtistCreated;
    int tabSize;

    int cpt = 0;
    CheckBox box;

    String[] valeurstest = {"Lus", "Jean", "Paul"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_artist);
        //getSupportActionBar().show();

        Intent intent = getIntent();

        firstname = intent.getStringExtra("firstname");
        lastname = intent.getStringExtra("lastname");
        pseudo = intent.getStringExtra("pseudo");

        artist = new Artist();
        artist.setFirstname(firstname);
        artist.setLastname(lastname);
        artist.setPseudo(pseudo);
        artist.setExposed(false);

        listView = (ListView) findViewById(R.id.list);

        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object o = listView.getItemAtPosition(position);

                Intent intent = new Intent(List_artist.this, Card_artist.class);

                startActivity(intent);
            }
        });

        listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE);

        listView.setTextFilterEnabled(true);

        listArtistCreated.add(artist);
        cpt++;


        tabArtistCreated = new String[listArtistCreated.size()];

        for (int i = 0; i < tabArtistCreated.length; i++) {
            tabArtistCreated[i] = listArtistCreated.get(i).toString() + cpt;
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, android.R.id.text1, valeurstest);

        listView.setAdapter(adapter);



       /* ctv_artitst = (CheckedTextView)findViewById(R.id.list_artiste);

        ctv_artitst.setText(artist.toString());*/
    }


  /*  public void onListItemClick(ListView parent, View v, int position, long id){
        CheckedTextView item = (CheckedTextView) v;
        Toast.makeText(this, tabArtistCreated[position] + "checked : " + item.isChecked(), Toast.LENGTH_SHORT).show();
    }*/


    public void initPanel() {

        tabArtistCreated = new String[listArtistCreated.size()];
        for (int i = 0; i < tabArtistCreated.length; i++) {
            tabArtistCreated[i] = listArtistCreated.get(i).toString();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_artist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {

            case R.id.accueil_menu:
                Intent intenthome = new Intent(this, MainActivity.class);
                startActivity(intenthome);
                return true;

            case R.id.list_artwork_menu:
                Intent intentartwork = new Intent(this, List_artwork.class);
                startActivity(intentartwork);
                return true;

            case R.id.exposition_menu:
                Intent intentexhibition = new Intent(this, List_exhibition.class);
                startActivity(intentexhibition);
                return true;

            case R.id.parametres_menu:
                Intent intentsettings = new Intent(this, Settings.class);
                startActivity(intentsettings);
                return true;

            case R.id.addArtist_menu:
                Intent intentaddArtistmenu = new Intent(this, Create_artist.class);
                startActivity(intentaddArtistmenu);
                return true;
        }

        return (super.onOptionsItemSelected(item));
    }
}

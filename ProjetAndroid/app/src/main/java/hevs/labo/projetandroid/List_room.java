package hevs.labo.projetandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;

import java.util.ArrayList;

public class List_room extends AppCompatActivity {

    String name;
    double size;

    CheckedTextView ctv_room;
    ListView listView;

    Room room;
    ArrayList<Room> listRoomCreated = new ArrayList<>();
    String[] tabRoomCreated;
    int tabSize;

    int cpt = 0;
    CheckBox box;

    String[]valeurstest = {"S00", "S01", "s02"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_room);
        //getSupportActionBar().show();

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        size = intent.getDoubleExtra("size", 0);

        room = new Room();
        room.setName(name);
        room.setSize(size);
        room.setSelected(false);

        listView = (ListView) findViewById(R.id.listView_room);
        listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE);
        listView.setTextFilterEnabled(true);

        listRoomCreated.add(room);
        cpt++;

        tabRoomCreated = new String[listRoomCreated.size()];

        for(int i=0; i<tabRoomCreated.length; i++) {
            tabRoomCreated[i] = listRoomCreated.get(i).toString() + cpt;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, android.R.id.text1, valeurstest);
        listView.setAdapter(adapter);
    }

    public void initPanel() {
        tabRoomCreated = new String[listRoomCreated.size()];

        for(int i=0; i<tabRoomCreated.length; i++) {
            tabRoomCreated[i] = listRoomCreated.get(i).toString();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_room, menu);
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
                Intent intentartist = new Intent(this, List_artist.class);
                startActivity(intentartist);
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
        }

        return (super.onOptionsItemSelected(item));
    }

    public void addRoom(View view){


        Intent intent = new Intent(this, Create_room.class);

        startActivity(intent);
    }
}

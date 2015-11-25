package hevs.labo.projetandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hevs.labo.projetandroid.database.adapter.ArtistDataSource;
import hevs.labo.projetandroid.database.adapter.RoomDataSource;
import hevs.labo.projetandroid.database.object.Artist;
import hevs.labo.projetandroid.database.object.Room;

public class List_room extends AppCompatActivity {

    ListView listView_room;
    List<Room> list_room;
    String[] tabRoomCreated;
    private Room roompicked;
    private ActionMode mActionMode = null;
    String occup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_room);
        //getSupportActionBar().show();

        final RoomDataSource roomDataSource = new RoomDataSource(this);

        List<Room> roomList = roomDataSource.getAllRooms();
        list_room= roomDataSource.getAllRooms();

        listView_room = (ListView) findViewById(R.id.listView_room);

        listView_room.setClickable(true);

        listView_room.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Room r = list_room.get(position);

                Intent intent = new Intent(List_room.this, Card_room.class);
                intent.putExtra("id_RoomRecup", String.valueOf(r.getId()));
                startActivity(intent);
            }
        });

        listView_room.setChoiceMode(listView_room.CHOICE_MODE_SINGLE);
        listView_room.setTextFilterEnabled(true);

        if(roomList == null){
            return;
        }

        tabRoomCreated = new String[roomList.size()];

        for(int i = 0; i < roomList.size(); i++)
        {

            if(roomList.get(i).isSelected() == true)
            {
                occup = "-------*OCCUP*";

            }
            else
            {
                occup = "---*NOCCUP*";
            }
            // tabArtistCreated[i] = artistList.get(i).toString();
            tabRoomCreated[i]= roomList.get(i).getName() + "\t" + roomList.get(i).getSize()+ "\t"+ occup;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, tabRoomCreated);

        listView_room.setAdapter(adapter);


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

            case R.id.parametres_menu:
                Intent intentsettings = new Intent(this, Settings.class);
                startActivity(intentsettings);
                return true;

            case R.id.addRoom_menu:
                Intent intentaddRoom = new Intent(this, Create_room.class);
                startActivity(intentaddRoom);
                return true;
        }

        return (super.onOptionsItemSelected(item));
    }



}

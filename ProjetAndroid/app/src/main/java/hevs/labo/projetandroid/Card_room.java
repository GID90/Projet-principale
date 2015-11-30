package hevs.labo.projetandroid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import hevs.labo.projetandroid.database.adapter.ArtworkDataSource;
import hevs.labo.projetandroid.database.adapter.RoomDataSource;
import hevs.labo.projetandroid.database.object.Artist;
import hevs.labo.projetandroid.database.object.Room;

public class Card_room extends AppCompatActivity {

    private TextView nameRoom;
    private TextView size;
    private Room roomAafficher;

    private ImageView photoRoom;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_room);


        RoomDataSource roomDataSource = new RoomDataSource(this);
        Intent intent = getIntent();

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            id =extras.getInt("id_RoomRecup");
        }

        Log.e("Room", "idRoom: " + id);
        roomAafficher = roomDataSource.getRoomById(id);

        nameRoom = (TextView) findViewById(R.id.tv_name_card_room);
        nameRoom.setText(roomAafficher.getName());

        size = (TextView) findViewById(R.id.tv_card_room_size);
        size.setText(String.valueOf(roomAafficher.getSize()));

/*
        photoRoom = (ImageView) findViewById(R.id.ImageView_card_room);
        File imgFile = new  File(roomAafficher.getImage_path());

        if(imgFile.exists()) {
            Context context = getApplicationContext();
            Uri uri = Uri.fromFile(imgFile);
            photoRoom.setImageURI(uri);
        }*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_card_artwork, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){

            case R.id.rooms_menu:
                Intent intenthome = new Intent(this, List_room.class);
                startActivity(intenthome);
                return true;

            case R.id.modifyRoom_menu:
                Log.e("Room", "idRoom: " + id);
                Intent intentmodifyRoom = new Intent(this, Modify_room.class);
                intentmodifyRoom.putExtra("id_RoomRecup", String.valueOf(id));
                startActivity(intentmodifyRoom);
                return true;


            case R.id.deleteRoom_menu:

                int id_rooomTodelete  = roomAafficher.getId();
                RoomDataSource roomDataSource = new RoomDataSource(this);
                roomDataSource.deleteRoom(id_rooomTodelete);

                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(this, "Room deleted", duration);
                toast.show();

                Intent backToListRoom = new Intent(this, List_room.class);
                startActivity(backToListRoom);
                return true;
        }

        return (super.onOptionsItemSelected(item));
    }
}

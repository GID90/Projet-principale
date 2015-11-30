package hevs.labo.projetandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import hevs.labo.projetandroid.database.SQLiteHelper;
import hevs.labo.projetandroid.database.adapter.ArtistDataSource;
import hevs.labo.projetandroid.database.adapter.ArtworkDataSource;
import hevs.labo.projetandroid.database.adapter.RoomDataSource;
import hevs.labo.projetandroid.database.object.Artist;
import hevs.labo.projetandroid.database.object.Artwork;
import hevs.labo.projetandroid.database.object.Room;

public class Create_exhibition extends AppCompatActivity {

    List<Artist> spinnerResourcesArtists;
    List<Artwork> spinnerResourcesArtworks;
    List<Room> spinnerResourcesRoom;

    private Artist artist;
    private Room room;
    private Artwork artwork;

    String[] resourcesSpinnerNameArtists;
    String[] resourcesSpinnerNameArtwork;
    String[] resourcesSpinnerNameRoom;

    ListView listView_artworkFromTheArtist;
    String[] tabArtworkByArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exhibition);

        ArtistDataSource artistDataSource = new ArtistDataSource(this);
        ArtworkDataSource artworkDataSource = new ArtworkDataSource(this);
        RoomDataSource roomDataSource = new RoomDataSource(this);

        Spinner spinnerRoom = (Spinner) findViewById(R.id.spinner_room);
        Spinner spinnerArtist = (Spinner) findViewById(R.id.spinner_artist);
        Spinner spinnerArtwork = (Spinner) findViewById(R.id.spinner_artwork);


        spinnerResourcesArtists = artistDataSource.getAllArtists();
        spinnerResourcesArtworks = artworkDataSource.getAllArtworks();
        spinnerResourcesRoom = roomDataSource.getAllRooms();

        resourcesSpinnerNameArtists = new String[spinnerResourcesArtists.size()];
        resourcesSpinnerNameArtwork = new String[spinnerResourcesArtworks.size()];
        resourcesSpinnerNameRoom = new String[spinnerResourcesRoom.size()];

        for(int i = 0; i<spinnerResourcesRoom.size(); i++){
            resourcesSpinnerNameRoom[i] = spinnerResourcesRoom.get(i).getId()+ "  " + spinnerResourcesRoom.get(i).getName() ;
        }

        for(int i = 0; i<spinnerResourcesArtists.size(); i++){
            resourcesSpinnerNameArtists[i] = spinnerResourcesArtists.get(i).getId()+ "  " + spinnerResourcesArtists.get(i).getLastname() ;
        }

        for(int i = 0; i<spinnerResourcesArtists.size(); i++){
            resourcesSpinnerNameArtwork[i] = spinnerResourcesArtworks.get(i).getId()+ "  " + spinnerResourcesArtworks.get(i).getName() + "  " + spinnerResourcesArtists.get(i).getLastname();
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,  resourcesSpinnerNameArtists);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArtist.setAdapter(adapter1);
        spinnerRoom.setPrompt("Select an artist");
        spinnerRoom.setAdapter(new NothingSelectedSpinnerAdapter(adapter1, R.layout.artist_spinner_row_nothing_selected, this));

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,  resourcesSpinnerNameArtwork);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArtwork.setAdapter(adapter2);
        spinnerRoom.setPrompt("Select an artwork");
        spinnerRoom.setAdapter(new NothingSelectedSpinnerAdapter(adapter2, R.layout.artwork_spinner_row_nothing_selected, this));

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,  resourcesSpinnerNameRoom);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRoom.setAdapter(adapter3);

        spinnerRoom.setPrompt("Select a room");
        spinnerRoom.setAdapter(new NothingSelectedSpinnerAdapter(adapter3, R.layout.room_spinner_row_nothing_selected, this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_exhibition, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.exposition_menu:
                Intent intentexhibition = new Intent(this, List_exhibition.class);
                startActivity(intentexhibition);
                return true;

            case R.id.cancelexhibitionreated_menu:
                Intent intentExhibitionCancel = new Intent(this, List_exhibition.class);
                startActivity(intentExhibitionCancel);
                return true;

            case R.id.saveexhibitioncreated_menu:

                /**
                 * Manage Room
                 */
                room = new Room();
                RoomDataSource rds = new RoomDataSource(this);

                Spinner spinnerRoomId = (Spinner) findViewById(R.id.spinner_room);
                String recupRoom = spinnerRoomId.getSelectedItem().toString();
                String parts[] = recupRoom.split(" ");
                String idRecupRoom = parts[0];
                int idRoom = Integer.parseInt(idRecupRoom);
                room.setId(idRoom);
                room.setSelected(true);
                artwork.setForeign_key_Room_id(idRoom);

                /**
                 * Manage Artist
                 */
                artist = new Artist();
                ArtistDataSource atds = new ArtistDataSource(this);

                Spinner spinnerArtistId = (Spinner) findViewById(R.id.spinner_artist);
                String recupArtist = spinnerArtistId.getSelectedItem().toString();
                parts = recupArtist.split(" ");
                String idRecupArtist = parts[0];
                int idArtist = Integer.parseInt(idRecupArtist);
                artist.setId(idArtist);
                artist.setExposed(true);


                /**
                 * Manage Artwork
                 */
                artwork = new Artwork();
                ArtworkDataSource akds = new ArtworkDataSource(this);

                Spinner spinnerArtworkId = (Spinner) findViewById(R.id.spinner_artwork);
                String recupArtwork = spinnerArtworkId.getSelectedItem().toString();
                parts = recupArtwork.split(" ");
                String idRecupArtwork = parts[0];
                int idArtwork = Integer.parseInt(idRecupArtwork);
                artwork.setId(idArtwork);
                artwork.setExposed(true);


                SQLiteHelper sqlHelper = SQLiteHelper.getInstance(this);
                sqlHelper.getWritableDatabase().close();

                startActivity(new Intent(this, List_exhibition.class));

                Toast toast = Toast.makeText(this, R.string.exhibitionAdded, Toast.LENGTH_LONG);
                toast.show();

                return true;
        }
        return (super.onOptionsItemSelected(item));
    }
}

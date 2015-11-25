package hevs.labo.projetandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import hevs.labo.projetandroid.database.ArtGalleryContract;
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

    String[] resourcesSpinnerNameArtists;
    String[] resourcesSpinnerNameArtwork;
    String[] resourcesSpinnerNameRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exhibition);


        ArtistDataSource artistDataSource = new ArtistDataSource(this);
        ArtworkDataSource artworkDataSource = new ArtworkDataSource(this);
        RoomDataSource roomDataSource = new RoomDataSource(this);

        Spinner spinnerArtist = (Spinner) findViewById(R.id.spinner_artist);
        Spinner spinnerArtwork = (Spinner) findViewById(R.id.spinner_artwork);
        Spinner spinnerRoom = (Spinner) findViewById(R.id.spinner_room);

        spinnerResourcesArtists = artistDataSource.getAllArtists();
        spinnerResourcesArtworks = artworkDataSource.getAllArtworks();
        spinnerResourcesRoom = roomDataSource.getAllRooms();

        resourcesSpinnerNameArtists = new String[spinnerResourcesArtists.size()];
        resourcesSpinnerNameArtwork = new String[spinnerResourcesArtworks.size()];
        resourcesSpinnerNameRoom = new String[spinnerResourcesRoom.size()];

        for(int i = 0; i<spinnerResourcesArtists.size(); i++){
            resourcesSpinnerNameArtists[i] = spinnerResourcesArtists.get(i).getId()+ "  " + spinnerResourcesArtists.get(i).getLastname() ;
        }
        for(int i = 0; i<spinnerResourcesArtworks.size(); i++){
            resourcesSpinnerNameArtwork[i] = spinnerResourcesArtworks.get(i).getId()+ "  " + spinnerResourcesArtworks.get(i).getName() ;
        }
        for(int i = 0; i<spinnerResourcesRoom.size(); i++){
            resourcesSpinnerNameRoom[i] = spinnerResourcesRoom.get(i).getId()+ "  " + spinnerResourcesRoom.get(i).getName() ;
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,  resourcesSpinnerNameArtists);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArtist.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,  resourcesSpinnerNameArtwork);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArtwork.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,  resourcesSpinnerNameRoom);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRoom.setAdapter(adapter3);
        


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
                return true;

            case R.id.saveexhibitioncreated_menu:
                return true;
        }

        return (super.onOptionsItemSelected(item));
    }
}

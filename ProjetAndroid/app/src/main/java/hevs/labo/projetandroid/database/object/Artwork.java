package hevs.labo.projetandroid.database.object;

/**
 * Created by Darlène on 07.11.2015.
 */
public class Artwork {

    private int id;
    private String name;
    private String type;
    private int creationYear;
    private String movement;
    private String description;
    private boolean exposed;
    private String image_path;
    private int foreign_key_Artist_id;
    private int foreign_key_Room_id;

    public Artwork(){}

    public Artwork(String name, int creationYear, String type, String movement, String description, boolean exposed, String image_path){
        this.name = name;
        this.creationYear = creationYear;
        this.type = type;
        this.movement = movement;
        this.description = description;
        this.exposed = exposed;
        this.image_path = image_path;
    }

    public void setId(int id) {this.id = id; }
    public int getId(){return id; }

    public void setName(String name){ this.name = name; }
    public String getName() {return name;}

    public void setType(String type) {this.type = type; }
    public String getType() {return type; }

    public int getCreationYear() {
        return creationYear;
    }
    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }

    public String getMovement() {
        return movement;
    }
    public void setMovement(String movement) {
        this.movement = movement;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setExposed(boolean exposed){this.exposed = exposed; }
    public boolean getExposed() {return exposed;}

    public void setImage_path(String image_path) {this.image_path = image_path;}
    public String getImage_path() {return image_path; }

    public void setForeign_key_Artist_id(int foreign_key_Artist_id){this.foreign_key_Artist_id = foreign_key_Artist_id; }
    public int getForeign_key_Artist_id(){ return foreign_key_Artist_id; }

    public void setForeign_key_Room_id(int foreign_key_Room_id) {this.foreign_key_Room_id = foreign_key_Room_id; }
    public int getForeign_key_Room_id(){ return foreign_key_Room_id; }

    public String toString()
    {
        return name + " " + type + "    " ;
    }
}

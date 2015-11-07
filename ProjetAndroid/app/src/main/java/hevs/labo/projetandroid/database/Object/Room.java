package hevs.labo.projetandroid.database.Object;

/**
 * Created by Anthony on 28/10/2015.
 */
public class Room {

    private String name;
    private double size;
    private boolean selected;

    public Room() {}

    public Room(String name, double size, boolean selected) {
        this.name = name;
        this.size = size;
        this.selected = selected;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public double getSize() { return size; }

    public void setSize(double size) { this.size = size; }

    public boolean isSelected() { return selected; }

    public void setSelected(boolean selected) { this.selected = selected; }

    @Override
    public String toString() {
        return name + "   " + size + " m2";
    }
}

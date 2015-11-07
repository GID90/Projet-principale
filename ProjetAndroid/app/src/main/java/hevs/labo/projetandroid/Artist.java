package hevs.labo.projetandroid;

import android.widget.CheckBox;

import java.util.Date;

/**
 * Created by Darlène on 23.10.2015.
 */
public class Artist {

    private String firstname;
    private String lastname;
    private String pseudo;
    private Date birth;
    private Date death;
    private String movement;
    boolean selected;

    public  Artist(){}

    public Artist(String firstname, String lastname, String pseudo, boolean selected){/* String birth, String death, String movement**/
        this.firstname = firstname;
        this.lastname = lastname;
        this.pseudo = pseudo;
        this.selected = selected;


      /*  this.birth = birth;
        this.death = death;
        this.movement = movement;*/
    }

    public String toString(){
        return firstname + "    " + lastname + "    /   " + pseudo;
    }

    public boolean isSelected(){
        return selected;
    }
    public void setSelected(boolean selected){
        this.selected = selected;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public void setPseudo(String pseudo){
        this.pseudo = pseudo;
    }

    public void setBirth(Date birth){
        this.birth = birth;
    }

    public void setDeath(Date death){
        this.death = death;
    }

    public  void setMovement(String movement){
        this.movement = movement;
    }

    public String getFirstname(){
        return firstname;
    }

    public String getLastname(){
        return lastname;
    }

    public String getPseudo(){
        return pseudo;
    }

    public Date getBirth(){
        return birth;
    }

    public Date getDeath(){
        return  death;
    }

    public String getMovement(){
        return  movement;
    }
}

package fr.eni.demo.bo;

import java.io.Serializable;

public class Cours implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String titre;
    private int duree;
    public Cours() {
    }
    public Cours(long id, String titre, int duree) {
        this.id = id;
        this.titre = titre;
        this.duree = duree;
    }
// + Getter/Setter + toString()

    @Override
    public String toString() {
        return "Cours{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", duree=" + duree +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
}

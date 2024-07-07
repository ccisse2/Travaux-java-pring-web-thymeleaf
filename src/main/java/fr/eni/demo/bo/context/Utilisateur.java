package fr.eni.demo.bo.context;

import java.io.Serializable;

public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;
    private String pseudo;

    public Utilisateur() {
    }

    public Utilisateur(String pseudo) {
        this.pseudo = pseudo;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "pseudo='" + pseudo + '\'' +
                '}';
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
    // + Getter/Setter // + toString() }

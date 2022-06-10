package classes;

import java.lang.Math;

public class De {

    private Couleur couleur;                        // Couleur du dé
    private String [] listeFaces = new String[6];   // Liste des faces du dé
    private int nbCerveaux;                         // Nombre de cerveaux du dé
    private int nbEmpreintes;                       // Nombre d'empreintes du dé
    private int nbFusils;                           // Nombre de fusils du dé

    /*
     * Constructeur de la classe De qui prend en paramètre la couleur du dé
     */
    public De(Couleur couleur) {
        this.couleur = couleur;

        /*
         * Initialisation du nombre de cerveaux, d'empreintes et de fusils sur le dé en fonction de sa couleur
         */
        switch (couleur) {
            case VERT: {
                nbCerveaux = 3;
                nbEmpreintes = 2;
                nbFusils = 1;
                break;
            }
            case ORANGE : {
                nbCerveaux = 2;
                nbEmpreintes = 2;
                nbFusils = 2;
                break;
            }
            case ROUGE : {
                nbCerveaux = 1;
                nbEmpreintes = 2;
                nbFusils = 3;
            }
        }

        /*
         * Initialisation de la liste des faces du dé
         */
        faireFaces();
    }

    /*
     * Méthode qui permet de faire la liste des faces du dé
     */
    public void faireFaces() {
        for (int i = 0; i < nbCerveaux; i++) {
            listeFaces[i] = "Cerveau";
        }
        for (int i = nbCerveaux; i < nbCerveaux+nbEmpreintes; i++) {
            listeFaces[i] = "Empreintes";
        }
        for (int i = nbCerveaux+nbEmpreintes; i < nbCerveaux+nbEmpreintes+nbFusils; i++) {
            listeFaces[i] = "Fusil";
        }
    }

    /*
     * Méthode qui permet de lancer le dé
     * Retourne une face du dé aléatoirement
     */
    public String lancer() {
        int nbAleatoire = (int) (Math.random() * 6);
        return listeFaces[nbAleatoire];
    }

    /*
     * Getters
     */
    public String getListeFaces() {
        String faces = "";
        for (int i = 0; i < listeFaces.length; i++) {
            faces += "\t- " + listeFaces[i] + "\n";
        }
        return faces;
    }

    public String getCouleur() {
        return couleur.toString();
    }

    /*
     * ToString
     */
    @Override
    public String toString() {
        return "De " + couleur + " :\n" + getListeFaces();
    }
}

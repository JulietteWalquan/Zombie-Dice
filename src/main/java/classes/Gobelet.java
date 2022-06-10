package classes;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Gobelet {

    private String difficulte;  // Difficulté du jeu
    private int nbDesVerts;     // Nombre de dés vert
    private int nbDesOranges;   // Nombre de dés orange
    private int nbDesRouges;    // Nombre de dés rouge
    private List<De> listeDes;  // Liste des dés du gobelet

    /*
     * Constante des couleurs
     */
    private static final Couleur vert = Couleur.VERT;
    private static final Couleur orange = Couleur.ORANGE;
    private static final Couleur rouge = Couleur.ROUGE;

    /*
     * Constructeur de la classe Gobelet qui prend en paramètre la difficulté du jeu
     */
    public Gobelet(String difficulte) {
        this.difficulte = difficulte;

        /*
         * Définition du nombre de dés de chaque couleur en fonction de la difficulté
         */
        switch (difficulte) {
            case "Facile": {
                nbDesVerts = 8;
                nbDesOranges = 3;
                nbDesRouges = 2;
                break;
            }
            case "Moyen": {
                nbDesVerts = 6;
                nbDesOranges = 4;
                nbDesRouges = 3;
                break;
            }
            case "Difficile": {
                nbDesVerts = 4;
                nbDesOranges = 5;
                nbDesRouges = 4;
                break;
            }
        }

        listeDes = new ArrayList<>();   // Initialisation de la liste des dés
        faireGobelet();                 // Création du gobelet
    }

    /*
     * Méthode qui ajoute à la liste du gobelet le nombre de dés de chaque couleur
     */
    public void faireGobelet() {
        for (int i = 0; i < nbDesVerts; i++) {
            listeDes.add(new De(vert));
        }
        for (int i = 0; i < nbDesOranges; i++) {
            listeDes.add(new De(orange));
        }
        for (int i = 0; i < nbDesRouges; i++) {
            listeDes.add(new De(rouge));
        }
    }

    /*
     * Méthode qui permet de piocher un dé de la liste du gobelet
     * Retourne un dé pioché aléatoirement
     */
    public De piocher() {
        int nbAleatoire = (int) (Math.random() * listeDes.size());
        return listeDes.get(nbAleatoire);
    }
}

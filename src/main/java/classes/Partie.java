package classes;

import java.util.*;

public class Partie {

    private int nbJoueurs;                          // Nombre de joueurs de la partie
    private String difficulte;                      // Difficulté de la partie
    private Gobelet gobelet;                        // Gobelet de la partie
    private List<Joueur> listeJoueurs;              // Liste des joueurs de la partie
    private List<Joueur> listeClassementJoueurs;    // Liste du classement des joueurs
    private static boolean finPartie = false;       // Si la partie est terminée ou non

    /*
     * Constructeur de la classe Partie qui prend en paramètre le nombre de joueurs et la difficulté
     */
    public Partie(int nbJoueurs, String difficulte) {
        this.nbJoueurs = nbJoueurs;
        this.difficulte = difficulte;

        gobelet = new Gobelet(difficulte);  // Création du gobelet avec la difficulté choisie

        /*
         * Création des joueurs et de la liste des joueurs
         */
        listeJoueurs = new ArrayList<>();
        for (int i = 1; i <= nbJoueurs; i++) {
            listeJoueurs.add(new Joueur(i));
        }
    }

    /*
     * Méthode qui élabore le classement en fonction du nombre de cerveaux des joueurs
     */
    public void faireClassement() {
        listeClassementJoueurs = new ArrayList<>();
        for (Joueur j : listeJoueurs) {
            listeClassementJoueurs.add(j);
        }
        /*
         * Tri de la liste des joueurs par ordre croissant du nombre de cerveaux
         * Puis inversion de la liste pour avoir le classement décroissant
         */
        listeClassementJoueurs.sort(Comparator.comparingInt(Joueur::getNbCerveaux));
        Collections.reverse(listeClassementJoueurs);
    }

    /*
     * Getters et Setters
     */
    public Joueur getJoueur(int i) {
        return listeJoueurs.get(i);
    }

    public List<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }

    public Gobelet getGobelet() {
        return gobelet;
    }

    public int getNbJoueurs() {
        return nbJoueurs;
    }

    public List<Joueur> getClassement() {
        return listeClassementJoueurs;
    }

    public boolean getFinPartie() {
        return finPartie;
    }

    public void setFinPartie(boolean bool) {
        finPartie = bool;
    }
}

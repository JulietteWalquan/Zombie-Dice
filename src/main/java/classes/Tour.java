package classes;

import application.JeuController;

public class Tour {

    private Partie partie;                  // Partie dans laquelle se déroule le tour
    private Joueur joueur;                  // Joueur associé au tour
    private Gobelet gobelet;                // Gobelet associé à la partie
    private int nbCerveaux;                 // Nombre de cerveaux obtenus pendant le tour
    private int nbFusils;                   // Nombre de fusils obtenus pendant le tour
    private De[] desTour = new De[3];       // Tableau des 3 dés que le joueur possède en main
    private String face;                    // Face du dé
    private JeuController jeuController;    // Controlleur du jeu

    /*
     * Constructeur de la classe Tour qui prend en paramètre une partie, un joueur et un gobelet
     */
    public Tour(Partie partie, Joueur joueur, Gobelet gobelet) {
        this.partie = partie;
        this.joueur = joueur;
        this.gobelet = gobelet;

        jeuController = new JeuController();

        /*
         * Initialisation le nombre de cerveaux et de fusils à 0
         */
        nbCerveaux = 0;
        nbFusils = 0;

    }

    /*
     * Méthode qui permet au joueur de piocher des dés et effectue les changements nécessaires dans la partie
     * Retourne la main du joueur sous forme d'un tableau de 3 dés
     */
    public De[] piocherDes() {
        /*
         * Mise à jour des autorisations
         */
        joueur.setPeutFinirTour(false);
        joueur.setPeutLancer(true);
        joueur.setPeutPiocher(false);

        /*
         * Si l'emplacement du dé est null, le joueur pioche dans le gobelet
         */
        for (int i = 0; i < 3; i++) {
            if (desTour[i] == null) {
                desTour[i] = joueur.piocherDe(gobelet);
            }
        }

        return desTour;
    }

    /*
     * Méthode qui permet de lancer un dé et effectue les changements nécessaires dans la partie
     * Retourne la face du dé
     */
    public String lancerDe(De de) {
        /*
         * Mise à jour des autorisations
         */
        joueur.setPeutFinirTour(true);
        joueur.setPeutLancer(false);
        joueur.setPeutPiocher(true);

        /*
         * Le joueur lance le dé
         */
        String face = joueur.lancerDe(de);

        return face;
    }

    /*
     * Méthode qui permet au joueur de finir son tour et effectue les changements nécessaires dans la partie
     */
    public void arreterTour() {
        /*
         *  Ajout des cerveaux gagnés au compteur du joueur
         */
        joueur.setNbCerveaux(nbCerveaux);

        /*
         * Mise à jour des autorisations
         */
        partie.getJoueur(jeuController.getNumJoueur()).setPeutLancer(false);
        partie.getJoueur(jeuController.getNumJoueur()).setPeutPiocher(false);
    }

    /*
     * Méthode qui permet de vérifier si le joueur peut continuer son tour ou pas
     * Retourne un message qui s'affichera dans la fenêtre de jeu
     */
    public String verifTour() {
        /*
         * Si le joueur a plus de 3 fusils, il doit finir son tour
         */
        if (nbFusils >= 3) {
            nbCerveaux = 0;
            nbFusils = 0;
            partie.setFinPartie(false);
            arreterTour();
            return "Vous avez perdu votre tour.";
        }
        /*
         * Si le joueur possède plus de 13 cerveaux dans la main de sa partie et celle de son tour,
         * la fin de la partie est annoncée
         */
        else if (joueur.getNbCerveaux() + nbCerveaux >= 13) {
            partie.setFinPartie(true);
            return "Fin des tours restant puis fin de la partie.";
        }
        return "Vous pouvez rejouer ou arrêter votre tour.";
    }

    /*
     * Getters et Setters
     */
    public int getNbCerveaux() {
        return nbCerveaux;
    }

    public int getNbFusils() {
        return nbFusils;
    }

    public void zeroCerveau() {
        this.nbCerveaux = 0;
    }

    public void zeroFusil() {
        this.nbFusils = 0;
    }

    public void ajouterCerveau() {
        nbCerveaux++;
    }

    public void ajouterFusil() {
        nbFusils++;
    }
}

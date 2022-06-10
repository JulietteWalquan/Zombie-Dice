package classes;

public class Joueur {

    private int numero;                     // Numéro du joueur
    private String nom;                     // Nom du joueur
    private int nbCerveaux;                 // Nombre de cerveaux que le joueur possède depuis le début de la partie
    private boolean peutPiocher = true;     // Si le joueur peut piocher des dés ou pas
    private boolean peutLancer = false;     // Si le joueur peut lancer ses dés ou pas
    private boolean peutFinirTour = true;   // Si le joueur peut finir son tour ou pas

    /*
     * Constructeur de la classe Joueur qui prend en paramètre le numéro du joueur
     */
    public Joueur(int numero) {
        this.numero = numero;
        nom = "Joueur " + numero;
        nbCerveaux = 0; // Initialisation du nombre de cerveaux du joueur à 0
    }

    /*
     * Méthode qui permet au joueur de piocher un dé dans un gobelet
     * Retourne le dé pioché
     */
    public De piocherDe(Gobelet gobelet) {
        return gobelet.piocher();
    }

    /*
     * Méthode qui permet au joueur de lancer un dé
     * Retourne le résultat du dé
     */
    public String lancerDe(De de) {
        return de.lancer();
    }

    /*
     * Getters et Setters
     */
    public int getNbCerveaux() {
        return nbCerveaux;
    }

    public boolean getPeutPiocher() {
        return peutPiocher;
    }

    public boolean getPeutLancer() {
        return peutLancer;
    }

    public boolean getPeutFinirTour() {
        return peutFinirTour;
    }

    public void setNbCerveaux(int nb) {
        nbCerveaux += nb;
    }

    public void setPeutPiocher(boolean bool) {
        peutPiocher = bool;
    }

    public void setPeutLancer(boolean bool) {
        peutLancer = bool;
    }

    public void setPeutFinirTour(boolean peutFinirTour) {
        this.peutFinirTour = peutFinirTour;
    }

    /*
     * ToString
     */
    @Override
    public String toString() {
        return nom;
    }
}

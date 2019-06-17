package com.jeu.service;

import org.apache.log4j.Logger;

import java.util.Scanner;


/**
 * Cette classe contient le menu du début et la fin de jeu
 */

public class JeuRechercheMenu {

    private final static Logger logger = Logger.getLogger(JeuRechercheMenu.class);

    public JeuRechercheMenu() {
    }

    /**
     * Cette methode est le menu de l'application
     * L'utilisateur saisit son choix parmis ceux proposés pour rentrer dans l'un des modes.
     *
     * @param
     */

    public static void rechercheMenu() {
        System.out.println("Bienvenue dans le jeu Recherche +/-");
        System.out.println("Choisissez votre mode");
        System.out.println("CH - Challenger");
        System.out.println("DE - Defenseur");
        System.out.println("DU - Duel");

        logger.info("Vous êtes dans le menu du jeu");

        Scanner sc = new Scanner(System.in);
        String choice = sc.next();

        boolean choixMode = true;

        while (choixMode) {
            if ("CH".equals(choice)) {
                JeuRechercheChallenger jeuRechercheChallenger = new JeuRechercheChallenger();
                jeuRechercheChallenger.rechercheChallenger();
            } else if ("DE".equals(choice)) {
                JeuRechercheDefenseur jeuRechercheDefenseur = new JeuRechercheDefenseur();
                jeuRechercheDefenseur.rechercheDefenseur();
            } else if ("DU".equals(choice)) {
                JeuRechercheDuel jeuRechercheDuel = new JeuRechercheDuel();
                jeuRechercheDuel.rechercheDuel();
            } else {
                System.out.println("Vous n'avez pas choisi parmi les choix proposés");
                // il oblige l'utilisateur rentre son nouveau choix et arrête la boucle
                logger.warn("Vous n'avez pas choisi parmis les choix proposés");
                choice = sc.next();
            }
        }
    }


}
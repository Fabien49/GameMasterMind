package com.jeu.service;

import com.jeu.outils.Config;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Cette classe regroupe toutes les fonctionalités du mode Challenger.
 * les spécifications du mode challenger sont:
 * - L'intelligence artificielle de l’ordinateur joue le rôle de défenseur. Elle définit une combinaison de X chiffres aléatoirement.
 * - Le joueur a le rôle d’attaquant et doit faire une proposition d’une combinaison de X chiffres.
 * - L'intelligence artificielle de l’ordinateur indique pour chaque chiffre de la combinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si c’est le bon (=).
 * - Il y a un nombre limité d’essais.
 */

public class JeuRechercheChallenger {

    private final static Logger logger = Logger.getLogger(JeuRechercheChallenger.class);

    public JeuRechercheChallenger() {
    }

    public static void rechercheChallenger() {

        logger.info("Vous êtes dans le mode Challenger");

        /*
         * création d'une liste d'entier avec la collection d'arraylist contenant la combiansiaon secrète de l'ordinateur
         */

        List<Integer> combinaisonSecreteOrdi = new ArrayList<Integer>();
        Random rand = new Random();
        int max = 9;
        int min = 0;
        String configString = Config.getConfigValue("nbCombinaison");
        int configCombinaison = 0;
        int nb = 0;

        /**
         * Menu
         */

        System.out.println("Bienvenue dans le mode Challenger");

        if (configString.equals("")) {
            configCombinaison = 4;
        } else {
            configCombinaison = Integer.valueOf(Config.getConfigValue("nbCombinaison"));
        }

        logger.debug("le nombre de la combinaison est : " + configCombinaison);

        /*
         * Combinaison aléatoire de 'lordinateur
         */

        while (nb < configCombinaison) {
            combinaisonSecreteOrdi.add(rand.nextInt(max - min + 1) + min);
            nb++;
        }
        logger.debug("La combinaison secrète de l'ordinateur est : " + combinaisonSecreteOrdi);

        /*
         * Mode développeur ou non en fonction du fochier confi.properties
         * Si mode developpeur activé, il indique qu'on est en mode dev et montre la combinaison secrète
         */

        boolean configDeveloppeur = new Boolean(Config.getConfigValue("modeDeveloppeut"));
        if (configDeveloppeur) {
            logger.info("Vous êtes en mode développeur");
            System.out.println("Vous êtes en mode développeur");
            System.out.println("La combinaison de l'ordinateur est : " + combinaisonSecreteOrdi);
        }

        int nbessais = 1;
        int reste = 0;
        int confNbEssai = Integer.valueOf(Config.getConfigValue("nbEssai"));
        boolean findejeu = true;

        logger.debug("le nombre d'essai est : " + confNbEssai);

        System.out.println("Vous avez " + confNbEssai + " essai(s) pour trouver la combianaison");

        List<Integer> saisieJoueur = new ArrayList<>();

        /*
         * Boucle permettant que le joueur rentre sa comnbinaison et que l'ordinateur donne sa réponse tant que le nombre d'essai est > 0
         * Si la combinaison du joueur est égale à la combinaison de l'ordinateur, ça carrête la boucle et propose le menu de fin de jeu
         */
        //TODO exceptions de saisis longueur saisie
        while (nbessais < confNbEssai) {

            boolean saisieOk = true;
            while (saisieOk) {

                System.out.println("Veuillez entrer votre combinaison à " +configCombinaison+ " chiffres : ");
                Scanner sc = new Scanner(System.in);
                String nbsaisi = sc.next();

                try {
                    saisieJoueur = combiJoueurList(nbsaisi);
                    saisieOk = false;
                } catch (NumberFormatException exception) {
                    logger.error("Erreur de saisie. Veuillez entrer des chiffres " + exception);
                }
            }

            String reponseOrdi = new String();

            if (combinaisonSecreteOrdi.equals(saisieJoueur)) {
                System.out.println("" + saisieJoueur);
                System.out.println("Bravo vous avez trouvé la combinaison secrète de l'ordinateur");
                System.out.println("Vous avez trouvé la combinaison secrète en " + nbessais + " essai(s)");
                logger.debug("Vous avez trouvé la combianison secrète de l'ordinateur qui est : " + combinaisonSecreteOrdi + " en " + nbessais + " essais");
                findejeu = false;
                break;

            } else {
                for (int i = 0; i < combinaisonSecreteOrdi.size(); i++) {

                    if (combinaisonSecreteOrdi.get(i) < saisieJoueur.get(i)) {
                        reponseOrdi = reponseOrdi + "+";
                        logger.debug("La réponse de l'ordinateur est : " + reponseOrdi);
                    } else if (combinaisonSecreteOrdi.get(i) > saisieJoueur.get(i)) {
                        reponseOrdi = reponseOrdi + "-";
                        logger.debug("La réponse de l'ordinateur est : " + reponseOrdi);
                    } else {
                        reponseOrdi = reponseOrdi + "=";
                        logger.debug("La réponse de l'ordinateur est : " + reponseOrdi);
                    }
                }

                System.out.println("");
                System.out.println(saisieJoueur);
                logger.debug("La réponse de l'ordinateur est : " + reponseOrdi);
                System.out.println(reponseOrdi);
                nbessais++;
                reste = confNbEssai - nbessais;
                logger.debug("La réponse de l'ordinateur est : " + reste);
                System.out.println("Il vous reste " + reste + " essai(s)");
                reponseOrdi = new String();
            }
        }
        if (findejeu) {
            logger.debug("Dommage, vous n'avez pas trouvé la combinaison secrète de l'ordinateur");
            System.out.println("Dommage, vous n'avez pas trouvé la combinaison secrète de l'ordinateur");
        }

        System.out.println("Dommage vous n'avez poas trouvé la combinaison secrète");
        menuFinDeJeu();
    }


    /**
     * Converti la réponse du joueur de String à int
     *
     * @param saisie
     * @return
     */

    private static List<Integer> combiJoueurList(String saisie) {
        List<Integer> saisieJoueur = new ArrayList<Integer>();
        for (char charact : saisie.toCharArray()) {
            String chainChar = String.valueOf(charact);
            Integer chiffre = Integer.valueOf(chainChar);
            saisieJoueur.add(chiffre);
        }
        logger.debug("La combinaison du joueur est : " + saisieJoueur);
        System.out.println("Affichage de la nouvelle liste de chiffre : " + saisieJoueur);
        return saisieJoueur;
    }

    /**
     * Menu de fin du jeu
     * L'utilisateur saisit son choix parmis ceux proposés pour rejouer, changer de mode ou quitter l'application
     * @param
     */

    public static void menuFinDeJeu() {

        System.out.println("Choisissez parmis les choix suivants : ");
        System.out.println("Tapez RE pour rejouer");
        System.out.println("Tapez MO pour choisir un autre mode");
        System.out.println("Tapez QU pour quitter l'application");

        Scanner sc = new Scanner(System.in);
        String choice = sc.next();
        boolean choixJeu = true;

        while (choixJeu) {
            if ("RE".equals(choice)) {
                JeuRechercheChallenger jeuRechercheChallenger = new JeuRechercheChallenger();
                jeuRechercheChallenger.rechercheChallenger();
            } else if ("MO".equals(choice)) {
                JeuRechercheMenu jeuRechercheMenu = new JeuRechercheMenu();
                jeuRechercheMenu.rechercheMenu();
            } else if ("QU".equals(choice)) {
                System.exit(0);
            } else {
                System.out.println("Vous n'avez pas choisi parmi les choix proposés");
                logger.warn("Vous n'avez pas choisi parmis les choix proposés");
                choice = sc.next();
            }
        }
    }
}




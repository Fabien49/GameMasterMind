package com.jeu.service;

import org.apache.log4j.Logger;
import com.jeu.outils.Config;

import java.util.*;

/**
 * Cette classe regroupe toutes les fonctionalités du mode Duel.
 * * les spécifications du mode duel sont:
 * - Le joueur  et l'intelligence artificielle s'affrontent, ils définissent chacun une combianaison secrète.
 * - A tour de rôle ils essaient de trouver la combinaison de l'autre.
 * - Le joueur  et l'intelligence artificielle indiquent pour chaque chiffre de la combinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si c’est le bon (=).
 * - Le joueur et l’intelligence artificielle font une autre proposition en se basant sur la réponse fournit par le joueur.
 * - Le premier a trouver la combinaison secrète de l'autre a gagné
 * - Il y a un nombre limité d’essais.
 */

public class JeuRechercheDuel {

    private final static Logger logger = Logger.getLogger(JeuRechercheDuel.class);

    public JeuRechercheDuel() {
    }

    public void JeuRechercheDuel() {

        logger.info("Vous êtes dans le mode duel");

        /*
         * Menu
         */

        System.out.println("Bienvenue dans le mode Duel");

        /*
         * Mode développeur ou non en fonction du fochier confi.properties
         * Si mode developpeur activé, indique qu'on est en mode dev
         */

        boolean configDeveloppeur = new Boolean(Config.getConfigValue("modeDeveloppeut"));
        if (configDeveloppeur) {
            System.out.println("Vous êtes en mode développeur");
        }

        String configString = Config.getConfigValue("nbCombinaison");
        int configCombinaison = 0;
        int i = 0;

        if (configString.equals("")) {
            configCombinaison = 4;
        } else {
            configCombinaison = Integer.valueOf(Config.getConfigValue("nbCombinaison"));
        }

        logger.debug("le nombre de la combinaison est : " + configCombinaison);

        List<Integer> combinaisonSecreteJoueur = new ArrayList<>();
        boolean saisieOk = true;
        boolean saisiLongeur = true;
        while (saisieOk || saisiLongeur) {
            saisiLongeur = true;
            saisieOk = true;
            System.out.println("Veuillez saisir votre combinaison secrète à " + configCombinaison + " chiffres: ");
            Scanner sc = new Scanner(System.in);
            String nbsaisi = sc.next();
            if (nbsaisi.length() == configCombinaison) {
                saisiLongeur = false;
            } else {
                System.out.println("Vous n'avez pas saisit la bonne longueur pour votre combinaison");
            }
            try {
                combinaisonSecreteJoueur = combiSecreteJoueurList(nbsaisi);
                saisieOk = false;
            } catch (NumberFormatException exception) {
                logger.error("Erreur de saisie. Veuillez entrer des chiffres " + exception);
                System.out.println("Veuillez saisir des entiers entre 0 et 9");
            }
        }

        /*
         * Combinaison aléatoire de l'ordinateur
         */

        List<Integer> combinaisonSecreteOrdi = new ArrayList<Integer>();
        Random rand = new Random();
        int max = 9;
        int min = 0;

        while (i < configCombinaison) {
            combinaisonSecreteOrdi.add(rand.nextInt(max - min + 1) + min);
            i++;
        }

        logger.debug("La combinaison secrète de l'ordinateur est " + combinaisonSecreteOrdi);

        /*
         * montre les combinaisons secrètes si mode dev
         */

        if (configDeveloppeur) {
            logger.info("Vous êtes en mode développeur");
            System.out.println("Vous êtes en mode développeur");
            System.out.println("La combinaison secrète de l'ordinateur est : " + combinaisonSecreteOrdi);
            System.out.println("La combinaison secrète du joueur est : " + combinaisonSecreteJoueur);
        }

        int nbessais = 1;
        int reste = 0;
        int confNbEssai = Integer.valueOf(Config.getConfigValue("nbEssai"));

        List<Integer> combiOrdi = new ArrayList<Integer>();
        List<String> reponseJoueur = new ArrayList<>();

        boolean findejeu = true;


        String reponseOrdi = new String();

        Random random = new Random();
        int maxi = 9;
        int mini = 0;
        int j = 0;

        while (j < configCombinaison) {
            combiOrdi.add(random.nextInt(maxi - mini + 1) + mini);
            j++;
            logger.debug("La combinaison de l'ordinateur est " + combiOrdi);
            //System.out.println("La Combinaison de l'ordinateur est " +reponseOrdi);
        }

        List<Integer> saisieHumain;

        /*
         * Boucle permettant que le joueur et l'ordinateur rentrent leur comnbinaison respective et que chacun donnent sa réponse tant que le nombre d'essai est > 0
         * Si la combinaison du joueur est égale à la combinaison de l'ordinateur et /ou que la combinaison de l'ordianteur est égale à la combinaison secrète de l'ordinateur,
         * ça carrête la boucle et propose le menu de fin de jeu
         */

        while (nbessais < confNbEssai) {

            saisieOk = true;
            boolean saisiLongueur = true;
            String nbsaisiJoueur = new String();

            while (saisieOk || saisiLongueur) {

                System.out.println("Veuillez saisir votre combinaison");
                Scanner scan = new Scanner(System.in);
                nbsaisiJoueur = scan.next();
                if (nbsaisiJoueur.length() == configCombinaison) {
                    saisiLongueur = false;
                } else {
                    System.out.println("Vous n'avez pas saisit la bonne longueur pour votre combinaison");
                }
                try {
                    saisieHumain = saisieCombiJoueur(nbsaisiJoueur);
                    saisieOk = false;
                } catch (NumberFormatException exception) {
                    logger.error("Erreur de saisie. Veuillez entrer des chiffres " + exception);
                    System.out.println("Veuillez entrer des chiffres");
                }
            }
            saisieHumain = saisieCombiJoueur(nbsaisiJoueur);
            System.out.println("La combinaison du joueur est : " + saisieHumain);
            System.out.println("La combinaison de l'ordinateur est " + combiOrdi);

            // Verifie si la combinaison du joueur est égale à la combinaison secrète de l'ordinateur
            if (combinaisonSecreteOrdi.equals(saisieHumain)) {
                System.out.println("Bravo vous avez trouvé la combinaison secrète de l'ordinateur : " + saisieHumain);
                System.out.println("Vous avez trouvé la combinaison secrète en " + nbessais + " essai(s)");
                logger.debug("Vous avez trouvé la combinaison secrète de l'ordinateur qui est : " + combinaisonSecreteOrdi + " en " + nbessais + " essais");
                findejeu = false;
                break;
            }
            // Verifie si la combinaison de l'ordinateur est égale à la combinaison secrète du joueur
            if (combinaisonSecreteJoueur.equals(combiOrdi)) {
                System.out.println("Dommage vous avez perdu");
                System.out.println("L'ordinateur a trouvé votre combinaison secrète en " + nbessais + " essai(s)");
                System.out.println("La solution trouvé par l'ordinateur l'ordinateur est : " + combiOrdi);
                logger.debug("L'ordinateur a trouvé la combinaison secrète du joueur : " + combinaisonSecreteJoueur + " en " + nbessais + " essais");
                findejeu = false;
                break;
            }

            reponseJoueur = saisieReponseJoueur();
            System.out.println("La réponse du joueur est : " + reponseJoueur);

            //newList est une liste tampon pour  pouvoir modifier la combinaisson de l'ordinateur en fonction de la réponse du joueur
            List<Integer> newList = new ArrayList<Integer>();

            // Boucle permettant à l'ordinateur d'ajuster sa combinaison en fonction de la réponse du joueur
            for (int n = 0; n < reponseJoueur.size(); n++) {

                int valeurMoins = combiOrdi.get(n) + 1;
                int valeurPlus = combiOrdi.get(n) - 1;

                if (reponseJoueur.get(n).equals("-")) {
                    newList.add(valeurMoins);
                    logger.debug("La réponse de l'ordinateur est " + newList);
                } else if (reponseJoueur.get(n).equals("+")) {
                    newList.add(valeurPlus);
                    logger.debug("La réponse de l'ordinateur est " + newList);
                } else if (reponseJoueur.get(n).equals("=")) {
                    newList.add(combiOrdi.get(n));
                    logger.debug("La réponse de l'ordinateur est " + newList);
                }
            }
            combiOrdi.clear();
            combiOrdi.addAll(newList);
            newList.clear();

            // Bouble permettant de générer la réponse de l'ordinateur
            for (int k = 0; k < combinaisonSecreteOrdi.size(); k++) {

                if (combinaisonSecreteOrdi.get(k) < saisieHumain.get(k)) {
                    reponseOrdi = reponseOrdi + "+";
                    logger.debug("La réponse de l'ordinateur est : " + reponseOrdi);
                } else if (combinaisonSecreteOrdi.get(k) > saisieHumain.get(k)) {
                    reponseOrdi = reponseOrdi + "-";
                    logger.debug("La réponse de l'ordinateur est : " + reponseOrdi);
                } else {
                    reponseOrdi = reponseOrdi + "=";
                    logger.debug("La réponse de l'ordinateur est : " + reponseOrdi);
                }
            }

            System.out.println("La réponse de l'ordinateur est : " + reponseOrdi);
            reponseOrdi = "";

            System.out.println("");
            logger.debug("La combinaison de l'ordinateur est " + newList);

            nbessais++;
            reste = confNbEssai - nbessais;
            logger.debug("Il reste : " + reste + " essais");
            System.out.println("Il vous reste " + reste + " essai(s)");
        }
        if (findejeu) {
            logger.debug("Perssonne n'a trouvé la réponse");
            System.out.println("Personne n'a trouvé la combinaison secrète");
        }
        menuFinDeJeu();
    }


    /**
     * Converti la combianaison secrète du joueur de String à Integer
     * @param saisie
     * @return
     */

    private static List<Integer> combiSecreteJoueurList(String saisie) {
        List<Integer> combinaisonSecreteJoueur = new ArrayList<Integer>();
        for (char charact : saisie.toCharArray()) {
            String chainChar = String.valueOf(charact);
            Integer chiffre = Integer.valueOf(chainChar);
            combinaisonSecreteJoueur.add(chiffre);
        }
        logger.debug("La combinaison secrète du joueur est : " + combinaisonSecreteJoueur);
        return combinaisonSecreteJoueur;
    }


    /**
     * Appel de la métodhe scanner pour l'entrée de la réponse du joueur
     * Condition pour que la longueur soit égale à la longueur de la configuration
     * Condition pour que la longueur de la saisie soit aussi longue que celle de la configuration
     * @return
     */

    private static List<String> saisieReponseJoueur() {
        List<String> reponseHumain = new ArrayList<String>();
        String reponse = new String();
        boolean longueurReponse = true;

        String configString = Config.getConfigValue("nbCombinaison");
        int configCombinaison = 0;

        if (configString.equals("")) {
            configCombinaison = 4;
        } else {
            configCombinaison = Integer.valueOf(Config.getConfigValue("nbCombinaison"));
        }

        while (longueurReponse) {
            System.out.println("Merci de saisir votre réponse");
            Scanner sc = new Scanner(System.in);
            reponse = sc.next();
            if (reponse.length() == configCombinaison) {
                longueurReponse = false;
            } else {
                System.out.println("Vous n'avez pas saisit la bonne longueur pour votre réponse");
            }
            reponseHumain = changeList(reponse);
            logger.debug("La réponse du joueur est : " + reponseHumain);
        }
        return reponseHumain;
    }

    /**
     * Pour mettre de List à chaine de caractère dans la Liste reponseJoueur
     * @param saisie
     * @return
     */

    private static List<String> changeList(String saisie) {
        List<String> reponseJoueur = new ArrayList<String>();
        for (char charact : saisie.toCharArray()) {
            String chainChar = String.valueOf(charact);
            reponseJoueur.add(chainChar);
        }
        return reponseJoueur;
    }

    /**
     * methode pour convertir de String en Integer
     * @param saisie
     * @return
     */
    private static List<Integer> saisieCombiJoueur(String saisie) {
        List<Integer> saisieHumain = new ArrayList<Integer>();
        for (char charact : saisie.toCharArray()) {
            String chainChar = String.valueOf(charact);
            Integer chiffre = Integer.valueOf(chainChar);
            saisieHumain.add(chiffre);
        }
        logger.debug("La combinaison du joueur est : " + saisieHumain);
        return saisieHumain;
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
                JeuRechercheDuel jeuRechercheDuel = new JeuRechercheDuel();
                jeuRechercheDuel.JeuRechercheDuel();
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




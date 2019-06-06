package com.jeu.service;

import com.jeu.outils.Config;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Cette classe regroupe toutes les fonctionalités du mode Defenseur.
 *  * les spécifications du mode defenseur sont:
 * - Le joueur (cette fois dans le rôle de défenseur) définit une combinaison de X chiffres aléatoirement.
 * - L'intelligence artificielle de l’ordinateur doit faire une proposition d’une combinaison de X chiffres (c’est le rôle attaquant).
 * - Le joueur indique pour chaque chiffre de la combinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si c’est le bon (=).
 * - L’intelligence artificielle fait une autre proposition en se basant sur la réponse fournit par le joueur.
 * - Il y a un nombre limité d’essais.
 */

public class JeuRechercheDefenseur<menu> {

    private final static Logger logger = Logger.getLogger(JeuRechercheDefenseur.class);

    public JeuRechercheDefenseur(){
        }

        public static void rechercheDefenseur(){

            logger.info("Vous êtes dans le mode défenseur");

            /**
             * Menu
             */

            System.out.println("Bienvenue dans le mode Defenseur");

            /*
             * Mode développeur ou non en fonction du fochier confi.properties
             * Si mode developpeur activé, indique qu'on est en mode dev
             */

            boolean configDeveloppeur = new Boolean(Config.getConfigValue("modeDeveloppeut"));
            if (configDeveloppeur) {
                System.out.println("Vous êtes en mode développeur");
                logger.info("Vous êtes en mode développeur");
            }

            /*
             * Création de la liste de l'ordinateur "combinaisonSecreteJoueur avec la collection d'arraylist"
             * Creation de chiffres aléatoire et insertion dans la liste créée
             */

            System.out.println("Veuillez saisir votre combinaison secrète à 4 chiffres");
            Scanner sc = new Scanner(System.in);
            String nbsaisi = sc.next();
            List<Integer> combinaisonSecreteJoueur = combiJoueurList(nbsaisi);

            /*
             * montre la combinaison secrète si mode dev
             */

            if (configDeveloppeur) {
                System.out.println("La combinaison secrète du joueur est : " + combinaisonSecreteJoueur);
            }

            List<Integer> reponseOrdi = new ArrayList<Integer>();
            Random rand = new Random();
            int max = 9;
            int min = 0;
            String configString = Config.getConfigValue("nbCombinaison");
            int configCombinaison = 0;
            int nb= 0;

            if (configString.equals("")) {
                configCombinaison = 4;
            }else {
                configCombinaison = Integer.valueOf(Config.getConfigValue("nbCombinaison"));
            }

            logger.debug("le nombre de la combinaison est : " +configCombinaison);


            while (nb < configCombinaison) {

                reponseOrdi.add(rand.nextInt(max - min + 1) + min);
                nb++;
            }

            logger.debug("La combinaison de l'ordinateur est : " + reponseOrdi);

            System.out.println("La combinaison de l'ordinateur est : " + reponseOrdi);

            int nbessais = 0;
            int reste;
            int confNbEssai = Integer.valueOf(Config.getConfigValue("nbEssai"));

            logger.debug("le nombre d'essai est : " +confNbEssai);

            System.out.println("L'ordinateur a " +confNbEssai + " essai(s) pour trouver la combinaison secrète" );

            /*
             * Boucle permettant que l'ordinateur ajuste sa combinaison en fonction de la réponse du joueur et ceux tant que le nomùbre d'essai > 0
             * Si la combinaison de l'ordinateur est égale à la combinaison du joueur, ça carrête la boucle et propose le menu de fin de jeu
             */

            while (nbessais < confNbEssai) {

                if (combinaisonSecreteJoueur.equals(reponseOrdi)) {
                    List<String> reponseJoueur = saisieReponseJoueur(reponseOrdi, combinaisonSecreteJoueur);
                    System.out.println("" + reponseJoueur);
                    System.out.println("Dommage vous avez perdu");
                    System.out.println("L'ordinateur a trouvé votre combinaison secrète en " + nbessais + " essai(s)");
                    System.out.println("La solution trouvé par l'ordinateur l'ordinateur est : " + reponseOrdi);
                    logger.debug("L'ordinateur a trouvé la réponse : " + reponseOrdi + " en " + nbessais + " essais");
                    menuFinDeJeu();
                    break;

                } else {
                    List<String> reponseJoueur = saisieReponseJoueur(reponseOrdi, combinaisonSecreteJoueur);
                    List<Integer> newList = new ArrayList<Integer>();

                    for (int i = 0; i < reponseJoueur.size(); i++) {

                        int valeurMoins = reponseOrdi.get(i) +1;
                        int valeurPlus = reponseOrdi.get(i) -1;

                        if (reponseJoueur.get(i).equals("+")) {
                            newList.add(valeurMoins);
                            logger.debug("La réponse de l'ordinateur est " + newList);
                        } else if (reponseJoueur.get(i).equals("-")) {
                            newList.add(valeurPlus);
                            logger.debug("La réponse de l'ordinateur est " + newList);
                        } else if (reponseJoueur.get(i).equals("=")) {
                            newList.add(reponseOrdi.get(i));
                            logger.debug("La réponse de l'ordinateur est " + newList);
                        }
                    }
                    reponseOrdi.clear();
                    reponseOrdi.addAll(newList);

                    System.out.println("Le réponse du joueur est : " + reponseJoueur);
                    System.out.println("La nouvelle combinaison de l'ordinateur est : " + reponseOrdi);
                    logger.debug("La nouvelle combinaison de l'ordinateur est : " +reponseOrdi);
                    nbessais = nbessais + 1;

                    reste = confNbEssai - nbessais;
                    logger.debug("Il reste : " + reste + " essais");
                    System.out.println("Il vous reste " + reste + " essai(s)");
                }
            }
            System.out.println("L'ordianateur n'a pas réussi à trouver la combinaison secrète");
            menuFinDeJeu();
        }

    /**
     * Converti la combianaison secrète du joueur de String à int
     * @param saisie
     * @return
     */

    private static List<Integer> combiJoueurList(String saisie) {
        List<Integer> combinaisonSecreteJoueur = new ArrayList<Integer>();
        for (char charact : saisie.toCharArray()) {
            String chainChar = String.valueOf(charact);
            Integer chiffre = Integer.valueOf(chainChar);
            combinaisonSecreteJoueur.add(chiffre);
        }
        logger.debug("la combinaison secrète du joueur est : " +combinaisonSecreteJoueur);

        return combinaisonSecreteJoueur;
    }

    /**
     * Appel de la saisie de la réponse du joueur pour la mettre en chaine de caracteère dans la liste reponseJoueur
     * @param saisie
     * @return
     */

    private static List<String> changeList(String saisie) {
        List<String> reponseJoueur = new ArrayList<String>();
        for (char charact : saisie.toCharArray()) {
            String chainChar = String.valueOf(charact);
            reponseJoueur.add(chainChar);
        }

        logger.debug("la réponse du joueur est : " +reponseJoueur);

        return reponseJoueur;
    }

    /**
     * Appel de la métodhe scanner pour l'entrée de la réponse du joueur
     * Appel de la methode changeList pour la mettre dans la liste reponse Humain
     * @param reponseOrdi
     * @param combinaisonSecreteJoueur
     * @return
     */

    private static List<String> saisieReponseJoueur (List<Integer> reponseOrdi, List<Integer> combinaisonSecreteJoueur) {
        List<String> reponseHumain = new ArrayList<String>();

        System.out.println("Merci de saisir votre réponse");

        Scanner sc = new Scanner(System.in);
        String reponse = sc.next();
        reponseHumain = changeList(reponse);

        return reponseHumain;
    }

    /**
     * Menu de fin du jeu
     * Choix multiple:
     *  - rejouer (RE)
     *  - changer de mode (MO)
     *  - quitter l'application (QU)
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
                JeuRechercheDefenseur jeuRechercheDefenseur = new JeuRechercheDefenseur();
                jeuRechercheDefenseur.rechercheDefenseur();
            } else if ("MO".equals(choice)) {
                JeuRecherche jeuRecherche = new JeuRecherche();
                jeuRecherche.rechercheMenu();
            } else if ("QU".equals(choice)) {
                System.exit(0);
            } else {
                System.out.println("Vous n'avez pas choisi parmi les choix proposés");
                logger.warn("Vous n'avez pas choisi parmis les choix proposés");
                // il oblige l'utilisateur à rentrer son nouveau choix et arrête la boucle
                choice = sc.next();
            }
        }
    }
}

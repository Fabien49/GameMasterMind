package com.jeu.service;

import org.apache.log4j.Logger;
import com.jeu.outils.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JeuRechercheDuel {

    private final static Logger logger = Logger.getLogger(JeuRechercheDuel.class);

    public JeuRechercheDuel(){

        }

        public static void rechercheDuel() {

            logger.info("Vous êtes dans le mode duel");

            /**
             * Menu
             */

            System.out.println("Bienvenue dans le mode Duel");

            /**
             * Mode développeur ou non en fonction du fochier confi.properties
             * Si mode developpeur activé, indique qu'on est en mode dev
             */

            boolean configDeveloppeur = new Boolean(Config.getConfigValue("modeDeveloppeut"));
            if (configDeveloppeur) {
                System.out.println("Vous êtes en mode développeur");
            }

            String configString = Config.getConfigValue("nbCombinaison");
            int configCombinaison = 0;
            int i= 0;

            if (configString.equals("")) {
                configCombinaison = 4;
            }else {
                configCombinaison = Integer.valueOf(Config.getConfigValue("nbCombinaison"));
            }

            logger.debug("le nombre de la combinaison est : " +configCombinaison);

            System.out.println("Veuillez saisir votre combinaison secrète à " + configCombinaison + " chiffres");

            Scanner sc = new Scanner(System.in);
            String nbsaisi = sc.next();
            List<Integer> combinaisonSecreteJoueur = combiSecreteJoueurList(nbsaisi);

            /**
             * Combianaison aléatoire de l'ordinateur
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

            /**
             * montre les combinaisons secrètes si mode dev
             */

            if (configDeveloppeur) {
                logger.info("Vous êtes en mode développeur");
                System.out.println("Vous êtes en mode développeur");
                System.out.println("La combinaison secrète de l'ordinateur est : " + combinaisonSecreteOrdi);
                System.out.println("La combinaison secrète du joueur est : " + combinaisonSecreteJoueur);
            }

           // System.out.println("Veuillez saisir votre réponse : ");
           // Scanner sca = new Scanner(System.in);
           // String saisi = sca.next();
           // List<Integer> reponseJoueur = changeList(saisi);

            int nbessais = 0;
            int reste = 0;
            int confNbEssai = Integer.valueOf(Config.getConfigValue("nbEssai"));

            System.out.println("Il vous reste " +confNbEssai + " essai(s) pour trouver la combinaison" );

            /**
             * Boucle permettant que le joueur et l'ordinateur rentrent leur comnbinaison respective et que chacun donnent sa réponse tant que le nombre d'essai est > 0
             * Si la combinaison du joueur est égale à la combinaison de l'ordinateur et /ou que la combinaison de l'ordianteur est égale à la combinaison secrète de l'ordinateur,
             * ça carrête la boucle et propose le menu de fin de jeu
             */


              Scanner scan = new Scanner(System.in);
              String nbsaisiJoueur = scan.next();
              List<Integer> saisieHumain = saisieCombiJoueur (nbsaisiJoueur);

                if (combinaisonSecreteOrdi.equals(saisieHumain)) {
                    System.out.println("" + nbsaisiJoueur);
                    System.out.println("Bravo vous avez trouvé la combinaison secrète de l'ordinateur");
                    System.out.println("Vous avez trouvé la combinaison secrète en " + nbessais + " essai(s)");
                    menuFinDeJeu();
                }

                List<Integer> reponseOrdi = new ArrayList<Integer>();
                Random random = new Random();
                int maxi = 9;
                int mini = 0;
                int j = 0;

                logger.debug("le nombre de la combinaison est : " +configCombinaison);

                while (j < configCombinaison) {

                    reponseOrdi.add(random.nextInt(maxi - mini + 1) + mini);
                    j++;
                }

                System.out.println("La combinaison de l'ordinateur est : " + reponseOrdi);
                logger.debug("La combinaison de l'ordinateur est " + reponseOrdi);

                List<String> reponseJoueur = saisieReponseJoueur(reponseOrdi, combinaisonSecreteJoueur);

            while (nbessais < confNbEssai) {

                List<Integer> newList = new ArrayList<Integer>();

                String str = new String();

                for (int k = 0; k < combinaisonSecreteOrdi.size(); k++) {

                    if (combinaisonSecreteOrdi.get(k) < saisieHumain.get(k)) {
                        str = str + "+";
                        logger.debug("La réponse de l'ordinateur est : " +str );
                    } else if (combinaisonSecreteOrdi.get(k) > saisieHumain.get(k)) {
                        str = str + "-";
                        logger.debug("La réponse de l'ordinateur est : " +str );
                    } else {
                        str = str + "=";
                        logger.debug("La réponse de l'ordinateur est : " +str );
                    }
                }

                System.out.println("");
                logger.debug("La réponse de l'ordinateur est : " +str );
                System.out.println("La réponse de l'ordinateur est : " +str);

                if (combinaisonSecreteJoueur.equals(reponseOrdi)) {
                    System.out.println("" + reponseJoueur);
                    System.out.println("Dommage vous avez perdu");
                    System.out.println("L'ordinateur a trouvé votre combinaison secrète en " + nbessais + " essai(s)");
                    System.out.println("La solution trouvé par l'ordinateur l'ordinateur est : " + reponseOrdi);
                    menuFinDeJeu();
                    break;
                }

                    for (int n = 0; n < reponseJoueur.size(); n++) {

                        int valeurMoins = reponseOrdi.get(n) +1;
                        int valeurPlus = reponseOrdi.get(n) -1;

                        if (reponseJoueur.get(n).equals("+")) {
                            newList.add(valeurMoins);
                            logger.debug("La réponse de l'ordinateur est " + newList);
                        } else if (reponseJoueur.get(n).equals("-")) {
                            newList.add(valeurPlus);
                            logger.debug("La réponse de l'ordinateur est " + newList);
                        } else if (reponseJoueur.get(n).equals("=")) {
                            newList.add(reponseOrdi.get(n));
                            logger.debug("La réponse de l'ordinateur est " + newList);
                        }
                    }
                    System.out.println("");
                    System.out.println("La combinaison de l'ordinateur est : "+ newList);
                    logger.debug("La combinaison de l'ordinateur est " + newList);

                nbessais++;
                reste = confNbEssai - nbessais;
                logger.debug("Il reste : " + reste + " essais");
                System.out.println("Il vous reste " + reste + " essai(s)");

                reponseJoueur = saisieReponseJoueur(reponseOrdi, combinaisonSecreteJoueur);

                 System.out.println("Veuillez saisir votre nouvelle combinaison");
                 scan = new Scanner(System.in);
                 nbsaisiJoueur = scan.next();
                 saisieHumain = saisieCombiJoueur (nbsaisiJoueur);


                    //str = new String();

            }
            System.out.println("Personne n'a trouvé la combinaison secrète");
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
        logger.debug("La combinaison secrète du joueur est : " +combinaisonSecreteJoueur);
        //System.out.println("La combinaison secrète est : " + combinaisonSecreteJoueur);
        return combinaisonSecreteJoueur;
    }

    /**
     * Converti l'essai du joueur de String à Integer
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
     * Converti la saisie de l'utilisateur de Strint à Integer
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
        logger.debug("La combinaison du joueur est : " +saisieHumain);
        System.out.println("La combinaison du joueur est : " + saisieHumain);
        return saisieHumain;
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
        logger.debug("La réponse du joueur est : " +reponseHumain);
        return reponseHumain;
        }

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
                logger.error("Vous n'avez pas choisi parmis les choix proposés");
                // il oblige l'utilisateur à rentrer son nouveau choix et arrête la boucle
                choice = sc.next();
            }
        }
    }
}
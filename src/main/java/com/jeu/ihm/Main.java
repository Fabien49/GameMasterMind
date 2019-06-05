package com.jeu.ihm;

import com.jeu.outils.Config;
import com.jeu.service.JeuRecherche;
import org.apache.log4j.Logger;


public class Main {

    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        logger.info("Lancement du jeu");

        String resultat = Config.getConfigValue("nbCombinaison");
        System.out.println(resultat);

                JeuRecherche jeuRecherche = new JeuRecherche();
                jeuRecherche.rechercheMenu();

        }

}


package com.jeu.ihm;

import com.jeu.service.JeuRechercheMenu;
import org.apache.log4j.Logger;

/**
 * La classe Main sert à lancer l'application
 * Elle envoit directement dans la classe JeuRechercheMenu
 */

public class Main {

    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        logger.info("Lancement du jeu");

                JeuRechercheMenu jeuRechercheMenu = new JeuRechercheMenu();
                jeuRechercheMenu.rechercheMenu();
        }

}


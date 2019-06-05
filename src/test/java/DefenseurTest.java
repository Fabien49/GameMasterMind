import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DefenseurTest {


    public static void main(String[] args) {
        /**
         * création d'une liste d'entier avec la collection d'arraylist
         */
        System.out.println("Bienvenue dans le jeu Recherche +/- : mode Challengeur");
        System.out.println("Vous avez 10 chances pour trouver la combinaison secrète");
        System.out.println("A vous de jouer !!!");

        /**
         * Création de la liste de l'ordinateur "combinaisonSecrete"
         * Creation de chiffres aléatoire et insertion dans la liste créée
         */


        System.out.println("Veuillez saisir votre combinaison secrète à 4 chiffres");
        Scanner sc = new Scanner(System.in);
        String nbsaisi = sc.next();


        List<Integer> nouvelleList = new ArrayList<Integer>();
        Random rand = new Random();
        int max = 9;
        int min = 0;

        nouvelleList.add(rand.nextInt(max - min + 1) + min);
        nouvelleList.add(rand.nextInt(max - min + 1) + min);
        nouvelleList.add(rand.nextInt(max - min + 1) + min);
        nouvelleList.add(rand.nextInt(max - min + 1) + min);
        System.out.println("La première combinaison de l'ordinateur est : " + nouvelleList);
        List<Integer> combinaisonSecrete = extraiList(nbsaisi);


        int nbessais = 0;
        int reste;

        while (nbessais < 10) {

            if (combinaisonSecrete.equals(nouvelleList)) {
                List<String> resultat = comparaison(nouvelleList, combinaisonSecrete);
                System.out.println("" + resultat);
                System.out.println("L'ordinateur a trouvé votre combinaison secrète en " + nbessais + " essai(s)");
                System.out.println("La nouvelle combinaison de l'ordinateur est : " + nouvelleList);
                break;

            } else {
                List<String> resultat = comparaison(nouvelleList, combinaisonSecrete);
                List<Integer> newList = new ArrayList<Integer>();

                for (int i = 0; i < resultat.size(); i++) {

                    int valeurMoins = nouvelleList.get(i) +1;
                    int valeurPlus = nouvelleList.get(i) -1;

                    if (resultat.get(i).equals("+")) {
                        newList.add(valeurMoins);
                    } else if (resultat.get(i).equals("-")) {
                        newList.add(valeurPlus);
                    } else if (resultat.get(i).equals("=")) {
                        newList.add(nouvelleList.get(i));
                    }
                }
                nouvelleList.clear();
                nouvelleList.addAll(newList);

                System.out.println("Le resultat est : " + resultat);
                System.out.println("La nouvelle combinaison de l'ordinateur est : " + nouvelleList);
                nbessais = nbessais + 1;

                reste = 10 - nbessais;
                System.out.println("Il vous reste " + reste + " essai(s)");

            }
        }
    }

    private static List<Integer> extraiList(String saisie) {
        List<Integer> combinaisonSecrete = new ArrayList<Integer>();
        for (char charact : saisie.toCharArray()) {
            String chainChar = String.valueOf(charact);
            Integer chiffre = Integer.valueOf(chainChar);
            combinaisonSecrete.add(chiffre);
        }
        System.out.println("La combinaison secrète est : " + combinaisonSecrete);
        return combinaisonSecrete;
    }

    private static List<String> comparaison(List<Integer> nouvelleList, List<Integer> combinaisonSecrete) {
        List<String> resultat = new ArrayList<String>();

        for (int i = 0; i < combinaisonSecrete.size(); i++) {

            if (combinaisonSecrete.get(i) == nouvelleList.get(i)) {
                resultat.add("=");
            } else if (combinaisonSecrete.get(i) < nouvelleList.get(i)) {
                resultat.add("-");
            } else if (combinaisonSecrete.get(i) > nouvelleList.get(i)) {
                resultat.add("+");
            }
        }
        return resultat;
    }
}





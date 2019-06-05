import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Dueltest {

    public static void main(String[] args) {

        System.out.println("Bienvenue dans le jeu Recherche +/- : mode Duel");
        System.out.println("Vous avez 10 chances pour trouver la combinaison secrète de l'autre");
        System.out.println("A vous de jouer !!!");

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


}


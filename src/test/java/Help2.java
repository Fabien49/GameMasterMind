import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Help2 {

    public static void main(String[] args) {
        /**
         * création d'une liste d'entier avec la collection d'arraylist
         */
        List<Integer> combinaisonSecrete = new ArrayList<Integer>();
        Random rand = new Random();
        int max = 9;
        int min = 0;

        combinaisonSecrete.add(rand.nextInt(max - min + 1) + min);
        combinaisonSecrete.add(rand.nextInt(max - min + 1) + min);
        combinaisonSecrete.add(rand.nextInt(max - min + 1) + min);
        combinaisonSecrete.add(rand.nextInt(max - min + 1) + min);
        System.out.println("La première combinaison de l'ordinateur est : " + combinaisonSecrete);

        String str = new String();
        boolean equ4Egal = false;
        String chaineEgale = "====";

       do  {

            Scanner sc = new Scanner(System.in);
            String nbsaisi = sc.next();
            List<Integer> nouvelleList = extraiList(nbsaisi);

            for (int i = 0; i < combinaisonSecrete.size(); i++) {

                if (combinaisonSecrete.get(i) < nouvelleList.get(i)) {
                    str = str + "+";
                } else if (combinaisonSecrete.get(i) > nouvelleList.get(i)) {
                    str = str + "-";
                } else {
                    str = str + "=";
                }
            }

            System.out.print("Le str est  : " +str);
            System.out.println("");
            System.out.println(nouvelleList);
            equ4Egal = chaineEgale.equals(str);
            System.out.println(equ4Egal);
            str = new String();

        }while (!equ4Egal);
        System.out.println("Félicitation vous avez trouvé la combinaison secrète !!!!");
    }

    /**
     * Signture d'une méthode en Java:
     * 1 Visibilité (Public ou privé ...)
     * 2 Classe ou instance (facultatif)(Static ou pas static)
     * 3 Type de retour (int, double, list, String, propre classe etc)
     * 4 Nom de la méthode ou fonction
     * 5 La liste des paramètres entre parenthèses
     * 6 ouvrir et fermer l'accoclade
     * 7 retourner le type sauf si la méthode est void
     *
     * @param saisie
     * @return
     */
    private static List<Integer> extraiList(String saisie) {
        List<Integer> nouvelleList = new ArrayList<Integer>();
        for (char charact : saisie.toCharArray()) {
            String chainChar = String.valueOf(charact);
            Integer chiffre = Integer.valueOf(chainChar);
            nouvelleList.add(chiffre);
        }
        System.out.println("Affichage de la nouvelle liste de chiffre : " + nouvelleList);
        return nouvelleList;
    }


    /**
     * @param combinaisonSecrete
     * @param nouvelleList
     * @return
     */
    private static List<String> comparaison(List<Integer> combinaisonSecrete, List<Integer> nouvelleList) {
        List<String> resultat = new ArrayList<String>();

        if (combinaisonSecrete.equals(nouvelleList)) {
            resultat.add("=");
            resultat.add("=");
            resultat.add("=");
            resultat.add("=");

        } else {


            for (int i = 0; i < combinaisonSecrete.size(); i++) {
                for (int j = 0; i < nouvelleList.size(); j++) {

                    if (combinaisonSecrete.get(i) > nouvelleList.get(j)) {
                        resultat.add("+");
                    } else if (combinaisonSecrete.get(i) < nouvelleList.get(j)) {
                        resultat.add("-");

                    } else {
                        resultat.add("=");
                    }


                }

            }


        }
        return resultat;
    }
}




import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        /**
         * création d'une liste d'entier avec la collection d'arraylist
         */
        List<Integer> combinaisonSecrete = new ArrayList<Integer>();
        combinaisonSecrete.add(2);
        combinaisonSecrete.add(8);
        combinaisonSecrete.add(6);
        combinaisonSecrete.add(1);
        System.out.println(combinaisonSecrete);

        boolean condition = true;

        while (condition == true) {

            System.out.println("Veuillez saisir votre combinaison à 4 chiffres");
            Scanner sc = new Scanner(System.in);
            String nbsaisi = sc.next();
            List<Integer> nouvelleList = extraiList(nbsaisi);
            String resultat = comparaison(combinaisonSecrete, nouvelleList);
            String bonResultat = "====";


            if (bonResultat.equals(resultat)) {
                condition = true;
                System.out.println("Bravo !!! Vous avez trouvez la combinaison : " + resultat);
            } else {
                System.out.println("Dommage vous n'avez pas trouvé le bon réultat " + resultat);
            }

        }
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
    private static String comparaison(List<Integer> combinaisonSecrete, List<Integer> nouvelleList) {
        String resultat = "";

        for (int i = 0; i < combinaisonSecrete.size(); i++) {
            for (int j = 0; i < combinaisonSecrete.size(); j++) {

                if (combinaisonSecrete.get(i) == nouvelleList.get(j)) {
                    resultat = "=";
                    return resultat;
                } else if (combinaisonSecrete.get(i) < nouvelleList.get(j)) {
                    resultat = "-";
                    return resultat;

                } else if (combinaisonSecrete.get(i) > nouvelleList.get(j)) {
                    resultat = "+";
                    return resultat;

                }

            }
        }
        return resultat;
    }
}

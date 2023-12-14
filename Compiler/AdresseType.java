import java.util.*;

public  class AdresseType {

    public static int getSize( String t )
    {
        //array a été rajouté pour savoir si une variable est un tableau ou non
        //C'est utile lorsqu'un tableau est passé en argument à une fonction. On saura alors qu'il
        //faut passer l'adresse du tableau à la fonction et pas la valeur de la variable
        if (t.equals("int") || t.equals("bool") || t.equals("arrayint") || t.equals("arraybool"))  return 1;
        if (t.equals("double") || t.equals("arraydouble")) return 2;
        System.err.println("Erreur type "+ t + " non défini");
        return 0;
    }

    public final int adresse;
    public final String type;

    public AdresseType( int a, String t)
    {
        this.adresse = a; // adress of initial cell on the stack to store the variable
        this.type = t;    // type of the variable
    }
}

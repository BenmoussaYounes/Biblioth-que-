/** Benmoussa Younes  G02 */
import java.util.Scanner;
class Main {
    public static void main(String[] Args) {
        // Main Class l execution de Code commence ici
        System.out.println("System De Gestion De Bibliotheque :");
        System.out.print("Se Connecter : \n 1 --> Adherents \n 2 --> Bibliothecaires \n --> ");
        int A = new Scanner(System.in).nextInt();
        if (A == 1) {
            //Apelle Aux constructor Adherents
            new Adherents();//Objet Anonymme
        }else if (A==2) {
            //Apelle Constructor Bibliothecaires
            new Bibliothecaires();//Objet Anonyme
        }
    }
}

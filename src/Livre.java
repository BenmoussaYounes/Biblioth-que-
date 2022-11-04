/**Benmoussa Younes G02  */
import java.sql.*;
import java.util.Scanner;
public class Livre
{
    // Attributs -Variable locale -
    String Titre;
    String Editeur;
    String Auteur;
    String Annedepub;
    int Ref;
    int Dispo;
    // Methods
     public void  listedelivre(){ /** Affichage de livre disponible pour l emprunt   */
     try {
         Connection c = DriverManager.getConnection(
                 "jdbc:mysql://localhost/biblitheque?serverTimezone=UTC",
                 "younes",
                 "younesSQL");
         Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
         ResultSet Livre = s.executeQuery("SELECT `Titre`, `Editeur`, `Auteur`, `AnneeDePub`, `Ref`, `Dispo` FROM `livre`");
         int i=1;
         while(Livre.next()) {
             /**
              * Dispo = 0 Livre pas disponilbe ( il est deja emprunte )
              * Dispo = 1 Livre disponible pour l emprunt
              */
             Dispo = Livre.getInt("Dispo");
             if (Dispo==1) {
                 System.out.println("Les Livres Disponible : ");
                 System.out.println("---------------------------------");
                 System.out.println("  -Livre-  "+i);
                 Titre = Livre.getString("Titre");
                 Editeur = Livre.getString("Editeur");
                 Auteur = Livre.getString("Auteur");
                 Annedepub = Livre.getString("AnneeDePub");
                 Ref = Livre.getInt("Ref");
                 System.out.println("Titre : " + Titre);
                 System.out.println("Auteur : " + Auteur);
                 System.out.println("Editeur : " + Editeur);
                 System.out.println("AnnesDePub : " + Annedepub);
                 System.out.println("Ref : " + Ref);
                 i++;
                 System.out.println("---------------------------------");
             }
         }
     }catch(SQLException e){
         e.printStackTrace();
     }

 }
     public void DateDeRemis(){

}
}


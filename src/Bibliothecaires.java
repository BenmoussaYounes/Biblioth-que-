/** Benmoussa Younes G02 */
import java.sql.Connection; // SQL PACKAGE-Importation de la Class Connection
import java.sql.DriverManager; // SQL PACKAGE-Importaion de la class DriverManager
import java.sql.Statement; // SQL PACKAGE-Imortation de la clas Statement
import java.util.Scanner; // Importation de la class Scanner
import java.sql.ResultSet; //SQL PACKAGE-Imortation de la clas Statement
import java.sql.SQLException; //SQL PACKAGE-Imortation de la clas Statement
public class Bibliothecaires {
    Bibliothecaires(){ /** Constructor Bibliothecaires  */
        while(true) {
                    System.out.println("Choisie une Option : \n" +
                            "1 --> Ajouter Un Nouveau Adherents \n" +
                            "2 --> Resitution Des Livre \n" +
                            "3 --> Ajouter Un Nouveau Livre"
                    );
                    System.out.print("-->");
                    int op = new Scanner(System.in).nextInt();
                    switch (op) {
                        case 1: {
                            //Apelle au methode qui permet d adjoute des nouveau adherents
                          AjouteAdh();
                            break;
                        }
                        case 2: {
                            //Apelle au methode qui permet la Resitution des livres
                              RestitutionDL();
                            break;
                        }
                        case 3:
                            //Apelle au methode qui permet la ajoute de nouveau livre
                            AjouterDL();
                            break;
                        default:
                            System.out.println("Error");
                    }
                    break;
                }
        }
        // Methods
        public void RestitutionDL() {
        try {
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost/biblitheque?serverTimezone=UTC",
                    "younes",
                    "younesSQL");
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            Statement s2= c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            //Creation de l objet livre qui permet de manupile les donne de la base de donne livre
            ResultSet livre = s.executeQuery("SELECT `Titre`, `Editeur`, `Auteur`, `AnneeDePub`, `Ref`, `Dispo`, `Idad` FROM `livre`");
            //Creation de l objet Adherent qui permet de manupile les donne de la base de donne Adherent
            ResultSet Adherent = s2.executeQuery("SELECT `Nom`, `Prenom`, `Adresse`, `Id`, `Dispo` FROM `adherents`");
            System.out.println("Donner la refference de livre Svp : ");
            int R = new Scanner(System.in).nextInt();
            int n=0;
            while(livre.next()){
                if(R==livre.getInt("Ref")){
                    n=livre.getInt("Idad");
                    livre.updateInt("Dispo",1);
                    livre.updateRow();
                    break;
                }
            }
            while(Adherent.next()){
                if(n==Adherent.getInt("Id")){
                    int j=Adherent.getInt("Dispo");
                    j--;
                    Adherent.updateInt("Dispo",j);
                    Adherent.updateRow();
                    break;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }


        }
        public void AjouterDL() {
            try {
                Connection c = DriverManager.getConnection(
                        "jdbc:mysql://localhost/biblitheque?serverTimezone=UTC",
                        "younes",
                        "younesSQL");
                Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                //Les Information de nouveau lire a ajouter
                System.out.println("Entrer Les Information De Nouveau Livres :");
                System.out.print("Titre : ");
                String Titre = new Scanner(System.in).nextLine();
                System.out.print("Editeur : ");
                String Editeur = new Scanner(System.in).nextLine();
                System.out.print("Auteur : ");
                String Auteur = new Scanner(System.in).nextLine();
                System.out.print("AnnesDePub : ");
                String AnnesDePub = new Scanner(System.in).nextLine();
                System.out.print("Ref : ");
                int Ref = new Scanner(System.in).nextInt();
                System.out.println("Done");
                ResultSet Livre = s.executeQuery("SELECT `Titre`, `Editeur`, `Auteur`, `AnneeDePub`, `Ref` FROM `livre` ");
                //Creation de l objet livre qui permet de manupile les donne de la base de donne livre
                Livre.moveToInsertRow();
                Livre.updateString("Titre", Titre);
                Livre.updateString("Auteur", Auteur);
                Livre.updateString("Editeur", Editeur);
                Livre.updateString("AnneeDePub", AnnesDePub);
                Livre.updateInt("Ref", Ref);
                Livre.insertRow();//Ajoute de la nouveau ligne
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        public void AjouteAdh(){
        try {
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost/biblitheque?serverTimezone=UTC",
                    "younes",
                    "younesSQL");
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //Creation de l objet Adherent qui permet de manupile les donne de la base de donne adherents
            ResultSet Adherent = s.executeQuery("SELECT `Nom`, `Prenom`, `Adresse`, `Id`, `Dispo` FROM `adherents`");
            //Information de nouveau Adherents
            System.out.println("Entrer Les Information De Nouveau Adherents :");
            System.out.print("Nome : ");
            String nom = new Scanner(System.in).nextLine();
            System.out.print("Prenom : ");
            String prenom = new Scanner(System.in).nextLine();
            System.out.print("Adress : ");
            String Adress = new Scanner(System.in).nextLine();
            //Mouvement au dernier ligne
            Adherent.last();
            int Id=Adherent.getInt("Id");//Creation d un nouveau Id a partire de dernier exisistant sur la BDD
            Id++;//Id du nouveau Adherent = Id du dernier adherent  +1
            Adherent.moveToInsertRow();
            Adherent.updateString("Nom", nom);
            Adherent.updateString("Prenom", prenom);
            Adherent.updateString("Adresse", Adress);
            Adherent.updateInt("Id", Id);
            Adherent.insertRow();//Ajoute de la nouveau ligne
        }catch(SQLException e){
            e.printStackTrace();
        }
        }
    }


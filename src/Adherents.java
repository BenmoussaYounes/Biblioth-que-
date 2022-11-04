/** Benmoussa younes G02 */
import java.sql.*;
import java.util.Scanner;

public class Adherents {
    // Attributs -Variable locale
    String Nom;
    String Prenom;
    String adress;
    Boolean Access = false;
    int R;
    //Constructor
    Adherents() { /** Constructor Adherents */
        logon();
        if (Access == true) {
            while (true) {
                System.out.print("Choisire une Option : \n 1 --> Demande De Emprunt de livre : \n 2 --> Voire la liste des livre : \n -->");
                int op = new Scanner(System.in).nextInt();
                if (op == 1) {
                    DemandeDeEmprunt();
                    break;
                }
                else if (op == 2) {
                    Livre l=new Livre();
                    l.listedelivre();
                    break;
                }else
                System.out.println("Erreur nombre pas valide ! ");
            }
        }
    }
    //Methods
    public void logon() {//Method qui permet l identification des Adherent
        try {
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost/biblitheque?serverTimezone=UTC",
                    "younes",
                    "younesSQL");
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.print("Donner Votre Nom :\n -->");
            Nom = new Scanner(System.in).nextLine();
            System.out.print("Donner Votre Prenom :\n -->");
            Prenom = new Scanner(System.in).nextLine();
            ResultSet Adherent = s.executeQuery("SELECT `Nom`, `Prenom`, `Adresse`, `Id` FROM `adherents`");
            boolean nom = false;//Nom
            boolean prenom = false;//Prenom
                while (Adherent.next()) {
                /** Identification se fait Avec le nom prenom  */
                nom = Nom.equals(Adherent.getString("Nom"));
                prenom = Prenom.equals(Adherent.getString("Prenom"));
                if (nom == true && prenom == true) {  /**Seulement si le nom et prenom sont Vrai  */
                    System.out.println("-- Acces accepte --\n Binvenue Mr : " + Nom + " " + Prenom);
                    Access = true; // Adherent Reconue et il a access pour effectue d autre fonction
                    R=Adherent.getRow(); // Recupuration de numero de la ligne d Adherent reconue
                    break;

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void DemandeDeProlngation() {

    }
    public void DemandeDeEmprunt() {
            try {
                Connection c = DriverManager.getConnection(
                        "jdbc:mysql://localhost/biblitheque?serverTimezone=UTC",
                        "younes",
                        "younesSQL");
                Statement s1 = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                Statement s2 = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                ResultSet Adherent = s1.executeQuery("SELECT `Nom`, `Prenom`, `Adresse`, `Id`, `Dispo` FROM `adherents`");
                ResultSet Livre = s2.executeQuery("SELECT `Titre`, `Editeur`, `Auteur`, `AnneeDePub`, `Ref`, `Dispo`, `Idad` FROM `livre`");
                int a=0;
                int n=0;
                while(Adherent.next()){
                    if(R==Adherent.getRow()){  /** Recupuration de nombre d emprunt fait deja par Cette Adherent */
                         a=Adherent.getInt("Dispo");
                         n=Adherent.getInt("Id");
                         Adherent.moveToCurrentRow();
                         break;
                            }
                        }
                if(a<5) { /**Verfication si l Adherent na pas depasse le nombre maximal d emprunt -5- */
                    System.out.println("Donner la Refferance de livre : ");
                    int Ref = new Scanner(System.in).nextInt();
                    int i = 0;
                    while (Livre.next()) {
                        if (Ref == Livre.getInt("Ref")) {
                            Livre.updateInt("Dispo", 0);
                            Livre.updateInt("Idad",n);
                            i = Adherent.getInt("Dispo");
                            i++;
                            Adherent.updateInt("Dispo", i);
                            Livre.updateRow();
                            Adherent.updateRow();
                            break;
                        }
                    }
                }else{ /**   Operation Refuse si il a depasse le nombre d eumprunt max   */
                    System.out.println("Desole ! vous avez ateindre le nombre maxium de pret vous devez Retourne un livre , Merci ");
                        }
            } catch (SQLException e) {
                 e.printStackTrace();
            }

        }
    }

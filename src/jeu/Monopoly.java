
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Monopoly {
        
    private int nbMaisons = 32;
    private int nbHotels = 12;
    private Carreau carreaux;
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    public InterfaceJeu interfaceJeu;

    public int getCashJoueur() {
            throw new UnsupportedOperationException();
    }

    public int lancerDes() {
            throw new UnsupportedOperationException();
    }

    public Carreau getNouveauCarreau(int num) {
            throw new UnsupportedOperationException();
    }

    public Joueur getJoueur() {
            throw new UnsupportedOperationException();
    }

    public void possibiliteAchat(Joueur j, Carreau c) {
            throw new UnsupportedOperationException();
    }

    public void ChoixAchat(Joueur j, Carreau c) {
            throw new UnsupportedOperationException();
    }

    public void InfosLoyer(String nomP, int loyer, int nouveauCash) {
            throw new UnsupportedOperationException();
    }
    
    public Monopoly(String dataFilename) {
        buildGamePlateau(dataFilename);
    }

    private void buildGamePlateau(String dataFilename) {
        LinkedList<Carreau> carreaux = new LinkedList<>();
        /*                0    1     2    3        4    5   6   7   8  9    10        11          12       
         * P propr:     type, num, nom, couleur, prix, 0m, 1m, 2m, 3m, 4m, hotel, prixmaison, prixhotel
         * CA argent:   type, num, nom, montant __crédité__
         * CT tirage:   type, num, type tirage
         * G gare :     type, num, nom, prix
         * C compagnie: type, num, nom, prix
         * CM mouvement:type, num, nom 
         */

        try {
            ArrayList<String[]> data = readDataFile(dataFilename, ",");

            //TODO: create cases instead of displaying
            for (int i = 0; i < data.size(); ++i) {
                String typeCase = data.get(i)[0];

                if (typeCase.compareTo("P") == 0) { //Propriétés
                    System.out.println("Propriété :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    ProprieteAConstruire c = new ProprieteAConstruire();
                    c.setNumero(data.get(i)[1]);
                    c.setNomCarreau(data.get(i)[2]);
                    c.setGroupePropriete(Couleur.value(data.get(i)[3]));
                    c.setMontantAchat(Integer.parseInt(data.get(i)[4]));
                    LinkedList<Integer> loyerParMaison = new LinkedList<>();
                    for (int j; j <= 5; ++j) { // for j in 0..5
                        loyerParMaison.add(Integer.parseInt(data.get(i)[j]));
                    }
                    c.setLoyerParMaison(loyerParMaison);
                    c.setPrixMaison(Integer.parseInt(data.get(i)[11]));
                    c.setPrixHotel(Integer.parseInt(data.get(i)[12]));
                }
                else if (typeCase.compareTo("G") == 0) { //Gares
                    System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    Gare c = new Gare();
                    c.setNumero(data.get(i)[1]);
                    c.setNomCarreau(data.get(i)[2]);
                    c.setMontantAchat(Integer.parseInt(data.get(i)[3]));
                    
                }
                else if (typeCase.compareTo("C") == 0) { //Compagnie
                    System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    Compagnie c = new Compagnie();
                    c.setNumero(data.get(i)[1]);
                    c.setNomCarreau(data.get(i)[2]);
                    c.setMontantAchat(Integer.parseInt(data.get(i)[3]));
                }
                else if (typeCase.compareTo("CT") == 0) { // Tirage
                    System.out.println("Case Tirage :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    CarreauTirage c = new CarreauTirage();
                    c.setNumero(data.get(i)[1]);
                    c.setNomCarreau(data.get(i)[2]);
                    c.setTypeTirage(data.get(i)[3]);
                }
                else if (typeCase.compareTo("CA") == 0) { // Argent
                    System.out.println("Case Argent :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    CarreauArgent c = new CarreauArgent();
                    c.setNum(data.get(i)[1]);
                    c.setNomCarreau(data.get(i)[2]);
                    c.setMontant(Integer.parseInt(data.get(i)[3]));
                    
                }
                else if (typeCase.compareTo("CM") == 0) { // Mouvement
                    System.out.println("Case Mouvement :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                } 
                else {
                    System.err.println("[buildGamePleateau()] : Invalid Data type");
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("[buildGamePlateau()] : File is not found!");
        } catch (IOException e) {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
    }
    
    
        public void initialiserPartie() {
        int nbJoueurs;
        Scanner sc = new Scanner(System.in);
        System.out.println("Inscription des joueurs : ");
        System.out.print("Nombre de joueurs : ") ; nbJoueurs = sc.nextInt();
        int i;
        for (i=0 ; i < nbJoueurs ; ++i) {
                    System.out.print("Nom du joueur "+ i+1 +" : ") ; getJoueur(i).setNomJoueur(sc.nextLine()); 
        }
    }
        
    private ArrayList<String[]> readDataFile(String filename, String token) throws FileNotFoundException, IOException {
        ArrayList<String[]> data = new ArrayList<String[]>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = null;
        while ((line = reader.readLine()) != null) {
            data.add(line.split(token));
        }
        reader.close();

        return data;
    }
}

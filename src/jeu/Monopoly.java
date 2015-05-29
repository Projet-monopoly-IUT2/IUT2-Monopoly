package jeu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;

public class Monopoly {

    private int nbMaisons = 32;
    private int nbHotels = 12;
    private LinkedList<Carreau> carreaux;
    private LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
    public InterfaceJeu interfaceJeu;

    public Monopoly(String dataFilename) {
        carreaux = new LinkedList<>();
        buildGamePlateau(dataFilename);
    }
        
    public int getCashJoueur() {
        throw new UnsupportedOperationException();
    }

    public static int lancerDes() throws Double{
        int d1,d2;
        Random r = new Random();
        d1 = r.nextInt(6)+1; // 0 à 5  +1
        d2 = r.nextInt(6)+1;
        if (d1 == d2)
            throw new Double();
        return d1+d2;
    }

    public Carreau getNouveauCarreau(int num) {
        throw new UnsupportedOperationException();
    }

    public Joueur getJoueur(String nomJ) {
        Joueur incognito = new Joueur();
        for (Joueur j : joueurs) {
            if (j.getNomjoueur().equalsIgnoreCase(nomJ)){
                incognito = j;
            }
        }
        return incognito;
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


    public void jouerUnCoup(Joueur j) {
        throw new UnsupportedOperationException();
    }

    public void lancerDesAvancer() {
        throw new UnsupportedOperationException();
    }

    public void GetNbMaisons() {
        throw new UnsupportedOperationException();
    }

    private void buildGamePlateau(String dataFilename) {
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

            for (int i = 0; i < data.size(); ++i) {
                String typeCase = data.get(i)[0];
                // data.get(i)[j] : récupère le jème champ de texte de la ième ligne 
                if (typeCase.compareTo("P") == 0) { //Propriétés
                    System.out.println("Propriété :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    ProprieteAConstruire c = new ProprieteAConstruire();
                    c.setNumero(Integer.parseInt(data.get(i)[1]));
                    c.setNomCarreau(data.get(i)[2]);
                    c.setGroupe(data.get(i)[3]);
                    c.setMontantAchat(Integer.parseInt(data.get(i)[4]));
                    LinkedList<Integer> loyerParMaison = new LinkedList<>();
                    for (int j=0; j <= 5; ++j) // for j in 0..5
                        loyerParMaison.add(Integer.parseInt(data.get(i)[j]));
                    c.setLoyerParMaison(loyerParMaison);
                    c.setPrixMaison(Integer.parseInt(data.get(i)[11]));
                    c.setPrixHotel(Integer.parseInt(data.get(i)[12]));
                    this.carreaux.add(c);
                } else if (typeCase.compareTo("G") == 0) { //Gares
                    System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    Gare c = new Gare();
                    c.setNumero(Integer.parseInt(data.get(i)[1]));
                    c.setNomCarreau(data.get(i)[2]);
                    c.setMontantAchat(Integer.parseInt(data.get(i)[3]));
                    this.carreaux.add(c);
                } else if (typeCase.compareTo("C") == 0) { //Compagnie
                    System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    Compagnie c = new Compagnie();
                    c.setNumero(Integer.parseInt(data.get(i)[1]));
                    c.setNomCarreau(data.get(i)[2]);
                    c.setMontantAchat(Integer.parseInt(data.get(i)[3]));
                    this.carreaux.add(c);
                } else if (typeCase.compareTo("CT") == 0) { // Tirage
                    System.out.println("Case Tirage :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    CarreauTirage c = new CarreauTirage();
                    c.setNumero(Integer.parseInt(data.get(i)[1]));
                    c.setNomCarreau(data.get(i)[2]);
                    c.setTypeTirage(data.get(i)[3]);
                    this.carreaux.add(c);
                } else if (typeCase.compareTo("CA") == 0) { // Argent
                    System.out.println("Case Argent :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    CarreauArgent c = new CarreauArgent();
                    c.setNumero(Integer.parseInt(data.get(i)[1]));
                    c.setNomCarreau(data.get(i)[2]);
                    c.setMontant(Integer.parseInt(data.get(i)[3]));
                    this.carreaux.add(c);
                } else if (typeCase.compareTo("CM") == 0) { // Mouvement
                    System.out.println("Case Mouvement :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                } else {
                    System.err.println("[buildGamePleateau()] : Invalid Data type");
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("[buildGamePlateau()] : File is not found!");
        } catch (IOException e) {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
    }
    
        /**
         * Demande à l'utilisateur d'entrer le nom des joueurs :
         *      - A chaque entrée, le joueur est créé avec le nom fournit par l'Utilisateur
         *      - Le nouveau joueur est placé sur la case départ.
         */
    
    
    
        public void initialiserPartie() {
        int nbJoueurs;
        Scanner sc = new Scanner(System.in);
        System.out.println("Inscription des joueurs : ");
        System.out.print("Nombre de joueurs (2-6) : ");
        nbJoueurs = sc.nextInt();
        int i;
        for (i = 0; i < nbJoueurs; ++i) {
            joueurs.add(new Joueur());
            Joueur j = joueurs.get(i); // récupération du dernier joueur (que l'on vient d'ajouter)
            System.out.print("Nom du joueur " + i + 1 + " : ");
            String nj = sc.nextLine();
            j.setNomJoueur(nj);
            j.setCarreau(carreaux.peekFirst()); //Placement sur le 1er carreau (case départ)
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

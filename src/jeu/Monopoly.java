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
    private HashMap<Integer, Carreau> carreaux;
    private LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
    public InterfaceJeu interfaceJeu = new InterfaceJeu();
    

    public Monopoly(String dataFilename) {
        carreaux = new HashMap<>();
        buildGamePlateau(dataFilename);

    }

    public LinkedList<Joueur> getJoueurs() {
        return joueurs;
    }
    
    

    public int getCashJoueur(Joueur j) {
        return j.getCash();
    }

    public Carreau getCarreau(int num) {
        return carreaux.get(num);
    }

    public static ResultatDes lancerDes() {
        Random rand = new Random();
        ResultatDes res = new ResultatDes();
        int d1 = rand.nextInt(6) + 1; // 0 à 5  +1
        int d2 = rand.nextInt(6) + 1;
        res.setRes(d1 + d2);
        if (d1 == d2) {
            res.setDble(true);
        } else {
            res.setDble(false);
        }
        return res;
    }

    public Joueur getJoueur(String nomJ) {
        Joueur incognito = new Joueur(this);
        for (Joueur j : joueurs) {
            if (j.getNomjoueur().equalsIgnoreCase(nomJ)) {
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
        lancerDesAvancer(j);
        getCarreau(j.getPositionCourante()).action(j);
    }

    /**
      * @param j joueur courant
      * @return Vrai si le lancer est un double, faux sinon.
      */
    public boolean lancerDesAvancer(Joueur j) {
        ResultatDes nb;
        nb = lancerDes();
        int position = j.getPositionCourante();
        int caseCible = (position+nb.getRes())%41;
            if (caseCible < j.getPositionCourante() && !(caseCible == 1))
                //Si la n° de case après le déplacement est < à celui avant, on est passé par la case départ. La cas ou l'on tombe directement sur la case départ est déjà géré.
                j.ajouterCash(200);
            j.deplacer(caseCible);

        interfaceJeu.afficherJoueur(j);
        interfaceJeu.afficherResDEs(nb.getRes());

        for (Joueur js : joueurs) {
            System.out.println("Etat de tous les joueurs : ");
            interfaceJeu.afficherJoueur(js);
            ArrayList<CarreauPropriete> proprietes = js.getProprietes();
            if (proprietes != null) {
                interfaceJeu.afficherProprietes(proprietes);
            }
            
        }
        return nb.isDble();
    }

    public int getNbMaisons() {
        return this.nbMaisons;
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
           
            ArrayList<Groupe> groupes = new ArrayList<>();
                for (CouleurPropriete c: CouleurPropriete.values())
                    groupes.add(new Groupe(c));
                
            for (int i = 0; i < data.size(); ++i) {
                String typeCase = data.get(i)[0];
                // data.get(i)[j] : récupère le jème champ de texte de la ième ligne 
                if (typeCase.compareTo("P") == 0) { //Propriétés
                    System.out.println("Propriété :\t" + data.get(i)[2] +  "\t@ case " + data.get(i)[1]);
                    ProprieteAConstruire c = new ProprieteAConstruire(this);
                    c.setNumero(Integer.parseInt(data.get(i)[1]));
                    c.setNomCarreau(data.get(i)[2]);
                    c.setGroupe(getGroupe(CouleurPropriete.valueOf(data.get(i)[3]),groupes));
                    c.setMontantAchat(Integer.parseInt(data.get(i)[4]));
                    LinkedList<Integer> loyerParMaison = new LinkedList<>();
                    for (int j = 0; j <= 5; ++j) // for j in 0..5
                    {
                        loyerParMaison.add(Integer.parseInt(data.get(i)[j+5]));
                    }
                    c.setLoyerParMaison(loyerParMaison);
                    c.setPrixMaison(Integer.parseInt(data.get(i)[11]));
                    c.setPrixHotel(Integer.parseInt(data.get(i)[12]));
                    this.carreaux.put(Integer.parseInt(data.get(i)[1]), c);
                } else if (typeCase.compareTo("G") == 0) { //Gares
                    System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    Gare c = new Gare(this);
                    c.setNumero(Integer.parseInt(data.get(i)[1]));
                    c.setNomCarreau(data.get(i)[2]);
                    c.setMontantAchat(Integer.parseInt(data.get(i)[3]));
                    this.carreaux.put(Integer.parseInt(data.get(i)[1]), c);
                } else if (typeCase.compareTo("C") == 0) { //Compagnie
                    System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    Compagnie c = new Compagnie(this);
                    c.setNumero(Integer.parseInt(data.get(i)[1]));
                    c.setNomCarreau(data.get(i)[2]);
                    c.setMontantAchat(Integer.parseInt(data.get(i)[3]));
                    this.carreaux.put(Integer.parseInt(data.get(i)[1]), c);
                } else if (typeCase.compareTo("CT") == 0) { // Tirage
                    System.out.println("Case Tirage :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    CarreauTirage c = new CarreauTirage(this);
                    c.setNomCarreau(data.get(i)[2]);
                    c.setNumero(Integer.parseInt(data.get(i)[1]));
                    c.setTypeTirage(data.get(i)[2]);
                    this.carreaux.put(Integer.parseInt(data.get(i)[1]), c);
                } else if (typeCase.compareTo("CA") == 0) { // Argent
                    System.out.println("Case Argent :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    CarreauArgent c = new CarreauArgent(this);
                    c.setNumero(Integer.parseInt(data.get(i)[1]));
                    c.setNomCarreau(data.get(i)[2]);
                    c.setMontant(Integer.parseInt(data.get(i)[3]));
                    this.carreaux.put(Integer.parseInt(data.get(i)[1]), c);
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
     * Fonction annexe à buildGamePlateau
     * @param coul couleur du groupe recherché
     * @param grpes liste de groupes
     * @return groupe dont la couleur est coul
     */
    private Groupe getGroupe(CouleurPropriete coul, ArrayList<Groupe> grpes) {
        Groupe groupeCible = null;
        for (Groupe g: grpes) {
            if (g.getCouleur() == coul)
                groupeCible = g;
        }
        return groupeCible;
    }
    /**
     * Demande à l'utilisateur d'entrer le nom des joueurs : - A chaque entrée,
     * le joueur est créé avec le nom fournit par l'Utilisateur - Le nouveau
     * joueur est placé sur la case départ.
     */
    public void initialiserPartie() {
        int nbJoueurs;
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        
        System.out.println("Inscription des joueurs : ");
        System.out.print("Nombre de joueurs (2-6) : ");
        nbJoueurs = sc.nextInt();
        while (nbJoueurs < 2 || nbJoueurs > 6) {
            System.out.println("Entrez un nombre entre 2 et 6 : ");
            nbJoueurs = sc.nextInt();
        }

        for (int i = 1; i <= nbJoueurs; i++) {
            System.out.print("Nom du joueur " + i+ " : ");
            String nj = sc2.nextLine();
            Joueur j = new Joueur(this);
            j.setNomJoueur(nj);
            j.setCarreau(carreaux.get(1));
            joueurs.add(j);
            //Placement sur le 1er carreau (case départ)
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

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
    private int resultatDes;
    private HashMap<Integer, Carreau> carreaux;
    private LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
    private Joueur jCourant;
    public InterfaceJeu interfaceJeu = new InterfaceJeu(this);
    

    public Monopoly(String dataFilename) {
        carreaux = new HashMap<>();
        buildGamePlateau(dataFilename);

    }

    public LinkedList<Joueur> getJoueurs() {
        return joueurs;
    }
    
    
    /**
     * Retourne le solde d'un joueur
     * @param j joueur
     * @return montant du solde du joueur
     */
    public int getCashJoueur(Joueur j) {
        return j.getCash();
    }
    
    /**
     * Récupère un carreau selon son numéro
     * @param num numéro du carreau à récupérer
     * @return carreau où numCarreau = num
     */
    public Carreau getCarreau(int num) {
        return carreaux.get(num);
    }

    /**
     * Lance les dés et renvoie une structure contenant le résultat et 
     * un booléen valant vrai si le lencer est un double
     * @return résultat du lancer
     */
    public static ResultatDes lancerDes() {
        Random rand = new Random();
        ResultatDes res = new ResultatDes();
        int d1 = rand.nextInt(6) + 1; // 0 à 5  +1
        int d2 = rand.nextInt(6) + 1;
        d1 = d2;
        res.setRes(d1 + d2);
        if (d1 == d2) {
            res.setDble(true);
        } else {
            res.setDble(false);
        }
        return res;
    }

    /**
     * Renvoie un joueur dont le nom est nomJ
     * @param nomJ nom du joueur à retourner
     * @return joueur où nom = nomJ
     */
    public Joueur getJoueur(String nomJ) {
        Joueur incognito = new Joueur(this);
        for (Joueur j : joueurs) {
            if (j.getNomJoueur().equalsIgnoreCase(nomJ)) {
                incognito = j;
            }
        }
        return incognito;
    }
    
    /**
     * Récupère le numJème joueur 
     * @param numJ numéro du joueur à retourner
     * @return joueur où numero = numJ
     */
    public Joueur getJoueur(int numJ) {
        return joueurs.get(numJ);
    }

    /**
     *  ???
     * @param j
     * @param c 
     */
    // prise de décision ? vérification si possibilité d'achat ? éxécution de l'achat ? les 3 ? Remplir la doc svp
    public void possibiliteAchat(Joueur j, CarreauPropriete c) {
        interfaceJeu.afficherAchat(c, j);
        if (interfaceJeu.ChoixAchat(j, c)==1){
            c.setProprietaire(j);            
           if (c instanceof Gare) {
               j.setPropriete(c);
               c.setProprietaire(j);
           } else if (c instanceof Compagnie) {
               j.setPropriete(c);
               c.setProprietaire(j);
           } else if (c instanceof ProprieteAConstruire) {
               j.setPropriete(c);
               c.setProprietaire(j);
           }
           j.retirerCash(c.getMontantAchat());
            System.out.println("Vous venez d'acheter cette propriété, bravo !");
        }
        else {
            System.out.println("Vous n'avez pas acheté cette propriete");
        }
    }

    /**
     * Procède à l'affichage d'un paiement de loyer
     * @param jproprio propriétaire du carreau
     * @param loyer montant du loyer à payer
     * @param nouveauCash solde après paiement
     */
    // Afficher joueur qui paie ?
    public void InfosLoyer(Joueur jproprio, int loyer, int nouveauCash) {
       interfaceJeu.AfficherLoyer(jproprio, loyer, nouveauCash);
    }

    /**
     * Fait avancer le joueur et effectue l'action correspondante à la case sur laquelle il se trouve.
     * Rejoue si le joueur a fait un double.
     * @param j joueur courant
     */
    public void jouerUnCoup(Joueur j) {

        int i = 0;
        System.out.println("Tour du joueur : ");
        interfaceJeu.afficherJoueur(j);

        boolean rejouer = true;
        while (rejouer && i <= 2) {
            if (!j.isEnPrison()) {
                rejouer = lancerDesAvancer(j);
                getCarreau(j.getPositionCourante()).action(j);
                i++;
            } else {
                rejouer = jouerPrison(j);
            }
            
            
        }
        if (i == 3) {
            ResultatDes nb = lancerDes();
            if (nb.isDble() ) {
                
                j.setEnPrison(true);
                interfaceJeu.EstPrisonPourDouble(j);
                j.deplacer(11);
            }
            else {
                lancerDesAvancer(j, nb);
            }
            

        }
       
    }
    
    public boolean jouerPrison(Joueur j) {
        if (j.isCarteSortiePrison() && interfaceJeu.utiliserCarteSortiePrison()) {
            j.setEnPrison(false);
            return true;
        } else {
            j.addToursEnPrison(1);
            ResultatDes lancer = lancerDes();
            if (lancer.isDble()) {
                j.setEnPrison(false);
                lancerDesAvancer(j, lancer);
                return true;
            }
            else if (j.getToursEnPrison() >= 3) {
                j.setEnPrison(false);
                j.retirerCash(50);
                lancerDesAvancer(j,lancer);
                return false;
            }
        }
        return false; //placeholder
    }
            
    /**
     * Fait avancer le joueur selon un lancer de dés aléatoire.
     * @param j joueur courant
     * @return vrai si le lance de dés est un double.
     */
    public boolean lancerDesAvancer(Joueur j) {
        ResultatDes nb = lancerDes();
        return lancerDesAvancer(j, nb);
    }
    
    /**
     * Fait avancer le joueur selon un lancer de dés passé en paramètre.
     * @param j joueur courant
     * @param nb lancer de dés à utiliser
     * @return Vrai si le lancer est un double, faux sinon.
     */
    private boolean lancerDesAvancer(Joueur j, ResultatDes nb) { 
        int position = j.getPositionCourante();
        int caseCible = (position+nb.getRes())%41;
            if (caseCible < position && !(caseCible == 1))
                //Si la n° de case après le déplacement est < à celui avant, on est passé par la case départ. Le cas ou l'on tombe directement sur la case départ est déjà géré.
                j.ajouterCash(200);
            j.deplacer(caseCible);
            
        interfaceJeu.afficherResDes(nb);
        interfaceJeu.afficherJoueur(j);
        interfaceJeu.afficherEtatJoueurs(joueurs);

     
        return nb.isDble();
    }
    
    /**
     * Testing prurposes only. Déplace le joueur sans déclencher d'action.
     * @param j joueur a déplacer
     * @param numeroCarreau numéro de la case cible
     */
    public void forcerDeplacement(Joueur j, int numeroCarreau) {
        j.deplacer(numeroCarreau); //pour faire les test
        ResultatDes nb;
        nb = lancerDes();
        resultatDes = nb.getRes();
        
        interfaceJeu.afficherJoueur(j);
        interfaceJeu.afficherEtatJoueurs(joueurs);
    }

    public int getResultatDes() {
        return resultatDes;
    }
    
    
    
    public int getNbMaisons() {
        return this.nbMaisons;
    }
    
    /**
     * Initialise le plateau de jeu en fonction du contenu du fichier de données.
     * Le fichier de données contient le type de chaque carreau et ses attributs correcpondants.
     * @param dataFilename chemin du dossier de données
     */
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
                   
                    ProprieteAConstruire c = new ProprieteAConstruire(this);
                    c.setNumero(Integer.parseInt(data.get(i)[1]));
                    c.setNomCarreau(data.get(i)[2]);
                    c.setGroupe(getGroupe(CouleurPropriete.valueOf(data.get(i)[3]),groupes));
                    
//                    System.out.println("Propriété :\t" + data.get(i)[2] +  "\t@ case " + data.get(i)[1] + "\t@ Groupe " + c.getGroupePropriete().getCouleur().toString());
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
//                    System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    Gare c = new Gare(this);
                    c.setNumero(Integer.parseInt(data.get(i)[1]));
                    c.setNomCarreau(data.get(i)[2]);
                    c.setMontantAchat(Integer.parseInt(data.get(i)[3]));
                    this.carreaux.put(Integer.parseInt(data.get(i)[1]), c);
                } else if (typeCase.compareTo("C") == 0) { //Compagnie
//                    System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    Compagnie c = new Compagnie(this);
                    c.setNumero(Integer.parseInt(data.get(i)[1]));
                    c.setNomCarreau(data.get(i)[2]);
                    c.setMontantAchat(Integer.parseInt(data.get(i)[3]));
                    this.carreaux.put(Integer.parseInt(data.get(i)[1]), c);
                } else if (typeCase.compareTo("CT") == 0) { // Tirage
//                    System.out.println("Case Tirage :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    CarreauTirage c = new CarreauTirage(this);
                    c.setNomCarreau(data.get(i)[2]);
                    c.setNumero(Integer.parseInt(data.get(i)[1]));
                    c.setTypeTirage(data.get(i)[2]);
                    this.carreaux.put(Integer.parseInt(data.get(i)[1]), c);
                } else if (typeCase.compareTo("CA") == 0) { // Argent
//                    System.out.println("Case Argent :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    CarreauArgent c = new CarreauArgent(this);
                    c.setNumero(Integer.parseInt(data.get(i)[1]));
                    c.setNomCarreau(data.get(i)[2]);
                    c.setMontant(Integer.parseInt(data.get(i)[3]));
                    this.carreaux.put(Integer.parseInt(data.get(i)[1]), c);
                } else if (typeCase.compareTo("CM") == 0) { // Mouvement
//                    System.out.println("Case Mouvement :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
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
     * le joueur est créé avec le nom fourni par l'utilisateur - Le nouveau
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

    public Joueur getJCourant() {
        return jCourant;
    }

    public void setjCourant(Joueur jCourant) {
        this.jCourant = jCourant;
    }
    
    //Snippet, il faudra lui trouver une place plus appropriée. Le main ?
    
    public void jouerPlusieursCoups() {
      
        int compteurTours = 1;
        boolean continuer = true;

        while (continuer) {
            
            //Déterminer le joueur qui va commencer à l'aide d'un lancer de dés - A TESTER
            if (compteurTours == 1) {
                int premierJoueur = 0, lancer = 0, meilleurLancer = 0;
                for (int i = 0 ; i < getJoueurs().size() ; ++i){
                    lancer = lancerDes().getRes();
                    if(lancer > meilleurLancer) {
                        meilleurLancer = lancer;
                        premierJoueur = i;
                    }               
                }
                setjCourant(getJoueur(premierJoueur));
            }
            
            //TODO 
            //il faut penser à tester les doubles
            
            int nbJoueursFaillite = 0;
            for (Joueur j : getJoueurs()) {
                if (j.getCash() <= 0) ++nbJoueursFaillite;
            }
            if (nbJoueursFaillite == getJoueurs().size() -1) {
                continuer = false;
            }
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
    
    private boolean ChoixConstructionEstEquilibre(ArrayList<ProprieteAConstruire> proprietes,Integer NumCarreau) {
        int MinMaison = 4;
        int MinHotel = 1;
        boolean Sortie = false;
        boolean Res = false;
        
        for(ProprieteAConstruire PaC : proprietes){
            if (PaC.getNbMaisonsC()<MinMaison){
                MinMaison=PaC.getNbMaisonsC();
            }
            else if (PaC.getNbHotelsC()<MinHotel && PaC.getNbMaisonsC()==4){
                MinHotel=PaC.getNbHotelsC();
            }
        }
        
        int i = 0;
        while (!Sortie){
            if(proprietes.get(i).getNumero()==NumCarreau && proprietes.get(i).getNbMaisonsC()==MinMaison){
                Res = true;
            }
            else if (proprietes.get(i).getNumero()==NumCarreau && proprietes.get(i).getNbMaisonsC()==4 && proprietes.get(i).getNbHotelsC()==0){
                Res = true;
            }
        }
        
        return Res;
        
    }
    public void setNbMaisons(int nbMaisons) {
        this.nbMaisons = nbMaisons;
    }
    public void setNbHotels(int nbHotels) {
        this.nbHotels = nbHotels;
    }
    public int getNbHotels() {
        return nbHotels;
    }
    public InterfaceJeu getInterfaceJeu() {
        return interfaceJeu;
    }
    
    public ProprieteAConstruire possibiliteConstruire(Joueur j, ArrayList<ProprieteAConstruire> proprietes) {
        boolean sortie = false;
        int numCarreau = 0;
        ProprieteAConstruire c = null;
        while (!sortie){
            if(j.getCash()>=proprietes.get(0).getPrixMaison()){
                numCarreau = interfaceJeu.affichageChoixConstruction(j,proprietes);               
                if(numCarreau !=1 && this.ChoixConstructionEstEquilibre(proprietes,numCarreau)){
                    c = (ProprieteAConstruire)getCarreau(numCarreau);
                        if(this.getNbMaisons()==0){
                            interfaceJeu.MessageErreur(3);
                            c=null;
                            sortie = true; 
                        }
                        if(this.getNbHotels()==0){
                            interfaceJeu.MessageErreur(4);
                            c=null;
                            sortie = true; 
                        }
                        else{
                            sortie = true; 
                        }                  
                    }             
                else if (numCarreau== 1){
                    sortie = true;
                }
                else {
                    interfaceJeu.MessageErreur(1); 
                }
            }
            else {
                interfaceJeu.MessageErreur(2);
            }
        }
        return c;
    }


}

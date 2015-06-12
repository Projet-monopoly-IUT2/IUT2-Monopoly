package jeu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;

public class Monopoly {

    private int nbMaisons = 32;
    private int nbHotels = 12;
    private int resultatDes;
    private HashMap<Integer, Carreau> carreaux = new HashMap<>();
    private LinkedList<Carte> cartesChance = new LinkedList<>();
    private LinkedList<Carte> cartesCommu = new LinkedList<>();
    private LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
    private Joueur jCourant;
    public InterfaceJeu interfaceJeu = new InterfaceJeu(this);
    

    public Monopoly(String dataFilename) {
        carreaux = new HashMap<>();
        buildGamePlateau(dataFilename);
    }

    /**
     * 
     * @return Liste des joueurs 
     */
    public LinkedList<Joueur> getJoueurs() {
        return joueurs;
    }
    
    
    /**
     *
     * @param j joueur
     * @return montant du solde du joueur
     */
    public int getCashJoueur(Joueur j) {
        return j.getCash();
    }
    
    /**
     * 
     * @param num numéro du carreau à récupérer
     * @return carreau où numCarreau = num
     */
    public Carreau getCarreau(int num) {
        return carreaux.get(num);
    }

    /**
     * Lance les dés et renvoie une structure contenant le résultat et 
     * un booléen valant vrai si le lancer est un double
     * @return résultat du lancer
     */
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

    /**
     * 
     * @param nomJ nom du joueur à retourner
     * @return joueur où nom = nomJ
     */
    public Joueur getJoueur(String nomJ) {
        Joueur incognito = null;
        for (Joueur j : joueurs) {
            if (j.getNomJoueur().equalsIgnoreCase(nomJ)) {
                incognito = j;
            }
        }
        return incognito;
    }

    public HashMap<Integer, Carreau> getCarreaux() {
        return carreaux;
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
     * Achète une propriété avec l'accord du joueur, et si celui-ci en a les ressources.
     * @param j Joueur acheteur
     * @param c Propriété à acheter
     */
    // prise de décision ? vérification si possibilité d'achat ? éxécution de l'achat ? les 3 ? Remplir la doc svp
    public void Achat(Joueur j, CarreauPropriete c) {
        if (j.testFaillite(c.getMontantAchat()) == false) {
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
           boolean paiement = j.retirerCash(c.getMontantAchat());
           interfaceJeu.MessageAchat(1);
        }
            else {
           interfaceJeu.MessageAchat(2);
            }
        } else {
           interfaceJeu.MessageErreur(2);
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
     * Sélectionne le premier joueur selon un lancer de dés, éxécute plusieurs tours
     * de jeu jusqu'à la fin de la partie via faillite.
     */
    public void jouerPlusieursCoups() {

        int compteurTours = 1;
        boolean continuer = true;
        int nJoueur;
        int nbJoueursFaillite = 0;

        while (continuer) {

            //Déterminer le joueur qui va commencer à l'aide d'un lancer de dés - 
            if (compteurTours == 1) {
                compteurTours++;
                int premierJoueur = 0, lancer = 0, meilleurLancer = 0;
                for (int i = 1; i <= getJoueurs().size(); i++) {
                    lancer = lancerDes().getRes();
                    if (lancer > meilleurLancer) {
                        meilleurLancer = lancer;
                        premierJoueur = i;
                    }
                    else if (lancer == meilleurLancer) {
                        //A FAIRE pour gérer le cas où deux joueurs font le même meilleur lancer

                    }
                }
                setjCourant(getJoueur(premierJoueur-1));
            }
            
            //Tour de jeu
            try {
                if (jCourant.enFaillite()) {
                    interfaceJeu.jouerFaillite(jCourant);  
                } else if (jCourant.isEnPrison()) {
                    jouerPrison(jCourant);
                } else {
                    jouerUnCoup(jCourant);
                }
            } catch (Faillite f) {
                interfaceJeu.faillite(jCourant);
                jCourant.setFaillite();
            }
            
            compteurTours++;
            
            //Vérifier si il reste plus d'un joueur en non-faillite
            for (Joueur j : getJoueurs()) {
                if (j.enFaillite()) {
                    ++nbJoueursFaillite;
                }
            }
            if (nbJoueursFaillite == getJoueurs().size() - 1) {
                continuer = false;
     
                interfaceJeu.afficherFinJeu(jCourant);
                
                
            }
          
           
        }

    }
    
    
    /**
     * Fait avancer le joueur et effectue l'action correspondante à la case sur laquelle il se trouve.
     * Rejoue si le joueur a fait un double.
     * @param j joueur courant
     * @throws Faillite si un joueur entre en faillite durant son tour de jeu.
     */
    public void jouerUnCoup(Joueur j) throws Faillite {

            

        int i = 1;
        interfaceJeu.afficherJoueur(j);

        boolean rejouer = true;
        while (rejouer && i <= 2) {
            if (!j.isEnPrison()) {
                rejouer = lancerDesAvancer(j);
                getCarreau(j.getPositionCourante()).action(j);
                if (rejouer)i++;
            } else {
                rejouer = jouerPrison(j);
            }
            
            
        }
        if (i == 3) {
            ResultatDes nb = lancerDes();
            resultatDes = nb.getRes();
            if (nb.isDble() ) {
                
                j.setEnPrison(true);
                interfaceJeu.EstPrisonPourDouble(j, nb);
                j.deplacer(11);
            }
            else {
                lancerDesAvancer(j, nb);
            }
            

        }
       
    }
    /**
     * Gère un tour en prison.
     * @param j joueur courant, doit être en prison
     * @throws Faillite si le joueur n'a pas assez d'argent pour sortir.
     * @return vrai si le joueur doit rejouer, faux sinon.
     */
    public boolean jouerPrison(Joueur j) throws Faillite{
        if (j.isCarteSortiePrison() && interfaceJeu.utiliserCarteSortiePrison()) {
            j.setEnPrison(false);
            j.retirerCarteSortiePrison();
            j.setCarteSortiePrison(false);
            return true;
        } else {
            j.addToursEnPrison(1);
            ResultatDes lancer = lancerDes();
            resultatDes = lancer.getRes();
            if (lancer.isDble()) {
                j.setEnPrison(false);
                lancerDesAvancer(j, lancer);
                return true;
            }
            else if (j.getToursEnPrison() >= 3) {
                j.setEnPrison(false);
                boolean paiement = j.retirerCash(50);
                if (!paiement) throw new Faillite();
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
        resultatDes = nb.getRes(); //important pour le calcul loyer
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
        int caseCible = (position+nb.getRes()-1)%40+1;
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
    
    
    /**
     * 
     * @return Nombre de maisons à construire restantes
     */
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
                    CarreauMouvement c = new CarreauMouvement(this);
                    c.setNumero(Integer.parseInt(data.get(i)[1]));
                    c.setNomCarreau(data.get(i)[2]);
                    this.carreaux.put(Integer.parseInt(data.get(i)[1]), c);
                } else {
                    System.err.println("[buildGamePleateau()] : Invalid Data type");
                }
            }
            
        } catch (FileNotFoundException e) {
            System.err.println("[buildGamePlateau()] : File is not found!");
        } catch (IOException e) {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
        
        cartesChance.add(new CarteGain(this, 1, "Vous êtes libérés de prison, cette carte peut être conservée jusqu'à ce que vous l'utilisiez", 0));
        cartesChance.add(new CarteMouvement(this,2,"Reculez de trois cases",-3,true));
        cartesChance.add(new CarteGain(this, 3,"Vous êtes imposé pour des réparations de voirie à raison de : 40€ par maison et 115€ par hôtel.", 40, 115));
        cartesChance.add(new CarteGain(this,4,"Amende pour excès de vitesse : 15€",-15));
        cartesChance.add(new CarteGain(this,5,"Faites des réparations dans toutes vos maisons : versez pour chaque maison 25€ et pour chaque hotel 100€.",25,100)); 
        cartesChance.add(new CarteGain(this,6,"Amende pour ivresse : 20€",-20));
        cartesChance.add(new CarteMouvement(this, 7, "Allez à la case départ", 1, false));
        cartesChance.add(new CarteMouvement(this, 8,"Allez en prison. Allez directement en prison, ne passez pas par la case départ, ne recevez pas 200€.", 11, false));
        cartesChance.add(new CarteMouvement(this, 9, "Rendez-vous à l'avenue Henri-Martin. Si vous passez par la case départ, recevez 200€.", 25, false));
        cartesChance.add(new CarteMouvement(this, 10, "Allez à la gare de Lyon, Si vous passez par la case départ, recevez 200€", 16, false));
        cartesChance.add(new CarteGain(this, 11, "Payez pour frais de scolarité : 150€", -150));
        cartesChance.add(new CarteGain(this, 12, "Vous avez gagné le prix de mots croisés. Recevez 100€", 100));
        cartesChance.add(new CarteGain(this, 13, "La banque vous verse un dividende de 50€", 50));
        cartesChance.add(new CarteMouvement(this, 14, "Rendez-vous rue de la Paix", 40, false));
        cartesChance.add(new CarteGain(this, 15, "Votre immeuble et votre appartement vous rapportent. Vous devez toucher 150€.", 150));
        cartesChance.add(new CarteMouvement(this, 16, "Accédez au Boulevard de la Vilette. Si vous passez par la case départ, recevez 200€.", 12, false));
        
        cartesCommu.add(new CarteGain(this, 1, "Vous êtes libérés de prison, cette carte peut être conservée jusqu'à ce que vous l'utilisiez", 0));        
        cartesCommu.add(new CarteGain(this, 2, "Payez une amende de 10€", -10));
        cartesCommu.add(new CarteGain(this, 3, "C'est votre Anniversaire ! Chaque joueur vous donne 10€",0));
        cartesCommu.add(new CarteGain(this, 4, "Erreur de la Banque en votre faveur, reçevez 200€", 200));
        cartesCommu.add(new CarteMouvement(this, 5, "Retournez à BelleVille", 2, false));
        cartesCommu.add(new CarteGain(this, 6, "Payez la note du médecin : 50€", -50));
        cartesCommu.add(new CarteGain(this, 7, "Les contributions vous remboursent la somme de 20€", 20));
        cartesCommu.add(new CarteGain(this, 8, "Payez à l'hopital 100€", -100));
        cartesCommu.add(new CarteGain(this, 9, "Vous héritez : 100€", 100));
        cartesCommu.add(new CarteMouvement(this, 10, "Allez en Prisons, Ne Passez pas par la case Départ, Ne reçevez pas 200€", 11, false));
        cartesCommu.add(new CarteGain(this, 11, "Payez votre police d'assurance : 50€", -50));
        cartesCommu.add(new CarteGain(this, 12, "La vente de votre stock vous apporte la somme de 50€", 50));
        cartesCommu.add(new CarteMouvement(this, 13, "Aller jusqu'à la case départ", 1, false));
        cartesCommu.add(new CarteGain(this, 14, "Recevez votre interêt sur l'emprunt à 7% : 25€", 25));
        cartesCommu.add(new CarteGain(this, 15, "Reçevez votre revenu annuel : 100€", 100));
        cartesCommu.add(new CarteGain(this, 16, "Vous gagnez le deuxième prix de beauté derrière Emma Watson : reçevez 10€", 10));
        
        Collections.shuffle(cartesChance);
        Collections.shuffle(cartesCommu);
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
     * 
     * @return Liste des cartes Chance
     */
    public LinkedList<Carte> getCartesChance() {
        return cartesChance;
    }

    /**
     * 
     * @return Liste des cartes de communauté. 
     */
    public LinkedList<Carte> getCartesCommu() {
        return cartesCommu;
    }
    
    /**
     * Crée les joueurs et les place sur la case départ.
     */
    public void initialiserPartie() {
       
        int nbJoueurs = this.interfaceJeu.SaisienbJoueurs();
        
       

        for (int i = 1; i <= nbJoueurs; i++) {
            
            String nj = this.interfaceJeu.SaisieNomJ(i);
         //On ajoute un premier joueur
           Joueur j = new Joueur(this);  
            
//            while (NomJoueurDejaExistant) {
//                
//                    for (Joueur joTest : joueurs) {
//                                //on vérifie dans la liste de joueurs que le nom saisi n'est pas déjà pris. 
//                       if (nj == joTest.getNomJoueur()) {
//                           NomJoueurDejaExistant = true;
//
//                       }
//                       else {
//                           NomJoueurDejaExistant = false;
//                           j = new Joueur(this);
//                           j.setNomJoueur(nj);
//                           j.setCarreau(carreaux.get(1)); 
//               //Placement sur le 1er carreau (case départ)
//                           joueurs.add(j);
//                       }
//                    }
//                if (NomJoueurDejaExistant) {
//                    System.out.println("Ce nom existe déjà");
//                    nj = this.interfaceJeu.SaisieNomJ();
//                }
//             }
//           
            j.setNomJoueur(nj);
            j.setCarreau(carreaux.get(1));
            //Placement sur le 1er carreau (case départ)
            joueurs.add(j);

        }
    }

    /**
     * 
     * @return Joueur en train de jouer 
     */
    public Joueur getJCourant() {
        return jCourant;
    }

    /**
     * 
     * @param jCourant Nouveau joueur en train de jouer 
     */
    public void setjCourant(Joueur jCourant) {
        this.jCourant = jCourant;
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
    
    /**
     * Vérifie si toutes les propriété à construire d'un groupe possèdent le même nombre de maisons construites
     * @param proprietes Liste de propriétés à vérifier
     * @param numCarreau 
     * @return vrai si les propriétés ont toutes le même nombre de maisons construites
     */
    private boolean ChoixConstructionEstEquilibre(ArrayList<ProprieteAConstruire> proprietes,Integer numCarreau) {
        int MinMaison = 4;
        int MinHotel = 1;
        boolean Sortie = false;
        boolean res = false;
        
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
            if(proprietes.get(i).getNumero()==numCarreau && proprietes.get(i).getNbMaisonsC()==MinMaison){
                res = true;
            }
            else if (proprietes.get(i).getNumero()==numCarreau && proprietes.get(i).getNbMaisonsC()==4 && proprietes.get(i).getNbHotelsC()==0){
                res = true;
            }
        }
        
        return res;
        
    }
    
    /**
     * 
     * @param nbMaisons Nouveau nombre de maisons à construire restantes 
     */
    public void setNbMaisons(int nbMaisons) {
        this.nbMaisons = nbMaisons;
    }
    
    /**
     * 
     * @param nbHotels Nouveau nombre d'hotels à construire restantes 
     */
    public void setNbHotels(int nbHotels) {
        this.nbHotels = nbHotels;
    }
    
    /**
     * 
     * @return Nombre d'hotels à construire restants 
     */
    public int getNbHotels() {
        return nbHotels;
    }
    
    /**
     * 
     * @return L'interface du jeu
     */
    public InterfaceJeu getInterfaceJeu() {
        return interfaceJeu;
    }
    
    /**
     * Vérifie s'il est possible de construire sur un groupe de cases.
     * @param j Le joueur souhaitant construire
     * @param proprietes Liste de cases où vérifier la constructibilité
     * @return vrai si les propriétés sont constructibles.
     */
   public ProprieteAConstruire possibiliteConstruire(Joueur j, ArrayList<ProprieteAConstruire> proprietes) {
        boolean sortie = false;
        int numCarreau = 0;
        ProprieteAConstruire c = null;
        while (!sortie){
            if (interfaceJeu.MessageConstruction(1)==2){
                if(!j.testFaillite(proprietes.get(1).getPrixMaison())){                
                    numCarreau = interfaceJeu.affichageChoixConstruction(j,proprietes);               
                    if(this.ChoixConstructionEstEquilibre(proprietes,numCarreau)){
                        c = (ProprieteAConstruire)getCarreau(numCarreau);
                        if(this.getNbMaisons()==0){
                            interfaceJeu.MessageErreur(3);
                            c=null;
                        }
                        if(this.getNbHotels()==0){
                            interfaceJeu.MessageErreur(4);
                            c=null;   
                        }
                        sortie = true;                  
                    }
                    else {
                    interfaceJeu.MessageErreur(1); 
                    }
                }                             
                else{                  
                    interfaceJeu.MessageErreur(2);
                    sortie = true;
                }
            }
            else {
                sortie = true;
            }
        }
        return c;
    }

    public Carte tirerCarte(String typeTirage) {
        if (typeTirage.equalsIgnoreCase("Chance")){
            return cartesChance.peek();
        } else {
            return cartesCommu.peek();
        }
        
    }
    /**
     * Remet la carte du dessus de la pile qui vient d'être tirée au dessous de la pile.
     * @param typeTirage 
     */
    public void RemettreCarte(String typeTirage){
        if (typeTirage.equalsIgnoreCase("Chance")){
//            cartesChance.addLast(cartesChance.getFirst());
//            cartesChance.removeFirst();
              cartesChance.offerLast(cartesChance.pollFirst());
        } else {
//            cartesCommu.addLast(cartesCommu.getFirst());
//            cartesCommu.removeFirst();
              cartesCommu.offerLast(cartesCommu.pollFirst());

        }
        
    }
    /**
     * Retire la carte prison après avoir été tirée par le joueur qui souhaite la conserver.
     * @param typeTirage 
     */
    public void retirerCartePrison(String typeTirage){
            removeCartePrison(typeTirage);
    }
    
    private void removeCartePrison(String typeTirage){
        if (typeTirage.equalsIgnoreCase("chance")) {
            cartesChance.removeFirst();
        } else {
            cartesCommu.removeFirst();
        }
    }
    /**
     * Remet dans le bon tas la carte Sortie de Prison après avoir été utilisée par un joueur.
     */
    public void remettreCartePrison(){
        addCartePrison();
    }
    
    private void addCartePrison(){
        boolean presenceCartePrison = false;
        for (Carte c : cartesChance){
            if (c.getNumero() == 1){
                presenceCartePrison = true;
            }
        }
        
        if (presenceCartePrison){
            cartesChance.addLast(new CarteGain(this, 1, "Vous êtes libérés de prison, cette carte peut être conservée jusqu'à ce que vous l'utilisiez", 0));
        } else {
            cartesCommu.addLast(new CarteGain(this, 1, "Vous êtes libérés de prison, cette carte peut être conservée jusqu'à ce que vous l'utilisiez", 0));
        }
    }

}

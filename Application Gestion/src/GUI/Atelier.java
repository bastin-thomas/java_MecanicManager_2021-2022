/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;


import GUI.Aide.APropos;
import Commandes.CommandesLubrifiants;
import Commandes.CommandesPièces;
import Commandes.CommandesPneux;
import GUI.Travail.AtelierTerminer;
import GUI.Travail.AtelierPriseEnCharge;
import GUI.Travail.AtelierHistoriqueTravaux;
import GUI.Travail.AtelierAPrevoir;
import GUI.Commandes.NewCommande;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import FichierLogPackage.FichierLog;
import Container.ContainerClass;
import ClockThread.Clock;
import GUI.Paramètre.ChoixFormatDate;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import network.NetworkBasicClient;
import people.Employe;
import people.Mecanicien;
import people.Personne;
import people.TechnicienExterieur;



/**
 *
 * @author matteoarnone
 */
public class Atelier extends javax.swing.JFrame
{
    /**
     * Creates new form Atelier
     */
    public static final String  FilePath = "SavedEnvironment.ser";
    private ContainerClass Container;
    private Personne User;
    private FichierLog log;
    
    private NetworkBasicClient Lubrifiants;
    private NetworkBasicClient Pièces;
    private NetworkBasicClient Pneux;
    
    private Clock thread;
    
    @SuppressWarnings({"Convert2Lambda", "OverridableMethodCallInConstructor", "ConvertToTryWithResources"})
    public Atelier() {
        initComponents();
        
        log = new FichierLog();
        thread = null;
        
        //Ajout des composants de la bar de menu       
        JMenu para, aide;
        JMenuItem info,debut,apropos, date;
    
        MenuBar.add(Box.createHorizontalGlue());
        para = new JMenu("Paramètres");
        info = new JMenuItem("Infos système");
        date = new JMenuItem("Format Date");
        para.add(info);
        para.add(date);
        
        aide = new JMenu("Aide");
        debut = new JMenuItem("Pour débuter");
        apropos = new JMenuItem("A propos de...");
        
        aide.add(debut);
        aide.addSeparator();
        aide.add(apropos);
        
        MenuBar.add(Box.createHorizontalGlue());
        MenuBar.add(para);
        MenuBar.add(aide);
         
        apropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                    APropos ap = new APropos(null,true);
                    ap.setVisible(true);
                }
        });
        
        
        //Permet de prendre en paramètre la ref vers cette fenètre
        date.addActionListener(new ActionListener() {
            Atelier Parent;

            @Override
            public void actionPerformed(ActionEvent e) {
                ChoixFormatDate date = new ChoixFormatDate(Parent,true); //Lancement de la fenètre lors de l'action performed.
                date.setVisible(true);
            }

            public ActionListener setParams(Atelier parent) {

                this.Parent = parent;

                return this;
            }
        }.setParams((Atelier)this));
        
        
        //Override Croix de fermeture
        this.setDefaultCloseOperation(Atelier.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent arg0) {
                try {
                    SaveContainer();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Atelier.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        });
        
        
        //Chargement de l'état du programme a sa fermeture :
        try {
            //Load Container:
            ObjectInputStream ObjectIN = new ObjectInputStream(new FileInputStream(FilePath));
            
            setContainer((ContainerClass) ObjectIN.readObject());
            ObjectIN.close();
            
        } catch (FileNotFoundException ex) {
            
            //Si il n'y a rien à charger, On met en mémoire ses données par défaut
            ContainerClass tmp = new ContainerClass();
            
            Vector Users = new Vector();
            
            Users.add(new Mecanicien("Moteur","M0001","Wagner","JeanMarc","95 UneRueAuHazard 4042 UneCommune","04777777777"));
            Users.add(new Mecanicien("Mécanique Competition","M0002","Wilvers","Karl","20 UneRueAuHazard 4052 UneCommune","04777777777"));
            Users.add(new Mecanicien("Pneu","M0003","Costa","Corinne","11 UneRueAuHazard 4043 UneCommune","04777777777"));
            Users.add(new Employe("M0006","Hiard","Samuel","7 UneRueAuHazard 4056 UneCommune","04777777777"));

            Users.add(new TechnicienExterieur("M0011","Engels","Valerie"));
            Users.add(new TechnicienExterieur("M0012","Thiernesse","Cédric"));
            Users.add(new TechnicienExterieur("M001Server.setEndReceiving();3","Vanstapel","Herman"));
            tmp.setUsers(Users);
            
            //Etat Par Defaut
            tmp.setEtatFormat(null);          
            //Format par Defaut
            tmp.setDateformat(new SimpleDateFormat("EEEE dd, MMMM yyyy" + " | " + "HH:mm", Locale.FRENCH));
            
            setContainer(tmp);
        } catch (IOException | ClassNotFoundException ex) {
            Log().PrintLN("ApplicationCentrale", Atelier.class.getName() + " " + Level.SEVERE + ": " + ex);
        }
        Log().PrintLN("Atelier","Chargement de l'état du programme Atelier");
        
        //Lancement des Serveurs:
        this.Lubrifiants = CommandesLubrifiants.CreateClient();
        this.Pièces = CommandesPièces.CreateClient();
        this.Pneux = CommandesPneux.CreateClient();
        
        this.StartClock();
        
        RefreshUI();
    }
    
    //Permet de Sauvegarder l'état du conteneur, contenant toutes les infos du système.
    @SuppressWarnings("ConvertToTryWithResources")
    public void SaveContainer() throws FileNotFoundException{
        try {
            ObjectOutputStream ObjectOUT = new ObjectOutputStream(new FileOutputStream(FilePath));
            ObjectOUT.writeObject(getContainer());
            ObjectOUT.close();
        } catch (IOException ex) {
            Logger.getLogger(Atelier.class.getName()).log(Level.SEVERE, null, ex);
        }      
        Log().PrintLN("Atelier","Sauvegarde de l'état du programme Atelier");
    }
    
    public void StartClock(){
        //Creation du thread
        if(thread == null) thread = new Clock(this);
        
        //Si le Thread est déjà lancer
        if(!thread.isAlive()){
            thread.start();
        }
    }
    
    public void RefreshUI(){
        setVisible(false);
        
        if(Container.IsPont1Free()){
            TextField_Pont1.setText("- Vide -");
            TextField_Pont1.setBackground(new java.awt.Color(0,102,204,75));
            TextField_Pont1.setOpaque(true);
        }
        else{
            TextField_Pont1.setText(getContainer().getPont1().toString());
            TextField_Pont1.setBackground(new java.awt.Color(245,206,66,75));
            TextField_Pont1.setOpaque(true);
        }
            
        if(Container.IsPont2Free()){
            TextField_Pont2.setText("- Vide -");
            TextField_Pont2.setBackground(new java.awt.Color(0,102,204,75));
            TextField_Pont2.setOpaque(true);
        }
        else{
            TextField_Pont2.setText(getContainer().getPont2().toString());
            TextField_Pont2.setBackground(new java.awt.Color(245,206,66,75));
            TextField_Pont2.setOpaque(true);
        }
                
        if(Container.IsPont3Free()){
            TextField_Pont3.setText("- Vide -");
            TextField_Pont3.setBackground(new java.awt.Color(0,102,204,75));
            TextField_Pont3.setOpaque(true);
        }
        else{
            TextField_Pont3.setText(getContainer().getPont3().toString());
            TextField_Pont3.setBackground(new java.awt.Color(245,206,66,75));
            TextField_Pont3.setOpaque(true);
        }
        
        if(Container.IsPont4Free()){
            TextField_Pont4.setText("- Vide -");
            TextField_Pont4.setBackground(new java.awt.Color(0,102,204,75));
            TextField_Pont4.setOpaque(true);
        }
        else{
            TextField_Pont4.setText(getContainer().getPont4().toString());
            TextField_Pont4.setBackground(new java.awt.Color(245,206,66,75));
            TextField_Pont4.setOpaque(true);
        }
        
        if(Container.IsSolFree()){
            TextField_Sol.setText("- Vide -");
            TextField_Sol.setBackground(new java.awt.Color(0,102,204,75));
            TextField_Sol.setOpaque(true);
        }
        else{
            TextField_Sol.setText(getContainer().getSol().toString());
            TextField_Sol.setBackground(new java.awt.Color(245,206,66,75));
            TextField_Sol.setOpaque(true);
        }
        setVisible(true);
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jSeparator1 = new javax.swing.JSeparator();
        jComboBox1 = new javax.swing.JComboBox<>();
        Label_Atelier = new javax.swing.JLabel();
        Label_Pont1 = new javax.swing.JLabel();
        Label_Pont2 = new javax.swing.JLabel();
        Label_Pont3 = new javax.swing.JLabel();
        Label_Pont4 = new javax.swing.JLabel();
        Label_Sol = new javax.swing.JLabel();
        Label_Divers = new javax.swing.JLabel();
        TextField_Pont1 = new javax.swing.JTextField();
        TextField_Pont2 = new javax.swing.JTextField();
        TextField_Pont3 = new javax.swing.JTextField();
        TextField_Pont4 = new javax.swing.JTextField();
        TextField_Divers = new javax.swing.JTextField();
        TextField_Sol = new javax.swing.JTextField();
        Label_Bureau = new javax.swing.JLabel();
        Label_BureauClient = new javax.swing.JLabel();
        LabelBureauClient = new javax.swing.JTextField();
        Label_BureauCompta = new javax.swing.JLabel();
        LabelPrésence = new javax.swing.JLabel();
        CheckBox_Absent = new javax.swing.JRadioButton();
        CheckBox_Present = new javax.swing.JRadioButton();
        CheckBox_Patron = new javax.swing.JCheckBox();
        CheckBox_PauseMidi = new javax.swing.JCheckBox();
        ImageFond = new javax.swing.JLabel();
        jLabel_HoroDateHeure = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        Menu_Atelier = new javax.swing.JMenu();
        MenuItem_APrevoir = new javax.swing.JMenuItem();
        MenuItem_PriseEnCharge = new javax.swing.JMenuItem();
        MenuItem_Terminé = new javax.swing.JMenuItem();
        SeparateurMenuAtelier = new javax.swing.JPopupMenu.Separator();
        MenuItem_Listes = new javax.swing.JMenuItem();
        Menu_Materiel = new javax.swing.JMenu();
        MenuItem_Commander = new javax.swing.JMenu();
        MenuItem_CommanderPièce = new javax.swing.JMenuItem();
        MenuItem_CommanderPneux = new javax.swing.JMenuItem();
        MenuItem_CommanderLubrifiants = new javax.swing.JMenuItem();
        MenuItem_Receptionner = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        MenuItem_ListeCommandes = new javax.swing.JMenuItem();
        Menu_Clients = new javax.swing.JMenu();
        Menu_Facture = new javax.swing.JMenu();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Garage HEPL: Atelier");
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 2147483647, 455));
        setMaximumSize(new java.awt.Dimension(2147483647, 475));
        setMinimumSize(new java.awt.Dimension(1000, 475));
        setPreferredSize(new java.awt.Dimension(924, 475));

        Label_Atelier.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        Label_Atelier.setForeground(new java.awt.Color(60, 60, 60));
        Label_Atelier.setText("Atelier");
        Label_Atelier.setToolTipText("");

        Label_Pont1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        Label_Pont1.setText("Pont n°1");

        Label_Pont2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        Label_Pont2.setText("Pont n°2");

        Label_Pont3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        Label_Pont3.setText("Pont n°3");

        Label_Pont4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        Label_Pont4.setText("Pont n°4");

        Label_Sol.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        Label_Sol.setText("Sol");

        Label_Divers.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        Label_Divers.setText("Divers");

        TextField_Pont1.setEditable(false);

        TextField_Pont2.setEditable(false);

        TextField_Pont3.setEditable(false);

        TextField_Pont4.setEditable(false);

        TextField_Divers.setEditable(false);

        TextField_Sol.setEditable(false);

        Label_Bureau.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        Label_Bureau.setText("Bureau");

        Label_BureauClient.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        Label_BureauClient.setText("Bureau client");

        LabelBureauClient.setEditable(false);

        Label_BureauCompta.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        Label_BureauCompta.setText("Bureau de compatibilité");

        LabelPrésence.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        LabelPrésence.setText("Me Boulier est là");

        buttonGroup2.add(CheckBox_Absent);
        CheckBox_Absent.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        CheckBox_Absent.setText("Certains absents");

        buttonGroup2.add(CheckBox_Present);
        CheckBox_Present.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        CheckBox_Present.setText("Tout le monde présent");

        buttonGroup1.add(CheckBox_Patron);
        CheckBox_Patron.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        CheckBox_Patron.setText("Patron disponible");

        buttonGroup1.add(CheckBox_PauseMidi);
        CheckBox_PauseMidi.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        CheckBox_PauseMidi.setText("Pause-midi");

        ImageFond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Screenshot 2022-05-03 at 11.24.14.png"))); // NOI18N
        ImageFond.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ImageFond.setFocusable(false);
        ImageFond.setRequestFocusEnabled(false);
        ImageFond.setVerifyInputWhenFocusTarget(false);

        jLabel_HoroDateHeure.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_HoroDateHeure.setText("Dates");

        MenuBar.setBackground(new java.awt.Color(242, 242, 242));
        MenuBar.setToolTipText("");

        Menu_Atelier.setText("Atelier");

        MenuItem_APrevoir.setText("A prevoir");
        MenuItem_APrevoir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItem_APrevoirActionPerformed(evt);
            }
        });
        Menu_Atelier.add(MenuItem_APrevoir);

        MenuItem_PriseEnCharge.setText("Prise en charge");
        MenuItem_PriseEnCharge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItem_PriseEnChargeActionPerformed(evt);
            }
        });
        Menu_Atelier.add(MenuItem_PriseEnCharge);

        MenuItem_Terminé.setText("Terminé");
        MenuItem_Terminé.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItem_TerminéActionPerformed(evt);
            }
        });
        Menu_Atelier.add(MenuItem_Terminé);
        Menu_Atelier.add(SeparateurMenuAtelier);

        MenuItem_Listes.setText("Historique Travaux");
        MenuItem_Listes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItem_ListesActionPerformed(evt);
            }
        });
        Menu_Atelier.add(MenuItem_Listes);

        MenuBar.add(Menu_Atelier);

        Menu_Materiel.setText("Materiel");

        MenuItem_Commander.setText("Commander");

        MenuItem_CommanderPièce.setText("Commander une pièce");
        MenuItem_CommanderPièce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItem_CommanderPièceActionPerformed(evt);
            }
        });
        MenuItem_Commander.add(MenuItem_CommanderPièce);

        MenuItem_CommanderPneux.setText("Commander des pneus");
        MenuItem_CommanderPneux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItem_CommanderPneuxActionPerformed(evt);
            }
        });
        MenuItem_Commander.add(MenuItem_CommanderPneux);

        MenuItem_CommanderLubrifiants.setText("Commander des lubrifiants");
        MenuItem_CommanderLubrifiants.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItem_CommanderLubrifiantsActionPerformed(evt);
            }
        });
        MenuItem_Commander.add(MenuItem_CommanderLubrifiants);

        Menu_Materiel.add(MenuItem_Commander);

        MenuItem_Receptionner.setText("Réceptionner");
        Menu_Materiel.add(MenuItem_Receptionner);
        Menu_Materiel.add(jSeparator4);

        MenuItem_ListeCommandes.setText("Liste commandes");
        Menu_Materiel.add(MenuItem_ListeCommandes);

        MenuBar.add(Menu_Materiel);

        Menu_Clients.setText("Clients");
        MenuBar.add(Menu_Clients);

        Menu_Facture.setText("Facture");
        MenuBar.add(Menu_Facture);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_Bureau)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Label_BureauClient)
                                .addGap(12, 12, 12)
                                .addComponent(LabelBureauClient, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(98, 98, 98)
                                .addComponent(CheckBox_Patron)
                                .addGap(0, 0, 0)
                                .addComponent(CheckBox_Present))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Label_BureauCompta)
                                .addGap(26, 26, 26)
                                .addComponent(LabelPrésence)
                                .addGap(300, 300, 300)
                                .addComponent(CheckBox_PauseMidi)
                                .addGap(42, 42, 42)
                                .addComponent(CheckBox_Absent)))
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_Atelier)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Label_Pont1)
                                    .addComponent(Label_Pont2)
                                    .addComponent(Label_Pont3)
                                    .addComponent(Label_Pont4)
                                    .addComponent(Label_Sol, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Label_Divers))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextField_Pont1)
                            .addComponent(TextField_Pont2)
                            .addComponent(TextField_Pont3)
                            .addComponent(TextField_Pont4)
                            .addComponent(TextField_Sol)
                            .addComponent(TextField_Divers))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel_HoroDateHeure, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ImageFond, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel_HoroDateHeure, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Label_Atelier)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_Pont1)
                            .addComponent(TextField_Pont1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_Pont2)
                            .addComponent(TextField_Pont2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_Pont3)
                            .addComponent(TextField_Pont3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_Pont4)
                            .addComponent(TextField_Pont4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_Sol)
                            .addComponent(TextField_Sol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_Divers)
                            .addComponent(TextField_Divers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(ImageFond))
                .addGap(17, 17, 17)
                .addComponent(Label_Bureau)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(Label_BureauClient))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(LabelBureauClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CheckBox_Patron)
                    .addComponent(CheckBox_Present))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CheckBox_PauseMidi)
                    .addComponent(CheckBox_Absent)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_BureauCompta)
                            .addComponent(LabelPrésence))))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuItem_APrevoirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItem_APrevoirActionPerformed
        AtelierAPrevoir Popup = new AtelierAPrevoir(this,true);
        Popup.setVisible(true);
    }//GEN-LAST:event_MenuItem_APrevoirActionPerformed

    private void MenuItem_PriseEnChargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItem_PriseEnChargeActionPerformed
        AtelierPriseEnCharge Popup = new AtelierPriseEnCharge(this, true);
        Popup.setVisible(true);
    }//GEN-LAST:event_MenuItem_PriseEnChargeActionPerformed

    private void MenuItem_TerminéActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItem_TerminéActionPerformed
        AtelierTerminer Popup = new AtelierTerminer(this, true);
        Popup.setVisible(true);
    }//GEN-LAST:event_MenuItem_TerminéActionPerformed

    private void MenuItem_ListesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItem_ListesActionPerformed
        AtelierHistoriqueTravaux Popup = new AtelierHistoriqueTravaux(this, true);
        Popup.setVisible(true);
    }//GEN-LAST:event_MenuItem_ListesActionPerformed

    private void MenuItem_CommanderPièceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItem_CommanderPièceActionPerformed
        NewCommande Popup = new NewCommande(this, true, "Pièces");
        if(Popup.getClient() == null)
        {
            Popup.dispose();
            JOptionPane.showMessageDialog(this,"La Connexion au serveur n'a pas pus se faire, réessayer plus tard","Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Popup.setTitle("Garage HEPL: Commande " + Popup.getName());
        Popup.setVisible(true);
    }//GEN-LAST:event_MenuItem_CommanderPièceActionPerformed

    private void MenuItem_CommanderPneuxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItem_CommanderPneuxActionPerformed
        NewCommande Popup = new NewCommande(this, true, "Pneux");
        if(Popup.getClient() == null)
        {
            Popup.dispose();
            JOptionPane.showMessageDialog(this,"La Connexion au serveur n'a pas pus se faire, réessayer plus tard","Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Popup.setTitle("Garage HEPL: Commande " + Popup.getName());
        Popup.setVisible(true);
    }//GEN-LAST:event_MenuItem_CommanderPneuxActionPerformed

    private void MenuItem_CommanderLubrifiantsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItem_CommanderLubrifiantsActionPerformed
        NewCommande Popup = new NewCommande(this, true, "Lubrifiants");
        if(Popup.getClient() == null)
        {
            Popup.dispose();
            JOptionPane.showMessageDialog(this,"La Connexion au serveur n'a pas pus se faire, réessayer plus tard","Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Popup.setTitle("Garage HEPL: Commande " + Popup.getName());
        Popup.setVisible(true);
    }//GEN-LAST:event_MenuItem_CommanderLubrifiantsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton CheckBox_Absent;
    private javax.swing.JCheckBox CheckBox_Patron;
    private javax.swing.JCheckBox CheckBox_PauseMidi;
    private javax.swing.JRadioButton CheckBox_Present;
    private javax.swing.JLabel ImageFond;
    private javax.swing.JTextField LabelBureauClient;
    private javax.swing.JLabel LabelPrésence;
    private javax.swing.JLabel Label_Atelier;
    private javax.swing.JLabel Label_Bureau;
    private javax.swing.JLabel Label_BureauClient;
    private javax.swing.JLabel Label_BureauCompta;
    private javax.swing.JLabel Label_Divers;
    private javax.swing.JLabel Label_Pont1;
    private javax.swing.JLabel Label_Pont2;
    private javax.swing.JLabel Label_Pont3;
    private javax.swing.JLabel Label_Pont4;
    private javax.swing.JLabel Label_Sol;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem MenuItem_APrevoir;
    private javax.swing.JMenu MenuItem_Commander;
    private javax.swing.JMenuItem MenuItem_CommanderLubrifiants;
    private javax.swing.JMenuItem MenuItem_CommanderPièce;
    private javax.swing.JMenuItem MenuItem_CommanderPneux;
    private javax.swing.JMenuItem MenuItem_ListeCommandes;
    private javax.swing.JMenuItem MenuItem_Listes;
    private javax.swing.JMenuItem MenuItem_PriseEnCharge;
    private javax.swing.JMenuItem MenuItem_Receptionner;
    private javax.swing.JMenuItem MenuItem_Terminé;
    private javax.swing.JMenu Menu_Atelier;
    private javax.swing.JMenu Menu_Clients;
    private javax.swing.JMenu Menu_Facture;
    private javax.swing.JMenu Menu_Materiel;
    private javax.swing.JPopupMenu.Separator SeparateurMenuAtelier;
    private javax.swing.JTextField TextField_Divers;
    private javax.swing.JTextField TextField_Pont1;
    private javax.swing.JTextField TextField_Pont2;
    private javax.swing.JTextField TextField_Pont3;
    private javax.swing.JTextField TextField_Pont4;
    private javax.swing.JTextField TextField_Sol;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel_HoroDateHeure;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    // End of variables declaration//GEN-END:variables
    
    public void setUser(Personne User) {
        this.User = User;
    }

    public Personne getUser() {
        return User;
    }

    public FichierLog Log() {
        return log;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        new Atelier().setVisible(true);
    }
    
    public NetworkBasicClient getLubrifiants() {
        return Lubrifiants;
    }

    public NetworkBasicClient getPièces() {
        return Pièces;
    }

    public NetworkBasicClient getPneux() {
        return Pneux;
    }
    
    public JLabel getjLabel_HoroDateHeure() {
        return jLabel_HoroDateHeure;
    }
    
    public void setContainer(ContainerClass Container) {
        this.Container = Container;
    }

    public ContainerClass getContainer() {
        return Container;
    }
    
    public void setLubrifiants(NetworkBasicClient Lubrifiants) {
        this.Lubrifiants = Lubrifiants;
    }

    public void setPièces(NetworkBasicClient Pièces) {
        this.Pièces = Pièces;
    }

    public void setPneux(NetworkBasicClient Pneux) {
        this.Pneux = Pneux;
    }
    
}

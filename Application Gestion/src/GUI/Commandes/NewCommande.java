/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Commandes;

import Commandes.Commandes;
import Commandes.Commandes.Priorité;
import Commandes.CommandesLubrifiants;
import Commandes.CommandesPièces;
import Commandes.CommandesPneux;
import GUI.Atelier;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import network.NetworkBasicClient;
/**
 *
 * @author student
 */
public class NewCommande extends javax.swing.JDialog {
    
    private String Name;
    private Atelier Parent;
    private NetworkBasicClient Client;

       
    /**
     * Creates new form Commande
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public NewCommande(java.awt.Frame parent, boolean modal, String name) {
        super(parent, modal);
        initComponents();
        
        this.Parent = (Atelier) parent;
        this.Name = name;
        
        this.jRadioButton_Normal.setSelected(true);
        
        RefreshUI();
        
        Client = getNetClient();
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
        jLabel1 = new javax.swing.JLabel();
        jRadioButton_Urgent = new javax.swing.JRadioButton();
        jRadioButton_Normal = new javax.swing.JRadioButton();
        jRadioButton_NonPrioritaire = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_Libellé = new javax.swing.JTextField();
        jTextField_Type = new javax.swing.JTextField();
        jTextField_Quantitée = new javax.swing.JTextField();
        jButton_Envoyer = new javax.swing.JButton();
        jButton_Annuler = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList_CommandesEffectuées = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Garage HEPL: Commande");

        jLabel1.setText("Nouvel Commande :");

        buttonGroup1.add(jRadioButton_Urgent);
        jRadioButton_Urgent.setText("Urgent");

        buttonGroup1.add(jRadioButton_Normal);
        jRadioButton_Normal.setText("Normal");

        buttonGroup1.add(jRadioButton_NonPrioritaire);
        jRadioButton_NonPrioritaire.setText("Non Prioritaire");

        jLabel2.setText("Libellé :");

        jLabel3.setText("Type :");

        jLabel4.setText("Quantitée :");

        jTextField_Libellé.setText("Libellé");
        jTextField_Libellé.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_LibelléFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_LibelléFocusLost(evt);
            }
        });

        jTextField_Type.setText("Type");
        jTextField_Type.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_TypeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_TypeFocusLost(evt);
            }
        });

        jTextField_Quantitée.setText("Quantitée");
        jTextField_Quantitée.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_QuantitéeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_QuantitéeFocusLost(evt);
            }
        });

        jButton_Envoyer.setText("Envoyer");
        jButton_Envoyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EnvoyerActionPerformed(evt);
            }
        });

        jButton_Annuler.setText("Annuler");
        jButton_Annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AnnulerActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setToolTipText("");

        jLabel5.setText("Historique Commandes :");

        jList_CommandesEffectuées.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList_CommandesEffectuées);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton_Urgent)
                        .addGap(36, 36, 36)
                        .addComponent(jRadioButton_Normal)
                        .addGap(36, 36, 36)
                        .addComponent(jRadioButton_NonPrioritaire))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(jTextField_Libellé, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(jTextField_Type, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(jTextField_Quantitée, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jButton_Envoyer)
                        .addGap(82, 82, 82)
                        .addComponent(jButton_Annuler)))
                .addGap(12, 12, 12)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 299, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton_Urgent)
                    .addComponent(jRadioButton_Normal)
                    .addComponent(jRadioButton_NonPrioritaire))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel2))
                    .addComponent(jTextField_Libellé, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel3)))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel4))
                    .addComponent(jTextField_Quantitée, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_Envoyer)
                    .addComponent(jButton_Annuler)))
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel5)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_EnvoyerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EnvoyerActionPerformed
        
        
        //Verification des données encodées
        if(jTextField_Libellé.getText().equals("")) 
        {
            JOptionPane.showMessageDialog(this,"Veuillez encodez le libellé","Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(jTextField_Quantitée.getText().equals("")) 
        {
            JOptionPane.showMessageDialog(this,"Veuillez encodez la quantitée","Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try{
            int test = Integer.parseInt(jTextField_Quantitée.getText());
            
            if(test <= 0) 
            {
                JOptionPane.showMessageDialog(this,"La quantité doit être un chiffre Positif","Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this,"La quantité doit être un chiffre Positif","Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
       
        
        
        if(jTextField_Type.getText().equals("")) 
        {
            JOptionPane.showMessageDialog(this,"Veuillez encodez le Type","Erreur", JOptionPane.ERROR_MESSAGE);
            Parent.Log().PrintLN("Commande" + getName(), "Erreur de connexion au serveur distant.");
            return;
        }
        
        
        //Recuperation des données dans un objet Commande
        Commandes Cmd = Create();
        
        Cmd.setLibellé(jTextField_Libellé.getText());
        Cmd.setQuantité( Integer.parseInt(jTextField_Quantitée.getText()));
        Cmd.setType(jTextField_Type.getText());
        
        
        if(jRadioButton_Urgent.isSelected()){
            Cmd.setPriorité(Priorité.URGENT);
        }
        if(jRadioButton_Normal.isSelected()){
            Cmd.setPriorité(Priorité.NORMAL);
        }
        if(jRadioButton_NonPrioritaire.isSelected()){
            Cmd.setPriorité(Priorité.PASPRIORITAIRE);
        }
        
        //Envoi au Client de la commande
        String Rep = "";
        
        
        for(int i = 0 ; i<2 ; i++){
            Rep = Cmd.Send(Client);
            //Recuperation de la réponse
            if(Rep == null){
                //Si null, on recrée un objet client, et on renvoie le message avec un temps d'interval de 250ms
                Client = ResetClient();
                
                System.out.println(Client.getAdresse() + ":" + Client.getPort());
                
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ex) {
                    Logger.getLogger(NewCommande.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else break;
        }
            
        if(Rep == null)
        {
            JOptionPane.showMessageDialog(this,"Erreur de connexion au Serveur","Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        } 
        
        
        StringTokenizer Message = new StringTokenizer(Rep,";");
        String isOk;
        String commandeString;
        
        isOk = Message.nextToken();
        commandeString = Message.nextToken();
        
        if(isOk.equals("OK"))
        {
            String date = Message.nextToken();
            JOptionPane.showMessageDialog(this, "La Commande à été acceptée" + System.lineSeparator() + commandeString + System.lineSeparator() + "Sera Livré le " + date,"Reponse Fournisseur", JOptionPane.INFORMATION_MESSAGE);
            
            //Ajout dans l'historique
            Parent.getContainer().getListeCommandes().add(Cmd);
            this.dispose();
            return;
        }
        if(isOk.equals("KO"))
        {
            JOptionPane.showMessageDialog(this, "La Commande n'a pas été acceptée" + System.lineSeparator() + commandeString,"Reponse Fournisseur", JOptionPane.WARNING_MESSAGE);
            return;
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Erreur Réponse inconnue","Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_EnvoyerActionPerformed

    
    private Commandes Create(){
        switch(getName()){
            case "Lubrifiants":
                return new CommandesLubrifiants();
                    
            case "Pièces":
                return new CommandesPièces();
            
            case "Pneux":
                return new CommandesPneux();
                    
            default:
                Parent.Log().PrintLN("Commande" + getName(), "La commandes créer n'a pas de classe définie.");
                System.exit(50);
                return null;
        }
    }
    
    
    
    private void RefreshUI(){
        
        if(Parent.getContainer().getListeCommandes().isEmpty()) return;
        
        //On récupère le modèle de la table
        DefaultListModel<String> model = new DefaultListModel();
        
        for(int i = 0 ; i < Parent.getContainer().getListeCommandes().size() ; i++)
        {
            //On regarde le nom de la classe de l'objet et on le compare au nom de notre app
            String tmp = Parent.getContainer().getListeCommandes().get(i).getClass().getName();
            
            //Si nom app == nom class alors on ajoute
            if(tmp.equals("Commandes.Commandes"+getName())){
                model.addElement(Parent.getContainer().getListeCommandes().get(i).toString());
            }
        }
        jList_CommandesEffectuées.setModel(model);
    }
    
    private NetworkBasicClient getNetClient(){
        switch(getName()){
            case "Lubrifiants":
                return Parent.getLubrifiants();            
                    
            case "Pièces":
                return Parent.getPièces();
            
            case "Pneux":
                return Parent.getPneux();
                                            
            default:
                Parent.Log().PrintLN("Commande" + getName(), "La commandes créer n'a pas de classe définie.");
                System.exit(50);
                return null;
        }
    }
    
    private NetworkBasicClient ResetClient() throws java.lang.NullPointerException
    {        
        switch(getName()){
            case "Lubrifiants":
                return CommandesLubrifiants.CreateClient(Parent.getConfig());
                    
            case "Pièces":
                return CommandesPièces.CreateClient(Parent.getConfig());
            
            case "Pneux":
                return CommandesPneux.CreateClient(Parent.getConfig());
                                            
            default:
                Parent.Log().PrintLN("Commande" + getName(), "La commandes créer n'a pas de classe définie.");
                System.exit(50);
                return null;
        }
    }
    
    
    
    private void jButton_AnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AnnulerActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton_AnnulerActionPerformed

    private void jTextField_LibelléFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_LibelléFocusGained
        if(jTextField_Libellé.getText().equals("Libellé")){
            jTextField_Libellé.setText("");
        }
    }//GEN-LAST:event_jTextField_LibelléFocusGained

    private void jTextField_TypeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_TypeFocusGained
        if(jTextField_Type.getText().equals("Type")){
            jTextField_Type.setText("");
        }
    }//GEN-LAST:event_jTextField_TypeFocusGained

    private void jTextField_QuantitéeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_QuantitéeFocusGained
        if(jTextField_Quantitée.getText().equals("Quantitée")){
            jTextField_Quantitée.setText("");
        }
    }//GEN-LAST:event_jTextField_QuantitéeFocusGained

    private void jTextField_LibelléFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_LibelléFocusLost
        if(jTextField_Libellé.getText().equals("")){
            jTextField_Libellé.setText("Libellé");
        }
    }//GEN-LAST:event_jTextField_LibelléFocusLost

    private void jTextField_TypeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_TypeFocusLost
        if(jTextField_Type.getText().equals("")){
            jTextField_Type.setText("Type");
        }
    }//GEN-LAST:event_jTextField_TypeFocusLost

    private void jTextField_QuantitéeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_QuantitéeFocusLost
        if(jTextField_Quantitée.getText().equals("")){
            jTextField_Quantitée.setText("Quantitée");
        }
    }//GEN-LAST:event_jTextField_QuantitéeFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton_Annuler;
    private javax.swing.JButton jButton_Envoyer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList_CommandesEffectuées;
    private javax.swing.JRadioButton jRadioButton_NonPrioritaire;
    private javax.swing.JRadioButton jRadioButton_Normal;
    private javax.swing.JRadioButton jRadioButton_Urgent;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField_Libellé;
    private javax.swing.JTextField jTextField_Quantitée;
    private javax.swing.JTextField jTextField_Type;
    // End of variables declaration//GEN-END:variables

    
    @Override
    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String getName() {
        return Name;
    }
    
    public NetworkBasicClient getClient() {
        return Client;
    }
}
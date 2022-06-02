/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationcentrale;

import Beans.*;
import Commandes.*;
import FichierLogPackage.FichierLog;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author student
 */
public class ApplicationCentrale extends javax.swing.JFrame {
    
    private Properties Config;
    private FichierLog Log;
    
    //Beans
    ReceivingBean ReceivingB;
    SearchBean SearchB;
    PrepareOrderBean PrepareB;
    
    /**
     * Creates new form ApplicationCentrale
     */
    @SuppressWarnings("LocalVariableHidesMemberVariable")
    public ApplicationCentrale() {
        initComponents();
        
        Log = new FichierLog();
        Config = new Properties();
        
        //Chargement Fichier Config
        Log.PrintLN("ApplicationCentrale","Lancement de l'Application");
        try {
            //Tentative de chargement du fichier Config
            Config.load(new FileInputStream("Config.properties"));
        }
        catch (FileNotFoundException ex) {
            //Si on arrive pas à le charger, On met des valeurs par défaut
            
            //A remplacer par une jdialog prenant le nom, l'ip et le port distant pour ensuite les ajouter ici.
            Config.setProperty("Name",          "Inconnu");
            Config.setProperty("FileName",      "SavedEnv.ser");
            Config.setProperty("Port",          "50500");
            
            try {
                //On sauvegarde les valeurs par défaut
                Config.store(new FileOutputStream("Config.properties"),"Fichier de Configuration du Programme: ");
            }
            catch (IOException ex1) {
                Log.PrintLN("ApplicationCentrale",ApplicationCentrale.class.getName() + " " + Level.SEVERE);
                System.exit(1);
            } 
        } catch (IOException ex) {
            Log.PrintLN("ApplicationCentrale",ApplicationCentrale.class.getName() + " " + Level.SEVERE);
            System.exit(2);
        }
        
        //Initialise le nom de la centrale d'achat sur "l'image" et le nom de la jframe
        this.setTitle("Centrale d'Achat - " + Config.getProperty("Name"));
        this.jLabel_Image.setText(Config.getProperty("Name"));
        
        
        //Chargement de Beans:
        ReceivingB = new ReceivingBean(LoadPort(), this.jCheckBox_MessageEntrant, this.jTextField_Reception);
        SearchB = new SearchBean(Config.getProperty("FileName","SavedEnv.ser"), jComboBox_CommandeEnCours);
        PrepareB = new PrepareOrderBean();
        
        //SearchB s'abonne au PropertyChange de ReceivingB:
        ReceivingB.addPropertyChangeListener(SearchB);
        
        //PrepareB s'abonne au SearchFoundEvent de SearchB:
        SearchB.addSearchFoundEventListener(PrepareB);
        
        //ReceivingB s'abonne a l'event de PrepareB:
        PrepareB.addInstockEventListener(ReceivingB);
        
        
        //Chargement EnvironementAppCentrale
        SearchB.LoadContainer();
        SearchB.RefreshUI();
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
        jButton_VerifDispo = new javax.swing.JToggleButton();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jCheckBox_MessageEntrant = new javax.swing.JCheckBox();
        jButton_LectureMessage = new javax.swing.JButton();
        jLabel_CommandeEnCours = new javax.swing.JLabel();
        jComboBox_CommandeEnCours = new javax.swing.JComboBox<>();
        jTextField_Reception = new javax.swing.JTextField();
        jLabel_DetailCommande = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_DetailCommande = new javax.swing.JTable();
        jLabel_Image = new javax.swing.JLabel();
        jRadioButton_Dispo = new javax.swing.JRadioButton();
        jRadioButton_PasDispo = new javax.swing.JRadioButton();
        jButton_Send = new javax.swing.JButton();
        jLabel_CommandeEnCours1 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();

        jButton_VerifDispo.setText("Validation Choix Disponnibilitée");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jCheckBox_MessageEntrant.setAlignmentY(0.0F);
        jCheckBox_MessageEntrant.setFocusable(false);
        jCheckBox_MessageEntrant.setRequestFocusEnabled(false);
        jCheckBox_MessageEntrant.setRolloverEnabled(false);
        jCheckBox_MessageEntrant.setVerifyInputWhenFocusTarget(false);

        jButton_LectureMessage.setText("Lire");
        jButton_LectureMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LectureMessageActionPerformed(evt);
            }
        });

        jLabel_CommandeEnCours.setText("Commande en Cours :");

        jTextField_Reception.setText(">>");

        jLabel_DetailCommande.setText("Détails de la Commande :");

        jTable_DetailCommande.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Caractéristique", "Valeur"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_DetailCommande.setRowHeight(23);
        jTable_DetailCommande.setRowSelectionAllowed(false);
        jScrollPane1.setViewportView(jTable_DetailCommande);

        jLabel_Image.setBackground(new java.awt.Color(255, 255, 51));
        jLabel_Image.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel_Image.setForeground(new java.awt.Color(51, 51, 255));
        jLabel_Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Image.setText("Pièces");
        jLabel_Image.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jLabel_Image.setFocusable(false);
        jLabel_Image.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel_Image.setOpaque(true);

        buttonGroup1.add(jRadioButton_Dispo);
        jRadioButton_Dispo.setText("Disponnible");
        jRadioButton_Dispo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_DispoActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton_PasDispo);
        jRadioButton_PasDispo.setText("Indisponible");
        jRadioButton_PasDispo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_PasDispoActionPerformed(evt);
            }
        });

        jButton_Send.setText("Envoyer Réponse");
        jButton_Send.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SendActionPerformed(evt);
            }
        });

        jLabel_CommandeEnCours1.setText("Message Entrant");

        buttonGroup2.add(jToggleButton1);
        jToggleButton1.setText("Validation Disponnibilité");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel_DetailCommande)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jCheckBox_MessageEntrant)
                                                    .addGap(4, 4, 4)
                                                    .addComponent(jLabel_CommandeEnCours1))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(4, 4, 4)
                                                    .addComponent(jButton_LectureMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(97, 97, 97)
                                                    .addComponent(jComboBox_CommandeEnCours, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel_CommandeEnCours)
                                                    .addGap(15, 15, 15))))
                                        .addComponent(jTextField_Reception, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jRadioButton_Dispo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jRadioButton_PasDispo))
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Send, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jCheckBox_MessageEntrant, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel_CommandeEnCours1)
                                    .addComponent(jLabel_CommandeEnCours))
                                .addGap(14, 14, 14)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox_CommandeEnCours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_LectureMessage))
                        .addGap(31, 31, 31)
                        .addComponent(jTextField_Reception, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jLabel_DetailCommande, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton_PasDispo)
                            .addComponent(jRadioButton_Dispo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jToggleButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Send, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("null")
    private void jButton_LectureMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LectureMessageActionPerformed
        //Lors de l'appuis sur le bouton lire
        Commandes tmp = null;

        //On dit au bean de lire
        tmp = ReceivingB.Lire();
        
        if(tmp == null)
        {
            return;
        } 
        
        //Remet a jours les informations contenues dans la table
        RefreshJtable(tmp);
        this.buttonGroup1.clearSelection();
    }//GEN-LAST:event_jButton_LectureMessageActionPerformed

    private void jButton_SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SendActionPerformed
        if(this.buttonGroup2.getSelection() == null || this.buttonGroup1.getSelection() == null) return;
        
        
        SearchB.Reponse(jRadioButton_Dispo.isSelected());
        
        this.jTextField_Reception.setText(">> ");
        RefreshJtable(null);
        this.buttonGroup2.clearSelection();
    }//GEN-LAST:event_jButton_SendActionPerformed

    private void jRadioButton_DispoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_DispoActionPerformed
        this.buttonGroup2.clearSelection();
    }//GEN-LAST:event_jRadioButton_DispoActionPerformed

    private void jRadioButton_PasDispoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_PasDispoActionPerformed
        this.buttonGroup2.clearSelection();
    }//GEN-LAST:event_jRadioButton_PasDispoActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton_LectureMessage;
    private javax.swing.JButton jButton_Send;
    private javax.swing.JToggleButton jButton_VerifDispo;
    private javax.swing.JCheckBox jCheckBox_MessageEntrant;
    private javax.swing.JComboBox<String> jComboBox_CommandeEnCours;
    private javax.swing.JLabel jLabel_CommandeEnCours;
    private javax.swing.JLabel jLabel_CommandeEnCours1;
    private javax.swing.JLabel jLabel_DetailCommande;
    private javax.swing.JLabel jLabel_Image;
    private javax.swing.JRadioButton jRadioButton_Dispo;
    private javax.swing.JRadioButton jRadioButton_PasDispo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_DetailCommande;
    private javax.swing.JTextField jTextField_Reception;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables

    
    private void RefreshJtable(Commandes tmp){
        
        DefaultTableModel tablemodel = (DefaultTableModel) this.jTable_DetailCommande.getModel();
        
        //Supprime les anciens Row
        tablemodel.setRowCount(0);
        
        //Si Commande a null, on reset l'affichage
        if(tmp == null){
            jTable_DetailCommande.setModel(tablemodel);
            return;
        }
        
               
        
        //Ajout Libellé
        Vector VecTmp = new Vector();
        VecTmp.add("Libellé");
        VecTmp.add(tmp.getLibellé());
        
        tablemodel.addRow(VecTmp);
    
    
        //Ajout Type
        VecTmp = new Vector();
        VecTmp.add("Type");
        VecTmp.add(tmp.getType());
        
        tablemodel.addRow(VecTmp);
    
        
        //Ajout Quantité
        VecTmp = new Vector();
        VecTmp.add("Quantitée");
        VecTmp.add(tmp.getQuantité());
        
        tablemodel.addRow(VecTmp);
        
        //Ajout Quantité
        VecTmp = new Vector();
        VecTmp.add("Prioritée");
        VecTmp.add(tmp.getPriorité());
        
        tablemodel.addRow(VecTmp);
        
        jTable_DetailCommande.setModel(tablemodel);
    }
    
    //Permet de récupéré le port du server
    private int LoadPort(){
        int Port;
        
        //Regarde si le port est dans le fichier Config, et si ce dernier est bien un nombre
        try{
            Port = Integer.parseInt( Config.getProperty("Port", ""));
        } catch(NumberFormatException ex){
          //Si ce n'est ni l'un ni l'autre on remet le port par défaut
          Port = 50500;
          Config.setProperty("Port", String.valueOf(Port));
        }
        
        //Si le port n'est pas dans la plage allouée, on met le port par défaut.
        if(Port < 50000 || Port >= 65000){
            Port = 50500;
            Config.setProperty("Port", String.valueOf(Port));
        }
        
        return Port;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ApplicationCentrale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ApplicationCentrale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ApplicationCentrale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ApplicationCentrale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        
        ApplicationCentrale App = new ApplicationCentrale();
        if(App.ReceivingB == null){
            System.exit(0);
        }
        App.setVisible(true);
    } 
}

package devizavalto10pt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 *
 * @author Plósz Tamás
 * 
 * Az alkalmazás gombnyomásra betölti a https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml helyen
 * található XML állományban lévő valutákat és annak aktuális árfolyamát.
 * A felhasználó megadhatja, hogy mennyi valutát szeretne váltani, majd a "Váltás" nyomógombbal kiszámoljuk
 * és megjelenítjuk a forintba átváltott összeget.
 */
public class DevizaValto10pt extends javax.swing.JFrame implements ActionListener {
    private static final String URLcim = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
    private static final DecimalFormat df = new DecimalFormat("0.00");
    Map<String, Double> arfolyamok = new HashMap<String, Double>();

    /**
     * Létrehozzuk az új DevizaValto10pt formot
     */
    public DevizaValto10pt() {
/**
 * Inicializáljuk a komponenseket.
 */
        initComponents();
/**
 * Letiltjuk a mezőket.
 */
        EnableItems(false);
/**
 * Beleírjuk az URL címet a mezőbe.
 */
        jTextField1.setText(URLcim);
/**
 * A mennyi valutát szeretne váltani mezőhöz adunk egy olyan listenert-t, amely csak számokat fogad el.
 */
        mennyitValt.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                if (!Character.isDigit(evt.getKeyChar())) {
                    evt.consume();
                }
            }
        });
    }


/**
 * Engedélyezzük/letiltjuk a mezőket.
 */
    public void EnableItems(boolean milegyen) {
        jComboBox1.setEnabled(milegyen);
        mennyitValt.setEnabled(milegyen);
        jButtonValtas.setEnabled(milegyen);
        osszegFt.setEnabled(milegyen);
        }
   /**
    * Beolvassuk az XML-t erről az URL címről: https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml.
    * Letároljuk az XML-ből beolvasott devizanemeket és árfolyamokat a arfolyamok map-ba, valamint feltöltjük a legördülő
    * dobozkába a devizanemeket, hogy majd azokból választhasson a felhasználó.
    */
    public void XMLBeOlvas() {
        arfolyamok.clear();
        jComboBox1.removeAllItems();
        mennyitValt.setText("");
        osszegFt.setText("");
 
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
     
           new CertificateValidator();

            Document doc = db.parse(new URL(URLcim).openStream());

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Cube");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element elem = (Element) nodeList.item(i);
                if (elem.getAttribute("currency") != null && !elem.getAttribute("currency").isEmpty()) {
                    arfolyamok.put(elem.getAttribute("currency"), Double.valueOf(elem.getAttribute("rate")));
                }
            }
/**
 * Betöltjük az arfolyamok Map-ból az XML-ből beolvasott devizanemeket a legördülő dobozkába.
 */
        arfolyamok.keySet().forEach(key -> {
        jComboBox1.addItem(key);
            });
/**
 * Lekezeljük a kivételeket.
 */
        } catch (ParserConfigurationException | SAXException | IOException e) {
            java.util.logging.Logger.getLogger(DevizaValto10pt.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }

        if (!arfolyamok.isEmpty()) {
            EnableItems(true);
        }
        else {
            System.out.println("Hiba történt a betöltéskor! Próbálja újra.");
            EnableItems(false);
        }
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        mennyitValt = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButtonValtas = new javax.swing.JButton();
        osszegFt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Devizaváltó Alkalmazás 1.0 (c) Plósz Tamás");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextField1.setToolTipText("");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("XML betöltés");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        mennyitValt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mennyitValt.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        mennyitValt.setActionCommand("<Not Set>");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox1.setEnabled(false);

        jButtonValtas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonValtas.setText("Váltás");
        jButtonValtas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValtasActionPerformed(evt);
            }
        });

        osszegFt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        osszegFt.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("=");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Ft");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Devizaváltó Alkalmazás 1.0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mennyitValt, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonValtas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(osszegFt, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(64, 64, 64))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(mennyitValt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonValtas)
                                .addComponent(jLabel2))
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(osszegFt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonValtasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValtasActionPerformed
        // TODO add your handling code here:
        Double ennyiFt;
/**
 * Amennyiben nem adott meg váltando összeget, akkor nem számolunk.
 */
        if ( !mennyitValt.getText().isEmpty() ) {
            ennyiFt = (Integer.valueOf(mennyitValt.getText()) / arfolyamok.get(jComboBox1.getSelectedItem()).doubleValue() ) * arfolyamok.get("HUF");
            osszegFt.setText(df.format(ennyiFt).toString());
        }
    }//GEN-LAST:event_jButtonValtasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
/**
 * A felhasználó megnyomta az "XML betöltés" gombot, ezért beolvassuk az URL címről az XML állományt.
 */
        XMLBeOlvas();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(DevizaValto10pt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DevizaValto10pt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DevizaValto10pt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DevizaValto10pt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DevizaValto10pt().setVisible(true);
            }
        });

    }


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonValtas;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField mennyitValt;
    private javax.swing.JTextField osszegFt;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

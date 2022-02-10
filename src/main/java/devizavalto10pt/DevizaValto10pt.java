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
 * található XML állományban lévő devizákat és annak aktuális árfolyamát.
 * A felhasználó kiválaszthatja, hogy milyen devizát szeretne váltani, valamint a véáltani kívánt mennyiséget,
 * majd a "Váltás" nyomógombbal kiszámoljuk és megjelenítjuk a forintba átváltott összeget.
 */
public class DevizaValto10pt extends javax.swing.JFrame implements ActionListener {
    private static final String URLcim = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
    private static final DecimalFormat df = new DecimalFormat("0.00");
    Map<String, Double> arfolyamok = new HashMap<String, Double>();

    /**
     * Létrehozzuk az DevizaValto10pt formot.
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
 * Engedélyezzük / letiltjuk a mezőket.
 */
    public void EnableItems(boolean milegyen) {
        jComboBox1.setEnabled(milegyen);
        mennyitValt.setEnabled(milegyen);
        jButtonValtas.setEnabled(milegyen);
        osszegFt.setEnabled(milegyen);
        }
   /**
    * Beolvassuk az XML-t erről az URL címről: https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml.
    * Letároljuk az XML-ből beolvasott devizanemeket és árfolyamokat a arfolyamok Map-ba, valamint feltöltjük a legördülő
    * dobozkába a devizanemeket, hogy majd azokból választhasson a felhasználó.
    */
    public void XMLBeOlvas() {
        /**
         * Töröljük az arfolyam Map-ot.
         */
        arfolyamok.clear();
        /**
         * Töröljük a legördülő dobozka tartalmát.
         */
        jComboBox1.removeAllItems();
        /**
         * Kiürítjük a mennyi valutát szeretne váltani szöveg mezőt.
         */
        mennyitValt.setText("");
        /**
         * Kiürítjük a forintban kiszámolt valuta értékét tartalmazó szöveg mezőt.
         */
        osszegFt.setText("");
 
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        /**
         * Megpróbáljuk beolvasni az URLcim-ről az XML állományt.
         */
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
     
            /**
             * Meghívjük a CertificateValidator-t, hogy a MÁK-ban a tűzfal mögött is működjön az alkalmazás.
             */
            new CertificateValidator();

            Document doc = db.parse(new URL(URLcim).openStream());

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Cube");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element elem = (Element) nodeList.item(i);
                /**
                 * Az XML-ben a "currency" tag-ban lévő szöveget, és a "rate" tag-ban lévő értéket
                 * letároljuk az arfolyamok Map-ba.
                 */
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
        /**
         * Amennyiben sikerült feltölteni az arfolyamok Map-ot, akkor engedélyezzük a valutaváltás elvégzéséhez tartozó elemeket.
         */
        if (!arfolyamok.isEmpty()) {
            EnableItems(true);
        }
        /**
         * Amennyiben nem sikerült feltölteni az arfolyamok Map-ot, akkor hibajelzést küldünk a felhasználónak,
         * és letiltjuk a valutaváltás elvégzéséhez tartozó elemeket.
         */
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
        setResizable(false);

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextField1.setToolTipText("");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("XML betöltés");
        jButton1.setToolTipText("Töltse be az aktuális árfolyamot.");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        mennyitValt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mennyitValt.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        mennyitValt.setToolTipText("Mennyi valutát szeretne váltani?");
        mennyitValt.setActionCommand("<Not Set>");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox1.setToolTipText("Válassza ki a devizanemet.");
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
        jLabel3.setForeground(new java.awt.Color(255, 0, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Devizaváltó Alkalmazás 1.0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mennyitValt, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButtonValtas, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(osszegFt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(64, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mennyitValt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonValtas)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(osszegFt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addContainerGap(48, Short.MAX_VALUE))
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
            /**
             * Kiszámoljuk a váltandó valuta összegét forintban és megjelenítjük.
             */
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

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KontoErstellenGUI {
    private static KontoGUI kontoGUI = new KontoGUI();
    public JPanel KontoErstellenView;
    private JTextField KontoinhaberFeld;
    private JTextField StartbetragFeld;
    private JComboBox<String> KontoartAuswahl;
    private JButton KontoErstellBtn;
    private JButton CancelBtn;
    private JLabel KontoartLabel;
    private JLabel KontoinhaberLabel;
    private JLabel StartbetragLabel;

    public KontoErstellenGUI(KontoGUI kontoGUI) {
        KontoErstellenGUI.kontoGUI = kontoGUI;

        // Kontoart-Auswahl füllen
        KontoartAuswahl.addItem("Girokonto");
        KontoartAuswahl.addItem("Sparkonto");
        KontoartAuswahl.addItem("Kreditkonto");

        // Konto erstellen (Button)
        KontoErstellBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kontoinhaber = KontoinhaberFeld.getText();
                double startbetrag;
                try {
                    startbetrag = Double.parseDouble(StartbetragFeld.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ungültiger Startbetrag!", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String kontoart = (String) KontoartAuswahl.getSelectedItem();
                Kontoklasse neuesKonto = null;

                switch (kontoart) {
                    case "Girokonto":
                        neuesKonto = new Girokonto(kontoinhaber, startbetrag);
                        break;
                    case "Sparkonto":
                        neuesKonto = new Sparkonto(kontoinhaber, startbetrag);
                        break;
                    case "Kreditkonto":
                        neuesKonto = new Kreditkonto(kontoinhaber, startbetrag);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Ungültige Kontoart!", "Fehler", JOptionPane.ERROR_MESSAGE);
                        return;
                }

                // Konto zur Liste hinzufügen
                KontoGUI.konten.add(neuesKonto);

                // Konto-Details in den Verlauf schreiben
                kontoGUI.textArea1.append("Konto erstellt:\n");
                kontoGUI.textArea1.append("Art: " + kontoart + "\n");
                kontoGUI.textArea1.append("Kontonummer: " + neuesKonto.getKontonummer() + "\n");
                kontoGUI.textArea1.append("Inhaber: " + kontoinhaber + "\n");
                kontoGUI.textArea1.append("Betrag: " + startbetrag + " EUR\n");
                kontoGUI.textArea1.append("------------------------------\n");

                // Fenster schließen
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(KontoErstellenView);
                frame.dispose();
            }
        });

        // Abbrechen Button
        CancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fenster schließen
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(KontoErstellenView);
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Konto Erstellen");
        frame.setContentPane(new KontoErstellenGUI(kontoGUI).KontoErstellenView);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

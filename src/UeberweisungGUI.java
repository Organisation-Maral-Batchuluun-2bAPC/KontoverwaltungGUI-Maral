import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UeberweisungGUI {
    private static KontoGUI kontoGUI = new KontoGUI();
    protected JPanel UeberweisungView;
    private JTextField VonKontonummer;
    private JTextField ZuKontonummer;
    private JTextField KontoinhaberFeldVon;
    private JTextField KontoinhaberFeldZu;
    private JTextField BetragFeld;
    private JButton UeberweisenBtn;
    private JButton CancelBtn;
    private JLabel ueberweisenVon;
    private JLabel ueberweisenZu;
    private JLabel KontonummerVon;
    private JLabel KontoinhaberVon;
    private JLabel KontoinhaberZu;
    private JLabel Betrag;

    public UeberweisungGUI(KontoGUI kontoGUI) {
        UeberweisungGUI.kontoGUI = kontoGUI;

        // Überweisen Button
        UeberweisenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int vonKontonummer = Integer.parseInt(VonKontonummer.getText());
                int zuKontonummer = Integer.parseInt(ZuKontonummer.getText());
                double betrag;
                try {
                    betrag = Double.parseDouble(BetragFeld.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ungültiger Betrag!", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Kontoklasse vonKonto = kontoGUI.findeKonto(vonKontonummer);
                Kontoklasse zuKonto = kontoGUI.findeKonto(zuKontonummer);

                if (vonKonto != null && zuKonto != null) {
                    if (vonKonto.abheben(betrag)) {
                        zuKonto.einzahlen(betrag);
                        kontoGUI.textArea1.append("Überweisung erfolgreich.\n");
                        kontoGUI.textArea1.append("Betrag: " + betrag + " EUR von Konto " + vonKontonummer + " zu Konto " + zuKontonummer + " überwiesen.\n");
                    } else {
                        kontoGUI.textArea1.append("Nicht genügend Guthaben für Überweisung!\n");
                    }
                } else {
                    kontoGUI.textArea1.append("Eines oder beide Konten nicht gefunden!\n");
                }

                // Fenster schließen
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(UeberweisungView);
                frame.dispose();
            }
        });

        // Abbrechen Button
        CancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fenster schließen
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(UeberweisungView);
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Überweisung");
        frame.setContentPane(new UeberweisungGUI(kontoGUI).UeberweisungView);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
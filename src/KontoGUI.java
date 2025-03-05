import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KontoGUI {
    public JPanel KontoView;
    public JTextArea textArea1;
    private JTextField KontonummerFeld;
    private JTextField KontoinhaberFeld;
    private JTextField ZinsenFeld;
    private JLabel KontostandFeld;
    private JLabel Kontonummer;
    private JLabel Kontoinhaber;
    private JLabel Zinsen;
    private JLabel Kontostand;
    private JButton Close;
    private JLabel Verlauf;
    private JButton KontoErstellBtn;
    private JButton einzahlungsBtn;
    private JButton auszahlungsBtn;
    private JButton ueberweisungsBtn;
    private JButton AnsichtsBtn;
    public static ArrayList<Kontoklasse> konten = new ArrayList<>();

    public KontoGUI() {
        // Konto erstellen (Button)
        KontoErstellBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Konto Erstellen");
                frame.setContentPane(new KontoErstellenGUI(KontoGUI.this).KontoErstellenView);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        // Einzahlung Button
        einzahlungsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int kontonummer = Integer.parseInt(KontonummerFeld.getText());
                double betrag = Double.parseDouble(ZinsenFeld.getText());

                Kontoklasse konto = findeKonto(kontonummer);
                if (konto != null) {
                    konto.einzahlen(betrag);
                    textArea1.append("Einzahlung erfolgreich.\n");
                    textArea1.append("Kontonummer: " + kontonummer + ", Betrag: " + betrag + " EUR eingezahlt.\n");
                } else {
                    textArea1.append("Konto nicht gefunden!\n");
                }
            }
        });

        // Auszahlung Button
        auszahlungsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int kontonummer = Integer.parseInt(KontonummerFeld.getText());
                double betrag = Double.parseDouble(ZinsenFeld.getText());

                Kontoklasse konto = findeKonto(kontonummer);
                if (konto != null) {
                    if (konto.abheben(betrag)) {
                        textArea1.append("Auszahlung erfolgreich.\n");
                        textArea1.append("Kontonummer: " + kontonummer + ", Betrag: " + betrag + " EUR abgehoben.\n");
                    } else {
                        textArea1.append("Nicht genügend Guthaben für Auszahlung!\n");
                    }
                } else {
                    textArea1.append("Konto nicht gefunden!\n");
                }
            }
        });

        // Überweisung Button
        ueberweisungsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Überweisung");
                frame.setContentPane(new UeberweisungGUI(KontoGUI.this).UeberweisungView);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        // Ansicht Button

        AnsichtsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder aktuellerBetrag = new StringBuilder();
                for (Kontoklasse konto : konten) {
                    aktuellerBetrag.append("Kontonummer: ")
                            .append(konto.getKontonummer())
                            .append(", Kontoinhaber: ")
                            .append(konto.getKontoinhaber())
                            .append(", Kontostand: ")
                            .append(konto.getKontostand())
                            .append(" EUR\n");
                }
                textArea1.setText(aktuellerBetrag.toString());
            }
        });

        // Close Button
        Close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Kontonummer Feld Key Listener
        KontonummerFeld.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    int kontonummer = Integer.parseInt(KontonummerFeld.getText());
                    Kontoklasse konto = findeKonto(kontonummer);
                    if (konto != null) {
                        KontostandFeld.setText("Kontostand: " + konto.getKontostand() + " EUR");
                    } else {
                        KontostandFeld.setText("Kontostand: Konto nicht gefunden");
                    }
                } catch (NumberFormatException ex) {
                    KontostandFeld.setText("Kontostand: Ungültige Kontonummer");
                }
            }
        });
    }

    protected Kontoklasse findeKonto(int kontonummer) {
        for (Kontoklasse konto : konten) {
            if (konto.getKontonummer() == kontonummer) {
                return konto;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("KontoGUI");
        frame.setContentPane(new KontoGUI().KontoView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
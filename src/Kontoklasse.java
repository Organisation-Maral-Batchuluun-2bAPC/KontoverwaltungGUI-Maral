public abstract class Kontoklasse {
    protected String kontoinhaber;
    protected double kontostand;
    protected int kontonummer; // Kontonummer

    private static int nextKontonummer = 1; // Statische Variable für die automatische Vergabe der Kontonummern

    public Kontoklasse(String kontoinhaber, double startbetrag) {
        this.kontoinhaber = kontoinhaber;
        this.kontostand = startbetrag;
        this.kontonummer = nextKontonummer++; // Automatische Zuweisung und Erhöhung der Kontonummer
    }

    public void einzahlen(double betrag) {
        kontostand += betrag;
        System.out.println(betrag + " EUR eingezahlt. Neuer Kontostand: " + kontostand);
    }

    public boolean abheben(double betrag) {
        if (betrag > 0 && kontostand >= betrag) { // Prüft, ob genug Guthaben da ist
            kontostand -= betrag;
            System.out.println(betrag + " EUR abgehoben. Neuer Kontostand: " + kontostand);
            return true; // Erfolgreiche Abhebung
        } else {
            System.out.println("Nicht genügend Guthaben oder ungültiger Betrag!");
            return false; // Abhebung fehlgeschlagen
        }
    }

    public void kontoauszug() {
        System.out.println("Kontonummer: " + kontonummer + ", Kontoinhaber: " + kontoinhaber + ", Kontostand: " + kontostand);
    }

    public String getKontoinhaber() {
        return kontoinhaber;
    }

    public int getKontonummer() {
        return kontonummer;
    }

    public double getKontostand() {
        return kontostand;
    }
}
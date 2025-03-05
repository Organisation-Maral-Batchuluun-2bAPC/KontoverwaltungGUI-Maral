public class Kreditkonto extends Kontoklasse {
    public Kreditkonto(String kontoinhaber, double startbetrag) {
        super(kontoinhaber, startbetrag);
    }

    @Override
    public void einzahlen(double betrag) {
        if (kontostand < 0) {
            System.out.println("Schulden reduziert um: " + betrag + " EUR.");
        }
        super.einzahlen(betrag);
    }
}


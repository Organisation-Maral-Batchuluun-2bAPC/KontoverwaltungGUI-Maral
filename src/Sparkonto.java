public class Sparkonto extends Kontoklasse {
    public Sparkonto(String kontoinhaber, double startbetrag) {
        super(kontoinhaber, startbetrag);
    }

    @Override
    public boolean abheben(double betrag) {
        if (kontostand - betrag < 0) {
            System.out.println("Abheben nicht möglich: Kontostand darf nicht negativ sein!");
        } else {
            super.abheben(betrag);
        }
        return false;
    }
}
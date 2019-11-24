package laskin.komento;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.Sovelluslogiikka;

public class Nollaa extends Komento {

    private TextField tuloskentta;
    private TextField syotekentta;
    private Button nollaa;
    private Button undo;
    private Sovelluslogiikka sovellus;
    private int edellinenTulos;

    public Nollaa(TextField tuloskentta,
                  TextField syotekentta,
                  Button nollaa,
                  Button undo,
                  Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
        this.edellinenTulos = 0;
    }

    @Override
    public void suorita() {
        this.edellinenTulos = sovellus.tulos();
        sovellus.nollaa();
        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
        nollaa.disableProperty().set(true);
        undo.disableProperty().set(false);
    }

    @Override
    public void peru() {
        sovellus.setTulos(edellinenTulos);
        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
        undo.disableProperty().set(true);
    }
}

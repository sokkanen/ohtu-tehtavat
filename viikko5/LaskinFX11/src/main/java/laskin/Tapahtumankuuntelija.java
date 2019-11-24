package laskin;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.komento.Erotus;
import laskin.komento.Komento;
import laskin.komento.Nollaa;
import laskin.komento.Summa;

import java.util.HashMap;

public class Tapahtumankuuntelija implements EventHandler {
    private HashMap<Button, Komento> komennot;
    private Komento edellinen = null;
    private Button undo;
    private Sovelluslogiikka sovellus;

    public Tapahtumankuuntelija(TextField tuloskentta,
                                TextField syotekentta,
                                Button plus,
                                Button miinus,
                                Button nollaa,
                                Button undo) {
        this.sovellus = new Sovelluslogiikka();
        this.undo = undo;
        this.komennot = new HashMap<>();
        komennot.put(plus, new Summa(tuloskentta, syotekentta, nollaa, undo, sovellus));
        komennot.put(miinus, new Erotus(tuloskentta, syotekentta, nollaa, undo, sovellus));
        komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta, nollaa, undo, sovellus));
    }

    @Override
    public void handle(Event event) {
        if (event.getTarget() != undo) {
            Komento komento = komennot.get(event.getTarget());
            komento.suorita();
            edellinen = komento;
        } else {
            edellinen.peru();
            edellinen = null;
        }
    }

}

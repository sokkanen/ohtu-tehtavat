package ohtu.kivipaperisakset.KPS;

import ohtu.kivipaperisakset.IO.IO;
import ohtu.kivipaperisakset.Tuomari;
import ohtu.kivipaperisakset.tekoaly.TekoalyParannettu;
import ohtu.kivipaperisakset.tekoaly.TekoalyTyhma;

import java.util.HashMap;
import java.util.Map;

public class KPSTehdas {

    private Map<String, KPS> pelit;
    private IO io;
    private Tuomari tuomari;

    public KPSTehdas(IO io, int vaikeusAste, Tuomari tuomari) {
        this.io = io;
        this.pelit = new HashMap<>();
        this.tuomari = tuomari;
        pelit.put("a", new KPSPeli(io, tuomari, null));
        pelit.put("b", new KPSPeli(io, tuomari, new TekoalyTyhma()));
        pelit.put("c", new KPSPeli(io, tuomari, new TekoalyParannettu(vaikeusAste)));
    }

    public KPS haePeli(String haettu) {
        return haettu.length() != 0 ? pelit.getOrDefault(haettu.substring(haettu.length() - 1), null) : null;
    }
}

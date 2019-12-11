package ohtu.kivipaperisakset.tekoaly;

import java.util.ArrayList;
import java.util.List;

public class TekoalyTyhma implements Tekoaly{

    int siirto;
    List<String> siirrot;

    public TekoalyTyhma() {
        this.siirto = 0;
        this.siirrot = new ArrayList<>();
        siirrot.add("k");
        siirrot.add("p");
        siirrot.add("s");
    }

    public String annaSiirto() {
        siirto = (siirto + 1) % 3;
        return siirrot.get(siirto);
    }

    public void asetaSiirto(String ekanSiirto) {
    }
}

package ohtu.verkkokauppa;

public class Viitegeneraattori implements IViitegeneraattori {

    private int seuraava;
    
    public Viitegeneraattori(){
        this.seuraava = 1;
    }
    
    @Override
    public int uusi(){
        return seuraava++;
    }
}

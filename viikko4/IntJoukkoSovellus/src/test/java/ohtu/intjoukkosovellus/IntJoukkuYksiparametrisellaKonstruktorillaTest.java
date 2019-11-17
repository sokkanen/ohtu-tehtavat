
package ohtu.intjoukkosovellus;

import org.junit.Before;
import org.junit.Test;


public class IntJoukkuYksiparametrisellaKonstruktorillaTest extends IntJoukkoTest {
    
    @Before
    @Override
    public void setUp() {
        joukko = new IntJoukko(3);
        joukko.lisaaJoukkoon(10);
        joukko.lisaaJoukkoon(3);
    }

    @Test(expected = IntJoukko.IntJoukkoException.class)
    public void epakelpoParametriHeittaaPoikkeuksen(){
        joukko = new IntJoukko(-1);
    }
}

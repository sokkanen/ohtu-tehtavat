
package ohtu.intjoukkosovellus;

import org.junit.Before;
import org.junit.Test;

public class IntJoukkoKaksiparametrisellaKonstruktorillaTest extends IntJoukkoTest {
    
    @Before
    public void setUp() {
        joukko = new IntJoukko(4, 2);
        joukko.lisaaJoukkoon(10);
        joukko.lisaaJoukkoon(3);
    }

    @Test(expected = IntJoukko.IntJoukkoException.class)
    public void epakelpoParametriHeittaaPoikkeuksen(){
        joukko = new IntJoukko(-1, -1);
    }
}

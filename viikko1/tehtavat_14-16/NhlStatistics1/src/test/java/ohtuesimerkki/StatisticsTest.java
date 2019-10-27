package ohtuesimerkki;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class StatisticsTest {

    private Statistics stats;

    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            List<Player> players = new ArrayList<>();
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
            return players;
        }
    };

    @Before
    public void setUp(){
        this.stats = new Statistics(readerStub);
    }

    @Test
    public void searchReturnsOnePlayer(){
        Player player = stats.search("Kurri");
        assertEquals("Kurri", player.getName());
        assertEquals(53, player.getAssists());
        assertEquals(37, player.getGoals());
        assertEquals(90, player.getPoints());
        assertEquals("EDM", player.getTeam());
    }

    @Test
    public void searchingNonExistingPlayerResultsInNull(){
        Player player = stats.search("Barkov");
        assertEquals(null, player);
    }

    @Test
    public void teamsArrayIsEmptyForNonExistingTeam(){
        ImmutableList<Player> greatTeam = ImmutableList.copyOf(stats.team("Saipa"));
        assertEquals(0, greatTeam.size());
    }

    @Test
    public void teamsArrayContains3PlayersPlayingInEdmonton(){
        ImmutableList<Player> greatTeam = ImmutableList.copyOf(stats.team("EDM"));
        assertEquals(3, greatTeam.size());
    }

    @Test
    public void topScorerIsGretzky(){
        ImmutableList<Player> tops = ImmutableList.copyOf(stats.topScorers(1));
        assertEquals(1, tops.size());
        assertEquals("Gretzky", tops.get(0).getName());
        assertEquals(124, tops.get(0).getPoints());
    }

    @Test
    public void topScorerListIsEmptyWithZeroRequest(){
        ImmutableList<Player> tops = ImmutableList.copyOf(stats.topScorers(0));
        assertEquals(0, tops.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void topScorerThrowsExceptionWithRequestOf1000(){
        ImmutableList<Player> tops = ImmutableList.copyOf(stats.topScorers(1000));
    }
}

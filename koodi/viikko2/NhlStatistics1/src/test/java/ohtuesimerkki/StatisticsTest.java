package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    public StatisticsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findRightPlayer() {
        Player result = stats.search("Semenko");
        assertEquals(String.format("%-20s", "Semenko") + " " + "EDM" + " " + String.format("%2d", 4) + " + "
                + String.format("%2d", 12) + " = " + 16, result.toString());
    }

    @Test
    public void returnsRightIfNoPlayer() {
        Player result = stats.search("Pekka");
        assertEquals(null, result);
    }

    @Test
    public void returnsRightTeamMembers() {
        List<Player> result = stats.team("EDM");
        assertEquals("Semenko", result.get(0).getName());
        assertEquals("Kurri", result.get(1).getName());
        assertEquals("Gretzky", result.get(2).getName());
    }
    
    @Test
    public void correctTopScorers(){
        List<Player> result = stats.topScorers(3);
        assertEquals("Gretzky", result.get(0).getName());
        assertEquals("Lemieux", result.get(1).getName());
        assertEquals("Yzerman", result.get(2).getName());
    }

}

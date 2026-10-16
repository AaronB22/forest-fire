import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FireTest {
    @Test
    public void testTimeToBurnExample() {
        char[][] forest = {
            {'t','.','.','t','t','t','t','.','t'},
            {'.','.','t','t','.','.','.','.','t'},
            {'.','.','t','t','t','t','t','t','t'},
            {'t','t','t','t','.','.','.','.','.'}
        };

        int matchR = 2;
        int matchC = 6;

        int expected = 8;
        int actual = Fire.timeToBurn(forest, matchR, matchC);

        assertEquals(expected, actual);
    }

    @Test
    public void testTimeToBurnExample_FireWithoutTree() {
        char[][] forest = {
            {'t','.','.','t','t','t','t','.','t'},
            {'.','.','t','t','.','.','.','.','t'},
            {'.','.','t','t','t','t','t','t','t'},
            {'t','t','t','t','.','.','.','.','.'}
        };

        int matchR = 1;
        int matchC = 1;

        assertThrows(IllegalArgumentException.class, () -> {
            Fire.timeToBurn(forest, matchR, matchC);
        });
    }

    @Test
    public void testTimeToBurn25() {
        char[][] forest = {
            {'t','t','t','t','t'},
            {'t','t','t','t','t'},
            {'t','t','t','t','t'},
            {'t','t','t','t','t'},
            {'t','t','t','t','t'}
        };

        int matchR = 2;
        int matchC = 2;

        int expected = 4;
        int actual = Fire.timeToBurn(forest, matchR, matchC);

        assertEquals(expected, actual);
    }

    @Test
    public void testTimeToBurnSpiral5x5() {
        char[][] forest = {
            {'t','.','t','t','t'},
            {'t','.','t','.','t'},
            {'t','.','t','.','t'},
            {'t','.','.','.','t'},
            {'t','t','t','t','t'}
        };

        int matchR = 0;
        int matchC = 0;

        int expected = 16;
        int actual = Fire.timeToBurn(forest, matchR, matchC);

        assertEquals(expected, actual);
    }

    @Test 
    public void  testFindNextTree(){
        char[][] forest = {
                { 't', '.', '.', 't', 't', 't', 't', '.', 't' },
                { '.', '.', 't', 't', '.', '.', '.', '.', 't' },
                { '.', '.', 't', 't', 't', 't', 't', 't', 't' },
                { 't', 't', 't', 't', '.', '.', '.', '.', '.' }
        };
        List<Location> treeNeighbors = new ArrayList<>();
        Location treeStart = new Location(3,0);

        treeNeighbors.add(treeStart);
  
        List<Location> actual = Fire.findNextTree(forest,4,0);
        assertEquals(treeNeighbors,actual);
        
    }
    // Add more tests!
}

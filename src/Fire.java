import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

public class Fire {
    /**
     * Returns how long it takes for all vulnerable trees to be set on fire if a
     * match is lit at a given location.
     * 
     * The forest is represented via a rectangular 2d char array where t represents
     * a tree
     * and . represents an empty space.
     * 
     * At time 0, the tree at location [matchR, matchC] is set on fire. At every
     * subsequent
     * time step, any tree that is adjacent (up/down/left/right) to a burning tree
     * is also
     * set on fire.
     * 
     * 
     * EXAMPLE
     * forest:
     * 
     * t..tttt.t
     * ..tt....t
     * ..ttttttt
     * tttt.....
     * 
     * matchR: 2
     * matchC: 6
     * 
     * Result: 8
     * 
     * Explanation:
     * At time 0, the tree at (2, 6) is set on fire. At time 1, its adjacent trees
     * also catch on fire
     * At time 2, the trees adjacent to those catch as well. At time 8, the last
     * tree to catch is at
     * (0,6). In this example, there is one tree that never burns, so it is not
     * included in the time calculation.
     * 
     * 
     * @param forest a 2d array where t represents a tree and . represents the
     *               ground
     * @param matchR The row the match is lit at
     * @param matchC The column the match is lit at
     * @return the time at which the final tree to be incinerated starts burning
     */
    public static int timeToBurn(char[][] forest, int matchR, int matchC) {
        // HINT: when adding to your BFS queue, you can include more information than
        // just a location. What other information might be useful?

        Map<Location,Integer> burnt = new HashMap<>();
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(matchR,matchC));

        while(!queue.isEmpty()){
            Location current = queue.remove();
            if(burnt.containsKey(current))continue;
            burnt.put(current,0);

            List<Location> neighbors = findNextTree(forest, current.row(),current.col());

            int lastDepth = -1;
            for(Location neighbor : neighbors){
                if(burnt.containsKey(neighbor)){
                    lastDepth = (lastDepth > 0) ? Math.min(lastDepth, burnt.get(neighbor)) : burnt.get(neighbor);
                }
                queue.add(neighbor);
            }
            burnt.put(current,lastDepth+1);
        }


        // Implement this AND add more tests!!!
        int depth = 0;
        for(int value : burnt.values()){
            depth = Math.max(depth,value);
        }
        return depth;
    }

    public static List<Location> findNextTree(char[][] forest, int matchR, int matchC){
        int[][] moves=new int[][]{
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
        };
        List<Location> treeNeighbors= new ArrayList<>();
        for(int[] move: moves){
            Location possibleTree= new Location(matchR+move[0], matchC+move[1]);
                        if(possibleTree.row()>=0&& possibleTree.row()< forest.length &&
                possibleTree.col()>=0 && possibleTree.col()< forest[0].length &&
                forest[possibleTree.row()][possibleTree.col()] !='.'){
                    treeNeighbors.add(possibleTree);
                }
        }
        return treeNeighbors;
    }
}
package assign07;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

public class GraphUtilTest {
    /**
     * -----------32-----------
     * |                      ∨
     * a---4.3---> b---10---> c---2.2---> d <---2.0---f
     * |                      ^
     * 2                      |
     * ∨                      |
     * e---->----1.1---->------
     */

    List<Character> src = List.of('a', 'a', 'a', 'b', 'c', 'e', 'f', 'g');
    List<Character> dst = List.of('e', 'c', 'b', 'c', 'd', 'c', 'd', 'e');
    List<Double> wght = List.of(2.0, 32.0, 4.3, 10.0, 2.2, 1.1, 2.2, 1.0);

    List<Integer> src2 = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 0, 1, 2, 3, 4, 5, 6, 7,
            8, 0, 1, 2, 3, 4, 5, 6, 7, 8);
    List<Integer> dst2 = List.of(7, 9, 9, 8, 6, 8, 9, 9, 9, 1, 3, 7, 4, 8, 9, 9, 9,
            9, 9, 3, 8, 7, 7, 6, 8, 8, 9);

    List<Character> src3 = List.of('a', 'c', 'b', 'c', 'e', 'f', 'g');
    List<Character> dst3 = List.of('b', 'a', 'c', 'd', 'c', 'd', 'e');
    List<Double> wght3 = List.of(2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0);

    List<Character> src4 = List.of('a', 'b', 'c', 'd');
    List<Character> dst4 = List.of('b', 'c', 'a', 'a');

    List<String> src5 = List.of("a", "a", "b", "b", "e", "d", "d", "c");
    List<String> dst5 = List.of("b", "d", "a", "e", "d", "b", "c", "a");

    private final Graph<Integer> denseGraph = new Graph<>(src2, dst2);
    private final Graph<Character> weightedGraph = new Graph<>(src, dst, wght);
    private final Graph<Character> graph = new Graph<>(src, dst);
    private final Graph<Character> sameWeightedGraph = new Graph<>(src3, dst3, wght3);
    private final Graph<Character> cycleGraph = new Graph<>(src4, dst4);
    private final Graph<String> newGraph = new Graph<>(src5, dst5);

    private final Random rng = new Random();

    @Test
    void testShortestPath() {
        List<Character> expected = List.of('a', 'c', 'd');
        Character srcData = 'a';
        Character dstData = 'd';
        assertEquals(expected, GraphUtility.shortestPath(src, dst, srcData, dstData));
    }

    @Test
    void testBFSWeighted() {
        List<Character> expected = List.of('a', 'e', 'c', 'd');
        assertEquals(expected, weightedGraph.BFSWeighted('a', 'd'));
    }

    @Test
    void testBFSWeightedNoPath() {
        assertThrows(IllegalArgumentException.class, () -> weightedGraph.BFSWeighted('a', 'f'));
    }

    @Test
    void testDense() {
        List<Integer> expected = List.of(1, 3, 7);
        assertEquals(expected, denseGraph.BFSWeighted(1, 7));
    }

    @Test
    void testCyclic() {
        List<Character> expected = List.of('a', 'b', 'c', 'd');
        assertEquals(expected, sameWeightedGraph.BFSWeighted('a', 'd'));
    }

    @Test
    void testNoPath() {
        assertThrows(IllegalArgumentException.class, () -> graph.BFS('a', 'f'));
    }

    @Test
    void testSort() {
        List<Character> expected = List.of('a', 'f', 'g', 'b', 'e', 'c', 'd');
        assertEquals(expected, graph.topoSort());
    }

    @Test
    void testSortCycle() {
        assertThrows(IllegalArgumentException.class, cycleGraph::topoSort);
    }

    @Test
    void testGraph() {
        List<String> expected = List.of("d", "b", "e");
        assertEquals(expected, newGraph.BFS("d", "e"));
    }
}
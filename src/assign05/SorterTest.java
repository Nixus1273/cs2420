package assign05;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class SorterTest {
    private RandomPivotChooser randomChooser = new RandomPivotChooser();
    private MedianOfThreePivotChooser medianChooser = new MedianOfThreePivotChooser();
    private FirstPivotChooser chooser = new FirstPivotChooser();

    private QuickSorter quickSorter = new QuickSorter(chooser);
    private QuickSorter medianSorter = new QuickSorter(medianChooser);
    private QuickSorter randomSorter = new QuickSorter(randomChooser);

    QuickSorter<String> qs = new QuickSorter<>((list, leftIndex, rightIndex) -> rightIndex);

    private MergeSorter mergeSorter = new MergeSorter(2);

    private final ArrayList<String> stringSimilarList = new ArrayList<>(Arrays.asList(
            "123", "321", "456", "645", "101", "110", "112"));
    private final ArrayList<String> sixList = new ArrayList<>(Arrays.asList(
            "6", "1", "1", "1", "1", "1"));
    private final ArrayList<Integer> threeList = new ArrayList<>(Arrays.asList(
            6, 1, 1, 0, -1, 22, 22, 3, 6, 3));
    private final ArrayList<String> emptyList = new ArrayList<>();


    @Test
    void testQuickSort() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList(
                "101", "110", "112", "123", "321", "456", "645"));
        qs.sort(stringSimilarList);
        assertEquals(expected, stringSimilarList);
    }

    @Test
    void testQuickSortFirstPivot() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList(
                "1", "1", "1", "1", "1", "6"));
        quickSorter.sort(sixList);
        assertEquals(expected, sixList);
    }

    @Test
    void testMedianPivot() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 6, 6));
        assertEquals(1, medianChooser.getPivotIndex(list, 0, 2));
    }

    @Test
    void testQuickSortMedian() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList(
                "101", "110", "112", "123", "321", "456", "645"));
        medianSorter.sort(stringSimilarList);
        assertEquals(expected, stringSimilarList);
    }

    @Test
    void testRandomPivot() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        for (int i = 0; i < 100; i++) {
            System.out.println(randomChooser.getPivotIndex(list, 0, 10));
        }
    }

    @Test
    void testMergeSortFirst() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList(
                "101", "110", "112", "123", "321", "456", "645"));
        mergeSorter.sort(stringSimilarList);
        assertEquals(expected, stringSimilarList);
    }

    @Test
    void testMergeSort() {
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(
                -1, 0, 1, 1, 3, 3, 6, 6, 22, 22));
        mergeSorter.sort(threeList);
        assertEquals(expected, threeList);
    }

    @Test
    void testQuickSortRandomEmpty() {
        assertThrows(NoSuchElementException.class,
                () -> randomSorter.sort(emptyList));
    }

    @Test
    void testNearlyAscendingArray(){
        QuickSortNearlySortedTimingExperiment timing = new QuickSortNearlySortedTimingExperiment();
        timing.populateNearlyAscendingArrayList(1000);
        System.out.println(timing.list);
    }
}
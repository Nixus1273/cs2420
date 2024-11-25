package assign09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class StudentHashCollisionExperiment extends CollisionExperiment {
    private final static String problemSizeDescription = "listSize";
    private final static int problemSizeMin = 100;
    private final static int problemSizeCount = 25;
    private final static int problemSizeStep = 50;
    private final static int experimentIterationCount = 50;

    HashTable<StudentBadHash, Integer> badHashTable = new HashTable<>();
    HashTable<StudentMediumHash, Integer> medHashTable  = new HashTable<>();
    HashTable<StudentGoodHash, Integer> goodHashTable = new HashTable<>();

    private final Random rng = new Random();
    private final ArrayList<StudentBadHash> collectionBad = new ArrayList<>();
    private final ArrayList<StudentMediumHash> collectionMed = new ArrayList<>();
    private final ArrayList<StudentGoodHash> collectionGood = new ArrayList<>();

    public static void main(String[] args) {
        CollisionExperiment timingExperiment = new StudentHashTimingExperiment();
        timingExperiment.printResults();
    }

    public StudentHashCollisionExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        collectionBad.clear();
        collectionMed.clear();
        collectionGood.clear();
        for(int i = 0; i < problemSize ; i++) {
//            collectionBad.add(new StudentBadHash(i,
//                                String.valueOf(i),
//                                ""));

//            collectionMed.add(new StudentMedHash(i,
//                                String.valueOf(rng.nextInt(problemSize)),
//                                ""));

            collectionGood.add(new StudentGoodHash(i,
                    String.valueOf(rng.nextInt(problemSize)),
                    ""));
        }
    }

    @Override
    protected void runComputation() {
//        for(StudentBadHash element : collectionBad) {
//            badHashTable.put(element, 1);
//        }

//        for(StudentMediumHash element : collectionMed) {
//            medHashTable.put(element, 1);
//        }

        for(StudentGoodHash element : collectionGood) {
            goodHashTable.put(element, 1);
        }


    }

    @Override
    protected int getCollisions() {
        return 0;
    }
}
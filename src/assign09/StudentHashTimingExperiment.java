package assign09;

import java.util.ArrayList;
import java.util.Random;

public class StudentHashTimingExperiment extends CollisionExperiment {
    private final static String problemSizeDescription = "listSize";
    private final static int problemSizeMin = 1000;
    private final static int problemSizeCount = 20;
    private final static int problemSizeStep = 500;
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

    public StudentHashTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        collectionBad.clear();
        badHashTable.clear();

        collectionMed.clear();
        medHashTable.clear();

        collectionGood.clear();
        goodHashTable.clear();

        for(int i = 0; i < problemSize ; i++) {
//            collectionBad.add(new StudentBadHash(rng.nextInt(problemSize),
//                                String.valueOf(i),
//                                ""));

//            collectionMed.add(new StudentMediumHash(rng.nextInt(problemSize),
//                                String.valueOf(rng.nextInt(problemSize)),
//                                ""));

            collectionGood.add(new StudentGoodHash(rng.nextInt(problemSize),
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

        for (StudentGoodHash element : collectionGood) {
            goodHashTable.put(element, 1);
        }
    }



    @Override
    protected int getCollisions() {
//        return badHashTable.collisions();
//        return medHashTable.collisions();
        return goodHashTable.collisions();
    }
}

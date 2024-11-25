package notes1;

import java.util.Random;

/**
 * Demo class to explore the impact of hash functions and load factors on hash tables.
 *
 * @author Aaron Wood
 * @version 2024-10-31
 */
public class HashTableDemo {
    private static final Random rng = new Random();

    public static void main(String[] args) {
        compareHashCodeImpact(80);
    }

    /**
     * Compare hash tables of various sizes with a fixed backing array capacity and hash function.
     */
    private static void compareLoadFactorImpact() {
        for (int size = 20; size <= 80; size += 20) {
            CustomString[] strings = new CustomString[size];
            for (int i = 0; i < size; i++) {
                strings[i] = new CustomString(randomString());
            }

            runExperiment(strings, 100);
        }
    }

    /**
     * Compare hash tables of various hash functions with a fixed load factor.
     * @implNote - the load factor is size / 100
     * @param size - number of elements in hash table
     */
    private static void compareHashCodeImpact(int size) {
        CustomString[] badHashStrings = new CustomStringBadHash[size];
        CustomString[] mediumHashStrings = new CustomStringMediumHash[size];
        CustomString[] goodHashStrings = new CustomStringGoodHash[size];
        CustomString[] javaHashStrings = new CustomString[size];

        for (int i = 0; i < size; i++) {
            String string = randomString();
            badHashStrings[i] = new CustomStringBadHash(string);
            mediumHashStrings[i] = new CustomStringMediumHash(string);
            goodHashStrings[i] = new CustomStringGoodHash(string);
            javaHashStrings[i] = new CustomString(string);
        }

        runExperiment(badHashStrings, 100);
        runExperiment(mediumHashStrings, 100);
        runExperiment(goodHashStrings, 100);
        runExperiment(javaHashStrings, 100);
    }

    /**
     * Run an experiment of building and searching a hash table.
     *      - Adds the strings provided to the hash table
     *      - Computes the size, load factor, and number of collisions
     *      - Searches for 25 random elements, computing the average number of comparisons per search
     * @param strings - strings to add to the hash table
     * @param capacity - capacity of the hash table's backing array
     */
    private static void runExperiment(CustomString[] strings, int capacity) {
        HashTable hashTable = new HashTable(capacity);
        for (CustomString string : strings) {
            hashTable.add(string);
        }
        System.out.println("HashTable with key type: " + strings[0].getClass().getSimpleName());
        System.out.println("\tsize:        " + hashTable.size());
        System.out.println("\tload factor: " + hashTable.loadFactor());
        System.out.println("\tcollisions:  " + hashTable.collisionCount() + " while building");

        int comparisons = 0;
        for (int i = 0; i < 25; i++) {
            CustomString string = strings[rng.nextInt(strings.length)];
            hashTable.contains(string);
            comparisons += hashTable.comparisonCount();
        }
        System.out.println("\tcomparisons: " + comparisons / 25.0 + " per search");
    }

    /**
     * @return random string of letters of length 4-20
     */
    private static String randomString() {
        int length = rng.nextInt(4, 20);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            // lowercase letter
            builder.append((char) rng.nextInt(97, 123));
        }
        return builder.toString();
    }
}

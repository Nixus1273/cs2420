package comprehensive;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //TODO: change file location!
        Glossary glossary = createGlossary(args[0]);
//        Glossary glossary = createGlossary("C:\\Users\\Ethan Laynor\\Desktop\\Intellij\\cs2420\\2420_glossary.txt");

        Scanner scanner = new Scanner(System.in);
        boolean application  = true;


        while (application ) {
            printMainMenu();

            int choice;
            while (true) {
                System.out.print("Select an option: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    break;
                } else {
                    System.out.println("Invalid input");
                    scanner.next();
                }
            }

            switch (choice) {
                case 1 -> option1(glossary);
                case 2 -> option2(glossary, scanner);
                case 3 -> option3(glossary, scanner);
                case 4 -> System.out.println(glossary.getFirst());
                case 5 -> System.out.println(glossary.getLast());
                case 6 -> option6(glossary, scanner);
                case 7 -> option7(glossary, scanner);
                case 8 -> option8(glossary, scanner);
                case 9 -> option9(glossary, scanner);
                case 10 -> option10(glossary,scanner);
                case 11 -> {
                    System.out.println(" - Exiting application...");
                    scanner.close();
                    application = false;
                }
                default -> System.out.println("Invalid input. Try again..");
            }
        }
    }

    public static Glossary createGlossary(String file) throws IOException{
        Glossary glossary = new Glossary();

        try {
            List<String> lines = Files.readAllLines(Paths.get(file));

            // build glossary from the file
            for (String line : lines) {
                String[] data = line.split("::");
                glossary.add(data[0], data[1], data[2]);
            }

        } catch (IOException e) {
            throw new IOException("File not found...");
        }
        return glossary;
    }

    public static void option1(Glossary glossary){
        String[] data = glossary.getMetadata();
        System.out.println("words: " + data[0] + "\n" +
                "definitions: " + data[1] + "\n" +
                "definitions per word: " +
                String.format("%.3f", Double.parseDouble(data[1]) / Double.parseDouble(data[0])) + "\n" +
                "parts of speech: " + data[2] + "\n" +
                "first word: " + data[3] + "\n" +
                "last word: " + data[4] + "\n");
    }


    public static void option2(Glossary glossary, Scanner scanner){
        scanner.nextLine(); //Buffer Clear

        System.out.print("Starting word: ");
        String start = scanner.nextLine();
        if(wordNotInGlossary(glossary, start)){
            return;
        }

        System.out.print("Ending word: ");
        String end = scanner.nextLine();
        if(wordNotInGlossary(glossary, end)){
            return;
        }

        System.out.println("\nThe words between " + start + " and " + end + " are: ");
        StringBuilder returnRange = new StringBuilder();

        for (String word: glossary.getInRange(start, end)) {
            returnRange.append("\t").append(word).append("\n");
        }
        System.out.println(returnRange);
    }

    
    public static void option3(Glossary glossary, Scanner scanner){
        scanner.nextLine(); //Buffer Clear

        System.out.print("\nSelect a word: ");
        String userSelection = scanner.nextLine();
        if(wordNotInGlossary(glossary, userSelection)){
            return;
        }

        System.out.println(glossary.get(userSelection));
    }

    
    public static void option4(Glossary glossary){

    }

    
    public static void option5(Glossary glossary){

    }
    
    // Gets all parts of speech for a given word
    public static void option6(Glossary glossary, Scanner scanner){
        scanner.nextLine();
        System.out.print("\nSelect a word: ");
        String userSelection = scanner.nextLine();
        if(wordNotInGlossary(glossary, userSelection)){
            return;
        }

        for (String s : glossary.getPOS(userSelection)) {
            System.out.println("\t" + s + ".");
        }
    }

    public static void option7(Glossary glossary, Scanner scanner){
        scanner.nextLine(); //Buffer Clear

        System.out.print("Select a word: ");
        String userSelection = scanner.nextLine();
        if(wordNotInGlossary(glossary, userSelection)){
            return;
        }

        ArrayList<Definitions.Definition> defs = glossary.getDef(userSelection);
        int numberChoices = printDefinitions(glossary, userSelection, defs);

        System.out.print("\nSelect a definition to update: ");

        int defSelection = getValidSelection(scanner, numberChoices);
        if (defSelection == -1){
                System.out.println("Invalid selection");
                System.out.println(" - Returning to the main menu...\n");
                return;
        }

        if (defSelection == numberChoices) {
            System.out.println(" - Returning to the main menu...\n");
            return;
        }

        scanner.nextLine(); //Buffer Clear
        System.out.print("\nType a new definition: ");
        String newDef = scanner.nextLine();

        glossary.changeDef(userSelection, defs.get(defSelection - 1), newDef);
    }


    public static void option8(Glossary glossary, Scanner scanner){
        scanner.nextLine(); //Buffer Clear

        System.out.print("\nSelect a word: ");
        String userSelection = scanner.nextLine();
        if(wordNotInGlossary(glossary, userSelection)){
            return;
        }

        ArrayList<Definitions.Definition> defsRemove = glossary.getDef(userSelection);

        StringBuilder returnDefs1 = new StringBuilder(userSelection + "\n");
        int k = 1;
        for (Definitions.Definition def : defsRemove) {
            returnDefs1.append("\t").append(k).append(". ").append(def.POS()).
                    append(".\t").append(def.def()).append("\n");
            k++;
        }

        returnDefs1.append("\t").append(k).append(". ").append("Back to main menu\n");
        System.out.print(returnDefs1);

        System.out.print("\nSelect a definition to remove: ");
        int userRemoveSelection = scanner.nextInt();
        //TODO Doesn't work
        if (userRemoveSelection == k)
            return;

        glossary.removeDef(userSelection, defsRemove.get(userRemoveSelection - 1));

        //TODO print out if a definition or a word has been deleted - check example of logic proposition
    }

    
    public static void option9(Glossary glossary, Scanner scanner){
        scanner.nextLine();
        System.out.print("\nType a word: ");
        String userSelection = scanner.nextLine();
        System.out.print("\nValid parts of speech: [noun, verb, adj, adv, pron, prep, conj, interj]\n");

        ArrayList<String> validPOS = new ArrayList<>(Arrays.asList(
                "adj", "adv", "conj", "interj", "noun", "prep", "pron", "verb"));

        String newEntryPOS;
        do {
            System.out.print("Type a valid part of speech: ");
            newEntryPOS = scanner.nextLine();
        } while (!validPOS.contains(newEntryPOS));

        System.out.print("\nType a definition: ");
        String newEntryDef = scanner.nextLine();

        if (glossary.contains(userSelection)) {
            glossary.addDef(userSelection, newEntryPOS, newEntryDef);
        } else {
            glossary.add(userSelection, newEntryPOS, newEntryDef);
        }

        System.out.println("Successfully added!\n");
    }

    //TODO Check IOEx error - does it have to be in main method?
    public static void option10(Glossary glossary, Scanner scanner) throws IOException {
        scanner.nextLine(); //Buffer Clear
        System.out.print("Type a filename with path: ");
        String fileName = scanner.nextLine();

        //User confirmation of the file path

        try{
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);


            //for every word in the glossary
                 //for every def in the word
                    //printWriter.println(word::pos::def);


            for(String word : glossary.getAllWords()){
                ArrayList<Definitions.Definition> definitions = glossary.getDef(word);
                for(Definitions.Definition def : definitions){
                    printWriter.println(word + "::" + def.POS() + "::" + def.def());
                 }
            }

            printWriter.close();

        }catch(IOException e){
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
            System.out.println(" - Returning to the main menu...\n");
        }
    }

    public static void printMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1.  Get metadata");
        System.out.println("2.  Get words in range");
        System.out.println("3.  Get word");
        System.out.println("4.  Get first word");
        System.out.println("5.  Get last word");
        System.out.println("6.  Get parts of speech");
        System.out.println("7.  Update definition");
        System.out.println("8.  Delete definition");
        System.out.println("9.  Add new definition");
        System.out.println("10.  Save dictionary");
        System.out.println("11.  Quit\n");
    }

    public static String checkUserWordInput(Glossary glossary, Scanner scanner, String input){
        //TODO If the user inputted a non correct Word will this ask for another word or go back to main menu?
        //It would need a stop feature if looping because the user might not know what is in the gloss...



        return null;
    }

    public static boolean wordNotInGlossary(Glossary glossary, String word){
        if(!glossary.contains(word)){
            System.out.println(" - Word ['" + word + "'] not found in the glossary. Please try again.");
            System.out.println(" - Returning to the main menu...\n");
            return true;
        }

        return false;

    }

    public static int printDefinitions(Glossary glossary, String word, ArrayList<Definitions.Definition> defs) {
        StringBuilder returnDefs = new StringBuilder("\nDefinitions for " + word + "\n");
        int i = 1;
        for (Definitions.Definition def : defs) {
            returnDefs.append("\t").append(i).append(". ").append(def.POS()).
                    append(".\t").append(def.def()).append("\n");
            i++;
        }
        returnDefs.append("\t").append(i).append(". ").append("Back to main menu\n");
        System.out.print(returnDefs);
        return i;

    }

    public static int getValidSelection(Scanner scanner, int numberChoices) {
        if (!scanner.hasNextInt()) {
            scanner.next();
            return -1;
        }

        int defSelection = scanner.nextInt();
        if (defSelection < 1 || defSelection > numberChoices) {
            return -1;
        }

        return defSelection;
    }
}

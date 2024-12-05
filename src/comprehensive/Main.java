package comprehensive;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Main class to interact with the Glossary application.
 * Provides a menu-based interface to manage a glossary of words and their definitions.
 *
 * @author - Brandon Flickinger and Ethan Laynor
 * @version - December 5, 2024
 */
public class Main {
    /**
     * Handles the initialization of the glossary application and manages the main user interaction loop.
     *
     * @param args Command-line arguments. If a file path is provided, it attempts to load a glossary from the file.
     */
    public static void main(String[] args){
        Glossary glossary = new Glossary();

        boolean application = false;

        if (args.length == 1) {
            try {
                application = createGlossary(args[0], glossary);
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file -  " + e.getMessage());
                System.out.println(" - Exiting Application...\n");
            }
        }

        while (application) {
            printMainMenu();

            int choice;
            do {
                System.out.print("Select an option: ");
                choice = getValidSelection(11);

                if (choice == -1) {
                    System.out.println("\nInvalid selection\n");
                }
            } while (choice == -1);

            switch (choice) {
                case 1 -> option1(glossary);
                case 2 -> option2(glossary);
                case 3 -> option3(glossary);
                case 4 -> option4(glossary);
                case 5 -> option5(glossary);
                case 6 -> option6(glossary);
                case 7 -> option7(glossary);
                case 8 -> option8(glossary);
                case 9 -> option9(glossary);
                case 10 -> {
                    try{
                        option10(glossary);
                    } catch (IOException e){
                        System.out.println("\nFile not found\n");
                    }
                }
                case 11 -> {
                    System.out.println(" - Exiting application...");
                    application = false;
                }
                default -> System.out.println("Invalid input. Try again.");
            }
        }
    }

    /**
     * Loads a glossary from a file.
     *
     * @param file     The file path containing the glossary data.
     * @param glossary The Glossary object to populate.
     * @return True if the glossary was successfully loaded; false otherwise.
     * @throws IOException If the file cannot be found or read.
     */
    public static boolean createGlossary(String file, Glossary glossary) throws IOException {
        try {
            List<String> lines = Files.readAllLines(Paths.get(file));
            for (String line : lines) {
                String[] data = line.split("::");
                glossary.add(data[0], data[1], data[2]);
            }
            return true;

        } catch (IOException e) {
            throw new IOException("File not found..." + e.getMessage());
        }
    }

    /**
     * Prints the metadata of the glossary.
     *
     * @param glossary The Glossary object to retrieve metadata from.
     */
    public static void option1(Glossary glossary){
        System.out.println("\n" + glossary.getMetadata());
    }

    /**
     * Displays all words in the glossary within a specified range.
     *
     * @param glossary The Glossary object to retrieve words from.
     */
    public static void option2(Glossary glossary){
        System.out.print("Starting word: ");
        String start = userInput();

        System.out.print("Ending word: ");
        String end = userInput();

        System.out.println("\nThe words between " + start + " and " + end + " are -  ");
        System.out.println(glossary.getInRange(start, end));
    }

    /**
     * Displays all definitions for a user-selected word in the glossary.
     *
     * @param glossary The Glossary object to retrieve definitions from.
     */
    public static void option3(Glossary glossary){
        System.out.print("Select a word: ");
        String userSelection = userInput();
        if(wordNotInGlossary(glossary, userSelection))return;
        System.out.println("\n" + glossary.getAllDefs(userSelection));
    }

    /**
     * Displays the first word and its definitions in the glossary.
     *
     * @param glossary The Glossary object to retrieve the first word from.
     */
    public static void option4(Glossary glossary){
        System.out.println("\n" + glossary.getFirstDefs());
    }

    /**
     * Displays the last word and its definitions in the glossary.
     *
     * @param glossary The Glossary object to retrieve the last word from.
     */
    public static void option5(Glossary glossary){
        System.out.println("\n" + glossary.getLastDefs());
    }

    /**
     * Displays all parts of speech for a user-selected word.
     *
     * @param glossary The Glossary object to retrieve parts of speech from.
     */
    public static void option6(Glossary glossary){
        System.out.print("Select a word: ");
        String userSelection = userInput();
        if(wordNotInGlossary(glossary, userSelection)) return;

        System.out.println("\n" + glossary.getPOS(userSelection));
    }

    /**
     * Allows the user to update a specific definition for a selected word.
     *
     * @param glossary The Glossary object containing the word and definition to update.
     */
    public static void option7(Glossary glossary){
        System.out.print("Select a word: ");
        String userSelection = userInput();
        if(wordNotInGlossary(glossary, userSelection)) return;

        ArrayList<Definitions.SingleDefinition> defs = glossary.getDefList(userSelection);
        int numberChoices = printDefinitions(userSelection, defs);

        System.out.print("\nSelect a definition to update: ");

        int defSelection = getValidSelection(numberChoices);
        while(defSelection == -1){
            System.out.println("- Invalid selection");
            numberChoices = printDefinitions(userSelection, defs);
            System.out.print("\nSelect a definition to update: ");
            defSelection = getValidSelection(numberChoices);
        }

        if (defSelection == numberChoices) {
            System.out.println(" - Returning to the main menu...\n");
            return;
        }

        System.out.print("Type a new definition: ");
        String newDef = userInput();

        glossary.changeDef(userSelection, defs.get(defSelection - 1), newDef);
        System.out.println("\nDefinition updated\n");
    }

    /**
     * Allows the user to delete a specific definition or an entire word from the glossary.
     *
     * @param glossary The Glossary object containing the word and definition to delete.
     */
    public static void option8(Glossary glossary){
        System.out.print("Select a word: ");
        String userSelection = userInput();
        if(wordNotInGlossary(glossary, userSelection)) return;


        ArrayList<Definitions.SingleDefinition> defs = glossary.getDefList(userSelection);
        int numberChoices = printDefinitions(userSelection, defs);

        System.out.print("\nSelect a definition to remove: ");
        int defSelection = getValidSelection(numberChoices);

        while(defSelection == -1){
            System.out.println("- Invalid selection");
            numberChoices = printDefinitions(userSelection, defs);
            System.out.print("\nSelect a definition to update: ");
            defSelection = getValidSelection(numberChoices);
        }

        if (defSelection == numberChoices) {
            System.out.println(" - Returning to the main menu...\n");
            return;
        }

        System.out.println("\nDefinition removed");
        if(glossary.removeDef(userSelection, defs.get(defSelection - 1)))
            System.out.println(userSelection +  " removed");
        System.out.println();
    }

    /**
     * Allows the user to add a new word, part of speech, and definition to the glossary.
     *
     * @param glossary The Glossary object to which the new entry will be added.
     */
    public static void option9(Glossary glossary){
        System.out.print("Type a word: ");
        String userSelection = userInput();
        System.out.print("Valid parts of speech: [noun, verb, adj, adv, pron, prep, conj, interj]\n");

        ArrayList<String> validPOS = new ArrayList<>(Arrays.asList(
                "adj", "adv", "conj", "interj", "noun", "prep", "pron", "verb"));

        String newEntryPOS;
        do {
            System.out.print("Type a valid part of speech: ");
            newEntryPOS = userInput();
        } while (!validPOS.contains(newEntryPOS));

        System.out.print("Type a definition: ");
        String newEntryDef = userInput();

        glossary.add(userSelection, newEntryPOS, newEntryDef);
        System.out.println("\nSuccessfully added!\n");
    }

    /**
     * Saves the current glossary to a specified file.
     *
     * @param glossary The Glossary object containing the words and definitions to save.
     * @throws IOException If an error occurs while writing to the file.
     */
    public static void option10(Glossary glossary) throws IOException {
        System.out.print("Type a filename with path: ");
        String fileName = userInput();

        try{
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for(String word : glossary.getAllWordsList()){
                ArrayList<Definitions.SingleDefinition> definitions = glossary.getDefList(word);
                for(Definitions.SingleDefinition def : definitions){
                    printWriter.println(word + "::" + def.POS() + "::" + def.def());
                }
            }

            System.out.println("\nSuccessfully saved dictionary to " + fileName + "\n");
            printWriter.close();

        } catch (IOException e){
            throw new IOException(e);
        }
    }

    /**
     * Prints the main menu options for the application.
     */
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

    /**
     * Checks if a word exists in the glossary.
     *
     * @param glossary The Glossary object to search for the word.
     * @param word     The word to check for existence in the glossary.
     * @return True if the word is not found in the glossary; false otherwise.
     */
    public static boolean wordNotInGlossary(Glossary glossary, String word){
        if(!glossary.contains(word)){
            System.out.println("\n" + word  + " not found\n");
            return true;
        }
        return false;
    }

    /**
     * Prints the list of definitions for a given word and provides an option to return to the main menu.
     *
     * @param word The word for which definitions will be printed.
     * @param defs A list of definitions associated with the word.
     * @return The number of choices printed, including the "Back to main menu" option.
     */
    public static int printDefinitions(String word, ArrayList<Definitions.SingleDefinition> defs) {
        StringBuilder returnDefs = new StringBuilder("\nDefinitions for " + word + "\n");
        int i = 1;
        for (Definitions.SingleDefinition def : defs) {
            returnDefs.append("\t").append(i).append(". ").append(def.POS()).
                    append(".\t").append(def.def()).append("\n");
            i++;
        }
        returnDefs.append("\t").append(i).append(". ").append("Back to main menu\n");
        System.out.print(returnDefs);
        return i;
    }

    /**
     * Validates a numeric input for a menu selection.
     *
     * @param numberChoices The number of valid menu options.
     * @return The validated choice if valid; -1 otherwise.
     */
    public static int getValidSelection(int numberChoices) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        if (!input.matches("\\d+")) {
            return -1;
        }

        int defSelection = Integer.parseInt(input);
        if (defSelection < 1 || defSelection > numberChoices) {
            return -1;
        }
        return defSelection;
    }

    /**
     * Retrieves user input from the console.
     *
     * @return The user's input as a string.
     */
    public static String userInput(){
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}

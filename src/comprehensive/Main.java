package comprehensive;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Glossary glossary = new Glossary();

        boolean application = false;

        try {
            application = createGlossary(args[0], glossary);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
            System.out.println(" - Exiting Application...\n");
        }


        while (application) {
            printMainMenu();

            int choice;
            do {
                System.out.print("Select an option: ");
                choice = getValidSelection(11);

                if (choice == -1) {
                    System.out.println(" - Invalid input. Please try again...\n");
                }
            } while (choice == -1);


            switch (choice) {
                case 1 -> option1(glossary);
                case 2 -> option2(glossary);
                case 3 -> option3(glossary);
                case 4 -> option4(glossary);
                case 5 -> option5(glossary);
                case 6 -> option6(glossary, scanner);
                case 7 -> option7(glossary, scanner);
                case 8 -> option8(glossary, scanner);
                case 9 -> option9(glossary, scanner);
                case 10 -> {
                    try{
                        option10(glossary, scanner);
                    } catch (IOException e){
                        System.out.println("\nFile not found" + e +"\n");
                    }
                }
                case 11 -> {
                    System.out.println(" - Exiting application...");
                    scanner.close();
                    application = false;
                }
                default -> System.out.println("Invalid input. Try again..");

            }

        }
        scanner.close();
    }


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



    public static void option1(Glossary glossary){
        System.out.println("\n" + glossary.getMetadata());
    }

    //TODO Check Formating of words that aren't in list.
    public static void option2(Glossary glossary){


        System.out.print("Starting word: ");
        String start = userInput();
        if(wordNotInGlossary(glossary, start)) return;


        System.out.print("Ending word: ");
        String end = userInput();
        if(wordNotInGlossary(glossary, end)) return;


        System.out.println("\nThe words between " + start + " and " + end + " are: ");
        System.out.println(glossary.getInRange(start, end));
    }

    
    public static void option3(Glossary glossary){


        System.out.print("Select a word: ");
        String userSelection = userInput();
        if(wordNotInGlossary(glossary, userSelection))return;


        System.out.println("\n" + glossary.getAllDefs(userSelection));
    }

    
    public static void option4(Glossary glossary){
        System.out.println("\n" + glossary.getFirstDefs());
    }

    
    public static void option5(Glossary glossary){
        System.out.println("\n" + glossary.getLastDefs());
    }
    
    // Gets all parts of speech for a given word
    public static void option6(Glossary glossary, Scanner scanner){

        System.out.print("Select a word: ");
        String userSelection = userInput();
        if(wordNotInGlossary(glossary, userSelection)) return;

        System.out.println("\n" + glossary.getPOS(userSelection));
    }


    //TODO Needs to reshow Def after invaild input
    public static void option7(Glossary glossary, Scanner scanner){

        System.out.print("Select a word: ");
        String userSelection = userInput();
        if(wordNotInGlossary(glossary, userSelection)) return;

        //TODO would defs ever be empty?
        ArrayList<Definitions.SingleDefinition> defs = glossary.getDefList(userSelection);
        int numberChoices = printDefinitions(userSelection, defs);

        System.out.print("\nSelect a definition to update: ");

        //TODO if defSelection is invaild should we ask promt for another input? Because if they want to leave they could use main menu
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


    public static void option8(Glossary glossary, Scanner scanner){

        System.out.print("Select a word: ");
        String userSelection = scanner.nextLine();
        if(wordNotInGlossary(glossary, userSelection)) return;


        ArrayList<Definitions.SingleDefinition> defs = glossary.getDefList(userSelection);
        int numberChoices = printDefinitions(userSelection, defs);

        System.out.print("\nSelect a definition to remove: ");
        int defSelection = getValidSelection(numberChoices);

        if (defSelection == -1){
            System.out.println("Invalid selection");

            return;
        }


        //TODO Keep or remove if causing errors
        if (defSelection == numberChoices) {
            System.out.println(" - Returning to the main menu...\n");
            return;
        }

        System.out.println("\nDefinition removed");
        if(glossary.removeDef(userSelection, defs.get(defSelection - 1)))
            System.out.println(userSelection +  " removed");

        System.out.println();

    }

    
    public static void option9(Glossary glossary, Scanner scanner){
        System.out.print("Type a word: ");
        String userSelection = scanner.nextLine();
        System.out.print("Valid parts of speech: [noun, verb, adj, adv, pron, prep, conj, interj]\n");

        ArrayList<String> validPOS = new ArrayList<>(Arrays.asList(
                "adj", "adv", "conj", "interj", "noun", "prep", "pron", "verb"));

        String newEntryPOS;
        do {
            System.out.print("Type a valid part of speech: ");
            newEntryPOS = scanner.nextLine();
        } while (!validPOS.contains(newEntryPOS));

        System.out.print("Type a definition: ");
        String newEntryDef = scanner.nextLine();

        glossary.add(userSelection, newEntryPOS, newEntryDef);


        System.out.println("\nSuccessfully added!\n");
    }

    public static void option10(Glossary glossary, Scanner scanner) throws IOException {
        System.out.print("Type a filename with path: ");
        String fileName = scanner.nextLine();

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
            System.out.println("\nSuccessfully saved dictionary to [" + fileName + "]\n");
            printWriter.close();

        }catch(IOException e){
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
            System.out.println(" - Returning to the main menu...\n");

        }

    }
//    public static void option10(Glossary glossary, Scanner scanner) throws IOException {
//        System.out.print("Type a filename with path: ");
//        String fileName = scanner.nextLine();
//        File file = new File(fileName);
//
//
//        Path path = Paths.get(fileName);
//        if (!Files.exists(path)) {
//            throw new IOException("HERE" + fileName);
//        }else{
//
//            try(BufferedWriter buffWriter = new BufferedWriter(new FileWriter(file))) {
//
//                for (String word : glossary.getAllWordsList()) {
//                    ArrayList<Definitions.SingleDefinition> definitions = glossary.getDefList(word);
//                    for (Definitions.SingleDefinition def : definitions) {
//                        buffWriter.write(word + "::" + def.POS() + "::" + def.def() + "\n");
//                    }
//                }
//                System.out.println("\nSuccessfully saved dictionary to " + fileName + "\n");
//
//            } catch (IOException e) {
//                throw new IOException("KSDMALK" + e.getMessage());
//            }
//
//        }
//
//    }
//    public static void option10(Glossary glossary, Scanner scanner) throws IOException {
//        System.out.print("Type a filename with path: ");
//        String fileName = scanner.nextLine();
//        File file = new File(fileName);
//
//        if(file.exists() && file.isFile()) {
//            try(BufferedWriter buffWriter = new BufferedWriter(new FileWriter(file))) {
//
//                for (String word : glossary.getAllWordsList()) {
//                    ArrayList<Definitions.SingleDefinition> definitions = glossary.getDefList(word);
//                    for (Definitions.SingleDefinition def : definitions) {
//                        buffWriter.write(word + "::" + def.POS() + "::" + def.def() + "\n");
//                    }
//                }
//                System.out.println("\nSuccessfully saved dictionary to " + fileName + "\n");
//
//            } catch (Exception e) {
//                throw new FileNotFoundException(e.getMessage());
//            }
//        }else{
//            throw new FileNotFoundException(fileName);
//
//        }
//
//
//
//    }



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


    public static boolean wordNotInGlossary(Glossary glossary, String word){
        if(!glossary.contains(word)){
            System.out.println("\n" + word  + " not found\n");
            return true;
        }

        return false;
    }


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

    public static String userInput(){
        Scanner input = new Scanner(System.in);

        return input.nextLine();
    }

    //TODO must have colon space

}

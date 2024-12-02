package comprehensive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Glossary glossary = new Glossary();
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(args[0]));
        } catch (IOException e) {
            throw new IOException("File not found");
        }

        // build glossary from the file
        for (String line : lines) {
            String[] data = line.split("::");
            glossary.add(data[0], data[1], data[2]);
        }


        Scanner scanner = new Scanner(System.in);
        boolean on = true;
        while (on) {
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

            String userSelection;
            switch (choice) {
                case 1:
                    System.out.println("\n" + glossary.getMetadata());
                    break;
                case 2:
                    scanner.nextLine();

                    System.out.print("\nStarting word: ");
                    String start = scanner.nextLine();

                    System.out.print("Ending word: ");
                    String end = scanner.nextLine();

                    System.out.println("The words between " + start + " and " + end + " are: ");
                    System.out.println("\t" + glossary.getWordsInRange(start, end));
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.print("\nSelect a word: ");
                    userSelection = scanner.nextLine();

                    System.out.println(glossary.getWord(userSelection));
                    break;
                case 4:
                    System.out.println(glossary.getFirst());
                    break;
                case 5:
                    System.out.println(glossary.getLast());
                    break;
                case 6:
                    scanner.nextLine();
                    System.out.print("\nSelect a word: ");
                    userSelection = scanner.nextLine();

                    System.out.println(glossary.getPOS(userSelection));
                    break;
                case 7:
                    scanner.nextLine();
                    System.out.print("\nSelect a word: ");
                    userSelection = scanner.nextLine();
                    if (!glossary.contains(userSelection)) {
                        System.out.println(userSelection + " not found\n");
                        break;
                    }

                    ArrayList<Word.Definition> defs = glossary.getDef(userSelection);

                    StringBuilder returnDefs = new StringBuilder(userSelection + "\n");
                    int i = 1;
                    for (Word.Definition def : defs) {
                        returnDefs.append("\t").append(i).append(". ").append(def.POS()).
                                append(".\t").append(def.def()).append("\n");
                        i++;
                    }
                    returnDefs.append("\t").append(i).append(". ").append("Back to main menu\n");
                    System.out.print(returnDefs);

                    System.out.print("\nSelect a definition to update: ");
                    int defSelection = scanner.nextInt();
                    if (defSelection == i)
                        break;

                    scanner.nextLine();
                    System.out.print("\nType a new definition: ");
                    String newDef = scanner.nextLine();

                    glossary.changeDef(userSelection, defs.get(defSelection - 1), newDef);
                    break;
                case 8:
                    scanner.nextLine();
                    System.out.print("\nSelect a word: ");
                    userSelection = scanner.nextLine();
                    if (!glossary.contains(userSelection)) {
                        System.out.println(userSelection + " not found\n");
                        break;
                    }

                    ArrayList<Word.Definition> defsRemove = glossary.getDef(userSelection);

                    StringBuilder returnDefs1 = new StringBuilder(userSelection + "\n");
                    int k = 1;
                    for (Word.Definition def : defsRemove) {
                        returnDefs1.append("\t").append(k).append(". ").append(def.POS()).
                                append(".\t").append(def.def()).append("\n");
                        k++;
                    }

                    returnDefs1.append("\t").append(k).append(". ").append("Back to main menu\n");
                    System.out.print(returnDefs1);

                    System.out.print("\nSelect a definition to remove: ");
                    int userRemoveSelection = scanner.nextInt();
                    if (userRemoveSelection == k)
                        break;

                    glossary.removeDef(userSelection, defsRemove.get(userRemoveSelection - 1));
                    break;
                case 9:
                    scanner.nextLine();
                    System.out.print("\nType a word: ");
                    userSelection = scanner.nextLine();
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
                    break;
                case 10:
                    //TODO: rewrite all the definitions to the file
                    break;
                case 11:
                    System.out.println("Exiting...");
                    scanner.close();
                    on = false;
                    break;
                default:
                    System.out.println("Input out of range");
                    break;
            }
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
        System.out.println("11.  Quit");
    }

    public static String printDefinitions(String word, ArrayList<Word.Definition> defs) {
        StringBuilder returnDefs = new StringBuilder(word + "\n");
        int i = 1;
        for (Word.Definition def : defs) {
            returnDefs.append("\t").append(i).append(". ").append(def.POS()).
                    append(".\t").append(def.def()).append("\n");
            i++;
        }
        returnDefs.append("\t").append(i).append(". ").append("Back to main menu\n");
        System.out.print(returnDefs);
        return returnDefs.toString();
    }
//     possible helper methods: printMainMenu, getUserWordInput (check if the word exists, etc.), printDefinitions,
}

//1

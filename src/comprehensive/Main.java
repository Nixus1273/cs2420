package comprehensive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Glossary glossary = new Glossary();
        List<String> lines;

        try {
            lines = Files.readAllLines(Paths.get(args[0]));
        }catch(IOException e) {
            throw new IOException("File not found");
        }

        for (String line : lines) {
            String[] data = line.split("::");
            glossary.add(data[0], data[1], data[2]);
        }


        Scanner scanner = new Scanner(System.in);

        boolean on = true;
        while (on) {
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

            System.out.print("Select an option: "); // TODO: what if not a number?

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Selection must be an integer.");
            }


            switch (choice) {
                case 1:
                    System.out.println("\n" + glossary.getMetadata());
                    break;
                case 2:
                    scanner.nextLine();

                    System.out.print("\nStarting word: ");
                    String firstWord = scanner.nextLine();


                    System.out.print("Ending word: ");
                    String endingWord = scanner.nextLine();

                    System.out.println("\n" + glossary.wordInRange(firstWord, endingWord));
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.print("\nSelect a word: ");
                    String userSelectionsWord = scanner.nextLine();

                    System.out.println(glossary.getWord(userSelectionsWord));

                    break;
                case 4:
                    System.out.println(glossary.getWordFirst());
                    break;
                case 5:
                    System.out.println(glossary.getWordLast());
                    break;
                case 6:
                    scanner.nextLine();
                    System.out.print("\nSelect a word: ");
                    String userSelectionsPOS = scanner.nextLine();

                    System.out.println(glossary.getWordPOS(userSelectionsPOS));
                    break;
                case 7:
                    scanner.nextLine();
                    System.out.print("\nSelect a word: ");
                    String userSelectionsDef = scanner.nextLine();
                    if (!glossary.containsWord(userSelectionsDef)) {
                        System.out.println(userSelectionsDef + " not found\n");
                        break;
                    }

                    ArrayList<Word.Definition> defs = glossary.getWordDef(userSelectionsDef);

                    String returnDefs = userSelectionsDef + "\n";
                    int i = 1;
                    for (Word.Definition def : defs) {
                        returnDefs += "\t" + i + ". " + def.getPOS() + ".\t" + def.getDef() + "\n";
                        i++;
                    }

                    returnDefs += "\t" + i + ". " + "Back to main menu\n";
                    System.out.print(returnDefs);


                    System.out.print("\nSelect a definition to update: ");
                    int userChoice = scanner.nextInt();
                    if (userChoice == i)
                        break;

                    scanner.nextLine();
                    System.out.print("\nType a new definition: ");
                    String userNewDef = scanner.nextLine();

                    glossary.changeDefOfWord(userSelectionsDef, defs.get(userChoice - 1), userNewDef);
                    break;
                case 8:
                    scanner.nextLine();
                    System.out.print("\nSelect a word: ");
                    String userSelectionsRemove = scanner.nextLine();
                    if (!glossary.containsWord(userSelectionsRemove)) {
                        System.out.println(userSelectionsRemove + " not found\n");
                        break;
                    }

                    ArrayList<Word.Definition> defsRemove = glossary.getWordDef(userSelectionsRemove);

                    String returnDefsRemove = userSelectionsRemove + "\n";
                    int k = 1;
                    for (Word.Definition def : defsRemove) {
                        returnDefsRemove += "\t" + k + ". " + def.getPOS() + ".\t" + def.getDef() + "\n";
                        k++;
                    }

                    returnDefsRemove += "\t" + k + ". " + "Back to main menu\n";
                    System.out.print(returnDefsRemove);


                    System.out.print("\nSelect a definition to remove: ");
                    int userChoiceRemove = scanner.nextInt();
                    if (userChoiceRemove == k)
                        break;

                    glossary.removeDefOfWord(userSelectionsRemove, defsRemove.get(userChoiceRemove - 1));
                    break;
                case 9:
                    scanner.nextLine();
                    System.out.print("\nType a word: ");
                    String newEntryWord = scanner.nextLine();
                    System.out.print("\nValid parts of speech: [noun, verb, adj, adv, pron, prep, conj, interj]\n");

                    ArrayList<String> validPOS = new ArrayList<>(Arrays.asList(
                            "adj", "adv", "conj", "interj", "noun", "prep", "pron", "verb"));
                    String newEntryPOS;
                    do {
                        System.out.print("\nType a valid part of speech: ");
                        newEntryPOS = scanner.nextLine();
                    } while (!validPOS.contains(newEntryPOS));


                    //TODO Check POS is valid
                    System.out.print("\nType a definition: ");
                    String newEntryDef = scanner.nextLine();

                    if (glossary.containsWord(newEntryWord)) {
                        glossary.addDefOfWord(newEntryWord, newEntryPOS, newEntryDef);
                    } else {
                        glossary.add(newEntryWord, newEntryPOS, newEntryDef);
                    }

                    System.out.println("Successfully added!\n");
                    break;
                case 10:
                    break;
                case 11:
                    System.out.println("Exiting...");
                    scanner.close();
                    on = false;
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
//     possible helper methods: printMainMenu, getUserWordInput (check if the word exists, etc.), printDefinitions,
}

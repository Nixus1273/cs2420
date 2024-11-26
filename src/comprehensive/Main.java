package comprehensive;

import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        // TODO: create the data structure from the file
        TreeMap<String, String> glossary = new TreeMap<>();

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

            System.out.print("Select an option: "); // TODO: what if they put in not a number?
            try {
                int choice = scanner.nextInt();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Selection must be an integer.");
            }

            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
//                    String userSelections = scanner.nextLine("Select a word: ");
//                    Word word = glossary.get(userSelections);
//                    if(){
//
//                    }
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    // TODO: find a way to access the definition without having to use the whole thing?
                    break;
                case 9:
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
}

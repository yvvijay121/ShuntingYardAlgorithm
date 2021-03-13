import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.print("Enter equation: ");
        String input = scanner.nextLine();  // Read user input
        System.out.println("Input is: " + input);
        ArrayList<String> output = Parser.parse(input);
        StringBuilder outputString = new StringBuilder();
        for (String s : output) outputString.append(s).append(" ");
        System.out.println("Output is: " + outputString);
    }
}

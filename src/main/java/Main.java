import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.print("Enter equation: ");
        String input = scanner.nextLine();  // Read user input
        System.out.println("Input is: " + input);
        Object[] output = Parser.parse(input);
        System.out.println("Output is: " + Arrays.toString(output));
    }
}

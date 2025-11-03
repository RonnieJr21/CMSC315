import java.util.*;

public class Substring {

    public static void main(String[] args) {
        System.out.print("Please enter a string: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String longest = "";
        String current = "";

        for (int i = 0; i < input.length(); i++) {
            // Start or extend the current substring
            if (i == 0 || input.charAt(i) > input.charAt(i - 1)) {
                current += input.charAt(i);
            } else {
                // Reset when the order is broken
                current = "" + input.charAt(i);
            }

            // Update longest if needed
            if (current.length() > longest.length()) {
                longest = current;
            }
        }

        System.out.println("Maximum consecutive increasingly ordered substring: " + longest);
    }
}

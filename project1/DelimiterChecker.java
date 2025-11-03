package project1;

/**
 * Ronnie Huggins
 * CMSC 315 Programming Project 1
 * Date: October 22, 2025
 * 
 * Description:
 * This class contains the main method that prompts the user for a
 * Java source file, uses a stack to check for matching delimiters,
 * and reports mismatches with their line and character positions.
 */

import java.io.*;
import java.util.*;

public class DelimiterChecker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SourceFile source = null;

        // Step 1: Read a valid file name
        while (source == null) {
            System.out.print("Enter the Java source file name: ");
            String fileName = input.nextLine();
            try {
                source = new SourceFile(fileName);
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Please try again.");
            }
        }

        Stack<Character> stack = new Stack<>();

        try {
            Character c;
            while ((c = source.getNextCharacter()) != null) {
                if (isLeftDelimiter(c)) {
                    stack.push(c);
                } else if (isRightDelimiter(c)) {
                    if (stack.isEmpty()) {
                        System.out.println("Unmatched closing delimiter '" + c + "' at " + source.getPosition());
                        return;
                    }

                    char top = stack.pop();
                    if (!isMatchingPair(top, c)) {
                        System.out.println("Mismatched delimiters: expected match for '" + top + "', but found '" + c + "' at " + source.getPosition());
                        return;
                    }
                }
            }

            if (!stack.isEmpty()) {
                System.out.println("Unmatched opening delimiter(s) remaining: " + stack);
            } else {
                System.out.println("All delimiters match correctly!");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static boolean isLeftDelimiter(char c) {
        return c == '(' || c == '{' || c == '[';
    }

    private static boolean isRightDelimiter(char c) {
        return c == ')' || c == '}' || c == ']';
    }

    private static boolean isMatchingPair(char left, char right) {
        return (left == '(' && right == ')') ||
               (left == '{' && right == '}') ||
               (left == '[' && right == ']');
    }
}

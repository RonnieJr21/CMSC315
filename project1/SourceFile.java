// package declaration removed — use the default package
package project1;

/**
 * Ronnie Huggins
 * CMSC 315 Programming Project 1
 * Date: October 22, 2025
 * 
 * Description:
 * This class encapsulates a Java source file and provides methods to
 * retrieve characters one at a time, skipping comments, string literals,
 * and character literals. It also tracks line and character numbers.
 */

import java.io.*;
public class SourceFile {
    private BufferedReader reader;
    private int lineNumber;
    private int charPosition;
    private String currentLine;

    public SourceFile(String fileName) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(fileName));
        lineNumber = 0;
        charPosition = 0;
        currentLine = "";
    }

    // Returns the next valid character (skipping comments, strings, chars)
    public Character getNextCharacter() throws IOException {
        while (true) {
            // If current line is empty or fully read, move to next line
            if (currentLine == null || charPosition >= currentLine.length()) {
                currentLine = reader.readLine();
                if (currentLine == null)
                    return null; // End of file
                lineNumber++;
                charPosition = 0;
                continue;
            }

            char c = currentLine.charAt(charPosition++);
            
            // Skip comments and literals
            if (c == '/') {
                if (charPosition < currentLine.length()) {
                    char next = currentLine.charAt(charPosition);
                    if (next == '/') { // Single-line comment
                        currentLine = "";
                        continue;
                    } else if (next == '*') { // Multi-line comment
                        skipBlockComment();
                        continue;
                    }
                }
            } else if (c == '"') { // Skip string literal
                skipStringLiteral();
                continue;
            } else if (c == '\'') { // Skip char literal
                skipCharLiteral();
                continue;
            }

            return c;
        }
    }

    private void skipBlockComment() throws IOException {
        charPosition += 2;
        while (true) {
            if (charPosition >= currentLine.length()) {
                currentLine = reader.readLine();
                if (currentLine == null) break;
                lineNumber++;
                charPosition = 0;
            } else if (currentLine.charAt(charPosition) == '*' &&
                       charPosition + 1 < currentLine.length() &&
                       currentLine.charAt(charPosition + 1) == '/') {
                charPosition += 2;
                return;
            } else {
                charPosition++;
            }
        }
    }

    private void skipStringLiteral() {
        while (charPosition < currentLine.length()) {
            char c = currentLine.charAt(charPosition++);
            if (c == '\\' && charPosition < currentLine.length()) charPosition++; // Skip escaped char
            else if (c == '"') return;
        }
    }

    private void skipCharLiteral() {
        while (charPosition < currentLine.length()) {
            char c = currentLine.charAt(charPosition++);
            if (c == '\\' && charPosition < currentLine.length()) charPosition++; // Skip escaped char
            else if (c == '\'') return;
        }
    }

    // Returns line and character info for error messages
    public String getPosition() {
        return "Line " + lineNumber + ", Character " + charPosition;
    }
}

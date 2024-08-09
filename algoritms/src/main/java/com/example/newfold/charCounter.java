package com.example.newfold;


/**
 * This class provides a method to count the occurrences of each character in a string
 * and returns the result in the format "countCharacter".
 */
public class charCounter {


    public static void main(String[] args) {

        // Example 1: Regular case with repeated characters
        String madWord1 = "VVVVVTTTTKKKVV";
        String result1 = countCharacterLineal(madWord1);
        System.out.println("Input: " + madWord1 + " -> Output: " + result1); // Expected: 5V4T3K2V

        // Example 2: String with single characters
        String madWord2 = "ABCDE";
        String result2 = countCharacterLineal(madWord2);
        System.out.println("Input: " + madWord2 + " -> Output: " + result2); // Expected: 1A1B1C1D1E

        // Example 3: String with mixed repetition
        String madWord3 = "AABBBCCCCDDDEEE";
        String result3 = countCharacterLineal(madWord3);
        System.out.println("Input: " + madWord3 + " -> Output: " + result3); // Expected: 2A3B4C3D3E

        // Example 4: String with all identical characters
        String madWord4 = "ZZZZZZ";
        String result4 = countCharacterLineal(madWord4);
        System.out.println("Input: " + madWord4 + " -> Output: " + result4); // Expected: 6Z

        // Example 5: Empty string case
        String madWord5 = "";
        String result5 = countCharacterLineal(madWord5);
        System.out.println("Input: \"" + madWord5 + "\" -> Output: \"" + result5 + "\""); // Expected: ""

    }

    /**
     * Counts the occurrences of each character in the input string and returns
     * the result in the format "countCharacter", such as "5V4T3K2V".
     *
     * @param madWord the input string containing characters to be counted
     * @return a formatted string with each character and its count
     */
    public static String countCharacterLineal(String madWord) {
        int counter = 0;
        char currentChar = ' ';
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < madWord.length(); i++) {
            char c = madWord.charAt(i);

            if (currentChar != c) {
                if (counter > 0) {
                    result.append(counter).append(currentChar);
                }
                currentChar = c;
                counter = 1;
            } else {
                counter++;
            }
        }

        // Append the last character and its count
        if (counter > 0) {
            result.append(counter).append(currentChar);
        }

        return result.toString();
    }

}

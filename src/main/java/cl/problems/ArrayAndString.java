package cl.problems;

public class ArrayAndString {

    // Is Unique
    // Implement an algorithm to determine if a string has all unique characters.
    // Don't use additional data structures
    public boolean isUnique(String input) {

        StringBuilder sb = new StringBuilder();

        for (char currentChar : input.toCharArray()) {
            if (sb.toString().contains(Character.toString(currentChar))) {
                return false;
            }

            sb.append(currentChar);
        }

        return true;
    }


    // Check Permutation
    // Given two strings, write a method to decide if one is a permutation of the other
    public boolean checkPermutation(String one, String two) {

        if (one.length() != two.length()) {
            return false;
        }

        StringBuilder sb = new StringBuilder(two);

        for (char currentChar : one.toCharArray()) {
            int indexOf = sb.indexOf(Character.toString(currentChar));
            if (indexOf == -1) {
                return false;
            }

            sb.deleteCharAt(indexOf);
        }

        return true;
    }


    // URLify
    /*
    Write a method to replace all spaces in a string with '%20'. You may assume that the string
    has sufficient space at the end to hold the additional characters, and that you are given
    the "true" length of the string. (Note: If implementing in Java, please use a character array so
    that your can perform this operation in place

    EXAMPLE:
    Input: "Mr John Smith    ", 13
    Output: "Mr%20John%20Smith"
     */

    public String URLify(String textInput, int length) {
        StringBuilder sb = new StringBuilder(textInput);
        return sb.substring(0, length).replace(" ", "%20%");
    }

}

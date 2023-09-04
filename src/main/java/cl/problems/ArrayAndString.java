package cl.problems;

import java.util.Arrays;

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

    public boolean isUniqueSolution(String str) {
        if (str.length() > 128) return false;

        boolean[] char_set = new boolean[128];

        for (int i = 0; i < str.length() ; i++) {
            int val = str.charAt(i);
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
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

    // check permutation answer
    public String sort(String s) {
        char[] content = s.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }

    public boolean permutationSolution(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        return sort(s).equals(sort(t));
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

    // URLify solution
    public void replaceSpaces(char[] str, int trueLength) {
        int numberOfSpaces = countOfChar(str, 0, trueLength, ' ');
        int newIndex = trueLength - 1 + numberOfSpaces * 2;

        if (newIndex + 1 < str.length) str[newIndex + 1] = '\0';
        for (int oldIndex = trueLength - 1; oldIndex >= 0; oldIndex -= 1) {
            if (str[oldIndex] == '0') {
                str[newIndex] = '0';
                str[newIndex - 1] = '2';
                str[newIndex - 2] = '%';
                newIndex -= 3;
            } else {
                str[newIndex] = str[oldIndex];
                newIndex -= 1;
            }
        }
    }

    public int countOfChar(char[] str, int start, int end, int target) {
        int count = 0;
        for (int i = start; i < end; i++) {
            if (str[i] == target) {
                count++;
            }
        }
        return count;
    }

    // Palindrome Permutation
    /*
    Given a string, write a function to check if it is a permutation of a palindrome. A palindrome is a word of phrase
    that is the same forwards and backwards. A permutation is a rearrangement of letters. The palindrome does not need
    to be limited to just dictionary words. You can ignore casing and non-letter characters.

    EXAMPLE:
    Input: Tact Coa
    Output: True (permutations: "taco cat", "atco cta", etc.)
     */
    public boolean palindromePermutation(String s) {

        char[] chars = s.toCharArray();

        Arrays.sort(chars);

        int oddCount = 0;
        char auxChar = chars[0];
        int auxRepeatedChars = 0;

        for (char current : chars) {
            if (auxChar == current) {
                auxRepeatedChars++;
            } else {
                if (current == chars[chars.length - 1] && oddCount > 0) {
                    return false;
                }
                if (auxRepeatedChars % 2 != 0) {
                    oddCount++;
                    if (oddCount > 1) {
                        return false;
                    }
                }
                auxRepeatedChars = 1;
                auxChar = current;
            }
        }
        return true;
    }

    // palindrome permutation solution

    public boolean isPermutationOfPalindrome(String phrase) {
        int[] table = buildCharFrequencyTable(phrase);
        return checkMaxOneOdd(table);
    }

    public boolean checkMaxOneOdd(int[] table) {
        boolean foundOdd = false;
        for (int count: table) {
            if (count % 2 == 1) {
                if (foundOdd) {
                    return false;
                }
                foundOdd = true;
            }
        }
        return true;
    }

    public int getCharNumber(Character c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);
        if (a <= val && val <= z) {
            return val - a;
        }
        return -1;
    }

    public int[] buildCharFrequencyTable(String phrase) {
        int[] table = new int[Character.getNumericValue('z')
                - Character.getNumericValue('a') + 1];
        for (char c: phrase.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                table[x]++;
            }
        }
        return table;
    }


    // ONE AWAY
    /*
    There are three types of edits that can be performed on strings: insert a character, remove a character, or replace
    a character. Given two strings, write a function to check if they are one edit (or zero edits) away
    EXAMPLE:
    pale, ple -> true
    pales, pale -> true
    pale, bale -> true
    pale, bae -> false
     */
    boolean wasStringEdited(String original, String edited) {
        if (edited.length() < original.length() - 1 || edited.length() > original.length() + 1) {
            return false;
        }

        // insert --> pale, pales -> true  |  pale, spale -> true

        for (int i = 0; i < original.length(); i++) {
            if (original.length() < edited.length()) {
                StringBuilder sb = new StringBuilder(edited);
                if (i == original.length() - 1) {
                    sb.deleteCharAt(i + 1);
                    return sb.toString().equals(edited);
                }
                sb.deleteCharAt(i);
                if (sb.toString().equals(edited)) {
                    return true;
                }
            }
            if (original.length() == edited.length()) {
                StringBuilder sb = new StringBuilder(original);
                char currentChar = edited.charAt(i);
                sb.replace(i, i + 1, currentChar + "");
                if (sb.toString().equals(edited)) {
                    return true;
                }
            }
            if (original.length() > edited.length()) {
                StringBuilder sb = new StringBuilder(original);
                sb.deleteCharAt(i);
                if (sb.toString().equals(edited)) {
                    return true;
                }
            }
        }

        return false;
    }


    // One away solution

    boolean oneEditAway(String first, String second) {
        if (first.length() == second.length()) {
            return oneEditReplace(first, second);
        } else if (first.length() + 1 == second.length()) {
            return oneEditInsert(first, second);
        } else if (first.length() - 1 == second.length()) {
            return oneEditInsert(second, first);
        }
        return false;
    }

    boolean oneEditReplace(String s1, String s2) {
        boolean foundDifference = false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (foundDifference) {
                    return false;
                }
            }
            foundDifference = true;
        }
        return true;
    }

    boolean oneEditInsert(String s1, String s2) {
        int index1 = 0;
        int index2 = 0;
        while (index2 < s2.length() && index1 < s1.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                if (index1 != index2) {
                    return false;
                }
                index2++;
            } else {
                index1++;
                index2++;
            }
        }
        return true;
    }

    // One away solution #2

    boolean oneEditAwayAnotherWay(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }
        String s1 = first.length() < second.length() ? first : second;
        String s2 = first.length() < second.length() ? second : first;

        int index1 = 0;
        int index2 = 0;
        boolean foundDifference = false;

        while (index2 < s2.length() && index1 < s1.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                if (foundDifference) return false;

                foundDifference = true;

                if (s1.length() == s2.length()) {
                    index1++;
                }
            } else {
                index1++;
            }
            index2++;
        }
        return true;
    }

    // STRING COMPRESSION
    /*
    Implement a method to perform basic string compression using the counts of repeated characters. For example, the
    string aabcccccaaa would become a2b1c5a3. If the "compressed" string would not become smaller than the original
    string, your method should return the original string. You can assume the string has only uppercase and lowercase
    letters (a-z)
     */

    String compressString(String s) {
        StringBuilder sb = new StringBuilder();

        char auxChar = s.charAt(0);
        int charCount = -1;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] == auxChar) {
                charCount++;
                if (i == s.length() - 1) {
                    sb.append(auxChar);
                    sb.append(charCount + 1);
                }
            } else {
                sb.append(auxChar);
                sb.append(charCount + 1);
                auxChar = chars[i];
                charCount = 0;
            }
        }

        return sb.length() < s.length() ? sb.toString() : s;
    }


    // STRING COMPRESSION Solution

    String compress(String str) {
        StringBuilder compressed = new StringBuilder();
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }
        return compressed.length() < str.length() ? compressed.toString() : str;
    }


    // ROTATE MATRIX
    /*
    Given an image represented by an NxN matrix, where each pixel in the image is represented by an integer, write a
    method to rotate the image by 90 degrees. Can you do this in place?
     */

    int[][] rotateMatrix(int[][] matrix) {
        int[][] rotatedMatrix = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length ; i++) {
            for (int j = 0; j < matrix.length ; j++) {
                int current = matrix[i][j];
                rotatedMatrix[j][matrix.length - 1 - i] = current;
            }
        }

        return rotatedMatrix;
    }

}

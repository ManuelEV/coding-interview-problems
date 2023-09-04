package cl.problems;

public class Main {

    public static void main(String[] args) {

        System.out.println("Starting STRINGS/ARRAYS problems");

        ArrayAndString arrayAndString = new ArrayAndString();

        System.out.println(arrayAndString.isUnique("abclqw"));
        System.out.println(arrayAndString.checkPermutation("asdddd", "sadsqq"));
        System.out.println(arrayAndString.URLify("Mr John Smith    ", 13));
        System.out.println(arrayAndString.palindromePermutation("aasdd"));
        System.out.println(arrayAndString.wasStringEdited("pale", "pala"));
        System.out.println(arrayAndString.compressString("aabcccccaaa"));

        int[][] matrix = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        printMatrix(matrix);
        System.out.println();
        printMatrix(arrayAndString.rotateMatrix(matrix));


        System.out.println("STRING/ARRAYS problems has ended");


    }

    static void printMatrix (int[][] matrix) {
        for (int i=0; i < matrix.length; i++) {
            for (int j=0; j < matrix.length; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
    }

}
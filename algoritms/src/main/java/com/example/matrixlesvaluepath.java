package com.example;

public class matrixlesvaluepath {


    public static void main(String[] args) {
        // Declarar y poblar la matriz unidimensional
        int[] myArray = {3, 4, 9, 2, 5, 1, 5, 6, 5};

        // Asignar el valor de n (en este caso, n = 3)
        int n = 3;

        printMatrixLineal(myArray, n);
        // Encontrar el camino con la menor suma
        int[] result = findMinPath(myArray, n);

        // Imprimir el resultado
        for (int num : result) {
            System.out.print(num + " ");
        }
    }

    public static void printMatrixLineal(int[] myArray, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(myArray[i * n + j] + " ");
            }
            System.out.println();
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[] findMinPath(int[] myArray, int n) {
        // Crear una matriz bidimensional para almacenar las sumas acumulativas
        int[][] dp = new int[n][n];

        // Inicializar la primera columna de la matriz dp
        for (int i = 0; i < n; i++) {
            dp[i][0] = myArray[i * n];
//            System.out.println(dp[i][0]);
//            System.out.println("i: " + i + " \n");
        }
        System.out.println();
        System.out.println();
        // Calcular las sumas acumulativas
        for (int col = 1; col < n; col++) {
            for (int row = 0; row < n; row++) {
                int currentVal = myArray[row * n + col];
                System.out.println("currentvalue " + currentVal);
                int minSum = dp[row][col - 1];

                if (row - 1 >= 0) {
                    System.out.println(minSum + " vs" + dp[row - 1][col - 1]);
                    minSum = Math.min(minSum, dp[row - 1][col - 1]);
                }

                if (row + 1 < n) {
                    System.out.println(minSum + " vs" + dp[row + 1][col - 1]);
                    minSum = Math.min(minSum, dp[row + 1][col - 1]);
                }

                System.out.println("minSum " + minSum);
                dp[row][col] = currentVal + minSum;
                System.out.println("currentVal +minSum   " + dp[row][col]);

            }
        }

        // Imprimir la matriz
        printMatrix(dp);


        // Encontrar el camino con la menor suma
        int[] result = new int[n];
        int minRow = 0;

        for (int i = 1; i < n; i++) {
            if (dp[i][n - 1] < dp[minRow][n - 1]) {
                minRow = i;
            }
        }

        // Reconstruir el camino
        for (int col = n - 1; col >= 0; col--) {
            result[col] = myArray[minRow * n + col];
            System.out.println("-------------------");
            System.out.println("row " + minRow);
            System.out.println("col " + col);
            System.out.println("value " + result[col]);

            if (col > 0) {
                int minSum = dp[minRow][col - 1];
                if (minRow - 1 >= 0 && dp[minRow - 1][col - 1] < minSum) {
                    minRow--;
                } else if (minRow + 1 < n && dp[minRow + 1][col - 1] < minSum) {
                    minRow++;
                }
            }
        }

        return result;
    }

}

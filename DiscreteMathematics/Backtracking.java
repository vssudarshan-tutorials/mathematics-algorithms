import java.util.ArrayList;

public class Backtracking {

    //Input: square matrix size n.
    //Output : Number of diagonals that do not touch


    private static int zeroes;

    public static void solutionsToDiagonals(int n) {

        int[][] perm = new int[n][n];

        int nSol = (n * (n + 1)) / 2;
        nSol = n % 2 == 0 || n < 5 ? nSol : nSol + 1;

        zeroes = n * n - nSol;

       generatePerm(perm, 0, 0, n);
    }

    private static int count = 0;

    private static int[][] generatePerm(int[][] perm, int i, int j, int n) {

        if (i >= n) {

            printPerm(perm);
            return perm;
        }

        for (int k = 1; k <= 3; k++) {
            perm[i][j] = k;

            boolean flag = false;

            if (k == 3) {
                flag = true;
                count++;
            }

            if (canExtend(perm, i, j)) {


                if (j + 1 == n)
                    generatePerm(perm, i + 1, 0, n);
                else
                    generatePerm(perm, i, j + 1, n);
            }

            if (flag)
                count--;

            perm[i][j] = 0; //trivial

        }

        return perm;
    }

    private static void printPerm(int[][] perm) {
        for (int i = 0; i < perm.length; i++) {
            for (int j = 0; j < perm[0].length; j++)
                System.out.print(perm[i][j] + " ");
            System.out.print("\n");
        }
        System.out.println("*********************************************");


    }

    private static boolean canExtend(int[][] perm, int i, int j) {
        boolean flag = true;

        if (count > zeroes)
            return false;

        if (perm[i][j] == 3)
            return true;

        if (j - 1 >= 0)
            flag = ((perm[i][j - 1] == 3) || (perm[i][j] == perm[i][j - 1]));


        if (i - 1 >= 0) {
            flag = flag && ((perm[i - 1][j] == 3 || perm[i][j] == perm[i - 1][j]));


            if (perm[i][j] == 1) {
                if (j - 1 >= 0)
                    flag = flag && ((perm[i - 1][j - 1] == 3) || (perm[i][j] != perm[i - 1][j - 1]));
            } else if (perm[i][j] == 2) {
                if (j + 1 < perm.length)
                    flag = flag && ((perm[i - 1][j + 1] == 3) || (perm[i][j] != perm[i - 1][j + 1]));

            }

        }
        return flag;
    }


}



import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Matrix {


    public static void solLinearEquation(double[][] a, double[] b) {
        double[][] result = gaussianElimination(a, b);

        System.out.println("***Augmented Matrix***");
        for (double[] array : result) {
            for (double value : array)
                System.out.print(value + " ");
            System.out.println();
        }

        double[] ans = backSubstitution(result);

        if (ans == null)
            System.out.println("\nNo solutions");
        else {
            System.out.println("\nSolutions");
            for (int i = 0; i < ans.length; i++)
                System.out.println("x" + (i + 1) + " = " + ans[i]);
        }

    }

    //Transform the matrix into an upper triangular matrix

    private static double[][] gaussianElimination(double[][] lhs, double[] rhs) {

        int n = rhs.length;

        for (int i = 0; i < n - 1; i++) {

            if (lhs[i][i] == 0) {  //pivot must be non-zero
                rowSwap(lhs, rhs, i, n); //swap row i for row with max value in column i
            }

            double pivot = lhs[i][i];

            if (pivot == 0)
                continue;

            for (int j = i + 1; j < n; j++) {
                double alpha = -lhs[j][i] / pivot;

                if (alpha == 0)   //alpha must be non-zero factor
                    continue;

                rhs[j] += rhs[i] * alpha;

                for (int k = i; k < lhs[0].length; k++) {
                    lhs[j][k] += lhs[i][k] * alpha;
                }
            }
        }

        double[][] result = new double[n][n + 1];

        for (int i = 0; i < n; i++) {
            System.arraycopy(lhs[i], 0, result[i], 0, n); //construct augmented matrix
            result[i][n] = rhs[i];
        }

        return result; //return the augmented matrix
    }

    private static void rowSwap(double[][] lhs, double[] rhs, int i, int n) {

        double max = Math.abs(lhs[i][i]);
        int maxRow = i;

        for (int r = i + 1; r < n; r++)
            if (Math.abs(lhs[r][i]) > max) {
                max = Math.abs(lhs[r][i]);
                maxRow = r;
            }

        double[] temp = lhs[i];
        lhs[i] = lhs[maxRow];
        lhs[maxRow] = temp;

        max = rhs[maxRow];
        rhs[maxRow] = rhs[i];
        rhs[i] = max;

    }


    private static double[] backSubstitution(double[][] result) {

        int rows = result.length;
        int cols = result[0].length;
        double[] ans = new double[rows];

        if (result[rows - 1][cols - 2] == 0)
            return null;

        ans[rows - 1] = result[rows - 1][cols - 1] / result[rows - 1][cols - 2];

        for (int i = rows - 2; i >= 0; i--) {

            ans[i] = result[i][cols - 1];

            for (int j = cols - 2; j > i; j--) {
                ans[i] -= result[i][j] * ans[j];
            }

            if (result[i][i] == 0)
                return null;

            ans[i] /= result[i][i];

        }
        return ans;
    }

// Find inverse of a matrix using rref

    public static void inverseMatrix(double[][] mat) {

        double[][] result = rref(mat);
        int cols = result[0].length;

        //lhs part which is the identity matrix
        for (double[] doubles : result) {
            for (int j = 0; j < cols / 2; j++)
                System.out.print(doubles[j] + " \t");
            System.out.println();
        }
        System.out.println("\n***Inverse Matrix***\n");

        //rhs part which is the inverse
        for (double[] doubles : result) {
            for (int j = cols / 2; j < cols; j++)
                System.out.print(doubles[j] + " \t");
            System.out.println();
        }

    }


    private static double[][] rref(double[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        double[][] invMat = new double[rows][cols];

        //initialize as identity matrix
        for (int i = 0; i < mat.length; i++)
            invMat[i][i] = 1;

        for (int i = 0; i < rows; i++) {

            rowSwapRREF(mat, invMat, i, rows); //swap row i for row with max value in column i

            double pivot = mat[i][i];

            if (pivot == 0)
                continue;

            for (int j = 0; j < rows; j++) {

                if (j == i) //skip pivot row
                    continue;

                double alpha = -mat[j][i] / pivot;

                if (alpha == 0)   //alpha must be non-zero factor
                    continue;

                //calculate new row values
                for (int k = i; k < cols; k++) {
                    mat[j][k] += mat[i][k] * alpha;
                    invMat[j][k] += invMat[i][k] * alpha;
                }
            }
        }


        double[][] result = new double[rows][2 * cols];

        //construct augmented matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = mat[i][j];
                result[i][j + cols] = invMat[i][j];
            }
        }

        //compute final result by converting lhs to identity matrix
        for (int i = 0; i < rows; i++) {
            double pivot = result[i][i];
            if (pivot == 0)
                continue;
            for (int j = cols; j < 2 * cols; j++) {
                result[i][j] /= pivot;
            }

        }

        //round off to 1e4
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < 2 * cols; j++)
                result[i][j] = Math.round(result[i][j] * 1e4) / 1e4;

        return result; //return the augmented matrix
    }

    private static void rowSwapRREF(double[][] a, double[][] invMat, int i, int rows) {
        double max = Math.abs(a[i][i]);
        int maxRow = i;

        for (int r = i + 1; r < rows; r++)
            if (Math.abs(a[r][i]) > max) {
                max = Math.abs(a[r][i]);
                maxRow = r;
            }

        double[] temp = a[i];
        a[i] = a[maxRow];
        a[maxRow] = temp;

        temp = invMat[maxRow];
        invMat[maxRow] = invMat[i];
        invMat[i] = temp;

    }

    public static void solLinearLU(double[][] a, double[][] b) {


        double[] tempB = new double[b[0].length];
        double[][] tempA = new double[a.length][a[0].length];

        for (int i = 0; i < a.length; i++)
            System.arraycopy(a[i], 0, tempA[i], 0, tempA.length);

        System.arraycopy(b[0], 0, tempB, 0, tempB.length);

        //decompose A to LU form
        ArrayList<double[][]> lu = LUDecomposition(tempA, tempB);

        //print L and U matrices
        System.out.println("LU FORM");
        for (double[][] array : lu) {
            for (int i = 0; i < tempB.length; i++) {
                for (int j = 0; j < tempB.length; j++)
                    System.out.print((Math.round(array[i][j] * 1e4) / 1e4) + " \t");
                System.out.println();
            }
            System.out.println("\n");
        }

        //store solutions in an arraylist
        ArrayList<double[]> result = new ArrayList<>();

        //solution for the first system
        result.add(backSubstitution(lu.get(1), tempB));

        int count = 0;

        //solutions for other systems using LU form
        while (++count < b.length) {

            //correct row order for B
            for (int i = 0; i < b[0].length; i++) {
                tempB[i] = b[count][tracker[i] - 1];
            }

            //find solution for each new B
            result.add(solveB(lu.get(0), lu.get(1), tempB));
        }

        count = 0;

        //print solutions
        for (double[] ans : result) {
            System.out.println("Solution " + (++count));
            for (int i = 0; i < ans.length; i++)
                System.out.println("x" + (i + 1) + " = " + Math.round(ans[i] * 1e4) / 1e4);
            System.out.println();
        }

    }


    static int[] tracker;

    private static ArrayList<double[][]> LUDecomposition(double[][] a, double[] b) {

        int n = a.length;
        double[][] l = new double[n][n];

        tracker = new int[n];

        for (int i = 0; i < n; i++)
            tracker[i] = i + 1;

        //identity matrix
        for (int i = 0; i < n; i++)
            l[i][i] = 1;

        for (int i = 0; i < n - 1; i++) {

            rowSwapTracker(a, b, l, i, n, tracker); //swap row i for row with max value in column i

            double pivot = a[i][i];

            if (pivot == 0)
                continue;


            for (int j = i + 1; j < n; j++) {
                double alpha = a[j][i] / pivot;

                if (alpha == 0)   //alpha must be non-zero factor
                    continue;

                l[j][i] = alpha;
                b[j] -= b[i] * alpha;

                for (int k = i; k < n; k++) {
                    a[j][k] -= a[i][k] * alpha;
                }
            }
        }

        ArrayList<double[][]> result = new ArrayList<>();
        result.add(l);
        result.add(a);
        return result; //return lower and upper triangular matrix
    }


    private static void rowSwapTracker(double[][] lhs, double[] rhs, double[][] l, int i, int n, int[] tracker) {

        double max = Math.abs(lhs[i][i]);
        int maxRow = i;

        for (int r = i + 1; r < n; r++)
            if (Math.abs(lhs[r][i]) > max) {
                max = Math.abs(lhs[r][i]);
                maxRow = r;
            }

        int index = tracker[maxRow];
        tracker[maxRow] = tracker[i];
        tracker[i] = index;

        double[] temp = lhs[i];
        lhs[i] = lhs[maxRow];
        lhs[maxRow] = temp;

        max = rhs[maxRow];
        rhs[maxRow] = rhs[i];
        rhs[i] = max;

        for (int j = 0; j < i; j++) {
            max = l[maxRow][j];
            l[maxRow][j] = l[i][j];
            l[i][j] = max;
        }

    }


    private static double[] solveB(double[][] l, double[][] u, double[] b) {

        double[] y = forwardSubstitution(l, b);
        return backSubstitution(u, y);
    }

    private static double[] forwardSubstitution(double[][] l, double[] b) {
        int rows = l.length;
        double[] ans = new double[rows];

        if (l[0][0] == 0)
            return null;

        ans[0] = b[0] / l[0][0];

        for (int i = 1; i < rows; i++) {
            ans[i] = b[i];

            for (int j = 0; j < i; j++) {
                ans[i] -= l[i][j] * ans[j];
            }

            if (l[i][i] == 0)
                return null;

            ans[i] /= l[i][i];
        }

        return ans;
    }

    private static double[] backSubstitution(double[][] u, double[] y) {

        int rows = u.length;
        int cols = u[0].length;
        double[] ans = new double[rows];

        if (u[rows - 1][cols - 1] == 0)
            return null;

        ans[rows - 1] = y[rows - 1] / u[rows - 1][cols - 1];

        for (int i = rows - 2; i >= 0; i--) {

            ans[i] = y[i];

            for (int j = cols - 1; j > i; j--) {
                ans[i] -= u[i][j] * ans[j];
            }

            if (u[i][i] == 0)
                return null;

            ans[i] /= u[i][i];

        }
        return ans;
    }


    public static void bestLine(double[] x, double[] y) {

        double[][] a = new double[2][2];
        double[] b = new double[2];

        for (int i = 0; i < x.length; i++) {
            a[0][0] += 1;
            a[0][1] += x[i];
            a[1][1] += x[i] * x[i];
            b[0] += y[i];
            b[1] += x[i] * y[i];
        }
        a[1][0] = a[0][1];
        solLinearEquation(a, b);
    }

    public static void findDet(double[][] a) throws Exception {

        int size = 0;

        for (int i = 0; i < a.length; i++)
            size += a[i].length;

        if (size != a.length * a.length) {

            throw new Exception("Not Square Matrix Exception");
        }

        System.out.println("Determinant = " + determinant(a));
    }

    private static double determinant(double[][] a) throws Exception {


        //base case
        if (a.length == 2) {
            return (a[0][0] * a[1][1]) - (a[0][1] * a[1][0]);
        }

        //select best row or column for determinant
        int maxRow = 0, maxCol = 0, rowCount = 0, colCount = 0, maxRowCount = 0, maxColCount = 0;

        for (int i = 0; i < a.length; i++) {
            rowCount = 0;
            colCount = 0;
            for (int j = 0; j < a[0].length; j++) {

                if (a[i][j] == 0) {
                    rowCount++;
                }

                if (a[j][i] == 0) {
                    colCount++;
                }

            }
            if (rowCount > maxRow) {
                maxRow = i;
                maxRowCount = rowCount;
            }
            if (colCount > maxCol) {
                maxCol = i;
                maxColCount = colCount;
            }
        }

        double det = 0.0;
        if (maxRowCount >= maxColCount) {

            //when row is selected
            for (int i = 0; i < a[0].length; i++) {
                if (a[maxRow][i] == 0)
                    continue;

                //construct n-1 matrix
                double[][] temp = new double[a.length - 1][a[0].length - 1];

                for (int j = 0, l = 0; j < temp.length; j++, l++)
                    for (int k = 0, m = 0; k < temp[0].length; k++, m++) {

                        if (l == maxRow)  //skip element's row
                            l++;
                        if (m == i)       //skip element's column
                            m++;

                        temp[j][k] = a[l][m];
                    }
                //recursively calculate determinant
                det += Math.pow(-1, maxRow + i) * a[maxRow][i] * determinant(temp);

            }
        } else {
            //when column is selected
            for (int i = 0; i < a[0].length; i++) {
                if (a[i][maxCol] == 0)
                    continue;

                //construct n-1 matrix
                double[][] temp = new double[a.length - 1][a[0].length - 1];

                for (int j = 0, l = 0; j < temp.length; j++, l++)
                    for (int k = 0, m = 0; k < temp[0].length; k++, m++) {

                        if (l == i)
                            l++;
                        if (m == maxCol)
                            m++;

                        temp[j][k] = a[l][m];
                    }
                //recursively calculate determinant
                det += Math.pow(-1, maxRow + i) * a[i][maxCol] * determinant(temp);

            }
        }
        //return final value
        return det;
    }


}

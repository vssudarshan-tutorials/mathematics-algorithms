public class Main {
    public static void main(String[] args) {

//        int[][] removed = {{7, 0},{7,7}};
//        int n = Puzzles.tilesOnChessBoard(8, removed);
//        if (n == 1)
//            System.out.println("Tiled");
//        else if (n == -1)
//            System.out.println("Not Tiled");
//        for(int i=0;i<1001;i++) {
//            System.out.println(i + " = " + Puzzles.findNumber(i));
//        }

//        int[][] result = Puzzles.magicSquare(5);
//
//        if (result != null)
//            for (int i = 0; i < result.length; i++) {
//                for (int j = 0; j < result[0].length; j++)
//                    System.out.print(result[i][j] + "\t");
//                System.out.println("");
//            }
//        else
//            System.out.println("no magic");
//
//        ArrayList<Double> result = Puzzles.numberDivisibleBy(100, 9127, 6);
//        if (result != null)
//            for (Double ans : result)
//                System.out.println(ans);
//        else
//            System.out.println("no result");

        // System.out.println(Puzzles.numberWithRemainder(new int[]{2,3,4,5,6,7}, 1));
//
//        double[] result = Puzzles.findSquare(0);
//
//        for (Double ans : result)
//            System.out.println(ans);

        //   System.out.println(Puzzles.lcmOfArray(new long[]{2,3,4,5,6,7}));

        //    Puzzles.linearCombination(21,15);

        // System.out.println(Puzzles.gcdOfArray(new long[]{10,35,90}));

        // System.out.println(Puzzles.findQuantity(new long[]{3, 4, 5}, new long[]{2, 3, 4}));
        // Backtracking.solutionsToDiagonals(5);


        int[] result = Puzzles.sequenceOfSigns(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, -15);

        if (result != null)
            for (int n : result)
                System.out.print(n + " ");
        else
            System.out.println("No result");


     //  System.out.println(Puzzles.sequenceTransposition("post", "stoi"));

    }


}

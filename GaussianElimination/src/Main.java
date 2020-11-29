public class Main {

    public static void main(String[] args) throws Exception {

        double[][] a = {
                {-3, 2, -1},
                {6, -6, 7},
                {3, -4, 4}
        };
//        a = new double[][]{{0, 2, 3},
//                {2, 3, 7},
//                {1, 5, 6}};

//        a = new double[][]{{0, 2, 3},
//                {2, 3, 7},
//                {4, 6, 14}};

//                a = new double[][]{{3, -7, -2},
//                {-3, 5, 1},
//                {6, -4, 0}};

        double[] b = {-1, -7, -6};

        //  b = new double[]{5, 12, 12};

//        b = new double[]{-3, 3, 2};
//        b = new double[]{1, -1, 1};
//
//        a = new double[][] {{-3,6,-1,1},{1,-2,2,3},{2,-4,5,8}};
//        b = new double[]{-7,-1,-4};



//        Matrix.solLinearEquation(a, b);
  //     Matrix.inverseMatrix(a);

//        a = new double[][]{{2, -2, 1},
//                           {4, -2, 3},
//                           {-4, 8, -2}
//                          };


//        a = new double[][]{{6, -7, 2},
//                {-6, 0, -3},
//                {-12, 49, 0}
//        };

        double[][] c = {{-1, -7, -6},
                {5, 12, 12}
        };

  //      c = new double[][]{{0,0,0},{1,0,1}};


       // a = new double[][]{{3,-7,-2},{-3,5,1},{6,-4,0}};
       // c = new double[][]{{-3,3,2},{1,-1,1}};

       // a = new double[][]{{2,1,1,0},{4,3,3,1},{8,7,9,5},{6,7,9,8}};
       // c = new double[][]{{4,6,8,-2}};
       //Matrix.solLinearLU(a, c);

     //  Matrix.bestLine(new double[]{1,2,3},new double[]{1,3,2});
      // Matrix.bestLine(new double[]{0,1,2,1},new double[]{0,2,1,2});

     // a = new double[][]{{1,0,0,-1},{3,0,0,5},{2,2,4,3},{1,0,5,0}};

        a = new double[][]{{6,3,2,4,0},{9,0,4,1,0},{8,-5,6,7,-2},{-2,0,0,0,0},{4,0,3,2,0,1},};

        Matrix.findDet(a);
    }

}

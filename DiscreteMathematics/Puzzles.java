import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Puzzles {

    /* input: board of n x n dimensions and squares on the board cut out.

    output: check if board can be tiled completely by dominoes of dimension 1 x 2

     */

    public static int tilesOnChessBoard(int n, int[][] removed) {

        int[][] board = new int[n][n];


        for (int i = 0; i < removed.length; i++)
            board[removed[i][0]][removed[i][1]] = 1;


        int i = 0, j = 0, count = 0;
        int dir = 1; //1:right | 2:down | 3:left | 4:up
        while (true) {

            if (board[i][j] == 0)
                count++;
            else if (count % 2 == 0)
                count = 0;
            else
                return -1;

            if (i == 1 && j == 0)
                break;
            else if (i == 0)
                dir = 1;

            if (j == n - 1 && i == n - 1) {
                dir = 3;
            } else if (j == n - 1)
                dir = 2;
            else if (i == n - 1 || i == 1) {
                if (dir == 3) {
                    if (i == n - 1) dir = 4;
                    else if (i == 1) dir = 2;
                } else
                    dir = 3;
            }


            switch (dir) {
                case 1:
                    j++;
                    break;
                case 2:
                    i++;
                    break;
                case 3:
                    j--;
                    break;
                case 4:
                    i--;
            }
        }

        return 1;
    }

    /*input: n
    output: find a number such that dropping the first digit of that number reduces the number by n times.
     */

    static Map<Integer, Boolean> prime = new LinkedHashMap<>();

    static {
        prime.put(1, false);
    }

    static int lastEntry;
    static boolean primeFlag;


    public static double findNumber(int n) {
        if (n < 2)
            return -1;

        primeFlag = false;
        int digit = n - 1;

        ArrayList<Integer> nums = new ArrayList<>();

        for (int i = 2; i <= Math.sqrt(digit); i++) {
            while (digit % i == 0) {
                nums.add(i);
                digit /= i;
            }
        }

        int multiple = getMultiple(nums);

        boolean swap = true;

        if (multiple > 9 || multiple < 1)
            swap = false;
        else if (digit > 9 || digit < 1 || primeFlag) {
            int temp = digit;
            digit = multiple;
            multiple = temp;
            primeFlag = isPrime(multiple);
            if (!primeFlag) {
                nums = getFactors(multiple);
                getMultiple(nums);
            }
            swap = false;
        }

        int count;
        while (true) {

            if (multiple == 2 || multiple == 5 || !primeFlag) {
                double x = 1.0 / multiple;

                int newDigit = (int) (n * x);
                double value = digit + x;

                count = 0;
                while ((x * 10) % 10 != 0) {
                    x *= 10;
                    count++;
                }

                if (n * x == value * Math.pow(10, count) && n * x == (digit * Math.pow(10, count)) + x) {
                    if (digit == newDigit)
                        return n * x;
                }
            }

            if (swap) {
                int temp = digit;
                digit = multiple;
                multiple = temp;
                swap = false;
            } else
                break;
        }

        //only if no other available answer

        digit = n;
        count = 0;

        while (digit != 0) {
            digit /= 10;
            count++;
        }

        if (n % Math.pow(10, count - 1) * n == n)
            return n;

        return -1;

    }

    private static ArrayList<Integer> getFactors(int n) {

        ArrayList<Integer> nums = new ArrayList<>();

        for (int i = 2; i <= Math.sqrt(n); i++) {
            while (n % i == 0) {
                nums.add(i);
                n /= i;
            }
        }
        nums.add(n);
        return nums;

    }

    private static int getMultiple(ArrayList<Integer> nums) {
        int multiple = 1;
        for (Integer num : nums) {
            if (num != 2 && num != 5 && !primeFlag)
                primeFlag = isPrime(num);
            multiple *= num;
        }
        return multiple;
    }

    private static boolean isPrime(Integer num) {
        lastEntry = num;

        if (prime.containsKey(num))
            return prime.get(num);

        int i = prime.size() == 2 ? 2 : lastEntry;

        for (; i <= num; i++) {
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0)
                    prime.put(i, false);
            }
            if (!prime.containsKey(i))
                prime.put(i, true);
        }

        return prime.get(num);
    }


    /* Input: given a odd number n
        Output : calculate the magic square.
     */

    public static int[][] magicSquare(int n) {

        if (n < 1 || n % 2 == 0)
            return null;

        int[][] squareMatrix = new int[n][n];

        int count = n * n;

        int i = n / 2, j = n - 1;
        squareMatrix[i][j] = 1;

        while (--count > 0) {

            int temp = squareMatrix[i][j];

            i--;
            j++;

            if (i == -1 && j == n) {
                i = 0;
                j = n - 2;
            } else if (i == -1)
                i = n - 1;
            else if (j == n)
                j = 0;

            if (squareMatrix[i][j] != 0) {
                i = i + 1;
                j = j - 2;
            }

            squareMatrix[i][j] = temp + 1;

        }

        return squareMatrix;
    }
  /*
   Input: a divisor, size of the number and the starting digits of the number
   Output: list of whole numbers divisible by the divisor
   */

    public static ArrayList<Double> numberDivisibleBy(int numStart, int divisor, int digit) {

        if (divisor < 2)
            return null;

        int temp = numStart;
        int count = 0;

        while (temp != 0) {
            temp /= 10;
            count++;
        }

        double interNum = numStart;
        double limit = 0;
        for (int i = 0; i < digit - count; i++) {
            interNum *= 10;
            limit *= 10;
            limit += 9;
        }
        long quotient = Math.round(interNum / divisor);

        ArrayList<Double> result = new ArrayList<>();
        for (long i = quotient; true; i++) {
            double ans = divisor * i;
            if (ans < limit + interNum)
                result.add(ans);
            else
                break;
        }

        return result;
    }


    /*Input: List of numbers and remainder.
      Output: Minimum number that gives the remainder when divided by the list of numbers.
     */

    /* Alternate
    LCM = lcm1(number0 * number1) / gcd(number0,number1)
    LCM = lcm2(number2, lcm1)
    LCM = lcm3(number3,lcm2)
    so on... till n
    */

    public static double numberWithRemainder(int[] divisors, int remainder) {

        double lcm = 1.0;
        double factor = 2;

        while (true) {
            boolean divisible = false;
            int count = 0;

            for (int i = 0; i < divisors.length; i++) {
                if (divisors[i] == 0)
                    return 0;
                else if (divisors[i] == 1)
                    count++;

                if (divisors[i] % factor == 0) {
                    divisible = true;
                    divisors[i] /= factor;
                }
            }

            if (divisible) {
                lcm *= factor;
            } else
                factor++;

            if (count == divisors.length)
                break;
        }

        return lcm + remainder;
    }
    /*
     * Input: starting digits
     * Output: perfect square with the given starting digits
     * */

    public static double[] findSquare(long startDigits) {

        double[] n = new double[2];
        n[0] = Math.sqrt(startDigits);
        n[1] = Math.sqrt(startDigits / 10.0);
        double[] zPart = new double[2];

        for (int num = 0; num < 2; num++) {
            long count = 0;

            while (true) {
                long factor = 1;
                for (long i = 0; i < count; i++)
                    factor *= 10;

                zPart[num] = Math.round(n[num] * factor);

                count++;
                BigDecimal bD = BigDecimal.valueOf((zPart[num] * zPart[num]) / (factor * factor));

                if (num == 1)
                    bD = bD.multiply(BigDecimal.valueOf(10));

                if (bD.toBigInteger().equals(BigInteger.valueOf(startDigits))) {
                    if (factor == 1)
                        zPart[num] *= 10;
                    break;
                }
            }
        }

        return zPart;
    }

    /*
     * Input: list of numbers
     * Output: LCM
     *
     * */

    public static BigInteger lcmOfArray(long[] numbers) {


        BigInteger lcm = BigInteger.valueOf(numbers[0]);

        for (int i = 1; i < numbers.length; i++) {
            BigInteger temp = lcm.multiply(BigInteger.valueOf(numbers[i]));
            lcm = temp.divide(findGCD(BigInteger.valueOf(numbers[i]), lcm));
        }
        return lcm;
    }

    private static BigInteger findGCD(BigInteger a, BigInteger b) {

        if (b.equals(BigInteger.valueOf(0)))
            return a;

        return findGCD(b, a.mod(b));
    }


    public static void linearCombination(long a, long b) {

        BigInteger gcd = findGCD(BigInteger.valueOf(a), BigInteger.valueOf(b));

        System.out.println(a + "x" + " + " + b + "y = c/" + gcd);

        if (gcd.equals(BigInteger.valueOf(1)))
            System.out.println("Solution exists for all integers");
        else
            System.out.println("Solution only exists if c is divisible by " + gcd + ".");

    }


    public static BigInteger gcdOfArray(long[] numbers) {

        BigInteger gcd = BigInteger.valueOf(numbers[0]);

        for (int i = 1; i < numbers.length; i++)
            gcd = findGCD(BigInteger.valueOf(numbers[i]), gcd);

        return gcd;
    }


    public static long findQuantity(long[] num, long[] remainder) {
        long i;
        long[] sol = new long[remainder.length - 1];
        int j, k;

        for (i = 0; i < Long.MAX_VALUE; i++) {
            for (j = 0, k = 1; j < sol.length; j++, k++) {
                sol[j] = num[0] * i + remainder[0] - remainder[k];
                if (sol[j] % num[k] != 0)
                    break;
            }

            if (j == sol.length)
                break;
        }

        return num[0] * i + remainder[0];
    }


    /*Input: Natural numbers till n (ordered)
      Output: Correct Sequence of signs.
     */
//  if (tempSum < sum)
//            for (int i = 0; i < num.length; i++)
//                num[i] = -num[i];

    public static int[] sequenceOfSigns(int[] num, int sum) {

        int n = num[num.length - 1];
        int upperBound = (n * (n + 1)) / 2;

        if (sum % 2 == 0 || sum < -upperBound || sum > upperBound)
            return null;

        int negativeSeqSum = (upperBound - Math.abs(sum)) / 2;

        for (int i = num.length - 1; i >= 0; i--) {

            if (num[i] <= negativeSeqSum) {
                negativeSeqSum -= num[i];
                num[i] = -num[i];
            }
            if (negativeSeqSum == 0)
                break;
        }

        if (sum == -Math.abs(sum))
            for (int i = 0; i < num.length; i++)
                num[i] = -num[i];

        return num;
    }


    public static int sequenceTransposition(String s1, String s2) {
        if (s1.length() != s2.length())
            return -1;

        int n = s1.length();

        HashMap<Character, Integer> charMap = new HashMap<>();
        initializeMap(charMap);

        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        for (int i = 0; i < n; i++) {

            char c = s1.charAt(i);
            charMap.put(c, charMap.get(c) + 1);

            c = s2.charAt(i);
            charMap.put(c, charMap.get(c) - 1);
        }

        for (int i : charMap.values())
            if (i != 0)
                return -1;

        return -1;
    }

    private static void initializeMap(HashMap<Character, Integer> s1CharMap) {

        for (char c = 'a'; c <= 'z'; c++)
            s1CharMap.put(c, 0);
    }


}




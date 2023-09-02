///////////////////////////////////////////////////////////////////////////////////////
////////    The recursive algorithm steps are as follows:     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

public class Main {

    static final int mod = 1000000007;

    static long f(int i, int j, int isTrue, String exp) {
        // Base case 1:
        if (i > j) return 0;
        // Base case 2:
        if (i == j) {
            if (isTrue == 1) return exp.charAt(i) == 'T' ? 1 : 0;
            else return exp.charAt(i) == 'F' ? 1 : 0;
        }
        long ways = 0;
        for (int ind = i + 1; ind <= j - 1; ind += 2) {
            long lT = f(i, ind - 1, 1, exp);
            long lF = f(i, ind - 1, 0, exp);
            long rT = f(ind + 1, j, 1, exp);
            long rF = f(ind + 1, j, 0, exp);

            if (exp.charAt(ind) == '&') {
                if (isTrue == 1) ways = (ways + (lT * rT) % mod) % mod;
                else ways = (ways + (lF * rT) % mod + (lT * rF) % mod + (lF * rF) % mod) % mod;
            }
            else if (exp.charAt(ind) == '|') {
                if (isTrue == 1) ways = (ways + (lF * rT) % mod + (lT * rF) % mod + (lT * rT) % mod) % mod;
                else ways = (ways + (lF * rF) % mod) % mod;
            }
            else {
                if (isTrue == 1) ways = (ways + (lF * rT) % mod + (lT * rF) % mod) % mod;
                else ways = (ways + (lF * rF) % mod + (lT * rT) % mod) % mod;
            }
        }
        return ways;
    }

    static int evaluateExp(String exp) {
        int n = exp.length();
        return (int) f(0, n - 1, 1, exp);
    }

    public static void main(String[] args) {

        String exp = "F|T^F";
        int ways = evaluateExp(exp);
        System.out.println("The total number of ways: " + ways);
    }
}
// Output: The total number of ways: 2 (Given expression = “F|T^F”)

// Time Complexity: Exponential







///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to memoize the recursive solution:    ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

public class Main {

    static final int mod = 1000000007;

    static long f(int i, int j, int isTrue, String exp, long[][][] dp) {
        // Base case 1:
        if (i > j) return 0;
        // Base case 2:
        if (i == j) {
            if (isTrue == 1) return exp.charAt(i) == 'T' ? 1 : 0;
            else return exp.charAt(i) == 'F' ? 1 : 0;
        }

        if (dp[i][j][isTrue] != -1) return dp[i][j][isTrue];
        long ways = 0;
        for (int ind = i + 1; ind <= j - 1; ind += 2) {
            long lT = f(i, ind - 1, 1, exp, dp);
            long lF = f(i, ind - 1, 0, exp, dp);
            long rT = f(ind + 1, j, 1, exp, dp);
            long rF = f(ind + 1, j, 0, exp, dp);

            if (exp.charAt(ind) == '&') {
                if (isTrue == 1) ways = (ways + (lT * rT) % mod) % mod;
                else ways = (ways + (lF * rT) % mod + (lT * rF) % mod + (lF * rF) % mod) % mod;
            }
            else if (exp.charAt(ind) == '|') {
                if (isTrue == 1) ways = (ways + (lF * rT) % mod + (lT * rF) % mod + (lT * rT) % mod) % mod;
                else ways = (ways + (lF * rF) % mod) % mod;
            }
            else {
                if (isTrue == 1) ways = (ways + (lF * rT) % mod + (lT * rF) % mod) % mod;
                else ways = (ways + (lF * rF) % mod + (lT * rT) % mod) % mod;
            }
        }
        return dp[i][j][isTrue] = ways;
    }

    static int evaluateExp(String exp) {
        int n = exp.length();
        long[][][] dp = new long[n][n][2];
        for (long[][] arr2D : dp) {
            for (long[] arr1D : arr2D) {
                Arrays.fill(arr1D, -1);
            }
        }
        return (int) f(0, n - 1, 1, exp, dp);
    }

    public static void main(String[] args) {

        String exp = "F|T^F";
        int ways = evaluateExp(exp);
        System.out.println("The total number of ways: " + ways);
    }
}
// Output: The total number of ways: 2 (Given expression = “F|T^F”);

// Time Complexity: O(N*N*2 * N) ~ O(N3) There are a total of 2*N2 no.
//  of states. And for each state, we are running a partitioning 
//  loop roughly for N times.

// Space Complexity: O(2*N2) + Auxiliary stack space of O(N), 2*N2 
// for the dp array we are using.







///////////////////////////////////////////////////////////////////////////////////////
////////   Tabulation:     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

public class Main {

    static final int mod = 1000000007;

    static long evaluateExp(String exp) {
        int n = exp.length();
        long[][][] dp = new long[n][n][2];
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= n - 1; j++) {
                // Base case 1:
                if (i > j) continue;
                for (int isTrue = 0; isTrue <= 1; isTrue++) {
                    // Base case 2:
                    if (i == j) {
                        dp[i][j][isTrue] = (isTrue == 1) ? (exp.charAt(i) == 'T' ? 1 : 0) : (exp.charAt(i) == 'F' ? 1 : 0);
                        continue;
                    }

                    // Recurrence logic:
                    long ways = 0;
                    for (int ind = i + 1; ind <= j - 1; ind += 2) {
                        long lT = dp[i][ind - 1][1];
                        long lF = dp[i][ind - 1][0];
                        long rT = dp[ind + 1][j][1];
                        long rF = dp[ind + 1][j][0];

                        if (exp.charAt(ind) == '&') {
                            if (isTrue == 1) ways = (ways + (lT * rT) % mod) % mod;
                            else ways = (ways + (lF * rT) % mod + (lT * rF) % mod + (lF * rF) % mod) % mod;
                        }
                        else if (exp.charAt(ind) == '|') {
                            if (isTrue == 1) ways = (ways + (lF * rT) % mod + (lT * rF) % mod + (lT * rT) % mod) % mod;
                            else ways = (ways + (lF * rF) % mod) % mod;
                        }
                        else {
                            if (isTrue == 1) ways = (ways + (lF * rT) % mod + (lT * rF) % mod) % mod;
                            else ways = (ways + (lF * rF) % mod + (lT * rT) % mod) % mod;
                        }
                    }
                    dp[i][j][isTrue] = ways;
                }
            }
        }
        return dp[0][n - 1][1];
    }

    public static void main(String[] args) {
        String exp = "F|T^F";
        long ways = evaluateExp(exp);
        System.out.println("The total number of ways: " + ways);
    }
}
// Output: The total number of ways: 2 (Given expression = “F|T^F”);

// Time Complexity: O(N*N*2 * N) ~ O(N3) There are a total of 2*N2 no.
//  of states. And for each state, we are running a partitioning loop roughly for N times.

// Space Complexity: O(2*N2), 2*N2 for the dp array we are using.
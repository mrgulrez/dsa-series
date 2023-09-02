
///////////////////////////////////////////////////////////////////////////////////////
////////   The recursive algorithm steps are as follows:    ///////////////
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
// Output: The minimum number of partitions: 4 (Given string: “BABABCBADCEDE”)

// Time Complexity: Exponential






///////////////////////////////////////////////////////////////////////////////////////
////////   Steps to memoize the recursive solution:    ///////////////
///////////////////////////////////////////////////////////////////////////////////////

public class PalindromePartitioning {
    public static boolean isPalindrome(int i, int j, String s) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static int palindromePartitioning(String str) {
        int n = str.length();
        int[] dp = new int[n + 1];
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            int minCost = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                if (isPalindrome(i, j, str)) {
                    int cost = 1 + dp[j + 1];
                    minCost = Math.min(minCost, cost);
                }
            }
            dp[i] = minCost;
        }

        return dp[0] - 1;
    }

    public static void main(String[] args) {
        String str = "BABABCBADCEDE";
        int partitions = palindromePartitioning(str);
        System.out.println("The minimum number of partitions: " + partitions);
    }
}

// Output: The minimum number of partitions: 4 (Given string: “BABABCBADCEDE”)

// Time Complexity: O(N2)
// Reason: There are a total of N states and inside each 
// state, a loop of size N(apparently) is running.

// Space Complexity: O(N) + Auxiliary stack space O(N)
// Reason: The first O(N) is for the dp array of size N.










///////////////////////////////////////////////////////////////////////////////////////
////////   Tabulation:     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

public class PalindromePartitioning {
    public static boolean isPalindrome(int i, int j, String s) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static int palindromePartitioning(String str) {
        int n = str.length();
        int[] dp = new int[n + 1];
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            int minCost = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                if (isPalindrome(i, j, str)) {
                    int cost = 1 + dp[j + 1];
                    minCost = Math.min(minCost, cost);
                }
            }
            dp[i] = minCost;
        }
        return dp[0] - 1;
    }

    public static void main(String[] args) {
        String str = "BABABCBADCEDE";
        int partitions = palindromePartitioning(str);
        System.out.println("The minimum number of partitions: " + partitions);
    }
}
// Output: The minimum number of partitions: 4 (Given string: “BABABCBADCEDE”)

// Time Complexity: O(N2)
// Reason: There are a total of N states and inside each state 
// a loop of size N(apparently) is running.

// Space Complexity: O(N)
// Reason: O(N) is for the dp array we have used.
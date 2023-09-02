///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to memoize a recursive solution:    ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;
import java.util.Vector;

public class Main {

    public static int getAns(Vector<Integer> Arr, int n, int ind, int buy, int cap, int[][][] dp) {

        if (ind == n || cap == 0)
            return 0; // base case

        if (dp[ind][buy][cap] != -1)
            return dp[ind][buy][cap];

        int profit;

        if (buy == 0) { // We can buy the stock
            profit = Math.max(0 + getAns(Arr, n, ind + 1, 0, cap, dp),
                    -Arr.get(ind) + getAns(Arr, n, ind + 1, 1, cap, dp));
        }

        if (buy == 1) { // We can sell the stock
            profit = Math.max(0 + getAns(Arr, n, ind + 1, 1, cap, dp),
                    Arr.get(ind) + getAns(Arr, n, ind + 1, 0, cap - 1, dp));
        }

        return dp[ind][buy][cap] = profit;
    }

    public static int maximumProfit(Vector<Integer> prices, int n, int k) {
        // Creating a 3d - dp of size [n][2][k+1]
        int[][][] dp = new int[n][2][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return getAns(prices, n, 0, 0, k, dp);
    }

    public static void main(String[] args) {

        Vector<Integer> prices = new Vector<>(Arrays.asList(3, 3, 5, 0, 0, 3, 1, 4));
        int n = prices.size();
        int k = 2;

        System.out.println("The maximum profit that can be generated is " + maximumProfit(prices, n, k));
    }
}
// Output:

// The maximum profit that can be generated is 6

// Time Complexity: O(N*2*3)

// Reason: There are N*2*K states therefore at max ‘N*2*K’ new problems will be solved.

// Space Complexity: O(N*2*K) + O(N)

// Reason: We are using a recursion stack space(O(N)) and a 3D array ( O(N*2*K)).









///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to convert Recursive Solution to Tabulation one.     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

public class Main {

    public static int maximumProfit(int[] Arr, int n, int k) {
        // Creating a 3d - dp of size [n+1][2][k+1] initialized to 0
        int[][][] dp = new int[n + 1][2][k + 1];

        // As dp array is initialized to 0, we have already covered the base case

        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) {

                    if (buy == 0) { // We can buy the stock
                        dp[ind][buy][cap] = Math.max(0 + dp[ind + 1][0][cap],
                                -Arr[ind] + dp[ind + 1][1][cap]);
                    }

                    if (buy == 1) { // We can sell the stock
                        dp[ind][buy][cap] = Math.max(0 + dp[ind + 1][1][cap],
                                Arr[ind] + dp[ind + 1][0][cap - 1]);
                    }
                }
            }
        }

        return dp[0][0][k];
    }

    public static void main(String[] args) {

        int[] prices = { 3, 3, 5, 0, 0, 3, 1, 4 };
        int n = prices.length;
        int k = 2;

        System.out.println("The maximum profit that can be generated is " + maximumProfit(prices, n, k));
    }
}
// Output:

// The maximum profit that can be generated is 6

// Time Complexity: O(N*2*k) 

// Reason: There are three nested loops that account for O(N*2*K) complexity.

// Space Complexity: O(N*2*k)

// Reason: We are using an external array of size ‘N*2*K’. Stack Space is eliminated.







///////////////////////////////////////////////////////////////////////////////////////
////////    Space Optimization     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

public class Main {

    public static int maxProfit(int[] Arr, int n, int k) {

        int[][] ahead = new int[2][k + 1];
        int[][] cur = new int[2][k + 1];

        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) {

                    if (buy == 0) { // We can buy the stock
                        cur[buy][cap] = Math.max(0 + ahead[0][cap],
                                -Arr[ind] + ahead[1][cap]);
                    }

                    if (buy == 1) { // We can sell the stock
                        cur[buy][cap] = Math.max(0 + ahead[1][cap],
                                Arr[ind] + ahead[0][cap - 1]);
                    }
                }
            }
            ahead = cur;
        }

        return ahead[0][k];
    }

    public static void main(String[] args) {

        int[] prices = { 3, 3, 5, 0, 0, 3, 1, 4 };
        int n = prices.length;
        int k = 2;

        System.out.println("The maximum profit that can be generated is " + maxProfit(prices, n, k));
    }
}
// Output:

// The maximum profit that can be generated is 6

// Time Complexity: O(N*2*K)

// Reason: There are three nested loops that account for O(N*2*K) complexity

// Space Complexity: O(K)

// Reason: We are using two external arrays of size ‘2*K’.
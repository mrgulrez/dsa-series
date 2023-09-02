///////////////////////////////////////////////////////////////////////////////////////
////////    The recursive algorithm steps are as follows:     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int f(int i, int j, List<Integer> a) {
        if (i > j)
            return 0;
        int maxi = Integer.MIN_VALUE;
        for (int ind = i; ind <= j; ind++) {
            int cost = a.get(i - 1) * a.get(ind) * a.get(j + 1) + f(i, ind - 1, a) + f(ind + 1, j, a);
            maxi = Math.max(maxi, cost);
        }
        return maxi;
    }

    public static int maxCoins(List<Integer> a) {
        int n = a.size();
        a.add(1);
        a.add(0, 1);
        return f(1, n, a);
    }

    public static void main(String[] args) {

        List<Integer> a = new ArrayList<>(List.of(3, 1, 5, 8));
        int ans = maxCoins(a);
        System.out.println(ans);
    }
}
// Output: The maximum coins we can get: 167(For example 1)

// Time Complexity: Exponential












///////////////////////////////////////////////////////////////////////////////////////
////////   Steps to memoize the recursive solution:     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int f(int i, int j, List<Integer> a, List<List<Integer>> dp) {
        if (i > j)
            return 0;
        if (dp.get(i).get(j) != -1)
            return dp.get(i).get(j);
        int maxi = Integer.MIN_VALUE;
        for (int ind = i; ind <= j; ind++) {
            int cost = a.get(i - 1) * a.get(ind) * a.get(j + 1) + f(i, ind - 1, a, dp) + f(ind + 1, j, a, dp);
            maxi = Math.max(maxi, cost);
        }
        dp.get(i).set(j, maxi);
        return maxi;
    }

    public static int maxCoins(List<Integer> a) {
        int n = a.size();
        a.add(1);
        a.add(0, 1);
        List<List<Integer>> dp = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            dp.add(new ArrayList<>(List.of(new Integer[n + 1])));
            for (int j = 0; j <= n; j++) {
                dp.get(i).add(-1);
            }
        }
        return f(1, n, a, dp);
    }

    public static void main(String[] args) {

        List<Integer> a = new ArrayList<>(List.of(3, 1, 5, 8));
        int ans = maxCoins(a);
        System.out.println(ans);
    }
}
// Output: The maximum coins we can get: 167(For example 1)

// Time Complexity: O(N3), There are total N2 no. of states.
// And for each state, we are running a partitioning loop roughly for N times.

// Space Complexity: O(N2) + Auxiliary stack space of O(N),
// N2 for the dp array we are using.









///////////////////////////////////////////////////////////////////////////////////////
////////    Tabulation:     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int maxCoins(List<Integer> a) {
        int n = a.size();
        a.add(1);
        a.add(0, 1);
        int[][] dp = new int[n + 2][n + 2];

        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= n; j++) {
                if (i > j)
                    continue;
                int maxi = Integer.MIN_VALUE;
                for (int ind = i; ind <= j; ind++) {
                    int cost = a.get(i - 1) * a.get(ind) * a.get(j + 1) + dp[i][ind - 1] + dp[ind + 1][j];
                    maxi = Math.max(maxi, cost);
                }
                dp[i][j] = maxi;
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {

        List<Integer> a = new ArrayList<>(List.of(3, 1, 5, 8));
        int ans = maxCoins(a);
        System.out.println(ans);
    }
}
// Output: The maximum coins we can get: 167(For example 1)

// Time Complexity: O(N3), There are total N2 no. of states.
// And for each state, we are running a partitioning loop roughly for N times.

// Space Complexity: O(N2), N2 for the dp array we are using.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static int f(int i, int j, List<Integer> cuts) {

        // Base case
        if (i > j)
            return 0;

        int mini = Integer.MAX_VALUE;

        for (int ind = i; ind <= j; ind++) {

            int ans = cuts.get(j + 1) - cuts.get(i - 1) + f(i, ind - 1, cuts) + f(ind + 1, j, cuts);

            mini = Math.min(mini, ans);
        }

        return mini;
    }

    public static int cost(int n, int c, List<Integer> cuts) {

        // Modify the cuts list
        cuts.add(n);
        cuts.add(0);
        cuts.sort(null); // Sort in ascending order

        return f(1, c, cuts);
    }

    public static void main(String[] args) {

        List<Integer> cuts = new ArrayList<>(Arrays.asList(3, 5, 1, 4));

        int c = cuts.size();
        int n = 7;

        System.out.println("The minimum cost incurred: " + cost(n, c, cuts));
    }
}
// Output:

// The minimum cost incurred: 16









///////////////////////////////////////////////////////////////////////////////////////
////////    Tabulation:     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static int f(int i, int j, List<Integer> cuts, List<List<Integer>> dp) {

        // Base case
        if (i > j)
            return 0;

        if (dp.get(i).get(j) != -1)
            return dp.get(i).get(j);

        int mini = Integer.MAX_VALUE;

        for (int ind = i; ind <= j; ind++) {

            int ans = cuts.get(j + 1) - cuts.get(i - 1) + f(i, ind - 1, cuts, dp) + f(ind + 1, j, cuts, dp);

            mini = Math.min(mini, ans);
        }

        dp.get(i).set(j, mini);
        return mini;
    }

    public static int cost(int n, int c, List<Integer> cuts) {

        // Modify the cuts list
        cuts.add(n);
        cuts.add(0);
        cuts.sort(null); // Sort in ascending order

        List<List<Integer>> dp = new ArrayList<>();
        for (int i = 0; i <= c; i++) {
            dp.add(new ArrayList<>(Arrays.asList(new Integer[c + 1])));
            for (int j = 0; j <= c; j++) {
                dp.get(i).set(j, -1);
            }
        }

        return f(1, c, cuts, dp);
    }

    public static void main(String[] args) {

        List<Integer> cuts = new ArrayList<>(Arrays.asList(3, 5, 1, 4));

        int c = cuts.size();
        int n = 7;

        System.out.println("The minimum cost incurred: " + cost(n, c, cuts));
    }
}
// Output:

// The minimum cost incurred: 16

// Time Complexity: O(N*N*N)

// Reason: There are 2 variables i and j, therefore,
//  N*N states and we explicitly run a loop inside the function 
//  which will run for N times, therefore at max ‘N*N*N’ 
//  new problems will be solved.

// Space Complexity: O(N*N) + O(N)

// Reason: We are using an auxiliary recursion stack 
// space(O(N))and a 2D array ( O(N*N)).








///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to memoize a recursive solution:     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static int cost(int n, int c, List<Integer> cuts) {

        cuts.add(n);
        cuts.add(0);
        cuts.sort(null); // Sort in ascending order

        int[][] dp = new int[c + 2][c + 2];

        for (int i = c; i >= 1; i--) {
            for (int j = 1; j <= c; j++) {

                if (i > j)
                    continue;

                int mini = Integer.MAX_VALUE;

                for (int ind = i; ind <= j; ind++) {

                    int ans = cuts.get(j + 1) - cuts.get(i - 1) + dp[i][ind - 1] + dp[ind + 1][j];

                    mini = Math.min(mini, ans);

                }

                dp[i][j] = mini;
            }
        }

        return dp[1][c];

    }

    public static void main(String[] args) {

        List<Integer> cuts = new ArrayList<>(Arrays.asList(3, 5, 1, 4));

        int c = cuts.size();
        int n = 7;

        System.out.println("The minimum cost incurred: " + cost(n, c, cuts));
    }
}
// Output:

// The minimum cost incurred: 16

// Time Complexity: O(N*N*N)

// Reason: There are 2 variables i and j, therefore, N*N states
// and we explicitly run a loop inside the function which will
// run for N times, therefore at max ‘N*N*N’ new problems will be solved.

// Space Complexity: O(N*N)

// Reason: We are using a 2D array ( O(N*N)).
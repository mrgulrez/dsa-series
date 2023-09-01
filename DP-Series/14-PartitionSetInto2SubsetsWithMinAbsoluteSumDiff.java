///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to memoize a recursive solution:     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

public class Main {
    static boolean subsetSumUtil(int ind, int target, ArrayList<Integer> arr, ArrayList<ArrayList<Integer>> dp) {
        if (target == 0)
            return dp.get(ind).set(target, true);

        if (ind == 0)
            return dp.get(ind).set(target, arr.get(0) == target);

        if (dp.get(ind).get(target) != -1)
            return dp.get(ind).get(target);

        boolean notTaken = subsetSumUtil(ind - 1, target, arr, dp);

        boolean taken = false;
        if (arr.get(ind) <= target)
            taken = subsetSumUtil(ind - 1, target - arr.get(ind), arr, dp);

        return dp.get(ind).set(target, notTaken || taken);
    }

    static int minSubsetSumDifference(ArrayList<Integer> arr, int n) {
        int totSum = 0;

        for (int i = 0; i < n; i++) {
            totSum += arr.get(i);
        }

        ArrayList<ArrayList<Integer>> dp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> row = new ArrayList<>(Collections.nCopies(totSum + 1, -1));
            dp.add(row);
        }

        for (int i = 0; i <= totSum; i++) {
            boolean dummy = subsetSumUtil(n - 1, i, arr, dp);
        }

        int mini = 1_000_000_000;
        for (int i = 0; i <= totSum; i++) {
            if (dp.get(n - 1).get(i)) {
                int diff = Math.abs(i - (totSum - i));
                mini = Math.min(mini, diff);
            }
        }
        return mini;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        int n = arr.size();

        System.out.println("The minimum absolute difference is: " + minSubsetSumDifference(arr, n));
    }
}

// Output:

// The minimum absolute difference is: 0

// Time Complexity: O(N*totSum) +O(N) +O(N)

// Reason: There are two nested loops that account for
// O(N*totSum), at starting we are running a for loop to
//  calculate totSum and at last a for loop to traverse the last row.

// Space Complexity: O(N*totSum) + O(N)

// Reason: We are using an external array of size
//  ‘N * totSum’ and a stack space of O(N).






///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to convert Recursive Solution to Tabulation one.     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

public class Main {
    static int minSubsetSumDifference(ArrayList<Integer> arr, int n) {
        int totSum = 0;

        for (int i = 0; i < n; i++) {
            totSum += arr.get(i);
        }

        ArrayList<ArrayList<Boolean>> dp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Boolean> row = new ArrayList<>(Collections.nCopies(totSum + 1, false));
            dp.add(row);
        }

        for (int i = 0; i < n; i++) {
            dp.get(i).set(0, true);
        }

        if (arr.get(0) <= totSum) {
            dp.get(0).set(totSum, true);
        }

        for (int ind = 1; ind < n; ind++) {
            for (int target = 1; target <= totSum; target++) {
                boolean notTaken = dp.get(ind - 1).get(target);
                boolean taken = false;
                if (arr.get(ind) <= target) {
                    taken = dp.get(ind - 1).get(target - arr.get(ind));
                }
                dp.get(ind).set(target, notTaken || taken);
            }
        }

        int mini = 1_000_000_000;
        for (int i = 0; i <= totSum; i++) {
            if (dp.get(n - 1).get(i)) {
                int diff = Math.abs(i - (totSum - i));
                mini = Math.min(mini, diff);
            }
        }
        return mini;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        int n = arr.size();

        System.out.println("The minimum absolute difference is: " + minSubsetSumDifference(arr, n));
    }
}
// Output:

// The minimum absolute difference is: 0

// Time Complexity: O(N*totSum) +O(N) +O(N)

// Reason: There are two nested loops that account for 
// O(N*totSum), at starting we are running a for loop 
// to calculate totSum, and at last a for loop to traverse
//  the last row.

// Space Complexity: O(N*totSum)

// Reason: We are using an external array of size ‘N * totSum’.
//  Stack Space is eliminated.






///////////////////////////////////////////////////////////////////////////////////////
////////    Space Optimization     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

public class Main {
    static int minSubsetSumDifference(ArrayList<Integer> arr, int n) {
        int totSum = 0;

        for (int i = 0; i < n; i++) {
            totSum += arr.get(i);
        }

        ArrayList<Boolean> prev = new ArrayList<>(Collections.nCopies(totSum + 1, false));

        prev.set(0, true);

        if (arr.get(0) <= totSum) {
            prev.set(arr.get(0), true);
        }

        for (int ind = 1; ind < n; ind++) {
            ArrayList<Boolean> cur = new ArrayList<>(Collections.nCopies(totSum + 1, false));
            cur.set(0, true);
            for (int target = 1; target <= totSum; target++) {
                boolean notTaken = prev.get(target);
                boolean taken = false;
                if (arr.get(ind) <= target) {
                    taken = prev.get(target - arr.get(ind));
                }
                cur.set(target, notTaken || taken);
            }
            prev = cur;
        }

        int mini = 1_000_000_000;
        for (int i = 0; i <= totSum; i++) {
            if (prev.get(i)) {
                int diff = Math.abs(i - (totSum - i));
                mini = Math.min(mini, diff);
            }
        }
        return mini;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        int n = arr.size();

        System.out.println("The minimum absolute difference is: " + minSubsetSumDifference(arr, n));
    }
}


// Output:

// 105

// Time Complexity: O(N*M)

// Reason: There are two nested loops

// Space Complexity: O(M)

// Reason: We are using an external array of size ‘M’ to 
// store only one row.



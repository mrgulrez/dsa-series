import java.util.Arrays;
import java.util.Vector;

public class Main {

    public static int getAns(int[] arr, int n, int ind, int prev_index, Vector<Vector<Integer>> dp) {

        // Base condition
        if (ind == n)
            return 0;

        if (dp.get(ind).get(prev_index + 1) != -1)
            return dp.get(ind).get(prev_index + 1);

        int notTake = 0 + getAns(arr, n, ind + 1, prev_index, dp);

        int take = 0;

        if (prev_index == -1 || arr[ind] > arr[prev_index]) {
            take = 1 + getAns(arr, n, ind + 1, ind, dp);
        }

        dp.get(ind).set(prev_index + 1, Math.max(notTake, take));
        return dp.get(ind).get(prev_index + 1);
    }

    public static int longestIncreasingSubsequence(int[] arr, int n) {

        Vector<Integer> temp = new Vector<>();
        temp.add(arr[0]);

        int len = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] > temp.lastElement()) {
                // arr[i] > the last element of temp array

                temp.add(arr[i]);
                len++;

            } else {
                // Replacement step
                int ind = Arrays.binarySearch(temp.toArray(), arr[i]);
                if (ind < 0) {
                    ind = -(ind + 1);
                }
                temp.set(ind, arr[i]);
            }
        }

        return len;
    }

    public static void main(String[] args) {

        int[] arr = { 10, 9, 2, 5, 3, 7, 101, 18 };

        int n = arr.length;

        System.out.println("The length of the longest increasing subsequence is " + longestIncreasingSubsequence(arr, n));
    }
}
// Output:

// The length of the longest increasing subsequence is 4

// Time Complexity: O(N*logN)

// Reason: We iterate over the array of size N and in every iteration, 
// we perform a binary search which takes logN time.

// Space Complexity: O(N)

// Reason: We are using an extra array of size N to store the temp variable.
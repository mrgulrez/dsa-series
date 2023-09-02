import java.util.Arrays;

public class MaxSumAfterPartitioning {
    public static int maxSumAfterPartitioning(int[] num, int k) {
        int n = num.length;
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int len = 0;
            int maxi = Integer.MIN_VALUE;
            for (int j = i - 1; j >= 0 && len < k; j--) {
                len++;
                maxi = Math.max(maxi, num[j]);
                dp[i] = Math.max(dp[i], dp[j] + len * maxi);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int[] num = {1, 15, 7, 9, 2, 5, 10};
        int k = 3;
        int maxSum = maxSumAfterPartitioning(num, k);
        System.out.println("The maximum sum is: " + maxSum);
    }
}
// Output: The maximum sum is: 84 (For example 1)

// Time Complexity: Exponential









import java.util.Arrays;

public class MaxSumAfterPartitioning {
    public static int maxSumAfterPartitioning(int[] num, int k) {
        int n = num.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            int len = 0;
            int maxi = Integer.MIN_VALUE;
            for (int j = i; j >= 0 && len < k; j--) {
                len++;
                maxi = Math.max(maxi, num[j]);
                int sum = (j > 0 ? dp[j - 1] : 0) + len * maxi;
                dp[i] = Math.max(dp[i], sum);
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] num = {1, 15, 7, 9, 2, 5, 10};
        int k = 3;
        int maxSum = maxSumAfterPartitioning(num, k);
        System.out.println("The maximum sum is: " + maxSum);
    }
}
// Output: The maximum sum is: 84 (For example 1)

// Time Complexity: O(N*k)
// Reason: There are a total of N states and for each state, we are running a loop from 0 to k.

// Space Complexity: O(N) + Auxiliary stack space O(N)
// Reason: First O(N) for the dp array we are using.







import java.util.Arrays;

public class MaxSumAfterPartitioning {
    public static int maxSumAfterPartitioning(int[] num, int k) {
        int n = num.length;
        int[] dp = new int[n + 1];
        
        for (int ind = n - 1; ind >= 0; ind--) {
            int len = 0;
            int maxi = Integer.MIN_VALUE;
            int maxAns = Integer.MIN_VALUE;
            
            for (int j = ind; j < Math.min(ind + k, n); j++) {
                len++;
                maxi = Math.max(maxi, num[j]);
                int sum = len * maxi + dp[j + 1];
                maxAns = Math.max(maxAns, sum);
            }
            
            dp[ind] = maxAns;
        }
        
        return dp[0];
    }

    public static void main(String[] args) {
        int[] num = {1, 15, 7, 9, 2, 5, 10};
        int k = 3;
        int maxSum = maxSumAfterPartitioning(num, k);
        System.out.println("The maximum sum is: " + maxSum);
    }
}
// Output: The maximum sum is: 84 (For example 1)

// Time Complexity: O(N*k)
// Reason: There are a total of N states and for each
//  state, we are running a loop from 0 to k.

// Space Complexity: O(N)
// Reason: O(N) for the dp array we are using.
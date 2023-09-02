import java.util.Arrays;
import java.util.List;

public class Main {

    public static boolean compare(String s1, String s2) {
        if (s1.length() != s2.length() + 1)
            return false;

        int first = 0;
        int second = 0;

        while (first < s1.length()) {
            if (second < s2.length() && s1.charAt(first) == s2.charAt(second)) {
                first++;
                second++;
            } else
                first++;
        }
        return first == s1.length() && second == s2.length();
    }

    public static int longestStrChain(List<String> arr) {

        int n = arr.size();

        // Sort the array
        arr.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int maxi = 1;

        for (int i = 0; i < n; i++) {

            for (int prev_index = 0; prev_index < i; prev_index++) {

                if (compare(arr.get(i), arr.get(prev_index)) && 1 + dp[prev_index] > dp[i]) {
                    dp[i] = 1 + dp[prev_index];
                }
            }

            if (dp[i] > maxi)
                maxi = dp[i];
        }
        return maxi;
    }

    public static void main(String[] args) {

        List<String> words = Arrays.asList("a", "b", "ba", "bca", "bda", "bdca");

        System.out.println("The length of the longest string chain is: " + longestStrChain(words));
    }
}

// Output:

// The length of the longest string chain is: 4

// Time Complexity: O(N*N * l)

// Reason: We are setting up two nested loops and the compare 
// function can be estimated to l, where l is the length of the 
// longest string in the words [ ] array. Also, we are sorting so 
// the time complexity will be (N^2 * l + NlogN)

// Space Complexity: O(N)

// Reason: We are only using a single array of size n.
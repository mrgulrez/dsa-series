///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to memoize a recursive solution:    ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;
class TUF {
  static boolean isAllStars(String S1, int i) {
    for (int j = 0; j <= i; j++) {
      if (S1.charAt(j) != '*')
        return false;
    }
    return true;
  }

  static int wildcardMatchingUtil(String S1, String S2, int i, int j, int[][] dp) {

    //Base Conditions
    if (i < 0 && j < 0)
      return 1;
    if (i < 0 && j >= 0)
      return 0;
    if (j < 0 && i >= 0)
      return isAllStars(S1, i) ? 1 : 0;

    if (dp[i][j] != -1) return dp[i][j];

    if (S1.charAt(i) == S2.charAt(j) || S1.charAt(i) == '?')
      return dp[i][j] = wildcardMatchingUtil(S1, S2, i - 1, j - 1, dp);

    else {
      if (S1.charAt(i) == '*')
        return (wildcardMatchingUtil(S1, S2, i - 1, j, dp) == 1 || wildcardMatchingUtil(S1, S2, i, j - 1, dp) == 1) ? 1 : 0;
      else return 0;
    }
  }

  static int wildcardMatching(String S1, String S2) {

    int n = S1.length();
    int m = S2.length();

    int dp[][] = new int[n][m];
    for (int row[]: dp)
      Arrays.fill(row, -1);
    return wildcardMatchingUtil(S1, S2, n - 1, m - 1, dp);

  }

  public static void main(String args[]) {

    String S1 = "ab*cd";
    String S2 = "abdefcd";

    if (wildcardMatching(S1, S2) == 1)
      System.out.println("String S1 and S2 do match");
    else System.out.println("String S1 and S2 do not match");
  }
}

// Output:

// String S1 and S2 do match

// Time Complexity: O(N*M)

// Reason: There are N*M states therefore at max ‘N*M’ new 
// problems will be solved.

// Space Complexity: O(N*M) + O(N+M)

// Reason: We are using a recursion stack space(O(N+M))
//  and a 2D array ( O(N*M)).








///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to convert Recursive Solution to Tabulation one.     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class TUF {
  static boolean isAllStars(String S1, int i) {

    // S1 is taken in 1-based indexing
    for (int j = 1; j <= i; j++) {
      if (S1.charAt(j - 1) != '*')
        return false;
    }
    return true;
  }

  static boolean wildcardMatching(String S1, String S2) {

    int n = S1.length();
    int m = S2.length();

    boolean dp[][] = new boolean[n + 1][m + 1];
    dp[0][0] = true;

    for (int j = 1; j <= m; j++) {
      dp[0][j] = false;
    }
    for (int i = 1; i <= n; i++) {
      dp[i][0] = isAllStars(S1, i);
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {

        if (S1.charAt(i - 1) == S2.charAt(j - 1) || S1.charAt(i - 1) == '?')
          dp[i][j] = dp[i - 1][j - 1];

        else {
          if (S1.charAt(i - 1) == '*')
            dp[i][j] = dp[i - 1][j] || dp[i][j - 1];

          else dp[i][j] = false;
        }
      }
    }

    return dp[n][m];

  }

  public static void main(String args[]) {

    String S1 = "ab*cd";
    String S2 = "abdefcd";

    if (wildcardMatching(S1, S2))
      System.out.println("String S1 and S2 do match");
    else System.out.println("String S1 and S2 do not match");
  }
}
// Output:

// String S1 and S2 do match

// Time Complexity: O(N*M)

// Reason: There are two nested loops

// Space Complexity: O(N*M)

// Reason: We are using an external array
//  of size ‘N*M’. Stack Space is eliminated.







///////////////////////////////////////////////////////////////////////////////////////
////////    Space Optimization     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class TUF {
  static boolean isAllStars(String S1, int i) {

    // S1 is taken in 1-based indexing
    for (int j = 1; j <= i; j++) {
      if (S1.charAt(j - 1) != '*')
        return false;
    }
    return true;
  }

  static boolean wildcardMatching(String S1, String S2) {

    int n = S1.length();
    int m = S2.length();

    boolean prev[] = new boolean[m + 1];
    boolean cur[] = new boolean[m + 1];

    prev[0] = true;

    for (int i = 1; i <= n; i++) {
      cur[0] = isAllStars(S1, i);
      for (int j = 1; j <= m; j++) {

        if (S1.charAt(i - 1) == S2.charAt(j - 1) || S1.charAt(i - 1) == '?')
          cur[j] = prev[j - 1];

        else {
          if (S1.charAt(i - 1) == '*')
            cur[j] = prev[j] || cur[j - 1];

          else cur[j] = false;
        }
      }
      prev = (boolean[]) cur.clone();
    }

    return prev[m];

  }

  public static void main(String args[]) {

    String S1 = "ab*cd";
    String S2 = "abdefcd";

    if (wildcardMatching(S1, S2))
      System.out.println("String S1 and S2 do match");
    else System.out.println("String S1 and S2 do not match");
  }
}
// Output:

// String S1 and S2 do match

// Time Complexity: O(N*M)

// Reason: There are two nested loops.

// Space Complexity: O(M)

// Reason: We are using an external array of size ‘M+1’ to
// store two rows.
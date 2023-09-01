import java.util.*;

class Solution{
static int lcs(String s1, String s2) {
    
    int n=s1.length();
    int m=s2.length();

    int dp[][]=new int[n+1][m+1];
    for(int rows[]:dp)
    Arrays.fill(rows,-1);
    for(int i=0;i<=n;i++){
        dp[i][0] = 0;
    }
    for(int i=0;i<=m;i++){
        dp[0][i] = 0;
    }
    
    for(int ind1=1;ind1<=n;ind1++){
        for(int ind2=1;ind2<=m;ind2++){
            if(s1.charAt(ind1-1)==s2.charAt(ind2-1))
                dp[ind1][ind2] = 1 + dp[ind1-1][ind2-1];
            else
                dp[ind1][ind2] = 0 + Math.max(dp[ind1-1][ind2],dp[ind1][ind2-1]);
        }
    }
    
    return dp[n][m];
}

static int longestPalindromeSubsequence(String s){
    String t = s;
    String ss=new StringBuilder(s).reverse().toString();
    return lcs(ss,t);
}

public static void main(String args[]) {

  String s= "bbabcbcab";
                                 
  System.out.print("The Length of Longest Palindromic Subsequence is ");
  System.out.println(longestPalindromeSubsequence(s));
}
}
// Output: The Length of Longest Palindromic Subsequence is 7

// Time Complexity: O(N*N)

// Reason: There are two nested loops

// Space Complexity: O(N*N)

// Reason: We are using an external array of size ‘(N*N)’. Stack Space is eliminated.














///////////////////////////////////////////////////////////////////////////////////////
////////    Space Optimization     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class Solution{
static int lcs(String s1, String s2) {
    
    int n=s1.length();
    int m=s2.length();

    int prev[]=new int[m+1];
    int cur[]=new int[m+1];
    
    // Base Case is covered as we have initialized the prev and cur to 0.
    
    for(int ind1=1;ind1<=n;ind1++){
        for(int ind2=1;ind2<=m;ind2++){
            if(s1.charAt(ind1-1)==s2.charAt(ind2-1))
                cur[ind2] = 1 + prev[ind2-1];
            else
                cur[ind2] = 0 + Math.max(prev[ind2],cur[ind2-1]);
        }
        prev= cur;
    }
    
    return prev[m];
}

static int longestPalindromeSubsequence(String s){
    String t = s;
    String ss=new StringBuilder(s).reverse().toString();
    return lcs(ss,t);
}

public static void main(String args[]) {

  String s= "bbabcbcab";
                                 
  System.out.print("The Length of Longest Palindromic Subsequence is ");
  System.out.println(longestPalindromeSubsequence(s));
}
}
// Output:

// The Length of Longest Palindromic Subsequence is 7

// Time Complexity: O(N*N)

// Reason: There are two nested loops.

// Space Complexity: O(N)

// Reason: We are using an external array of size ‘N+1’ to store only two rows.
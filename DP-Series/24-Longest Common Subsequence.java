///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to memoize a recursive solution:     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class Solution{
static int lcsUtil(String s1, String s2, int ind1, int ind2, int[][] dp){

    if(ind1<0 || ind2<0)
        return 0;
        
    if(dp[ind1][ind2]!=-1)
        return dp[ind1][ind2];
    
    if(s1.charAt(ind1) == s2.charAt(ind2))
        return dp[ind1][ind2] = 1 + lcsUtil(s1,s2,ind1-1,ind2-1,dp);
    
    else 
        return dp[ind1][ind2] = 0 + Math.max(lcsUtil(s1,s2,ind1,ind2-1,dp),
        lcsUtil(s1,s2,ind1-1,ind2,dp));
   
}


static int lcs(String s1, String s2) {
    
    int n=s1.length();
    int m=s2.length();

    int dp[][]=new int[n][m];
    for(int rows[]: dp)
    Arrays.fill(rows,-1);
    return lcsUtil(s1,s2,n-1,m-1,dp);
}

public static void main(String args[]) {

  String s1= "acd";
  String s2= "ced";
                                 
  System.out.println("The Length of Longest Common Subsequence is "+lcs(s1,s2));
}
}

// Output: The Length of Longest Common Subsequence is 2

// Time Complexity: O(N*M)

// Reason: There are N*M states therefore at max ‘N*M’ new 
// problems will be solved.

// Space Complexity: O(N*M) + O(N+M)

// Reason: We are using an auxiliary recursion stack 
// space(O(N+M)) (see the recursive tree, in the worst case, 
// we will go till N+M calls at a time) and a 2D array ( O(N*M)).






///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to convert Recursive Solution to Tabulation one.     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class TUF{
static int lcs(String s1, String s2) {
    
    int n=s1.length();
    int m=s2.length();

    int dp[][]=new int[n+1][m+1];
    for(int rows[]: dp)
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

public static void main(String args[]) {

  String s1= "acd";
  String s2= "ced";
                                 
  System.out.println("The Length of Longest Common Subsequence is "+lcs(s1,s2));
}
}
// Output:

// The Length of Longest Common Subsequence is 2

// Time Complexity: O(N*M)

// Reason: There are two nested loops

// Space Complexity: O(N*M)

// Reason: We are using an 
// external array of size ‘N*M)’. Stack Space is eliminated.






///////////////////////////////////////////////////////////////////////////////////////
////////    Space Optimization     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class TUF{
static int lcs(String s1, String s2) {
    
    int n=s1.length();
    int m=s2.length();
    
    // Base Case is covered as we have initialized the prev and cur to 0.
    int prev[]=new int[m+1];
    int cur[]=new int[m+1];
    for(int ind1=1;ind1<=n;ind1++){
        for(int ind2=1;ind2<=m;ind2++){
            if(s1.charAt(ind1-1)==s2.charAt(ind2-1))
                cur[ind2] = 1 + prev[ind2-1];
            else
                cur[ind2] = 0 + Math.max(prev[ind2],cur[ind2-1]);
        }
        prev=(int[])(cur.clone());
    }
    
    return prev[m];
}

public static void main(String args[]) {

  String s1= "acd";
  String s2= "ced";
                                 
  System.out.println("The Length of Longest Common Subsequence is "+lcs(s1,s2));
}
}
// Output:

// The Maximum price generated is 12

// Time Complexity: O(N*M)

// Reason: There are two nested loops.

// Space Complexity: O(M)

// Reason: We are using an external 
// array of size ‘M+1’ to store only two rows.
///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to memoize a recursive solution:    ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class Solution{
static int prime = (int)(Math.pow(10,9)+7);

static int countUtil(String s1, String s2, int ind1, int ind2,int[][] dp){
    if(ind2<0)
        return 1;
    if(ind1<0)
        return 0;
    
    if(dp[ind1][ind2]!=-1)
        return dp[ind1][ind2];
    
    if(s1.charAt(ind1)==s2.charAt(ind2)){
        int leaveOne = countUtil(s1,s2,ind1-1,ind2-1,dp);
        int stay = countUtil(s1,s2,ind1-1,ind2,dp);
        
        return dp[ind1][ind2] = (leaveOne + stay)%prime;
    }
    
    else{
        return dp[ind1][ind2] = countUtil(s1,s2,ind1-1,ind2,dp);
    }
}

static int subsequenceCounting(String t, String s, int lt, int ls) {
    // Write your code here.
    
    int dp[][]=new int[lt][ls];
    for(int rows[]: dp)
    Arrays.fill(rows,-1);
    return countUtil(t,s,lt-1,ls-1,dp);
} 

public static void main(String args[]) {

  String s1 = "babgbag";
  String s2 = "bag";

  System.out.println("The Count of Distinct Subsequences is "+
  subsequenceCounting(s1,s2,s1.length(),s2.length()));
}
}
// Output:

// The Count of Distinct Subsequences is 5

// Time Complexity: O(N*M)

// Reason: There are N*M states therefore at max ‘N*M’ new problems 
// will be solved.

// Space Complexity: O(N*M) + O(N+M)

// Reason: We are using a recursion stack space(O(N+M)) 
// and a 2D array ( O(N*M)).









///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to convert Recursive Solution to Tabulation one.     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class Solution{
static int prime =(int)(Math.pow(10,9)+7);

static int subsequenceCounting(String s1, String s2, int n, int m) {
    // Write your code here.
    
    int dp[][]=new int[n+1][m+1];
    
    for(int i=0;i<n+1;i++){
        dp[i][0]=1;
    }
    for(int i=1;i<m+1;i++){
        dp[0][i]=0;
    }
    
    for(int i=1;i<n+1;i++){
        for(int j=1;j<m+1;j++){
            
            if(s1.charAt(i-1)==s2.charAt(j-1))
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j])%prime;
            else
                dp[i][j] = dp[i-1][j];
        }
    }
    
    
    return dp[n][m];
} 

public static void main(String args[]) {

  String s1 = "babgbag";
  String s2 = "bag";

  System.out.println("The Count of Distinct Subsequences is "+
  subsequenceCounting(s1,s2,s1.length(),s2.length()));
}
}
// Output:

// The Count of Distinct Subsequences is 5

// Time Complexity: O(N*M)

// Reason: There are two nested loops

// Space Complexity: O(N*M)

// Reason: We are using an external 
// array of size ‘N*M’. Stack Space is eliminated.







///////////////////////////////////////////////////////////////////////////////////////
////////    Space Optimization     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class Solution{
static int prime =(int)(Math.pow(10,9)+7);

static int subsequenceCounting(String s1, String s2, int n, int m) {
    // Write your code here.
    
    int[] prev=new int[m+1];
    prev[0]=1;
    
    for(int i=1;i<n+1;i++){
        for(int j=m;j>=1;j--){ // Reverse direction
            
            if(s1.charAt(i-1)==s2.charAt(j-1))
                prev[j] = (prev[j-1] + prev[j])%prime;
            else
                prev[j] = prev[j]; //can omit this statemwnt
        }
    }
    
    return prev[m];
} 

public static void main(String args[]) {

  String s1 = "babgbag";
  String s2 = "bag";

  System.out.println("The Count of Distinct Subsequences is "+
  subsequenceCounting(s1,s2,s1.length(),s2.length()));
}
}
// Output:

// The Count of Distinct Subsequences is 5

// Time Complexity: O(N*M)

// Reason: There are two nested loops.

// Space Complexity: O(M)

// Reason: We are using an external
//  array of size ‘M+1’ to store only one row.
///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to memoize a recursive solution:    ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class Solution{
static int editDistanceUtil(String S1, String S2, int i, int j, int[][] dp){
    
    if(i<0)
        return j+1;
    if(j<0)
        return i+1;
        
    if(dp[i][j]!=-1) return dp[i][j];
        
    if(S1.charAt(i)==S2.charAt(j))
        return dp[i][j] =  0+editDistanceUtil(S1,S2,i-1,j-1,dp);
        
    // Minimum of three choices
    else return dp[i][j] = 1+Math.min(editDistanceUtil(S1,S2,i-1,j-1,dp),
    Math.min(editDistanceUtil(S1,S2,i-1,j,dp),editDistanceUtil(S1,S2,i,j-1,dp)));
    
}

static int editDistance(String S1, String S2){
    
    int n = S1.length();
    int m = S2.length();
    
    int[][] dp=new int[n][m];
    for(int row[]: dp)
    Arrays.fill(row,-1);
    return editDistanceUtil(S1,S2,n-1,m-1,dp);
    
}

public static void main(String args[]) {

  String s1 = "horse";
  String s2 = "ros";

  System.out.println("The minimum number of operations required is: "+
  editDistance(s1,s2));
}
}
// Output: The minimum number of operations required is: 3

// Time Complexity: O(N*M)

// Reason: There are N*M states therefore at
// max ‘N*M’ new problems will be solved.

// Space Complexity: O(N*M) + O(N+M)

// Reason: We are using a recursion stack
//  space(O(N+M)) and a 2D array ( O(N*M)).









///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to convert Recursive Solution to Tabulation one.     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class TUF{

static int editDistance(String S1, String S2){
    
    int n = S1.length();
    int m = S2.length();
    
    int[][] dp=new int[n+1][m+1];
     for(int i=0;i<=n;i++){
        dp[i][0] = i;
    }
    for(int j=0;j<=m;j++){
        dp[0][j] = j;
    }
    
    for(int i=1;i<n+1;i++){
        for(int j=1;j<m+1;j++){
            if(S1.charAt(i-1)==S2.charAt(j-1))
                dp[i][j] = 0+dp[i-1][j-1];
            
            else dp[i][j] = 1+Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
        }
    }
    
    return dp[n][m];
}

public static void main(String args[]) {

  String s1 = "horse";
  String s2 = "ros";

  System.out.println("The minimum number of operations required is: "+
  editDistance(s1,s2));
}
}
// Output: The minimum number of operations required is: 3

// Time Complexity: O(N*M)

// Reason: There are two nested loops

// Space Complexity: O(N*M)

// Reason: We are using an external array of 
// size ‘N*M’. Stack Space is eliminated.









///////////////////////////////////////////////////////////////////////////////////////
////////    Space Optimization     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class TUF{

static int editDistance(String S1, String S2){
    
    int n = S1.length();
    int m = S2.length();
    
    int[] prev=new int[m+1];
    int[] cur=new int[m+1];
    
    for(int j=0;j<=m;j++){
        prev[j] = j;
    }
    
    for(int i=1;i<n+1;i++){
        cur[0]=i;
        for(int j=1;j<m+1;j++){
            if(S1.charAt(i-1)==S2.charAt(j-1))
                cur[j] = 0+prev[j-1];
            
            else cur[j] = 1+Math.min(prev[j-1],Math.min(prev[j],cur[j-1]));
        }
        prev = (int[])(cur.clone());
    }
    
    return prev[m];
    
}

public static void main(String args[]) {

  String s1 = "horse";
  String s2 = "ros";

  System.out.println("The minimum number of operations required is: "+
  editDistance(s1,s2));
}
}
// Output: The minimum number of operations required is: 3

// Time Complexity: O(N*M)

// Reason: There are two nested loops.

// Space Complexity: O(M)

// Reason: We are using an external 
// array of size ‘M+1’ to store two rows.
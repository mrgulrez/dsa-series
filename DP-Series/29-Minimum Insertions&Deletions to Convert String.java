import java.util.*;

class Solution{
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

static int canYouMake(String str1, String str2){
    
    int n = str1.length();
    int m = str2.length();
    
    int k = lcs(str1,str2);
    
    return (n-k)+(m-k);
}

public static void main(String args[]) {

  String str1= "abcd";
  String str2= "anc";
  System.out.println("The Minimum operations required to convert str1 to str2: "
  +canYouMake(str1,str2));
}
}
// Output:

// The Minimum operations required
// to convert str1 to str2: 3

// Time Complexity: O(N*M)

// Reason: There are two nested loops

// Space Complexity: O(N*M)

// Reason: We are using an external array of size
// (N*M). Stack Space is eliminated.








///////////////////////////////////////////////////////////////////////////////////////
////////    Space Optimization     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class Solution{
static int lcs(String s1, String s2) {
    
     int n=s1.length();
    int m=s2.length();

    int[] prev=new int[m+1];
    int[] cur=new int[m+1];
    
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

static int canYouMake(String str1, String str2){
    
    int n = str1.length();
    int m = str2.length();
    
    int k = lcs(str1,str2);
    
    return (n-k)+(m-k);
}

public static void main(String args[]) {

  String str1= "abcd";
  String str2= "anc";
  System.out.println("The Minimum operations required to convert str1 to str2: "+
  canYouMake(str1,str2));
}
}
// Output:

// The Minimum operations required to convert str1 to str2: 3

// Time Complexity: O(N*M)

// Reason: There are two nested loops.

// Space Complexity: O(M)

// Reason: We are using an external array of size ‘M+1’
// to store only two rows.
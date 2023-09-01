import java.util.*;

class TUF{
static int lcs(String s1, String s2){
    
    int n = s1.length();
    int m = s2.length();
    
    int[][] dp=new int[n+1][m+1];
    int ans = 0;
    
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            if(s1.charAt(i-1)==s2.charAt(j-1)){
                int val = 1 + dp[i-1][j-1];
                dp[i][j] = val;
                ans = Math.max(ans,val);
            }
            else
                dp[i][j] = 0;
        }
    }
    
    return ans;
    
}

public static void main(String args[]) {

  String s1= "abcjklp";
  String s2= "acjkp";
                                 
  System.out.println("The Length of Longest Common Substring is "+lcs(s1,s2));
}
}

// Output:

// The Length of Longest Common Substring is 3

// Time Complexity: O(N*M)

// Reason: There are two nested loops

// Space Complexity: O(N*M)

// Reason: We are using an external array of size ‘N*M)’.
//  Stack Space is eliminated.




///////////////////////////////////////////////////////////////////////////////////////
////////    Space Optimization     ///////////////
///////////////////////////////////////////////////////////////////////////////////////


import java.util.*;

class Solution{
static int lcs(String s1, String s2){
	//	Write your code here.
    
    int n = s1.length();
    int m = s2.length();
    
    int prev[]=new int[m+1];
    int cur[]=new int[m+1];

    int ans = 0;
    
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            if(s1.charAt(i-1)==s2.charAt(j-1)){
                int val = 1 + prev[j-1];
                cur[j] = val;
                ans = Math.max(ans,val);
            }
            else
                cur[j] = 0;
        }
        prev=cur;
    }
    
    return ans;
    
}

public static void main(String args[]) {

  String s1= "abcjklp";
  String s2= "acjkp";
                                 
  System.out.println("The Length of Longest Common Substring is "+lcs(s1,s2));
}
}
// Output:

// The Length of Longest Common Substring is 3

// Time Complexity: O(N*M)

// Reason: There are two nested loops.

// Space Complexity: O(M)

// Reason: We are using an external array 
// of size ‘M+1’ to store only two rows.
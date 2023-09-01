import java.util.*;
public class Main{
static int solveUtil(int ind, int[] arr, int[] dp){
    if(ind<0)  return 0;
    if(ind==0) return arr[ind];
    if(dp[ind]!=-1) return dp[ind];
    int pick= arr[ind]+ solveUtil(ind-2, arr,dp);
    int nonPick = 0 + solveUtil(ind-1, arr, dp);
    
    return dp[ind]=Math.max(pick, nonPick);
}

static int solve(int n,int[] arr){
    int dp[]=new int[n];
    Arrays.fill(dp,-1);
    return solveUtil(n-1, arr, dp);
}


public static void main(String args[]) {

  int arr[]={2,1,4,9};
  int n=arr.length;
  System.out.println(solve(n,arr));
}
}


////////////////////////////////////////////////////
// Time Complexity: O(N)

// Reason: The overlapping subproblems will return 
// the answer in constant time O(1). Therefore the 
// total number of new subproblems we solve is ‘n’. 
// Hence total time complexity is O(N).

// Space Complexity: O(N)

// Reason: We are using a recursion stack space(O(N)) and an array
//  (again O(N)). Therefore total space complexity will be 
//  O(N) + O(N) ≈ O(N)
/////////////////////////////////////////////////////////////







import java.util.*;
class TUF{
static int solveUtil(int n, int[] arr, int[] dp){
    
    dp[0]= arr[0];
    
    for(int i=1 ;i<n; i++){
        int pick = arr[i];
        if(i>1)
            pick += dp[i-2];
        int nonPick = 0+ dp[i-1];
        
        dp[i]= Math.max(pick, nonPick);
    }
    
    
    return dp[n-1];
}

static int solve(int n, int[] arr){
    int dp[]=new int[n];
    Arrays.fill(dp,-1);
    return solveUtil(n, arr, dp);
}


public static void main(String args[]) {

  int arr[]={2,1,4,9};
  int n=arr.length;
  System.out.println(solve(n,arr));
}
}



// Time Complexity: O(N)

// Reason: We are running a simple iterative loop

// Space Complexity: O(N)

// Reason: We are using an external array of size ‘n+1’.
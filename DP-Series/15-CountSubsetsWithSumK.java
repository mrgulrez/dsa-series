///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to memoize a recursive solution:     ///////////////
///////////////////////////////////////////////////////////////////////////////////////


import java.util.*;

class TUF{
static int findWaysUtil(int ind, int target, int[] arr,int[][] dp){
    if(target==0)
        return 1;
    
    if(ind == 0)
        return arr[0] == target?1:0;
    
    if(dp[ind][target]!=-1)
        return dp[ind][target];
        
    int notTaken = findWaysUtil(ind-1,target,arr,dp);
    
    int taken = 0;
    if(arr[ind]<=target)
        taken = findWaysUtil(ind-1,target-arr[ind],arr,dp);
        
    return dp[ind][target]= notTaken + taken;
}

static int findWays(int[] num, int k){
    int n = num.length;
    int dp[][]=new int[n][k+1];
    for(int row[]: dp)
    Arrays.fill(row,-1);
    
    return findWaysUtil(n-1,k,num,dp);
}

public static void main(String args[]) {

  int arr[] = {1,2,2,3};
  int k=3;
                                 
  System.out.println("The number of subsets found are "+findWays(arr,k));
}
}

// Output:

// The number of subsets found are 3

// Time Complexity: O(N*K)

// Reason: There are N*K states therefore at max ‘N*K’ 
// new problems will be solved.

// Space Complexity: O(N*K) + O(N)

// Reason: We are using a recursion stack space(O(N)) 
// and a 2D array ( O(N*K)).





///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to convert Recursive Solution to Tabulation one.     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class TUF{

static int findWays(int[] num, int k){
    int n = num.length;

    int[][] dp=new int[n][k+1];
    
    for(int i=0; i<n; i++){
        dp[i][0] = 1;
    }
    
    if(num[0]<=k)
        dp[0][num[0]] = 1;
    
    for(int ind = 1; ind<n; ind++){
        for(int target= 1; target<=k; target++){
            
            int notTaken = dp[ind-1][target];
    
            int taken = 0;
                if(num[ind]<=target)
                    taken = dp[ind-1][target-num[ind]];
        
            dp[ind][target]= notTaken + taken;
        }
    }
    
    return dp[n-1][k];
    

}

public static void main(String args[]) {

  int arr[] = {1,2,2,3};
  int k=3;
                                 
  System.out.println("The number of subsets found are "+findWays(arr,k));
}
}
// Output:

// The number of subsets found are 3

// Time Complexity: O(N*K)

// Reason: There are two nested loops

// Space Complexity: O(N*K)

// Reason: We are using an 
// external array of size ‘N*K’. Stack Space is eliminated.




///////////////////////////////////////////////////////////////////////////////////////
////////    Space Optimization     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class TUF{

static int findWays(int[] num, int k){
    int n = num.length;

    int prev[]=new int[k+1];
    
    prev[0] =1;
    
    if(num[0]<=k)
        prev[num[0]] = 1;
    
    for(int ind = 1; ind<n; ind++){
        int cur[]=new int[k+1];
        cur[0]=1;
        for(int target= 1; target<=k; target++){
            
            int notTaken = prev[target];
    
            int taken = 0;
                if(num[ind]<=target)
                    taken = prev[target-num[ind]];
        
            cur[target]= notTaken + taken;
        }
        
        prev = cur;
    }
    
    return prev[k];

}

public static void main(String args[]) {

  int arr[] = {1,2,2,3};
  int k=3;
                                 
  System.out.println("The number of subsets found are "+findWays(arr,k));
}
}
// Output:

// The number of subsets found are 3

// Time Complexity: O(N*K)

// Reason: There are two nested loops

// Space Complexity: O(K)

// Reason: We are using an external array
//  of size ‘K+1’ to store only one row.
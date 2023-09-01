///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to memoize a recursive solution:     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class TUF{
static long countWaysToMakeChangeUtil(int[] arr,int ind, int T, long[][] dp){

    if(ind == 0){
        if(T%arr[0]==0)
        return 1;
        else
        return 0;
    }
    
    if(dp[ind][T]!=-1)
        return dp[ind][T];
        
    long notTaken = countWaysToMakeChangeUtil(arr,ind-1,T,dp);
    
    long taken = 0;
    if(arr[ind] <= T)
        taken = countWaysToMakeChangeUtil(arr,ind,T-arr[ind],dp);
        
    return dp[ind][T] = notTaken + taken;
}


static long countWaysToMakeChange(int[] arr, int n, int T){
    
    long dp[][]=new long[n][T+1];
    for(long row[]: dp)
    Arrays.fill(row,-1);
    return countWaysToMakeChangeUtil(arr,n-1, T, dp);
}

public static void main(String args[]) {

  int arr[] ={1,2,3};
  int target=4;
  
  int n =arr.length;
                                 
  System.out.println("The total number of ways is "+countWaysToMakeChange(arr,n,
  target));
}
}
// Output:

// The total number of ways is 4

// Time Complexity: O(N*T)

// Reason: There are N*W states therefore at max ‘N*T’ 
// new problems will be solved.

// Space Complexity: O(N*T) + O(N)

// Reason: We are using a recursion stack space(O(N)) and
//  a 2D array ( O(N*T)).






///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to convert Recursive Solution to Tabulation one.     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class TUF{
static long countWaysToMakeChange(int[] arr, int n, int T){
    
    long dp[][]=new long[n][T+1];
    
    
    //Initializing base condition
    for(int i=0;i<=T;i++){
        if(i%arr[0]==0)
            dp[0][i]=1;
        // Else condition is automatically fulfilled,
        // as dp array is initialized to zero
    }
    
    for(int ind=1; ind<n;ind++){
        for(int target=0;target<=T;target++){
            long notTaken = dp[ind-1][target];
            
            long taken = 0;
            if(arr[ind]<=target)
                taken = dp[ind][target-arr[ind]];
                
            dp[ind][target] = notTaken + taken;
        }
    }
    
    return dp[n-1][T];
}

public static void main(String args[]) {

  int arr[] ={1,2,3};
  int target=4;
  
  int n =arr.length;
                                 
  System.out.println("The total number of ways is "+countWaysToMakeChange(arr,n,
  target));
}
}
// Output:

// The total number of ways is 4

// Time Complexity: O(N*T)

// Reason: There are two nested loops

// Space Complexity: O(N*T)

// Reason: We are using an external array of size
// ‘N*T’. Stack Space is eliminated.





///////////////////////////////////////////////////////////////////////////////////////
////////    Space Optimization     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class TUF{
static long countWaysToMakeChange(int[] arr, int n, int T){
    
    long[] prev=new long[T+1];
    
    
    //Initializing base condition
    for(int i=0;i<=T;i++){
        if(i%arr[0]==0)
            prev[i]=1;
        // Else condition is automatically fulfilled,
        // as prev array is initialized to zero
    }
    
    for(int ind=1; ind<n;ind++){
        long cur[]=new long[T+1];
        for(int target=0;target<=T;target++){
            long notTaken = prev[target];
            
            long taken = 0;
            if(arr[ind]<=target)
                taken = cur[target-arr[ind]];
                
            cur[target] = notTaken + taken;
        }
        prev = cur;
    }
    
    return prev[T];
}

public static void main(String args[]) {

  int arr[] ={1,2,3};
  int target=4;
  
  int n =arr.length;
                                 
  System.out.println("The total number of ways is "+countWaysToMakeChange
  (arr,n,target));
}
}
// Output:

// The total number of ways is 4

// Time Complexity: O(N*T)

// Reason: There are two nested loops.

// Space Complexity: O(T)

// Reason: We are using an external array of 
// size ‘T+1’ to store two rows only.
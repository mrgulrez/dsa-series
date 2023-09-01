
///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to memoize a recursive solution:    ///////////////
///////////////////////////////////////////////////////////////////////////////////////



import java.util.*;

class Sol{
static boolean subsetSumUtil(int ind, int target,int arr[],int[][] dp){
    if(target==0)
        return true;

    if(ind == 0)
        return arr[0] == target;

    if(dp[ind][target]!=-1)
        return dp[ind][target]==0?false:true;

    boolean notTaken = subsetSumUtil(ind-1,target,arr,dp);

    boolean taken = false;
    if(arr[ind]<=target)
        taken = subsetSumUtil(ind-1,target-arr[ind],arr,dp);
        dp[ind][target]=notTaken||taken?1:0;
    return notTaken||taken;
}

static boolean canPartition(int n,int[] arr){

    int totSum=0;

    for(int i=0; i<n;i++){
        totSum+= arr[i];
    }

    if (totSum%2==1) return false;

    else{
        int k = totSum/2;
        int dp[][]=new int[n][k+1];
        for(int row[]: dp)
        Arrays.fill(row,-1);
        return subsetSumUtil(n-1,k,arr,dp);
    } 
}

public static void main(String args[]) {

  int arr[] = {2,3,3,3,4,5};
  int n = arr.length;

  if(canPartition(n,arr))
    System.out.println("The Array can be partitioned into two equal subsets");
  else
    System.out.println("The Array cannot be partitioned into two equal subsets");
}
}
// Output:

// The array can be partitioned into two equal subsets

// Time Complexity: O(N*K) + O(N)

// Reason: There are N*K states therefore at max ‘N*K’ new
//  problems will be solved and we are running a for loop
//   for ‘N’ times to calculate the total sum

// Space Complexity: O(N*K) + O(N)

// Reason: We are using a recursion stack space(O(N))
// and a 2D array ( O(N*K)).





///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to convert Recursive Solution to Tabulation one.     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class Sol{

static boolean canPartition(int n,int[] arr){
    
    int totSum=0;
    
    for(int i=0; i<n;i++){
        totSum+= arr[i];
    }
    
    if (totSum%2==1) return false;
    
    else{
        int k = totSum/2;
        boolean dp[][]=new boolean[n][k+1];
    
        for(int i=0; i<n; i++){
            dp[i][0] = true;
        }
        
        if(arr[0]<=k)
            dp[0][arr[0]] = true;
        
        for(int ind = 1; ind<n; ind++){
            for(int target= 1; target<=k; target++){
                
                boolean notTaken = dp[ind-1][target];
        
                boolean taken = false;
                    if(arr[ind]<=target)
                        taken = dp[ind-1][target-arr[ind]];
            
                dp[ind][target]= notTaken||taken;
            }
        }
        
        return dp[n-1][k];

    } 
}

public static void main(String args[]) {

  int arr[] = {2,3,3,3,4,5};
  int n = arr.length;
                                 
  if(canPartition(n,arr))
    System.out.println("The Array can be partitioned into two equal subsets");
  else 
    System.out.println("The Array cannot be partitioned into two equal subsets");
}
}

// Output:

// The array can be partitioned into two equal subsets

// Time Complexity: O(N*K) +O(N)

// Reason: There are two nested loops that account for
//  O(N*K) and at starting we are running a for loop 
//  to calculate totSum.

// Space Complexity: O(N*K)

// Reason: We are using an external array of size ‘N*K’.
//  Stack Space is eliminated.






///////////////////////////////////////////////////////////////////////////////////////
////////    Space Optimization     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class TUF{

static boolean canPartition(int n,int[] arr){
    
   int totSum=0;
    
    for(int i=0; i<n;i++){
        totSum+= arr[i];
    }
    
    if (totSum%2==1) return false;
    
    else{
        int k = totSum/2;
        boolean prev[]=new boolean[k+1];
    
        prev[0] = true;
        
        if(arr[0]<=k)
            prev[arr[0]] = true;
        
        for(int ind = 1; ind<n; ind++){
            boolean cur[]=new boolean[k+1];
            cur[0] = true;
            for(int target= 1; target<=k; target++){
                boolean notTaken = prev[target];
        
                boolean taken = false;
                    if(arr[ind]<=target)
                        taken = prev[target-arr[ind]];
            
                cur[target]= notTaken||taken;
            }
            prev = cur;
        }
        
        return prev[k];
    } 
}

public static void main(String args[]) {

  int arr[] = {2,3,3,3,4,5};
  int n = arr.length;
                                 
  if(canPartition(n,arr))
    System.out.println("The Array can be partitioned into two equal subsets");
  else 
    System.out.println("The Array cannot be partitioned into two equal subsets");
}
}



// Output:

// The Array can be partitioned into two equal subsets

// Time Complexity: O(N*K) +O(N)

// Reason: There are two nested loops that account 
// for O(N*K) and at starting we are running a for loop to 
// calculate totSum.

// Space Complexity: O(K)

// Reason: We are using an external array of size ‘K+1’ to 
// store only one row.
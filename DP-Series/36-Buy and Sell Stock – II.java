///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to memoize a recursive solution:    ///////////////
///////////////////////////////////////////////////////////////////////////////////////


import java.util.Arrays;

public class Main {

    public static long getAns(long[] Arr, int ind, int buy, int n, long[][] dp) {

        if (ind == n)
            return 0; // base case

        if (dp[ind][buy] != -1)
            return dp[ind][buy];

        long profit;

        if (buy == 0) { // We can buy the stock
            profit = Math.max(0 + getAns(Arr, ind + 1, 0, n, dp), -Arr[ind] + getAns(Arr, ind + 1, 1, n, dp));
        }

        if (buy == 1) { // We can sell the stock
            profit = Math.max(0 + getAns(Arr, ind + 1, 1, n, dp), Arr[ind] + getAns(Arr, ind + 1, 0, n, dp));
        }

        return dp[ind][buy] = profit;
    }

    public static long getMaximumProfit(long[] Arr, int n) {
        // Write your code here

        long[][] dp = new long[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        if (n == 0)
            return 0;
        long ans = getAns(Arr, 0, 0, n, dp);
        return ans;
    }

    public static void main(String[] args) {

        int n = 6;
        long[] Arr = { 7, 1, 5, 3, 6, 4 };

        System.out.println("The maximum profit that can be generated is " + getMaximumProfit(Arr, n));
    }
}
// Output:

// The maximum profit that can be generated is 7

// Time Complexity: O(N*2) 

// Reason: There are N*2 states therefore at max ‘N*2’ new 
// problems will be solved and we are running a for loop for 
// ‘N’ times to calculate the total sum

// Space Complexity: O(N*2) + O(N)

// Reason: We are using a recursion stack space(O(N)) 
// and a 2D array ( O(N*2)).








///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to convert Recursive Solution to Tabulation one.     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class Solution{
static long getMaximumProfit(long Arr[], int n)
{
    //Write your code here
    
    long dp[][]=new long[n+1][2];
    for(long row[]: dp)
    Arrays.fill(row,-1);
    
    //base condition
    dp[n][0] = dp[n][1] = 0;
    
    long profit=0;
    
    for(int ind= n-1; ind>=0; ind--){
        for(int buy=0; buy<=1; buy++){
            if(buy==0){// We can buy the stock
                profit = Math.max(0+dp[ind+1][0], -Arr[ind] + dp[ind+1][1]);
            }
    
            if(buy==1){// We can sell the stock
                profit = Math.max(0+dp[ind+1][1], Arr[ind] + dp[ind+1][0]);
            }
            
            dp[ind][buy]  = profit;
        }
    }
    return dp[0][0];
}

public static void main(String args[]) {

  int n =6;
  long Arr[] = {7,1,5,3,6,4};
                                 
  System.out.println("The maximum profit that can be generated is "+getMaximumProfit(Arr, n));
}
}
// Output:

// The maximum profit that can be generated is 7

// Time Complexity: O(N*2)

// Reason: There are two nested loops that account
// for O(N*2) complexity.

// Space Complexity: O(N*2)

// Reason: We are using an external array of size
//  ‘N*2’. Stack Space is eliminated.









///////////////////////////////////////////////////////////////////////////////////////
////////    Space Optimization     ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class TUF{
static long getMaximumProfit(long Arr[], int n)
{
    //Write your code here
    
    long ahead[]=new long[2];
    long cur[] =new long[2];
    
    //base condition
    ahead[0] = ahead[1] = 0;
    
    long profit=0;
    
    for(int ind= n-1; ind>=0; ind--){
        for(int buy=0; buy<=1; buy++){
            if(buy==0){// We can buy the stock
                profit = Math.max(0+ahead[0], -Arr[ind] + ahead[1]);
            }
    
            if(buy==1){// We can sell the stock
                profit = Math.max(0+ahead[1], Arr[ind] + ahead[0]);
            }
            cur[buy]  = profit;
        }
        
        ahead = (long[])(cur.clone());
    }
    return cur[0];
}

public static void main(String args[]) {

  int n =6;
  long Arr[] = {7,1,5,3,6,4};
                                 
  System.out.println("The maximum profit that can be generated is "+
  getMaximumProfit(Arr, n));
}
}
// Output:

// The maximum profit that can be generated is 7

// Time Complexity: O(N*2)

// Reason: There are two nested loops that account for O(N*2) complexity

// Space Complexity: O(1)

// Reason: We are using an external array of size ‘2’.
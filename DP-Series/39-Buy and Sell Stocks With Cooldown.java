///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to memoize a recursive solution:    ///////////////
///////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class Solution{
static int getAns(int[] Arr, int ind, int buy, int n, int[][] dp ){

    if(ind>=n) return 0; //base case
    
    if(dp[ind][buy]!=-1)
        return dp[ind][buy];
        
    int profit=0;
    
    if(buy==0){// We can buy the stock
    profit=Math.max(0+getAns(Arr,ind+1,0,n,dp), -Arr[ind]+getAns(Arr,ind+1,1,n,dp));
    }
    
    if(buy==1){// We can sell the stock
    profit=Math.max(0+getAns(Arr,ind+1,1,n,dp), Arr[ind]+getAns(Arr,ind+2,0,n,dp));
    }
    
    return dp[ind][buy] = profit;
}


static int stockProfit(int[] Arr)
{
    int n = Arr.length;
    int dp[][]=new int[n][2];
    for(int row[]: dp)
    Arrays.fill(row,-1);
    
    int ans = getAns(Arr,0,0,n,dp);
    return ans;
}

public static void main(String args[]) {

  int prices[]= {4,9,0,4,10};
                                 
  System.out.println("The maximum profit that can be generated is "+
  stockProfit(prices));
}
}
// Output:

// The maximum profit that can be generated is 11

// Time Complexity: O(N*2)

// Reason: There are N*2 states therefore at max ‘N*2’ new problems
// will be solved and we are running a for loop for ‘N’ times to calculate the total sum

// Space Complexity: O(N*2) + O(N)

// Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*2)).








///////////////////////////////////////////////////////////////////////////////////////
////////    Steps to convert Recursive Solution to Tabulation one.     ///////////////
///////////////////////////////////////////////////////////////////////////////////////
import java.util.*;

class Solution{

static int stockProfit(int[] Arr)
{  
    
    int n = Arr.length;
    int dp[][]=new int[n+2][2];
    
    for(int ind = n-1; ind>=0; ind--){
        for(int buy=0; buy<=1; buy++){
            int profit=0;
            
            if(buy==0){// We can buy the stock
                profit = Math.max(0+dp[ind+1][0], -Arr[ind] + dp[ind+1][1]);
            }
    
            if(buy==1){// We can sell the stock
                profit = Math.max(0+dp[ind+1][1], Arr[ind] + dp[ind+2][0]);
            }
            
            dp[ind][buy] = profit;
        }
    }
    
    return dp[0][0];

}

public static void main(String args[]) {

  int prices[]= {4,9,0,4,10};
                                 
  System.out.println("The maximum profit that can be generated is "+
  stockProfit(prices));
}
}
// Output:

// The maximum profit that can be generated is 11

// Time Complexity: O(N*2) 

// Reason: There are two nested loops that account for O(N*2) complexity.

// Space Complexity: O(N*2)

// Reason: We are using an external array of size ‘N*2’. Stack Space is eliminated.











///////////////////////////////////////////////////////////////////////////////////////
/////////////    Space Optimization     ///////////////
///////////////////////////////////////////////////////////////////////////////////////
import java.util.*;

class Solution{

static int stockProfit(int[] Arr)
{  
    
      int n = Arr.length;
    int cur[] = new int[2];
    int front1[] = new int[2];
    int front2[] = new int[2];
    
    for(int ind = n-1; ind>=0; ind--){
        for(int buy=0; buy<=1; buy++){
            int profit=0;
            
            if(buy==0){// We can buy the stock
                profit = Math.max(0+front1[0], -Arr[ind] + front1[1]);
            }
    
            if(buy==1){// We can sell the stock
                profit = Math.max(0+front1[1], Arr[ind] + front2[0]);
            }
            
            cur[buy] = profit;
        }
        
        front2 = (int[])(front1.clone());
        front1 = (int [])(cur.clone());
        
    }
    
    return cur[0];


}

public static void main(String args[]) {

  int prices[]= {4,9,0,4,10};
                                 
  System.out.println("The maximum profit that can be generated is "+
  stockProfit(prices));
}
}
// Output:

// The maximum profit that can be generated is 11

// Time Complexity: O(N*2)

// Reason: There are two nested loops that account for O(N*2) complexity

// Space Complexity: O(1)

// Reason: We are using three external arrays of size ‘2’.
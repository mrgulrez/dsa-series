import java.util.*;

class Solution{
static int f(int[] arr, int i, int j,int[][] dp){
    
    // base condition
    if(i == j)
        return 0;
        
    if(dp[i][j]!=-1)
        return dp[i][j];
    
    int mini = Integer.MAX_VALUE;
    
    // partioning loop
    for(int k = i; k<= j-1; k++){
        
        int ans = f(arr,i,k,dp) + f(arr, k+1,j,dp) + arr[i-1]*arr[k]*arr[j];
        
        mini = Math.min(mini,ans);
        
    }
    
    return mini;
}


static int matrixMultiplication(int[] arr, int N){
    
    int [][] dp=new int[N][N];
    for(int row[]: dp)
    Arrays.fill(row,-1);
    
    for(int i=1; i<N; i++){
        dp[i][i] = 0;
    }
    
    for(int i=N-1; i>=1; i--){
        
        for(int j=i+1; j<N; j++){
            
            int mini = Integer.MAX_VALUE;
    
            // partioning loop
            for(int k = i; k<= j-1; k++){
                
                int ans = dp[i][k]+ dp[k+1][j] + arr[i-1]*arr[k]*arr[j];
                
                mini = Math.min(mini,ans);
                
            }
            
            dp[i][j] = mini;
    
        }
    }
    
    return dp[1][N-1];
    
    
}
public static void main(String args[]) {
	
    int[] arr = {10, 20, 30, 40, 50};
	
	int n = arr.length;
	
	System.out.println("The minimum number of operations are "+
        matrixMultiplication(arr,n));
	
	
}
}
// Output:

// The minimum number of operations are 38000

// Time Complexity: O(N*N*N)

// Reason: There are N*N states and we explicitly run a loop inside the function
//  which will run for N times, therefore at max ‘N*N*N’ new problems will be solved.

// Space Complexity: O(N*N)

// Reason: We are using a 2D array ( O(N*N)) space.
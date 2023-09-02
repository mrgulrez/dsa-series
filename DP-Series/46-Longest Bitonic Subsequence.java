import java.util.*;

class Solution{
static int longestBitonicSequence(int[] arr, int n){
    
    int[] dp1= new int[n];
    int[] dp2= new int[n];
    
    Arrays.fill(dp1,1);
    Arrays.fill(dp2,1);
    
    for(int i=0; i<=n-1; i++){
        for(int prev_index = 0; prev_index <=i-1; prev_index ++){
            
            if(arr[prev_index]<arr[i]){
                dp1[i] = Math.max(dp1[i], 1 + dp1[prev_index]);
            }
        }
    }
    
    // reverse the direction of nested loops
    for(int i=n-1; i>=0; i--){
        for(int prev_index = n-1; prev_index >i; prev_index --){
            
            if(arr[prev_index]<arr[i]){
                dp2[i] = Math.max(dp2[i], 1 + dp2[prev_index]);
            }
        }
    }
    
    int maxi = -1;
    
    for(int i=0; i<n; i++){
        maxi = Math.max(maxi, dp1[i] + dp2[i] - 1);
    }
    
    return maxi;
    
}
public static void main(String args[]) {
	
	int arr[] = {1, 11, 2, 10, 4, 5, 2, 1};
	int n = arr.length;
	
	System.out.println("The length of the longest bitonic subsequence is
        "+longestBitonicSequence(arr,n));
	
}
}
// Output:

// The length of the longest bitonic subsequence is 6

// Time Complexity: O(N*N)

// Reason: There are two nested loops that are run twice.

// Space Complexity: O(N)

// Reason: We are only using two rows of size n.
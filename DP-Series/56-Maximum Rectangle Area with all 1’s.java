import java.util.Stack;

public class MaximalAreaOfSubmatrix {
    public static int largestRectangleArea(int[] histo) {
        Stack<Integer> st = new Stack<>();
        int maxA = 0;
        int n = histo.length;
        
        for (int i = 0; i <= n; i++) {
            while (!st.isEmpty() && (i == n || histo[st.peek()] >= histo[i])) {
                int height = histo[st.pop()];
                int width = st.isEmpty() ? i : i - st.peek() - 1;
                maxA = Math.max(maxA, width * height);
            }
            st.push(i);
        }
        
        return maxA;
    }

    public static int maximalAreaOfSubMatrixOfAll1(int[][] mat, int n, int m) {
        int maxArea = 0;
        int[] height = new int[m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            
            int area = largestRectangleArea(height);
            maxArea = Math.max(maxArea, area);
        }
        
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] mat = {
            {1, 0, 1, 0, 0}, {1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1}, {1, 0, 0, 1, 0}
        };
        int n = 4, m = 5;
        int maxArea = maximalAreaOfSubMatrixOfAll1(mat, n, m);
        System.out.println("The maximum area is: " + maxArea);
    }
}
// Output: The maximum area is: 6 (For example 1)

// Time Complexity: O(N * (M+M)), where N = total no. of rows
//  and M = total no. of columns.
// Reason: O(N) for running a loop to check all rows.
//  Now, inside that loop, O(M) is for visiting all the columns,
//   and another O(M) is for the function we are using. The function 
//   takes linear time complexity. Here, the size of the height array 
//   is M, so it will take O(M).

// Space Complexity: O(M), where M = total no. of columns.
// Reason: We are using a height array and a stack of size M.
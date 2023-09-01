import java.util.*;

class TUF{
static int mazeObstaclesUtil(int i, int j, int[][] maze, int[][] dp) {
  if(i>0 && j>0 && maze[i][j]==-1) return 0; 
  if(i==0 && j == 0)
    return 1;
  if(i<0 || j<0)
    return 0;
  if(dp[i][j]!=-1) return dp[i][j];
    
  int up = mazeObstaclesUtil(i-1,j,maze,dp);
  int left = mazeObstaclesUtil(i,j-1,maze,dp);
  
  return dp[i][j] = up+left;
  
}

static int mazeObstacles(int n, int m, int[][] maze){
    int dp[][]=new int[n][m];
    for(int row[]: dp)
    Arrays.fill(row,-1);
    return mazeObstaclesUtil(n-1,m-1,maze,dp);
    
}

public static void main(String args[]) {

  int[][] maze = { {0,0,0},
                   {0,-1,0},
                   {0,0,0}};
                            
  int n = maze.length;
  int m = maze.length;
  
  System.out.println(mazeObstacles(n,m,maze));
}
}
///////////////////////////////////////////////////////////////
// Time Complexity: O(N*M)
// Reason: At max, there will be N*M calls of recursion.
// Space Complexity: O((M-1)+(N-1)) + O(N*M)
// Reason: We are using a recursion
//  stack space:O((M-1)+(N-1)),
// here (M-1)+(N-1) is the path length and an
// external DP Array of size ‘N*M’.
/////////////////////////////////////////////////////////////////









import java.util.*;

class TUF{
static int mazeObstaclesUtil(int n, int m, int[][] maze, int[][] dp) {
 for(int i=0; i<n ;i++){
      for(int j=0; j<m; j++){
          
          //base conditions
          if(i>0 && j>0 && maze[i][j]==-1){
            dp[i][j]=0;
            continue;
          }
          if(i==0 && j==0){
              dp[i][j]=1;
              continue;
          }
          
          int up=0;
          int left = 0;
          
          if(i>0) 
            up = dp[i-1][j];
          if(j>0)
            left = dp[i][j-1];
            
          dp[i][j] = up+left;
      }
  }
  
  return dp[n-1][m-1];

}

static int mazeObstacles(int n, int m, int[][] maze){
    int dp[][]=new int[n][m];
    for(int row[]: dp)
    Arrays.fill(row,-1);
    return mazeObstaclesUtil(n,m,maze,dp);
    
}

public static void main(String args[]) {

  int[][] maze = { {0,0,0},
                   {0,-1,0},
                   {0,0,0}};
                            
  int n = maze.length;
  int m = maze.length;
  
  System.out.println(mazeObstacles(n,m,maze));
}
}

//////////////////////////////////////////////////////
// Time Complexity: O(N*M)
// Reason: There are two nested loops
// Space Complexity: O(N*M)
// Reason: We are using an external array of size ‘N*M’’.
/////////////////////////////////////////////////////////






import java.util.*;

class TUF{
static int mazeObstacles(int n, int m,int[][] maze){

    int prev[]=new int[n];
    for(int i=0; i<n; i++){
        int temp[]=new int[m];
        for(int j=0; j<m; j++){
            if(i>0 && j>0 && maze[i][j]==-1){
                temp[j]=0;
                continue;
            }
            if(i==0 && j==0){
                temp[j]=1;
                continue;
            }

            int up=0;
            int left =0;

            if(i>0)
                up = prev[j];
            if(j>0)
                left = temp[j-1];

            temp[j] = up + left;
        }
        prev = temp;
    }

    return prev[n-1];


}

public static void main(String args[]) {

  int[][] maze = { {0,0,0},
                   {0,-1,0},
                   {0,0,0}};

  int n = maze.length;
  int m = maze.length;

  System.out.println(mazeObstacles(n,m,maze));
}
}
/////////////////////////////////////////////////////////////////
// Time Complexity: O(M*N)
// Reason: There are two nested loops
// Space Complexity: O(N)
// Reason: We are using an external array
// of size ‘N’ to store only one row.
////////////////////////////////////////////////////////////////////
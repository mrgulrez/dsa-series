class Solution {
    public static Node buildTree(int inorder[], int preorder[], int n) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < n; i++) {
            hm.put(inorder[i], i);
        }
        return buildTreeHelper(inorder, 0, n - 1, preorder, 0, n - 1, hm);
    }

    private static Node buildTreeHelper(int[] inorder, int inStart, int inEnd, int[] preorder, int preStart, int preEnd, HashMap<Integer, Integer> hm) {
        if (preStart > preEnd || inStart > inEnd)
            return null;

        int rootData = preorder[preStart];
        Node root = new Node(rootData);

        // int inRoot = hm.get(rootData);
        int inRoot = findPos(inorder, rootData, inStart, inEnd);
        int numLeft = inRoot - inStart;

        root.left = buildTreeHelper(inorder, inStart, inRoot - 1, preorder, preStart + 1, preStart + numLeft, hm);
        root.right = buildTreeHelper(inorder, inRoot + 1, inEnd, preorder, preStart + numLeft + 1, preEnd, hm);

        return root;
    }
    
    static int findPos(int inorder[], int data, int start, int end) {
        for(int i=start; i<=end; i++) {
            if(inorder[i] == data) return i;
        }
        
       return -1;
    }
}
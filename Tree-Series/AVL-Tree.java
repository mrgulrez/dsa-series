
/*
class Node
{
	int data, height;
	Node left, right;
	Node(int x)
	{
		data = x;
		height = 1;
		left = right = NULL;
	}
}
*/
class Sol
{
    public static int height (Node N)
    {
        if (N == null) return 0;
        return N.height;
    }

    public static Node rotateRight (Node b)
    {
        Node a = b.left;
        Node c = a.right;

        a.right = b;
        b.left = c;

        b.height = Math.max (height (b.left), height (b.right)) + 1;
        a.height = Math.max (height (a.left), height (a.right)) + 1;

        return a;
    }
    
    public static Node rotateLeft (Node a)
    {
        Node b = a.right;
        Node c = b.left;

        b.left = a;
        a.right = c;

        a.height = Math.max (height (a.left), height (a.right)) + 1;
        b.height = Math.max (height (b.left), height (b.right)) + 1;

        return b;
    }

    public static int getBalance (Node N)
    {
        if (N == null) return 0;
        return height (N.left) - height (N.right);
    }
    
    public static Node insert (Node node, int key)
    {
        if (node == null) return (new Node (key));

        if (key < node.data) node.left = insert (node.left, key);
        else if (key > node.data) node.right = insert (node.right, key);
        else return node;

        node.height = 1 + Math.max (height (node.left), height (node.right));

        int balance = getBalance (node);
        if (balance > 1 && key < node.left.data) return rotateRight (node);
        if (balance < -1 && key > node.right.data) return rotateLeft (node);
        if (balance > 1 && key > node.left.data){
            node.left = rotateLeft (node.left);
	        return rotateRight (node);
        }
        if (balance < -1 && key < node.right.data){
	        node.right = rotateRight (node.right);
	        return rotateLeft (node);
        }
        return node;
    }
    
    public static Node minValueNode (Node node)
    {
        Node temp;
        for (temp = node; temp.left != null; temp = temp.left);

        return temp;
    }
    
    public static Node deleteNode(Node root, int key)
    {
        if (root == null) return root;
        if (key < root.data) root.left = deleteNode (root.left, key);
        else if (key > root.data) root.right = deleteNode (root.right, key);
        else{
	        if ((root.left == null) || (root.right == null)){
	            Node temp = null;
	            if (temp == root.left) temp = root.right;
	            else temp = root.left;
	            if (temp == null){
		            temp = root;
		            root = null;
	            }else root = temp;
	        }else{
	            Node temp = minValueNode (root.right);
	            root.data = temp.data;
	            root.right = deleteNode (root.right, temp.data);
	        }
        }
        if (root == null) return root;

        root.height = Math.max (height (root.left), height (root.right)) + 1;
        int balance = getBalance (root);
        if (balance > 1 && getBalance (root.left) >= 0) return rotateRight (root);
        if (balance > 1 && getBalance (root.left) < 0){
	        root.left = rotateLeft (root.left);
	        return rotateRight (root);
        }
        if (balance < -1 && getBalance (root.right) <= 0) return rotateLeft (root);
        if (balance < -1 && getBalance (root.right) > 0){
	        root.right = rotateRight (root.right);
	        return rotateLeft (root);
        }
        return root;
    }
}
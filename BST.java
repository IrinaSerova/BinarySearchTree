package com.irinaserova;

public class BST<E extends Comparable<E>>{
	// root of tree
	Node root;
	// size of tree
	int size;
	// Constructors
	BST (){
		root= null;
		size= 0;
	}

	class Node{	
		Node left;
		Node right;
		E data;
		Node parent;

		Node(){
			left = null;
			right = null;
			data = null;

		}
	}

	/* Implement add(E item) */
	public void add(E item) {

		if(root == null) {
			Node newNode = new Node();
			newNode.data = item;
			root = newNode;
			return;
		}
		addHelper(root, item);
	}

	public boolean addHelper(Node root, E item) {
		int value = item.compareTo(root.data);
		// if item is same as root's data means already in tree
		if(value == 0) {
			System.out.println("This item is already in tree");
			return false;
		}
		//   if item smaller than root
		if(value < 0) {
			//   if no left child then make a left child
			if(root.left == null) {
				Node newNode = new Node();
				root.left = newNode;
				newNode.data = item;
			}
			//  if there's left child keep going downwards
			else {
				addHelper(root.left, item);
			}
		}
		//if item is greater than root
		else {
			//if no right child then make a right child
			if(root.right == null) {
				Node newNode = new Node();
				root.right = newNode;
				newNode.data = item;
			}
			//if right child found, keep going downwards
			else {
				addHelper(root.right, item);
			}
		}
		return true;
	}
	/* Implement Node search(E item) */

	public E search(E item) {
		Node found = searchHelper(root, item);
		if(found == null) {
			System.out.println("Not in Tree");
		}
		else {
			System.out.println("Found " + found.data);
		}
		return found.data;
	}

	public Node searchHelper(Node root, E item) {
		//item not in tree or first one is item
		if(root == null || root.data == item) {
			return root;
		}
		if(root.data.compareTo(item) > 0) {
			return searchHelper(root.left, item);
		}
		return searchHelper(root.right, item);
	}

	// number of elements in tree
	public  void size() {

		System.out.println("Size is " + sizeHelper(root));



	}
	private int sizeHelper(Node root) {
		//Node current = root;
		if (root == null)
			return 0;
		else{
			int count = 1;
			count += sizeHelper(root.left);
			count += sizeHelper(root.right);
			return count;
		}
	}


	// height of the tree
	public void height() {
		System.out.println("Height is " + heightHelper(root));
	}
	private int heightHelper(Node root) {
		int heightLeft = 0;
		int heightRight = 0;
		if(root.left!=null)
			heightLeft = heightHelper(root.left);
		if(root.right!=null)
			heightRight = heightHelper(root.right);
		if(heightLeft > heightRight){
			return heightLeft+1;
		}
		else{
			return heightRight+1;
		}
	}


	/* Implement E Min(Node root) */
	public  E min() {
		System.out.println("Minimum value of BST is " + minHelper(root));
		return minHelper(root);
	}


	public E minHelper(Node root) {
		Node current = root;

		/* loop down to find the leftmost leaf */
		while (current.left != null) {
			current = current.left;
		}
		return (current.data);

	}	

	/* Implement E Max(Node root) */
	public  E max() {
		System.out.println("Maximum value of BST is " + maxHelper(root));
		return maxHelper(root);
	}

	private E maxHelper(Node root) {
		Node current = root;

		/* loop down to find the leftmost leaf */
		while (current.right != null) {
			current = current.right;
		}
		return (current.data);
	}	

	/* Implement Node successor(Node root, E item) */
	//  successor is the minimum value in right subtree
	// the first "left-type" parent node of node x   
	public  Node find(E item) {
		Node current = root; // Start from the root
		while (current != null) {
			if (item.compareTo(current.data) < 0) {
				current = current.left;
			}
			else if (item.compareTo(current.data) > 0) {
				current = current.right;
			}
			else // element matches current.element
				return current; // Element is found
		}
		return null;
	}
	public void successor(E item) {

		if(root == null) {
			System.out.println("The item has no successor");

		} 
		else {
			System.out.println("The successor of " + item + " is " + successorHelper(root, item));
			successorHelper(root, item);
		}
	}
	public E successorHelper(Node root, E item){

		Node current =  find(item);

		if(current.right != null) { // case 1: has left subtree

			current = current.right; //left subtree
			while(current.left != null) // find right most
				current = current.left;
			return current.data;
		}

		// case 2: no left subtree traverse until we hit the element

		Node parent = root; 

		while(parent != null && parent.right == root){
			if(current.data.compareTo(parent.data) > 0) {
				root = parent;
				parent = root.parent;
			}
			return parent.data;
		}

		return parent.data;
	}



	public void predecessor(E item) {

		if(predecessorHelper(item) == null) {
			System.out.println("The item has no predecessor");

		} 
		else {
			System.out.println("The predecessor of " + item + " is " + predecessorHelper(item));
			predecessorHelper(item);
		}
	}
	public E predecessorHelper(E item){

		Node current =  find(item);
		if(current == null) {
			return null;

		}
		if(current.left != null) { // case 1: has left subtree

			current = current.left; //left subtree
			while(current.right != null) // find right most
				current = current.right;
			return current.data;
		}
		else { // case 2: no left subtree traverse until we hit the element

			Node parent = root; 
			Node predecessor = null;
			while(parent != null){
				if(current.data.compareTo(parent.data) > 0) {
					predecessor = parent;
					parent = parent.right;
				}
				else{
					parent = parent.left;
				}
			}
			if(predecessor == null) {
				return null;
			}
			else

				return predecessor.data;

		}

	}


	/* Implement inOrder(), preOrder(), postOrder() traversal functions */
	public void preorder() {
		if(root == null) {
			System.out.println("root is null");
		}
		preorderHelper(root);
	}
	private void preorderHelper(Node root) {
		if(root != null) {
			System.out.println(root.data);
			preorderHelper(root.left);
			preorderHelper(root.right);
		}
	}

	public void inorder() {
		if(root == null) {
			System.out.println("root is null");
		}
		inorderHelper(root);
	}

	private void inorderHelper(Node root) {
		if(root != null) {
			inorderHelper(root.left);
			System.out.println(root.data);
			inorderHelper(root.right);
		}
	}

	public void postorder() {
		if(root == null) {
			System.out.println("root is null");

		}
		postorderHelper(root);

	}
	private void postorderHelper(Node root) {
		if(root != null) {
			postorderHelper(root.left);
			postorderHelper(root.right);
			System.out.println(root.data);

		}
	}


	//	/* Implement delete(E item) */
	public void delete(E item) {
		root = deleteHelper(root, item);
	}
	private Node deleteHelper(Node root, E item) {
		if (root == null) {
			return root; // If the tree is empty
		}
		// recur down the tree
		if (item.compareTo(root.data) < 0)
			root.left = deleteHelper(root.left, item);
		else if (item.compareTo(root.data) > 0)
			root.right = deleteHelper(root.right, item);

		else {
			// node with only one child or no child
			if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;
			root.data = min();
			//	root.data = min(root.right);
			// Delete the inorder successor
			root.right = deleteHelper(root.right, root.data);
		}

		return root;
	}



	/* Implement clearAll() */
	public void clearAll() {
		root = null;
		size = 0;
	}

	//check if tree is empty
	public boolean isEmpty() {
		System.out.println("The tree is empty!");
		return (root == null);

	}

	public static void main(String[] args) {
		BST <Integer> treeDemo = new BST<Integer>();
		System.out.println("Implement add");
		treeDemo.add(Integer.valueOf(7));
		treeDemo.add(Integer.valueOf(4));
		treeDemo.add(Integer.valueOf(12));
		treeDemo.add(Integer.valueOf(10));
		treeDemo.add(Integer.valueOf(16));
		treeDemo.add(Integer.valueOf(3));
		treeDemo.add(Integer.valueOf(14));
		treeDemo.add(Integer.valueOf(22));
		treeDemo.add(Integer.valueOf(11));
		treeDemo.add(Integer.valueOf(15));
		treeDemo.search(10);
		treeDemo.size();
		treeDemo.height();
		treeDemo.min();
		treeDemo.max();
		treeDemo.successor(12);
		treeDemo.predecessor(4);
		System.out.println("Inorder");
		treeDemo.inorder();
		System.out.println("PreOrder");
		treeDemo.preorder();
		System.out.println("PostOrder");
		treeDemo.postorder();
		treeDemo.delete(22);
		System.out.println("Item deleted");
		treeDemo.inorder();
		treeDemo.clearAll();
		treeDemo.isEmpty();

	}
}

package com.irinaserova;



public class BST<E extends Comparable<E>>{
	// root of tree
	Node root;
	// size of tree
	int size;

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

	public void search(E item) {
		Node found = searchHelper(root, item);
		if(found == null) {
			System.out.println("Not in Tree");
		}
		else {
			System.out.println("Found " + found.data);
		}
	}

	private Node searchHelper(Node root, E item) {
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
	public void size() {
		return;

	}
	// height of the tree
	public void height() {

	}

	/* Implement E Min(Node root) */
	public E min(Node root) {
		Node current = root;

		/* loop down to find the leftmost leaf */
		while (current.left != null) {
			current = current.left;
		}
		return (current.data);

	}	

	/* Implement E Max(Node root) */
	public E max(Node root) {
		return null;
	}

	public Node predecessor(Node root, E item) {
		return null;
	}

	/* Implement Node successor(Node root, E item) */
	public Node succesor(Node root, E item) {
		if (root == null) 
			return null; // no successor
		else if (root.right != null) {
			root = root.right;
			while (root.left != null) {
				root = root.left;
			}
			return root;
		} else {
			Node parent = root.parent;
			while (parent != null && parent.right == root) {
				root = parent;
				parent = root.parent;
			}
			return parent;
		}
	}
	/* Implement inOrder(), preOrder(), postOrder() traversal functions */
	public void preorder(Node root) {
		if(root != null) {
			System.out.println(root.data);
			preorder(root.left);
			preorder(root.right);
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


	/* Implement delete(E item) */
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
			root.data = min(root.right);
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
		return (root == null);

	}

	public static void main(String[] args) {
		BST <Integer> treeDemo = new BST<Integer>();
		System.out.println("In order");
		treeDemo.add(Integer.valueOf(4));
		treeDemo.add(Integer.valueOf(7));
		treeDemo.add(Integer.valueOf(12));
		treeDemo.add(Integer.valueOf(10));
		treeDemo.add(Integer.valueOf(16));
		treeDemo.add(Integer.valueOf(3));
		treeDemo.add(Integer.valueOf(14));
		treeDemo.add(Integer.valueOf(22));
		treeDemo.add(Integer.valueOf(11));
		treeDemo.add(Integer.valueOf(15));
		treeDemo.inorder();
		System.out.println("Post order");
		// newTree.clearAll();
		treeDemo.postorder();
		treeDemo.search(10);


	}
}

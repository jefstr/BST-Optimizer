

///
/// Contents: Binary Tree Implementation
/// Author:   Jeff Parvin
/// Date:     January 2012
///

public class BinaryTree {

	private Node root;
	private int cost;

	// default constructor
	public BinaryTree(){
		root=null;
	}

	// leaf constructor
	public BinaryTree(String k){
		Node temp = new Node(k);
		root = temp;
	}

	// tree constructor
	public BinaryTree(String k, Node l, Node r){
		Node temp = new Node(k,l,r);
		root = temp;
	}

	// setters
	public void setCost(int c){
		cost = c;
	}

	// getters
	public int getCost(){
		return cost;
	}

	public Node getRoot(){
		return root;
	}

	public String getRootKey(){
		return root.getKey();
	}


	// prints tree sideways (root at left, leaves growing to the right)
	public void printTree(){  	
		printTree(root,"");
	}

	private void printTree(Node currentNode,String tabs){
		if(currentNode!=null){
			tabs+="\t";
			printTree(currentNode.getRight(),tabs);
			System.out.println(tabs+currentNode.getKey()+"\n");
			printTree(currentNode.getLeft(),tabs);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/// Private Inner Node Class
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private class Node{

		private String key;
		private Node left;
		private Node right;

		public Node(){
			key=null;
			left=null;
			right=null;
		}

		public Node(String k){
			key=k;
			left=null;
			right=null;
		}

		public Node(String k, Node l, Node r){
			key = k;
			left=l;
			right=r;
		}

		public String getKey(){
			return key;
		}

		public Node getLeft(){
			return left;
		}

		public Node getRight(){
			return right;
		}

	}

}


import java.util.ArrayList;
import java.util.Collections;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// Contents: Test BSTOptimizer methods.
/// Author:   John Aronis
/// Date:     January 2012
////////////////////////////////////////////////////////////////////////////////////////////////////////////////


public class BSTOptimizer {

	private ArrayList<Key> keys = new ArrayList<Key>();
	private int calls=0;
	private boolean memo=false;

	// default constructor
	public BSTOptimizer(){

	}

	// clear keys method
	public void clearKeys(){
		keys.clear();

	}

	// gets the number of calls
	public int getCalls(){
		return calls;
	}

	// sets memoization variable
	public void setMemo(boolean m){
		memo = m;
	}

	// addKey method - adds keys to key array for storage until optimization
	public void addKey(String k, int f){
		Key temp = new Key(k,f);			//make a new key
		keys.add(temp);						//add new key to key array
	}

	// sort key method - sorts keys based off of their name value
	public Key[] sortKeys(){
		Collections.sort(keys);
		Key[] sortedKeys = new Key[keys.size()];
		keys.toArray(sortedKeys);
		return sortedKeys;
	}

	// recursive optimize method - finds optimal BST
	public BinaryTree optimize(){	
		calls=0;
		Key[] k = sortKeys();
		BinaryTree[][] known = new BinaryTree[k.length][k.length];

		return optimize(k, 0, k.length-1, known);
	}

	// private overloaded optimize method
	private BinaryTree optimize(Key[] keyList, int left, int right, BinaryTree[][] known){
		calls++;

		BinaryTree minTree = new BinaryTree("empty");

		// check memo if memoization is turned on
		if(memo==true && left<=right){

			if(known[left][right]!=null){
				return known[left][right];
			}
		}

		// find sum of all possible roots for this call
		int C=0;
		for(int i=left;i<=right;i++){
			C+=keyList[i].getFreq();
		}

		// base case
		if(right<=left || left>=right){

			if(left==right){
				BinaryTree tempTree = new BinaryTree(null);
				minTree = new BinaryTree(keyList[right].getName(),tempTree.getRoot(),tempTree.getRoot());
				minTree.setCost(C);
			}

			else{
				return new BinaryTree(null);
			}

		}

		// other case
		else{

			for(int i=left;i<=right;i++){

				BinaryTree lTree = optimize(keyList,left,i-1,known);
				BinaryTree rTree = optimize(keyList,i+1,right,known);

				BinaryTree testTree = new BinaryTree(keyList[i].getName(), lTree.getRoot(), rTree.getRoot());
				testTree.setCost(lTree.getCost()+rTree.getCost()+C);

				if(minTree.getRootKey().equals("empty") || minTree.getCost()>testTree.getCost()){
					minTree = testTree;	
				}
			}
		}

		known[left][right] = minTree;
		return minTree;
	}


	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/// Private Inner Key Class
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private class Key implements Comparable<Key>{
		private String name;
		private int freq;

		public Key(){
			name = null;
			freq = 0;
		}

		public Key(String n, int f){
			name = n;
			freq = f;
		}

		public String getName(){
			return name;
		}

		public int getFreq(){
			return freq;
		}


		// Override compareTo
		public int compareTo(Key k) {
			return this.name.compareTo(k.getName());
		}

		public String toString(){
			return "("+name+","+freq+")";
		}

	}
}

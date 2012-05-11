import java.util.Random;


public class TestBSTOptimizer {

	public static void main(String[] args) {

		///
		/// Set up experiment:
		///

		int MIN_KEYS = 1 ;
		int MAX_KEYS = 100 ;
		int INCREMENT = 1 ;
		int MIN_FREQUENCY = 1 ;
		int MAX_FREQUENCY = 100 ;

		Random R = new Random() ;

		///
		/// Simple test to show functionality
		///
		
		BSTOptimizer bop = new BSTOptimizer();

		///
		/// Create objects with add(NAME,FREQUENCY):
		///
		
		bop.addKey("F",2) ;
		bop.addKey("B",2) ;
		bop.addKey("D",3) ;
		bop.addKey("A",4) ;
		bop.addKey("E",5) ;
		bop.addKey("G",1) ;
		bop.addKey("C",1) ;

		BinaryTree bestTree;

		System.out.println("BST Optimization without Memoization");
		bop.setMemo(false);
		bestTree = bop.optimize();
		System.out.println("Recursive Calls: "+bop.getCalls());
		System.out.println("Cost of Optimal Tree: "+bestTree.getCost());
		bestTree.printTree();		// prints tree sideways (root at left, leaves growing to the right)

		System.out.println("BST Optimization with Memoization");
		bop.setMemo(true);
		bestTree = bop.optimize();
		System.out.println("Recursive Calls: "+bop.getCalls());
		System.out.println("Cost of Optimal Tree: "+bestTree.getCost());
		bestTree.printTree();

		///
		/// Test performance
		///

		BSTOptimizer bopMem = new BSTOptimizer();
		bopMem.setMemo(true);
		BSTOptimizer bopNonMem = new BSTOptimizer();
		bopNonMem.setMemo(false);

		System.out.println("DEMONSTRATE PERFORMANCE WITH MEMOIZATION") ;
		System.out.println("\t[N,Calls/Keys^3]: ") ;

		for (int keys=MIN_KEYS ; keys<=MAX_KEYS ; keys+=INCREMENT) {

			bopMem.clearKeys();
			for (int k=0 ; k<keys ; k++) { 
				bopMem.addKey("K"+k,MIN_FREQUENCY+R.nextInt(MAX_FREQUENCY)) ; 
			}

			bestTree = bopMem.optimize() ;
			System.out.println( "\t [" + keys + "," + bopMem.getCalls()/(float)(keys*keys*keys) + "]") ;
			//bestTree.printTree();

		}

		System.out.println("--------------------") ;
		System.out.println("DEMONSTRATE PERFORMANCE WITHOUT MEMOIZATION") ;
		System.out.println("\t[N,Calls/Keys^3]: ") ;

		for (int keys=MIN_KEYS ; keys<=MAX_KEYS ; keys+=INCREMENT) {

			bopNonMem.clearKeys();
			for (int k=0 ; k<keys ; k++) { 
				bopNonMem.addKey("K"+k,MIN_FREQUENCY+R.nextInt(MAX_FREQUENCY)) ; 
			}

			bestTree = bopNonMem.optimize() ;
			//bestTree.printTree();
			System.out.println( "\t [" + keys + "," + bopNonMem.getCalls()/(float)(keys*keys*keys) + "]") ;
		}

		System.out.println("=====================") ;


	}

}

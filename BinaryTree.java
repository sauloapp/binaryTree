
/**
 * implements a not-balanced binary search tree.
 * 
 * This in a very old code I wrote in 1997 to aid the parsing of records collected
 * from a Web Cache. 
 * 
 * Since it is so old it doesn't use any generics. The algorithms to build (constructor
 * and insert methods) were inspired by Sedgewick's "Algorithms in C" books and they don't use
 * any recursion: they're iterative and very fast!  
 * 
 * In order to illustrate how to use the tree, take a look at the main() method and at the 
 * StringObject class.
 * 
 * In order to use it besides the example application that is in the main() method in the
 * end of this file, you must define your own "counting object" that extends the 
 * CounterObject class and provide code for the abstract methods.
 * 
 * 
 */
/*	Historical comments:  :-) 	*/
//==> Like that exposed by Sedgewick in the Algorithms in C book (1990)
//==> By saulo@dcc.ufmg.br 1997/05

//     THIS E-MAIL IS NOT USED ANYMORE!
//	   If you wish send any thoughts to sauloaugusto@gmail.com

import java.util.StringTokenizer;

public class BinaryTree {

	protected BiNode head, tail;

	protected int nbElements,    // number of nodes in this tree
						last,    // indexes the sorted array
						ii, jj;  // for Quicksort method since there is no "by reference parameters"
	   							 // in Java

	/**
	 * Creates an empty binary tree with head and tail nodes.
	 * 
	 * @param init: an initialization node that is used to finish operations as Sedgewick proposed.
	 */
	public BinaryTree(CounterObject init) {
		this.head = new BiNode(init);
		this.tail = new BiNode(init);
		this.head.changeLeft (this.tail);
		this.head.changeRight (this.tail);
		this.tail.changeLeft (this.tail);
		this.tail.changeRight (this.tail);
		this.nbElements = 0;
		this.last = 0;
	}

	public int getNumberOfElements() {
		return this.nbElements;
	}

	/**
	 * Inserts the element in the tree:
	 * 	
	 * 						If it is already in the tree updates its occurrences.
	 * @param element
	 */
	public void insert (CounterObject element)  {
		BiNode walker,
		sentinel;
		boolean end = false;

		sentinel = this.head;
		walker = (BiNode)this.head.getRight();
		while ( (walker != this.tail) && (end == false) ) {
			sentinel = walker;
			if ( element.lessThan (walker.getValue()) )
				walker = walker.getLeft();
			else
				if ( element.greaterThan (walker.getValue()) )
					walker = (BiNode)walker.getRight();
				else {  				// already in the tree
					walker.setOccurrences(walker.getOccurrences() + element.getOccurrences());
					//System.err.println("UPDATING " + walker.getValue().getValue() + " occurrences: " +
					//                    walker.getOccurrences());
					return;
				}
		}
		// element is not in the tree		
		//System.err.println("Inserting " + element.getValue() + " for the first time.");
		walker = new BiNode(element);
		walker.changeLeft (this.tail);
		walker.changeRight (this.tail);
		if ( walker.getValue().lessThan (sentinel.getValue()) )
			sentinel.changeLeft (walker);
		else
			sentinel.changeRight (walker);
		this.nbElements++;
	}  //==> Insert

	/**
	 * Generates a sorted array of CounterObject by traversing the three "in order".
	 * 
	 * @return the sorted array
	 */
	public CounterObject[] generateSortedVector () {
	//==> Generates a ordered array of MyObjects from this tree
		CounterObject[] result = new CounterObject[this.getNumberOfElements()];

		this.generateInOrder ((BiNode)this.head.getRight(), result);

		return result;
	}

	/* THESE QUICKSORT RELATED METHODS ARE ONLY TO YOU PLAY...
	 * FOR EXAMPLE, YOU COULD COMPARE RUNNING TIMES TO SORT AN
	 * ARRAY OF CounterObjects INSERTING THEM IN A BINARY TREE
	 * VERSUS INSERTING THEM IN AN ARRAY AND QUICKSORTING IT.*/
	
	public void quickSort (CounterObject[] a) {
		this.quickSortByOccurrences (a, 0, (a.length - 1));
	}
	
	public void sortByOccurrences (CounterObject[] a) {
		this.quickSortByOccurrences (a, 0, (a.length - 1));
	}
	
	/**
	 * Take a look at the very exquisite comments below! Huhu!
	 * 
	 * @param walker: points to the root
	 * @param result: the array to get the objects in the tree.
	 */
	protected void generateInOrder (BiNode walker, CounterObject[] result) {
	//==> Generates a ordered array of MyObjects in result traversing the
	//==> this tree in order
		if ( walker != this.tail ) {
			this.generateInOrder(walker.getLeft(), result);
			result[this.last++] = walker.getValue(); 
			this.generateInOrder((BiNode)walker.getRight(), result);
		}
	}


	protected void quickSortByOccurrences (CounterObject a[], int left, int right) {
	//==> By Sedgewick in Algorithms in C, 1990
		partitionByOccurrences (a, left, right);
		if ( left < jj ) {
			quickSortByOccurrences (a, left, jj);
		}
		if ( right > ii ) {
			quickSortByOccurrences (a, ii, right);
		}
	}  //==> quickSortByOccurrences


	protected void partitionByOccurrences (CounterObject a[], int left, int right) {
		// using exception handling here only to have fun!
		try{
			float pivot;
			CounterObject aux;

			ii = left;
			jj = right;
			pivot = a[ (ii + jj) / 2 ].getOccurrences();
			for ( ; ii <= jj; )	{
				while ( a[ii].getOccurrences() < pivot )      
					ii++;
				while ( a[jj].getOccurrences() > pivot )      
					jj--;
				if ( ii <= jj )	{
					aux = a[ii];
					a[ii] = a[jj];
					a[jj] = aux;
					ii++;
					jj--;
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException aioobe) {
			System.err.println ("Indice invalido. Tamanho do array: " + a.length + ". ii = " + ii + ". jj = " + jj);
			throw new ArrayIndexOutOfBoundsException("Indice invalido. Tamanho do array: " + a.length + ". ii = " + ii + ". jj = " + jj);
			//	      System.exit(0);
		}
	}  //==> partitionByOccurrences


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "Let it be, let it be.\n	Speaking words of wisdom, let it be...Pan, pan";
		StringTokenizer st = new StringTokenizer(text, " ,.\n\t");
		BinaryTree tree = new BinaryTree(new StringObject("Beatles Amazing Song"));
		
		while (st.hasMoreTokens()) {
			tree.insert(new StringObject(st.nextToken()));
		}

        
		CounterObject[] v = tree.generateSortedVector();
        for (int index = 0; index < v.length; index++)
           System.out.println(((StringObject)v[index]).getValue());
        
        
        tree.sortByOccurrences(v);
        for (int index = 0; index < v.length; index++) {
            System.out.print(((StringObject)v[index]).getValue());
            System.out.println("  --  " + v[index].getOccurrences());
        }

	}

}

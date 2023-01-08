# binaryTree
A not balanced non-recursive Java binary search tree inspired by the Sedgewick's implementation as presented in his "Algorithms" books (mainly Algorithms in C, 1990). The tree sticks with the rule that for each node all nodes with smaller search keys are in its left subtree and all nodes with greater keys are in its right subtree.

The BinaryTree only provides initialization (constructor) and insertion as public methods to manipulate the tree structure. The "in order" traversal is used to sort the elements of the tree out to an sorted array of CounterObjects. Yes, there is no search method at all!. But it's easily implemented adapting the insert method to not insert and to not increment the occurences count... Implementing a search procedure is left as a (very simple) exercise!


Each node stores, in the minimum, the number of occurrences of the object (a CounterObject) being represented for a given application. As usual, the tree only holds one search key, but it counts how many insertions of the same key are performed (thatis the number of occurrences of the objects represented by the key).

Besides, Sedgewick's implementation of the QuickSort algorithms is available in the BinaryTree class only to someone play with it comparing performances for two sorting methods with O(nlogn) time complexity...


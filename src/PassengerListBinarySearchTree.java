 /* 
  * File name: PassengerListBinarySearchTree.java
  * 
  * Programmer: Christopher Runyan
  * ULID: caruny1
  * 
  * Date: 4/19/2016
  * 
  * Class: IT 179
  * Lecture Section: 03
  * Lecture Instructor: Cathy Holbrook
  */
package edu.ilstu;

/**
 * Binary search tree managing a list of passengers for a cruise. Contains methods to modify and return parts of 
 * the tree as well as append to an output file via a static class, Output.
 * 
 * @author Christopher Runyan
 */
public class PassengerListBinarySearchTree{
	private Node root;
	private Name deleteReturn;
	final String OUTPUT_FILENAME="caruny1Cruise.txt";
	
	/**
	 * Default constructor
	 */
	public PassengerListBinarySearchTree(){
		root=null;
		deleteReturn=null;
	}
	
	/**
	 * Constructor accepting a value for the root
	 * 
	 * @param root root of binary search tree 
	 */
	public PassengerListBinarySearchTree(Node root){
		this.root=root;
	}
	
	/**
	 * Inserts a name object into the binary search tree
	 * 
	 * @param name passenger name to be inserted into the binary search tree
	 */
	public void insert(Name name){
		root=insert(root, name);
	}
	
	/**
	 * Helper method to insert a name object into the binary search tree
	 * 
	 * @param node place keeper to recursively search through the binary tree until the target can be inserted into
	 * the proper place within the tree
	 * @param target name to be added into the binary search tree
	 * @return place keeper to recursively search through the binary tree until the target can be inserted into the
	 * proper place within the tree
	 */
	private Node insert(Node node, Name target){
		if(node==null){
			node=new Node(target);
		}
		else{
			if(target.compareTo(node.name)<0){
				node.left=insert(node.left, target);
			}
			else{
				if(target.compareTo(node.name)>0){
					node.right=insert(node.right, target);
				}
				else{
					Output.appendToFile(OUTPUT_FILENAME, "duplicate name");
				}
			}
		}
		return node;
	}
	
	/**
	 * Deletes a name object from the binary search tree
	 * 
	 * @param name passenger name to be deleted from the binary search tree 
	 * @return name deleted from the binary search tree
	 */
	public Name delete(Name name){
		root=delete(root, name);
		return deleteReturn;
	}
	
	/**
	 * Helper method to delete a name object into the binary search tree
	 * 
	 * @param localRoot place keeper to recursively search through the binary tree until the target can be removed 
	 * into the proper place within the tree
	 * @param target name to be added into the binary search tree
	 * @return place keeper to recursively search through the binary tree until the target can be removed into the 
	 * proper place within the tree
	 */
	private Node delete(Node localRoot, Name target){
		if(localRoot==null){
			deleteReturn=null;
			return localRoot;
		}
		if(target.compareTo(localRoot.name)<0){
			localRoot.left=delete(localRoot.left, target);
			return localRoot;
		}
		else if(target.compareTo(localRoot.name)>0){
			localRoot.right=delete(localRoot.right, target);
			return localRoot;
		}
		else{
			deleteReturn = localRoot.name;
			
			if(localRoot.left==null)    
			{                             
				return localRoot.right;
			}
			else if(localRoot.right==null) 
			{
				return localRoot.left;
			}
			else    
			{      
				if(localRoot.left.right==null)    
				{                                  
					localRoot.name = localRoot.left.name;   
					localRoot.left = localRoot.left.left;   
					return localRoot;
				}
				else   
				{      
					localRoot.name = findLargestChild(localRoot.left);
					return localRoot;
				}
			}
		}
	}
	
	/**
	 * Helper method to delete a name object from the binary search tree 
	 * 
	 * @param parent parent of children to search for the largest child of
	 * @return the largest child of the accepted parent as a name object
	 */
	private Name findLargestChild(Node parent)
	{
		if(parent.right.right == null) 
		{                              
			Name returnValue = parent.right.name;
			parent.right = parent.right.left;
			return returnValue;
		}
		else
		{
			return findLargestChild(parent.right);
		}
	}
	
	/**
	 * Appends the contents of the binary search tree to an output file via a static class, Output
	 */
	public void printInOrder(){
		printInOrder(root);
		Output.appendToFile(OUTPUT_FILENAME, "\n");
	}
	
	/**
	 * Helper method to print the content of the binary search tree
	 * 
	 * @param node place keeper to recursively print through the binary tree
	 */
	private void printInOrder(Node node){
		if(node!=null){
			printInOrder(node.left);
			Output.appendToFile(OUTPUT_FILENAME, "\n\t"+node.name);
			printInOrder(node.right);
		}
	}
	
	/**
	 * Inner class node for a binary search tree
	 * 
	 * @author Christopher Runyan
	 */
	private class Node{
		Node left;
		Node right;
		Name name;
		
		public Node(Name name){
			left=null;
			right=null;
			this.name=name;
		}
	}
}

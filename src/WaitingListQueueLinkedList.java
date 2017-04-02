 /* 
  * File name: WaitingListQueueLinkedList.java
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
 * Queue linked list managing a list of passengers waiting to be on a passenger list for a cruise. Contains methods
 * to modify and return parts of the queue as well as append to an output file via a static class, Output.
 * 
 * @author Christopher Runyan
 */
public class WaitingListQueueLinkedList{
	private Node front;
	private Node back;
	private int size;
	final String OUTPUT_FILENAME="caruny1Cruise.txt";
	
	/**
	 * Default constructor
	 */
	public WaitingListQueueLinkedList(){
		front=null;
		back=null;
		size=0;
	}
	
	/**
	 * Has a name object to represent a passenger as a parameter and immediately inserts name object into the queue
	 * linked list
	 * 
	 * @param name passenger name to add to the queue linked list
	 * @return true if the name was inserted into the queue or false if the name was not inserted
	 */
	public boolean offer(Name name){
		boolean inserted=true;
		
		if(front==null){
			front=new Node(name);
			back=front;
		}
		else{
			back.next=new Node(name);
			back=back.next;
		}
		size++;
		
		return inserted;
	}
	
	/**
	 * Retrieves, returns, and removes the head of the queue linked list of passengers (the first passenger added to
	 * the list) or returns null if the queue linked list is empty. 
	 * 
	 * @return the head of the queue linked list of passengers, or null if the queue linked list is empty
	 */
	public Name poll(){
		Name firstName=null;
		
		if(front!=null){
			firstName=front.name;
			front=front.next;
			size--;
		}
		
		return firstName;
	}
	
	/**
	 * Retrieves and returns the head of the queue linked list of passengers (the first passenger added to the list)
	 * or returns null if the queue linked list is empty
	 * 
	 * @return the head of the queue linked list of passengers, or null if the queue linked list is empty
	 */
	public Name peek(){
		Name firstName=null;
		
		if(front!=null){
			firstName=front.name;
		}
		
		return firstName;
	}
	
	/**
	 * Has a name object to represent a passenger as a parameter, retrieves and removes this name object from the
	 * queue linked list if it is in it, or returns null if it is not
	 * 
	 * @param name passenger name to remove from the queue linked list
	 * @return the passenger name removed or null if the passenger name accepted as a parameter is not in the 
	 * queue linked list of passengers
	 */
	public Name delete(Name name){
		Name deleteReturn=null;
		Node curr=front;
		
		if(doesExist(name)){
			if(front!=null){
				if(front.name!=name){
					while(curr.next.name!=name&&curr.next!=null){
						curr=curr.next;
					}
					curr.next=curr.next.next;
					size--;
				}
				else{
					front=front.next;
				}
			}
		}
		
		return deleteReturn;
	}
	
	/**
	 * Has a name object to represent a passenger as a parameter, searches the queue linked list and returns true
	 * if the name object is in the list or false if it is not
	 * 
	 * @param name passenger name to check attendance for in the queue linked list
	 * @return true if the name object is in the list or false if it is not
	 */
	private boolean doesExist(Name name){
		boolean exists=false;
		Node curr=front;
		
		if(front!=null){
			while(front.name!=name&&curr!=null){
				curr=curr.next;
			}
			if(curr!=null){
				exists=true;
			}
		}
		
		return exists;
	}
	
	/**
	 * Appends the content of the queue linked list to an output file via a static class, Output
	 */
	public void print(){
		Node curr=front;
		
		if(front!=null){
			while(curr!=null){
				Output.appendToFile(OUTPUT_FILENAME, "\t"+curr.name.toString()+"\n");
				curr=curr.next;
			}
		}
	}
	
	/**
	 * Returns the number of name objects in the queue linked list
	 * 
	 * @return the number of name objects in the queue linked list
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * Inner class node for a queue linked list
	 * 
	 * @author Christopher Runyan
	 */
	private class Node{
		private Name name;
		private Node next;
		
		public Node(Name name){
			this.name=name;
			next=null;
		}
	}
}

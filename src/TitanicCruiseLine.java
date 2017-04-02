 /* 
  * File name: TitanicCruiseLine.java
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
 * Contains methods to perform transactions for Titanic Cruise Line. Can add a passenger to a cruise or waiting list,
 * remove a passenger from a cruise or waiting list, print passenger and waiting lists for all cruises, and
 * depart a cruise.
 * 
 * @author Christopher Runyan
 */
public class TitanicCruiseLine{
	private PassengerListBinarySearchTree[] cruises=new PassengerListBinarySearchTree[30];
	private WaitingListQueueLinkedList[] waitingList=new WaitingListQueueLinkedList[30];
	private int[] passengerCounter=new int[30];
	private int[] waitingListCounter=new int[30];
	private boolean[] departed=new boolean[30];
	final String OUTPUT_FILENAME="caruny1Cruise.txt";
	
	/**
	 * Accepts a name representing a passenger and a day for the unique cruise departing on that date and adds
	 * the passenger to the passenger list of the cruise departing on the given day or, if there are six people
	 * in the passenger list, adds the passenger to a waiting list of passengers for the cruise departing on
	 * the given day.
	 * 
	 * @param day day of cruise depart
	 * @param passengerName name of passenger booking a cruise
	 */
	public void doTransactionOne(int day, Name passengerName){
		if(departed[day-1]==false){
			if(passengerCounter[day-1]<6){
				if(passengerCounter[day-1]==0){
					cruises[day-1]=new PassengerListBinarySearchTree();
				}
				cruises[day-1].insert(passengerName);
				passengerCounter[day-1]++;
				Output.appendToFile(OUTPUT_FILENAME, "Passenger "+passengerName+" has been added to the passenger list for the cruise departing on day "+day+"\n");
			}
			else{
				if(waitingListCounter[day-1]==0){
					waitingList[day-1]=new WaitingListQueueLinkedList();
				}
				waitingList[day-1].offer(passengerName);
				waitingListCounter[day-1]++;
				Output.appendToFile(OUTPUT_FILENAME, "Passenger "+passengerName+" has been added to the waiting list for the cruise departing on day "+day+"\n");
			}
		}
		else{
			Output.appendToFile(OUTPUT_FILENAME, "Cannot add "+passengerName+" to the cruise departing on day "+day+" because the cruise has already departed\n");
		}
	}
	
	/**
	 * Accepts a name representing a passenger and a day for the unique cruise departing on that date and removes
	 * the passenger in the passenger list of the cruise departing on the given day or, if there are less than six 
	 * people in the passenger list after a passenger is removed, adds to the passenger list the first passenger
	 * on the waiting list for the cruise departing the given day.
	 * 
	 * @param day day of cruise depart
	 * @param passengerName name of passenger booking a cruise
	 */
	public void doTransactionTwo(int day, Name passengerName){
		if(departed[day-1]==false){
			if(passengerCounter[day-1]!=0){
				if(cruises[day-1].delete(passengerName)==null){
					Output.appendToFile(OUTPUT_FILENAME, "Passenger "+passengerName+" was not on the passenger list for day "+day+" to delete\n");
				}
				else{
					passengerCounter[day-1]--;
					Output.appendToFile(OUTPUT_FILENAME, "Passenger "+passengerName+" has been deleted from the passenger list for the cruise departing on day "+day+"\n");
					if(passengerCounter[day-1]==5&&waitingListCounter[day-1]!=0){
						passengerName=waitingList[day-1].poll();
						cruises[day-1].insert(passengerName);
						waitingListCounter[day-1]--;
						passengerCounter[day-1]++;
						Output.appendToFile(OUTPUT_FILENAME, "Passenger "+passengerName+" has been moved from the waiting list to the passenger list on day "+day+"\n");
					}
				}
			}
			else{
				Output.appendToFile(OUTPUT_FILENAME, "Passenger "+passengerName+" was not on the passenger list for day "+day+" to delete\n");
			}
			if(waitingListCounter[day-1]!=0){
				if(waitingList[day-1].delete(passengerName)==null){
					Output.appendToFile(OUTPUT_FILENAME, "Passenger "+passengerName+" was not on the waiting list for day "+day+" to delete\n");
				}
				else{
					waitingListCounter[day-1]--;
					Output.appendToFile(OUTPUT_FILENAME, "Passenger "+passengerName+" has been deleted from the waiting list for the cruise departing on day "+day+"\n");
				}
			}
			else{
				Output.appendToFile(OUTPUT_FILENAME, "Passenger "+passengerName+" was not on the waiting list for day "+day+" to delete\n");
			}
		}
		else{
			Output.appendToFile(OUTPUT_FILENAME, "Cannot delete "+passengerName+" to the cruise departing on day "+day+" because the cruise has already departed\n");
		}
	}
	
	/**
	 * Accepts a name representing a passenger and a day for the unique cruise departing on that date and prints
	 * the list of passengers for the cruise departing on the given date and the waiting list for the cruise
	 * departing on the given date.
	 * 
	 * @param day day of cruise depart
	 * @param passengerName name of passenger booking a cruise
	 */
	public void doTransactionThree(int day, Name passengerName){
		if(departed[day-1]==false){
			Output.appendToFile(OUTPUT_FILENAME, "Passenger list for cruise departing on day "+day+":");
		}
		else{
			Output.appendToFile(OUTPUT_FILENAME, "Passenger list for cruise that departed on day "+day+":");
		}
		if(passengerCounter[day-1]>0){
			cruises[day-1].printInOrder();
			Output.appendToFile(OUTPUT_FILENAME, "Total number of passengers: "+passengerCounter[day-1]+"\n");
		}
		else{
			Output.appendToFile(OUTPUT_FILENAME, "\nNo passengers in list\n");
		}
		if(departed[day-1]==false){
			Output.appendToFile(OUTPUT_FILENAME, "Waiting list for cruise departing on day "+day+":\n");
		}
		else{
			Output.appendToFile(OUTPUT_FILENAME, "Waiting list for cruise that departed on day "+day+":\n");
		}
		if(waitingListCounter[day-1]>0){
			waitingList[day-1].print();
		}
		else{
			Output.appendToFile(OUTPUT_FILENAME, "No passengers in waiting list\n");
		}
	}
	
	/**
	 * Accepts a name representing a passenger and a day for the unique cruise departing on that date and "departs"
	 * the cruise for the given date, meaning that no more passengers can be added to the cruise or the waiting 
	 * list for the cruise
	 * 
	 * @param day day of cruise depart
	 * @param passengerName name of passenger booking a cruise
	 */
	public void doTransactionFour(int day, Name passengerName){
		if(departed[day-1]==false){
			departed[day-1]=true;
			Output.appendToFile(OUTPUT_FILENAME, "Cruise for day "+day+" has departed.\nFinal passenger list:");
			if(passengerCounter[day-1]>0){
				cruises[day-1].printInOrder();
				Output.appendToFile(OUTPUT_FILENAME, "Total number of passengers: "+passengerCounter[day-1]+"\n");
			}
			else{
				Output.appendToFile(OUTPUT_FILENAME, "\nNo passengers in list\n");
			}
			Output.appendToFile(OUTPUT_FILENAME, "Final waiting list:\n");
			if(waitingListCounter[day-1]>0){
				waitingList[day-1].print();
			}
			else{
				Output.appendToFile(OUTPUT_FILENAME, "No passengers in waiting list\n");
			}
		}
		else{
			Output.appendToFile(OUTPUT_FILENAME, "Cannot depart cruise departing on day "+day+" because cruise has already departed\n");
		}
	}
}

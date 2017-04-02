 /* 
  * File name: Name.java
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
 * Name object which holds a first initial and last name for passenger as well as methods to modify and return 
 * these variables
 * 
 * @author Christopher Runyan
 */
public class Name{
	private String lastName;
	private String firstInitial;
	
	/**
	 * Default constructor
	 */
	public Name(){
		lastName=null;
		firstInitial=null;
	}
	
	/**
	 * Constructor accepting values for a last name and first initial of passenger
	 * 
	 * @param lastName last name of passenger 
	 * @param firstInitial first initial of passenger
	 */
	public Name(String lastName, String firstInitial){
		this.lastName=lastName;
		this.firstInitial=firstInitial;
	}
	
	/**
	 * Sets the value of lastName to an accepted String value
	 * 
	 * @param lastName last name of passenger
	 */
	public void setLastName(String lastName){
		this.lastName=lastName;
	}
	
	/**
	 * Sets the value of firstInitial to an accepted String value
	 * 
	 * @param firstInitial first initial of passenger
	 */
	public void setFirstInitial(String firstInitial){
		this.firstInitial=firstInitial;
	}
	
	/**
	 * Returns the value of lastName (the last name of the passenger)
	 * 
	 * @return the last name of the passenger
	 */
	public String getLastName(){
		return lastName;
	}
	
	/**
	 * Returns the value of firstInitial (the first initial of the passenger)
	 * 
	 * @return the first initial of the passenger
	 */
	public String getFirstInitial(){
		return firstInitial;
	}
	
	/**
	 * Compares two name objects together and returns an int value based on the difference of the two in relation
	 * to their place in the alphabet
	 * 
	 * @param name the name value to compare
	 * @return an int value based on the difference of the two names in relation to their place in the alphabet
	 */
	public int compareTo(Name name){
		if(lastName.compareTo(name.getLastName())==0){
			if(firstInitial.compareTo(name.getFirstInitial())==0){
				return 0;
			}
			else{
				return firstInitial.compareTo(name.getFirstInitial());		
			}
		}
		else{
			return lastName.compareTo(name.getLastName());
		}
	}
	
	//overrides the default toString() method to a formatted output
	@Override
	public String toString(){
		return firstInitial+" "+lastName;
	}
}

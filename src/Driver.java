 /* 
  * File name: Driver.java
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Driver class for Titanic Cruise Line. Reads transaction data from a file, performs selected transaction, and then
 * prints to a file the results of the transaction.
 * 
 * @author Christopher Runyan
 */
public class Driver{
	public static void main(String[] args){
		Scanner reader=null;
		TitanicCruiseLine cruise=new TitanicCruiseLine();
		Name passengerName=null;
		final String INPUT_FILENAME="Titanic.txt";
		final String OUTPUT_FILENAME="caruny1Cruise.txt";
		int transactionCode;
		int transactionCounter=1;
		int day;
		String lastName;
		String firstInitial;

		try{
			reader=new Scanner(new File(INPUT_FILENAME));
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
			System.exit(1);
		}
		while(reader.hasNext()){
			transactionCode=reader.nextInt();
			day=reader.nextInt();
			if(!reader.hasNextInt()&&reader.hasNext()){
				lastName=reader.next();
				firstInitial=reader.next();
				passengerName=new Name(lastName, firstInitial);
			}
			Output.appendToFile(OUTPUT_FILENAME, "TRANSACTION "+transactionCounter+":\n");
			if(transactionCode==1){
				cruise.doTransactionOne(day, passengerName);
			}
			else if(transactionCode==2){
				cruise.doTransactionTwo(day, passengerName);
			}
			else if(transactionCode==3){
				cruise.doTransactionThree(day, passengerName);
			}
			else if(transactionCode==4){
				cruise.doTransactionFour(day, passengerName);
			}
			transactionCounter++;
			Output.appendToFile(OUTPUT_FILENAME, "\n");
		}
		reader.close();
	}
}

 /* 
  * File name: Output.java
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

import java.io.FileWriter;
import java.io.IOException;

/**
 * Class that contains a method to append to an output file given the file name and data to be appended
 *  
 * @author Christopher Runyan
 */
public class Output{
	
	/**
	 * Static method to append to an output file given the output final name and data to be appended to the file
	 * 
	 * @param OUTPUT_FILENAME name of the file to append data to 
	 * @param data data to append to the output file
	 */
	public static void appendToFile(String OUTPUT_FILENAME, String data){
		FileWriter fw=null;
		
		try {
			fw=new FileWriter(OUTPUT_FILENAME, true);
			fw.write(data);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}

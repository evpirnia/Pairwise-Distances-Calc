import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* 
 * Evelyn Pirnia
 * ICS 475 
 * 
 *  Homework 2 
 *  Problem: Calculate distance between all pair of sequences
 *  Input: Alignment file in the Clustal Omega alignment format
 */
public class pairseqdist { 
	static String[][] data 		= new String[50][2];	// 2-dimensional string array for names and sequences
	static int smallest 		= 10000000; 			// smallest distance 
	static int closestsmallest 	= 10000000; 			// closest's smallest distance 
	static String header 		= "CLUSTAL O(1.2.3) multiple sequence alignment";
	static String closest; 								// sequence closest to human 
	
	/* 
	 * Main function. 
	 */
	public static void main(String args[]) throws IOException {
		int count 				= readfile(args[0]);	// count is number of different sequences read in
		switch (count) {
		    case 0:
		    	System.out.println("Not enough sequences to determine distance.");
		    case 1:
		    	System.out.println("Not enough sequences to determine distance.");
		    default:
		    	// determine distance per pair
		    	getpairs(count);
		    	
		    	// smallest distance
		    	System.out.println();
		    	System.out.println("Smallest distance = " + smallest);
		    	
		    	// closest to human 
		    	System.out.println("Closest to Human is " + closest); 	 
		}
	}

	/* 
	 * Reads file into two-dimensional string array
	 * Return: Number of different sequences read in from the file
	 */
	private static int readfile(String fileloc) throws IOException {
		BufferedReader reader 	= new BufferedReader(new FileReader(fileloc));		
		int count 				= 0;
		boolean end 			= false;
		int i 					= 0;
		String line;

		while((line = reader.readLine()) != null) {			
			if(line.equals(header) || (line.length() == 0)) {
				// do nothing 
			} else if(line.contains("*")) {
				end = true;
				i = 0;
			} else {
				if(end == false) {
					//create a new char array
					String[] li = line.split("\\s+");
					data[i][0] = li[0];
					data[i][1] = li[1];
					i++;
					count++;
				} else {
					// search names for match
					// add li[1] to name's i of sequence 
					String[] li = line.split("\\s+");
					for(int k = 0; k < count; k++) {
						if(li[0].equals(data[k][0])) {
							StringBuilder sb = new StringBuilder();
							data[k][1] = sb.append(data[k][1]).append(li[1]).toString(); 
						}
					}
				}
			}
		} 
		reader.close();
		return count;
	}

	/*
	 * Gets number of pairs depending on number of sequences read in
	 * Return: nothing
	 */
	private static void getpairs(int count) {
		for (int i = 0; i < count-1; i++) {
  		      for (int j = i + 1; j < count; j++) {
  		    	  int dist = getdistance(data[i][0], data[j][0], data[i][1], data[j][1]);
  		    	  System.out.println("Distance (" + data[i][0] + ", " + data[j][0] + ") = " + dist);
  		      }
  		}
	}

	/* 
	 * Gets distance between a pair of sequences  
	 * Return: dist
	 */
	private static int getdistance(String name, String name2, String string, String string2) {
		int dist = 0;
		int i = 0;
		while(i < string.length()) {
//			System.out.println(string.toCharArray()[i] + " " + string2.toCharArray()[i] + " ");
			// if match and not a gap
			if((string.toCharArray()[i] == string2.toCharArray()[i]) 
					&& (string.toCharArray()[i] != '-')) {
				// do nothing
			}
			// if mismatch and not gap
			else {
				dist++;
//				System.out.println(dist);
//				System.out.println();
			}
			i++;
		}
		
		// update smallest distance if smallest distance
		if(dist < smallest) {
			smallest = dist;
		}

		// update closest to human 
		if((name.contains("HUMAN") == true) && (dist < closestsmallest)) {
			closestsmallest = dist;
			closest = name2;
		} else if((name2.contains("HUMAN") == true) && (dist < closestsmallest)) {
			closestsmallest = dist;
			closest = name;
		}
		return dist;
	}
}
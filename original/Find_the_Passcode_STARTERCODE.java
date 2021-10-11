import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Find_the_Passcode {

	public static void main(String[] args) throws IOException {
		
		//
		// Set the input data file name here
		//
		String fn = "length_4_N_8589.txt";

		//
		// Call helper function to read the input file
		//
		Integer[] data = read_array(fn);
		
		//
		// We need to know the length of the "strings" in this data file (for output 
		// purposes later), but we read and saved them as integers, so the best way to 
		// determine this now is to loop over the list, find the largest item, and 
		// assume that all of the items are as long as that one.
		//
		Integer max = 0;
		for (int i=0; i<data.length; i++) {
			if (data[i] > max) {
				max = data[i];
			}
		}
		int length_of_passcodes = String.valueOf(max).length();
		System.out.println("length of passcodes is " + length_of_passcodes);
		
		//
		// Print out the list of input data, for testing only
		//
		for (int i=0; i<data.length; i++) {
			//
			// Format the numbers with the correct number of leading zeroes as
			// needed according to the length of the passcodes being processed.
			//
			String fmt = "%0" + length_of_passcodes + "d";
			System.out.println(String.format(fmt, data[i]));
		}
		System.out.println(data.length + " data items in the file");
		
		//
		//---------------------------------------------------------------
		// PUT YOUR MAIN PROGRAM LOGIC HERE
		//  1) Call your function to sort the input list
		//  2) Call your function to find the smallest missing number
		//  3) Print out the smallest missing number
		//---------------------------------------------------------------
		//
		
	}

	public static Integer[] read_array(String filename) throws IOException {
		//
		// Reads the input file given by "filename", assumed to contain a list of
		// integer numbers. Stores the numbers into an array and returns the
		// array.
		//
		File file = new File(filename);
		Scanner sc = new Scanner(file);

		//
		// Read the items initially into an ArrayList (for dynamic growth)
		//
		ArrayList<Integer> input_list = new ArrayList<Integer>();		
		while (sc.hasNext()) {			
			int n = sc.nextInt();
			input_list.add(n);
		}

		//
		// Copy the ArrayList to an Integer[] array of the proper size
		//
        Integer[] arr = new Integer[input_list.size()]; 
        arr = input_list.toArray(arr); 
		return arr;
	}
}
